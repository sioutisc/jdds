/*
 * Copyright (c) 2005 by the University of California, Irvine
 * All Rights Reserved.
 * 
 * This software is released under the terms of the RTZen license, which
 * you should have received along with this software. If not, you may
 * obtain a copy here: http://zen.ece.uci.edu/rtzen/license.php
 */

package javax.realtime;

/**
 * This class provides a stub for the RTSJ memory regions. It is used on Java
 * implementations that dont support RTSJ as a placeholder so that ZEN can
 * compile.
 */
public final class HeapMemory extends MemoryArea {

    private static HeapMemory singleton = null;

    public static HeapMemory instance() {
        if (singleton == null) singleton = new HeapMemory();
        return singleton;
    }

    public long memoryConsumed() {
        Runtime r = Runtime.getRuntime();
        return (long) (r.totalMemory() - r.freeMemory());
    }

    public long memoryRemaining() {
        return (long) (Runtime.getRuntime().freeMemory());
    }
}