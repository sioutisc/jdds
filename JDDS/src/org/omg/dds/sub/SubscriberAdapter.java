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

import org.omg.dds.core.status.DataAvailableStatus;
import org.omg.dds.core.status.DataOnReadersStatus;
import org.omg.dds.core.status.LivelinessChangedStatus;
import org.omg.dds.core.status.RequestedDeadlineMissedStatus;
import org.omg.dds.core.status.RequestedIncompatibleQosStatus;
import org.omg.dds.core.status.SampleLostStatus;
import org.omg.dds.core.status.SampleRejectedStatus;
import org.omg.dds.core.status.SubscriptionMatchedStatus;


public class SubscriberAdapter implements SubscriberListener
{
    public void onDataAvailable(DataAvailableStatus<?> status)
    {
        // empty
    }

    public void onLivelinessChanged(LivelinessChangedStatus<?> status)
    {
        // empty
    }

    public void onRequestedDeadlineMissed(
            RequestedDeadlineMissedStatus<?> status)
    {
        // empty
    }

    public void onRequestedIncompatibleQos(
            RequestedIncompatibleQosStatus<?> status)
    {
        // empty
    }

    public void onSampleLost(SampleLostStatus<?> status)
    {
        // empty
    }

    public void onSampleRejected(SampleRejectedStatus<?> status)
    {
        // empty
    }

    public void onSubscriptionMatched(SubscriptionMatchedStatus<?> status)
    {
        // empty
    }

    public void onDataOnReaders(DataOnReadersStatus status)
    {
        // empty
    }
}
