//================================================================
//
//	DBProfile.java - Created by kerush, 2006 2-dic-06 3:58:27 
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

public class DBProfile {

	public MemoryArea MEM_AREA = ImmortalMemory.instance();
	
	public int MAX_DISCOVERED_PARTICIPANTS = 256;
	
	public int MAX_TOPICS = 255;
	
	public int MAX_PUBLISHERS = 255;
	
	public int MAX_SUBSCRIBERS = 255;
	
	/* 
	 * forse readers e writers possono essere spostati 
	 * in hashtables nei relativi subscribers e publishers!
	 * Lookup in due fasi:
	 * 1. il primo byte identifica il subscriber (o il publisher)
	 * 2. gli altri due bytes indentificano il reader (o il writer)
	 */ 
	public int MAX_LOCAL_READERS = 255;
	
	public int MAX_LOCAL_WRITERS = 255;
	
	
	/*
	 * magari anche readers e writers remoti possono essere
	 * spostati in hashtables all'interno dei discovered
	 * participants!
	 * Lookup in due fasi: 
	 * 1. prima GuidPrefix per beccare il RemoteParticipant
	 * 2. poi EntityId per beccare il Writer o il Reader
	 * 
	 * Così si evitano i problemi di 'unregistering'
	 */
	public int MAX_REMOTE_READERS = 255;
	
	public int MAX_REMOTE_WRITERS = 255;

}
