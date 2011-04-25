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

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.realtime.MemoryArea;
import javax.realtime.RealtimeThread;

import rtjdds.rtps.exceptions.CollectionsException;


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
public class RTHashtableReentrant implements RTMap{
	
	/* pool for contained objects */
	private ObjectPool _pool = null;
	/* the hashtable */
	private RTMap _hashtable = null;
	
	
	public RTHashtableReentrant(Class type, int size, HashFunction hasher, MemoryArea mem) 
	throws CollectionsException {
		_pool = new ObjectPool(type,size,mem);
		_hashtable = new RTHashtable(size,hasher,mem);
	}
	
	public RTHashtableReentrant(Class type, int size, HashFunction hasher) 
	throws CollectionsException {
		this(type,size,hasher,/*ImmortalMemory.instance()*/RealtimeThread.getCurrentMemoryArea());
	}
	
	public RTHashtableReentrant(Constructor c, Object[] args, int size, HashFunction hasher, MemoryArea mem) 
	throws CollectionsException {
		this._pool = new ObjectPool(c,args,size,mem);
		_hashtable = new RTHashtable(size,hasher,mem);
	}
	
	public RTHashtableReentrant(Constructor c, Object[] args, int size, HashFunction hasher) 
	throws CollectionsException {
		this(c,args,size,hasher,/*ImmortalMemory.instance()*/RealtimeThread.getCurrentMemoryArea());
	}
	
	public RTHashtableReentrant (ObjectPool pool, HashFunction hasher, MemoryArea mem) 
	throws CollectionsException {
		this._pool = pool;
		_hashtable = new RTHashtable(pool.capacity(),hasher,mem);
	}
	
	public RTHashtableReentrant (ObjectPool pool, HashFunction hasher) 
	throws CollectionsException {
		this(pool,hasher,/*ImmortalMemory.instance()*/RealtimeThread.getCurrentMemoryArea());
	}
	
	/**
	 * Puts an object into the table. Runs in <i>O(1)</i> because
	 * it is possible to associate more values to the same key. If the
	 * uniqueness key policy is needed, use the <code>putUnique()</code> method.<br/>
	 * The hard-copy of the value object is done during the insertion<br/>
	 * The idea is to use this method in a ScopedMemory to copy the 
	 * object in the ImmortalMemory. This is completely scope-safe.
	 * 
	 * @param key
	 * @param value
	 * @throws CollectionsException 
	 */
	public void put (Object key, Copyable value) throws CollectionsException {
		/* if the table is full these methods throw an exception */
		PoolManagedObject recycled = _pool.borrowObj();
//		Copyable recycled = (Copyable)_pool.borrowObj();
		/* hard-copy of the passed object */
		value.copyTo(recycled);
		_hashtable.put(key,recycled);
	}
	
	/**
	 * Puts the key-value pair in the table according to the key-uniqueness policy.<br/>
	 * Runs in <i>O(n)</i> because a call to <code>contains(key)</code> is done before
	 * the insertion
	 * 
	 * @param key
	 * @param value
	 * @throws CollectionsException 
	 * 			If the table is full or if there is already one or more values associated
	 * 			to the given key. 
	 */
	public void putUnique(Object key, Copyable value) throws CollectionsException {
		if ( ! contains(key) ) {
			put(key,value);
		}
		else {
			throw new CollectionsException("Key already present in the table");
		}
	}
	
	/**
	 * Lookups the value associated with the given key.<br/>
	 * The execution time of this method strongly depends on the implementation
	 * of the <code>KeyHasher</code> and the capacity of the given <code>ObjectPool</code>. 
	 * If <code>pool.getSize>=hasher.getDomainWidth()</code> the method surely
	 * executes in <i>O(1)</i>, else the execution time depends on the number of 
	 * collisions. Anyway this time is bounded to <i>O(n)</i> where <i>n</i> is the
	 * maximum collisions grade of the provided hash function.
	 * 
	 * @param key
	 * @return the reference to the value if the the key 
	 */
	public Object get (Object key) {
		return /*(PoolManagedObject)*/_hashtable.get(key);
	}
	
	/**
	 * Fills the passed array with references to all the objects contained in this hashtable.
	 * If no used objects are available, <code>null</code> is returned.<br/>
	 * 
	 * @param array
	 * 			The array that will be filled with contained references
	 * @return
	 * 			The array that will be filled with contained references
	 * @throws CollectionsException
	 * 			If the given array is smaller than the free objects contained in this pool
	 */
	public Object[] getAll(Object[] array) throws CollectionsException {
		return (PoolManagedObject[]) _hashtable.getAll(array);
	}
	
	/**
	 * Removes the value associated with the given key from the table.<br/>
	 * As the <code>get()</code> method, the execution time here is linear
	 * and bounded at <i>O(n)</i>.
	 * 
	 * @param key
	 * @return <code>true</code> if a value has been removed, <code>false</code> otherwise.
	 * @throws CollectionsException
	 */
	public Object remove(Object key) {
		PoolManagedObject obj = (PoolManagedObject) _hashtable.remove(key);
		if (obj != null) {
			try {
				obj.free();
			} catch (CollectionsException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}
	
	/**
	 * 
	 * @param key
	 * @return <code>true</code> if there is at least one value associated 
	 * 			to the given key, <code>false</code> otherwise.
	 */
	public boolean contains(Object key) {
		return get(key) == null ? false : true;
	}
	
	public int capacity() {
		return _hashtable.capacity();
	}
	
	public int freePos() {
		return _hashtable.freePos();
	}
	
	public int size() {
		return _hashtable.size();
	}

	public Object put(Object key, Object value) {
		if (value instanceof Copyable) {
			try {
				put(key,(Copyable)value);
			} catch (CollectionsException e) {
				e.printStackTrace();
			}
		} 
		return value;
	}

	public void putUnique(Object key, Object value) throws CollectionsException {
		if (value instanceof Copyable) {
			putUnique(key,(Copyable)value);
		} 
		else
			throw new CollectionsException("Can put only a Copyable object");
	}

	public void clear() {
		_pool.clear();
		_hashtable.clear();
	}

	//
	//	methods from java.util.Map
	//
	
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
