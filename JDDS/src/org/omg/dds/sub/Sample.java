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

package org.omg.dds.sub;

import java.util.ListIterator;

import org.omg.dds.core.modifiable.ModifiableInstanceHandle;
import org.omg.dds.core.modifiable.ModifiableTime;
import org.omg.dds.core.modifiable.ModifiableValue;


public interface Sample<TYPE>
extends ModifiableValue<Sample<TYPE>, Sample<TYPE>> {
    // -----------------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------------

    // --- Sample data: ------------------------------------------------------
    /**
     * @return  the data associated with this sample. This method will return
     *          null if this sample contains no valid data.
     */
    public TYPE getData();


    // --- Sample meta-data: -------------------------------------------------
    /**
     * @return the sampleState
     */
    public SampleState getSampleState();

    /**
     * @return the viewState
     */
    public ViewState getViewState();

    /**
     * @return the instanceState
     */
    public InstanceState getInstanceState();

    public ModifiableTime getSourceTimestamp();

    public ModifiableInstanceHandle getInstanceHandle();

    public ModifiableInstanceHandle getPublicationHandle();

    /**
     * @return the disposedGenerationCount
     */
    public int getDisposedGenerationCount();

    /**
     * @return the noWritersGenerationCount
     */
    public int getNoWritersGenerationCount();

    /**
     * @return the sampleRank
     */
    public int getSampleRank();

    /**
     * @return the generationRank
     */
    public int getGenerationRank();

    /**
     * @return the absoluteGenerationRank
     */
    public int getAbsoluteGenerationRank();



    // -----------------------------------------------------------------------
    // Types
    // -----------------------------------------------------------------------

    public static interface Iterator<IT_DATA>
    extends ListIterator<Sample<IT_DATA>> {
        /**
         * The samples provided by this iterator have been loaned from a
         * pool maintained by the Service; return that loan now.
         */
        public void returnLoan();

        // --- From ListIterator: --------------------------------------------
        /**
         * @exception UnsupportedOperationException always.
         */
        public void remove();

        /**
         * @exception UnsupportedOperationException always.
         */
        public void set(Sample<IT_DATA> o);

        /**
         * @exception UnsupportedOperationException always.
         */
        public void add(Sample<IT_DATA> o);
    }

}
