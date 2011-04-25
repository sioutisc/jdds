//================================================================
//
//	HashNode.java - Created by kerush, 2006 24-nov-06 6:06:25 
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

/**
 * This class represents a node of a doubly-linked list,
 * with a generic reference to an <code>Object</code> as
 * contained data.
 * 
 * @author kerush
 *
 */
public class ListNode extends PoolManagedObject implements Copyable {
	
    protected Object _data = null;
    protected ListNode _next = null;
    protected ListNode _prev = null;
    
    public ListNode() {}
    
    /**
     * Creates an unlinked node.
     * 
     * @param obj
     */
    public ListNode(PoolManagedObject obj){
    	_data = obj;
    }
    
    /**
     * Creates a node linked between the given nodes.
     * 
     * @param obj
     * @param prev
     * @param next
     */
    public ListNode(PoolManagedObject obj, ListNode prev, ListNode next){
    	this(obj);
    	attachBetween(prev,next);
    }
    
    /**
     * Inserts the node between the two given nodes.<br/>
     * Works also with null values, so can be used to insert
     * at head and at tail with no problems.
     * 
     * @param prev
     * @param next
     */
    public void attachBetween(ListNode prev, ListNode next) {
    	_prev = prev;
    	_next = next;
    	if (prev != null)
    		prev._next = this;
    	if (next != null)
    		next._prev = this;
    }
    
    public void attachAfter(ListNode prev) {
    	attachBetween(prev,(prev == null ? null : prev._next));
//    	_prev = prev;
//    	_next = prev._next;
//    	if (prev._next != null) /* NOT inserting at the tail*/
//    		prev._next._prev = this;
//    	prev._next = this;
    }
    
    public void attachBefore(ListNode next) {
    	attachBetween((next == null ? null : next._prev),next);
//    	_next = next;
//    	_prev = next._prev;
//    	if (next._prev != null) /* NOT inserting at the head */
//    		next._prev._next = this;
//    	next._prev = this;   
    }
    
    /**
     * Removes this node from its list.
     *
     */
    public void detach() {
    	if (_prev != null)
    		_prev._next = _next;
    	if (_next != null)
    		_next._prev = _prev;
    	_next = null;
    	_prev = null;
    }
    
	public void copyFrom(Copyable obj) {
		ListNode src = (ListNode)obj;
		_data = src._data;
		_next = src._next;
	}
	public void copyTo(Copyable obj) {
		ListNode dst = (ListNode)obj;
		dst._data = _data;
		dst._next = _next;
	};
	
	public void free() throws CollectionsException {
		_data = null;
		_prev = null;
		_next = null;
		super.free();
	}
}