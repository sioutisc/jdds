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

package org.omg.dds.core.modifiable;

import java.util.concurrent.TimeUnit;

import org.omg.dds.core.Duration;


public abstract class ModifiableDuration
extends Duration implements ModifiableValue<Duration, ModifiableDuration>
{
    // -----------------------------------------------------------------------
    // Private Constants
    // -----------------------------------------------------------------------

    private static final long serialVersionUID = -5340397683235687863L;



    // -----------------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------------

    // --- Data access: ------------------------------------------------------

    /**
     * @return  this
     */
    public abstract ModifiableDuration setDuration(
            long duration, TimeUnit unit);


    // --- Manipulation: -----------------------------------------------------

    /**
     * Increase this duration by the given amount.
     * 
     * @return  this
     */
    public abstract ModifiableDuration add(Duration duration);

    /**
     * Increase this duration by the given amount.
     * 
     * @return  this
     */
    public abstract ModifiableDuration add(long duration, TimeUnit unit);

    /**
     * Decrease this duration by the given amount.
     * 
     * @return  this
     */
    public abstract ModifiableDuration subtract(Duration duration);

    /**
     * Decrease this duration by the given amount.
     * 
     * @return  this
     */
    public abstract ModifiableDuration subtract(long duration, TimeUnit unit);


    // --- From Object: ------------------------------------------------------

    @Override
    public abstract ModifiableDuration clone();
}
