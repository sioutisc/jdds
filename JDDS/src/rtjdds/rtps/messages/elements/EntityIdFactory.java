//================================================================
//
//	WriterId.java - Created by kerush, 2006 8-dic-06 3:37:30 
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

import rtjdds.rtps.types.EntityId_t;

public class EntityIdFactory  {
	
	public static EntityId createWriterId(int writer_count, byte[] topicId, boolean wKey) {
		byte[] id = new byte[]{(byte)writer_count,topicId[0],topicId[1]};
		if (wKey)
			return new EntityId(new EntityId_t(id, EntityKindEnum.USER_DEF_WRITER_W_KEY));
		else
			return new EntityId(new EntityId_t(id, EntityKindEnum.USER_DEF_WRITER_NO_KEY));
	}
	
	public static EntityId createWriterId(int writer_count, EntityId topicId, boolean wKey) {
		byte[] id = new byte[]{topicId._value.entityKey[1],topicId._value.entityKey[2]};
		return createWriterId(writer_count,id,wKey);
	}
	
	public static EntityId createReaderId(int reader_count, byte[] topicId, boolean wKey) {
		byte[] id = new byte[]{(byte)reader_count,topicId[0],topicId[1]};
		if (wKey)
			return new EntityId(new EntityId_t(id, EntityKindEnum.USER_DEF_READER_W_KEY));
		else
			return new EntityId(new EntityId_t(id, EntityKindEnum.USER_DEF_READER_NO_KEY));
	}
	
	public static EntityId createReaderId(int reader_count, EntityId topicId, boolean wKey) {
		byte[] id = new byte[]{topicId._value.entityKey[1],topicId._value.entityKey[2]};
		return createWriterId(reader_count,id,wKey);
	}

}
