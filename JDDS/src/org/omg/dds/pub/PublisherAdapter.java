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

import org.omg.dds.core.status.LivelinessLostStatus;
import org.omg.dds.core.status.OfferedDeadlineMissedStatus;
import org.omg.dds.core.status.OfferedIncompatibleQosStatus;
import org.omg.dds.core.status.PublicationMatchedStatus;


public class PublisherAdapter implements PublisherListener
{
    public void onLivelinessLost(LivelinessLostStatus<?> status)
    {
        // empty
    }

    public void onOfferedDeadlineMissed(
            OfferedDeadlineMissedStatus<?> status)
    {
        // empty
    }

    public void onOfferedIncompatibleQos(
            OfferedIncompatibleQosStatus<?> status)
    {
        // empty
    }

    public void onPublicationMatched(PublicationMatchedStatus<?> status)
    {
        // empty
    }
}
