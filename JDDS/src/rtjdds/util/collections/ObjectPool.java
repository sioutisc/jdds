//================================================================
//
//	ObjectPool.java - Created by kerush, 2006 23-nov-06 9:32:42 
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

import javax.realtime.MemoryArea;
import javax.realtime.RealtimeThread;

import rtjdds.rtps.exceptions.CollectionsException;
import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;

/**
 * An <code>ObjectPool</code> is a container for <code>RecyclableObject</code>s that offers
 * recycling functionalities. It has a fixed size to improve predictability.<br/>
 * 
 * 
 * @author kerush
 *
 */
public class ObjectPool {
	
	/*
	 * 
	 */
	
	/* references to managed objects */
	private PoolManagedObject[] _freeObjs = null;
	/* initial size, means pool empty */
	private int _peek = -1; /* range between (-1) and (freeObjs.length-1) */
	
	private void register(PoolManagedObject obj) {
		if (_peek < _freeObjs.length-1) {
			_freeObjs[++_peek] = obj;
			obj._repo_index = _peek;
			obj._pool = this;
		}
	}

	// ----------------------------------------------------------
	// initialization-phase methods
	// ----------------------------------------------------------
	
	/**
	 * Creates the pool over the given array of objects.<br/>
	 * No copies are done internally, it is user responsibility to
	 * create the objects references in the passed array observing
	 * scope rules.
	 * @throws CollectionsException
	 * 							If any of the contained objects are not 
	 * 							subclasses of <code>RecyclableObject</code>
	 */
	public ObjectPool(PoolManagedObject[] objs) throws CollectionsException {
		_freeObjs = new PoolManagedObject[objs.length];
		for (int i=0; i<objs.length; i++) {
			/* check over Class type, it has to implement RecyclableObject */
			if (! PoolManagedObject.class.isAssignableFrom(objs[i].getClass()) ) {
				throw new CollectionsException("The given class is not a subclass of RecyclableObject");
			}
			register(objs[i]);
		}
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"new()",
				"Created a new ObjectPool of capacity "+objs.length+" in "+RealtimeThread.getCurrentMemoryArea());
		GlobalProperties.logger.printMemStats();
	}
	
	/**
	 *
	 * Initializes the repository with <code>depth<code> objects
	 * of the given <code>Class</code>. Objects are instantiated
	 * in the given <code>MemoryArea</code>. The execution of this
	 * method is completely scope-safe.
	 *
	 *
	 * @param type 
	 * 				The class of every object contained in the pool. Note
	 * 				that here the default constructor is used. If the 
	 * 				passed class doesn't have a default constructor,
	 * 				an <code>InstantiationException</code> is thrown.
	 * @param size
	 * 				The size of the pool
	 * @param mem
	 * 				The memory where the pool will be created
	 * @throws CollectionsException
	 * 				If the given class is not a subclass of <code>RecyclableObject</code>.<br/>
	 * 				If the size of the pool is non-positive.<br/>
	 * 				If an <code>InstantiationException</code> is thrown during the
	 * 				creation of the internally contained objects calling the default constructor.
	 */
	public ObjectPool(Class type, int size, MemoryArea mem) throws CollectionsException {
		/* check over Class type, it has to implement RecyclableObject */
		if (! PoolManagedObject.class.isAssignableFrom(type) ) 
		{throw new CollectionsException("The given class is not a subclass of RecyclableObject");}
		/* check over depth... */
		if (size < 1) 
		{throw new CollectionsException("The size of the pool has to be a positive integer");}
		/* creation of the objects -- space allocation */
		try{
			_freeObjs = (PoolManagedObject[])mem.newArray(PoolManagedObject.class,size);
		}
		catch (Exception e) {
			throw new CollectionsException("Instantiation Exception: "+e.getMessage());
		}
		for (int i=0; i<size; i++) {
			/* this cast is safe */
			try {
				register((PoolManagedObject)mem.newInstance(type));
//				freeObjs[i] = (RecyclableObject)mem.newInstance(type);
			} catch (Exception e) {
				throw new CollectionsException("Instantiation Exception: "+e.getMessage());
			}
		}
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"new()",
				"Created a new ObjectPool of capacity "+size+" in "+mem);
		GlobalProperties.logger.printMemStats();
//		_peek = _freeObjs.length-1; /* already setted by register() */
	}
	
	/**
	 *
	 * Initializes the repository with <code>depth<code> objects
	 * with the given <code>Constructor</code> and its relative arguments. 
	 * Objects are instantiated in the given <code>MemoryArea</code>. The execution of this
	 * method is completely scope-safe.
	 *
	 *
	 * @param c
	 * 				The <code>Constructor</code> of every object contained in the pool. If the 
	 * 				instantiation fails using the given constructor and arguments,
	 * 				an <code>InstantiationException</code> is thrown.
	 * @param args
	 * 				The arguments passed to the constructor.
	 * @param size
	 * 				The size of the pool
	 * @param mem
	 * 				The memory where the pool will be created
	 * @throws CollectionsException
	 * 				If the given class is not a subclass of <code>RecyclableObject</code>.<br/>
	 * 				If the size of the pool is non-positive.<br/>
	 * 				If an <code>InstantiationException</code> is thrown during the
	 * 				creation of the internally contained objects calling the passed constructor.
	 */
	public ObjectPool(Constructor c, Object[] args, int size, MemoryArea mem) throws CollectionsException {
		/* check over Class type, it has to implement RecyclableObject */
		if (! PoolManagedObject.class.isAssignableFrom(c.getDeclaringClass()) ) 
		{throw new CollectionsException("The given class is not a subclass of RecyclableObject");}
		/* check over depth... */
		if (size < 1) 
		{throw new CollectionsException("The size of the pool has to be a positive integer");}
		/* creation of the objects -- space allocation */
		try {
			_freeObjs = (PoolManagedObject[])mem.newArray(PoolManagedObject.class,size);
		}
		catch (Exception e) {
			throw new CollectionsException("Instantiation Exception: "+e.getMessage());
		}
		for (int i=0; i<size; i++) {
			/* this cast is safe */
			try {
				register((PoolManagedObject)mem.newInstance(c,args));
//				freeObjs[i] = (RecyclableObject)mem.newInstance(c,args);
			} catch (Exception e) {
				throw new CollectionsException("Instantiation Exception: "+e.getMessage());
			}
		}
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"new()",
				"Created a new ObjectPool of capacity "+size+" in "+mem);
		GlobalProperties.logger.printMemStats();
