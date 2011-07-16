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
 * From OMG RTPS Standard v2.1 p30: Type used to specify a Submessage flag. 
 * A Submessage flag takes a boolean value and affects the parsing of the
 * Submessage by the receiver.
 * 
 * @author Christos Sioutis <christos.sioutis@gmail.com>
 *
 */

public class SubmessageFlag {
	private boolean flag_;
	
	public SubmessageFlag(boolean flag){
		flag_ = flag;
	}
	
	public SubmessageFlag(int intValue){
		if(intValue == 1)
			flag_ = true;
		flag_ = false;
	}
	
	public SubmessageFlag(char val){
		if(val == '1')
			flag_ = true;
		flag_ = false;
	}
	
	public boolean value(){
		return flag_;
	}
	
	public int intValue(){
		if(flag_)
			return 1;
		return 0;
	}
	
	public static final int INDEX_ENDIANNESS_FLAG = 0;
	
	public static byte byteValue(SubmessageFlag[] flags){
		String str = "";
		for(int i=0; i < Math.min(8, flags.length); i++){
			str = "" + flags[i].intValue() + str;
		}
		return Byte.parseByte(str,2);
	}
	
	public static SubmessageFlag[] flags(byte value){
		StringBuffer str = new StringBuffer(Integer.toBinaryString(value)).reverse();
		SubmessageFlag[] flags = new SubmessageFlag[str.length()];
		for(int i=0; i<str.length(); i++){
			flags[i] = new SubmessageFlag(str.charAt(i));
		}
		return flags;
	}

}
