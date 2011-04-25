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

import javax.realtime.LTMemory;
import javax.realtime.MemoryArea;
import javax.realtime.NoHeapRealtimeThread;
import javax.realtime.PriorityParameters;

import rtjdds.rtps.database.Database;
import rtjdds.rtps.portable.InputPacket;
import rtjdds.rtps.transport.Receiver;
import rtjdds.util.Executor;
import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;

/**
 * Creates a Thread that accepts messages on the given <code>Receiver</code>
 * and process it using an internally created <code>MessageProcessor</code> not shared
 * with other threads. This thread share with other threads only the database. 
 * 
 * @author kerush
 *
 */
public class ReceiverThread extends NoHeapRealtimeThread {
	
	protected MemoryArea _demarshallMemArea = null;
	
	protected Receiver _receiver = null;
	
	protected CDRMessageProcessor _processor = null;
	
	protected InputPacket _packet = null;
	
	protected Executor _demarshallingExecutor = null;
	
	protected volatile boolean _isActive = true;
	
	private volatile Integer _lock = new Integer(0); 
	
	public ReceiverThread(Receiver receiver, Database db, int priority) {
		super(new PriorityParameters(priority),null);
		_processor = new CDRMessageProcessor(new SubmessageDispatcherImpl(db));
		_receiver = receiver;
		_demarshallMemArea = new LTMemory(GlobalProperties.SCRATCH_SCOPE_SIZE,GlobalProperties.SCRATCH_SCOPE_SIZE);
		_demarshallingExecutor = new Executor(_demarshallMemArea,new DemarshallingExecutor());
		
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"new()",
				"created a new ReceiverThread with priority "+this.getPriority()+" rooted in "+getCurrentMemoryArea());
		GlobalProperties.logger.printMemStats();
		
		/* registering a shutdown hook for this thread */
		Runtime.getRuntime().addShutdownHook(new Thread() {
		    public void run() {
//		    	shutdown();
		    	GlobalProperties.logger.log(Logger.INFO,getClass(),"shutdownHook.run()","ReceiverThread Shutted Down");
		    }
		});
		
	}
	
	/**
	 * Shuts down this accepting thread.
	 *  
	 * @param waitForCompletion if is needed to wait until the
	 * reception of the last packet.
	 */
	public synchronized final void shutdown() {
		_isActive = false;
		_receiver.shutdown();
		System.out.println("shutdown() done.");
		// TODO remove
		this.interrupt();
//		try {
//			wait();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
	
	public void run() {
		try{
			
			GlobalProperties.logger.log(Logger.INFO,this.getClass(),"run()",
					"starting ReceiverThread with priority "+this.getPriority());
			GlobalProperties.logger.printMemStats();
			
			while (_isActive) {
				
				/* reception in the Reader's scope (buffer is reused) */
				_packet = _receiver.receive();

				if (_packet != null) {
					// TODO remove
//					test.latency.ReceiveLatency.timer.start();
//					GlobalProperties.timer.start();
					
					/* accept and process (in a scratch scope) */
					_demarshallingExecutor.execute();
				}
			}
			
		}
		catch (Exception e) {
			if (_isActive) {
				GlobalProperties.logger.log(Logger.INFO,this.getClass(),"run()","received exception: "+e.getMessage());
				e.printStackTrace();
			}
		}
		System.out.println("run() receiver done.");
	}
	
	class DemarshallingExecutor implements Runnable {
		public void run() {
//			GlobalProperties.logger.log(Logger.INFO,this.getClass(),"run()",
//				"entering the accepting loop with priority "+getPriority());
//			GlobalProperties.logger.printMemStats();

			/* accept and process (in a scratch scope) */
			_processor.process(_packet);
			
//			GlobalProperties.logger.log(Logger.INFO,this.getClass(),"run()",
//				"exiting the accepting loop with priority "+getPriority());
//				GlobalProperties.logger.printMemStats();
		}
	}

}
