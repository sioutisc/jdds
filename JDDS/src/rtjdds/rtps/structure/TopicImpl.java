//================================================================
//
//	Topic.java - Created by kerush, 2006 2-dic-06 12:57:38 
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
package rtjdds.rtps.structure;

import java.net.SocketAddress;

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

import rtjdds.rtps.exceptions.CollectionsException;
import rtjdds.rtps.exceptions.RTjDDSException;
import rtjdds.rtps.messages.elements.EntityId;
import rtjdds.rtps.structure.local.ParticipantImpl;
import rtjdds.rtps.subscription.Reader;
import rtjdds.rtps.transport.locators.Locator;
import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;
import rtjdds.util.collections.Copyable;
import rtjdds.util.collections.PoolManagedObject;
import rtjdds.util.collections.RTLinkedList;
import rtjdds.util.collections.RTList;
import rtjdds.util.collections.RTListIterator;
import DDS.DomainParticipant;
import DDS.InconsistentTopicStatus;
import DDS.InconsistentTopicStatusHolder;
import DDS.StatusCondition;
import DDS.Topic;
import DDS.TopicDescription;
import DDS.TopicListener;
import DDS.TopicQos;
import DDS.TopicQosHolder;

public class TopicImpl extends PoolManagedObject implements Copyable, TopicDescription, Topic {
	
	/* reference to the participant */
	protected ParticipantImpl _participant;
	
	/* the ID of the topic */
	protected EntityId _id = null;
	
	protected int _priority = 0;

	/* the name of the topic */
	protected String _name = "";
	
	/* the name of the type associated to this topic */
	protected String _typeName = "";
	
	/* the length of the data associated to this topic */
	protected int _maxDataLength = 0;
	
	/* QoS policies */
	protected TopicQos _qos = null;
	
	/* the listener */
	protected TopicListener _listener = null;
	
	/* references to related entities */
	private RTList _localReaders = null;
	private RTList _localWriters = null;
	private RTList _remoteReaders = null;
	private RTList _remoteWriters = null;
	
	/* TODO move out from here and place it
	 * in ReaderProxies... The problem is to implement a
	 * mechanins that prevents from sending multiple times
	 * the same message to a multicast address that more
	 * readers have joined...
	 * list of remote sockets, should be ordered!!!
	 * and unique!!
	 */
	private RTList _remoteLocators = null;
	
	/**
	 * Lazy constructor used to initialize object in the pool
	 *
	 */
	public TopicImpl() {
	}
	
	/**
	 * Standard constructor: uses the ID, the name and the profile.
	 * 
	 * @param topicId
	 * @param name
	 * @param profile
	 * @throws RTjDDSException
	 */
	public TopicImpl(ParticipantImpl participant, EntityId topicId, String name, 
			String typeName, int maxDataLength, TopicQos qos, TopicProfile profile, TopicListener listener) throws RTjDDSException {
		_participant = participant;
		_id = topicId;
		_name = name;
		_typeName = typeName;
		_qos = qos;
		
		// add the type support for topics
		_maxDataLength = maxDataLength;
		
		_listener = listener;
		_localReaders = new RTLinkedList(profile.MAX_LOCAL_READERS);
		_localWriters = new RTLinkedList(profile.MAX_LOCAL_WRITERS);
		_remoteWriters = new RTLinkedList(profile.MAX_REMOTE_WRITERS);
		_remoteReaders = new RTLinkedList(profile.MAX_REMOTE_READERS);
		// TODO see above
		_remoteLocators = new RTLinkedList(profile.MAX_REMOTE_READERS);
		
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"new()",
				"Created new topic '"+_name+" ID="+_id);
		GlobalProperties.logger.printMemStats();
	}
	
	////////////////////////////////////////////////////////////
	// Local Readers getters and setters (type: Reader)
	////////////////////////////////////////////////////////////
	
	public void setPriority( int prio ) {
		_priority = prio;
	}
	
	public int getPriority() {
		return _priority;
	}
	
	public void addLocalReader(Reader reader) throws RTjDDSException {
		_localReaders.add(reader);
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"addReader()",
				"Topic '"+_name+"' : added new Reader "+reader._guid+
				", there are now "+_localReaders.size()+" registered readers");
		GlobalProperties.logger.printMemStats();
	}
	
	public RTListIterator localReadersIterator() {
		return _localReaders.listIterator();
	}
	
//	public Reader[] getLocalReaders() throws CollectionsException {
//		Reader[] readers = new Reader[_localReaders.size()];
//		_localReaders.toArray(readers);
//		return readers;
//	}
	
	public Reader[] getLocalReaders(Reader[] readers) throws CollectionsException {
		return (Reader[]) _localReaders.toArray(readers);
	}
	
	public void removeLocalReader(Reader reader) throws CollectionsException {
		_localReaders.remove(reader);
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"addReader()",
				"Topic '"+_name+"' : removed Reader "+reader._guid+
				", there are now "+_localReaders.size()+" registered readers");
		GlobalProperties.logger.printMemStats();
	}
	
	public int numberOfLocalReaders() {
		return _localReaders.size();
	}
	
	public EntityId getTopicId() {
		return _id;
	}
	
	public int getMaxDataLength() {
		return _maxDataLength;
	}
	
	
	////////////////////////////////////////////////////////////
	// RemoteReaders getters and setters (type: Reader)
	////////////////////////////////////////////////////////////
	
	public void addRemoteLocator(Locator socket) throws RTjDDSException {
		_remoteLocators.add(socket);
	}
	
	public void removeRemoteLocator(Locator socket) throws RTjDDSException {
		_remoteLocators.remove(socket);
	}
	
	public RTListIterator getRemoteLocatorsIterator() {
		return _remoteLocators.listIterator();
	}

	////////////////////////////////////////////////////////
	// methods from Copyable
	////////////////////////////////////////////////////////

	public void copyFrom(Copyable obj) {
		if (obj instanceof TopicImpl) {
			TopicImpl src = (TopicImpl) obj;
			_localReaders = src._localReaders;
			_localWriters = src._localWriters;
			_remoteReaders = src._remoteReaders;
			_remoteWriters = src._remoteWriters;
		}
		else {
			GlobalProperties.logger.log(Logger.WARN,this.getClass(),"copyFrom()",
					"Cannot copy from a non Topic instance");
		}
	}

	public void copyTo(Copyable obj) {
		if (obj instanceof TopicImpl) {
			TopicImpl dst = (TopicImpl) obj;
			dst._localReaders = _localReaders;
			dst._localWriters = _localWriters;
			dst._remoteReaders = _remoteReaders;
			dst._remoteWriters = _remoteWriters;
		}
		else {
			GlobalProperties.logger.log(Logger.WARN,this.getClass(),"copyTo()",
					"Cannot copy into a non Topic instance");
		}
	}
	
	////////////////////////////////////////////////////////
	// methods from DDS::Topic
	////////////////////////////////////////////////////////	

	public InconsistentTopicStatus get_inconsistent_topic_status() {
		// TODO Auto-generated method stub
		return null;
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

	public String get_type_name() {
		return _typeName;
	}

	public String get_name() {
		return _name;
	}

	public DomainParticipant get_participant() {
		return _participant;
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

	@Override
	public int set_qos(TopicQos qos) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_qos(TopicQosHolder qos) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int set_listener(TopicListener a_listener, int mask) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TopicListener get_listener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int get_inconsistent_topic_status(
			InconsistentTopicStatusHolder a_status) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_instance_handle() {
		// TODO Auto-generated method stub
		return 0;
	}



}
