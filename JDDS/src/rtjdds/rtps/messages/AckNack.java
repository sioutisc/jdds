//================================================================
//
//	AckNackSubmessage.java - Created by kerush, 2006 24-ott-06 12:04:25 
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
import rtjdds.rtps.messages.elements.SequenceNumberSet;
import rtjdds.rtps.portable.CDROutputPacket;
import rtjdds.rtps.types.ACKNACK;

public class AckNack extends Submessage {
	
	private EntityId readerId = null;
	private EntityId writerId = null;
	private SequenceNumberSet sns = null;
	private Count count = null;
	
	public AckNack(EntityId readerId, EntityId writerId, SequenceNumberSet sns, Count count) {
		super(ACKNACK.value);
		this.readerId = readerId;
		this.writerId = writerId;
		this.sns = sns;
		this.count = count;
	}

//	public AckNack(CDRInputPacket packet) throws MalformedSubmessageException {
//		super(ACKNACK.value);
//		super.readHeader(packet);
//		// INTO THE BODY
//		this.readerId = new EntityId(EntityId_tHelper.read(packet));
//		this.writerId = new EntityId(EntityId_tHelper.read(packet));
//		try {
//			this.sns = SequenceNumberSet.read(packet);
//		} catch (MalformedSubmessageElementException e) {
//			throw new MalformedSubmessageException("Message incorrect");
//		}
//		this.count = new Count(Count_tHelper.read(packet));
//	}
	
	
	/**
	 * Checks if the Reader requires or not a response from the Writer 
	 * @return
	 */
	public boolean isFinal() {
		return getFlagAt(1);
	}
	
	public void setFinal(boolean value) {
		super.setFlagAt(1,value);
	}

	protected void writeBody(CDROutputPacket os) {
		readerId.write(os);
		writerId.write(os);
		sns.write(os);
		count.write(os);
	}

}
