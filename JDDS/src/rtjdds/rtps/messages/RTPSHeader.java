//================================================================
//
//	Header.java - Created by kerush, 2006 7-ott-06 7:55:29 
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

import org.omg.CORBA.portable.OutputStream;

import rtjdds.rtps.messages.elements.GuidPrefix;
import rtjdds.rtps.messages.elements.ProtocolId;
import rtjdds.rtps.messages.elements.ProtocolVersion;
import rtjdds.rtps.messages.elements.SubmessageElement;
import rtjdds.rtps.messages.elements.VendorId;
import rtjdds.util.GlobalProperties;

public class RTPSHeader {

	protected ProtocolId protocol = null;
	protected ProtocolVersion version = null;
	protected VendorId vendorId = null;
	protected GuidPrefix guidPrefix = null;
	
	public RTPSHeader() {}
	
	public RTPSHeader (ProtocolId proto, ProtocolVersion ver, VendorId vendor, GuidPrefix prefix) {
		protocol = proto;
		version = ver;
		vendorId = vendor;
		guidPrefix = prefix;
	}
	
	public RTPSHeader (GuidPrefix prefix) {
		protocol = GlobalProperties.protocolId;
		version = GlobalProperties.protocolVersion;
		vendorId = GlobalProperties.vendorId;
		guidPrefix = prefix;
	}
	
	/**
	 * @return Returns the guidPrefix.
	 */
	public GuidPrefix getGuidPrefix() {
		return guidPrefix;
	}

	/**
	 * @return Returns the protocol.
	 */
	public ProtocolId getProtocol() {
		return protocol;
	}

	/**
	 * @return Returns the vendorId.
	 */
	public VendorId getVendorId() {
		return vendorId;
	}

	/**
	 * @return Returns the version.
	 */
	public ProtocolVersion getVersion() {
		return version;
	}

	public int getSize() {
		return SubmessageElement.RTPS_HEADER_SIZE;
	}
	
	public void write(OutputStream os) {
		protocol.write(os);
		version.write(os);
		vendorId.write(os);
		guidPrefix.write(os);
	}
	
//	public static RTPSHeader read(CDRInputPacket packet) throws MalformedSubmessageException {
//		RTPSHeader newOne = new RTPSHeader();
//		if (packet.getLength() >= SubmessageElement.RTPS_HEADER_SIZE) {
//			// reading protoId
//			newOne.protocol = ProtocolId_tHelper.read(packet);
//			if (Arrays.equals(newOne.protocol.value, GlobalProperties.protocolId.value)) {
//				// reading version, vendor and GUIDprefix
//				newOne.version = ProtocolVersion_tHelper.read(packet);
//				newOne.vendorId = VendorId_tHelper.read(packet);
//				newOne.guidPrefix = GuidPrefix_tHelper.read(packet);	
//			}
//			else {
//				throw new MalformedSubmessageException("RTPS Protocol string not correct");
//			}
//		}
//		else {
//			throw new MalformedSubmessageException("RTPS Header non found in the message");
//		}
//		
//		GlobalProperties.logger.log(Logger.INFO,RTPSHeader.class,
//				"read()","" +
//				"Received RTPS message: " + 
//				"GUID_PREFIX="+String.valueOf(newOne.guidPrefix.prefix)+" " +
//				"VENDOR_ID="+String.valueOf(newOne.vendorId.vendorId)+" " +
//				"VERSION="+newOne.version.major+"."+newOne.version.minor);
//		
//		return newOne;
//	}



}