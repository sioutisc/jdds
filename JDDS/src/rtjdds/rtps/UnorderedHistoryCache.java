//================================================================
//
//	UnorderedHistoryCache.java - Created by kerush, 2006 5-dic-06 4:22:25 
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
package rtjdds.rtps;

import rtjdds.rtps.exceptions.CollectionsException;
import rtjdds.rtps.exceptions.OutOfResourceException;
import rtjdds.rtps.messages.elements.SequenceNumber;
import rtjdds.rtps.messages.elements.SerializedData;
import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;
import rtjdds.util.collections.ObjectPool;
import rtjdds.util.collections.RTLinkedList;
import rtjdds.util.collections.RTList;
import rtjdds.util.collections.RTListIterator;

public class UnorderedHistoryCache extends HistoryCache {

	private ObjectPool _pool = null;
	private RTList _list = null;
	
	/**
	 * 
	 * @param data_lenght in bytes
	 * @param deep in unità
	 * @throws CollectionsException 
	 */
	public UnorderedHistoryCache(int data_length, int deep) throws CollectionsException {
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"new()",
				"Creating an UnorderedHistoryCache with depth of "+deep+"." +
						" Max sample size = "+data_length+" bytes");
		GlobalProperties.logger.printMemStats();
		CacheChange[] changes = new CacheChange[deep];
		for (int i=0; i<deep; i++) {
			changes[i] = new CacheChange(data_length);
		}
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"new()","Array created");
		GlobalProperties.logger.printMemStats();
		_pool = new ObjectPool(changes);
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"new()","Pool created");
		GlobalProperties.logger.printMemStats();
		_list = new RTLinkedList(deep);
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"new()","List created");
		GlobalProperties.logger.printMemStats();
	}
	
	public CacheChange readNextChange(CacheChange c) {
		return (CacheChange)_list.getTail();
	}

	public CacheChange takeNextChange(CacheChange c) {
		CacheChange out = (CacheChange)_list.removeTail();
			if (out != null) {
			try {
				_pool.returnObj(out);
			} catch (CollectionsException e) {
				e.printStackTrace();
			}
		}
		return out;
	}
	
	
	public void add_change(CacheChange c) throws OutOfResourceException {
		try {
			
			CacheChange recycled = (CacheChange) _pool.borrowObj();
			c.copyTo(recycled);
			_list.add(recycled);
			
//			GlobalProperties.logger.log(Logger.INFO, this.getClass(), "add_change()",
//					"Current Cache "+this.toString());
		} catch (CollectionsException e) {
			throw new OutOfResourceException(e.getMessage());
		}
	}

	public void remove_change(CacheChange c) {
		try {
			CacheChange recycled = (CacheChange) _list.remove(c);
			_pool.returnObj(recycled);
		} catch (CollectionsException e) {
			e.printStackTrace();
		}
	}

	public CacheChange get_change(CacheChange c) {
		// TODO Auto-generated method stub
		return null;
	}

	public SequenceNumber get_seq_num_min() {
		// TODO Auto-generated method stub
		return null;
	}

	public SequenceNumber get_seq_num_max() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString() {
		String out = "";
		RTListIterator iterator = _list.listIterator();
		while (iterator.hasNext()) {
			CacheChange change = (CacheChange) iterator.next();
			out += "\n"+change;
		}
		return out;
	}

}
