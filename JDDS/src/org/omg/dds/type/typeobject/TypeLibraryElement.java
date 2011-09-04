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
import org.omg.dds.type.BitBound;
import org.omg.dds.type.Extensibility;
import org.omg.dds.type.Nested;
import org.omg.dds.type.TypeKind;
import org.omg.dds.type.Value;


@Extensibility(Extensibility.Kind.MUTABLE_EXTENSIBILITY)
@Nested
public interface TypeLibraryElement
extends ModifiableValue<TypeLibraryElement, TypeLibraryElement>
{
    // -----------------------------------------------------------------------
    // Properties
    // -----------------------------------------------------------------------

    public Kind discriminator();


    public AliasType getAliasType();

    /**
     * @return  this
     */
    public TypeLibraryElement setAliasType(AliasType value);


    public AnnotationType getAnnotationType();

    /**
     * @return  this
     */
    public TypeLibraryElement setAnnotationType(AnnotationType value);


    public ArrayType getArrayType();

    /**
     * @return  this
     */
    public TypeLibraryElement setArrayType(ArrayType value);


    public BitSetType getBitsetType();

    /**
     * @return  this
     */
    public TypeLibraryElement setBitsetType(BitSetType value);


    public EnumerationType getEnumerationType();

    /**
     * @return  this
     */
    public TypeLibraryElement setEnumerationType(EnumerationType value);


    public MapType getMapType();

    /**
     * @return  this
     */
    public TypeLibraryElement setMapType(MapType value);


    public SequenceType getSequenceType();

    /**
     * @return  this
     */
    public TypeLibraryElement setSequenceType(SequenceType value);


    public StringType getStringType();

    /**
     * @return  this
     */
    public TypeLibraryElement setStringType(StringType value);


    public StructureType getStructureType();

    /**
     * @return  this
     */
    public TypeLibraryElement setStructureType(StructureType value);


    public UnionType getUnionType();

    /**
     * @return  this
     */
    public TypeLibraryElement setUnionType(UnionType value);


    public Module getModule();

    /**
     * @return  this
     */
    public TypeLibraryElement setModule(Module value);



    // -----------------------------------------------------------------------
    // Types
    // -----------------------------------------------------------------------

    @BitBound(16)
    public static enum Kind
    {
        // --- Constants: ----------------------------------------------------
        @Value(TypeKind.Values.ALIAS_TYPE_VALUE)
        ALIAS_TYPE_ELEMENT      (TypeKind.ALIAS_TYPE.value),

        @Value(TypeKind.Values.ANNOTATION_TYPE_VALUE)
        ANNOTATION_TYPE_ELEMENT (TypeKind.ANNOTATION_TYPE.value),

        @Value(TypeKind.Values.ARRAY_TYPE_VALUE)
        ARRAY_TYPE_ELEMENT      (TypeKind.ARRAY_TYPE.value),

        @Value(TypeKind.Values.BITSET_TYPE_VALUE)
        BITSET_TYPE_ELEMENT     (TypeKind.BITSET_TYPE.value),

        @Value(TypeKind.Values.ENUMERATION_TYPE_VALUE)
        ENUMERATION_TYPE_ELEMENT(TypeKind.ENUMERATION_TYPE.value),

        @Value(TypeKind.Values.MAP_TYPE_VALUE)
        MAP_TYPE_ELEMENT        (TypeKind.MAP_TYPE.value),

        @Value(TypeKind.Values.SEQUENCE_TYPE_VALUE)
        SEQUENCE_TYPE_ELEMENT   (TypeKind.SEQUENCE_TYPE.value),

        @Value(TypeKind.Values.STRING_TYPE_VALUE)
        STRING_TYPE_ELEMENT     (TypeKind.STRING_TYPE.value),

        @Value(TypeKind.Values.STRUCTURE_TYPE_VALUE)
        STRUCTURE_TYPE_ELEMENT  (TypeKind.STRUCTURE_TYPE.value),

        @Value(TypeKind.Values.UNION_TYPE_VALUE)
        UNION_TYPE_ELEMENT      (TypeKind.UNION_TYPE.value),

        @Value(TypeKind.Values.UNION_TYPE_VALUE + 1)
        MODULE_ELEMENT      ((short) (TypeKind.UNION_TYPE.value + 1)),
        ;


        // --- Fields: -------------------------------------------------------
        public final short value;


        // --- Constructor: --------------------------------------------------
        private Kind(short value) {
            this.value = value;
        }
    }
}
