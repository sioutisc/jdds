//================================================================
//
//	MessageDecoder.java - Created by kerush, 2006 7-nov-06 7:32:16 
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
package rtjdds.rtps.receive;

import rtjdds.rtps.portable.InputPacket;

public abstract class MessageProcessor {
	
	protected SubmessageDispatcher _dispatcher = null;
	
	/**
	 * Constructs a <code>MessageProcessor</code> object that
	 * will be charged to decode and process incoming packets
	 * by the method <code>process()</code>.
	 * 
	 * @param disp The dispatcher used to handle all the types
	 * of <code>Submessage</code> that can be contained into
	 * the <code>Message</code> itself.
	 *  
	 */
	public MessageProcessor(SubmessageDispatcher disp) {
		this._dispatcher = disp;
	}

	/**
	 * Decode the input packet and dispatch its submessages
	 * through the <code>SubmessageDispatcher</code> passed
	 * to the constructor.<br/>
	 * In case of an invalid message, the decoding process
	 * will immediately stop and the process() method returns.
	 * No notification will be available to the calling entity.
	 * 
	 * @param packet
	 */
	public abstract void process(InputPacket packet);
	
}
