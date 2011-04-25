//================================================================
//
//	NoKeyData.java - Created by kerush, 2006 20-ott-06 12:27:49 
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
import rtjdds.rtps.messages.elements.ParameterList;
import rtjdds.rtps.messages.elements.SequenceNumber;
import rtjdds.rtps.messages.elements.SerializedData;
import rtjdds.rtps.portable.CDROutputPacket;
import rtjdds.rtps.types.NOKEY_DATA;

public class NoKeyData extends Submessage {
	
	private EntityId readerId;
	private EntityId writerId;
	private SequenceNumber writerSN;
	private ParameterList inlineQoS;
	private SerializedData serializedData;
	
	/**
	 * Constructs a NoKeyData submessage that contains SerializedData
	 * @param readerId
	 * @param writerId
	 * @param writerSN
	 * @param inlineQoS
	 * @param serializedData
	 */
	public NoKeyData(EntityId readerId, EntityId writerId, SequenceNumber writerSN, 
			ParameterList inlineQoS,SerializedData serializedData) {
		super(NOKEY_DATA.value);
		this.readerId = readerId;
		this.writerId = writerId;
		this.writerSN = writerSN;
		this.inlineQoS = inlineQoS;
		this.serializedData = serializedData;
		// optional (implies Q flag)
		if (inlineQoS != null) {
			super.setFlagAt(1,true);
		}
		// optional (implies D flag)
		if (serializedData != null) {
			super.setFlagAt(2,true);
		}
	}
	
//	public NoKeyData(CDRInputPacket packet) throws MalformedSubmessageException {
//		super(NOKEY_DATA.value);
//		super.readHeader(packet);
//		int messageBodyStart = packet.getCursorPosition();
//		this.readerId = new EntityId(EntityId_tHelper.read(packet));
//		this.writerId = new EntityId(EntityId_tHelper.read(packet));
//		this.writerSN = new SequenceNumber(SequenceNumber_tHelper.read(packet));
//		// ParameterList inlineQoS
//		if (BitUtility.getFlagAt(flags,1)) {
//			this.inlineQoS = new ParameterList(packet);
//		}
//		// Serialized Data serializedData
//		if (BitUtility.getFlagAt(flags,2)) {
//			int dataLength = this.octetsToNextHeader - (packet.getCursorPosition() - messageBodyStart) +1;
//			this.serializedData = new SerializedData(packet,dataLength);
//		}
//	}

	protected void writeBody(CDROutputPacket os) {
		readerId.write(os);
		writerId.write(os);
		writerSN.write(os);
		if (this.getFlagAt(1)) {
			inlineQoS.write(os);
		}
		if (this.getFlagAt(2)) {
			serializedData.write(os);
		}
	}

	public ParameterList getInlineQoS() {
		return inlineQoS;
	}

	public EntityId getReaderId() {
		return readerId;
	}

	public SerializedData getSerializedData() {
		return serializedData;
	}

	public EntityId getWriterId() {
		return writerId;
	}

	public SequenceNumber getWriterSN() {
		return writerSN;
	}


}
