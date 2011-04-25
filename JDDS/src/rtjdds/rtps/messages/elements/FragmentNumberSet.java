//================================================================
//
//	FragmentNumberSet.java - Created by kerush, 2006 20-ott-06 2:37:06 
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
import rtjdds.rtps.types.FragmentNumber_t;
import rtjdds.rtps.types.FragmentNumber_tHelper;

/**
 * @author kerush
 *
 */
public class FragmentNumberSet extends SubmessageElement {

	protected FragmentNumber_t base;
	protected int nBits;
	protected int[] set;
	
	/**
	 * TODO: constructor weak: it can't set the size of the CDR encoded element...
	 * @param base
	 * @param set
	 */
	public FragmentNumberSet(FragmentNumber_t base, int nBits, int[] set) {
		super(0);
		this.base = base;
		this.nBits = nBits;
		this.set = set;
		super.size = SubmessageElement.FRAGMENT_NUMBER_SIZE + 4 + set.length*4;
	}
	
	public static FragmentNumberSet read (InputPacket p) throws MalformedSubmessageElementException {
		FragmentNumber_t base = FragmentNumber_tHelper.read(p);
		int numBits = p.read_long();
		int M = (numBits+31)/32;
		int[] set = new int[M];
		for (int i=0; i<M; i++) {
			set[i] = p.read_long();
		}
		return new FragmentNumberSet(base,numBits,set);
	}
	
	public void write(OutputStream os) {
		// bitmapBase
		FragmentNumber_tHelper.write(os, this.base);
		// numBits
		os.write_long(this.nBits);
		// bitmap
		int M = (this.nBits+31)/32;
		for (int i=0; i<M; i++) {
			os.write_long(this.set[i]);
		}
	}
	
}
