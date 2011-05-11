/*
 * Copyright (c) 2005 by the University of California, Irvine
 * All Rights Reserved.
 * 
 * This software is released under the terms of the RTZen license, which
 * you should have received along with this software. If not, you may
 * obtain a copy here: http://zen.ece.uci.edu/rtzen/license.php
 */

package javax.realtime;

public class NoHeapRealtimeThread extends RealtimeThread {
	
	public NoHeapRealtimeThread(SchedulingParameters schedulingParam, ReleaseParameters releaseParam, 
			MemoryParameters memoryParam, MemoryArea memoryArea, ProcessingGroupParameters groupParam, 
			Runnable logic)  {
		super(schedulingParam, releaseParam, memoryParam, memoryArea, groupParam, logic);
	}
	

    public NoHeapRealtimeThread(SchedulingParameters sched_parms, MemoryArea mem) {
    	super(null,null,null,mem,null,null);
    }

    public NoHeapRealtimeThread() {
        super();
    }
}
