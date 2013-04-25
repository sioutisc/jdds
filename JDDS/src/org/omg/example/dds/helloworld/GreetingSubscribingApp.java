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

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.omg.dds.core.InstanceHandle;
import org.omg.dds.core.ServiceEnvironment;
import org.omg.dds.core.event.DataAvailableEvent;
import org.omg.dds.domain.DomainParticipant;
import org.omg.dds.domain.DomainParticipantFactory;
import org.omg.dds.sub.DataReader;
import org.omg.dds.sub.DataReaderAdapter;
import org.omg.dds.sub.DataReaderListener;
import org.omg.dds.sub.Sample;
import org.omg.dds.sub.Subscriber;
import org.omg.dds.topic.Topic;


public class GreetingSubscribingApp {
    public static void main(String[] args) {
        ServiceEnvironment env = ServiceEnvironment.createInstance(
                GreetingSubscribingApp.class.getClassLoader());
        DomainParticipantFactory factory =
                DomainParticipantFactory.getInstance(env);
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

        Subscriber sub = dp.createSubscriber();
        DataReaderListener<Greeting> ls = new MyListener();
        DataReader<Greeting> dr = sub.createDataReader(
                tp,
                sub.getDefaultDataReaderQos(),
                ls,
                null /* all status changes */);

        try {
            dr.waitForHistoricalData(10, TimeUnit.SECONDS);
        } catch (TimeoutException tx) {
            tx.printStackTrace();
        }

        dp.close();
    }


    private GreetingSubscribingApp() {
        // do nothing
    }



    // -----------------------------------------------------------------------
    // Types
    // -----------------------------------------------------------------------

    private static class MyListener extends DataReaderAdapter<Greeting>
    {
        @Override
        public void onDataAvailable(DataAvailableEvent<Greeting> evt)
        {
            DataReader<Greeting> dr = evt.getSource();
            Sample.Iterator<Greeting> it = dr.take();
            try {
                while (it.hasNext()) {
                    Sample<Greeting> smp = it.next();
                    // SampleInfo stuff is built into Sample:
                    InstanceHandle inst = smp.getInstanceHandle();
                    System.out.println("Instance: " + inst);
                    // Data accessible from Sample; null if invalid:
                    Greeting dt = smp.getData();
                    System.out.println("Data: " + dt);
                }
            } finally {
        		try {
        			it.close();
        		}
        		catch (java.io.IOException e) {
        	    	System.out.println(e);	
        		}
            }
        }
    }
}
