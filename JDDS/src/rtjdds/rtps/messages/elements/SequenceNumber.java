//================================================================
//
//	SequenceNumber.java - Created by kerush, 2006 20-ott-06 1:17:08 
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

import rtjdds.rtps.types.SequenceNumber_t;
import rtjdds.rtps.types.SequenceNumber_tHelper;
import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;
import rtjdds.util.collections.Copyable;

/**
 * @author kerush
 *
 */
public class SequenceNumber extends SubmessageElement implements Comparable, Copyable {
	
	protected SequenceNumber_t _value;
	
	public SequenceNumber(SequenceNumber_t value) {
		super(SubmessageElement.SEQUENCE_NUMBER_SIZE);
		this._value = value;
	}
	
	public SequenceNumber() {
		this(new SequenceNumber_t());
	}

	public void write(OutputStream os) {
		SequenceNumber_tHelper.write(os, _value);
	}
	
	public long getLongValue() {
		return _value.value;
	}
	
	public void setValue(long value) {
		this._value.value = value;
	}
	
	public boolean equals(Object obj) {
		SequenceNumber sn = (SequenceNumber) obj;
		return (this._value == sn._value);
	}
	
	public void copyFrom(SequenceNumber from) {
		_value.value  = from._value.value;
	}
	
	public String toString() {
		return ""+_value.value;
	}
	
	////////////////////////////////////////////////
	// methods from Comparable
	////////////////////////////////////////////////

	public int compareTo(Object o) {
		if (o instanceof SequenceNumber) {
			SequenceNumber sn = (SequenceNumber) o;
			if (this._value.value > sn._value.value) {
				return 1;
			}
			else if (this._value.value < sn._value.value) {
				return -1;
			}
			// they are the same
			return 0;
		}
		else {
			GlobalProperties.logger.log(Logger.WARN,this.getClass(),"compareTo()",
			"Cannot compare to a non Timestamp object");
			return 0;
		}
	}
	

	////////////////////////////////////////////////
	// methods from Copyable
	////////////////////////////////////////////////
	
	public void copyFrom(Copyable obj) {
		if (obj instanceof SequenceNumber && obj != null) {
			SequenceNumber sn = (SequenceNumber)obj;
//			_value = sn._value;
			_value.copyFrom(sn._value);
		}
		else {
			GlobalProperties.logger.log(Logger.WARN,this.getClass(),"copyFrom()",
					"Cannot copy from non SequenceNumber object");
		}
	}

	public void copyTo(Copyable obj) {
		if (obj instanceof SequenceNumber && obj != null) {
			SequenceNumber sn = (SequenceNumber)obj;
//			sn._value = _value;
			_value.copyTo(sn._value);
		}
		else {
			GlobalProperties.logger.log(Logger.WARN,this.getClass(),"copyTo()",
					"Cannot copy to non SequenceNumber object");
		}
	}
	

}
