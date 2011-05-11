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
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.nio.ByteBuffer;

import rtjdds.rtps.exceptions.ShuttingDownException;
import rtjdds.rtps.portable.CDRInputBuffer;
import rtjdds.rtps.portable.InputPacket;
import rtjdds.rtps.transport.locators.Locator;
import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;

/**
 * Receives InputPackets from the network using UDP protocol.<br/>
 * Internally contains the receiving buffer, which is reused for
 * each receiving operation. Use it carefully when using ScopedMemory. 
 * 
 * @author kerush
 *
 */
public class UDPMulticastReceiver implements Receiver {
	
	/* UDP listening socket */
	protected MulticastSocket _socket = null;
	
	/* SocketAddress to which the socket is bound */
	private InetSocketAddress _addr = null;
	
	/* rcv buffer */
	protected byte[] _buffer = null;
	
	/* rcv packet */
	protected DatagramPacket _packet = null;
	
	/* rcv CDR packet */
	protected CDRInputBuffer _CDRpacket = null;

	/* flag for soft shutdown */
	protected boolean _isShuttingDown = false;
	
	public UDPMulticastReceiver(InetSocketAddress addr, int bufferSize) throws IOException {
		_addr = addr;
		/* WATCHOUT - GNU classpath wants the binding address
		 *            in the constructor, SUN wants only the
		 *            port (can't bind multicast addresses)
		 */
		_socket = new MulticastSocket(_addr.getPort());
		/* here we set the o.s. internal buffer size */
//		_socket.setReceiveBufferSize(2*bufferSize);
		_socket.joinGroup(addr.getAddress());
		_buffer = new byte[bufferSize];
		_packet = new DatagramPacket(_buffer,_buffer.length);
		_CDRpacket = new CDRInputBuffer(_buffer);
	}
	
	public UDPMulticastReceiver(InetSocketAddress addr) throws IOException {
		this(addr, GlobalProperties.BUFFER_SIZE);
	}
	
	public void shutdown() {
		_isShuttingDown = true;
		try {
			_socket.leaveGroup(_socket.getLocalAddress());
		} catch (IOException e) {
			GlobalProperties.logger.log(Logger.WARN,this.getClass(),"internalShutdown()",
					"Error while leaving the group, socket will be closed anyway");
		}
	    _socket.close();
	}

	public InputPacket receive() {
		try {
			_socket.receive(_packet);
			// if here no exceptions occurred...
//			if (!_isShuttingDown) 
//			GlobalProperties.logger.log(Logger.INFO, this.getClass(), "accept() @ "+ 
//					this._socket.getLocalSocketAddress(),"Received a new packet from "+_packet.getAddress().getHostAddress());
		} 
		catch (java.net.SocketException se){
			if( !_isShuttingDown )
				GlobalProperties.logger.log(Logger.WARN, getClass(), "accept()", se);
		}
		catch (IOException e) {
			GlobalProperties.logger.log(Logger.WARN,this.getClass(),"accept()",e);
//			e.printStackTrace();
		}
		catch (Exception e) {
			GlobalProperties.logger.log(Logger.WARN,this.getClass(),"accept()","received unhandled exception: "+e.getMessage());
//			e.printStackTrace();
		}
		finally {
			// reset the cursor position in the buffer
			_CDRpacket.setCursorPosition(0);
		}
		return _CDRpacket;
	}

	@Override
	public Locator receive(ByteBuffer buffer) throws ShuttingDownException {
		//FIXME
		return null;
	}
	
	
}
