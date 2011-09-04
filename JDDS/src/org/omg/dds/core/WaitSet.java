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

package org.omg.dds.core;

import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.omg.dds.domain.DomainParticipant;


/**
 * A WaitSet object allows an application to wait until one or more of the
 * attached {@link Condition} objects has a triggerValue of true or else until
 * the timeout expires.
 * 
 * WaitSet is not necessarily associated with a single
 * {@link DomainParticipant} and could be used to wait on Condition objects
 * associated with different DomainParticipant objects.
 */
public abstract class WaitSet implements DDSObject {
    // -----------------------------------------------------------------------
    // Factory Methods
    // -----------------------------------------------------------------------

    /**
     * @param bootstrap Identifies the Service instance to which the new
     *                  object will belong.
     */
    public static WaitSet newWaitSet(Bootstrap bootstrap) {
        return bootstrap.getSPI().newWaitSet();
    }



    // -----------------------------------------------------------------------
    // Instance Methods
    // -----------------------------------------------------------------------

    public abstract void waitForConditions() throws TimeoutException;

    public abstract void waitForConditions(
            Collection<Condition> activeConditions)
    throws TimeoutException;

    public abstract void waitForConditions(Duration timeout)
    throws TimeoutException;

    public abstract void waitForConditions(long timeout, TimeUnit unit)
    throws TimeoutException;

    public abstract void waitForConditions(
            Collection<Condition> activeConditions,
            Duration timeout)
    throws TimeoutException;

    public abstract void waitForConditions(
            Collection<Condition> activeConditions,
            long timeout,
            TimeUnit unit)
    throws TimeoutException;

    public abstract void attachCondition(Condition cond);
    public abstract void detachCondition(Condition cond);

    /**
     * @return  an unmodifiable collection of the conditions attached to this
     *          wait set.
     */
    public abstract Collection<Condition> getConditions();
}
