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

package org.omg.dds.type.dynamic;

import org.omg.dds.core.Bootstrap;
import org.omg.dds.core.DDSObject;
import org.omg.dds.type.TypeKind;


public abstract class DynamicTypeFactory implements DDSObject
{
    // -----------------------------------------------------------------------
    // Singleton Access
    // -----------------------------------------------------------------------

    /**
     * @param bootstrap Identifies the Service instance to which the
     *                  object will belong.
     */
    public static DynamicTypeFactory getInstance(Bootstrap bootstrap) {
        return bootstrap.getSPI().getTypeFactory();
    }



    // -----------------------------------------------------------------------
    // Instance Methods
    // -----------------------------------------------------------------------

    public abstract DynamicType getPrimitiveType(TypeKind kind);

    public abstract DynamicType createType(TypeDescriptor descriptor);
    public abstract DynamicType createStringType(int bound);
    public abstract DynamicType createWStringType(int bound);
    public abstract DynamicType createSequenceType(
            DynamicType elementType, int bound);
    public abstract DynamicType createArrayType(
            DynamicType elementType, int... bound);
    public abstract DynamicType createMapType(
            DynamicType keyElementType, DynamicType elementType, int bound);
    public abstract DynamicType createBitSetType(int bound);

    public abstract DynamicType loadTypeFromUrl(
            String documentUrl, String typeName, String... includePaths);
    public abstract DynamicType loadTypeFromDocument(
            String document, String typeName, String... includePaths);
}
