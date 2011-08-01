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

package jdds.rtps.messages.submessage.attribute;

import RTPS.SequenceNumber_t;
import jdds.rtps.messages.submessage.SubmessageElement;

/**
 * A sequence number is a 64-bit signed integer, that can take values in the range: -2^63 <= N <= 2^63-1. The selection of
64 bits as the representation of a sequence number ensures the sequence numbers never1 wrap. Sequence numbers begin
at 1.

 * @author chris
 *
 */

public class SequenceNumber extends SubmessageElement implements Diff<SequenceNumber>{
	public SequenceNumber_t value;
	static long HIGH = (long) Math.pow(2,32);
	
	
	public SequenceNumber(SequenceNumber_t val){
		value = val;
	}
	
	public SequenceNumber(long longValue){
		value = new SequenceNumber_t();
		value.high = (int) (longValue / HIGH);
		value.low = (int) (longValue % HIGH);		
	}
	
	int octets(){
		return 8;
	}

	@Override
	public int compareTo(SequenceNumber o) {
		return (int) diff(o);
	}

	@Override
	public long diff(SequenceNumber e) {
		return longValue()-e.longValue();
	}
	
	@Override
	public long longValue() {
		return ((long) value.high) * HIGH + value.low;
	}
}
