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
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import jdds.dds.pub.JDDS_Publisher;
import jdds.dds.topic.JDDS_Topic;

import org.omg.dds.core.Bootstrap;
import org.omg.dds.core.Duration;
import org.omg.dds.core.InstanceHandle;
import org.omg.dds.core.StatusCondition;
import org.omg.dds.core.modifiable.ModifiableTime;
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

	public JDDS_DomainParticipant(int domainId,
			DomainParticipantQos qos, DomainParticipantListener listener,
			Collection<Class<? extends Status<?, ?>>> statuses) {
		qos_ = qos;
		listener_ = listener;
		//TODO statuses?
	}

	public JDDS_DomainParticipant(int domainId,
			String qosLibraryName, String qosProfileName,
			DomainParticipantListener listener,
			Collection<Class<? extends Status<?, ?>>> statuses) {
	}
	

	@Override
	public DomainParticipantListener getListener() {
		return listener_;
	}

	@Override
	public void setListener(DomainParticipantListener listener) {
		listener_ = listener;
	}

	@Override
	public DomainParticipantQos getQos() {
		return qos_;
	}

	@Override
	public void setQos(DomainParticipantQos qos) {
		qos_ = qos;
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
	public StatusCondition<DomainParticipant> getStatusCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Class<? extends Status<?, ?>>> getStatusChanges(
			Collection<Class<? extends Status<?, ?>>> statuses) {
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
	public Bootstrap getBootstrap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Publisher createPublisher() {
		JDDS_Publisher pub = new JDDS_Publisher(this);
		publishers_.add(pub);
		return pub;
	}

	@Override
	public Publisher createPublisher(PublisherQos qos,
			PublisherListener listener,
			Collection<Class<? extends Status<?, ?>>> statuses) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Publisher createPublisher(String qosLibraryName,
			String qosProfileName, PublisherListener listener,
			Collection<Class<? extends Status<?, ?>>> statuses) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subscriber createSubscriber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subscriber createSubscriber(SubscriberQos qos,
			SubscriberListener listener,
			Collection<Class<? extends Status<?, ?>>> statuses) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subscriber createSubscriber(String qosLibraryName,
			String qosProfileName, SubscriberListener listener,
			Collection<Class<? extends Status<?, ?>>> statuses) {
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
		Topic<TYPE> topic = new JDDS_Topic<TYPE>(topicName,type);
		topics_.add(topic);
		return topic;
	}

	@Override
	public <TYPE> Topic<TYPE> createTopic(String topicName, Class<TYPE> type,
			TopicQos qos, TopicListener<TYPE> listener,
			Collection<Class<? extends Status<?, ?>>> statuses) {
		Topic<TYPE> topic = new JDDS_Topic<TYPE>(topicName,type,qos,listener,statuses);
		topics_.add(topic);
		return topic;
	}

	@Override
	public <TYPE> Topic<TYPE> createTopic(String topicName, Class<TYPE> type,
			String qosLibraryName, String qosProfileName,
			TopicListener<TYPE> listener,
			Collection<Class<? extends Status<?, ?>>> statuses) {
		Topic<TYPE> topic = new JDDS_Topic<TYPE>(topicName,type,qosLibraryName,qosProfileName,listener,statuses);
		topics_.add(topic);
		return topic;
	}

	@Override
	public <TYPE> Topic<TYPE> createTopic(String topicName,
			TypeSupport<TYPE> type) {
		Topic<TYPE> topic = new JDDS_Topic<TYPE>(topicName,type);
		topics_.add(topic);
		return topic;
	}

	@Override
	public <TYPE> Topic<TYPE> createTopic(String topicName,
			TypeSupport<TYPE> type, TopicQos qos, TopicListener<TYPE> listener,
			Collection<Class<? extends Status<?, ?>>> statuses) {
		Topic<TYPE> topic = new JDDS_Topic<TYPE>(topicName,type,qos,listener,statuses);
		topics_.add(topic);
		return topic;	}

	@Override
	public <TYPE> Topic<TYPE> createTopic(String topicName,
			TypeSupport<TYPE> type, String qosLibraryName,
			String qosProfileName, TopicListener<TYPE> listener,
			Collection<Class<? extends Status<?, ?>>> statuses) {
		Topic<TYPE> topic = new JDDS_Topic<TYPE>(topicName,type,qosLibraryName,qosProfileName,listener,statuses);
		topics_.add(topic);
		return topic;	}

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
			String typeName, String subscriptionExpression,
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
		return domainId_;
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
	public void setDefaultPublisherQos(String qosLibraryName,
			String qosProfileName) {
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
	public void setDefaultSubscriberQos(String qosLibraryName,
			String qosProfileName) {
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
	public void setDefaultTopicQos(String qosLibraryName, String qosProfileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<InstanceHandle> getDiscoveredParticipants(
			Collection<InstanceHandle> participantHandles) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ParticipantBuiltinTopicData getDiscoveredParticipantData(
			ParticipantBuiltinTopicData participantData,
			InstanceHandle participantHandle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<InstanceHandle> getDiscoveredTopics(
			Collection<InstanceHandle> topicHandles) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TopicBuiltinTopicData getDiscoveredTopicData(
			TopicBuiltinTopicData topicData, InstanceHandle topicHandle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean containsEntity(InstanceHandle handle) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ModifiableTime getCurrentTime(ModifiableTime currentTime) {
		// TODO Auto-generated method stub
		return null;
	}

}
