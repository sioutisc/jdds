//================================================================
//
//	DatagramChannelReceiver.java - Created by kerush, 2007 05/apr/07 18:11:24
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

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

import rtjdds.rtps.exceptions.ShuttingDownException;
import rtjdds.rtps.messages.Data;
import rtjdds.rtps.transport.locators.InetLocator;
import rtjdds.rtps.transport.locators.Locator;

public class DatagramChannelReceiver implements Receiver {
	
	private DatagramChannel _channel = null;
	
	public DatagramChannelReceiver(InetLocator localEndpoint) throws IOException {
		_channel = DatagramChannel.open();
		_channel.socket().bind(localEndpoint);
	}

	public Locator receive(ByteBuffer buffer) throws ShuttingDownException {
		Locator source = null;
		SocketAddress addr = null;
		try {
		     addr = _channel.receive(buffer);
		     source = new InetLocator((InetSocketAddress)addr);
		} catch (IOException e) {
		    throw new ShuttingDownException();
		}
		return source;
	}

	public void shutdown() {
		// TODO Auto-generated method stub

	}

}
