//================================================================
//
//	AbstractAcceptorRunnable.java - Created by kerush, 2006 6-nov-06 11:48:41 
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
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

import javax.realtime.LTMemory;
import javax.realtime.MemoryArea;
import javax.realtime.NoHeapRealtimeThread;
import javax.realtime.PriorityParameters;
import javax.realtime.RealtimeThread;

import rtjdds.rtps.database.Database;
import rtjdds.rtps.portable.CDRInputBuffer;
import rtjdds.rtps.portable.InputPacket;
import rtjdds.rtps.transport.MailBox;
import rtjdds.rtps.transport.Receiver;
import rtjdds.util.Executor;
import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;
import rtjdds.util.concurrent.lf.Event;
import rtjdds.util.concurrent.lf.EventHandler;

/**
 * Creates a Thread that accepts messages on the given <code>Receiver</code>
 * and process it using an internally created <code>MessageProcessor</code> not shared
 * with other threads. This thread share with other threads only the database. 
 * 
 * @author kerush
 *
 */
public class ReceiverLogic extends EventHandler {
	
	protected ChannelSelector _selector = null;
	
	protected ByteBuffer _buffer = null;
	
	protected MessageProcessor _processor = null;
	
	protected InputPacket _packet = null;
	
	public ReceiverLogic(long memSize, ChannelSelector selector, int bufferSize, Database db) {
		super(memSize);
		_selector = selector;
//		_buffer = ByteBuffer.allocate(bufferSize);
		_buffer = ByteBuffer.allocate(72);
		_packet = new CDRInputBuffer(_buffer.array());
		_processor = new CDRMessageProcessor(new SubmessageDispatcherImpl(db));
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"new()",
				"created a new ReceiverLogic rooted in "+RealtimeThread.getCurrentMemoryArea()+
				" operating in a "+memSize+" bytes ScopedMemory. Buffer size is "+bufferSize+" bytes");
		GlobalProperties.logger.printMemStats();
	}
	
	public void handleEvent(Event ev) {
		/*
		DatagramChannel channel = (DatagramChannel)ev;
		try {
			channel.read(_buffer);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		*/
		
		SelectedPipeEvent pipe = (SelectedPipeEvent)ev;
		MailBox mbox = pipe.getMailBox();
		
		_packet.setCursorPosition(0);
		mbox.read(_packet.getBuffer());
		
//		mbox.read(_buffer.array());
//		_packet = new CDRInputPacket(_buffer.array());
		
//		GlobalProperties.timer.start();
		
		
		/* Adjusting priority */
		int handlingPrio = RealtimeThread.MIN_PRIORITY + mbox.getPriority() /*+ _topic.getPriority()*/;
		RealtimeThread.currentRealtimeThread().setPriority(handlingPrio);
//		System.out.println("READING AT PRIO "+RealtimeThread.currentRealtimeThread().getPriority()+ " FROM MBOX "+mbox.getPriority());
		
		_processor.process(_packet);
		
	}

	public Event pollEvent() {
		return _selector.getNextEvent();
	}

	public void unblockPolling() {
		_selector.unblock();
	}

}
