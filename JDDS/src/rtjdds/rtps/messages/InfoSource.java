//================================================================
//
//	InfoSource.java - Created by kerush, 2006 24-ott-06 2:49:57 
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
import rtjdds.rtps.messages.elements.ProtocolVersion;
import rtjdds.rtps.messages.elements.VendorId;
import rtjdds.rtps.portable.CDROutputPacket;
import rtjdds.rtps.types.INFO_SRC;

public class InfoSource extends Submessage {
	
	protected ProtocolVersion version;
	protected VendorId vendor;
	protected GuidPrefix guidPrefix;
	
	public InfoSource(ProtocolVersion version, VendorId vendor,GuidPrefix guidPrefix) {
		super(INFO_SRC.value);
		this.version = version;
		this.vendor = vendor;
		this.guidPrefix = guidPrefix;
	}

	protected void writeBody(CDROutputPacket os) {
		os.write_long(0); // 4 unused bytes
		this.version.write(os);
		this.vendor.write(os);
		this.guidPrefix.write(os);
	}

}
