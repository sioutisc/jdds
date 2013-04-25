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

import org.omg.dds.core.ServiceEnvironment;
import org.omg.dds.core.policy.EntityFactory;
import org.omg.dds.domain.DomainParticipant;
import org.omg.dds.domain.DomainParticipantFactory;
import org.omg.dds.domain.DomainParticipantQos;


public final class QosExample {
    public static void main(String[] args) {
        ServiceEnvironment env = ServiceEnvironment.createInstance(
                QosExample.class.getClassLoader());
        DomainParticipantFactory factory =
                DomainParticipantFactory.getInstance(env);
        DomainParticipant dp = factory.createParticipant();

        // Get unmodifiable QoS for inspection:
        DomainParticipantQos dpq = dp.getQos();
        EntityFactory pol = dpq.getEntityFactory();
        System.out.println(pol);

        // Set QoS:
        DomainParticipantQos dpqMod = dpq.withPolicy(
                pol.withAutoEnableCreatedEntities(false));
        dp.setQos(dpqMod);

        // Concise version:
        dp.setQos(
            dp.getQos().withPolicy(
                dp.getQos().getEntityFactory().withAutoEnableCreatedEntities(
                    false)));

        // Restore QoS to default:
        dp.setQos(factory.getDefaultParticipantQos());
    }


    private QosExample() {
        // do nothing
    }
}
