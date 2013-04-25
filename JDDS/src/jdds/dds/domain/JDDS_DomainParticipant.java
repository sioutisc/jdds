/* ********************************************************************* *
 *                                                                       *
 *   =============================================================       *
 *   Copyright 2011                                                      *
 *   Christos Sioutis <christos.sioutis@gmail.com>                       *
 *   =============================================================       *
 *                                                                       *
 *   This file is part of jdds.                                          *
 *                                                                       *
 *   jdds is free software: you can redistribute it and/or               *
 *   modify it under the terms of the GNU General Public License         *
 *   as published by the Free Software Foundation, either version 3 of   *
 *   the License, or (at your option) any later version.                 *
 *                                                                       *
 *   jdds is distributed in the hope that it will be useful,             *
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of      *
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the       *
 *   GNU General Public License for more details.                        *
 *                                                                       *
 *   You should have received a copy of the GNU General Public           *
 *   License along with jdds.                                            *
 *   If not, see <http://www.gnu.org/licenses/>.                         *
 *                                                                       *
 * ********************************************************************* */

package jdds.dds.domain;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import jdds.dds.pub.JDDS_Publisher;
import jdds.dds.topic.JDDS_Topic;

import org.omg.dds.core.Duration;
import org.omg.dds.core.InstanceHandle;
import org.omg.dds.core.ServiceEnvironment;
import org.omg.dds.core.StatusCondition;
import org.omg.dds.core.Time;
import org.omg.dds.core.status.Status;
import org.omg.dds.domain.DomainParticipant;
import org.omg.dds.domain.DomainParticipantListener;
import org.omg.dds.domain.DomainParticipantQos;
import org.omg.dds.pub.Publisher;
import org.omg.dds.pub.PublisherListener;
import org.omg.dds.pub.PublisherQos;
import org.omg.dds.sub.Subscriber;
import org.omg.dds.sub.SubscriberListener;
import org.omg.dds.sub.SubscriberQos;
import org.omg.dds.topic.ContentFilteredTopic;
import org.omg.dds.topic.MultiTopic;
import org.omg.dds.topic.ParticipantBuiltinTopicData;
import org.omg.dds.topic.Topic;
import org.omg.dds.topic.TopicBuiltinTopicData;
import org.omg.dds.topic.TopicDescription;
import org.omg.dds.topic.TopicListener;
import org.omg.dds.topic.TopicQos;
import org.omg.dds.type.TypeSupport;
import org.omg.dds.type.dynamic.DynamicType;

public class JDDS_DomainParticipant implements DomainParticipant {
	int domainId_ = 0;
	Vector<Publisher> publishers_ = new Vector<Publisher>();
	Vector<Subscriber> subscribers_ = new Vector<Subscriber>();
	Vector<Topic> topics_ = new Vector<Topic>();
	DomainParticipantQos qos_;
	DomainParticipantListener listener_ = null;
	
	public JDDS_DomainParticipant(){}

	public JDDS_DomainParticipant(int domainId) {
		domainId_ = domainId;
	}

	@Override
	public DomainParticipantListener getListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setListener(DomainParticipantListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setListener(DomainParticipantListener listener,
			Collection<Class<? extends Status>> statuses) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DomainParticipantQos getQos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setQos(DomainParticipantQos qos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setQos(String qosLibraryName, String qosProfileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Class<? extends Status>> getStatusChanges() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InstanceHandle getInstanceHandle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void retain() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ServiceEnvironment getEnvironment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Publisher createPublisher() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Publisher createPublisher(PublisherQos qos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Publisher createPublisher(PublisherQos qos,
			PublisherListener listener,
			Collection<Class<? extends Status>> statuses) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subscriber createSubscriber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subscriber createSubscriber(SubscriberQos qos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subscriber createSubscriber(SubscriberQos qos,
			SubscriberListener listener,
			Collection<Class<? extends Status>> statuses) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subscriber getBuiltinSubscriber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <TYPE> Topic<TYPE> createTopic(String topicName, Class<TYPE> type) {
		// TODO Auto-generated method stub
		return new JDDS_Topic<TYPE>();
	}

	@Override
	public <TYPE> Topic<TYPE> createTopic(String topicName, Class<TYPE> type,
			TopicQos qos, TopicListener<TYPE> listener,
			Collection<Class<? extends Status>> statuses) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <TYPE> Topic<TYPE> createTopic(String topicName,
			TypeSupport<TYPE> type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <TYPE> Topic<TYPE> createTopic(String topicName,
			TypeSupport<TYPE> type, TopicQos qos, TopicListener<TYPE> listener,
			Collection<Class<? extends Status>> statuses) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Topic<DynamicType> createTopic(String topicName, DynamicType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Topic<DynamicType> createTopic(String topicName, DynamicType type,
			TopicQos qos, TopicListener<DynamicType> listener,
			Collection<Class<? extends Status>> statuses) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Topic<DynamicType> createTopic(String topicName, DynamicType type,
			TypeSupport<DynamicType> typeSupport) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Topic<DynamicType> createTopic(String topicName, DynamicType type,
			TypeSupport<DynamicType> typeSupport, TopicQos qos,
			TopicListener<DynamicType> listener,
			Collection<Class<? extends Status>> statuses) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <TYPE> Topic<TYPE> findTopic(String topicName, Duration timeout)
			throws TimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <TYPE> Topic<TYPE> findTopic(String topicName, long timeout,
			TimeUnit unit) throws TimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <TYPE> TopicDescription<TYPE> lookupTopicDescription(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <TYPE> ContentFilteredTopic<TYPE> createContentFilteredTopic(
			String name, Topic<? extends TYPE> relatedTopic,
			String filterExpression, List<String> expressionParameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <TYPE> MultiTopic<TYPE> createMultiTopic(String name,
			Class<TYPE> type, String subscriptionExpression,
			List<String> expressionParameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <TYPE> MultiTopic<TYPE> createMultiTopic(String name,
			TypeSupport<TYPE> type, String subscriptionExpression,
			List<String> expressionParameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void closeContainedEntities() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ignoreParticipant(InstanceHandle handle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ignoreTopic(InstanceHandle handle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ignorePublication(InstanceHandle handle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ignoreSubscription(InstanceHandle handle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getDomainId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void assertLiveliness() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PublisherQos getDefaultPublisherQos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDefaultPublisherQos(PublisherQos qos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SubscriberQos getDefaultSubscriberQos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDefaultSubscriberQos(SubscriberQos qos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TopicQos getDefaultTopicQos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDefaultTopicQos(TopicQos qos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<InstanceHandle> getDiscoveredParticipants() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ParticipantBuiltinTopicData getDiscoveredParticipantData(
			InstanceHandle participantHandle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<InstanceHandle> getDiscoveredTopics() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TopicBuiltinTopicData getDiscoveredTopicData(
			InstanceHandle topicHandle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean containsEntity(InstanceHandle handle) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public org.omg.dds.core.ModifiableTime getCurrentTime(
			org.omg.dds.core.ModifiableTime currentTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Time getCurrentTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StatusCondition<DomainParticipant> getStatusCondition() {
		// TODO Auto-generated method stub
		return null;
	}



}
