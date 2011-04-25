//================================================================
//
//	CacheChange.java - Created by kerush, 2006 23-set-06 5:01:18 
//
//================================================================
//
//					  R T S J D D S  version 0.1
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

import rtjdds.rtps.messages.elements.EntityId;
import rtjdds.rtps.messages.elements.GUID;
import rtjdds.rtps.messages.elements.GuidPrefix;
import rtjdds.rtps.messages.elements.SequenceNumber;
import rtjdds.rtps.messages.elements.SerializedData;
import rtjdds.rtps.messages.elements.Timestamp;
import rtjdds.rtps.types.EntityId_t;
import rtjdds.rtps.types.GuidPrefix_t;
import rtjdds.rtps.types.SequenceNumber_t;
import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;
import rtjdds.util.collections.Copyable;
import rtjdds.util.collections.PoolManagedObject;

public class CacheChange extends PoolManagedObject implements Comparable, Copyable {
	
	public static final short ALIVE = 0;
	public static final short NOT_ALIVE_DISPOSED = 1;
	public static final short NOT_ALIVE_UNREGISTERED = 2;
	
	protected short _kind;
	
	protected GUID _writerGUID = null;
	
	protected InstanceHandle _instanceHandle = null;
	
	protected SequenceNumber _sequenceNumber = null;
	
	protected SerializedData _data = null;
	
	protected Timestamp _timestamp = null;
	
	public CacheChange(int length) {
		_data = new SerializedData(new byte[length]);
		_timestamp = new Timestamp();
		_sequenceNumber = new SequenceNumber();
		_instanceHandle = new InstanceHandle();
		_writerGUID = new GUID();
		_kind = NOT_ALIVE_UNREGISTERED;
	}
	
	/**
	 * Constructor without timestamp
	 * @param kind
	 * @param writerGUID
	 * @param instanceHandle
	 * @param sn
	 * @param data
	 */
	public CacheChange(short kind, GUID writerGUID, InstanceHandle instanceHandle, SequenceNumber sn, SerializedData data) {
		this(null, kind, writerGUID, instanceHandle, sn, data);
//		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"new()","Creating a cache change");
//		GlobalProperties.logger.printMemStats();
	}
	
	/**
	 * Constructor with timestamp
	 * @param ts
	 * @param kind
	 * @param writerGUID
	 * @param instanceHandle
	 * @param sn
	 * @param data
	 */
	public CacheChange(Timestamp ts, short kind, GUID writerGUID, InstanceHandle instanceHandle, SequenceNumber sn, SerializedData data) {
//		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"new()","Creating a cache change");
//		GlobalProperties.logger.printMemStats();
		_kind = kind;
		_writerGUID = writerGUID;
		_instanceHandle = instanceHandle;
		_sequenceNumber = sn;
		_data = data;
		_timestamp = ts;
	}
	
	/**
	 * Creates an empty CacheChange. Used to initialize ObjectPools.
	 * @param data
	 */
	public CacheChange(SerializedData data) {
//		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"new()","Creating a cache change");
//		GlobalProperties.logger.printMemStats();
		GuidPrefix prefix = new GuidPrefix(new GuidPrefix_t(new byte[]{0,0,0,0,0,0,0,0,0,0,0,0}));
		EntityId entityId = new EntityId(new EntityId_t(new byte[]{0,0,0},(byte)0));
		_writerGUID = new GUID(prefix,entityId);
		_instanceHandle = new InstanceHandle(0);
		_sequenceNumber = new SequenceNumber(new SequenceNumber_t(0));
		_kind = (short) 0;
		_data = data;
		_timestamp = null;
	}

	/**
	 * @return Returns the data.
	 */
	public SerializedData getData() {
		return _data;
	}

	/**
	 * @return Returns the instanceHandle.
	 */
	public InstanceHandle getInstanceHandle() {
		return _instanceHandle;
	}

	/**
	 * @return Returns the kind.
	 */
	public short getKind() {
		return _kind;
	}
	
	public String getKindString() {
		switch (_kind) {
		case ALIVE : return "ALIVE";
		case NOT_ALIVE_DISPOSED : return "NOT_ALIVE_DISPOSED";
		case NOT_ALIVE_UNREGISTERED : return "NOT_ALIVE_UNREGISTERED";
		default : return "UNKNOWN";
		}
	}
	
	/**
	 * Prints the CacheChange on a String
	 */
	public String toString() {
		String out = "";
		out += "GUID="+_writerGUID+" SN="+_sequenceNumber+" TS="+_timestamp+
		" KIND="+getKindString()+" HANDLE="+_instanceHandle.getHandle()+" DATA="+_data;
		return out;
	}

	/**
	 * @return Returns the sequenceNumber.
	 */
	public SequenceNumber getSequenceNumber() {
		return _sequenceNumber;
	}

	/**
	 * @return Returns the writerGUID.
	 */
	public GUID getWriterGUID() {
		return _writerGUID;
	}
	

	////////////////////////////////////////////////////////
	// methods from Comparable
	////////////////////////////////////////////////////////

	/**
	 * Compares this CacheChange to the passed one using contained
	 * timestamp.<br/>
	 */
	public int compareTo(Object o) {
		//return _sequenceNumber.compareTo(((CacheChange)arg0)._sequenceNumber);
		return _timestamp.compareTo(((CacheChange)o)._sequenceNumber);
	}
	
	public synchronized boolean equals(Object o) {
		if (o instanceof CacheChange) {
			CacheChange cmp = (CacheChange) o;
			return _writerGUID.equals(cmp._writerGUID) && _sequenceNumber.equals(cmp._sequenceNumber);
		}
		return false;
	}
	
	////////////////////////////////////////////////////////
	// methods from Copyable
	////////////////////////////////////////////////////////

	public void copyFrom(Copyable obj) {
		if (obj instanceof CacheChange && obj != null) {
			CacheChange src = (CacheChange)obj;
			_instanceHandle.setHandle(src._instanceHandle.getHandle());
			_kind  = src._kind;
			_sequenceNumber.copyFrom(src._sequenceNumber);
			_writerGUID.copyFrom(src._writerGUID);
			_data.copyFrom(src._data);
			if (_timestamp != null) {
				_timestamp.copyFrom(_timestamp);
			}
		}
		else {
			GlobalProperties.logger.log(Logger.WARN,this.getClass(),"copyFrom()",
					"Cannot copy from a non CacheChange object");
		}
	}

	public void copyTo(Copyable obj) {
		if (obj instanceof CacheChange && obj != null) {
			CacheChange dst = (CacheChange)obj;
//			GlobalProperties.logger.log(Logger.INFO,this.getClass(),"copyTo()"," BEFORE :: "+dst.toString());
			_instanceHandle.setHandle(dst._instanceHandle.getHandle());
			dst._kind = _kind;
			_sequenceNumber.copyTo(dst._sequenceNumber);
			_writerGUID.copyTo(dst._writerGUID);
			_data.copyTo(dst._data);
			if (_timestamp != null) {
				_timestamp.copyTo(dst._timestamp);
			}
//			GlobalProperties.logger.log(Logger.INFO,this.getClass(),"copyTo()"," AFTER :: "+dst.toString());
		}
		else {
			GlobalProperties.logger.log(Logger.WARN,this.getClass(),"copyTo()",
					"Cannot copy to a non CacheChange object");
		}
	}

}
