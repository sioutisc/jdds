//================================================================
//
//	KeyHashSuffix.java - Created by kerush, 2006 20-ott-06 1:12:09 
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

import rtjdds.rtps.types.EntityId_t;
import rtjdds.rtps.types.KeyHashSuffix_t;
import rtjdds.rtps.types.KeyHashSuffix_tHelper;

/**
 * @author kerush
 *
 */
public class KeyHashSuffix extends EntityId {
	
	protected KeyHashSuffix_t value;
	
	public KeyHashSuffix(KeyHashSuffix_t value) {
		super(new EntityId_t(new byte[]{value.value[0],value.value[1],value.value[2]},value.value[3]));
		this.value = value;
	}

	public void write (OutputStream os) {
		KeyHashSuffix_tHelper.write(os, value);
	}
}
