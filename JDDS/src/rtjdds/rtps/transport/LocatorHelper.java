//================================================================
//
//	LocatorHelper.java - Created by kerush, 2006 26-nov-06 2:47:38 
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
package rtjdds.rtps.transport;

import rtjdds.rtps.messages.elements.LocatorList;
import rtjdds.rtps.messages.elements.LocatorUDPv4;
import rtjdds.rtps.types.Locator_t;

public class LocatorHelper {
	
	// -------------------------------------------------
	// locator to sender conversion
	// -------------------------------------------------
	
	//TODO implement to have locator to sender transformation
	public Sender getSender(Locator_t locator) {
		return null;
	}
	
	public Sender getSender(LocatorUDPv4 locator) {
		return null;
	}
	
	public Sender[] getSender(LocatorList locator) {
		return null;
	}
	
	// -------------------------------------------------
	// locator to receiver conversion
	// -------------------------------------------------
	
	public Receiver getReceiver(Locator_t locator) {
		return null;
	}
	
	public Receiver getReceiver(LocatorUDPv4 locator) {
		return null;
	}
	
	public Receiver[] getReceiver(LocatorList locator) {
		return null;
	}
	
	

}
