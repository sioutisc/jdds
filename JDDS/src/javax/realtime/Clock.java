/*
 * Copyright (c) 2005 by the University of California, Irvine
 * All Rights Reserved.
 * 
 * This software is released under the terms of the RTZen license, which
 * you should have received along with this software. If not, you may
 * obtain a copy here: http://zen.ece.uci.edu/rtzen/license.php
 */

package javax.realtime;


public final class Clock {

    private static Clock inst;

    public Clock (){
    }

    public static Clock getRealtimeClock(){
        if(inst == null)
            inst = new Clock();

        return inst;
    }

    public void getTime(AbsoluteTime at){
        at.millis = System.currentTimeMillis();
    }
    
    public AbsoluteTime getTime(){
    	AbsoluteTime at = new AbsoluteTime();
        at.millis = System.currentTimeMillis();
        return at;
    }
}