/* ********************************************************************* *
 *                                                                       *
 *   =============================================================       *
 *   Copyright 2011                                                      *
 *   Christos Sioutis <christos.sioutis@gmail.com>                       *
 *   =============================================================       *
 *                                                                       *
 *   This file is part of jdds.                                          *
 *                                                                       *
 *   jdds is free software: you can redistribute it and/or               *
 *   modify it under the terms of the GNU General Public License         *
 *   as published by the Free Software Foundation, either version 3 of   *
 *   the License, or (at your option) any later version.                 *
 *                                                                       *
 *   jdds is distributed in the hope that it will be useful,             *
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of      *
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the       *
 *   GNU General Public License for more details.                        *
 *                                                                       *
 *   You should have received a copy of the GNU General Public           *
 *   License along with jdds.                                            *
 *   If not, see <http://www.gnu.org/licenses/>.                         *
 *                                                                       *
 * ********************************************************************* */

package jdds.rtps.messages.submessage;

import RTPS.SubmessageHeaderHolder;

/*
 * 

In case octetsToNextHeader > 0, it is the number of octets from the first octet of the contents of the Submessage until the
first octet of the header of the next Submessage (in case the Submessage is not the last Submessage in the Message) OR
it is the number of octets remaining in the Message (in case the Submessage is the last Submessage in the Message). An
interpreter of the Message can distinguish these two cases as it knows the total length of the Message.

In case octetsToNextHeader==0 and the kind of Submessage is NOT PAD or INFO_TS, the Submessage is the last
Submessage in the Message and extends up to the end of the Message. This makes it possible to send Submessages
larger than 64k (the size that can be stored in the octetsToNextHeader field), provided they are the last Submessage in the
Message.

In case the octetsToNextHeader==0 and the kind of Submessage is PAD or INFO_TS, the next Submessage header starts
immediately after the current Submessage header OR the PAD or INFO_TS is the last Submessage in the Message.

 * 
 */


public class SubmessageHeader {
	
	public enum EndiannessFlag {
		BigEndian(0),
		LittleEndian(1);
		
		int value;
		
		EndiannessFlag(int val){
			value = val;
		}
		
		public int value(){
			return value;
		}

	}	
	
	public static final int MAXIMUM_SUBMESSAGE_LENGTH = 64000;
	
	SubmessageHeaderHolder value;
		
	public SubmessageHeader(SubmessageKind id,  SubmessageFlag[] flags, int length ){
		value  = new SubmessageHeaderHolder(new RTPS.SubmessageHeader(id.value(), SubmessageFlag.byteValue(flags), (short)length));
	}
	
	public EndiannessFlag endianness(){
		if ((value.value.submessageId & 1) == 1)
			return EndiannessFlag.LittleEndian;
		return EndiannessFlag.BigEndian;
	}
		
}
