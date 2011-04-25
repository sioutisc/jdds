//================================================================
//
//	MessageSerializer.java - Created by kerush, 2006 13-dic-06 1:11:51 
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
package rtjdds.rtps.send;

import javax.realtime.LTMemory;
import javax.realtime.MemoryArea;
import javax.realtime.RealtimeThread;


import rtjdds.rtps.messages.Data;
import rtjdds.rtps.messages.InfoTimestamp;
import rtjdds.rtps.messages.NoKeyData;
import rtjdds.rtps.messages.RTPSHeader;
import rtjdds.rtps.messages.Submessage;
import rtjdds.rtps.messages.elements.EntityId;
import rtjdds.rtps.messages.elements.KeyHashPrefix;
import rtjdds.rtps.messages.elements.KeyHashSuffix;
import rtjdds.rtps.messages.elements.ParameterList;
import rtjdds.rtps.messages.elements.SequenceNumber;
import rtjdds.rtps.messages.elements.SerializedData;
import rtjdds.rtps.messages.elements.StatusInfo;
import rtjdds.rtps.portable.CDROutputPacket;
import rtjdds.rtps.publication.Writer;
import rtjdds.rtps.structure.local.ParticipantImpl;
import rtjdds.rtps.types.DATA;
import rtjdds.rtps.types.INFO_TS;
import rtjdds.rtps.types.NOKEY_DATA;
import rtjdds.rtps.types.SequenceNumber_t;
import rtjdds.util.Executor;
import rtjdds.util.GlobalProperties;

/**
 * Serializes...
 * @author kerush
 *
 */
public class MessageSerializer {
	
	/* the scratch scope */
	private Executor _executor = null;
	
	/* the writer this Serializer belongs to */
	private Writer _writer = null;
	
	/* the packet resised in Writer's scope
	 * it is overwritten at each write() */
	private CDROutputPacket _packet = null;
	
	//////////////////////////////////////////
	//	fixed fields of the message
	//////////////////////////////////////////
	
	/* the header that will be used to send each message */
	private RTPSHeader _header = null;
	
	/* writerId */
	EntityId _writerId = null;
	
	//////////////////////////////////////////
	//	variable fields of the message
	//////////////////////////////////////////
	
	private EntityId _readerId = null;
	private KeyHashPrefix _prefix = null;
	private KeyHashSuffix _suffix = null;
	private StatusInfo _status = null;
	private ParameterList _parms = null;
	private SerializedData _user_data = null;
	
	//////////////////////////////////////////
	//	internal message-type switches
	//////////////////////////////////////////
	private byte _submessageKind = 0;
	
	public MessageSerializer(Writer w, CDROutputPacket packet, long scopeSize) {
		GlobalProperties.logger.log(rtjdds.util.Logger.INFO,getClass(),"new()",
				"Creating a MessageSerializer with "+(scopeSize/1024)+" Kb buffer");
		_executor = new Executor(scopeSize, new InnerScopeSerializer());
		_writer = w;
		_packet = packet;
		_header = ((ParticipantImpl)_writer.get_publisher().get_participant()).getHeader();
		_writerId = _writer.getGuid().getEntityId();
		GlobalProperties.logger.log(rtjdds.util.Logger.INFO,getClass(),"new()",
				"Created a MessageSerializer with "+(scopeSize/1024)+" Kb buffer");
	}
	
	/**
	 * To be called at the beginning of each serialization
	 *
	 */
	public void writeHeader() {
		/* reset the cursor */
		_packet.setCursorPosition(0);
		/* the header */
		_header.write(_packet);
	}
	
	public void writeData(EntityId readerId, KeyHashPrefix prefix, KeyHashSuffix suffix,
			StatusInfo status, ParameterList parms, SerializedData user_data) {
		_readerId = readerId;
		_prefix = prefix;
		_suffix = suffix;
		_status = status;
		_parms = parms;
		_user_data = user_data;
		
		_submessageKind = DATA.value;
		
		writeSubmessage();
		
	}
	
	public void writeNoKeyData(EntityId readerId, ParameterList parms, SerializedData user_data) {
		_readerId = readerId;
		_parms = parms;
		_user_data = user_data;
		
		_submessageKind = NOKEY_DATA.value;
		
		writeSubmessage();

	}
	
	public void writeInfoTimestamp() {
		_submessageKind = INFO_TS.value;
		writeSubmessage();
	}
	
	public CDROutputPacket getPacket() {
		return _packet;
	}
	
	////////////////////////////////////
	//	private methods
	////////////////////////////////////
	
	private SequenceNumber getSN() {
		return new SequenceNumber(new SequenceNumber_t(_writer.getNextSequenceNumber()));
	}

	
	private void writeSubmessage() {
		_executor.execute();
	}
	
	
	class InnerScopeSerializer implements Runnable {
		public void run() {
			

//			GlobalProperties.logger.log(rtjdds.util.Logger.INFO,getClass(),"writeSubmessage()",
//					"Start serializing...");
//			GlobalProperties.logger.printMemStats();
			
			Submessage submsg = null;
	    	
	    	switch (_submessageKind) { 
	    	
	    	case DATA.value:
	    		submsg = new Data(_readerId,_writerId,getSN(), _prefix, _suffix, _status, _parms, _user_data);
	    		break;
	    		
	    	case NOKEY_DATA.value:
	    		submsg = new NoKeyData(_readerId,_writerId,getSN(),_parms,_user_data);
	    		break;
	    		
	    	case INFO_TS.value:
	    		submsg = InfoTimestamp.now();
	    		break;
	    	
	    	default:
	    		return;
	    	
	    	}
	    	
			/* serialization */
	    	submsg.write(_packet);
	    	
//			GlobalProperties.logger.log(rtjdds.util.Logger.INFO,getClass(),"writeSubmessage()",
//					"Ended serializing...");
//			GlobalProperties.logger.printMemStats();
			
	    }
	}
	
}
