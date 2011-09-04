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

package org.omg.dds.core.status;

import java.util.EventObject;
import java.util.Set;

import org.omg.dds.core.Bootstrap;
import org.omg.dds.core.Entity;
import org.omg.dds.core.StatusCondition;
import org.omg.dds.core.modifiable.ModifiableValue;


/**
 * Status is the abstract root class for all communication status objects.
 * All concrete kinds of Status classes extend this class.
 * 
 * Each concrete {@link Entity} is associated with a set of Status objects
 * whose value represents the "communication status" of that entity. These
 * status values can be accessed with corresponding methods on the Entity.
 * The changes on these status values are the ones that both cause activation
 * of the corresponding {@link StatusCondition} objects and trigger invocation
 * of the proper Listener objects to asynchronously inform the application.
 */
public abstract class Status<SELF extends Status<SELF, SOURCE>,
                             SOURCE extends Entity<SOURCE, ?, ?>>
extends EventObject
implements ModifiableValue<SELF, SELF> {
    // -----------------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------------

    private static final long serialVersionUID = 1989817719529565165L;



    // -----------------------------------------------------------------------
    // Object Life Cycle
    // -----------------------------------------------------------------------

    /**
     * @param bootstrap Identifies the Service instance to which the
     *                  object will belong.
     */
    public static Set<Class<? extends Status<?, ?>>> allStatuses(
            Bootstrap bootstrap) {
        return bootstrap.getSPI().allStatusKinds();
    }


    /**
     * @param bootstrap Identifies the Service instance to which the
     *                  object will belong.
     */
    public static Set<Class<? extends Status<?, ?>>> noStatuses(
            Bootstrap bootstrap) {
        return bootstrap.getSPI().noStatusKinds();
    }


    // -----------------------------------------------------------------------

    protected Status(SOURCE source) {
        super(source);
    }



    // -----------------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------------

    // --- API: --------------------------------------------------------------

    @Override
    public abstract SOURCE getSource();


    @Override
    public abstract SELF clone();


    // --- SPI: --------------------------------------------------------------

    protected void setSource(SOURCE source) {
        super.source = source;
    }
}
