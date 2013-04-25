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

import java.util.concurrent.TimeoutException;

import org.omg.dds.core.ServiceEnvironment;
import org.omg.dds.domain.DomainParticipant;
import org.omg.dds.domain.DomainParticipantFactory;
import org.omg.dds.pub.DataWriter;
import org.omg.dds.pub.Publisher;
import org.omg.dds.topic.Topic;
import org.omg.dds.type.TypeSupport;


public class GreetingPublishingApp {
    public static void main(String[] args) {
        ServiceEnvironment env = ServiceEnvironment.createInstance(
                GreetingPublishingApp.class.getClassLoader());
        DomainParticipantFactory factory =
                DomainParticipantFactory.getInstance(env);
        DomainParticipant dp = factory.createParticipant();

        // Implicitly register type:
        TypeSupport<Greeting> greetingType = TypeSupport.newTypeSupport(
                Greeting.class,
                env);
        Topic<Greeting> tp = dp.createTopic("My Topic", greetingType);

        Publisher pub = dp.createPublisher();
        DataWriter<Greeting> dw = pub.createDataWriter(tp);

        try {
            dw.write(greetingType.newData());
        } catch (TimeoutException tx) {
            tx.printStackTrace();
        }

        dp.close();
    }


    private GreetingPublishingApp() {
        // do nothing
    }
}
