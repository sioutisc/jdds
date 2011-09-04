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

package org.omg.example.dds.helloworld;

import org.omg.dds.core.Bootstrap;
import org.omg.dds.core.policy.EntityFactoryQosPolicy;
import org.omg.dds.core.policy.modifiable.ModifiableEntityFactoryQosPolicy;
import org.omg.dds.domain.DomainParticipant;
import org.omg.dds.domain.DomainParticipantFactory;
import org.omg.dds.domain.DomainParticipantQos;
import org.omg.dds.domain.modifiable.ModifiableDomainParticipantQos;


public final class QosExample {
    public static void main(String[] args) {
        DomainParticipantFactory factory =
            DomainParticipantFactory.getInstance(Bootstrap.createInstance());
        DomainParticipant dp = factory.createParticipant();

        // Get unmodifiable QoS for inspection:
        DomainParticipantQos dpqUnmod = dp.getQos();
        EntityFactoryQosPolicy polUnmod = dpqUnmod.getEntityFactory();
        System.out.println(polUnmod);

        // Set QoS:
        ModifiableDomainParticipantQos dpqMod = dpqUnmod.modify();
        ModifiableEntityFactoryQosPolicy polMod = dpqMod.getEntityFactory();
        polMod.setAutoEnableCreatedEntities(false);
        dp.setQos(dpqMod);

        // Concise version:
        dpqMod = dp.getQos().modify();
        dpqMod.getEntityFactory().setAutoEnableCreatedEntities(false);
        dp.setQos(dpqMod);

        // Restore QoS to default:
        dp.setQos(factory.getDefaultParticipantQos());
    }


    private QosExample() {
        // do nothing
    }
}
