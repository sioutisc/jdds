//================================================================
//
//	HeartBeatFrag.java - Created by kerush, 2006 24-ott-06 1:25:18 
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
package rtjdds.rtps.messages;

import rtjdds.rtps.messages.elements.Count;
import rtjdds.rtps.messages.elements.EntityId;
import rtjdds.rtps.messages.elements.FragmentNumber;
import rtjdds.rtps.messages.elements.SequenceNumber;
import rtjdds.rtps.portable.CDROutputPacket;
import rtjdds.rtps.types.HEARTBEAT_FRAG;

public class HeartBeatFrag extends Submessage {
	
	protected EntityId readerId;
	protected EntityId writerId;
	protected SequenceNumber writerSN;
	protected FragmentNumber fn;
	protected Count count;
	
	public HeartBeatFrag(EntityId readerId, EntityId writerId, SequenceNumber writerSN, FragmentNumber fn, Count count) {
		super(HEARTBEAT_FRAG.value);
		this.readerId = readerId;
		this.writerId = writerId;
		this.writerSN = writerSN;
		this.fn = fn;
		this.count = count;
	}

	protected void writeBody(CDROutputPacket os) {
		this.readerId.write(os);
		this.writerId.write(os);
		this.writerSN.write(os);
		this.fn.write(os);
		this.count.write(os);
	}

}
