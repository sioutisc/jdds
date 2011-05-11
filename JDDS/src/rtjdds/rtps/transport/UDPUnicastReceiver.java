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

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
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
public class UDPUnicastReceiver implements Receiver {
	
	/* UDP listening socket */
	protected DatagramSocket socket = null;
	
	/* rcv buffer */
	protected byte[] buffer = null;
	
	/* rcv packet */
	protected DatagramPacket packet = null;
	
	/* rcv CDR packet */
	protected CDRInputBuffer CDRpacket = null;

	/* flag for soft shutdown */
	protected boolean isShuttingDown = false;
	
	/**
	 * Constructs an <code>UDPUnicastReceiver</code>
	 * @param addr
	 * @param bufferSize
	 * @throws SocketException
	 */
	public UDPUnicastReceiver(InetSocketAddress addr, int bufferSize) throws SocketException {
		this.socket = new DatagramSocket(addr);
		this.socket.setReuseAddress(true);
		this.socket.setReceiveBufferSize(bufferSize);
		this.buffer = new byte[bufferSize];
		this.packet = new DatagramPacket(this.buffer,this.buffer.length);
		this.CDRpacket = new CDRInputBuffer(buffer);
	}
	
	public UDPUnicastReceiver(InetSocketAddress addr) throws SocketException {
		this(addr, GlobalProperties.BUFFER_SIZE);
	}
	
	public void shutdown() {
		isShuttingDown = true;
	    socket.close();
	    GlobalProperties.logger.log(Logger.INFO,getClass(),"internalShutdown()","Socket closed");
	}

	public InputPacket receive() throws ShuttingDownException {
		try {
			this.socket.receive(packet);
			// reset the cursor position in the buffer
			CDRpacket.setCursorPosition(0);
			// if here no exceptions occurred...
			if (!isShuttingDown) {
	//			GlobalProperties.logger.log(Logger.INFO, this.getClass(), "accept() @ "+ 
	//					this.socket.getLocalSocketAddress(),"Received a new packet from "+packet.getAddress().getHostAddress());
				return CDRpacket;
			}
		}
		catch (Exception e) {
			GlobalProperties.logger.log(Logger.WARN,this.getClass(),"accept()",e);
			e.printStackTrace();
			if (isShuttingDown) {
				throw new ShuttingDownException();
			}
		}
		return null;
	}

	@Override
	public Locator receive(ByteBuffer buffer) throws ShuttingDownException {
		// TODO Auto-generated method stub
		//FIXME
		return null;
	}
	
	
}
