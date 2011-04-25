//================================================================
//
//	NoKeyDataFrag.java - Created by kerush, 2006 24-ott-06 12:11:00 
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
import rtjdds.rtps.messages.elements.FragmentNumber;
import rtjdds.rtps.messages.elements.ParameterList;
import rtjdds.rtps.messages.elements.SequenceNumber;
import rtjdds.rtps.messages.elements.SerializedData;
import rtjdds.rtps.portable.CDROutputPacket;
import rtjdds.rtps.types.NOKEY_DATA_FRAG;

public class NoKeyDataFrag extends Submessage {
	
	private EntityId readerId;
	private EntityId writerId;
	private SequenceNumber writerSN;
	private ParameterList inlineQoS;
	private FragmentNumber fragmentStartingNum;
	private short fragmentsInSubmessage;
	private short fragmentSize;
	private int sampleSize;
	private SerializedData serializedData;
	
	
	public NoKeyDataFrag(EntityId readerId, EntityId writerId, SequenceNumber writerSN,
			ParameterList inlineQoS, FragmentNumber fragmentStartingNum,
			short fragmentsInSubmessage, short 
			fragmentSize, int sampleSize, SerializedData serializedData) {
		super(NOKEY_DATA_FRAG.value);
		this.readerId = readerId;
		this.writerId = writerId;
		this.writerSN = writerSN;
		this.inlineQoS = inlineQoS;
		this.fragmentStartingNum = fragmentStartingNum;
		this.fragmentsInSubmessage = fragmentsInSubmessage;
		this.fragmentSize = fragmentSize;
		this.sampleSize = sampleSize;
		this.serializedData = serializedData;
		// if inlineQoS is present, set the Q flag
		if (inlineQoS != null) {
			super.setFlagAt(1,true);
		}
	}


	protected void writeBody(CDROutputPacket os) {
		readerId.write(os);
		writerId.write(os);
		writerSN.write(os);
		if (super.getFlagAt(1)) {
			inlineQoS.write(os);
		}
		fragmentStartingNum.write(os);
		os.write_short(fragmentsInSubmessage);
		os.write_short(fragmentSize);
		os.write_long(sampleSize);
		serializedData.write(os);
	}
	
	


}
