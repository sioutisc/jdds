//================================================================
//
//	GuidPrefix.java - Created by kerush, 2006 20-ott-06 1:03:53 
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

import rtjdds.rtps.types.GuidPrefix_t;
import rtjdds.rtps.types.GuidPrefix_tHelper;
import rtjdds.util.BitUtility;
import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;
import rtjdds.util.collections.Copyable;

/**
 * A <code>GuidPrefix</code> uniquely identifies a Participant in a Domain.<br/>
 * It consists of 12 bytes. The structure here adopted is the following:
 * <pre>
 * 0xFFFFFFFF0000000000000000 IP Address of the Participant
 * 0x00000000FFFFFFFF00000000 time_milliseconds
 * 0x000000000000F000FFFFFFFF time_nanoseconds
 * </pre>
 * It would be better to use the MAC address instead of the IP address, but
 * currently java doesn't support this feature. The next java major release
 * will allow programmers to get the MAC address, but we still need to wait
 * the next Real Time major release!  
 * 
 * @author kerush
 *
 */
public class GuidPrefix extends SubmessageElement implements Copyable {
	
	protected GuidPrefix_t _value;
	
	public GuidPrefix(GuidPrefix_t value) {
		super(SubmessageElement.GUID_PREFIX_SIZE);
		this._value = value;
	}
	
	public GuidPrefix() {
		this(new GuidPrefix_t(new byte[12]));
	}

	public int getIP() {
		return BitUtility.bytesToInt
		(new byte[]{_value.prefix[11],_value.prefix[10],_value.prefix[9],_value.prefix[8]});
	}
	
	public int getTSms() {
		return BitUtility.bytesToInt
		(new byte[]{_value.prefix[7],_value.prefix[6],_value.prefix[5],_value.prefix[4]});
	}
	
	public int getTSns() {
		return BitUtility.bytesToInt
		(new byte[]{_value.prefix[3],_value.prefix[2],_value.prefix[1],_value.prefix[0]});
	}
	
	public void write (OutputStream os) {
		GuidPrefix_tHelper.write(os, _value);
	}
	
	public boolean equals(Object o) {
		GuidPrefix p = (GuidPrefix) o;
		return java.util.Arrays.equals(this._value.prefix,p._value.prefix);
	}
	
	public String toHexString() {
		return ""+BitUtility.getHexString(_value.prefix);
	}
	
	public String toByteString() {
		return BitUtility.getNiceString(_value.prefix);
	}
	
	public String toString() {
		return toHexString();
	}
	
	////////////////////////////////////////////////
	// methods from Copyable
	////////////////////////////////////////////////
	
	public void copyFrom(Copyable obj) {
		if (obj instanceof GuidPrefix && obj != null) {
			GuidPrefix src = (GuidPrefix) obj;
			System.arraycopy(src._value.prefix,0,_value.prefix,0,12);
		}
		else {
			GlobalProperties.logger.log(Logger.WARN,this.getClass(),"copyFrom()",
					"Cannot copy from non GuidPrefix object");
		}
	}

	public void copyTo(Copyable obj) {
		if (obj instanceof GuidPrefix && obj != null) {
			GuidPrefix dst = (GuidPrefix) obj;
			System.arraycopy(_value.prefix,0,dst._value.prefix,0,12);
		}
		else {
			GlobalProperties.logger.log(Logger.WARN,this.getClass(),"copyTo()",
					"Cannot copy to non GuidPrefix object");
		}
	}
}
