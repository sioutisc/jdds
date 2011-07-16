
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

package rtps.messages.submessage;

/**
 * From OMG RTPS Standard v2.1 p30: Enumeration used to identify the kind of
 * Submessage. The following values are reserved by this version of the protocol:
 * DATA, GAP, HEARTBEAT, ACKNACK, PAD, INFO_TS, INFO_REPLY, INFO_DST, INFO_SRC, 
 * DATA_FRAG, NACK_FRAG, HEARTBEAT_FRAG
 * 
 * @author Christos Sioutis <christos.sioutis@gmail.com>
 *
 */



public enum SubmessageKind {	
	PAD("1"),
	ACKNACK("6"),
	HEARTBEAT("7"),
	GAP("8"),
	INFO_TS("9"),
	INFO_SRC("c"),
	INFO_REPLY_IP4("d"),
	INFO_DST("e"),
	INFO_REPLY("f"),
	NACK_FRAG("12"),
	HEARTBEAT_FRAG("13"),
	DATA("15"),
	DATA_FRAG("16");
	
	byte value;
	
	SubmessageKind(String val){
		value = Byte.parseByte(val,16);
	}
	
	public byte value(){
		return value;
	}
	
	public int octets(){
		return 1;
	}
}
