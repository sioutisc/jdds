//================================================================
//
//	EventHandler.java - Created by kerush, 2007 09/feb/07 17:19:21
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

import javax.realtime.LTMemory;
import javax.realtime.MemoryArea;

import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;

public abstract class EventHandler implements Runnable {

	private MemoryArea _handlerScope = null;
	
	private LeaderFollowerTP _pool = null;
	
	private boolean _isActive = true;
	
	private boolean _isBlocked = false;
	
	public EventHandler(long memSize) {
		// TODO use PrivateScopedMemory if the runtime is jRate
		_handlerScope = new LTMemory(memSize, memSize);
	}
	
//	public EventHandler(MemoryArea mem) {
//		_handlerScope = mem;
//	}
	
	public void assignToPool(LeaderFollowerTP pool) {
		_pool = pool;
	}
	
	/**
	 * Entry point for the EventHandlerLauncher.
	 * THIS RUNS IN THE POOL SCOPE
	 */
	Event _currentEvent = null;
	protected final void execute() {
		
		//TODO this code should be moved into handler scope section
		//     The user can consume our memory in its pollEvent()!!
	    //synchronized (this) {
			/* BEGIN OF IMPLICITLY SYNCHRONIZED SECTION */
			_isBlocked = true;
			// TODO select() è cazzo scope-unsafe!!!
			_currentEvent = pollEvent(); // this can block
			_isBlocked = false;
			/* END OF IMPLICITLY SYNCHRONIZED SECTION */
			//	}
		
		/* handle the event */
		_handlerScope.enter(this);
	}
	
	/**
	 * Softly shuts down the handler, even if it is 
	 * waiting for an event.
	 *
	 */
	public void shutdown() {
		synchronized (this) {
			if (_isBlocked) {
				unblockPolling();
			}
			_isActive = false;
		}
	}
	
	/* in handler's scope */
	public void run() {
		
//		GlobalProperties.logger.log(Logger.INFO, getClass(), "run()", 
//			"Working");
			if (_isActive) {
//				GlobalProperties.logger.log(Logger.INFO, getClass(), "run()",
//				"Promoting Leader");
				_pool.promoteLeader();
				/* execute the handler */
				handleEvent(_currentEvent);
			}
		
	}
	
	/**
	 * If it waits for an event, implicitly releases the lock
	 * @return
	 */
	public abstract Event pollEvent();
	
	public abstract void unblockPolling();
	
	public abstract void handleEvent(Event ev);

}
