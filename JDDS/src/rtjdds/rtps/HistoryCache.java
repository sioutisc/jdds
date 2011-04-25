//================================================================
//
//	HistoryCache.java - Created by kerush, 2006 14-nov-06 2:55:03 
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

import rtjdds.rtps.exceptions.OutOfResourceException;
import rtjdds.rtps.messages.elements.SequenceNumber;

public abstract class HistoryCache {
	
	public abstract void add_change (CacheChange c) throws OutOfResourceException;
	
	public abstract void remove_change (CacheChange c);
	
	public abstract CacheChange readNextChange(CacheChange c);
	
	public abstract CacheChange takeNextChange(CacheChange c);
	
	public abstract SequenceNumber get_seq_num_min();
	
	public abstract SequenceNumber get_seq_num_max();

}
