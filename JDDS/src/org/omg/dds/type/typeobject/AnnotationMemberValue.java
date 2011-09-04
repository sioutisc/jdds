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

import java.math.BigDecimal;

import org.omg.dds.core.modifiable.ModifiableValue;
import org.omg.dds.type.Extensibility;
import org.omg.dds.type.Nested;
import org.omg.dds.type.TypeKind;


/* Literal value of an annotation member: either the default value in its
 * definition or the value applied in its usage.
 */
@Extensibility(Extensibility.Kind.EXTENSIBLE_EXTENSIBILITY)
@Nested
public interface AnnotationMemberValue
extends ModifiableValue<AnnotationMemberValue, AnnotationMemberValue>
{
    public TypeKind discriminator();


    public boolean booleanValue();

    public void booleanValue(boolean value);


    public byte byteValue();

    public void byteValue(byte value);


    public short int16Value();

    public void int16Value(short value);


    public short uint16Value();

    public void uint16Value(short value);


    public int int32Value();

    public void int32Value(int value);


    public int uint32Value();

    public void uint32Value(int value);


    public long int64Value();

    public abstract void int64Value(long value);


    public long uint64Value();

    public void uint64Value(long value);


    public float float32Value();

    public void float32Value(float value);


    public double float64Value();

    public void float64Value(double value);


    public BigDecimal float128Value();

    public void float128Value(BigDecimal value);


    public char characterValue();

    public void characterValue(char value);


    public char wideCharacterValue();

    public void wideCharacterValue(char value);


    public int enumerationValue();

    public void enumerationValue(int value);


    public String stringValue();

    public void stringValue(String value);
}
