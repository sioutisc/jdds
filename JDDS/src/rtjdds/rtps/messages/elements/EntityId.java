//================================================================
//
//	EntityId.java - Created by kerush, 2006 20-ott-06 1:06:45 
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
package rtjdds.rtps.messages.elements;

import org.omg.CORBA.portable.OutputStream;

import rtjdds.rtps.types.EntityId_t;
import rtjdds.rtps.types.EntityId_tHelper;
import rtjdds.util.BitUtility;
import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;
import rtjdds.util.collections.Copyable;

/**
 * 
 * 
 * Formato di EntityId
 * 
 * 	+--------+--------+--------+--------+
 * 	|endpoint|          	   | type   | 
 * 	|counter |	topicId		   |  id.   |
 *  +--------+--------+--------+--------+
 *  
 *  
 * @author kerush
 *
 */
public class EntityId extends SubmessageElement implements Copyable {
	
	/* PRE-DEFINED INSTANCE_ID */
	public final static EntityId participant = 
		new EntityId(new EntityId_t(new byte[]{0,0,1},EntityKindEnum.BUILTIN_PARTICIPANT));
	public final static EntityId builtInTopicWriter = 
		new EntityId(new EntityId_t(new byte[]{0,0,2},EntityKindEnum.BUILTIN_WRITER_W_KEY));
	public final static EntityId builtInTopicReader = 
		new EntityId(new EntityId_t(new byte[]{0,0,2},EntityKindEnum.BUILTIN_READER_W_KEY));
	public final static EntityId SEDPbuiltinPublicationsWriter = 
		new EntityId(new EntityId_t(new byte[]{0,0,3},EntityKindEnum.BUILTIN_WRITER_W_KEY));
	public final static EntityId SEDPbuiltinPublicationsReader = 
		new EntityId(new EntityId_t(new byte[]{0,0,3},EntityKindEnum.BUILTIN_READER_W_KEY));
	public final static EntityId SEDPbuiltinSubscriptionsWriter = 
		new EntityId(new EntityId_t(new byte[]{0,0,4},EntityKindEnum.BUILTIN_WRITER_W_KEY));
	public final static EntityId SEDPbuiltinSubscriptionsReader = 
		new EntityId(new EntityId_t(new byte[]{0,0,4},EntityKindEnum.BUILTIN_READER_W_KEY));
	public final static EntityId SEDPbuiltinParticipantWriter = 
		new EntityId(new EntityId_t(new byte[]{0,1,0},EntityKindEnum.BUILTIN_WRITER_W_KEY));
	public final static EntityId SEDPbuiltinParticipantReader = 
		new EntityId(new EntityId_t(new byte[]{0,1,0},EntityKindEnum.BUILTIN_READER_W_KEY));
	
	protected EntityId_t _value;
	
	public EntityId (EntityId_t value) {
		super(SubmessageElement.ENTITY_ID_SIZE);
		this._value = value;
	}
	
	public EntityId() {
		this (new EntityId_t(new byte[3],(byte)0x00));
	}

	public byte getTypeID() {
		return _value.entityKind;
	}
	
	public byte byte0() {
		return _value.entityKey[0];
	}
	
	public byte byte1() {
		return _value.entityKey[1];
	}
	
	public byte byte2() {
		return _value.entityKey[2];
	}
	
	public void write(OutputStream os) {
		EntityId_tHelper.write(os, _value);
	}
	
	public boolean equals(Object o) {
		EntityId id = (EntityId) o;
		return (
		java.util.Arrays.equals(this._value.entityKey, id._value.entityKey) 
		&&
		this._value.entityKind == id._value.entityKind );
	}
	
	/**
	 * Overridden method to have a good (specialized) hash code
	 * for hashtables. Now it can have 256 values, so 256 is a
	 * good choice for fixed-size hashtable dimension.
	 */
	// TODO: more accurate implementation
	public int hashCode() {
		return _value.entityKey[2];
	}
	
	public int getIntValue() {
		int out = 0;
		out |= ((int)_value.entityKind)  << 24 ;
		out |= ((int)_value.entityKey[0])<< 16 ;
		out |= ((int)_value.entityKey[1])<< 8  ;
		out |= ((int)_value.entityKey[2]);
		return out;
	}
	
	public String toString() {
		return ""+_value.entityKey[2]+_value.entityKey[1]+_value.entityKey[0]+"-"+_value.entityKind;
	}
	
	////////////////////////////////////////////////
	// methods from Copyable
	////////////////////////////////////////////////
	
	public void copyFrom(Copyable obj) {
		if (obj instanceof EntityId && obj != null) {
			EntityId src = (EntityId) obj;
			System.arraycopy(src._value.entityKey,0,_value.entityKey,0,_value.entityKey.length);
			_value.entityKind = src._value.entityKind;
		}
		else {
			GlobalProperties.logger.log(Logger.WARN,this.getClass(),"copyFrom()",
					"Cannot copy from non EntityId object");
		}
	}

	public void copyTo(Copyable obj) {
		if (obj instanceof EntityId && obj != null) {
			EntityId dst = (EntityId) obj;
			System.arraycopy(_value.entityKey,0,dst._value.entityKey,0,_value.entityKey.length);
			dst._value.entityKind = _value.entityKind;
		}
		else {
			GlobalProperties.logger.log(Logger.WARN,this.getClass(),"copyTo()",
					"Cannot copy to non EntityId object");
		}
	}
	
}
