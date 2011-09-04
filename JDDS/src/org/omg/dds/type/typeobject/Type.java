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
import org.omg.dds.type.ID;
import org.omg.dds.type.Nested;


@Extensibility(Extensibility.Kind.MUTABLE_EXTENSIBILITY)
@Nested
public interface Type extends ModifiableValue<Type, Type>
{
    @ID(MemberId.PROPERTY_TYPE_MEMBER_ID)
    public TypeProperty getProperty();

    /**
     * @return  this
     */
    public Type setProperty(TypeProperty newProperty);

    @ID(MemberId.ANNOTATION_TYPE_MEMBER_ID)
    public List<AnnotationUsage> getAnnotation();

    /**
     * @return  this
     */
    public Type setAnnotation(List<AnnotationUsage> newAnnotation);



    // -----------------------------------------------------------------------
    // Types
    // -----------------------------------------------------------------------

    public static final class MemberId
    {
        // --- Constants: ----------------------------------------------------
        public static final int PROPERTY_TYPE_MEMBER_ID = 0;
        public static final int ANNOTATION_TYPE_MEMBER_ID = 1;

        // --- Constructor: --------------------------------------------------
        private MemberId() {
            // empty
        }
    }
}
