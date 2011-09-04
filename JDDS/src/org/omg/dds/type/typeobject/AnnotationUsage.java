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

package org.omg.dds.type.typeobject;

import java.util.List;

import org.omg.dds.core.modifiable.ModifiableValue;
import org.omg.dds.type.Extensibility;
import org.omg.dds.type.Nested;


// The application of an annotation to some type or type member
@Extensibility(Extensibility.Kind.EXTENSIBLE_EXTENSIBILITY)
@Nested
public interface AnnotationUsage
extends ModifiableValue<AnnotationUsage, AnnotationUsage> {
    /**
     * @param typeId the typeId to set
     * 
     * @return  this
     */
    public AnnotationUsage setTypeId(int typeId);

    /**
     * @return the typeId
     */
    public int getTypeId();

    /**
     * @param member the member to set
     * 
     * @return  this
     */
    public AnnotationUsage setMember(List<AnnotationUsageMember> member);

    /**
     * @return the member
     */
    public List<AnnotationUsageMember> getMember();
}
