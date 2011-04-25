//================================================================
//
//	SubmessageElement.java - Created by kerush, 2006 19-ott-06 6:19:25 
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
package rtjdds.rtps.messages.elements;

import org.omg.CORBA.portable.OutputStream;

public abstract class SubmessageElement {
	
	public static int ENTITY_ID_SIZE = 4;
	public static int GUID_PREFIX_SIZE = 12;
	public static int VENDOR_ID_SIZE = 2;
	public static int PROTOCOL_VERSION_SIZE = 2;
	public static int SEQUENCE_NUMBER_SIZE = 8;
//	public static int SEQUENCE_NUMBER_SET_SIZE = 12;
	public static int FRAGMENT_NUMBER_SIZE = 4;
//	public static int FRAGMENT_NUMBER_SET_SIZE = 8;
	public static int TIMESTAMP_SIZE = 8;
	public static int LOCATOR_SIZE = 24;
//	public static int PARAMETER_LIST_SIZE = 4;
	public static int COUNT_SIZE = 4;
	public static int KEY_HASH_PREFIX_SIZE = 12;
	public static int KEY_HASH_SUFFIX_SIZE = 4;
	public static int LOCATOR_UDP_V4_SIZE = 8;
	public static int RTPS_HEADER_SIZE = 20;
	public static int PROTOID_SIZE = 4;
	
	protected int size;
	
	public SubmessageElement(int size) {
		this.size = size;
	}
	
	public abstract void write(OutputStream os);
	
	/**
	 * Returns the size in bytes of this submessage element
	 * when it is encoded in CDR.
	 * @return
	 */
	public int getSize() {
		return size;
	}

}
