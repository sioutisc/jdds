//================================================================
//
//	StatusInfo.java - Created by kerush, 2006 20-ott-06 1:13:01 
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

import rtjdds.rtps.portable.CDROutputPacket;

/**
 * @author kerush
 *
 */
public class StatusInfo extends SubmessageElement { 
	
	protected int value;
	
	public StatusInfo(int value) {
		super(CDROutputPacket.LONG);
		this.value = value;
	}
	
	public StatusInfo(boolean isDisposed, boolean isUnregistered){
		super(CDROutputPacket.LONG);
		this.value = 0;
		if (isDisposed) this.value |= 0x01;
		if (isUnregistered) this.value |= 0x02;
	}
	
	public void write(OutputStream os) {
		os.write_long(value);
	}
	
	/**
	 * If return value is <code>true</true>, means that the Data-object no longer exists.<br/>
	 * Otherwise (return value is <code>false</code>) the Data-object continues existing.
	 * @return
	 */
	public boolean isDisposed() {
		return ((value & 0x01) == 1);
	}
	
	/**
	 * If return value is <code>true</code>, means that the writer will not provide further informations
	 * on the Data-object, in other words it has unregistered the Data-object.
	 * If return value is <code>false</code>, means that the writer plans to provide further informations
	 * on the Data-object.<br/>
	 * @return
	 */
	public boolean isUnregistered() {
		return ((value & 0x02) == 2);
	}

}
