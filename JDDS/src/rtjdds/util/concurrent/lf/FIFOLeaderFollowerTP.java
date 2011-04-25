//================================================================
//
//	FIFOThreadPool.java - Created by kerush, 2007 11/feb/07 12:20:37
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

import javax.realtime.RealtimeThread;

import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;

public class FIFOLeaderFollowerTP extends LeaderFollowerTP {
	
	private volatile Integer _sem = new Integer(0);
	
	public FIFOLeaderFollowerTP (int numThreads, int defaultPriority, boolean noHeap, EventHandler[] handler) {
		super(numThreads, defaultPriority, noHeap, handler);
	}
	
	protected void promoteLeader() {
		synchronized (_sem) {
			_sem.notify();
		}
	}

	protected void returnToPool() {
		RealtimeThread.currentRealtimeThread().setPriority(_defPrio);
		synchronized (_sem) {
			try {
				_sem.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
