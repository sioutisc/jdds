//================================================================
//
//	LIFOThreadPool.java - Created by kerush, 2007 12/feb/07 11:12:05
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

import java.util.Stack;

import javax.realtime.RealtimeThread;

import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;
import rtjdds.util.collections.RTLinkedList;
import rtjdds.util.collections.RTList;

public class LIFOLeaderFollowerTP extends LeaderFollowerTP {
	
//	private int _stackTop = 0;
	
	private Stack _followerStack = new Stack();
	
	/* an array of private binary semaphores */
//	private Integer _sems[] = null;
	
//	private volatile Integer _lock = new Integer(0);

	public LIFOLeaderFollowerTP(int numThreads, int defaultPriority, boolean noHeap, EventHandler[] handler) {
		super(numThreads, defaultPriority, noHeap, handler);
//		_sems = new Integer[numThreads];
//		for (int i=0; i<numThreads; i++) {
//			_sems[i] = new Integer(0);
//		}
//		_stackTop = 0;
	}

	protected void promoteLeader() {
		if (!_followerStack.isEmpty()) {
			GlobalProperties.logger.log(Logger.SEVERE, getClass(), "promoteLeader()", "Going to pop()");
			RealtimeThread leader = (RealtimeThread)_followerStack.pop();
			GlobalProperties.logger.log(Logger.SEVERE, getClass(), "promoteLeader()", "popped");
			synchronized (leader) {
				leader.notify();
			}
		}
		else {
			GlobalProperties.logger.log(Logger.SEVERE, getClass(), "promoteLeader()", "No followers available");
		}
	}

	/**
	 * In this implementation each follower waits on its own (private) semaphore.
	 * The last returned thread is the first that will be woken up.
	 */
	protected void returnToPool() {
		RealtimeThread caller = null;
//		synchronized (this) {
			caller = RealtimeThread.currentRealtimeThread();
			_followerStack.push(caller);
			GlobalProperties.logger.log(Logger.SEVERE, getClass(), "returnToPool()", "Pushed, current stack size = " + _followerStack.size());
//		}
		synchronized (caller) {
			try {
				GlobalProperties.logger.log(Logger.SEVERE, getClass(), "returnToPool()", "Waiting");
				caller.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
