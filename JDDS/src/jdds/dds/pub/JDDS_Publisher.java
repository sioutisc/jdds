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

package jdds.dds.pub;

import java.util.Collection;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.omg.dds.core.Bootstrap;
import org.omg.dds.core.Duration;
import org.omg.dds.core.InstanceHandle;
import org.omg.dds.core.StatusCondition;
import org.omg.dds.core.status.Status;
import org.omg.dds.domain.DomainParticipant;
import org.omg.dds.pub.DataWriter;
import org.omg.dds.pub.DataWriterListener;
import org.omg.dds.pub.DataWriterQos;
import org.omg.dds.pub.Publisher;
import org.omg.dds.pub.PublisherListener;
import org.omg.dds.pub.PublisherQos;
import org.omg.dds.sub.DataReader;
import org.omg.dds.sub.DataReaderListener;
import org.omg.dds.sub.DataReaderQos;
import org.omg.dds.sub.InstanceState;
import org.omg.dds.sub.SampleState;
import org.omg.dds.sub.Subscriber;
import org.omg.dds.sub.SubscriberListener;
import org.omg.dds.sub.SubscriberQos;
import org.omg.dds.sub.ViewState;
import org.omg.dds.topic.Topic;
import org.omg.dds.topic.TopicDescription;
import org.omg.dds.topic.TopicQos;
import org.omg.dds.type.builtin.BytesDataReader;
import org.omg.dds.type.builtin.BytesDataWriter;
import org.omg.dds.type.builtin.KeyedBytes;
import org.omg.dds.type.builtin.KeyedBytesDataReader;
import org.omg.dds.type.builtin.KeyedBytesDataWriter;
import org.omg.dds.type.builtin.KeyedString;
import org.omg.dds.type.builtin.KeyedStringDataReader;
import org.omg.dds.type.builtin.KeyedStringDataWriter;
import org.omg.dds.type.builtin.StringDataReader;
import org.omg.dds.type.builtin.StringDataWriter;

public class JDDS_Publisher implements Publisher {
	DomainParticipant dp_;
	PublisherQos qos_;
	PublisherListener listener_;
	Vector<DataWriter> writers_ = new Vector<DataWriter>();

	public JDDS_Publisher(DomainParticipant dp) {
		dp_ = dp;
	}

	public JDDS_Publisher(DomainParticipant dp,
			PublisherQos qos,
			PublisherListener listener,
			Collection<Class<? extends Status<?, ?>>> statuses) {
		dp_ = dp;
		qos_ = qos;
		listener_ = listener;
		//TODO statuses
	}

	public JDDS_Publisher(DomainParticipant dp,
			String qosLibraryName,
			String qosProfileName, PublisherListener listener,
			Collection<Class<? extends Status<?, ?>>> statuses) {
		dp_ = dp;
		listener_ = listener;
		// TODO Auto-generated method stub
	}

	@Override
	public DomainParticipant getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PublisherListener getListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setListener(PublisherListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PublisherQos getQos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setQos(PublisherQos qos) {
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
	public StatusCondition<Publisher> getStatusCondition() {
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
	public <TYPE> DataWriter<TYPE> createDataWriter(Topic<TYPE> topic) {
		DataWriter<TYPE> dw = new JDDS_DataWriter<TYPE>(this,topic);
		writers_.add(dw);
		return dw;
	}

	@Override
	public <TYPE> DataWriter<TYPE> createDataWriter(Topic<TYPE> topic,
			DataWriterQos qos, DataWriterListener<TYPE> listener,
			Collection<Class<? extends Status<?, ?>>> statuses) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <TYPE> DataWriter<TYPE> createDataWriter(Topic<TYPE> topic,
			String qosLibraryName, String qosProfileName,
			DataWriterListener<TYPE> listener,
			Collection<Class<? extends Status<?, ?>>> statuses) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BytesDataWriter createBytesDataWriter(Topic<byte[]> topic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BytesDataWriter createBytesDataWriter(Topic<byte[]> topic,
			DataWriterQos qos, DataWriterListener<byte[]> listener,
			Collection<Class<? extends Status<?, ?>>> statuses) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BytesDataWriter createBytesDataWriter(Topic<byte[]> topic,
			String qosLibraryName, String qosProfileName,
			DataWriterListener<byte[]> listener,
			Collection<Class<? extends Status<?, ?>>> statuses) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KeyedBytesDataWriter createKeyedBytesDataWriter(
			Topic<KeyedBytes> topic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KeyedBytesDataWriter createKeyedBytesDataWriter(
			Topic<KeyedBytes> topic, DataWriterQos qos,
			DataWriterListener<KeyedBytes> listener,
			Collection<Class<? extends Status<?, ?>>> statuses) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KeyedBytesDataWriter createKeyedBytesDataWriter(
			Topic<KeyedBytes> topic, String qosLibraryName,
			String qosProfileName, DataWriterListener<KeyedBytes> listener,
			Collection<Class<? extends Status<?, ?>>> statuses) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StringDataWriter createStringDataWriter(Topic<String> topic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StringDataWriter createStringDataWriter(Topic<String> topic,
			DataWriterQos qos, DataWriterListener<String> listener,
			Collection<Class<? extends Status<?, ?>>> statuses) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StringDataWriter createStringDataWriter(Topic<String> topic,
			String qosLibraryName, String qosProfileName,
			DataWriterListener<String> listener,
			Collection<Class<? extends Status<?, ?>>> statuses) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KeyedStringDataWriter createKeyedStringDataWriter(
			Topic<KeyedString> topic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KeyedStringDataWriter createKeyedStringDataWriter(
			Topic<KeyedString> topic, DataWriterQos qos,
			DataWriterListener<KeyedString> listener,
			Collection<Class<? extends Status<?, ?>>> statuses) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KeyedStringDataWriter createKeyedStringDataWriter(
			Topic<KeyedString> topic, String qosLibraryName,
			String qosProfileName, DataWriterListener<KeyedString> listener,
			Collection<Class<? extends Status<?, ?>>> statuses) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <TYPE> DataWriter<TYPE> lookupDataWriter(String topicName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <TYPE> DataWriter<TYPE> lookupDataWriter(Topic<TYPE> topicName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BytesDataWriter lookupBytesDataWriter(Topic<byte[]> topicName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KeyedBytesDataWriter lookupKeyedBytesDataWriter(
			Topic<KeyedBytes> topicName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StringDataWriter lookupStringDataWriter(Topic<String> topicName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KeyedStringDataWriter lookupKeyedStringDataWriter(
			Topic<KeyedString> topicName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void closeContainedEntities() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void suspendPublications() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resumePublications() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beginCoherentChanges() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endCoherentChanges() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void waitForAcknowledgments(Duration maxWait)
			throws TimeoutException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void waitForAcknowledgments(long maxWait, TimeUnit unit)
			throws TimeoutException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DataWriterQos getDefaultDataWriterQos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDefaultDataWriterQos(DataWriterQos qos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDefaultDataWriterQos(String qosLibraryName,
			String qosProfileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void copyFromTopicQos(DataWriterQos dst, TopicQos src) {
		// TODO Auto-generated method stub
		
	}
	

}
