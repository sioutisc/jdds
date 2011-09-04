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

import org.omg.dds.core.status.Status;


/**
 * A StatusCondition object is a specific Condition that is associated with
 * each {@link Entity}. The triggerValue of the StatusCondition depends on
 * the communication status of that entity (e.g., arrival of data, loss of
 * information, etc.), "filtered" by the set of enabledStatuses on the
 * StatusCondition.
 * 
 * @param <ENTITY>      The type of the entity with which this condition is
 *                      associated.
 */
public interface StatusCondition<ENTITY extends Entity<ENTITY, ?, ?>>
extends Condition {
    public Collection<Class<? extends Status<?, ?>>> getEnabledStatuses(
            Collection<Class<? extends Status<?, ?>>> statuses);

    /**
     * @param statuses  For which status changes the condition should trigger.
     *                  A null collection signifies all status changes.
     */
    public void setEnabledStatuses(
            Collection<Class<? extends Status<?, ?>>> statuses);

    public ENTITY getEntity();
}
