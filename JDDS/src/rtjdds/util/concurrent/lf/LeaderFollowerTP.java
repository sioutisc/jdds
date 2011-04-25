//================================================================
//
//	ThreadPool.java - Created by kerush, 2007 09/feb/07 16:33:25
//
//================================================================
// $ Id $
//================================================================
//
//					  R T  j  D D S  version 0.1
//
//				Copyright (C) 2006 by Vincenzo Caruso
//					   <bitclash[at]gmail.com>
//
// This program is free software; you can redistribute it and/or
// modify it under  the terms of  the GNU General Public License
// as published by the Free Software Foundation;either version 2
// of the License, or (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or  FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// A copy of the GNU  General Public License  is available in the
// file named LICENSE,  that  should  be  shipped along with this
// package;  if not,  write to the Free Software Foundation, Inc., 
// 51 Franklin Street, Fifth Floor,   Boston, MA  02110-1301, USA.
//
//================================================================
package rtjdds.util.concurrent.lf;

import javax.realtime.MemoryArea;
import javax.realtime.NoHeapRealtimeThread;
import javax.realtime.PriorityParameters;
import javax.realtime.RealtimeThread;
import javax.realtime.SchedulingParameters;

import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;
import rtjdds.util.concurrent.executor.Executor;
import rtjdds.util.concurrent.executor.ShutdownExecutorException;
import rtjdds.util.concurrent.executor.ThreadBoundExecutor;

public abstract class LeaderFollowerTP {

	protected EventHandlerExecutor[] _executors = null;
	
	protected int _defPrio = RealtimeThread.MIN_PRIORITY;
	
	private EventHandler _handler = null;
	
	private PriorityParameters _prioParms = null;
	
	private boolean _isActive = false;
	
	private int _numFollowers = 0;
	
	private Integer _waitLock = new Integer(0);
		
	/**
	 * Creates the pool along with the contained threads. No memory area is entered in this
	 * constructor, everything is allocated in the caller scope.
	 * 
	 * @param numThreads the number of threads contained into the pool
	 * @param defaultPriority default priority of contained threads
	 * @param noHeap indicates if internal threads can touch or not the heap
	 * @param handler the handler's logic
	 */
	public LeaderFollowerTP(int numThreads, int defaultPriority, boolean noHeap, EventHandler[] handler) {
		_executors = new EventHandlerExecutor[numThreads];
//		_handler = handler;
		_prioParms = new PriorityParameters(defaultPriority);
		_defPrio = defaultPriority;
		for (int i=0; i<numThreads; i++) {
			_executors[i] = new EventHandlerExecutor(handler[i] /*_handler modified*/,this,_prioParms,noHeap);
			handler[i].assignToPool(this);
		}
//		_handler.assignToPool(this);
	}
	
	/**
	 * Starts the accepting procedure by internally promoting the first leader.
	 *
	 */
	public synchronized void start() {
		if (!_isActive) {
			/* 1. start all the threads */
			for (int i=0; i<_executors.length; i++) {
				_executors[i].start();
			} 
			/* TODO change here!!! */
			/* 1.5 DUMMY IMPL. wait for all threads to join the pool */
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {}
			/* 2. promote the first leader */
			promoteLeader();
			/* 3. mark the pool as active */
			_isActive = true;
			/* 4. log something */
			GlobalProperties.logger.log(Logger.INFO, getClass(), "start()", 
			"Leader/Follower Pool started ");
		}
		else {
			GlobalProperties.logger.log(Logger.INFO, getClass(), "start()", 
			"Leader/Follower pool no more usable, is has already been used ");
		}
	}
	
	/**
	 * Terminates all the managed threads. The termination of each thread depends on its state:
	 * 	- handling:		wait until handling is done.
	 * 	- leading:		the select() is interrupted and the thread dies immediately.
	 * 	- following:	the wait() is interrupted and the thread dies immediately.
	 * This method blocks until all the threads are died.
	 */
	public synchronized void shutdown() {
		if (_isActive) {
			/* 1. stop all the threads */
			for (int i=0; i<_executors.length; i++) {
				_executors[i].shutdown();
			} 
			/* 2. mark the pool as inactive */
			_isActive = false;
		}
		else {
			GlobalProperties.logger.log(Logger.INFO, getClass(), "shutdown()", 
			"Leader/Follower pool already shut down");
		}
	}
	
	/**
	 * Promotes a new leader
	 *
	 */
	protected abstract void promoteLeader();
	
	/**
	 * Returns the calling thread to the pool.
	 *
	 */
	protected abstract void returnToPool();
	
}
