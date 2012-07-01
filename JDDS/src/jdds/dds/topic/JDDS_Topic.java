/* ********************************************************************* *
 *                                                                       *
 *   =============================================================       *
 *   Copyright 2012                                                      *
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

package jdds.dds.topic;

import java.util.Collection;

import org.omg.dds.core.Bootstrap;
import org.omg.dds.core.InstanceHandle;
import org.omg.dds.core.StatusCondition;
import org.omg.dds.core.status.InconsistentTopicStatus;
import org.omg.dds.core.status.Status;
import org.omg.dds.domain.DomainParticipant;
import org.omg.dds.topic.Topic;
import org.omg.dds.topic.TopicDescription;
import org.omg.dds.topic.TopicListener;
import org.omg.dds.topic.TopicQos;
import org.omg.dds.type.TypeSupport;

public class JDDS_Topic<TYPE> implements Topic<TYPE>{
	
	public JDDS_Topic(String topicName, Class<TYPE> type) {
	}

	public JDDS_Topic(String topicName, Class<TYPE> type,
			TopicQos qos, TopicListener<TYPE> listener,
			Collection<Class<? extends Status<?, ?>>> statuses) {
	}
	
	public JDDS_Topic(String topicName, Class<TYPE> type,
			String qosLibraryName, String qosProfileName,
			TopicListener<TYPE> listener,
			Collection<Class<? extends Status<?, ?>>> statuses) {
	}

	public JDDS_Topic(String topicName,
			TypeSupport<TYPE> type) {
	}

	public JDDS_Topic(String topicName,
			TypeSupport<TYPE> type, TopicQos qos, TopicListener<TYPE> listener,
			Collection<Class<? extends Status<?, ?>>> statuses) {
	}

	public JDDS_Topic(String topicName,
			TypeSupport<TYPE> type, String qosLibraryName,
			String qosProfileName, TopicListener<TYPE> listener,
			Collection<Class<? extends Status<?, ?>>> statuses) {
	}

	@Override
	public Class<TYPE> getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <OTHER> TopicDescription<OTHER> cast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DomainParticipant getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Bootstrap getBootstrap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TopicListener<TYPE> getListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setListener(TopicListener<TYPE> listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TopicQos getQos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setQos(TopicQos qos) {
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
	public StatusCondition<Topic<TYPE>> getStatusCondition() {
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
	public void retain() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public InconsistentTopicStatus<TYPE> getInconsistentTopicStatus(
			InconsistentTopicStatus<TYPE> status) {
		// TODO Auto-generated method stub
		return null;
	}

}
