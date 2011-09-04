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

package org.omg.dds.type.builtin;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.omg.dds.core.InstanceHandle;
import org.omg.dds.core.Time;
import org.omg.dds.core.modifiable.ModifiableInstanceHandle;
import org.omg.dds.pub.DataWriter;


public interface KeyedStringDataWriter extends DataWriter<KeyedString> 
{
    public InstanceHandle registerInstance(String key)
    throws TimeoutException;

    public InstanceHandle registerInstance(String key, Time sourceTimestamp)
    throws TimeoutException;

    public InstanceHandle registerInstance(
            String key, long sourceTimestamp, TimeUnit unit)
    throws TimeoutException;

    public void unregisterInstance(String key) throws TimeoutException;

    public void unregisterInstance(String key, Time sourceTimestamp)
    throws TimeoutException;

    public void unregisterInstance(
            String key, long sourceTimestamp, TimeUnit unit)
    throws TimeoutException;

    public void write(String key, String str) throws TimeoutException;

    public void write(String key, String str, InstanceHandle handle)
    throws TimeoutException;

    public void write(
            String key,
            String str,
            InstanceHandle handle,
            Time sourceTimestamp)
    throws TimeoutException;

    public void write(
            String key,
            String str,
            InstanceHandle handle,
            long sourceTimestamp,
            TimeUnit unit)
    throws TimeoutException;

    public void dispose(String key) throws TimeoutException;

    public void dispose(String key, Time sourceTimestamp)
    throws TimeoutException;

    public void dispose(String key, long sourceTimestamp, TimeUnit unit)
    throws TimeoutException;

    public StringBuilder getKeyValue(StringBuilder key, InstanceHandle handle);

    public ModifiableInstanceHandle lookupInstance(
            ModifiableInstanceHandle handle, String key);
}
