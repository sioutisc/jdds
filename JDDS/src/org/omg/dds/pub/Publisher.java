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

package org.omg.dds.pub;

import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.omg.dds.core.DomainEntity;
import org.omg.dds.core.Duration;
import org.omg.dds.core.status.Status;
import org.omg.dds.domain.DomainParticipant;
import org.omg.dds.topic.Topic;
import org.omg.dds.topic.TopicQos;
import org.omg.dds.type.builtin.BytesDataWriter;
import org.omg.dds.type.builtin.KeyedBytes;
import org.omg.dds.type.builtin.KeyedBytesDataWriter;
import org.omg.dds.type.builtin.KeyedString;
import org.omg.dds.type.builtin.KeyedStringDataWriter;
import org.omg.dds.type.builtin.StringDataWriter;


public interface Publisher
extends DomainEntity<Publisher,
                     DomainParticipant,
                     PublisherListener,
                     PublisherQos>
{
    // --- Create (any) DataWriter: ------------------------------------------

    public <TYPE> DataWriter<TYPE> createDataWriter(
            Topic<TYPE> topic);

    /**
     * Create a new data writer.
     * 
     * @param statuses  Of which status changes the listener should be
     *                  notified. A null collection signifies all status
     *                  changes.
     */
    public <TYPE> DataWriter<TYPE> createDataWriter(
            Topic<TYPE> topic,
            DataWriterQos qos,
            DataWriterListener<TYPE> listener,
            Collection<Class<? extends Status<?, ?>>> statuses);

    /**
     * Create a new data writer.
     * 
     * @param statuses  Of which status changes the listener should be
     *                  notified. A null collection signifies all status
     *                  changes.
     */
    public <TYPE> DataWriter<TYPE> createDataWriter(
            Topic<TYPE> topic,
            String qosLibraryName,
            String qosProfileName,
            DataWriterListener<TYPE> listener,
            Collection<Class<? extends Status<?, ?>>> statuses);


    // --- Create DataWriter for built-in bytes type: ------------------------

    public BytesDataWriter createBytesDataWriter(
            Topic<byte[]> topic);

    /**
     * Create a new data writer.
     * 
     * @param statuses  Of which status changes the listener should be
     *                  notified. A null collection signifies all status
     *                  changes.
     */
    public BytesDataWriter createBytesDataWriter(
            Topic<byte[]> topic,
            DataWriterQos qos,
            DataWriterListener<byte[]> listener,
            Collection<Class<? extends Status<?, ?>>> statuses);

    /**
     * Create a new data writer.
     * 
     * @param statuses  Of which status changes the listener should be
     *                  notified. A null collection signifies all status
     *                  changes.
     */
    public BytesDataWriter createBytesDataWriter(
            Topic<byte[]> topic,
            String qosLibraryName,
            String qosProfileName,
            DataWriterListener<byte[]> listener,
            Collection<Class<? extends Status<?, ?>>> statuses);


    // --- Create DataWriter for built-in KeyedBytes type: -------------------

    public KeyedBytesDataWriter createKeyedBytesDataWriter(
            Topic<KeyedBytes> topic);

    /**
     * Create a new data writer.
     * 
     * @param statuses  Of which status changes the listener should be
     *                  notified. A null collection signifies all status
     *                  changes.
     */
    public KeyedBytesDataWriter createKeyedBytesDataWriter(
            Topic<KeyedBytes> topic,
            DataWriterQos qos,
            DataWriterListener<KeyedBytes> listener,
            Collection<Class<? extends Status<?, ?>>> statuses);

    /**
     * Create a new data writer.
     * 
     * @param statuses  Of which status changes the listener should be
     *                  notified. A null collection signifies all status
     *                  changes.
     */
    public KeyedBytesDataWriter createKeyedBytesDataWriter(
            Topic<KeyedBytes> topic,
            String qosLibraryName,
            String qosProfileName,
            DataWriterListener<KeyedBytes> listener,
            Collection<Class<? extends Status<?, ?>>> statuses);


    // --- Create DataWriter for built-in string type: -----------------------

    public StringDataWriter createStringDataWriter(
            Topic<String> topic);

    /**
     * Create a new data writer.
     * 
     * @param statuses  Of which status changes the listener should be
     *                  notified. A null collection signifies all status
     *                  changes.
     */
    public StringDataWriter createStringDataWriter(
            Topic<String> topic,
            DataWriterQos qos,
            DataWriterListener<String> listener,
            Collection<Class<? extends Status<?, ?>>> statuses);

    /**
     * Create a new data writer.
     * 
     * @param statuses  Of which status changes the listener should be
     *                  notified. A null collection signifies all status
     *                  changes.
     */
    public StringDataWriter createStringDataWriter(
            Topic<String> topic,
            String qosLibraryName,
            String qosProfileName,
            DataWriterListener<String> listener,
            Collection<Class<? extends Status<?, ?>>> statuses);


    // --- Create DataWriter for built-in KeyedString type: ------------------

    public KeyedStringDataWriter createKeyedStringDataWriter(
            Topic<KeyedString> topic);

    /**
     * Create a new data writer.
     * 
     * @param statuses  Of which status changes the listener should be
     *                  notified. A null collection signifies all status
     *                  changes.
     */
    public KeyedStringDataWriter createKeyedStringDataWriter(
            Topic<KeyedString> topic,
            DataWriterQos qos,
            DataWriterListener<KeyedString> listener,
            Collection<Class<? extends Status<?, ?>>> statuses);

    /**
     * Create a new data writer.
     * 
     * @param statuses  Of which status changes the listener should be
     *                  notified. A null collection signifies all status
     *                  changes.
     */
    public KeyedStringDataWriter createKeyedStringDataWriter(
            Topic<KeyedString> topic,
            String qosLibraryName,
            String qosProfileName,
            DataWriterListener<KeyedString> listener,
            Collection<Class<? extends Status<?, ?>>> statuses);


    // --- Lookup operations: ------------------------------------------------

    public <TYPE> DataWriter<TYPE> lookupDataWriter(String topicName);
    public <TYPE> DataWriter<TYPE> lookupDataWriter(Topic<TYPE> topicName);

    public BytesDataWriter lookupBytesDataWriter(Topic<byte[]> topicName);
    public KeyedBytesDataWriter lookupKeyedBytesDataWriter(
            Topic<KeyedBytes> topicName);
    public StringDataWriter lookupStringDataWriter(Topic<String> topicName);
    public KeyedStringDataWriter lookupKeyedStringDataWriter(
            Topic<KeyedString> topicName);


    // --- Other operations: -------------------------------------------------

    public void closeContainedEntities();

    public void suspendPublications();
    public void resumePublications();

    public void beginCoherentChanges();
    public void endCoherentChanges();

    public void waitForAcknowledgments(Duration maxWait)
    throws TimeoutException;

    public void waitForAcknowledgments(long maxWait, TimeUnit unit)
    throws TimeoutException;

    public DataWriterQos getDefaultDataWriterQos();
    public void setDefaultDataWriterQos(DataWriterQos qos);
    public void setDefaultDataWriterQos(
            String qosLibraryName,
            String qosProfileName);

    public void copyFromTopicQos(DataWriterQos dst, TopicQos src);
}
