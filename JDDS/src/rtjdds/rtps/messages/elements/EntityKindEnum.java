//================================================================
//
//	EntityKind.java - Created by kerush, 2006 22-nov-06 3:05:48 
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

public final class EntityKindEnum {
	
	/*
	 * RTPS defines the following 11 kinds according to this convention:
	 * <ol>
	 * 		<li>The less significative half-byte is used to 
	 * 			distinguish between the 'type' of the entity</li>
	 * 		<li>The most significative half-byte is used to 
	 * 			distinguish between the 'nature' of the entity</li>
	 * <ol>
	 * Where type can be one of the following: {UNKNOWN, PARTICIPANT, WRITER, READER}
	 * and 'nature' one of the following: {BUILTIN, USER_DEFINED}.<br/>
	 * To distinguish between builtin and user_defined entities, the 2 most 
	 * significative bits (0xc0) are used.<br/>
	 * 
	 * According to this convention, the following pre-defined kinds are used:
	 */
	
	public static final byte BUILTIN_UNKNOWN		= (byte) 0x00;
	public static final byte USER_DEF_UNKNOWN 	 	= (byte) 0xC0;
	
	public static final byte BUILTIN_PARTICIPANT 	= (byte) 0xC1;
	
	public static final byte USER_DEF_WRITER_W_KEY	= (byte) 0x02;
	public static final byte BUILTIN_WRITER_W_KEY	= (byte) 0xC2;
	
	public static final byte USER_DEF_WRITER_NO_KEY	= (byte) 0x03;
	public static final byte BUILTIN_WRITER_NO_KEY	= (byte) 0xC3;
	
	public static final byte USER_DEF_READER_W_KEY	= (byte) 0x04;
	public static final byte BUILTIN_READER_W_KEY	= (byte) 0xC4;
	
	public static final byte USER_DEF_READER_NO_KEY	= (byte) 0x07;
	public static final byte BUILTIN_READER_NO_KEY	= (byte) 0xC7;
	
	/*
	 * These are implementation-specific kinds to include every DDS type
	 * in the specification of the kind.<br/>
	 * More we introduce another distinction between local entities and
	 * remote entities through the 0x30 bits... Remote entities are
	 * classified locally as 'proxy'
	 */
	
	/* SUBSCRIBER (LOCAL) */
	public static final byte SUBSCRIBER 					= (byte) 0x08;
	
	/* PUBLISHER (LOCAL) */
	public static final byte PUBLISHER						= (byte) 0x09;
	
	/* TOPIC */
	public static final byte USER_DEF_TOPIC					= (byte) 0x05;
	public static final byte BUILTIN_TOPIC					= (byte) 0xc5;
	
	/* INSTANCES ??? */
	public static final byte INSTANCE_HANDLE				= (byte) 0x06;
	
	/* REMOTE ENTITIES */
	public static final byte REMOTE_BUILTIN_UNKNOWN			= (byte) 0x30;
	public static final byte REMOTE_USER_DEF_UNKNOWN 	 	= (byte) 0xF0;
	
	public static final byte REMOTE_BUILTIN_PARTICIPANT 	= (byte) 0xF1;
	
	public static final byte REMOTE_USER_DEF_WRITER_W_KEY	= (byte) 0x32;
	public static final byte REMOTE_BUILTIN_WRITER_W_KEY	= (byte) 0xF2;
	
	public static final byte REMOTE_USER_DEF_WRITER_NO_KEY	= (byte) 0x33;
	public static final byte REMOTE_BUILTIN_WRITER_NO_KEY	= (byte) 0xF3;
	
	public static final byte REMOTE_USER_DEF_READER_W_KEY	= (byte) 0x34;
	public static final byte REMOTE_BUILTIN_READER_W_KEY	= (byte) 0xF4;
	
	public static final byte REMOTE_USER_DEF_READER_NO_KEY	= (byte) 0x37;
	public static final byte REMOTE_BUILTIN_READER_NO_KEY	= (byte) 0xF7;

	
}
