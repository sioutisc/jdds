//================================================================
//
//	ReceiverPool.java - Created by kerush, 2006 6-dic-06 12:57:55 
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

import java.io.IOException;

import javax.realtime.ImmortalMemory;
import javax.realtime.PriorityScheduler;

import rtjdds.rtps.database.Database;
import rtjdds.rtps.transport.ReceiverFactory;
import rtjdds.rtps.transport.UDPMulticastReceiver;
import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;

/**
 *  
 * 
 * @author kerush
 *
 */
public class MulticastReceiverPool extends ReceiverPool {
	
	private ReceiverThread[] _lanes = null;
	
	private static int minPrio = PriorityScheduler.MIN_PRIORITY;
	private static int maxPrio = PriorityScheduler.MAX_PRIORITY;
	private static int priorityRange = maxPrio - minPrio; 
	
	public MulticastReceiverPool(int nLanes, int domainId, Database db) throws IOException {
		
		_lanes = new ReceiverThread[nLanes];
		int step = priorityRange / nLanes;
		for (int i=0; i<nLanes; i++) {
			/* receiver (the socket) */
			UDPMulticastReceiver receiver = ReceiverFactory.createUserMulticastReceiver(domainId, i);
			int priority = minPrio + step * i;
			_lanes[i] = new ReceiverThread(receiver,db,priority);
		}
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"new()",
				"Created Receiver Pool of "+nLanes+" lanes, ranging from priority "+minPrio+" to "+maxPrio);
		
	}
	
	public void start() {
		for (int i=0; i<_lanes.length; i++) {
			_lanes[i].start();
		}
	}

	public void shutdown() {
		for (int i=0; i<_lanes.length; i++) {
			_lanes[i].shutdown();
		}
	}

}

