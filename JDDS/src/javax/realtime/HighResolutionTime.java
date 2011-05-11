/*
 * Copyright (c) 2005 by the University of California, Irvine
 * All Rights Reserved.
 * 
 * This software is released under the terms of the RTZen license, which
 * you should have received along with this software. If not, you may
 * obtain a copy here: http://zen.ece.uci.edu/rtzen/license.php
 */

package javax.realtime;


public class HighResolutionTime {

    long millis = 0;
    long nanos = 0;

    public HighResolutionTime (){
    }

    public int getNanoseconds(){
        return (int)nanos;
    }

    public int getMilliseconds(){
        return (int)millis;
    }

}