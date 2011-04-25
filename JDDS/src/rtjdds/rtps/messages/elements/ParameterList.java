//================================================================
//
//	ParameterList.java - Created by kerush, 2006 20-ott-06 2:41:35 
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

import java.util.ArrayList;

import org.omg.CORBA.portable.OutputStream;

import rtjdds.rtps.messages.MalformedSubmessageElementException;
import rtjdds.rtps.portable.InputPacket;

/**
 * @author kerush
 *
 */
public class ParameterList extends SubmessageElement {
	
	public final static short PID_SENTINEL = 1;
	public final static short PID_PAD = 0;

	protected Parameter[] value;
	
	public ParameterList(Parameter[] values) {
		super(0);
		this.value = values;
		// set the size of the encoded element
		for (int i=0; i<values.length; i++) {
			super.size += (4 + values.length);
		}
		// PID_SENTINEL (2 bytes) + 2 ignored bytes
		super.size += 4;
	}
	
	// TODO checks
	public ParameterList(InputPacket packet) throws MalformedSubmessageElementException {
		super(0);
		// array lists in which store parameters
		ArrayList parameters = new ArrayList();
		
		boolean stop = false;
		do {
			// at least 4 bytes for length and type
			if (packet.getCursorPosition() + 4 <= packet.getLength()) {
				short parameterId = packet.read_short();
				short length = packet.read_short();
				byte[] p = new byte[length];
				if (parameterId != PID_SENTINEL) {
					for (int i=0; i<length; i++) {
						p[i] = packet.read_octet();
					}
					parameters.add(new Parameter(parameterId,length,p));
				}
				else {
					stop = true;
				}
			}
			else {
				throw  new MalformedSubmessageElementException("ParameterList too short");
			}
		} while (!stop);
		
		value = new Parameter[parameters.size()];
		parameters.toArray(value);
	}
	
	public void write (OutputStream os) {
		for (int i=0; i<value.length; i++) {
			value[i].write(os);
			// PID_PAD for 4 bytes alignment
			for (int j=value[i].value.length; j<4; j++) {
				os.write_short((short)0);
			}
		}
		// PID_SENTINEL (short value = 1)
		os.write_short((short)1);
		// last 16 bits after PID_SENTINEL are ignored
		os.write_short((short)0);
	}
	
	
}
