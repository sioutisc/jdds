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

import java.util.List;

import org.omg.dds.core.Bootstrap;
import org.omg.dds.core.modifiable.ModifiableValue;
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
import org.omg.dds.type.Extensibility;
import org.omg.dds.type.ID;
import org.omg.dds.type.Key;
import org.omg.dds.type.Optional;
import org.omg.dds.type.typeobject.TypeObject;


@Extensibility(Extensibility.Kind.MUTABLE_EXTENSIBILITY)
public abstract class TopicBuiltinTopicData
implements ModifiableValue<TopicBuiltinTopicData, TopicBuiltinTopicData>
{
    // -----------------------------------------------------------------------
    // Private Constants
    // -----------------------------------------------------------------------

    private static final long serialVersionUID = -3621587724397579935L;



    // -----------------------------------------------------------------------
    // Factory Methods
    // -----------------------------------------------------------------------

    /**
     * @param bootstrap Identifies the Service instance to which the new
     *                  object will belong.
     */
    public static TopicBuiltinTopicData newTopicBuiltinTopicData(
            Bootstrap bootstrap) {
        return bootstrap.getSPI().newTopicBuiltinTopicData();
    }


    // -----------------------------------------------------------------------
    // Instance Methods
    // -----------------------------------------------------------------------

    @ID(0x005A) @Key
    public abstract BuiltinTopicKey getKey();

    /**
     * @return the name
     */
    @ID(0x0005)
    public abstract String getName();

    /**
     * @return the typeName
     */
    @ID(0x0007)
    public abstract String getTypeName();

    @ID(0x0075) @Optional
    public abstract List<String> getEquivalentTypeName();

    @ID(0x0076) @Optional
    public abstract List<String> getBaseTypeName();

    @ID(0x0072) @Optional
    public abstract TypeObject getType();

    /**
     * @return the durability
     */
    @ID(0x001D)
    public abstract DurabilityQosPolicy getDurability();

    /**
     * @return the durabilityService
     */
    @ID(0x001E)
    public abstract DurabilityServiceQosPolicy getDurabilityService();

    /**
     * @return the deadline
     */
    @ID(0x0023)
    public abstract DeadlineQosPolicy getDeadline();

    /**
     * @return the latencyBudget
     */
    @ID(0x0027)
    public abstract LatencyBudgetQosPolicy getLatencyBudget();

    /**
     * @return the liveliness
     */
    @ID(0x001B)
    public abstract LivelinessQosPolicy getLiveliness();

    /**
     * @return the reliability
     */
    @ID(0x001A)
    public abstract ReliabilityQosPolicy getReliability();

    /**
     * @return the transportPriority
     */
    @ID(0x0049)
    public abstract TransportPriorityQosPolicy getTransportPriority();

    /**
     * @return the lifespan
     */
    @ID(0x002B)
    public abstract LifespanQosPolicy getLifespan();

    /**
     * @return the destinationOrder
     */
    @ID(0x0025)
    public abstract DestinationOrderQosPolicy getDestinationOrder();

    /**
     * @return the history
     */
    @ID(0x0040)
    public abstract HistoryQosPolicy getHistory();

    /**
     * @return the resourceLimits
     */
    @ID(0x0041)
    public abstract ResourceLimitsQosPolicy getResourceLimits();

    /**
     * @return the ownership
     */
    @ID(0x001F)
    public abstract OwnershipQosPolicy getOwnership();

    /**
     * @return the topicData
     */
    @ID(0x002E)
    public abstract TopicDataQosPolicy getTopicData();

    @ID(0x0073)
    public abstract DataRepresentationQosPolicy getRepresentation();

    @ID(0x0074)
    public abstract TypeConsistencyEnforcementQosPolicy getTypeConsistency();


    // --- From Object: ------------------------------------------------------

    @Override
    public abstract TopicBuiltinTopicData clone();
}
