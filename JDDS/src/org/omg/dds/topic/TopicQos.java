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

package org.omg.dds.topic;

import org.omg.dds.core.EntityQos;
import org.omg.dds.core.policy.DataRepresentationQosPolicy;
import org.omg.dds.core.policy.DeadlineQosPolicy;
import org.omg.dds.core.policy.DestinationOrderQosPolicy;
import org.omg.dds.core.policy.DurabilityQosPolicy;
import org.omg.dds.core.policy.DurabilityServiceQosPolicy;
import org.omg.dds.core.policy.HistoryQosPolicy;
import org.omg.dds.core.policy.LatencyBudgetQosPolicy;
import org.omg.dds.core.policy.LifespanQosPolicy;
import org.omg.dds.core.policy.LivelinessQosPolicy;
import org.omg.dds.core.policy.OwnershipQosPolicy;
import org.omg.dds.core.policy.ReliabilityQosPolicy;
import org.omg.dds.core.policy.ResourceLimitsQosPolicy;
import org.omg.dds.core.policy.TopicDataQosPolicy;
import org.omg.dds.core.policy.TransportPriorityQosPolicy;
import org.omg.dds.core.policy.TypeConsistencyEnforcementQosPolicy;
import org.omg.dds.topic.modifiable.ModifiableTopicQos;


public interface TopicQos extends EntityQos<TopicQos, ModifiableTopicQos>
{
    /**
     * @return the topicData
     */
    public TopicDataQosPolicy getTopicData();

    /**
     * @return the durability
     */
    public DurabilityQosPolicy getDurability();

    /**
     * @return the durabilityService
     */
    public DurabilityServiceQosPolicy getDurabilityService();

    /**
     * @return the deadline
     */
    public DeadlineQosPolicy getDeadline();

    /**
     * @return the latencyBudget
     */
    public LatencyBudgetQosPolicy getLatencyBudget();

    /**
     * @return the liveliness
     */
    public LivelinessQosPolicy getLiveliness();

    /**
     * @return the reliability
     */
    public ReliabilityQosPolicy getReliability();

    /**
     * @return the destinationOrder
     */
    public DestinationOrderQosPolicy getDestinationOrder();

    /**
     * @return the history
     */
    public HistoryQosPolicy getHistory();

    /**
     * @return the resourceLimits
     */
    public ResourceLimitsQosPolicy getResourceLimits();

    /**
     * @return the transportPriority
     */
    public TransportPriorityQosPolicy getTransportPriority();

    /**
     * @return the lifespan
     */
    public LifespanQosPolicy getLifespan();

    /**
     * @return the ownership
     */
    public OwnershipQosPolicy getOwnership();

    public DataRepresentationQosPolicy getRepresentation();

    public TypeConsistencyEnforcementQosPolicy getTypeConsistency();
}
