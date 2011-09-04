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

import org.omg.dds.core.modifiable.ModifiableValue;
import org.omg.dds.type.Extensibility;
import org.omg.dds.type.Nested;


@Extensibility(Extensibility.Kind.EXTENSIBLE_EXTENSIBILITY)
@Nested
public interface TypeProperty
extends ModifiableValue<TypeProperty, TypeProperty>
{
    /**
     * @param flag the flag to set
     * 
     * @return  this
     */
    public TypeProperty setFlag(TypeFlag flag);

    /**
     * @return the flag
     */
    public TypeFlag getFlag();

    /**
     * @param typeId the typeId to set
     * 
     * @return  this
     */
    public TypeProperty setTypeId(int typeId);

    /**
     * @return the typeId
     */
    public int getTypeId();

    /**
     * @param name the name to set
     * 
     * @return  this
     */
    public TypeProperty setName(String name);

    /**
     * @return the name
     */
    public String getName();
}
