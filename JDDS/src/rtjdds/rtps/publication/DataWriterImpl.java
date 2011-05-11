//================================================================
//
//	DataWriterImpl.java - Created by kerush, 2006 22-nov-06 9:16:52 
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

import DDS.DataWriter;
import DDS.DataWriterListener;
import DDS.DataWriterQos;
import DDS.InstanceHandle;
import DDS.LivelinessLostStatus;
import DDS.OfferedDeadlineMissedStatus;
import DDS.OfferedIncompatibleQosStatus;
import DDS.PublicationMatchedStatus;
import DDS.Publisher;
import DDS.StatusCondition;
import DDS.SubscriptionBuiltinTopicData;
import DDS.Topic;

/**
 * This class implements the DDS <code>DataWriter</code> entity.<br/>
 * The communication with the RTPS <code>Writer</code> is done by the
 * shared <code>HistoryCache</code>.
 * All the methods in this class runs in the caller thread.
 * 
 * @author kerush
 *
 */
public abstract class DataWriterImpl implements DataWriter {

	/* (non-Javadoc)
	 * @see DDS.DataWriterOperations#set_qos(DDS.DataWriterQos)
	 */
	public int set_qos(DataWriterQos qos) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see DDS.DataWriterOperations#get_qos(DDS.DataWriterQos)
	 */
	public void get_qos(DataWriterQos qos) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see DDS.DataWriterOperations#set_listener(DDS.DataWriterListener, int)
	 */
	public int set_listener(DataWriterListener a_listener, int mask) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see DDS.DataWriterOperations#get_listener()
	 */
	public DataWriterListener get_listener() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see DDS.DataWriterOperations#get_topic()
	 */
	public Topic get_topic() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see DDS.DataWriterOperations#get_publisher()
	 */
	public Publisher get_publisher() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see DDS.DataWriterOperations#get_liveliness_lost_status()
	 */
	public LivelinessLostStatus get_liveliness_lost_status() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see DDS.DataWriterOperations#get_offered_deadline_missed_status()
	 */
	public OfferedDeadlineMissedStatus get_offered_deadline_missed_status() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see DDS.DataWriterOperations#get_offered_incompatible_qos_status()
	 */
	public OfferedIncompatibleQosStatus get_offered_incompatible_qos_status() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see DDS.DataWriterOperations#get_publication_match_status()
	 */
	public PublicationMatchedStatus get_publication_matched_status() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see DDS.DataWriterOperations#assert_liveliness()
	 */
	public int assert_liveliness() {
		// TODO Auto-generated method stub
		return 0;

	}

	/* (non-Javadoc)
	 * @see DDS.DataWriterOperations#get_matched_subscriptions(DDS.InstanceHandle[])
	 */
	public int get_matched_subscriptions(InstanceHandle[] subscription_handles) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see DDS.DataWriterOperations#get_matched_subscription_data(DDS.SubscriptionBuiltinTopicData, int)
	 */
	public int get_matched_subscription_data(
			SubscriptionBuiltinTopicData subscription_data,
			int subscription_handle) {
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
