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

package org.omg.dds.core.policy;

import org.omg.dds.core.Bootstrap;
import org.omg.dds.core.Entity;
import org.omg.dds.core.Value;
import org.omg.dds.domain.DomainParticipant;
import org.omg.dds.pub.Publisher;
import org.omg.dds.topic.Topic;
import org.omg.dds.type.Extensibility;
import org.omg.dds.type.Nested;


/**
 * This class is the abstract root for all the QoS policies. It provides the
 * basic mechanism for an application to specify quality of service
 * parameters. It has a name (<code>getId().getPolicyName()</code>) that is
 * used to identify uniquely each QoS policy. All concrete QosPolicy classes
 * derive from this root and include a value whose type depends on the
 * concrete QoS policy.
 * 
 * The type of a QosPolicy value may be atomic, such as an integer or float,
 * or compound (a structure). Compound types are used whenever multiple
 * parameters must be set coherently to define a consistent value for a
 * QosPolicy.
 * 
 * Each {@link Entity} can be configured with a collection of QosPolicy.
 * However, any Entity cannot support any QosPolicy. For instance, a
 * {@link DomainParticipant} supports different QosPolicy than a {@link Topic}
 * or a {@link Publisher}.
 * 
 * QosPolicy can be set when the Entity is created, or modified with the
 * {@link Entity#setQos(org.omg.dds.core.EntityQos)} method. Each QosPolicy
 * in collection list is treated independently from the others. This approach
 * has the advantage of being very extensible. However, there may be cases
 * where several policies are in conflict. Consistency checking is performed
 * each time the policies are modified via the
 * {@link Entity#setQos(org.omg.dds.core.EntityQos) }operation.
 * 
 * When a policy is changed after being set to a given value, it is not
 * required that the new value be applied instantaneously; the Service is
 * allowed to apply it after a transition phase. In addition, some QosPolicy
 * have “immutable” semantics meaning that they can only be specified either
 * at Entity creation time or else prior to calling the
 * {@link Entity#enable()} operation on the Entity.
 */
@Extensibility(Extensibility.Kind.EXTENSIBLE_EXTENSIBILITY)
@Nested
public interface QosPolicy<UNMOD_SELF extends QosPolicy<UNMOD_SELF, MOD_SELF>,
                           MOD_SELF extends UNMOD_SELF>
extends Value<UNMOD_SELF, MOD_SELF> {
    // -----------------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------------

    public Id getId();



    // -----------------------------------------------------------------------
    // Types
    // -----------------------------------------------------------------------
    
    public static abstract class Id {
        // --- Factory Methods: ----------------------------------------------
        /**
         * Get the QoS policy ID for the given QoS policy class.
         * 
         * @param bootstrap Identifies the Service instance to which the
         *                  object will belong.
         */
        public static Id getId(
                Class<? extends QosPolicy<?, ?>> policyClass,
                Bootstrap bootstrap) {
            return bootstrap.getSPI().getQosPolicyId(policyClass);
        }

        // --- Instance Methods: ---------------------------------------------
        public abstract int getPolicyIdValue();

        public abstract String getPolicyName();
    }

}
