/* ********************************************************************* *
 *                                                                       *
 *   =============================================================       *
 *   Copyright 2011                                                      *
 *   Christos Sioutis <christos.sioutis@gmail.com>                       *
 *   =============================================================       *
 *                                                                       *
 *   This file is part of jdds.                                          *
 *                                                                       *
 *   jdds is free software: you can redistribute it and/or               *
 *   modify it under the terms of the GNU General Public License         *
 *   as published by the Free Software Foundation, either version 3 of   *
 *   the License, or (at your option) any later version.                 *
 *                                                                       *
 *   jdds is distributed in the hope that it will be useful,             *
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of      *
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the       *
 *   GNU General Public License for more details.                        *
 *                                                                       *
 *   You should have received a copy of the GNU General Public           *
 *   License along with jdds.                                            *
 *   If not, see <http://www.gnu.org/licenses/>.                         *
 *                                                                       *
 * ********************************************************************* */

package jdds.rtps.transport.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class RTPS_Socket {
	InetAddress group;
	String ip = "239.255.0.1";
	int port = 7400;
	 MulticastSocket sock;
	 
	 public RTPS_Socket() throws Exception{
		// TODO Auto-generated constructor stub
		group = InetAddress.getByName(ip);
		sock = new MulticastSocket(port);
		 sock.joinGroup(group);
		 
		 // get their responses!
		 //...
		 // OK, I'm done talking - leave the group...
		 //s.leaveGroup(group);	
		 
	}
	 
	 public void send(byte[] bytes) throws IOException{
		 DatagramPacket p = new DatagramPacket(bytes, bytes.length, group, port);
		 sock.send(p);
	 }
	 
	 public byte[] recv() throws IOException {
		 byte[] buff = new byte[1000];
		 DatagramPacket p = new DatagramPacket(buff, buff.length);
		 sock.receive(p);
		 return buff;
	 }

}
