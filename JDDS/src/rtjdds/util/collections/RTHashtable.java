//================================================================
//
//	RTHashtable.java - Created by kerush, 2006 24-nov-06 2:39:19 
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

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.realtime.MemoryArea;
import javax.realtime.RealtimeThread;

import rtjdds.rtps.exceptions.CollectionsException;
import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;


/**
 * This class implements an extremely predictable HashMap. The size of the
 * map is setted at initialization-time and cannot be modified in mission-phase.<br/>
 * When a <code>put()</code> is done, the passed object is hard-copied to the 
 * internal object by the <code>copyInto()</code> method of the contained 
 * <code>ObjectPool</code>.
 * 
 * @author kerush
 *
 */
public class RTHashtable /*implements java.util.Map*/ implements RTMap {
	
	/* pool for hash nodes */
	private ObjectPool _nodePool = null;
	/* array for the hash collisions lists */
	private HashBucketNode[] _bucket = null;
	/* the user-provided hash function */
	private HashFunction _hasher = null;
	
	/* RTSJ hai hai hai */
	private CollectionsException exception = null;
	
	/**
	 * Constructs a new <code>RTHashtable</code>, bounded in size, that defaults
	 * in the given <code>MemoryArea</code>.
	 * 
	 * @param size
	 * 				the maximum number of objects that can be stored in this hashtable
	 * @param hasher
	 * 				the hash function
	 * @param mem
	 * 				the memory area where this hashtable will be created. Note that
	 * 				the area has to be compatible with the area of the objects that
	 * 				will be stored in.
	 * @throws CollectionsException
	 * 				if an error occured during the creation of this hashtable
	 */
	public RTHashtable(final int size, final HashFunction hasher, final MemoryArea mem) throws CollectionsException {
		mem.enter( new Runnable() { public void run() {
			try {
				_nodePool = new ObjectPool(HashBucketNode.class,size,mem);
			} catch (CollectionsException e) {
				exception = e;
			}
			_bucket = new HashBucketNode[hasher.getHashDomainWidth()];	
		}});
		if (exception != null) throw exception;
		this._hasher = hasher;
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"new()",
				"Created a new RTHashtable of capacity "+size+" in "+mem);
	}
	
	/**
	 * As the above constructor, but the table will defaults in
	 * <code>RealtimeThread.getCurrentMemoryArea()</code>.
	 * 
	 * @param size
	 * @param hasher
	 * @throws CollectionsException
	 */
	public RTHashtable(int size, HashFunction hasher) 
	throws CollectionsException {
		this(size,hasher,RealtimeThread.getCurrentMemoryArea());
	}
	
	///////////////////////////////////////////////
	//	private methods
	///////////////////////////////////////////////
	
	/**
	 * Adds the node at the top of the bucket. Runs in O(1).
	 */
	private void addInBucket(int hash, HashBucketNode node) {
		synchronized (_bucket) {
			/* insert at the top of the bucket */
//			node.addBetween(_bucket[hash], (_bucket[hash] == null) ? null : _bucket[hash]._next); 
			node.attachBefore(_bucket[hash]);
			_bucket[hash] = node;
	    }
	}
	
	/**
	 * Removes from the bucket the node with the given collisionID.
	 * @param bucket
	 * @param key
	 * @throws CollectionsException 
	 */
	private Object removeFromBucket(int hash, int collId) throws CollectionsException {
		synchronized (_bucket) {
			HashBucketNode node = _bucket[hash];
			while (node != null) {
				if (node._collisionId == collId) {
					if (node._prev == null)
						_bucket[hash] = (HashBucketNode) node._next;
					Object tmp = node._data;
					node.detach();
					node.free();
					return tmp;
				}
				node = (HashBucketNode) node._next;
			}
        }
        return null;
	}
	
	///////////////////////////////////////////////
	//	public methods
	///////////////////////////////////////////////
	
	/* (non-Javadoc)
	 * @see rtjdds.util.collections.RTMap#put(java.lang.Object, java.lang.Object)
	 */
	public Object put (Object key, Object value) {
		/* if the table is full this method throws an exception */
		HashBucketNode node = null;
		try {
			node = (HashBucketNode) _nodePool.borrowObj();
		} catch (CollectionsException e) {
			e.printStackTrace();
			return null;
		}
		node._data = value;
		node._collisionId = _hasher.collisionId(key);
		int hash = _hasher.hash(key);
		addInBucket(hash,node);
		return value;
	}
	
	/* (non-Javadoc)
	 * @see rtjdds.util.collections.RTMap#putUnique(java.lang.Object, java.lang.Object)
	 */
	public void putUnique(Object key, Object value) throws CollectionsException {
		if ( ! contains(key) ) {
			put(key,value);
		}
		else {
			throw new CollectionsException("Key already present in the table");
		}
	}
	
	/* (non-Javadoc)
	 * @see rtjdds.util.collections.RTMap#get(java.lang.Object)
	 */
	public Object get (Object key) {
		int hash = _hasher.hash(key);
		int collisionID = _hasher.collisionId(key);
        synchronized (_bucket) {
            for (HashBucketNode i = _bucket[hash]; i != null; i = (HashBucketNode) i._next) {
                if (i._collisionId == collisionID) return i._data;
            }
        }
        return null;
	}
	
	/* (non-Javadoc)
	 * @see rtjdds.util.collections.RTMap#getAll(java.lang.Object[])
	 */
	public Object[] getAll(Object[] array) throws CollectionsException {
		if (array.length < _nodePool.getUsedObjectNumber())
			throw new CollectionsException("The passed array is too small, "+array.length+"<"+_nodePool.getUsedObjectNumber());
		int index = 0;
		for (int i=0; i<_bucket.length; i++) {
			HashBucketNode node = _bucket[i];
			while (node != null) {
				array[index] = node._data;
				node = (HashBucketNode) node._next;
				index++;
			}
		}
		return array;
	}
	
	/* (non-Javadoc)
	 * @see rtjdds.util.collections.RTMap#remove(java.lang.Object)
	 */
	public Object remove(Object key) {
		int hash = _hasher.hash(key);
		int collisionID = _hasher.collisionId(key);
		Object obj = null;
		try {
			obj =  removeFromBucket(hash, collisionID);
		} catch (CollectionsException e) {
			e.printStackTrace();
		}
		return obj;
//        synchronized (_bucket) {
//            ListNode prev = null;
//            for (ListNode i = _bucket[hash]; i != null; ) {
//                if (i._collisionId == collisionID) {
//                    if (prev == null) {
//                    	/* we're at the top of the bucket */
//                    	ListNode tmp = _bucket[hash];
//                        _bucket[hash] = i._next;
//                        pool.returnObj(tmp._data);
//                        _nodePool.returnObj(tmp);
////                        System.out.println("\n1.removed "+((MyClass)tmp.data)+" hash="+hash+" collisionId="+tmp.collisionId);
//                        return true;
//                    } else {
//                    	/* we're in the middle of the list */
//                    	ListNode tmp = i;
//                        prev._next = i._next;
//                        pool.returnObj(tmp._data);
//                        _nodePool.returnObj(tmp);
////                        System.out.println("\n2.removed "+((MyClass)tmp.data)+" hash="+hash+" collisionId="+tmp.collisionId);
//                        return true;
//                    }
//                }
//                else {
//                	/* go ahead */
//                	prev = i;
//                	i = i._next;
//                }
//            }
//        }
//        return false;
	}
	
	//
	//	methods from java.util.Map
	//
	
	/* (non-Javadoc)
	 * @see rtjdds.util.collections.RTMap#contains(java.lang.Object)
	 */
	public boolean contains(Object key) {
		return get(key) == null ? false : true;
	}
	
	/* (non-Javadoc)
	 * @see rtjdds.util.collections.RTMap#capacity()
	 */
	public int capacity() {
		return _nodePool.capacity();
	}
	
	/* (non-Javadoc)
	 * @see rtjdds.util.collections.RTMap#freePos()
	 */
	public int freePos() {
		return _nodePool.getFreeObjectNumber();
	}
	
	/* (non-Javadoc)
	 * @see rtjdds.util.collections.RTMap#size()
	 */
	public int size() {
		return _nodePool.getUsedObjectNumber();
	}

	/* (non-Javadoc)
	 * @see rtjdds.util.collections.RTMap#clear()
	 */
	public void clear() {
		for (int i=0; i<_bucket.length; i++)
			_bucket[i] = null;
		_nodePool.clear();
	}

	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	public Set entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public Set keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	public void putAll(Map m) {
		// TODO Auto-generated method stub
		
	}

	public Collection values() {
		// TODO Auto-generated method stub
		return null;
	}

}
