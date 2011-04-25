//================================================================
//
//	Writer.java - Created by kerush, 2006 9-nov-06 11:58:27 
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
package rtjdds.rtps.publication;

import java.net.SocketException;

import javax.realtime.LTMemory;
import javax.realtime.MemoryArea;

import rtjdds.rtps.UnorderedHistoryCache;
import rtjdds.rtps.exceptions.RTjDDSException;
import rtjdds.rtps.messages.elements.EntityId;
import rtjdds.rtps.messages.elements.EntityIdFactory;
import rtjdds.rtps.messages.elements.SerializedData;
import rtjdds.rtps.messages.elements.Timestamp;
import rtjdds.rtps.portable.CDROutputPacket;
import rtjdds.rtps.send.MessageSerializer;
import rtjdds.rtps.structure.TopicImpl;
import rtjdds.rtps.structure.local.Endpoint;
import rtjdds.rtps.structure.local.ParticipantImpl;
import rtjdds.rtps.transport.Sender;
import rtjdds.rtps.transport.UDPSender;
import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;
import DDS.DataReaderListener;
import DDS.DataWriter;
import DDS.DataWriterListener;
import DDS.DataWriterQos;
import DDS.InstanceHandle;
import DDS.LivelinessLostStatus;
import DDS.OfferedDeadlineMissedStatus;
import DDS.OfferedIncompatibleQosStatus;
import DDS.PublicationMatchStatus;
import DDS.Publisher;
import DDS.ReliabilityQosPolicyKind;
import DDS.StatusCondition;
import DDS.SubscriptionBuiltinTopicData;
import DDS.Topic;

public abstract class Writer extends Endpoint implements DataWriter {
	
	/* the publisher */
	protected PublisherImpl _publisher = null;
	
	/* the topic */
	protected TopicImpl _topic = null;
	
	/* the write scope */
	protected MemoryArea _mem = new LTMemory(GlobalProperties.SCRATCH_SCOPE_SIZE,GlobalProperties.SCRATCH_SCOPE_SIZE);
	
	/* writer cache */
	protected UnorderedHistoryCache _writerCache = null;

	/* listener */
	protected DataWriterListener _listener = null;
	
	/* socket to send data */
	protected Sender _sender = null;
	
	/* packet to send */
	protected CDROutputPacket _packet = null;
	
	/* message serializer */
	protected MessageSerializer _serializer = null;
	
	/* the sequence number (starts @ 0) */
	protected int _sn = 0;
	
	/* qos */
	protected DataWriterQos _qos = null;
	
	/* FACTORY METHOD */
	protected static Writer createWriter(PublisherImpl publisher, TopicImpl topic, DataWriterQos qos, DataWriterListener listener) throws RTjDDSException {
		Writer writer = null;
		
		/* switch on qos */
		// create the writerId based on the topicId, using the first byte with instanceCount
		// TODO last parameter wKey/noKey ???
		EntityId writerId = EntityIdFactory.createWriterId(_instanceCount,topic.getTopicId(),false);
		// topicKind ???
		//if (qos.reliability.kind.value() == ReliabilityQosPolicyKind._BEST_EFFORT_RELIABILITY_QOS)
		writer = new StatelessWriter(publisher, topic, writerId, 
				qos.reliability.kind.value(), qos.reliability.kind.value(), 
				topic.getMaxDataLength(), qos.history.depth, listener);
		
		return writer;
	}
	
	/* CONSTRUCTOR */
	protected Writer(PublisherImpl publisher, TopicImpl topic, EntityId entityId, int topicKind, 
			int reliabilityKind, int max_data_length, int history_depth, DataWriterListener listener) throws RTjDDSException{
		
		super((ParticipantImpl)publisher.get_participant(), entityId, topicKind, reliabilityKind);
		
		/* the publisher this writer belongs to */
		_publisher = publisher;
		
		/* the topic this writer belongs to */
		_topic = topic;
		
		/* the cache of ingoing messages */
		_writerCache = new UnorderedHistoryCache(max_data_length, history_depth);
		
		/* the packet used to serialize outgoing messages (it is reused at each send) */
		_packet = new CDROutputPacket(new byte[GlobalProperties.BUFFER_SIZE],GlobalProperties.BUFFER_SIZE,false);
		
		/* the serializer */
		_serializer = new MessageSerializer(this,_packet, GlobalProperties.SCRATCH_SCOPE_SIZE);
		
		/* the send socket */
		try {
			_sender = new UDPSender(_packet);
		} catch (SocketException e) {
			throw new RTjDDSException(e);
		}
		
		/* the user listener */
		_listener = listener;
		
		GlobalProperties.logger.log(Logger.INFO,"Created Writer GUID="+_guid);
	
	}
	
	public int getNextSequenceNumber() {
		_sn++;
		return _sn;
	}
	
	/////////////////////////////////////////////////
	//	abstract methods
	/////////////////////////////////////////////////
	
    public abstract void writeUntyped(SerializedData data, InstanceHandle handle);
    
    public abstract void writeUntyped(SerializedData s_data, InstanceHandle handle, int prio);
    
    public abstract void writeUntypedWithTimestamp(SerializedData data, InstanceHandle handle, Timestamp ts);
	
	// ----------------------------------------------
	// DDS herited methods
	// ----------------------------------------------


	public int set_qos(DataWriterQos qos) {
		_qos = qos;
		return 0;
	}

	public void get_qos(DataWriterQos qos) {
		
	}

	public int set_listener(DataWriterListener a_listener, int mask) {
		_listener = a_listener;
		return 0;
	}

	public DataWriterListener get_listener() {
		return _listener;
	}

	public Topic get_topic() {
		return _participant.getDatabase().lookupTopic(_guid.getEntityId());
	}

	public Publisher get_publisher() {
		return _publisher;
	}

	public LivelinessLostStatus get_liveliness_lost_status() {
		// TODO Auto-generated method stub
		return null;
	}

	public OfferedDeadlineMissedStatus get_offered_deadline_missed_status() {
		// TODO Auto-generated method stub
		return null;
	}

	public OfferedIncompatibleQosStatus get_offered_incompatible_qos_status() {
		// TODO Auto-generated method stub
		return null;
	}

	public PublicationMatchStatus get_publication_match_status() {
		// TODO Auto-generated method stub
		return null;
	}

	public void assert_liveliness() {
		// TODO Auto-generated method stub

	}

	public int get_matched_subscriptions(InstanceHandle[] subscription_handles) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int get_matched_subscription_data(
			SubscriptionBuiltinTopicData subscription_data,
			int subscription_handle) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int enable() {
		// TODO Auto-generated method stub
		return 0;
	}

	public StatusCondition get_statuscondition() {
		// TODO Auto-generated method stub
		return null;
	}

	public int get_status_changes() {
		// TODO Auto-generated method stub
		return 0;
	}

}
