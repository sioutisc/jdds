//================================================================
//
//	RTLinkedList.java - Created by kerush, 2006 30-nov-06 11:04:49 
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

//import java.util.Collection;
//import java.util.Iterator;
//import java.util.List;
//import java.util.ListIterator;

import javax.realtime.MemoryArea;
import javax.realtime.RealtimeThread;

import rtjdds.rtps.exceptions.CollectionsException;

/**
 * This class represents a list of objects limited in its capacity.
 * The capacity is specified at construction time and cannot grow
 * dynamically as in <code>java.util.List</code>.
 * 
 * @author kerush
 *
 */
public class RTLinkedList implements RTList {
	
	private ObjectPool _nodePool = null;
	private ListNode _head = null;
	private ListNode _tail = null;
	
	/**
	 * Constructs a new <code>RTLinkedList</code> object of the given capacity
	 * in the given <code>MemoryArea</code>. Objects that will be stored
	 * in the list have to be scope-safe in respect of the given memory area.
	 * In this sense all the inserting methods can throw an exception
	 * of type <code>IllegalAssigmentException</code>.<br/>
	 * Be aware that the list cannot grow its capacity dynamically.
	 * 
	 * @param capacity
	 * @param mem
	 * @throws CollectionsException
	 */
	public RTLinkedList(int capacity, MemoryArea mem) throws CollectionsException {
		if (capacity < 1 || mem == null) {
			throw new CollectionsException
			("Cannot construct a RTLinkedList of a non-positive size or in null MemoryArea");
		}
		_nodePool = new ObjectPool(ListNode.class,capacity,mem);
	}
	
	/**
	 * As the above constructor, but the current memory area is used.
	 * 
	 * @param capacity
	 * @throws CollectionsException
	 */
	public RTLinkedList(int capacity) throws CollectionsException {
		this(capacity, RealtimeThread.getCurrentMemoryArea());
	}
	
	////////////////////////////////////////////////////
	// methods from Copyable
	////////////////////////////////////////////////////

	public void copyFrom(Copyable obj) {
		RTLinkedList src = (RTLinkedList) obj;
		
	}

	public void copyTo(Copyable obj) {
		// TODO Auto-generated method stub
		
	}
	
	////////////////////////////////////////////////////
	// private methods
	////////////////////////////////////////////////////
	
	private ListNode getNodeAt(int index) throws IndexOutOfBoundsException {
		ListNode node = null;
		if (0 <= index && index < size()/2) {
			/* search forward */
			node = _head;
			for (int i=0;i<index;i++) {
				node = node._next;
			}
			return node;
		}
		else if (index >= size()/2 && index < size()) {
			/* search backward */
			node = _tail;
			for (int i=0; i<size()-index-1; i++) {
				node = node._prev;
			}
			return node;
		}
		else {
			throw new IndexOutOfBoundsException("index = "+index+" >= size = "+size());
		}
	}
	
	/**
	 * Removes the given node from the list.
	 * This method is synchronized on the entire list object. Indeed it is
	 * tread-safe.
	 * @param node
	 * @return
	 * 			the reference to the element next to the removed node.
	 * @throws CollectionsException
	 */
	private synchronized ListNode removeNodeAndGetNext(ListNode node) throws CollectionsException {
		if (node._prev == null)
			_head = node._next;
		if (node._next == null)
			_tail = node._prev;
		ListNode tmp = node._next;
		node.detach();
		node.free();
		return tmp;
	}
	
	////////////////////////////////////////////////////
	// public methods
	////////////////////////////////////////////////////
	

	/* (non-Javadoc)
	 * @see rtjdds.util.collections.RTList#add(int, java.lang.Object)
	 */
	public void add(int index, Object o) throws CollectionsException, IndexOutOfBoundsException {
		if ( index >= 0 && index < size() ) {
			/* this instr. can throw exception if the table is full */
			ListNode newNode = (ListNode) _nodePool.borrowObj();
			newNode._data = o;
			ListNode prevNode = getNodeAt(index);
			newNode.attachAfter(prevNode);
		}
		else {
			throw new CollectionsException(
					"Cannot insert the element at the given index="+index+", it is invalid.");
		}
	}

