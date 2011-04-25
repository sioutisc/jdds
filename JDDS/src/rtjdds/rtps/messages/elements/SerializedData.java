//================================================================
//
//	SerializedData.java - Created by kerush, 2006 20-ott-06 1:21:52 
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
import rtjdds.rtps.portable.InputPacket;
import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;
import rtjdds.util.collections.Copyable;

/**
 * @author kerush
 *
 */
public class SerializedData extends SubmessageElement implements Copyable {

	protected byte[] _value;
	
	public SerializedData(byte[] value) {
		super(value.length);
		this._value = value;
	}
	
	public SerializedData(InputPacket packet, int length) throws MalformedSubmessageElementException{
		super(length);
		if (packet.getCursorPosition() + length <= packet.getLength()) {
			_value = new byte[length];
			packet.read_octet_array(_value,0,length);
		}
		else {
			throw new MalformedSubmessageElementException("SerializedData length is incorrect");
		}
	}
	
	public byte[] getBytes() {
		return _value;
	}
	
	public String toString() {
		return new String(_value);
	}
	
	public void write(OutputStream os) {
		/* data */
		os.write_octet_array(_value,0,_value.length);
		/* alignment */
		if (_value.length % 4 != 0) {
			byte[] zeros = new byte[ 4 - (_value.length % 4) ];
			os.write_octet_array(zeros,0,zeros.length);
		}
	}
	

	////////////////////////////////////////////////
	// methods from Copyable
	////////////////////////////////////////////////
	
	public void copyFrom(Copyable obj) {
		if (obj instanceof SerializedData && obj != null) {
			SerializedData src = (SerializedData) obj;
			int min = Math.min(_value.length,src._value.length);
			System.arraycopy(src._value,0,_value,0,min);
			while (min < _value.length) {
				_value[min] = 0;
				min++;
			}
		}
		else {
			GlobalProperties.logger.log(Logger.WARN,this.getClass(),"copyFrom()",
					"Cannot copy from non SerializedData object");
		}
	}

	public void copyTo(Copyable obj) {
		if (obj instanceof SerializedData && obj != null) {
			SerializedData dst = (SerializedData) obj;
			int min = Math.min(_value.length,dst._value.length);
			System.arraycopy(_value,0,dst._value,0,min);
			while (min < dst._value.length) {
				dst._value[min] = 0;
				min++;
			}
		}
		else {
			GlobalProperties.logger.log(Logger.WARN,this.getClass(),"copyTo()",
					"Cannot copy to non SerializedData object");
		}
	}
	
}
