// ************************************************************************
//    $Id$
// ************************************************************************
//
//                               jTools
//
//               Copyright (C) 2001-2003 by Angelo Corsaro.
//                         <corsaro@cs.wustl.edu>
//                          All Rights Reserved.
//
//   Permission to use, copy, modify, and distribute this software and
//   its  documentation for any purpose is hereby  granted without fee,
//   provided that the above copyright notice appear in all copies and
//   that both that copyright notice and this permission notice appear
//   in  supporting  documentation. I don't make  any  representations
//   about the  suitability  of this  software for any  purpose. It is
//   provided "as is" without express or implied warranty.
//
//
// *************************************************************************
//  
// *************************************************************************
package jtools.time;


/**
 * This class provide an high resolution clock. It is implemented
 * using through JNI the ACE_High_Res_Timer
 *
 * @author <a href="mailto:corsaro@doc.cs.wustl.edu">Angelo Corsaro</a>
 * @version 1.0
 */
public class HighResClock {
    
    static {
        System.loadLibrary("HRTime");
    }

    public static HighResTime getTime() {
        
        HighResTime time = new HighResTime();
        HighResClock.getTime(time);
        return time;
    }

    public static native void getTime(HighResTime time);

    public static native long getClockTickCount();

    public static HighResTime clockTick2HighResTime(long clockTicks) {
        HighResTime time = new HighResTime();
        clockTick2HighResTime(clockTicks, time);
        return time;
    }

    /* this was native... */
    public static void clockTick2HighResTime(long clockTicks, HighResTime time) {
		
    	double timeNS = clockTicks * getClockPeriod();
		
		long ms = (long) (timeNS / 1000000);
		long roundNs = ms * 1000000;
		long us = (long) ((timeNS -roundNs) / 1000);
		long ns = (long) (timeNS - roundNs - ( us * 1000 )) ;
	
		time.setTime(ms,us,ns);    
    }


    /**
     * Returns the frequency of the clock in MHz
     *
     * @return a <code>float</code> value representing the frequency of the clock.
     */
    public static native float getClockFrequency();
    
    /**
     * Returns the period of the clock in nano seconds.
     *
     * @return a <code>double</code> value representing the period of the clock.
     */
    public static native double getClockPeriod();
    
}
