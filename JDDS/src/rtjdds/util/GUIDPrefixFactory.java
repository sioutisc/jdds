//================================================================
//
//	GUIDFactory.java - Created by kerush, 2006 23-nov-06 3:31:37 
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

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.realtime.AbsoluteTime;
import javax.realtime.Clock;
import javax.realtime.HighResolutionTime;

import rtjdds.rtps.messages.elements.GuidPrefix;
import rtjdds.rtps.types.GuidPrefix_t;


/**
 * A Factory for generating GUIDs.
 *
 * @see        rtjdds.rtps.messages.elements.GUID
 */
public final class GUIDPrefixFactory {

    public static GuidPrefix newGuidPrefix(InetAddress addr) {
    	
//    	AbsoluteTime time = Clock.getRealtimeClock().getTime();
    	
    	byte[] ip = null;
    	if (addr != null) {
    		ip = addr.getAddress();
    	}
    	else {
    		ip = BitUtility.intToBytes(0);
    	}
//    	int nanosec = time.getNanoseconds();
//    	int millisec = (int)(time.getMilliseconds());
    	int nanosec = 0;
    	int millisec = (int) System.currentTimeMillis();
    	byte[] ms = BitUtility.intToBytes(millisec);
    	byte[] ns = BitUtility.intToBytes(nanosec);
    
    	byte[] prefix = new byte[12];
    	System.arraycopy(ip,0,prefix,0,4);
    	System.arraycopy(ms,0,prefix,4,4);
    	System.arraycopy(ns,0,prefix,8,4);
    	
    	return new GuidPrefix(new GuidPrefix_t(prefix));
    }
    
    public static GuidPrefix newGuidPrefix() {
    	InetAddress addr;
		try {
			addr = InetAddress.getByName("127.0.0.1");
		} catch (UnknownHostException e) {
			addr = null;
		}
    	return newGuidPrefix(addr);
    }
    
}
