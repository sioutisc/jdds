//================================================================
//
//	SubmessageDispatcherImpl.java - Created by kerush, 2006 20-nov-06 12:55:36 
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

import rtjdds.rtps.database.Database;
import rtjdds.rtps.messages.AckNack;
import rtjdds.rtps.messages.Data;
import rtjdds.rtps.messages.DataFrag;
import rtjdds.rtps.messages.Gap;
import rtjdds.rtps.messages.HeartBeat;
import rtjdds.rtps.messages.HeartBeatFrag;
import rtjdds.rtps.messages.NackFrag;
import rtjdds.rtps.messages.NoKeyData;
import rtjdds.rtps.messages.NoKeyDataFrag;
import rtjdds.rtps.structure.TopicImpl;
import rtjdds.rtps.subscription.Reader;
import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;
import rtjdds.util.collections.RTListIterator;

public class SubmessageDispatcherImpl extends SubmessageDispatcher {
	
	/* Hashtables holding references to active entities */
	private Database _db = null;
	
	/* object used for synchronization */
	private Integer _sync = new Integer(0);
	
	
	public SubmessageDispatcherImpl(Database db) {
		_db = db;
	}

	
	// ---------------------------------------
	// Submessage-specific dispatching
	// ---------------------------------------

	public void dispatch(Data data) {
		TopicImpl topic = _db.lookupTopic(data.getWriterId());
		if (topic != null) {
			RTListIterator iterator = topic.localReadersIterator();
			while (iterator.hasNext()) {
				((Reader)iterator.next()).accept(data);
			}
		}
		else {
			GlobalProperties.logger.log(Logger.WARN,this.getClass(),"dispatch(Data)",
					"topic with EntityId "+data.getWriterId()+" not found! Dropping the message");
		}
	}

	public void dispatch(DataFrag dataFrag) {
		// TODO Auto-generated method stub

	}

	public void dispatch(NoKeyData noKeyData) {
		TopicImpl topic = _db.lookupTopic(noKeyData.getWriterId());
		if (topic != null) {
			RTListIterator iterator = topic.localReadersIterator();
			while (iterator.hasNext()) {
				((Reader)iterator.next()).accept(noKeyData);
			}
		}
		else {
			GlobalProperties.logger.log(Logger.WARN,this.getClass(),"dispatch(Data)",
					"topic with EntityId "+noKeyData.getWriterId()+" not found! Dropping the message");
		}
		
	}

	public void dispatch(NoKeyDataFrag noKeyDataFrag) {
		// TODO Auto-generated method stub

	}

	public void dispatch(HeartBeat heartBeat) {
		// TODO Auto-generated method stub

	}

	public void dispatch(AckNack ackNack) {
		// TODO Auto-generated method stub

	}

	public void dispatch(HeartBeatFrag heartBeatFrag) {
		// TODO Auto-generated method stub

	}

	public void dispatch(NackFrag nackFrag) {
		// TODO Auto-generated method stub

	}

	public void dispatch(Gap gap) {
		// TODO Auto-generated method stub

	}

}
