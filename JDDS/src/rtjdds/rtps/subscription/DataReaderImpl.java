//================================================================
//
//	DataReaderImpl.java - Created by kerush, 2006 22-nov-06 8:56:31 
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
import DDS.LivelinessChangedStatus;
import DDS.PublicationBuiltinTopicData;
import DDS.QueryCondition;
import DDS.REQUESTED_DEADLINE_MISSED_STATUS;
import DDS.ReadCondition;
import DDS.RequestedDeadlineMissedStatus;
import DDS.RequestedIncompatibleQosStatus;
import DDS.SampleLostStatus;
import DDS.SampleRejectedStatus;
import DDS.StatusCondition;
import DDS.Subscriber;
import DDS.SubscriptionMatchStatus;
import DDS.TopicDescription;

/**
 * This class implements the DDS <code>DataReader</code> entity.<br/>
 * The communication with the RTPS <code>Reader</code> is done by the
 * shared <code>HistoryCache</code>.
 * All the methods in this class runs in the caller thread.
 * 
 * @author kerush
 *
 */
public abstract class DataReaderImpl implements DataReader {
	
	private Reader _reader = null;
	
	public DataReaderImpl (Reader reader) {
		_reader = reader;
	}

	/* (non-Javadoc)
	 * @see DDS.DataReaderOperations#create_readcondition(int, int, int)
	 */
	public ReadCondition create_readcondition(int sample_states,
			int view_states, int instance_states) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see DDS.DataReaderOperations#create_querycondition(int, int, int, java.lang.String, java.lang.String[])
	 */
	public QueryCondition create_querycondition(int sample_states,
			int view_states, int instance_states, String query_expression,
			String[] query_parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see DDS.DataReaderOperations#delete_readcondition(DDS.ReadCondition)
	 */
	public int delete_readcondition(ReadCondition a_condition) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see DDS.DataReaderOperations#delete_contained_entities()
	 */
	public int delete_contained_entities() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see DDS.DataReaderOperations#set_qos(DDS.DataReaderQos)
	 */
	public int set_qos(DataReaderQos qos) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see DDS.DataReaderOperations#get_qos(DDS.DataReaderQos)
	 */
	public void get_qos(DataReaderQos qos) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see DDS.DataReaderOperations#set_listener(DDS.DataReaderListener, int)
	 */
	public int set_listener(DataReaderListener a_listener, int mask) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see DDS.DataReaderOperations#get_listener()
	 */
	public DataReaderListener get_listener() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see DDS.DataReaderOperations#get_topicdescription()
	 */
	public TopicDescription get_topicdescription() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see DDS.DataReaderOperations#get_subscriber()
	 */
	public Subscriber get_subscriber() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see DDS.DataReaderOperations#get_sample_rejected_status()
	 */
	public SampleRejectedStatus get_sample_rejected_status() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see DDS.DataReaderOperations#get_liveliness_changed_status()
	 */
	public LivelinessChangedStatus get_liveliness_changed_status() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see DDS.DataReaderOperations#get_requested_deadline_missed_status()
	 */
	public RequestedDeadlineMissedStatus get_requested_deadline_missed_status() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see DDS.DataReaderOperations#get_requested_incompatible_qos_status()
	 */
	public RequestedIncompatibleQosStatus get_requested_incompatible_qos_status() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see DDS.DataReaderOperations#get_subscription_match_status()
	 */
	public SubscriptionMatchStatus get_subscription_match_status() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see DDS.DataReaderOperations#get_sample_lost_status()
	 */
	public SampleLostStatus get_sample_lost_status() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see DDS.DataReaderOperations#wait_for_historical_data(DDS.Duration_t)
	 */
	public int wait_for_historical_data(Duration_t max_wait) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see DDS.DataReaderOperations#get_matched_publications(DDS.InstanceHandle[])
	 */
	public int get_matched_publications(InstanceHandle[] publication_handles) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see DDS.DataReaderOperations#get_matched_publication_data(DDS.PublicationBuiltinTopicData, int)
	 */
	public int get_matched_publication_data(
			PublicationBuiltinTopicData publication_data, int publication_handle) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see DDS.EntityOperations#enable()
	 */
	public int enable() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see DDS.EntityOperations#get_statuscondition()
	 */
	public StatusCondition get_statuscondition() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see DDS.EntityOperations#get_status_changes()
	 */
	public int get_status_changes() {
		// TODO Auto-generated method stub
		return 0;
	}

}