//		_peek = _freeObjs.length-1; /* already setted by register() */
	}
	
	/**
	 * Equivalent to <code>new ObjectPool(type, size, RealtimeThread.getCurrentMemoryArea())<code>
	 * @param type
	 * @param depth
	 * @throws Exception
	 */
	public ObjectPool(Class type, int size) throws Exception {
		this(type, size, RealtimeThread.getCurrentMemoryArea());
	}
	
	/**
	 * Equivalent to <code>new ObjectPool(c, args, size, RealtimeThread.getCurrentMemoryArea())<code>
	 * @param c
	 * @param args
	 * @param depth
	 * @throws Exception
	 */
	public ObjectPool(Constructor c, Object[] args, int size) throws Exception {
		this(c, args, size, RealtimeThread.getCurrentMemoryArea());
	}
	
	
	// ----------------------------------------------------------
	// mission-phase methods
	// ----------------------------------------------------------
	
	/**
	 * Returns a reference to a free internally contained object.<br/>
	 * It is useful to override in subclasses for rigth casting
	 * the return value. 
	 * 
	 * @throws CollectionsException if the pool is empty
	 * @return the reference to the internally contained object
	 */
	public synchronized PoolManagedObject borrowObj() throws CollectionsException {
		if (_peek >= 0) {
			return _freeObjs[_peek--];
			/* peek==-1 means pool empty */
		}
		else {
			throw new CollectionsException("The pool is empty");
		}
	}
	
	/**
	 * Return the previously borrowed object to the pool.<br/>
	 * 
	 * @param obj the object to return. It has to be pool-native, previously
	 * borrowed by <code>borrowObj()</code>
	 * @throws CollectionsException if the returned object is not pool-native or the
	 * pool is full
	 */
	public synchronized void returnObj(PoolManagedObject obj) throws CollectionsException {
		/* here we check if the object is pool-native or not... */
		if (_peek < _freeObjs.length-1) {
			/* swap used and free reference */
			if (_freeObjs[obj._repo_index]==obj) {
				_freeObjs[obj._repo_index] = _freeObjs[_peek+1];
				_freeObjs[obj._repo_index]._repo_index = obj._repo_index;
				_freeObjs[++_peek] = obj;
				obj._repo_index = _peek;
				/* (peek==freeObjs.length-1) means pool full */
			}
			else {
				throw new CollectionsException("The passed object is not pool-native (pool-managed)");
			} 
		}
		else {
			throw new CollectionsException("The pool is full, cannot add more objects");
		}
	}
	
	/**
	 * Hard-Copies the passed object into the pool. This method is scope-safe
	 * cause deep-copy is done.
	 *  
	 * @param obj
	 * @return The reference to the internally contained object where the passed object has been copied
	 * @throws CollectionsException If the pool is full
	 */
//	public synchronized PoolManagedObject copyInto(PoolManagedObject obj) throws CollectionsException {
//		PoolManagedObject recycled = this.borrowObj();
//		recycled.copyFrom(obj);
//		return recycled;
//	}
	
	/**
	 * Fills the passed array with references to all the used (borrowed) objects.
	 * If no used objects are available, <code>null</code> is returned.<br/>
	 * 
	 * @param array
	 * 			The array that will be filled with contained references
	 * @return
	 * 			The array that will be filled with contained references
	 * @throws CollectionsException
	 * 			If the given array is smaller than the free objects contained in this pool
	 */
	public Object[] getAllBorrowed(Object[] array) throws CollectionsException {
		if (array.length < getUsedObjectNumber())
			throw new CollectionsException("The passed array is too small, "+array.length+"<"+getUsedObjectNumber());
		for (int i=0; i<getUsedObjectNumber(); i++) {
			array[i] = _freeObjs[_peek+1+i];
		}
		return array;
	}
	
	public synchronized void clear() {
		_peek = -1;
	}
	
	/**
	 * 
	 * @return The size of this pool
	 */
	public int capacity() {
		return _freeObjs.length;
	}
	
	/**
	 * 
	 * @return The number of free (available) objects that can be borrowed.
	 */
	public int getFreeObjectNumber() {
		return _peek+1;
	}
	
	/**
	 * 
	 * @return The number of used (borrowed) objects.
	 */
	public int getUsedObjectNumber() {
		return _freeObjs.length - getFreeObjectNumber();
	}
	
}