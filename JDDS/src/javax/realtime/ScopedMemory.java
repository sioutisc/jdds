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
public abstract class ScopedMemory extends MemoryArea {

    int referenceCount = 0;

    public ScopedMemory(long size) {
        super(size);
    }

    public ScopedMemory(long size, Runnable logic) {
        super(size, logic);
    }

    public void enter() {
        //System.err.println( "RTSJ: Enter in memory area
        // ["+this.toString()+"]" );
        //Thread.dumpStack();
        MemoryArea old = RealtimeThread.getCurrentMemoryArea();
        RealtimeThread.setCurrentMemoryArea(this);
        referenceCount++;
        logic.run();
        referenceCount--;
        RealtimeThread.setCurrentMemoryArea(old);
        //System.err.println( "RTSJ: Return from memory area
        // ["+this.toString()+"]" );
    }

    public void enter(Runnable logic) {
        //System.err.println( "RTSJ: Enter in memory area
        // ["+this.toString()+"]" );
        //Thread.dumpStack();
        MemoryArea old = RealtimeThread.getCurrentMemoryArea();
        RealtimeThread.setCurrentMemoryArea(this);
        referenceCount++;
        logic.run();
        referenceCount--;
        RealtimeThread.setCurrentMemoryArea(old);
        //System.err.println( "RTSJ: Return from memory area
        // ["+this.toString()+"]" );
    }

    public long getMaximumSize() {
        return size();
    }

    private Object portal;

    public Object getPortal() {
        return portal;
    }

    public int getReferenceCount() {
        return referenceCount;
    }

    public void join() {
        while (referenceCount > 0) {
            synchronized (this) {
                try {
                    wait();
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }

    public void setPortal(Object obj) {
        portal = obj;
    }

    public void executeIn(Runnable e) {
        //System.err.println( "RTSJ: Execute in memory area
        // ["+this.toString()+"]" );
        //Thread.dumpStack();
        MemoryArea old = RealtimeThread.getCurrentMemoryArea();
        logic.run();
        RealtimeThread.setCurrentMemoryArea(old);
        //System.err.println( "RTSJ: Return from memory area
        // ["+this.toString()+"]" );
    }
}
