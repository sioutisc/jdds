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

import org.omg.dds.core.Bootstrap;
import org.omg.dds.core.modifiable.ModifiableInstanceHandle;
import org.omg.dds.sub.DataReader;


public abstract class LivelinessChangedStatus<TYPE>
extends Status<LivelinessChangedStatus<TYPE>, DataReader<TYPE>> {
    // -----------------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------------

    private static final long serialVersionUID = -6569834894650163848L;



    // -----------------------------------------------------------------------
    // Object Life Cycle
    // -----------------------------------------------------------------------

    /**
     * @param bootstrap Identifies the Service instance to which the new
     *                  object will belong.
     */
    public static <TYPE> LivelinessChangedStatus<TYPE>
    newLivelinessChangedStatus(Bootstrap bootstrap) {
        return bootstrap.getSPI().newLivelinessChangedStatus();
    }


    // -----------------------------------------------------------------------

    protected LivelinessChangedStatus(DataReader<TYPE> source) {
        super(source);
    }



    // -----------------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------------

    /**
     * @return the aliveCount
     */
    public abstract int getAliveCount();

    /**
     * @return the notAliveCount
     */
    public abstract int getNotAliveCount();

    /**
     * @return the aliveCountChange
     */
    public abstract int getAliveCountChange();

    /**
     * @return the notAliveCountChange
     */
    public abstract int getNotAliveCountChange();

    public abstract ModifiableInstanceHandle getLastPublicationHandle();

}