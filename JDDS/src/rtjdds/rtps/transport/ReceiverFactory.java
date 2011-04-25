//================================================================
//
//	ReceiverFactory.java - Created by kerush, 2006 6-dic-06 11:08:09 
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
import java.net.UnknownHostException;

import rtjdds.util.GlobalProperties;


public class ReceiverFactory {
	
	//////////////////////////////////////////////////
	//	BUILTIN traffic endpoints
	//////////////////////////////////////////////////
	
	/**
	 * 
	 * @param domainId
	 * @param participantId
	 * @param priority
	 * @return
	 * @throws IOException 
	 */
	public static UDPMulticastReceiver createBuiltinMulticastReceiver(int domainId) throws IOException {
		int port = GlobalProperties.PB + GlobalProperties.DG * domainId + GlobalProperties.d0;
		String ip = GlobalProperties.DEFAULT_MULTICAST_ADDRESS;
		int bufferSize = GlobalProperties.BUFFER_SIZE;
		InetAddress ipaddr = InetAddress.getByName(ip);
		InetSocketAddress sock = new InetSocketAddress(ipaddr,port);
		UDPMulticastReceiver rcvr = new UDPMulticastReceiver(sock,bufferSize);
		return rcvr;
	}
	
	/**
	 * 
	 * @param domainId
	 * @param participantId
	 * @return
	 * @throws IOException
	 */
	public static UDPUnicastReceiver createBuiltinUnicastReceiver(String ip, int domainId, int participantId) throws IOException {
		int port = GlobalProperties.PB + GlobalProperties.DG * domainId + GlobalProperties.d1 + GlobalProperties.PG * participantId;
		int bufferSize = GlobalProperties.BUFFER_SIZE;
		InetAddress ipaddr = InetAddress.getByName(ip);
		InetSocketAddress sock = new InetSocketAddress(ipaddr,port);
		UDPUnicastReceiver rcvr = new UDPUnicastReceiver(sock,bufferSize);
		return rcvr;
	}
	
	//////////////////////////////////////////////////
	//	USER traffic endpoints
	//////////////////////////////////////////////////
	
	/**
	 * 
	 * @param domainId
	 * @param participantId
	 * @param priority
	 * @return
	 * @throws IOException 
	 */
	public static UDPMulticastReceiver createUserMulticastReceiver(int domainId, int priority) throws IOException {
		int port = GlobalProperties.PB + GlobalProperties.DG * domainId + GlobalProperties.d2 + priority;
		String ip = GlobalProperties.DEFAULT_MULTICAST_ADDRESS;
		int bufferSize = GlobalProperties.BUFFER_SIZE;
		InetAddress ipaddr = InetAddress.getByName(ip);
		InetSocketAddress sock = new InetSocketAddress(ipaddr,port);
		UDPMulticastReceiver rcvr = new UDPMulticastReceiver(sock,bufferSize);
		return rcvr;
	}
	
	/**
	 * 
	 * @param domainId
	 * @param participantId
	 * @return
	 * @throws IOException
	 */
	public static UDPUnicastReceiver createUserUnicastReceiver(String ip, int domainId, int participantId, int priority) throws IOException {
		int port = GlobalProperties.PB + GlobalProperties.DG * domainId + GlobalProperties.d1 + GlobalProperties.PG * participantId + priority;
		int bufferSize = GlobalProperties.BUFFER_SIZE;
		InetAddress ipaddr = InetAddress.getByName(ip);
		InetSocketAddress sock = new InetSocketAddress(ipaddr,port);
		UDPUnicastReceiver rcvr = new UDPUnicastReceiver(sock,bufferSize);
		return rcvr;
	}
	
}
