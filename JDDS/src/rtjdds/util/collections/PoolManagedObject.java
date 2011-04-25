//================================================================
//
//	RepositoryEntity.java - Created by kerush, 2006 23-nov-06 5:55:03 
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
package rtjdds.util.collections;

import rtjdds.rtps.exceptions.CollectionsException;


public abstract class PoolManagedObject implements Copyable{
	
	/* reference to the pool */
	protected ObjectPool _pool = null;
	/* maintains the object position in the pool (-1 means uninitialized) */
	protected int _repo_index = -1;
	
//	public abstract void copyFrom(PoolManagedObject obj);
//	
//	public abstract void copyTo(PoolManagedObject obj);
	
	
	/**
	 * Returns this Object to the pool.
	 * 
	 * @throws CollectionsException
	 */
	public void free() throws CollectionsException {
		_pool.returnObj(this);
	}

}
