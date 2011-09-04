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

package org.omg.dds.type;

import org.omg.dds.core.Bootstrap;
import org.omg.dds.core.DDSObject;


public abstract class TypeSupport<TYPE> implements DDSObject
{
    // -----------------------------------------------------------------------
    // Factory Methods
    // -----------------------------------------------------------------------

    // --- Types: ------------------------------------------------------------

    /**
     * Create a new TypeSupport object for the given physical type.
     * This method is equivalent to:
     * 
     * <code>newTypeSupport(type, type.getClass().getName(), bootstrap)</code>
     * 
     * @see #newTypeSupport(Class, String, Bootstrap)
     */
    public static <TYPE> TypeSupport<TYPE> newTypeSupport(
            Class<TYPE> type, Bootstrap bootstrap) {
        return newTypeSupport(type, type.getClass().getName(), bootstrap);
    }


    /**
     * Create a new TypeSupport object for the given physical type.
     * The Service will register this type under the given name with any
     * participant with which the TypeSupport is used.
     * 
     * @param <TYPE>    The physical type of all samples read or written by
     *                  any {@link org.omg.dds.sub.DataReader} or
     *                  {@link org.omg.dds.pub.DataWriter} typed by the
     *                  resulting <code>TypeSupport</code>.
     * @param type      The physical type of all samples read or written by
     *                  any {@link org.omg.dds.sub.DataReader} or
     *                  {@link org.omg.dds.pub.DataWriter} typed by the
     *                  resulting <code>TypeSupport</code>.
     * @param registeredName    The logical name under which this type will
     *                          be registered with any
     *                          {@link org.omg.dds.domain.DomainParticipant}
     *                          with which the resulting
     *                          <code>TypeSupport</code> is used.
     * @param bootstrap Identifies the Service instance to which the new
     *                  object will belong.
     * 
     * @return          A new <code>TypeSupport</code> object, which can
     *                  subsequently be used to create one or more
     *                  {@link org.omg.dds.topic.Topic}s.
     * 
     * @see #newTypeSupport(Class, Bootstrap)
     */
    public static <TYPE> TypeSupport<TYPE> newTypeSupport(
            Class<TYPE> type, String registeredName, Bootstrap bootstrap) {
        return bootstrap.getSPI().newTypeSupport(type, registeredName);
    }



    // -----------------------------------------------------------------------
    // Instance Methods
    // -----------------------------------------------------------------------

    public abstract String getTypeName();
}
