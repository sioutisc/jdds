//================================================================
//
//	SequenceNumberSet.java - Created by kerush, 2006 20-ott-06 1:20:10 
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
import rtjdds.rtps.types.SequenceNumber_t;
import rtjdds.rtps.types.SequenceNumber_tHelper;

/**
 * This class encodes a set of <code>SequenceNumber</code>s in a compact way.
 * Please refer to the OMG RTPS-Wire Protocol standard for an extensive
 * explanation of the encoding tecnique.<br/>
 * Basically a <code>SequenceNumber</code> is mantained as the 
 * @author kerush
 *
 */
public class SequenceNumberSet extends SubmessageElement {
	
	protected SequenceNumber_t base;
	protected int numBits = 0;
	protected int[] set;
	
	/**
	 * TODO: constructor weak: it can't set the size of the CDR encoded element...
	 * @param base
	 * @param set
	 */
	public SequenceNumberSet(SequenceNumber_t base, int numBits, int[] set) {
		super(0);
		this.numBits = numBits;
		this.base = base;
		this.set = set;
		super.size = SubmessageElement.SEQUENCE_NUMBER_SIZE + 4 + set.length*4;
	}
	
	public static SequenceNumberSet read(InputPacket packet) throws MalformedSubmessageElementException {
		// sequence number base
		SequenceNumber_t base = SequenceNumber_tHelper.read(packet);
		// numBits
		int numBits = packet.read_long();
		int M = (numBits+31)/32;
		// reading bitmaps
		int set[] = new int[M];
		for (int i=0; i<M; i++) {
			set[i] = packet.read_long();
		}
		return new SequenceNumberSet(base,numBits,set);
	}
	
//	 TODO: method not fully implemented
	public void write(OutputStream os) {
		// bitmapBase
		SequenceNumber_tHelper.write(os, base);
		// numBits
		os.write_long(numBits);
		// bitmap
		for (int i=0; i<set.length; i++) {
			os.write_long(set[i]);
		}
	}

}
