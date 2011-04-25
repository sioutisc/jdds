//================================================================
//
//	Gap.java - Created by kerush, 2006 24-ott-06 1:10:57 
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

import rtjdds.rtps.messages.elements.EntityId;
import rtjdds.rtps.messages.elements.SequenceNumber;
import rtjdds.rtps.messages.elements.SequenceNumberSet;
import rtjdds.rtps.portable.CDROutputPacket;
import rtjdds.rtps.types.GAP;

public class Gap extends Submessage {
	
	protected EntityId readerId;
	protected EntityId writerId;
	protected SequenceNumber gapStart;
	protected SequenceNumberSet gapList;

	public Gap(EntityId readerId, EntityId writerId, SequenceNumber gapStart, SequenceNumberSet gapList) {
		super(GAP.value);
		this.readerId = readerId;
		this.writerId = writerId;
		this.gapStart = gapStart;
		this.gapList = gapList;
	}

	protected void writeBody(CDROutputPacket os) {
		this.readerId.write(os);
		this.writerId.write(os);
		this.gapStart.write(os);
		this.gapList.write(os);
	}
	
}
