//================================================================
//
//	Data.java - Created by kerush, 2006 24-ott-06 12:26:56 
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
import rtjdds.rtps.messages.elements.KeyHashPrefix;
import rtjdds.rtps.messages.elements.KeyHashSuffix;
import rtjdds.rtps.messages.elements.ParameterList;
import rtjdds.rtps.messages.elements.SequenceNumber;
import rtjdds.rtps.messages.elements.SerializedData;
import rtjdds.rtps.messages.elements.StatusInfo;
import rtjdds.rtps.portable.CDROutputPacket;
import rtjdds.rtps.types.DATA;

public class Data extends Submessage {
	
	protected EntityId readerId;
	protected EntityId writerId;
	protected SequenceNumber writerSN;
	protected KeyHashPrefix khp;
	protected KeyHashSuffix khs;
	protected StatusInfo si;
	protected ParameterList inlineQoS;
	protected SerializedData serializedData;
	
	public Data(EntityId readerId, EntityId writerId, SequenceNumber writerSN, 
			KeyHashPrefix khp, KeyHashSuffix khs, StatusInfo si, 
			ParameterList inlineQoS,SerializedData serializedData) {
		// set the kind of submessage
		super(DATA.value);
		this.readerId = readerId;
		this.writerId = writerId;
		this.writerSN = writerSN;
		this.khp = khp;
		this.khs = khs;
		this.si = si;
		this.inlineQoS = inlineQoS;
		this.serializedData = serializedData;
		// mandatory fields that sets flags in the header
		// KeyHash sets H
		if (khp != null && khs != null) {
			super.setFlagAt(3,true);
		}
		// statusInfo sets I
		if (si != null) {
			super.setFlagAt(4,true);
		}
		// inlineQoS sets Q
		if (inlineQoS != null) {
			super.setFlagAt(1,true);
		}
		// serializedData sets D
		if (serializedData != null) {
			super.setFlagAt(2,true);
		}
	}
	
	

	/**
	 * @return Returns the inlineQoS.
	 */
	public ParameterList getInlineQoS() {
		return inlineQoS;
	}



	/**
	 * @return Returns the khp.
	 */
	public KeyHashPrefix getKhp() {
		return khp;
	}



	/**
	 * @return Returns the khs.
	 */
	public KeyHashSuffix getKhs() {
		return khs;
	}



	/**
	 * @return Returns the readerId.
	 */
	public EntityId getReaderId() {
		return readerId;
	}



	/**
	 * @return Returns the serializedData.
	 */
	public SerializedData getSerializedData() {
		return serializedData;
	}



	/**
	 * @return Returns the si.
	 */
	public StatusInfo getSi() {
		return si;
	}



	/**
	 * @return Returns the writerId.
	 */
	public EntityId getWriterId() {
		return writerId;
	}



	/**
	 * @return Returns the writerSN.
	 */
	public SequenceNumber getWriterSN() {
		return writerSN;
	}



	protected void writeBody(CDROutputPacket os) {
		this.readerId.write(os);
		this.writerId.write(os);
		this.writerSN.write(os);
		// mandatory fields that sets flags in the header
		// KeyHash sets H
		if (super.getFlagAt(3)) {
			this.khp.write(os);
			this.khs.write(os);;
		}
		// statusInfo sets I
		if (super.getFlagAt(4)) {
			this.si.write(os);
		}
		// inlineQoS sets Q
		if (super.getFlagAt(1)) {
			this.inlineQoS.write(os);
		}
		// serializedData sets D
		if (super.getFlagAt(2)) {
			this.serializedData.write(os);
		}
	}
	
	
	
	


}
