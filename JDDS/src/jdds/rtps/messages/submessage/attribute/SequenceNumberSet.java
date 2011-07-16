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

package rtps.messages.submessage.attribute;

import RTPS.SequenceNumber_t;

public class SequenceNumberSet {
	RTPS.SequenceNumberSet value;
	
	public SequenceNumberSet(SequenceNumber_t bitmapBase, int numbits, int[] bitmap){
		value = new RTPS.SequenceNumberSet(bitmapBase,numbits,bitmap);
	}
	
	/** parses a SequenceBumberSet string of the format 1234/12:00110 */
	public SequenceNumberSet(String strSeqNumSet){
		String[] tmp = strSeqNumSet.split("/");

		SequenceNumber bitmapBase = new SequenceNumber(Integer.parseInt(tmp[0]));
		
		tmp = tmp[1].split(":");

		int numbits = Integer.parseInt(tmp[0]);
		
		if(tmp[1].length() <= 32 ){
			int[] bitmap = new int[1];
			bitmap[0] = Integer.parseInt(tmp[1], 2);
			value = new RTPS.SequenceNumberSet(bitmapBase.value,numbits,bitmap);
		} else if(tmp[1].length() % 32 == 0) {
			int[] bitmap = new int[tmp[1].length() / 32];
			for(int i=0; i<bitmap.length; i++){
				bitmap[i] = Integer.parseInt(tmp[1].substring(i*32,(i+1)*32), 2);				
			}
			value = new RTPS.SequenceNumberSet(bitmapBase.value,numbits,bitmap);
		} else {
			int[] bitmap = new int[(tmp[1].length() / 32) + 1];
			for(int i=0; i<bitmap.length-1; i++){
				bitmap[i] = Integer.parseInt(tmp[1].substring(i*32,(i+1)*32), 2);				
			}
			bitmap[bitmap.length-1] = Integer.parseInt(tmp[1].substring((bitmap.length-1)*32), 2);							
			value = new RTPS.SequenceNumberSet(bitmapBase.value,numbits,bitmap);
		}		
	}
	
	public String toString(){
		String toReturn = "";
		toReturn += new SequenceNumber(value.bitmapBase).toString();
		toReturn += "/";
		toReturn += value.numbits;
		for(int i=0; i< value.bitmap.length; i++){
			toReturn += Integer.toString(value.bitmap[i],2);
		}
		return toReturn;
	}
	
	//public int octets(){
		
	//}
}
