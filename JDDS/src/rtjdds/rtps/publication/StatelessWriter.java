//================================================================
//
//	StatelessWriter.java - Created by kerush, 2006 4-dic-06 10:31:02 
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

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import javax.realtime.LTMemory;
import javax.realtime.MemoryArea;
import javax.realtime.RealtimeThread;

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

import DDS.DataWriterListener;
import DDS.DataWriterQosHolder;
import DDS.Duration_t;
import DDS.InstanceHandle;
import DDS.InstanceHandleSeqHolder;
import DDS.LivelinessLostStatusHolder;
import DDS.OfferedDeadlineMissedStatusHolder;
import DDS.OfferedIncompatibleQosStatusHolder;
import DDS.PublicationMatchedStatusHolder;
import DDS.SubscriptionBuiltinTopicDataHolder;
import DDS.Time_t;



import rtjdds.rtps.CacheChange;
import rtjdds.rtps.exceptions.OutOfResourceException;
import rtjdds.rtps.exceptions.RTjDDSException;
import rtjdds.rtps.messages.Data;
import rtjdds.rtps.messages.InfoTimestamp;
import rtjdds.rtps.messages.Message;
import rtjdds.rtps.messages.NoKeyData;
import rtjdds.rtps.messages.RTPSHeader;
import rtjdds.rtps.messages.Submessage;
import rtjdds.rtps.messages.elements.EntityId;
import rtjdds.rtps.messages.elements.EntityKindEnum;
import rtjdds.rtps.messages.elements.KeyHashPrefix;
import rtjdds.rtps.messages.elements.KeyHashSuffix;
import rtjdds.rtps.messages.elements.ParameterList;
import rtjdds.rtps.messages.elements.SequenceNumber;
import rtjdds.rtps.messages.elements.SerializedData;
import rtjdds.rtps.messages.elements.StatusInfo;
import rtjdds.rtps.messages.elements.Timestamp;
import rtjdds.rtps.portable.CDROutputPacket;
import rtjdds.rtps.structure.TopicImpl;
import rtjdds.rtps.structure.local.ParticipantImpl;
//import rtjdds.rtps.transport.InetSocketLocator;
import rtjdds.rtps.types.DATA;
import rtjdds.rtps.types.EntityId_t;
import rtjdds.rtps.types.INFO_TS;
import rtjdds.rtps.types.NOKEY_DATA;
import rtjdds.rtps.types.SequenceNumber_t;
import rtjdds.util.GUIDPrefixFactory;
import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;
import rtjdds.util.collections.RTListIterator;
//import test.latency.SendLatency;

public class StatelessWriter extends Writer {

	public StatelessWriter(PublisherImpl publisher, TopicImpl topic, EntityId entityId, 
			int topicKind, int reliabilityKind, int data_length, int history_depth, DataWriterListener listener) throws RTjDDSException {
		
		super(publisher, topic, entityId, topicKind, reliabilityKind, data_length, history_depth, listener);
		
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"new()",
				"Creating a StatelessWriter with History Depth of "+history_depth);
		
	}
	
	public void writeUntyped(SerializedData s_data, InstanceHandle handle) {
		writeUntyped(s_data, handle, RealtimeThread.currentRealtimeThread().getPriority());
	}
	
    public void writeUntyped(SerializedData s_data, InstanceHandle handle, int prio) {
    	/* in this implementation, stateless and unreliable, the history
    	 * cache have no sense for the writer... Simply the message is
    	 * sended at write time */
    	
    	/* runs in a  scratch scope */
    	_serializer.writeHeader();
    	if (handle == null) {
    		_serializer.writeNoKeyData(_topic.getTopicId(),null,s_data);
    	}
    	else {
    		_serializer.writeData(_topic.getTopicId(),null,null,null,null,s_data);
    	}
    	
//    	System.out.println("WRITING AT PRIO "+prio+ " TO MBOX "+(prio-1));
//    	GlobalProperties.pipeSelector.write(_serializer.getPacket().getBuffer(), prio-1);
    	
    	/* send to each reader - in writer's scope  */
//    	RTListIterator it = _topic.getRemoteLocatorsIterator();
//    	while (it.hasNext()) {
//    		InetSocketLocator addr = (InetSocketLocator) it.next();
//    		_sender.send(addr);
//    	}
    	
    }
    
    
    public void writeUntypedWithTimestamp(SerializedData s_data, InstanceHandle handle, Timestamp ts) {
    
    	
    	_serializer.writeHeader();
    	_serializer.writeInfoTimestamp();
    	if (handle == null) {
    		_serializer.writeNoKeyData(_topic.getTopicId(),null,s_data);
    	}
    	else {
    		_serializer.writeData(_topic.getTopicId(),null,null,null,null,s_data);
    	}
    	
    	
//    	RTListIterator it = _topic.getRemoteReaderSocketIterator();
//    	while (it.hasNext()) {
//    		InetSocketAddress addr = (InetSocketAddress) it.next();
//    		_sender.send(addr);
//    	}
    	
    }

	@Override
	public int get_qos(DataWriterQosHolder qos) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int wait_for_acknowledgments(Duration_t max_wait) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_liveliness_lost_status(LivelinessLostStatusHolder status) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_offered_deadline_missed_status(
			OfferedDeadlineMissedStatusHolder status) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_offered_incompatible_qos_status(
			OfferedIncompatibleQosStatusHolder status) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_publication_matched_status(
			PublicationMatchedStatusHolder status) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_matched_subscriptions(
			InstanceHandleSeqHolder subscription_handles) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_matched_subscription_data(
			SubscriptionBuiltinTopicDataHolder subscription_data,
			int subscription_handle) {
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


