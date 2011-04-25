//================================================================
//
//	DataFrag.java - Created by kerush, 2006 24-ott-06 12:54:34 
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
import rtjdds.rtps.messages.elements.KeyHashPrefix;
import rtjdds.rtps.messages.elements.KeyHashSuffix;
import rtjdds.rtps.messages.elements.LongWrapperSubmessageElement;
import rtjdds.rtps.messages.elements.ParameterList;
import rtjdds.rtps.messages.elements.SequenceNumber;
import rtjdds.rtps.messages.elements.SerializedData;
import rtjdds.rtps.messages.elements.ShortWrapperSubmessageElement;
import rtjdds.rtps.portable.CDROutputPacket;
import rtjdds.rtps.types.DATA_FRAG;

public class DataFrag extends Submessage {
	
	protected EntityId readerId;
	protected EntityId writerId;
	protected SequenceNumber writerSN;
	protected KeyHashPrefix khp;
	protected KeyHashSuffix khs;
	protected ParameterList inlineQoS;
	protected FragmentNumber fsn;
	protected ShortWrapperSubmessageElement fis;
	protected ShortWrapperSubmessageElement fsize;
	protected LongWrapperSubmessageElement sampleSize;
	protected SerializedData serializedData;
	
	
	public DataFrag(EntityId readerId, EntityId writerId, SequenceNumber writerSN, 
			KeyHashPrefix khp, KeyHashSuffix khs, ParameterList inlineQoS,
			FragmentNumber fsn, ShortWrapperSubmessageElement fis, ShortWrapperSubmessageElement fsize,
			LongWrapperSubmessageElement sampleSize, SerializedData serializedData) {
		// set the kind of submessage
		super(DATA_FRAG.value);
		this.readerId = readerId;
		this.writerId = writerId;
		this.writerSN = writerSN;
		this.khp = khp;
		this.khs = khs;
		this.inlineQoS = inlineQoS;
		this.fsn = fsn;
		this.fis = fis;
		this.fsize = fsize;
		this.sampleSize = sampleSize;
		this.serializedData = serializedData;
		// mandatory fields that sets flags in the header
		// KeyHash sets H
		if (khp != null && khs != null) {
			super.setFlagAt(2,true);
		}
		// inlineQoS sets Q
		if (inlineQoS != null) {
			super.setFlagAt(1,true);
		}
	}


	protected void writeBody(CDROutputPacket os) {
		this.readerId.write(os);
		this.writerId.write(os);
		this.writerSN.write(os);
		if (super.getFlagAt(2)) {
			this.khp.write(os);
			this.khs.write(os);
		}
		if (super.getFlagAt(1)) {
			this.inlineQoS.write(os);
		}
		this.fsn.write(os);
		this.fis.write(os);
		this.fsize.write(os);
		this.sampleSize.write(os);
		this.serializedData.write(os);
	}

}
