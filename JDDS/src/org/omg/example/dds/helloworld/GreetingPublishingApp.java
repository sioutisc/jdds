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

import org.omg.dds.core.Bootstrap;
import org.omg.dds.domain.DomainParticipant;
import org.omg.dds.domain.DomainParticipantFactory;
import org.omg.dds.pub.DataWriter;
import org.omg.dds.pub.Publisher;
import org.omg.dds.topic.Topic;


public class GreetingPublishingApp {
    public static void main(String[] args) {
        DomainParticipantFactory factory =
            DomainParticipantFactory.getInstance(Bootstrap.createInstance());
        DomainParticipant dp = factory.createParticipant();

        // Implicitly create TypeSupport and register type:
        Topic<Greeting> tp = dp.createTopic("My Topic", Greeting.class);
        // OR explicitly create TypeSupport, registered with default name:
        // Topic<Greeting> tp = dp.createTopic(
        //         "My Topic",
        //         ctx.createTypeSupport(Greeting.class));
        // OR explicitly create TypeSupport, registered with custom name:
        // Topic<Greeting> tp = dp.createTopic(
        //         "My Topic",
        //         ctx.createTypeSupport(Greeting.class, "MyType"));

        Publisher pub = dp.createPublisher();
        DataWriter<Greeting> dw = pub.createDataWriter(tp);

        try {
            dw.write(new Greeting("Hello, World"));
        } catch (TimeoutException tx) {
            tx.printStackTrace();
        }

        dp.close();
    }


    private GreetingPublishingApp() {
        // do nothing
    }
}
