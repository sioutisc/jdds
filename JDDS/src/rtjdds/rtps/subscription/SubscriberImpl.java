//================================================================
//
//	Subscriber.java - Created by kerush, 2006 7-dic-06 11:09:52 
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

import rtjdds.rtps.exceptions.RTjDDSException;
import rtjdds.rtps.messages.elements.EntityId;
import rtjdds.rtps.messages.elements.EntityKindEnum;
import rtjdds.rtps.messages.elements.GUID;
import rtjdds.rtps.structure.EntityImpl;
import rtjdds.rtps.structure.TopicImpl;
import rtjdds.rtps.structure.local.ParticipantImpl;
import rtjdds.rtps.types.EntityId_t;
import rtjdds.util.BitUtility;
import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;
import DDS.DataReader;
import DDS.DataReaderListener;
import DDS.DataReaderQos;
import DDS.DomainParticipant;
import DDS.StatusCondition;
import DDS.SubscriberListener;
import DDS.SubscriberQos;
import DDS.TopicDescription;
import DDS.TopicQos;

public class SubscriberImpl extends EntityImpl implements DDS.Subscriber {
	
	/* the participant */
	private ParticipantImpl _participant = null;
	
	/* the QoS */
	private SubscriberQos _qos = null;
	
	/* the listener */
	private SubscriberListener _listener = null;
	
	public SubscriberImpl(ParticipantImpl participant, SubscriberQos qos, SubscriberListener listener) {
		_guid = new GUID(
				participant.getGuid().getPrefix(), 
				new EntityId(new EntityId_t(BitUtility.intToBytes(_instanceCount),EntityKindEnum.SUBSCRIBER)));
		_participant = participant;
		_qos = qos;
		_listener = listener;
	}

	///////////////////////////////////////////////
	//	Methods from DDS.Subscriber
	///////////////////////////////////////////////
	
	/**
	 * Entry point for DataReader Creation
	 */
	public DataReader create_datareader(TopicDescription a_topic, DataReaderQos qos, DataReaderListener a_listener) {
		// TODO check qos!!!
		
		TopicImpl topic = (TopicImpl) a_topic;
		Reader reader = null;
		
		try {
			/* reader creation */
			reader = new StatelessReader(this, topic, topic.getTopicId(), 0, 0, topic.getMaxDataLength(), qos.history.depth);
			reader.set_listener(a_listener,0);
			/* register in the participant TODO registrare nel subscriber... */
			_participant.getDatabase().registerReader(reader);
			/* register to the topic */
			topic.addLocalReader(reader);
		} catch (RTjDDSException e) {
			GlobalProperties.logger.log(Logger.WARN, this.getClass(), "create_datareader()",
					"error creating the Reader: "+e.getMessage());
		}
		
		return reader;
	}

	public int delete_datareader(DataReader a_datareader) {
		try {
			_participant.getDatabase().removeReader((Reader)a_datareader);
		} catch (RTjDDSException e) {
			GlobalProperties.logger.log(Logger.WARN, this.getClass(), "delete_datareader()",
					"error deleting the Reader: "+e.getMessage());
			return -1;
		}
		return 1;
	}

	public int delete_contained_entities() {
		// TODO Auto-generated method stub
		return 0;
	}

	public DataReader lookup_datareader(String topic_name) {
		// TODO Auto-generated method stub
		return null;
	}

	public int get_datareaders(DataReader[] readers, int sample_states, int view_states, int instance_states) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void notify_datareaders() {
		// TODO Auto-generated method stub
		
	}

	public int set_qos(SubscriberQos qos) {
		_qos = qos;
		return 0;
	}

	public void get_qos(SubscriberQos qos) {
		qos = _qos;
	}

	public int set_listener(SubscriberListener a_listener, int mask) {
		_listener = a_listener;
		return 0;
	}

	public SubscriberListener get_listener() {
		return _listener;
	}

	public int begin_access() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int end_access() {
		// TODO Auto-generated method stub
		return 0;
	}

	public DomainParticipant get_participant() {
		return _participant;
	}

	public int set_default_datareader_qos(DataReaderQos qos) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void get_default_datareader_qos(DataReaderQos qos) {
		// TODO Auto-generated method stub
		
	}

	public int copy_from_topic_qos(DataReaderQos a_datareader_qos, TopicQos a_topic_qos) {
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
