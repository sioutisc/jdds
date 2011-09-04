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
import org.omg.dds.core.policy.ReliabilityQosPolicy;


public interface ModifiableReliabilityQosPolicy
extends ReliabilityQosPolicy,
        ModifiableQosPolicy<ReliabilityQosPolicy,
                            ModifiableReliabilityQosPolicy> {
    /**
     * @param kind the kind to set
     * 
     * @return  this
     */
    public ModifiableReliabilityQosPolicy setKind(Kind kind);

    /**
     * @param maxBlockingTime the maxBlockingTime to set
     * 
     * @return  this
     */
    public ModifiableReliabilityQosPolicy setMaxBlockingTime(
            Duration maxBlockingTime);

    /**
     * @return  this
     */
    public ModifiableReliabilityQosPolicy setMaxBlockingTime(
            long maxBlockingTime, TimeUnit unit);

    public ModifiableDuration getMaxBlockingTime();
}
