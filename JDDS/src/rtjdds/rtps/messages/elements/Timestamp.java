//================================================================
//
//	Timestamp.java - Created by kerush, 2006 20-ott-06 1:23:19 
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

import rtjdds.rtps.types.Time_t;
import rtjdds.rtps.types.Time_tHelper;
import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;
import rtjdds.util.collections.Copyable;

/**
 * @author kerush
 *
 */
public class Timestamp extends SubmessageElement implements Comparable, Copyable {

	protected Time_t _value;
	
	public Timestamp(Time_t value) {
		super(SubmessageElement.TIMESTAMP_SIZE);
		this._value = value;
	}
	
	public Timestamp() {
		this(new Time_t(0,0));
	}
	
	public String toString() {
		return _value.fraction+","+_value.seconds;
	}

	public void write(OutputStream os) {
		Time_tHelper.write(os, _value);
	}

	////////////////////////////////////////////////
	// methods from Comparable
	////////////////////////////////////////////////
	
	public int compareTo(Object obj) {
		if (obj instanceof Timestamp && obj != null) {
			Timestamp ts = (Timestamp)obj;
			if (_value.seconds > ts._value.seconds) {
				return 1;
			}
			else if (_value.seconds < ts._value.seconds) {
				return -1;
			}
			else {
				if (_value.fraction > ts._value.fraction) {
					return 1;
				}
				else if (_value.fraction < ts._value.fraction) {
					return -1;
				}
				else {
					return 0;
				}
			}
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
		if (obj instanceof Timestamp) {
			Timestamp ts = (Timestamp)obj;
			_value.fraction = ts._value.fraction;
			_value.seconds = ts._value.seconds;
		}
		else {
			GlobalProperties.logger.log(Logger.WARN,this.getClass(),"copyFrom()",
					"Cannot copy from non Timestamp object");
		}
	}

	public void copyTo(Copyable obj) {
		if (obj instanceof Timestamp) {
			Timestamp ts = (Timestamp)obj;
			ts._value.fraction = _value.fraction;
			ts._value.seconds = _value.seconds;
		}
		else {
			GlobalProperties.logger.log(Logger.WARN,this.getClass(),"copyTo()",
					"Cannot copy to non Timestamp object");
		}
	}
	
}
