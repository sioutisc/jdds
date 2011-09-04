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

package org.omg.dds.topic;

import org.omg.dds.core.Bootstrap;
import org.omg.dds.core.modifiable.ModifiableValue;
import org.omg.dds.type.Extensibility;
import org.omg.dds.type.Nested;


@Extensibility(Extensibility.Kind.EXTENSIBLE_EXTENSIBILITY)
@Nested
public abstract class BuiltinTopicKey
implements ModifiableValue<BuiltinTopicKey, BuiltinTopicKey>
{
    // -----------------------------------------------------------------------
    // Private Constants
    // -----------------------------------------------------------------------

    private static final long serialVersionUID = 5414777490591097112L;



    // -----------------------------------------------------------------------
    // Factory Methods
    // -----------------------------------------------------------------------

    /**
     * @param bootstrap Identifies the Service instance to which the new
     *                  object will belong.
     */
    public static BuiltinTopicKey newBuiltinTopicKey(Bootstrap bootstrap) {
        return bootstrap.getSPI().newBuiltinTopicKey();
    }



    // -----------------------------------------------------------------------
    // Instance Methods
    // -----------------------------------------------------------------------

    /**
     * Copy the value of this key into the first four positions of the given
     * array.
     * 
     * Service implementations that do not support the DDS-RTPS
     * interoperability protocol may use a key length of only three, not
     * four. Such implementations shall set index 3 in the given array to 0.
     * 
     * @param   dst     An array of length at least four integers. Any items
     *                  at index 4 or higher will not be modified.
     * 
     * @return  The input array.
     */
    public abstract int[] getValue(int[] dst);


    // --- From Object: ------------------------------------------------------

    @Override
    public abstract BuiltinTopicKey clone();
}
