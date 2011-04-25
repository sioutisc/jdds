//================================================================
//
//	SubmessageDispatcher.java - Created by kerush, 2006 19-nov-06 7:34:25 
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

import rtjdds.rtps.messages.AckNack;
import rtjdds.rtps.messages.Data;
import rtjdds.rtps.messages.DataFrag;
import rtjdds.rtps.messages.Gap;
import rtjdds.rtps.messages.HeartBeat;
import rtjdds.rtps.messages.HeartBeatFrag;
import rtjdds.rtps.messages.NackFrag;
import rtjdds.rtps.messages.NoKeyData;
import rtjdds.rtps.messages.NoKeyDataFrag;

/**
 * @author kerush
 *
 */
public abstract class SubmessageDispatcher {
	
	// ----------------------------------------
	// Submessage-specific dispatching methods
	// ----------------------------------------
	
	public abstract void dispatch(Data data);
	
	public abstract void dispatch(DataFrag dataFrag);
	
	public abstract void dispatch(NoKeyData noKeyData);
	
	public abstract void dispatch(NoKeyDataFrag noKeyDataFrag);
	
	public abstract void dispatch(HeartBeat heartBeat);
	
	public abstract void dispatch(AckNack ackNack);
	
	public abstract void dispatch(HeartBeatFrag heartBeatFrag);
	
	public abstract void dispatch(NackFrag nackFrag);

	public abstract void dispatch(Gap gap);


}
