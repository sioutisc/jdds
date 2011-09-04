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

import org.omg.dds.core.modifiable.ModifiableInstanceHandle;


public abstract class InstanceHandle
implements Value<InstanceHandle, ModifiableInstanceHandle>
{
    // -----------------------------------------------------------------------
    // Private Constants
    // -----------------------------------------------------------------------

    private static final long serialVersionUID = 4987266126475078295L;



    // -----------------------------------------------------------------------
    // Factory Methods
    // -----------------------------------------------------------------------

    /**
     * @param bootstrap Identifies the Service instance to which the new
     *                  object will belong.
     */
    public static ModifiableInstanceHandle newInstanceHandle(
            Bootstrap bootstrap) {
        return bootstrap.getSPI().newInstanceHandle();
    }


    /**
     * @param bootstrap Identifies the Service instance to which the
     *                  object will belong.
     * 
     * @return  An unmodifiable nil instance handle.
     */
    public static InstanceHandle nilHandle(Bootstrap bootstrap) {
        return bootstrap.getSPI().nilHandle();
    }



    // -----------------------------------------------------------------------
    // Instance Methods
    // -----------------------------------------------------------------------

    public abstract boolean isNil();


    // --- From Object: ------------------------------------------------------

    @Override
    public abstract InstanceHandle clone();
}
