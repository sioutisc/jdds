/*
 * Copyright (c) 2005 by the University of California, Irvine
 * All Rights Reserved.
 * 
 * This software is released under the terms of the RTZen license, which
 * you should have received along with this software. If not, you may
 * obtain a copy here: http://zen.ece.uci.edu/rtzen/license.php
 */

package javax.realtime;
//import rtjdds.util.GlobalProperties;

/**
 * This class provides a stub for the RTSJ memory regions. It is used on Java
 * implementations that dont support RTSJ as a placeholder so that ZEN can
 * compile.
 */
public abstract class MemoryArea {
    long sizeInBytes;

    Runnable logic;

    protected MemoryArea() {
        //System.err.println( "RTSJ: New memory area created
        // ["+this.toString()+"]" )
    }

    protected MemoryArea(long sizeInBytes) {
        //System.err.println( "RTSJ: New memory area created ( "+sizeInBytes+"
        // bytes ) ["+this.toString()+"]" );
        this.sizeInBytes = sizeInBytes;
    }

    protected MemoryArea(long sizeInBytes, Runnable logic) {
        //System.err.println( "RTSJ: New memory area created ( "+sizeInBytes+"
        // bytes ) ["+this.toString()+"]" );
        this.sizeInBytes = sizeInBytes;
        this.logic = logic;
    }

    public void enter() {
        //System.err.println( "RTSJ: Enter in memory area
        // ["+this.toString()+"]" );
        //Thread.dumpStack();
        MemoryArea old = RealtimeThread.getCurrentMemoryArea();
        logic.run();
        RealtimeThread.setCurrentMemoryArea(old);
        //System.err.println( "RTSJ: Return from memory area
        // ["+this.toString()+"]" );
    }

    public void enter(java.lang.Runnable logic) {
        //System.err.println( "RTSJ: Enter in memory area
        // ["+this.toString()+"]" );
        //Thread.dumpStack();
        MemoryArea old = RealtimeThread.getCurrentMemoryArea();
        RealtimeThread.setCurrentMemoryArea(this);
        logic.run();
        RealtimeThread.setCurrentMemoryArea(old);
        //System.err.println( "RTSJ: Return from memory area
        // ["+this.toString()+"]" );
    }

    public void executeInArea(java.lang.Runnable logic)
            throws InaccessibleAreaException {
        //System.err.println( "RTSJ: Execute in memory area
        // ["+this.toString()+"]" );
        //Thread.dumpStack();
        MemoryArea old = RealtimeThread.getCurrentMemoryArea();
        RealtimeThread.setCurrentMemoryArea(this);
        logic.run();
        RealtimeThread.setCurrentMemoryArea(old);
        //System.err.println( "RTSJ: Return from memory area
        // ["+this.toString()+"]" );
    }

    public long memoryConsumed() {
        return 0;
    }

    public long memoryRemaining() {
        return sizeInBytes;
    }

    public java.lang.Object newArray(java.lang.Class type, int number)
            throws java.lang.IllegalAccessException,
            java.lang.InstantiationException {
        //System.err.println( "RTSJ: New array("+type+") in memory area
        // ["+this.toString()+"]" );
        return java.lang.reflect.Array.newInstance(type, number);
    }

    public java.lang.Object newInstance(java.lang.Class type)
            throws java.lang.IllegalAccessException,
            java.lang.InstantiationException {
        //System.err.println( "RTSJ: New object("+type+") in memory area
        // ["+this.toString()+"]" );
        return type.newInstance();
    }

    public java.lang.Object newInstance(java.lang.reflect.Constructor c,
            java.lang.Object[] args) throws java.lang.IllegalAccessException,
            java.lang.InstantiationException {
        //System.err.println( "RTSJ: New object("+c.getDeclaringClass()+") from
        // constructor in memory area ["+this.toString()+"]" );
        try {
            return c.newInstance(args);
        } catch (java.lang.reflect.InvocationTargetException ite) {
            throw new IllegalAccessException();
        }
    }

    public static MemoryArea getMemoryArea(Object obj) {
        //return HeapMemory.instance();
        return new LTMemory(100, 16 * 1024);
    }

    public long size() {
        return sizeInBytes;
    }
}
