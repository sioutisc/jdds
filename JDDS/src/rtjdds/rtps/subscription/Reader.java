//================================================================
//
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
package rtjdds.rtps.subscription;

import DDS.DataReader;
import DDS.DataReaderListener;
import DDS.DataReaderQos;
import DDS.Duration_t;
import DDS.InstanceHandle;
import DDS.Listener;
import DDS.LivelinessChangedStatus;
import DDS.PublicationBuiltinTopicData;
import DDS.QueryCondition;
import DDS.ReadCondition;
import DDS.RequestedDeadlineMissedStatus;
import DDS.RequestedIncompatibleQosStatus;
import DDS.SampleInfo;
import DDS.SampleLostStatus;
import DDS.SampleRejectedStatus;
import DDS.StatusCondition;
import DDS.Subscriber;
import DDS.SubscriptionMatchStatus;
import DDS.TopicDescription;
import rtjdds.rtps.CacheChange;
import rtjdds.rtps.UnorderedHistoryCache;
import rtjdds.rtps.exceptions.CollectionsException;
import rtjdds.rtps.exceptions.RTjDDSException;
import rtjdds.rtps.messages.Data;
import rtjdds.rtps.messages.NoKeyData;
import rtjdds.rtps.messages.elements.EntityId;
import rtjdds.rtps.messages.elements.SerializedData;
import rtjdds.rtps.structure.TopicImpl;
import rtjdds.rtps.structure.local.Endpoint;
import rtjdds.rtps.structure.local.ParticipantImpl;
import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;
import rtjdds.util.collections.Copyable;
import rtjdds.util.collections.RTLinkedList;
import rtjdds.util.collections.RTList;
import rtjdds.util.collections.RTListIterator;

public abstract class Reader extends Endpoint implements DataReader{
	
	protected SubscriberImpl _subscriber = null;
	
	protected TopicImpl _topic = null;
	
	protected UnorderedHistoryCache _readerCache = null;
	
	protected DataReaderListener _listener = null;
	
	protected DataReaderQos _qos = null;
	
	// TODO add QoS passing parameter... use it to have reliabilityKind and history depth
	public Reader(SubscriberImpl subscriber, TopicImpl topic, EntityId entityId, int topicKind, 
			int reliabilityKind, int max_data_length, int history_depth) throws RTjDDSException {

		super((ParticipantImpl)subscriber.get_participant(), entityId, topicKind, reliabilityKind);

		_subscriber = subscriber;
		
		_topic = topic;
		
		_readerCache = new UnorderedHistoryCache(_topic.getMaxDataLength(), history_depth);
		
		GlobalProperties.logger.log(Logger.INFO,"Created Reader GUID="+_guid);
		
	}
	

	/////////////////////////////////////////////////
	//	Abstract Methods - called by R T j D D S
	/////////////////////////////////////////////////

	/**
	 * Used by RTPS to receive a Data message from the network
	 * @param data
	 */
	public abstract void accept(Data data);
	public abstract void accept(NoKeyData data);
	

	/////////////////////////////////////////////////
	//	General Methods - called by the user through
	//	read and write    the typed wrapper.
	/////////////////////////////////////////////////
	
	/**
	 * Reads a sample from the history cache. The sample
	 * will be deep-copied to the passed object, that
	 * should be already initialized!!!
	 * 
	 */
	public void readNextSampleUntyped(SerializedData sample, SampleInfo info) {
//		CacheChange change = new CacheChange(sample);
		CacheChange change = _readerCache.readNextChange(null);
		if (change != null)
			change.getData().copyTo(sample);
		else
			GlobalProperties.logger.log(Logger.INFO,getClass(),"readNextSampleUntyped()",
					"The history cache has already been read");
		// TODO add a method into CacheChange that can populate the SampleInfo structure
		//		and call it here!!! ;-)
	}
	
	public void takeNextSampleUntyped(SerializedData sample, SampleInfo info) {
//		CacheChange change = new CacheChange(sample);
		CacheChange change = _readerCache.takeNextChange(null);
		if (change != null) {
			change.getData().copyTo(sample);
			//_readerCache.remove_change(change);
		}
		else {
			GlobalProperties.logger.log(Logger.INFO,getClass(),"takeNextSampleUntyped()",
					"The history cache has already been read");
		}
//		 TODO add a method into CacheChange that can populate the SampleInfo structure
//       and call it here!!! ;-)
	}
	
	
	/////////////////////////////////////////////////
	//	Methods from DDS.DataReader
	/////////////////////////////////////////////////

//	IMPORTANTI
	
	public int set_qos(DataReaderQos qos) {
		_qos = qos;
		return 0;
	}

	public void get_qos(DataReaderQos qos) {
		
	}

	public int set_listener(DataReaderListener a_listener, int mask) {
		_listener = a_listener;
		return 0;
	}

	public DataReaderListener get_listener() {
		return _listener;
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

	public ReadCondition create_readcondition(int sample_states, int view_states, int instance_states) {
		// TODO Auto-generated method stub
		return null;
	}

	public QueryCondition create_querycondition(int sample_states, int view_states, int instance_states, String query_expression, String[] query_parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	public int delete_readcondition(ReadCondition a_condition) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete_contained_entities() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public TopicDescription get_topicdescription() {
		return _participant.getDatabase().lookupTopic(_guid.getEntityId());
	}

	public Subscriber get_subscriber() {
		// TODO Auto-generated method stub
		return null;
	}

	public SampleRejectedStatus get_sample_rejected_status() {
		// TODO Auto-generated method stub
		return null;
	}

	public LivelinessChangedStatus get_liveliness_changed_status() {
		// TODO Auto-generated method stub
		return null;
	}

	public RequestedDeadlineMissedStatus get_requested_deadline_missed_status() {
		// TODO Auto-generated method stub
		return null;
	}

	public RequestedIncompatibleQosStatus get_requested_incompatible_qos_status() {
		// TODO Auto-generated method stub
		return null;
	}

	public SubscriptionMatchStatus get_subscription_match_status() {
		// TODO Auto-generated method stub
		return null;
	}

	public SampleLostStatus get_sample_lost_status() {
		// TODO Auto-generated method stub
		return null;
	}

	public int wait_for_historical_data(Duration_t max_wait) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int get_matched_publications(InstanceHandle[] publication_handles) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int get_matched_publication_data(PublicationBuiltinTopicData publication_data, int publication_handle) {
		// TODO Auto-generated method stub
		return 0;
	}


	
	
}
