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

import java.util.EventListener;

import org.omg.dds.domain.DomainParticipant;


/**
 * DomainEntity is the abstract base class for all DCPS entities, except for
 * the {@link DomainParticipant}. Its sole purpose is to express that
 * DomainParticipant is a special kind of Entity, which acts as a container
 * of all other Entity, but itself cannot contain other DomainParticipant.
 * 
 * @param <SELF>        The most-derived DDS-standard interface implemented
 *                      by this entity.
 * @param <PARENT>      The most-derived DDS-standard interface implemented
 *                      by the entity that creates entities of this type.
 * @param <LISTENER>    The listener interface appropriate for this entity.
 * @param <QOS>         The QoS interface appropriate for this entity.
 */
public interface DomainEntity<SELF extends DomainEntity<SELF,
                                                        PARENT,
                                                        LISTENER,
                                                        QOS>,
                              PARENT extends Entity<?, ?, ?>,
                              LISTENER extends EventListener,
                              QOS extends EntityQos<?, ?>>
extends Entity<SELF, LISTENER, QOS>
{
    /**
     * @return  the factory object that created this entity.
     */
    public PARENT getParent();
}
