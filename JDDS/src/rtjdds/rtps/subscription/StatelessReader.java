//================================================================
//
//	StatelessReader.java - Created by kerush, 2006 22-nov-06 9:37:51 
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

import rtjdds.rtps.CacheChange;
import rtjdds.rtps.InstanceHandle;
import rtjdds.rtps.UnorderedHistoryCache;
import rtjdds.rtps.exceptions.CollectionsException;
import rtjdds.rtps.exceptions.OutOfResourceException;
import rtjdds.rtps.exceptions.RTjDDSException;
import rtjdds.rtps.messages.Data;
import rtjdds.rtps.messages.NoKeyData;
import rtjdds.rtps.messages.elements.EntityId;
import rtjdds.rtps.messages.elements.GUID;
import rtjdds.rtps.messages.elements.SequenceNumber;
import rtjdds.rtps.messages.elements.SerializedData;
import rtjdds.rtps.messages.elements.StatusInfo;
import rtjdds.rtps.structure.TopicImpl;
import rtjdds.rtps.structure.local.ParticipantImpl;
import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;
import DDS.DataReader;
import DDS.DataReaderListener;
import DDS.DataReaderQos;
import DDS.DataReaderQosHolder;
import DDS.Duration_t;
import DDS.InstanceHandleSeqHolder;
import DDS.LivelinessChangedStatus;
import DDS.LivelinessChangedStatusHolder;
import DDS.PublicationBuiltinTopicData;
import DDS.PublicationBuiltinTopicDataHolder;
import DDS.QueryCondition;
import DDS.ReadCondition;
import DDS.RequestedDeadlineMissedStatus;
import DDS.RequestedDeadlineMissedStatusHolder;
import DDS.RequestedIncompatibleQosStatus;
import DDS.RequestedIncompatibleQosStatusHolder;
import DDS.SampleLostStatus;
import DDS.SampleLostStatusHolder;
import DDS.SampleRejectedStatus;
import DDS.SampleRejectedStatusHolder;
import DDS.StatusCondition;
import DDS.Subscriber;
import DDS.SubscriptionMatchedStatus;
import DDS.SubscriptionMatchedStatusHolder;
import DDS.TopicDescription;

public class StatelessReader extends Reader {
	

	public StatelessReader(SubscriberImpl subscriber, TopicImpl topic, EntityId entityId, int entityKind, 
			int reliabilityKind, int max_data_length, int history_depth) throws RTjDDSException {
		
		super(subscriber, topic, entityId, entityKind, reliabilityKind, max_data_length, history_depth);
		
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"new()",
				"Creating a StatelessReader with History Depth of "+history_depth);
		
		
	}

	/**
	 * In the stateless implementation, every received <code>Data</code> message is added
	 * to the <code>HistoryCache</code> without considering the order.<br/>
	 * This method is called by the <code>SubmessageDispatcher</code>, thus it runs in the
	 * receiver thread and in the temporary demarshalling scope. To enable the passing of
	 * the data from the demarshalling scope to the reader scope, a deep copy of the <code>
	 * CacheChange</code> is done in the HistoryCache <code>add_change()</code> method.
	 * 
	 * @param data the Data submessage to add into the HistoryCache
	 */
	public void accept(Data data) {
		
		// kind =  {ALIVE; DISPOSED, UNREGISTERED}
		short kind = CacheChange.ALIVE;
		StatusInfo si = data.getSi(); 
		if (si != null) {
			if (si.isDisposed())
				kind = CacheChange.NOT_ALIVE_DISPOSED;
			if (si.isUnregistered())
				kind = CacheChange.NOT_ALIVE_UNREGISTERED;
		}
		// writer GUID
		GUID writerGUID = new GUID(data.getSrcGuidPrefix(),data.getWriterId());
		// instanceHandle TODO: implement GUID to instanceHandle conversion
		InstanceHandle handle = new InstanceHandle(0);
		// Sequence Number
		SequenceNumber sn = data.getWriterSN();
		// Serialized (raw) data
		SerializedData sd = data.getSerializedData();
		// Adding the change
		CacheChange newChange = new CacheChange(kind,writerGUID,handle,sn,sd);
		try {
			_readerCache.add_change(newChange);
		} catch (OutOfResourceException e) {
			// TODO Inform the user!!!!!!
			GlobalProperties.logger.log(Logger.INFO,this.getClass(),
					"acceptData()",e.getMessage());
			e.printStackTrace();
		}
		
//		GlobalProperties.logger.log(Logger.INFO,this.getClass(),
//				"acceptData()","Data accepted:"+_readerCache);
//		upcall to listener TODO should be done elsewhere
		if (_listener != null)
			_listener.on_data_available(this);
	}

	public void accept(NoKeyData data) {
		
//		 kind =  {ALIVE; DISPOSED, UNREGISTERED}
		short kind = CacheChange.ALIVE;
		// writer GUID
		GUID writerGUID = new GUID(data.getSrcGuidPrefix(),data.getWriterId());
		// instanceHandle TODO: implement GUID to instanceHandle conversion
		InstanceHandle handle = new InstanceHandle(0);
		// Sequence Number
		SequenceNumber sn = data.getWriterSN();
		// Serialized (raw) data
		SerializedData sd = data.getSerializedData();
		// Adding the change
		CacheChange newChange = new CacheChange(kind,writerGUID,handle,sn,sd);
		
		try {
			_readerCache.add_change(newChange);
		} catch (OutOfResourceException e) {
			// TODO Inform the user!!!!!! --> status changed
			GlobalProperties.logger.log(Logger.INFO,this.getClass(),"acceptData()",e.getMessage());
			e.printStackTrace();
		}
		
//		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"acceptData()","Data accepted:"+_readerCache);
//		upcall to listener TODO should be done elsewhere
		if (_listener != null)
			_listener.on_data_available(this);
	}

	@Override
	public int get_qos(DataReaderQosHolder qos) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_sample_rejected_status(SampleRejectedStatusHolder status) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_liveliness_changed_status(
			LivelinessChangedStatusHolder status) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_requested_deadline_missed_status(
			RequestedDeadlineMissedStatusHolder status) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_requested_incompatible_qos_status(
			RequestedIncompatibleQosStatusHolder status) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_subscription_matched_status(
			SubscriptionMatchedStatusHolder status) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_sample_lost_status(SampleLostStatusHolder status) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_matched_publications(
			InstanceHandleSeqHolder publication_handles) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_matched_publication_data(
			PublicationBuiltinTopicDataHolder publication_data,
			int publication_handle) {
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
