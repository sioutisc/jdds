//================================================================
//
//	GUID.java - Created by kerush, 2006 15-nov-06 2:41:26 
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

import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;
import rtjdds.util.collections.Copyable;

/**
 * GUID stands for Global Universal IDentifier. It consists of
 * a 12 bytes <code>GuidPrefix<code> and a 4 bytes <code>EntityId</code> suffix.<br/>
 * The prefix is used to identify a participant in the domain,
 * the suffix to identify the entity in the participant.
 * 
 * @see GuidPrefix
 * @see EntityId
 * 
 * @author kerush
 *
 */
public class GUID extends SubmessageElement implements Copyable {

	protected GuidPrefix _prefix;
	protected EntityId _entityId;
	
	public GUID(GuidPrefix prefix, EntityId entityId) {
		super(prefix.size+entityId.size);
		this._prefix = prefix;
		this._entityId = entityId;
	}
	
	public GUID() {
		this(new GuidPrefix(),new EntityId());
	}

	/**
	 * @return Returns the entityId.
	 */
	public EntityId getEntityId() {
		return _entityId;
	}

	/**
	 * @return Returns the prefix.
	 */
	public GuidPrefix getPrefix() {
		return _prefix;
	}
	
	// TODO implement that using the prefix too!! 
	public int getIntValue() {
		return _entityId.getIntValue();
	}

	public void write(OutputStream os) {
		_prefix.write(os);
		_entityId.write(os);
	}
	
	public void copyFrom(GUID fromGuid) {
		_prefix.copyFrom(fromGuid._prefix);
		_entityId.copyFrom(fromGuid._entityId);
	}
	
	public boolean equals(Object o) {
		GUID g = (GUID) o;
		return _prefix.equals(g._prefix) && _entityId.equals(g._entityId);
	}
	
	public String toString() {
		return ""+_prefix.toString()+":"+_entityId.toString(); 
	}
	

	////////////////////////////////////////////////
	// methods from Copyable
	////////////////////////////////////////////////
	
	public void copyFrom(Copyable obj) {
		if (obj instanceof GUID && obj != null) {
			GUID src = (GUID) obj;
			_entityId.copyFrom(src._entityId);
			_prefix.copyFrom(src._prefix);
		}
		else {
			GlobalProperties.logger.log(Logger.WARN,this.getClass(),"copyFrom()",
					"Cannot copy from non GUID object");
		}
	}

	public void copyTo(Copyable obj) {
		if (obj instanceof GUID && obj != null) {
			GUID dst = (GUID) obj;
			_entityId.copyTo(dst._entityId);
			_prefix.copyTo(dst._prefix);
		}
		else {
			GlobalProperties.logger.log(Logger.WARN,this.getClass(),"copyTo()",
					"Cannot copy to non GUID object");
		}
	}

}
