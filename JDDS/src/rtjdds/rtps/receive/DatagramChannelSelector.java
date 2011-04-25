//================================================================
//
//	ChannelSelector.java - Created by kerush, 2007 12/feb/07 17:14:24
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

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

import rtjdds.util.concurrent.lf.Event;

/**
 * Selector for incoming packets on multiple ports of a given IP address.
 * 
 * @author kerush
 *
 */
public class DatagramChannelSelector extends ChannelSelector {

	private Selector _selector = null;
	private SelectionKey[] _keys = null;
	
	/**
	 * Creates the selector over all the given ports. <br/>
	 * Watchout, this selector waits only for OP_READ events.
	 * 
	 * @param bindAddr
	 * 						The InetAddress where to bind the sockets associated
	 * 						to the selector. It is a local IP address.
	 * @param ports
	 * 						An array of ports where listen to on the same
	 * 						given address.
	 * @throws IOException
	 * 						In case of errors during the construction of the
	 * 						selector or sockets.
	 */
	public DatagramChannelSelector(InetAddress bindAddr, int[] ports) throws IOException {
		/* create the selector */
		_selector = Selector.open();
		_keys = new SelectionKey[ports.length];
		/* bind channels */
		DatagramChannel channel = null;
		InetSocketAddress socket = null;
		for (int i=0; i<ports.length; i++) {
			channel = DatagramChannel.open();
			socket = new InetSocketAddress(bindAddr, ports[i]);
//			NIO 
//			channel.socket().setBroadcast(true);
			channel.socket().bind(socket);
			channel.configureBlocking(false);
			_keys[i] = channel.register(_selector, SelectionKey.OP_READ);
		}
	}
	
	/**
	 * Wait for an incoming packet on the selector.
	 * Returns the WaitingPacketEvent, that wraps the
	 * selected DatagramChannel. 
	 */
	public synchronized Event getNextEvent() {
		int _readyKeys = 0;
		Iterator _keysIterator = null;
		SelectionKey _currentKey = null;
		while (true) {
			/* 1. wait for events */
			try {
				_readyKeys = _selector.select();
			} catch (IOException e) {
				e.printStackTrace();
			}
			/* 2. no events happened if select() returns 0 */
			if (_readyKeys > 0 ) {
				/* 3. iterate on selected keys */
				_keysIterator = _selector.selectedKeys().iterator();
				while (_keysIterator.hasNext()) {
					/* 4. check if this key is a registered one (additional overhead) */
					_currentKey = (SelectionKey) _keysIterator.next();
					for (int i=0; i<_keys.length; i++) {
						if (_currentKey.equals(_keys[i])) {
							if (_currentKey.isReadable()) {
								/* 5. this selected key is OK */
								/* 5.1 remove it from the selected ring */
								_keysIterator.remove();
								/* 5.2 return the event */
								return new WaitingPacketEvent((DatagramChannel)_currentKey.channel(), i);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Unblocks the waiting operation
	 */
	public void unblock() {
		try {
			_selector.selectNow();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
