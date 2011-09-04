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

package org.omg.dds.pub.modifiable;

import org.omg.dds.core.modifiable.ModifiableEntityQos;
import org.omg.dds.core.policy.EntityFactoryQosPolicy;
import org.omg.dds.core.policy.GroupDataQosPolicy;
import org.omg.dds.core.policy.PartitionQosPolicy;
import org.omg.dds.core.policy.PresentationQosPolicy;
import org.omg.dds.core.policy.modifiable.ModifiableEntityFactoryQosPolicy;
import org.omg.dds.core.policy.modifiable.ModifiableGroupDataQosPolicy;
import org.omg.dds.core.policy.modifiable.ModifiablePartitionQosPolicy;
import org.omg.dds.core.policy.modifiable.ModifiablePresentationQosPolicy;
import org.omg.dds.pub.PublisherQos;


public interface ModifiablePublisherQos
extends PublisherQos,
        ModifiableEntityQos<PublisherQos, ModifiablePublisherQos>
{
    /**
     * @param presentation the presentation to set
     * 
     * @return  this
     */
    public ModifiablePublisherQos setPresentation(
            PresentationQosPolicy presentation);

    /**
     * @return the presentation
     */
    public ModifiablePresentationQosPolicy getPresentation();

    /**
     * @param partition the partition to set
     * 
     * @return  this
     */
    public ModifiablePublisherQos setPartition(PartitionQosPolicy partition);

    /**
     * @return the partition
     */
    public ModifiablePartitionQosPolicy getPartition();

    /**
     * @param groupData the groupData to set
     * 
     * @return  this
     */
    public ModifiablePublisherQos setGroupData(GroupDataQosPolicy groupData);

    /**
     * @return the groupData
     */
    public ModifiableGroupDataQosPolicy getGroupData();

    /**
     * @param entityFactory the entityFactory to set
     * 
     * @return  this
     */
    public ModifiablePublisherQos setEntityFactory(
            EntityFactoryQosPolicy entityFactory);

    /**
     * @return the entityFactory
     */
    public ModifiableEntityFactoryQosPolicy getEntityFactory();

}
