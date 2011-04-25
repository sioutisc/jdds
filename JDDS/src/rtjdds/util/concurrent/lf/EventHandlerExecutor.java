//================================================================
//
//	EventHandlerLauncher.java - Created by kerush, 2007 09/feb/07 16:53:14
//
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

import javax.realtime.SchedulingParameters;

import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;
import rtjdds.util.concurrent.executor.Executor;
import rtjdds.util.concurrent.executor.ShutdownExecutorException;
import rtjdds.util.concurrent.executor.ThreadBoundExecutor;

/**
 * This class contains the logic for the threads contained in
 * <code>LeaderFollowerTP</code>. It self contains its executor
 * (a <code>RealtimeThread</code> wrapped by an <code>Executor</code>.<br/>
 * The logic of this <code>Runnable</code> is the main loop of a 
 * leader/follower thread:
 * <ol>
 * 	<li><b>Follower.</b> Waits to be leader.</li>
 * 	<li><b>Leader.</b> Calls the associated handler. </li>
 * </ol>
 * Note that in that loop there is no memory context change.
 * It is the handler responsibility to operate each handling
 * operation in its own scope.
 * 
 * Private class (only used by LeaderFollowerTP)
 * @author kerush
 *
 */
class EventHandlerExecutor implements Runnable {
	
	private static int _staticId = 0;
	
	private int _id = 0;
	
	private Executor _executor = null;
	
	private EventHandler _handler = null;
	
	private LeaderFollowerTP _pool = null;
	
	private boolean _isActive = false;
	
	private boolean _isShuttingDown = false;
	
	public EventHandlerExecutor(
			EventHandler handler, LeaderFollowerTP pool, 
			SchedulingParameters prioParms, boolean noHeap) {
		_handler = handler;
		_pool = pool;
		_executor = new ThreadBoundExecutor(prioParms,null,null,null,null,noHeap);
		_id = _staticId++;
	}
	
	private void beFollower() {
		_pool.returnToPool();
	}
	
	public synchronized void start() {
		try {
			_isActive = true;
			_executor.execute(this);
		} catch (ShutdownExecutorException e) {
			if (!_isShuttingDown) {
				e.printStackTrace();
			}
			else {
				GlobalProperties.logger.log(Logger.INFO, getClass(), "start()", 
				_id+". Thread shutted down");
			}
		}
	}
	
	/**
	 * This is a bit complex. Shutdown depends on the state of the thread:
	 * 1.	follower:	it is blocked on a sem. It has to be waked up and naturally die
	 * 2.	leader:		it is blocked on a syscall. It has to be waked up and naturally die (without serving the request)
	 * 3.	working:	it is handling a request. We have to wait until the handling completes (yeld)
	 *
	 * In all the three cases the follow/lead/serve loop needs to be broken.
	 * 
	 * TODO Implement me Implement me Implement me Implement me Implement me 
	 */
	public synchronized void shutdown() {
		/* breaks the main loop */
		_isActive = false;
		// TODO implement me
		_isShuttingDown = true;
	}
	
	/**
	 * The main loop.
	 */
	public void run() {
		while (_isActive) {
//			GlobalProperties.logger.log(Logger.PEDANTIC, getClass(), "run()", 
//					_id+". Following");
			beFollower(); // waits on a semaphore
//			GlobalProperties.logger.log(Logger.PEDANTIC, getClass(), "run()", 
//					_id+". Leading");
			if (!_isShuttingDown) {
				/* here i'm the leader */
				//_pool.promoteLeader(); // TODO fare confronto tra elezione qui o nell'handler...
				_handler.execute();
			}
		}
	}
}
