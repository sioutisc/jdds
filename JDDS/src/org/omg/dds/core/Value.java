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

import java.io.Serializable;


/**
 * Implementing classes have value semantics: they can be deeply copied, and
 * equality is determined based on their contents, not on their object
 * identity.
 */
public interface Value<UNMOD_SELF extends Value<UNMOD_SELF, MOD_SELF>,
                       MOD_SELF extends UNMOD_SELF>
extends DDSObject, Cloneable, Serializable
{
    // --- From Object: ------------------------------------------------------

    /**
     * Implementing classes should override <code>equals()</code>.
     */
    @Override
    public boolean equals(Object other);

    /**
     * Implementing classes should override <code>hashCode()</code>.
     */
    @Override
    public int hashCode();

    /**
     * Extends the concept of "cloneable" defined in <code>java.lang</code> by
     * providing an explicit public {@link #clone()} method.
     * 
     * @return  a new object that with state identical to that of this object.
     */
    public UNMOD_SELF clone();


    // --- Conversion: -------------------------------------------------------

    /**
     * If this value type is of a modifiable subtype, return this.
     * If this value type has a modifiable subtype, return a new object
     * of that type that is a modifiable copy of this object.
     * Otherwise, return null.
     * 
     * @return  <code>this</code>, a new modifiable copy of <code>this</code>,
     *          or <code>null</code>.
     */
    public MOD_SELF modify();
}
