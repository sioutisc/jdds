//================================================================
//
//	MailBox.java - Created by kerush, 2007 06/mar/07 12:06:26
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

import java.nio.ByteBuffer;

public class MailBox {
	
	private ByteBuffer _buffer = null;
	private int _priority = 0;
	
	
	public MailBox(int size, int prio) {
		_buffer = ByteBuffer.allocate(size);
		_priority = prio;
	}
	
	public int getPriority() {
		return _priority;
	}
	
	public synchronized void write(byte[] data) {
//		System.out.println("Trying to write "+data.length+" bytes to mbox " +_priority);
		while (data.length > _buffer.remaining()) {
			try {
				System.out.println("MailBox "+_priority+" full!");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		_buffer.put(data);
//		notifyAll();
//		System.out.println("Writed in mbox "+_priority);
	}
	
	public synchronized byte[] read(byte[] data) {
		_buffer.position(_buffer.position() - data.length);
		_buffer.get(data);
		_buffer.position(_buffer.position() - data.length);
		notifyAll();
//		System.out.println("Readed from mbox "+_priority+" data "+data[0]+" length "+data.length);
		return data;
	}
	
	public synchronized boolean isEmpty() {
		return (_buffer.position() == 0);
	}

}
