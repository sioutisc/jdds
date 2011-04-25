//================================================================
//
//	HashBucketNode.java - Created by kerush, 2006 29-nov-06 6:24:58 
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

public class HashBucketNode extends ListNode implements Copyable {
	
	protected int _collisionId;
	
	public HashBucketNode() {}
	
	public HashBucketNode(PoolManagedObject obj) {
		super(obj);
	}
	
	public HashBucketNode(PoolManagedObject obj, HashBucketNode prev, HashBucketNode next) {
		super(obj,prev,next);
	}
	
	public void copyFrom(Copyable obj) {
		super.copyFrom(obj);
		_collisionId = ((HashBucketNode)obj)._collisionId;
	}
	
	public void copyTo(Copyable obj) {
		super.copyTo(obj);
		((HashBucketNode)obj)._collisionId = _collisionId;
	};
	
}
