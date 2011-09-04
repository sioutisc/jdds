/* Copyright 2010, Object Management Group, Inc.
 * Copyright 2010, PrismTech, Inc.
 * Copyright 2010, Real-Time Innovations, Inc.
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.omg.dds.sub;

import java.util.Collection;

import org.omg.dds.core.DomainEntity;
import org.omg.dds.core.status.Status;
import org.omg.dds.domain.DomainParticipant;
import org.omg.dds.topic.TopicDescription;
import org.omg.dds.topic.TopicQos;
import org.omg.dds.type.builtin.BytesDataReader;
import org.omg.dds.type.builtin.KeyedBytes;
import org.omg.dds.type.builtin.KeyedBytesDataReader;
import org.omg.dds.type.builtin.KeyedString;
import org.omg.dds.type.builtin.KeyedStringDataReader;
import org.omg.dds.type.builtin.StringDataReader;


public interface Subscriber
extends DomainEntity<Subscriber,
                     DomainParticipant,
                     SubscriberListener,
                     SubscriberQos>
{
    // --- Create (any) DataReader: ------------------------------------------

    public <TYPE> DataReader<TYPE> createDataReader(
            TopicDescription<TYPE> topic);

    /**
     * Create a new data reader.
     * 
     * @param statuses  Of which status changes the listener should be
     *                  notified. A null collection signifies all status
     *                  changes.
     */
    public <TYPE> DataReader<TYPE> createDataReader(
            TopicDescription<TYPE> topic,
            DataReaderQos qos,
            DataReaderListener<TYPE> listener,
            Collection<Class<? extends Status<?, ?>>> statuses);

    /**
     * Create a new data reader.
     * 
     * @param statuses  Of which status changes the listener should be
     *                  notified. A null collection signifies all status
     *                  changes.
     */
    public <TYPE> DataReader<TYPE> createDataReader(
            TopicDescription<TYPE> topic,
            String qosLibraryName,
            String qosProfileName,
            DataReaderListener<TYPE> listener,
            Collection<Class<? extends Status<?, ?>>> statuses);


    // --- Create DataReader of built-in bytes type: -------------------------

    public BytesDataReader createBytesDataReader(
            TopicDescription<byte[]> topic);

    /**
     * Create a new data reader.
     * 
     * @param statuses  Of which status changes the listener should be
     *                  notified. A null collection signifies all status
     *                  changes.
     */
    public BytesDataReader createBytesDataReader(
            TopicDescription<byte[]> topic,
            DataReaderQos qos,
            DataReaderListener<byte[]> listener,
            Collection<Class<? extends Status<?, ?>>> statuses);

    /**
     * Create a new data reader.
     * 
     * @param statuses  Of which status changes the listener should be
     *                  notified. A null collection signifies all status
     *                  changes.
     */
    public BytesDataReader createBytesDataReader(
            TopicDescription<byte[]> topic,
            String qosLibraryName,
            String qosProfileName,
            DataReaderListener<byte[]> listener,
            Collection<Class<? extends Status<?, ?>>> statuses);


    // --- Create DataReader of built-in KeyedBytes type: --------------------

    public KeyedBytesDataReader createKeyedBytesDataReader(
            TopicDescription<KeyedBytes> topic);

    /**
     * Create a new data reader.
     * 
     * @param statuses  Of which status changes the listener should be
     *                  notified. A null collection signifies all status
     *                  changes.
     */
    public KeyedBytesDataReader createKeyedBytesDataReader(
            TopicDescription<KeyedBytes> topic,
            DataReaderQos qos,
            DataReaderListener<KeyedBytes> listener,
            Collection<Class<? extends Status<?, ?>>> statuses);

    /**
     * Create a new data reader.
     * 
     * @param statuses  Of which status changes the listener should be
     *                  notified. A null collection signifies all status
     *                  changes.
     */
    public KeyedBytesDataReader createKeyedBytesDataReader(
            TopicDescription<KeyedBytes> topic,
            String qosLibraryName,
            String qosProfileName,
            DataReaderListener<KeyedBytes> listener,
            Collection<Class<? extends Status<?, ?>>> statuses);


    // --- Create DataReader of built-in string type: ------------------------

    public StringDataReader createStringDataReader(
            TopicDescription<String> topic);

    /**
     * Create a new data reader.
     * 
     * @param statuses  Of which status changes the listener should be
     *                  notified. A null collection signifies all status
     *                  changes.
     */
    public StringDataReader createStringDataReader(
            TopicDescription<String> topic,
            DataReaderQos qos,
            DataReaderListener<String> listener,
            Collection<Class<? extends Status<?, ?>>> statuses);

    /**
     * Create a new data reader.
     * 
     * @param statuses  Of which status changes the listener should be
     *                  notified. A null collection signifies all status
     *                  changes.
     */
    public StringDataReader createStringDataReader(
            TopicDescription<String> topic,
            String qosLibraryName,
            String qosProfileName,
            DataReaderListener<String> listener,
            Collection<Class<? extends Status<?, ?>>> statuses);


    // --- Create DataReader of built-in KeyedString type: -------------------

    public KeyedStringDataReader createKeyedStringDataReader(
            TopicDescription<KeyedString> topic);

    /**
     * Create a new data reader.
     * 
     * @param statuses  Of which status changes the listener should be
     *                  notified. A null collection signifies all status
     *                  changes.
     */
    public KeyedStringDataReader createKeyedStringDataReader(
            TopicDescription<KeyedString> topic,
            DataReaderQos qos,
            DataReaderListener<KeyedString> listener,
            Collection<Class<? extends Status<?, ?>>> statuses);

    /**
     * Create a new data reader.
     * 
     * @param statuses  Of which status changes the listener should be
     *                  notified. A null collection signifies all status
     *                  changes.
     */
    public KeyedStringDataReader createKeyedStringDataReader(
            TopicDescription<KeyedString> topic,
            String qosLibraryName,
            String qosProfileName,
            DataReaderListener<KeyedString> listener,
            Collection<Class<? extends Status<?, ?>>> statuses);


    // --- Lookup operations: ------------------------------------------------

    public <TYPE> DataReader<TYPE> lookupDataReader(String topicName);
    public <TYPE> DataReader<TYPE> lookupDataReader(
            TopicDescription<TYPE> topicName);

    public BytesDataReader lookupBytesDataReader(
            TopicDescription<byte[]> topicName);
    public KeyedBytesDataReader lookupKeyedBytesDataReader(
            TopicDescription<KeyedBytes> topicName);
    public StringDataReader lookupStringDataReader(
            TopicDescription<String> topicName);
    public KeyedStringDataReader lookupKeyedStringDataReader(
            TopicDescription<KeyedString> topicName);


    // --- Other operations: -------------------------------------------------

    public void closeContainedEntities();

    public Collection<DataReader<?>> getDataReaders(
            Collection<DataReader<?>> readers);
    public Collection<DataReader<?>> getDataReaders(
            Collection<DataReader<?>> readers,
            Collection<SampleState> sampleStates,
            Collection<ViewState> viewStates,
            Collection<InstanceState> instanceStates);

    public void notifyDataReaders();

    public void beginAccess();
    public void endAccess();

    public DataReaderQos getDefaultDataReaderQos();
    public void setDefaultDataReaderQos(DataReaderQos qos);
    public void setDefaultDataReaderQos(
            String qosLibraryName,
            String qosProfileName);

    public void copyFromTopicQos(DataReaderQos dst, TopicQos src);
}
