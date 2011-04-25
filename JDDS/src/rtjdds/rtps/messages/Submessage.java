//================================================================
//
//	Submessage.java - Created by kerush, 2006 19-ott-06 2:45:12 
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


import rtjdds.rtps.messages.elements.GuidPrefix;
import rtjdds.rtps.messages.elements.Timestamp;
import rtjdds.rtps.portable.CDROutputPacket;
import rtjdds.util.BitUtility;

public abstract class Submessage {
	
	public static final int HEADER_SIZE = 4;
	
	protected byte kind  = 0x00;
	protected byte flags = 0x00;
	protected int octetsToNextHeader = 0;
	
	/* optional attributes setted during decoding process */
	protected Timestamp timestamp = null;
	protected GuidPrefix srcGuidPrefix = null;
	
	public Submessage(byte kind) {
		this.kind = kind;
	}
	
	public int getSize() {
		return octetsToNextHeader;
	}
	
	public int getSizeWithHeader() {
		return octetsToNextHeader + HEADER_SIZE;
	}
	
	public void write(CDROutputPacket os) {
		// endianess
		os.setEndianess( ! getFlagAt(0) );
		int initialPosition = os.getCursorPosition();
		// leaves space for the header (4 bytes)
		os.setCursorPosition(initialPosition + HEADER_SIZE);
		// first the body is written
		writeBody(os);
		int finalPosition = os.getCursorPosition(); 
		this.octetsToNextHeader = (short)(finalPosition - initialPosition - HEADER_SIZE);
		// then the header
		os.setCursorPosition(initialPosition);
		os.write_octet(kind);
		os.write_octet(flags);
		os.write_short((short)this.octetsToNextHeader);
		os.setCursorPosition(finalPosition);
	}
	
//	public void readHeader(CDRInputPacket packet) throws MalformedSubmessageException {
//		// checking header length
//		if (packet.getCursorPosition()+3 > packet.getLength()) {
//			throw new MalformedSubmessageException("Message TOO small");
//		}
//		// reading flags
//		this.flags = packet.read_octet();
//		// setting endianess
//		packet.setEndianess(getFlagAt(0));
//		// reading length
//		this.octetsToNextHeader = packet.read_octet();
//		// checking submessage length
//		if (packet.getCursorPosition()+octetsToNextHeader >= packet.getLength()) {
//			throw new MalformedSubmessageException("Message TOO small");
//		}
//		// reading the body...
//	}

	
	/**
	 * Writes the body of the Submessage.
	 * It works good if the array of subelements is
	 * given in the right writing order
	 * @param os
	 */
	protected abstract void writeBody(CDROutputPacket os);
	
	
	public boolean getFlagAt(int pos) {
		return (BitUtility.getBitAt(flags,pos) != 0);
	}
	
	public boolean getEndianess() {
		return getFlagAt(0);
	}
	
	protected void setFlagAt(int pos, boolean value) {
		flags = BitUtility.setBitAt(flags,pos,value);
	}
	
	public void setEndianess(boolean isLittleEndian) {
		setFlagAt(0,isLittleEndian);
	}

	/**
	 * @return Returns the srcGuidPrefix.
	 */
	public GuidPrefix getSrcGuidPrefix() {
		return srcGuidPrefix;
	}

	/**
	 * @param srcGuidPrefix The srcGuidPrefix to set.
	 */
	public void setSrcGuidPrefix(GuidPrefix srcGuidPrefix) {
		this.srcGuidPrefix = srcGuidPrefix;
	}

	/**
	 * @return Returns the timestamp.
	 */
	public Timestamp getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp The timestamp to set.
	 */
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

}
