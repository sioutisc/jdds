//================================================================
//
//	Parameter.java - Created by kerush, 2006 20-ott-06 4:21:53 
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

import rtjdds.rtps.messages.MalformedSubmessageElementException;

/**
 * @author kerush
 *
 */
public class Parameter extends SubmessageElement {

	protected  short parameterId;
	protected short length;
	protected byte[] value;
	
	public Parameter(short parameterId, short length, byte[] value) throws MalformedSubmessageElementException {
		super(4 + value.length);
		if (length != value.length) {
			throw new MalformedSubmessageElementException("");
		}
		this.parameterId = parameterId;
		this.length = length;
		this.value = value;
	}
	
	/*
	public Parameter(CDRInputPacket packet) throws MalformedSubmessageElementException {
		super(0);
		this.parameterId = packet.read_short();
		this.length = packet.read_short();
		this.value = new byte[length];
		packet.read_octet_array(this.value, 0, this.length);
	}
	*/
	
	public void write (OutputStream os) {
		os.write_short(parameterId);
		os.write_short(length);
		os.write_octet_array(value,0,value.length);
	}
	
	
}
