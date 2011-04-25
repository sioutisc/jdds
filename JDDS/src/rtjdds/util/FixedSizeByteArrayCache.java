//================================================================
//
//	FixedSizeByteArrayCache.java - Created by kerush, 2006 14-nov-06 12:36:52 
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

public class FixedSizeByteArrayCache {
	
	private int depth = 0;
	
	private int dataLength = 0;
	
	private byte[] data[] = null;
	
	/**
	 * Constructs the cache of (depth*data_size) bytes.
	 * @param depth
	 * @param data_size
	 */
	public FixedSizeByteArrayCache(int depth, int data_size) {
		this.depth = depth;
		this.dataLength = data_size;
		this.data = new byte[dataLength][depth];
		// initialization
		for (int i=0; i<depth; i++) {
			data[i] = new byte[dataLength];
		}
	}
	
	/**
	 * Inserts the given data into the given position.
	 * This method internally <b>copies</b> (via <code>System.arraycopy()</code>)
	 * the data into the internal buffer.<br/>
	 * If the data to be inserted is longer than the dimension of the
	 * slot, the data will be truncated.
	 * @param data
	 * @param pos
	 */
	public void insert(byte[] data, int pos) {
		if (pos > depth || pos < 0) {
			GlobalProperties.logger.log(Logger.WARN, this.getClass(), "insert()", "Trying to insert" +
			"a data into an unallowed position, nothing inserted!");
			return;
		}
		if (data.length > this.dataLength) {
			GlobalProperties.logger.log(Logger.WARN, this.getClass(), "insert()", "Trying to insert" +
					"a data bigger than the slot dimension, the data will be TRUNCATED");
		}
		// inserting...
		System.arraycopy(data,0,this.data[pos],0,dataLength);
	}
	
	/**
	 * Returns a reference to the data contained at the given position.<br/>
	 * Returns <code>null</code> if the specified position is invalid.
	 * @param pos
	 * @return
	 */
	public byte[] getReference(int pos) {
		if (pos > depth || pos < 0) {
			GlobalProperties.logger.log(Logger.WARN, this.getClass(), "get()", "Trying to retrieve" +
			"a data from an unexisting position, nothing retrieved!");
			return null;
		}
		// retrieving
		return this.data[pos];
	}
	

}
