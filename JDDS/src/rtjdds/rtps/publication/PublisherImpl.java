//================================================================
//
//	PublisherImpl.java - Created by kerush, 2006 7-dic-06 4:52:22 
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

import org.omg.CORBA.Context;
import org.omg.CORBA.ContextList;
import org.omg.CORBA.DomainManager;
import org.omg.CORBA.ExceptionList;
import org.omg.CORBA.NVList;
import org.omg.CORBA.NamedValue;
import org.omg.CORBA.Object;
import org.omg.CORBA.Policy;
import org.omg.CORBA.Request;
import org.omg.CORBA.SetOverrideType;

import rtjdds.rtps.exceptions.RTjDDSException;
import rtjdds.rtps.messages.elements.EntityId;
import rtjdds.rtps.messages.elements.EntityKindEnum;
import rtjdds.rtps.messages.elements.GUID;
import rtjdds.rtps.structure.EntityImpl;
import rtjdds.rtps.structure.TopicImpl;
import rtjdds.rtps.structure.local.ParticipantImpl;
import rtjdds.rtps.types.EntityId_t;
import rtjdds.util.BitUtility;
import DDS.DataWriter;
import DDS.DataWriterListener;
import DDS.DataWriterQos;
import DDS.DataWriterQosHolder;
import DDS.DomainParticipant;
import DDS.Duration_t;
import DDS.Publisher;
import DDS.PublisherListener;
import DDS.PublisherQos;
import DDS.PublisherQosHolder;
import DDS.StatusCondition;
import DDS.SubscriberListener;
import DDS.SubscriberQos;
import DDS.Topic;
import DDS.TopicQos;

public class PublisherImpl extends EntityImpl implements Publisher {
	
	/* the participant */
	private ParticipantImpl _participant = null;
	
	/* the QoS */
	private PublisherQos _qos = null;
	
	/* the listener */
	private PublisherListener _listener = null;
	
	public PublisherImpl(ParticipantImpl participant, PublisherQos qos, PublisherListener listener) {
		_guid = new GUID(
				participant.getGuid().getPrefix(), 
				new EntityId(new EntityId_t(BitUtility.intToBytes(_instanceCount),EntityKindEnum.SUBSCRIBER)));
		_participant = participant;
		_qos = qos;
		_listener = listener;
	}

	public DataWriter create_datawriter(Topic a_topic, DataWriterQos qos,
			DataWriterListener a_listener) {
		Writer writer = null;
		try {
			writer = Writer.createWriter(this,(TopicImpl)a_topic,qos,a_listener);
		} catch (RTjDDSException e) {
			e.printStackTrace();
		}
		return writer;
	}

	public int delete_datawriter(DataWriter a_datawriter) {
		// TODO Auto-generated method stub
		return 0;
	}

	public DataWriter lookup_datawriter(String topic_name) {
		// TODO Auto-generated method stub
		return null;
	}

	public int delete_contained_entities() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int set_qos(PublisherQos qos) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void get_qos(PublisherQos qos) {
		// TODO Auto-generated method stub
	}

	public int set_listener(PublisherListener a_listener, int mask) {
		// TODO Auto-generated method stub
		return 0;
	}

	public PublisherListener get_listener() {
		// TODO Auto-generated method stub
		return null;
	}

	public int suspend_publications() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int resume_publications() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int begin_coherent_changes() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int end_coherent_changes() {
		// TODO Auto-generated method stub
		return 0;
	}

	public DomainParticipant get_participant() {
		return _participant;
	}

	public int set_default_datawriter_qos(DataWriterQos qos) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void get_default_datawriter_qos(DataWriterQos qos) {
		// TODO Auto-generated method stub

	}

	public int copy_from_topic_qos(DataWriterQos a_datawriter_qos,
			TopicQos a_topic_qos) {
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

	@Override
	public DataWriter create_datawriter(Topic a_topic, DataWriterQos qos,
			DataWriterListener a_listener, int mask) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int get_qos(PublisherQosHolder qos) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int wait_for_acknowledgments(Duration_t max_wait) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_default_datawriter_qos(DataWriterQosHolder qos) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int copy_from_topic_qos(DataWriterQosHolder a_datawriter_qos,
			TopicQos a_topic_qos) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_instance_handle() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Request _create_request(Context ctx, String operation,
			NVList arg_list, NamedValue result) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Request _create_request(Context ctx, String operation,
			NVList arg_list, NamedValue result, ExceptionList exclist,
			ContextList ctxlist) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object _duplicate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DomainManager[] _get_domain_managers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object _get_interface_def() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Policy _get_policy(int policy_type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int _hash(int maximum) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean _is_a(String repositoryIdentifier) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean _is_equivalent(Object other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean _non_existent() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void _release() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Request _request(String operation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object _set_policy_override(Policy[] policies,
			SetOverrideType set_add) {
		// TODO Auto-generated method stub
		return null;
	}

}