	/* (non-Javadoc)
	 * @see rtjdds.util.collections.RTList#add(java.lang.Object)
	 */
	public boolean add(Object o) throws CollectionsException {
		/* this instr. can throw exception if the table is full */
		ListNode newNode = (ListNode) _nodePool.borrowObj();
		newNode._data = o;
		newNode.attachAfter(_tail);
		_tail = newNode;
		if (_head == null)
			_head = newNode;
		return true;
	}

	/* (non-Javadoc)
	 * @see rtjdds.util.collections.RTList#contains(java.lang.Object)
	 */
	public boolean contains(Object o) {
		ListNode node = _head;
		while (node != null) {
			if (o==null ? node==null : o.equals(node)) {
				return true;
			}
			else {
				/* go ahead */
				node = node._next;
			}
		}
 		return false;
	}

	/* (non-Javadoc)
	 * @see rtjdds.util.collections.RTList#get(int)
	 */
	public Object get(int index) throws IndexOutOfBoundsException{
		return getNodeAt(index)._data;
	}
	
	public Object getHead() {
		return _head._data;
	}
	public Object getTail() {
		return _tail._data;
	}
	
	public Object removeHead() {
		Object out = null;
		if (_head != null) {
			out = _head._data;
			try {
				removeNodeAndGetNext(_head);
			} catch (CollectionsException e) {
				e.printStackTrace();
			}
		}
		return out;
	}
	
	public Object removeTail() {
		Object out = null;
		if (_tail != null) {
			out = _tail._data;
			try {
				removeNodeAndGetNext(_tail);
			} catch (CollectionsException e) {
				e.printStackTrace();
			}
		}
		return out;
	}

	/* (non-Javadoc)
	 * @see rtjdds.util.collections.RTList#indexOf(java.lang.Object)
	 */
	public int indexOf(Object o) {
		ListNode node = _head;
		int index = 0;
		while (node != null) {
			if (o==null ? node==null : o.equals(node)) {
				return index;
			}
			else {
				/* go ahead */
				node = node._next;
				index++;
			}
		}
		/* not founded */
		return -1;
	}

	/* (non-Javadoc)
	 * @see rtjdds.util.collections.RTList#isEmpty()
	 */
	public boolean isEmpty() {
		if (_head == null)
			return true;
		else
			return false;
	}

	/* (non-Javadoc)
	 * @see rtjdds.util.collections.RTList#lastIndexOf(java.lang.Object)
	 */
	public int lastIndexOf(Object o) {
		ListNode node = _tail;
		int index = size()-1;
		while (node != null) {
			if (o==null ? node==null : o.equals(node)) {
				return index;
			}
			else {
				/* go back */
				node = node._prev;
				index--;
			}
		}
		return index;
	}
	
	/* (non-Javadoc)
	 * @see rtjdds.util.collections.RTList#set(int, java.lang.Object)
	 */
	public Object set(int index, Object o) throws IndexOutOfBoundsException {
		ListNode node = getNodeAt(index);
		node._data = o;
		return node;
	}

	/* (non-Javadoc)
	 * @see rtjdds.util.collections.RTList#remove(int)
	 */
	public Object remove(int index) throws CollectionsException, IndexOutOfBoundsException {
		ListNode node = getNodeAt(index);
		removeNodeAndGetNext(node);
		return null;
	}

	/* (non-Javadoc)
	 * @see rtjdds.util.collections.RTList#remove(java.lang.Object)
	 */
	public Object remove(Object o) throws CollectionsException {
		ListNode node = _head;
		while (node != null) {
			if (o==null ? node==null : o.equals(node)) {
				removeNodeAndGetNext(node);
				return node._data;
			}
			/* go ahead */
			node = node._next;
		}
		/* not founded */
		return null;
	}
	
	/* (non-Javadoc)
	 * @see rtjdds.util.collections.RTList#removeAll(java.lang.Object)
	 */
	public boolean removeAll(Object o) throws CollectionsException {
		ListNode node = _head;
		boolean out = false;
		while (node != null) {
			if (o==null ? node==null : o.equals(node)) {
				/* remove and go ahead */
				node = removeNodeAndGetNext(node);
				out = true;
			}
			else {
				/* go ahead */
				node = node._next;
			}
		}
		/* not founded */
		return out;
	}
	
