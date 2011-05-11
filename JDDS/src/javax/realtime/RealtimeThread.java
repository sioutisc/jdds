/*
 * Copyright (c) 2005 by the University of California, Irvine
 * All Rights Reserved.
 * 
 * This software is released under the terms of the RTZen license, which
 * you should have received along with this software. If not, you may
 * obtain a copy here: http://zen.ece.uci.edu/rtzen/license.php
 */

package javax.realtime;

public class RealtimeThread extends Thread {
	
    public RealtimeThread(SchedulingParameters schedulingParam, ReleaseParameters releaseParam, 
    		MemoryParameters memoryParam, MemoryArea memoryArea, ProcessingGroupParameters groupParam, Runnable logic) {
    	super(logic);
    	setCurrentMemoryArea(memoryArea);
    }

    public RealtimeThread() {
    	super();
        setCurrentMemoryArea(null);
    }
    
    private RealtimeThread(Thread t) {
    	super(t);
    }

    private static java.util.Hashtable memHash = new java.util.Hashtable();

    public static void setCurrentMemoryArea(MemoryArea currentMem) {
        if (currentMem == null) currentMem = HeapMemory.instance();
        memHash.put(Thread.currentThread(), currentMem);

    }

    public static MemoryArea getCurrentMemoryArea() {
        MemoryArea mem = (MemoryArea) memHash.get(Thread.currentThread());
        if (mem == null) return HeapMemory.instance();
        else return mem;
    }

    public static RealtimeThread currentRealtimeThread() {
        //System.out.println("current thread is: " + Thread.currentThread()
        //        + " class:" + Thread.currentThread().getClass().toString());
        return (RealtimeThread)Thread.currentThread();
    }

    public static MemoryArea getOuterMemoryArea(int index) {
        return (MemoryArea) memHash.get(Thread.currentThread());
    }

    public static int getMemoryAreaStackDepth() {
        return 0;
    }
}
