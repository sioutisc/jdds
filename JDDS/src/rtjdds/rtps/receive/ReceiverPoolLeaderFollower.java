//================================================================
//
//	ReceiverPoolLeaderFollower.java - Created by kerush, 2007 28/feb/07 16:12:01
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
package rtjdds.rtps.receive;

import java.io.File;
import java.io.IOException;

import rtjdds.rtps.database.Database;
import rtjdds.util.GlobalProperties;
import rtjdds.util.concurrent.lf.FIFOLeaderFollowerTP;
import rtjdds.util.concurrent.lf.LeaderFollowerTP;

public class ReceiverPoolLeaderFollower extends ReceiverPool {
	
	private LeaderFollowerTP _pool = null; 
	
	public ReceiverPoolLeaderFollower(int numThreads, int topicPriority, 
			boolean noHeap, Database db, ChannelSelector channel) throws IOException {
		
		ReceiverLogic threadLogic[] = new ReceiverLogic[numThreads];
		for (int i=0; i<numThreads; i++) {
			threadLogic[i] = new ReceiverLogic(
				GlobalProperties.SCRATCH_SCOPE_SIZE, channel, GlobalProperties.BUFFER_SIZE,db);
		}
		
		_pool = new FIFOLeaderFollowerTP(numThreads, topicPriority, noHeap, threadLogic);
		
	}

	public void start() {
		_pool.start();
	}
	
	public void shutdown() {
		_pool.shutdown();
	}

}
