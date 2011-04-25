//================================================================
//
//	Message.java - Created by kerush, 2006 7-ott-06 7:55:21 
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
package rtjdds.rtps.messages;

import java.util.Vector;

import rtjdds.rtps.portable.CDROutputPacket;

public class Message {

	protected RTPSHeader header = null;
	
	protected Vector submessages = null;
	
	Message() {
		submessages = new Vector();
	}
	
	public Message(RTPSHeader h, Submessage[] submsgs) {
		header = h;
		submessages = new Vector();
		for (int i=0; i<submsgs.length; i++) {
			if (submsgs[i] != null) {
				this.submessages.add(submsgs[i]);
			}
		}
	}
	
	public void setHeader(RTPSHeader header) {
		this.header = header;
	}
	
	public void addSubmessage(Submessage submsg) {
		if (submsg != null) {
			submessages.add(submsg);
		}
	}
	
	/**
	 * Writes the entire <code>Message</code> on the given <code>OutputStream</code>,
	 * following the CDR encoding.
	 * 
	 * @param os
	 */
	public void write(CDROutputPacket os) {
		header.write(os);
		for (int i=0; i<submessages.size(); i++) {
			((Submessage)submessages.get(i)).write(os);
		}
	}

	
	/**
	 * Tries to read a <code>Message</code> from the given <code>InputStream</code>.
	 * @param packet the packet containing the wire-version of the message
	 * @return The <code>Message</code>
	 * @throws MalformedMessageException
	 */
//	public static Message read(CDRInputPacket packet) throws MalformedMessageException {
//		Message newMsg = new Message();
//		/* reading the header (can throw exception) */
//		newMsg.header = RTPSHeader.read(packet);
//		/* watchout: reading until the end... */
//		ArrayList readedSubMsgs = new ArrayList();
//		while (packet.getCurrPos() < packet.getLength()) {
//			/* reading submessages (can throw exception)*/
//			//readedSubMsgs.add(Submessage.read(packet));
//		}
//		newMsg.submessages = new Submessage[readedSubMsgs.size()];
//		readedSubMsgs.toArray(newMsg.submessages);
//		return newMsg;
//	}


}
