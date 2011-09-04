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

import java.util.concurrent.TimeUnit;

import org.omg.dds.core.modifiable.ModifiableDuration;
import org.omg.dds.type.Extensibility;
import org.omg.dds.type.Nested;


/**
 * A span of elapsed time expressed with nanosecond precision.
 */
@Extensibility(Extensibility.Kind.FINAL_EXTENSIBILITY)
@Nested
public abstract class Duration implements Value<Duration, ModifiableDuration>
{
    // -----------------------------------------------------------------------
    // Private Constants
    // -----------------------------------------------------------------------

    private static final long serialVersionUID = 6926514364942353575L;



    // -----------------------------------------------------------------------
    // Factory Methods
    // -----------------------------------------------------------------------

    /**
     * Construct a time duration of the given magnitude.
     * 
     * A duration of magnitude {@link Long#MAX_VALUE} indicates an infinite
     * duration, regardless of the units specified.
     * 
     * @param bootstrap Identifies the Service instance to which the new
     *                  object will belong.
     * 
     * @see     #isInfinite()
     * @see     #infiniteDuration(Bootstrap)
     */
    public static ModifiableDuration newDuration(
            long duration, TimeUnit unit, Bootstrap bootstrap) {
        return bootstrap.getSPI().newDuration(duration, unit);
    }


    /**
     * @param bootstrap Identifies the Service instance to which the
     *                  object will belong.
     * 
     * @return  An unmodifiable {@link Duration} of infinite length.
     */
    public static Duration infiniteDuration(Bootstrap bootstrap) {
        return bootstrap.getSPI().infiniteDuration();
    }


    /**
     * @param bootstrap Identifies the Service instance to which the
     *                  object will belong.
     * 
     * @return  A {@link Duration} of zero length.
     */
    public static Duration zeroDuration(Bootstrap bootstrap) {
        return bootstrap.getSPI().zeroDuration();
    }



    // -----------------------------------------------------------------------
    // Instance Methods
    // -----------------------------------------------------------------------

    // --- Data access: ------------------------------------------------------

    /**
     * Truncate this duration to a whole-number quantity of the given time
     * unit. For example, if this duration is equal to one second plus 100
     * nanoseconds, calling this method with an argument of
     * {@link TimeUnit#SECONDS} will result in the value <code>1</code>.
     * 
     * If this duration is infinite, this method shall return
     * {@link Long#MAX_VALUE}, regardless of the units given.
     * 
     * If this duration cannot be expressed in the given units without
     * overflowing, this method shall return {@link Long#MAX_VALUE}. In such
     * a case, the caller may wish to use this method in combination with
     * {@link #getRemainder(TimeUnit, TimeUnit)} to obtain the full duration
     * without lack of precision.
     * 
     * @param   inThisUnit  The time unit in which the return result will
     *                      be measured.
     * 
     * @see     #getRemainder(TimeUnit, TimeUnit)
     * @see     Long#MAX_VALUE
     * @see     TimeUnit
     */
    public abstract long getDuration(TimeUnit inThisUnit);

    /**
     * If getting the magnitude of this duration in the given
     * <code>primaryUnit</code> would cause truncation with respect to the
     * given <code>remainderUnit</code>, return the magnitude of the
     * truncation in the latter (presumably finer-grained) unit. For example,
     * if this duration is equal to one second plus 100 nanoseconds, calling
     * this method with arguments of {@link TimeUnit#SECONDS} and
     * {@link TimeUnit#NANOSECONDS} respectively will result in the value
     * <code>100</code>.
     * 
     * This method is equivalent to the following pseudo-code:
     * 
     * <code>
     * (this - getDuration(primaryUnit)).getDuration(remainderUnit)
     * </code>
     * 
     * If <code>remainderUnit</code> is represents a coarser granularity than
     * <code>primaryUnit</code> (for example, the former is
     * {@link TimeUnit#HOURS} but the latter is {@link TimeUnit#SECONDS}),
     * this method shall return <code>0</code>.
     * 
     * If the resulting duration cannot be expressed in the given units
     * without overflowing, this method shall return {@link Long#MAX_VALUE}.
     * 
     * @param   primaryUnit
     * @param   remainderUnit   The time unit in which the return result will
     *                          be measured.
     * 
     * @see     #getDuration(TimeUnit)
     * @see     Long#MAX_VALUE
     * @see     TimeUnit
     */
    public abstract long getRemainder(
            TimeUnit primaryUnit, TimeUnit remainderUnit);


    // --- Query: ------------------------------------------------------------

    /**
     * Report whether this duration lasts no time at all. The result of this
     * method is equivalent to the following:
     * 
     * <code>this.getDuration(TimeUnit.NANOSECONDS) == 0;</code>
     * 
     * @see     #getDuration(TimeUnit)
     */
    public abstract boolean isZero();

    /**
     * Report whether this duration lasts forever.
     * 
     * If this duration is infinite, the following relationship shall be
     * true:
     * 
     * <code>this.equals(infiniteDuration(this.getBootstrap()))</code>
     * 
     * @see     #infiniteDuration(Bootstrap)
     */
    public abstract boolean isInfinite();


    // --- From Object: ------------------------------------------------------

    @Override
    public abstract Duration clone();
}
