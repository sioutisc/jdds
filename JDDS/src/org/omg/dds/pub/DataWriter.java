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
import org.omg.dds.core.InstanceHandle;
import org.omg.dds.core.Time;
import org.omg.dds.core.modifiable.ModifiableInstanceHandle;
import org.omg.dds.core.status.LivelinessLostStatus;
import org.omg.dds.core.status.OfferedDeadlineMissedStatus;
import org.omg.dds.core.status.OfferedIncompatibleQosStatus;
import org.omg.dds.core.status.PublicationMatchedStatus;
import org.omg.dds.topic.SubscriptionBuiltinTopicData;
import org.omg.dds.topic.Topic;


public interface DataWriter<TYPE>
extends DomainEntity<DataWriter<TYPE>,
                     Publisher,
                     DataWriterListener<TYPE>,
                     DataWriterQos> {
    /**
     * @return  the type parameter if this object's class.
     */
    public Class<TYPE> getType();

    /**
     * Cast this data writer to the given type, or throw an exception if
     * the cast fails.
     * 
     * @param <OTHER>   The type of the data published by this writer,
     *                  according to the caller.
     * @return          this data writer
     * @throws          ClassCastException if the cast fails
     */
    public <OTHER> DataWriter<OTHER> cast();

    public Topic<TYPE> getTopic();

    public void waitForAcknowledgments(Duration maxWait)
    throws TimeoutException;

    public void waitForAcknowledgments(long maxWait, TimeUnit unit)
    throws TimeoutException;

    public LivelinessLostStatus<TYPE> getLivelinessLostStatus(
            LivelinessLostStatus<TYPE> status);

    public OfferedDeadlineMissedStatus<TYPE> getOfferedDeadlineMissedStatus(
            OfferedDeadlineMissedStatus<TYPE> status);

    public OfferedIncompatibleQosStatus<TYPE> getOfferedIncompatibleQosStatus(
            OfferedIncompatibleQosStatus<TYPE> status);

    public PublicationMatchedStatus<TYPE> getPublicationMatchedStatus(
            PublicationMatchedStatus<TYPE> status);

    public void assertLiveliness();

    public Collection<InstanceHandle> getMatchedSubscriptions(
            Collection<InstanceHandle> subscriptionHandles);
    public SubscriptionBuiltinTopicData getMatchedSubscriptionData(
            SubscriptionBuiltinTopicData subscriptionData,
            InstanceHandle subscriptionHandle);


    // --- Type-specific interface: ------------------------------------------
    public InstanceHandle registerInstance(
            TYPE instanceData) throws TimeoutException;
    public InstanceHandle registerInstance(
            TYPE instanceData, 
            Time sourceTimestamp) throws TimeoutException;
    public InstanceHandle registerInstance(
            TYPE instanceData, 
            long sourceTimestamp,
            TimeUnit unit) throws TimeoutException;

    public void unregisterInstance(
            InstanceHandle handle) throws TimeoutException;
    public void unregisterInstance(
            InstanceHandle handle, 
            TYPE instanceData) throws TimeoutException;
    public void unregisterInstance(
            InstanceHandle handle, 
            TYPE instanceData,
            Time sourceTimestamp) throws TimeoutException;
    public void unregisterInstance(
            InstanceHandle handle, 
            TYPE instanceData,
            long sourceTimestamp,
            TimeUnit unit) throws TimeoutException;

    public void write(
            TYPE instanceData) throws TimeoutException;
    public void write(
            TYPE instanceData, 
            Time sourceTimestamp) throws TimeoutException;
    public void write(
            TYPE instanceData, 
            long sourceTimestamp,
            TimeUnit unit) throws TimeoutException;
    public void write(
            TYPE instanceData, 
            InstanceHandle handle) throws TimeoutException;
    public void write(
            TYPE instanceData, 
            InstanceHandle handle,
            Time sourceTimestamp) throws TimeoutException;
    public void write(
            TYPE instanceData, 
            InstanceHandle handle,
            long sourceTimestamp,
            TimeUnit unit) throws TimeoutException;

    public void dispose(
            InstanceHandle instanceHandle) throws TimeoutException;
    public void dispose(
            InstanceHandle instanceHandle, 
            TYPE instanceData) throws TimeoutException;
    public void dispose(
            InstanceHandle instanceHandle, 
            TYPE instanceData,
            Time sourceTimestamp) throws TimeoutException;
    public void dispose(
            InstanceHandle instanceHandle, 
            TYPE instanceData,
            long sourceTimestamp,
            TimeUnit unit) throws TimeoutException;

    public TYPE getKeyValue(
            TYPE keyHolder, 
            InstanceHandle handle);

    public ModifiableInstanceHandle lookupInstance(
            ModifiableInstanceHandle handle,
            TYPE keyHolder);
}
