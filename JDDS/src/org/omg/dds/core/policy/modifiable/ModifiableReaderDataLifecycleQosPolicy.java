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

package org.omg.dds.core.policy.modifiable;

import java.util.concurrent.TimeUnit;

import org.omg.dds.core.Duration;
import org.omg.dds.core.modifiable.ModifiableDuration;
import org.omg.dds.core.policy.ReaderDataLifecycleQosPolicy;


public interface ModifiableReaderDataLifecycleQosPolicy
extends ReaderDataLifecycleQosPolicy,
        ModifiableQosPolicy<ReaderDataLifecycleQosPolicy,
                            ModifiableReaderDataLifecycleQosPolicy> {
    /**
     * @param autoPurgeNoWriterSamplesDelay the autoPurgeNoWriterSamplesDelay
     *                                      to set
     * 
     * @return  this
     */
    public ModifiableReaderDataLifecycleQosPolicy
    setAutoPurgeNoWriterSamplesDelay(Duration autoPurgeNoWriterSamplesDelay);

    /**
     * @return  this
     */
    public ModifiableReaderDataLifecycleQosPolicy
    setAutoPurgeNoWriterSamplesDelay(
            long autoPurgeNoWriterSamplesDelay, TimeUnit unit);

    public ModifiableDuration getAutoPurgeNoWriterSamplesDelay();

    /**
     * @param autoPurgeDisposedSamplesDelay the autoPurgeDisposedSamplesDelay to set
     * 
     * @return  this
     */
    public ModifiableReaderDataLifecycleQosPolicy
    setAutoPurgeDisposedSamplesDelay(Duration autoPurgeDisposedSamplesDelay);

    /**
     * @return  this
     */
    public ModifiableReaderDataLifecycleQosPolicy
    setAutoPurgeDisposedSamplesDelay(
            long autoPurgeDisposedSamplesDelay, TimeUnit unit);

    public ModifiableDuration getAutoPurgeDisposedSamplesDelay();
}