	/* (non-Javadoc)
	 * @see rtjdds.util.collections.RTList#clear()
	 */
	public void clear() throws CollectionsException {
		ListNode node = _head;
		while (node != null) {
			node = removeNodeAndGetNext(node);
		}
//		_head = _tail = null;
	}

	/* (non-Javadoc)
	 * @see rtjdds.util.collections.RTList#toArray(java.lang.Object[])
	 */
	public Object[] toArray(Object[] array) throws CollectionsException {
		if (array == null)
			throw new CollectionsException("Passed array is null");
		if (array.length < size())
			throw new CollectionsException("Passed array is too small");
		ListNode node = _head;
		for (int i=0; i<size(); i++) {
			array[i] =  node._data;
			node = node._next;
		}
		return array;
	}
	
	public RTListIterator listIterator() {
		return new RTListIteratorImpl(this,_head,0);
	}

	public RTListIterator listIterator(int index) throws IndexOutOfBoundsException {
		ListNode node = getNodeAt(index);
		return new RTListIteratorImpl(this,node,index);
	}

	/* (non-Javadoc)
	 * @see rtjdds.util.collections.RTList#size()
	 */
	public int size() {
		return _nodePool.getUsedObjectNumber();
	}
	
	/* (non-Javadoc)
	 * @see rtjdds.util.collections.RTList#capacity()
	 */
	public int capacity() {
		return _nodePool.capacity();
	}
	
	/* (non-Javadoc)
	 * @see rtjdds.util.collections.RTList#freePos()
	 */
	public int freePos() {
		return _nodePool.getFreeObjectNumber();
	}

	
	/////////////////////////////////////////////////
	//	methods from java.util.Collection
	/////////////////////////////////////////////////
	
	
//	public boolean retainAll(Collection arg0) {
//	// TODO Auto-generated method stub
//	return false;
//}
	
//	public List subList(int arg0, int arg1) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	public Object[] toArray() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
//	public Iterator iterator() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}

/**
 * @author kerush
 *
 */
class RTListIteratorImpl implements RTListIterator {

	private ListNode _node = null;
	private RTList _list = null;
	private int _index;
	
	protected RTListIteratorImpl(RTList list, ListNode node, int index) {
		_node = node;
		_list = list;
		_index = index;
	}
	
	/* (non-Javadoc)
	 * @see rtjdds.util.collections.RTListIterator#hasNext()
	 */
	public boolean hasNext() {
		return _node != null;
	}

	/* (non-Javadoc)
	 * @see rtjdds.util.collections.RTListIterator#next()
	 */
	public Object next() {
		Object tmp = _node._data;
			_node = _node._next;
			_index++;
		return tmp;
	}

	/* (non-Javadoc)
	 * @see rtjdds.util.collections.RTListIterator#hasPrevious()
	 */
	public boolean hasPrevious() {
		return _node != null;
	}

	/* (non-Javadoc)
	 * @see rtjdds.util.collections.RTListIterator#previous()
	 */
	public Object previous() {
		Object tmp = _node._data;
//		if (hasPrevious()) {
			_node = _node._prev;
			_index--;
//		}
		return tmp;
	}

	/* (non-Javadoc)
	 * @see rtjdds.util.collections.RTListIterator#nextIndex()
	 */
	public int nextIndex() {
		return _index+1;
	}

	/* (non-Javadoc)
	 * @see rtjdds.util.collections.RTListIterator#previousIndex()
	 */
	public int previousIndex() {
		return _index-1;
	}
	

	/* (non-Javadoc)
	 * @see rtjdds.util.collections.RTListIterator#remove()
	 */
	public void remove() throws CollectionsException, IndexOutOfBoundsException {
		_list.remove(_index);
	}

	/* (non-Javadoc)
	 * @see rtjdds.util.collections.RTListIterator#add(java.lang.Object)
	 */
	public void add(Object o) throws CollectionsException, IndexOutOfBoundsException {
		_list.add(_index,o);
	}

	/* (non-Javadoc)
	 * @see rtjdds.util.collections.RTListIterator#set(java.lang.Object)
	 */
	public void set(Object o) {
		_list.set(_index,o);
	}

}

