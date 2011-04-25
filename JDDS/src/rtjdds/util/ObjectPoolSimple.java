//================================================================
//
//	ObjectPool.java - Created by kerush, 2006 24-nov-06 10:31:43 
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
package rtjdds.util;

import javax.realtime.MemoryArea;

import rtjdds.rtps.exceptions.OutOfResourceException;

public class ObjectPoolSimple {
	
	private Object[] freeObjs = null;
	private int peek = 0;
	
	// --------------------------------------
	// initialization-phase methods
	// --------------------------------------
	
	public ObjectPoolSimple(Object[] newObjs) {
		freeObjs = newObjs;
		peek = newObjs.length-1;
	}
	
	public ObjectPoolSimple(Class type, int size, MemoryArea mem) throws Exception {
		freeObjs = new Object[size];
		while (peek < size) {
			freeObjs[peek] = mem.newInstance(type);
		}
	}
	
	// --------------------------------------
	// mission-phase methods
	// --------------------------------------
	
	public Object getObj() throws OutOfResourceException {
		if (peek >= 0) {
			return freeObjs[peek--];
			/* peek == -1 means no more free objects */
		}
		else {
			throw new OutOfResourceException("Object Pool is empty");
		}
	}
	
	public void putObj(Object obj) {
		if (peek < freeObjs.length-1) {
			freeObjs[++peek] = obj;
			/* peek == freeObjs.length-1 means no more space */
		}
	}

}
