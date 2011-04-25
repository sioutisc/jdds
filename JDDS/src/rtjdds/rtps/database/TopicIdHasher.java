//================================================================
//
//	TopicIdHasher.java - Created by kerush, 2006 5-dic-06 1:03:42 
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

import rtjdds.rtps.messages.elements.EntityId;
import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;
import rtjdds.util.collections.HashFunction;

/**
 * Hash function used to lookup Topics in TopicDB.
 * @author kerush
 *
 */
public class TopicIdHasher extends HashFunction {
	
	private static final int HASH_CARDINALITY = 256;
	private static final int MAX_COLLISIONS = 1024;

	public TopicIdHasher() {
		super(HASH_CARDINALITY,MAX_COLLISIONS);
	}

	public int hash(Object obj) {
		if (obj instanceof EntityId) {
			EntityId entityId = (EntityId)obj;
			return entityId.byte2();
		}
		else {
			GlobalProperties.logger.log(Logger.WARN,this.getClass(),"hash()","passed object is not an EntityID");
			return -1;
		}
	}

	public int collisionId(Object obj) {
		if (obj instanceof EntityId) {
			EntityId entityId = (EntityId)obj;
//			return (entityId.byte1() << 4) + entityId.byte0();
			return entityId.byte1();
		}
		else {
			GlobalProperties.logger.log(Logger.WARN,this.getClass(),"hash()","passed object is not an EntityID");
			return -1;
		}
	}

}
