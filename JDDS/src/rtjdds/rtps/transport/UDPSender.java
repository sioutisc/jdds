//================================================================
//
//	UDPAcceptor.java - Created by kerush, 2006 6-nov-06 5:53:20 
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
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;

import rtjdds.rtps.portable.CDROutputPacket;
import rtjdds.rtps.portable.OutputPacket;
import rtjdds.rtps.transport.locators.Locator;
import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;

/**
 * Sends OutputPackets from the network using UDP protocol.<br/>
 * Internally contains the receiving buffer, which is reused for
 * each receiving operation. Use it carefully when using ScopedMemory. 
 * 
 * @author kerush
 *
 */
public class UDPSender extends Sender {
	
	/* UDP sending socket */
	protected DatagramSocket _socket = null;
	
	/* send packet */
	protected DatagramPacket _sendPacket = null;
	
	/* the CDR packet */
	protected CDROutputPacket _packet = null;

	/* flag for soft shutdown */
	protected boolean _isShuttingDown = false;
	
	/**
	 * Default constructor, uses an OS random port to send data.
	 *  
	 * @throws SocketException
	 */
	public UDPSender(CDROutputPacket packet) throws SocketException {
		_socket = new DatagramSocket();
		_packet = packet;
		_sendPacket = new DatagramPacket(_packet.getBuffer(),packet.getBuffer().length);
	}
	
	/**
	 * Uses the socket (address and port) specified by the used
	 * to send data.
	 * 
	 * @param bindAddr The socket where this <code>Sender</code> will bind.
	 * @throws SocketException
	 */
//	public UDPSender(SocketAddress bindAddr) throws SocketException {
//		this._socket = new DatagramSocket(bindAddr);
//	}
	
	public void shutdown() {
		_isShuttingDown = true;
	    _socket.close();
	}

	public void send(Locator locator) {
		InetSocketLocator addr = (InetSocketLocator)locator;
		try {
			_sendPacket.setSocketAddress(addr);
			_sendPacket.setLength(_packet.getCursorPosition());
			_socket.send(_sendPacket);
//			GlobalProperties.logger.log(Logger.INFO, this.getClass(), "send()", 
//					+_sendPacket.getLength()+" bytes packet correctly sended to "+addr.toString());
		} 
		catch (java.net.SocketException se){
			if( !_isShuttingDown )
				GlobalProperties.logger.log(Logger.WARN, getClass(), "send()", se);
		}
		catch (IOException e) {
			GlobalProperties.logger.log(Logger.WARN,this.getClass(),"send()",e);
			e.printStackTrace();
		}
	}

}
