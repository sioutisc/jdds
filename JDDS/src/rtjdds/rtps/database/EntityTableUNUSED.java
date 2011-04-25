//================================================================
//
//	EntityDB.java - Created by kerush, 2006 1-dic-06 10:12:51 
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
package rtjdds.rtps.database;

import javax.realtime.ImmortalMemory;
import javax.realtime.MemoryArea;
import javax.realtime.RealtimeThread;

import rtjdds.rtps.exceptions.CollectionsException;
import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;
import rtjdds.util.collections.HashFunction;
import rtjdds.util.collections.ObjectPool;
import rtjdds.util.collections.RTHashtable;
import rtjdds.util.collections.RTHashtableReentrant;
import rtjdds.util.collections.RTMap;

/**
 * Base class for the Entity Database. Each subclass should expose
 * typed methods to register, deregister and lookup entities into
 * the database.<br/>
 * Here only the implementation of two methods is requested to each
 * subclass:
 * <ul>
 * 	<li>createPool()</li>
 *  <li>createHasher()</li> 
 * <ul> 
 * that are used to initialize the internally contained (RT)Hashtable·<br/>
 * Note that in this implementation a <code>RTHashtable</code> is used.<br/>
 * 
 * @author kerush
 *
 */
public abstract class EntityTableUNUSED {
	
	protected RTMap _table = null;
	
	protected HashFunction _hasher = null;
	
	protected ObjectPool _pool = null;
	
	/* damn rtsj! */
	private CollectionsException _exception = null;
	
	
	public EntityTableUNUSED(int capacity) throws CollectionsException {
		
		_hasher = createHasher();
		_table = new RTHashtable(capacity,_hasher);
		
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"new()",
				"Created new EntityDB with capacity of "+capacity+
				" rooted in "+RealtimeThread.getCurrentMemoryArea());
		
		GlobalProperties.logger.printMemStats();
	}
	
	/**
	 * Should return the reference to the ObjectPool that will contain
	 * all the entities internally managed by this Database.
	 * Note that this code is executed in <code>ImmortalMemory</code>
	 * by the constructor.
	 * 
	 * @param capacity
	 * @return
	 * @throws CollectionsException
	 */
	protected abstract ObjectPool createPool(int capacity) throws CollectionsException;
	
	/**
	 * Should return an appropriate <code>HashFunction</code> used to store
	 * and lookup entities in the database.
	 * 
	 * @return
	 */
	protected abstract HashFunction createHasher();

}
