//================================================================
//
//	Logger.java - Created by kerush, 2006 21-ott-06 7:40:03 
//
//================================================================
//
//					  R T  j  D D S  version 0.1
//
//				Copyright (C) 2006 by Vincenzo Caruso
//					   <bitclash[at]gmail.com>
//
// This program is free software; you can redistribute it and/or
// modify it under  the terms of  the GNU General Public License
// as published by the Free Software Foundation;either version 2
// of the License, or (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or  FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// A copy of the GNU  General Public License  is available in the
// file named LICENSE,  that  should  be  shipped along with this
// package;  if not,  write to the Free Software Foundation, Inc., 
// 51 Franklin Street, Fifth Floor,   Boston, MA  02110-1301, USA.
//
//========================================================

package rtjdds.util;

import javax.realtime.ImmortalMemory;
import javax.realtime.MemoryArea;
import javax.realtime.RealtimeThread;

/** 
 * Class to enable loggin in R T j D D S
 * @author Vincenzo Caruso
 */
public abstract class Logger{

    // Logging levels
    public static final int PEDANTIC=0;
    public static final int CONFIG=1;
    public static final int INFO=2;
    public static final int WARN=3;
    public static final int SEVERE=4;
    public static final int FATAL=5;
    protected static final String levelLabels[] =
        new String[]{ 
    	"PEDANTIC" 	,
    	"CONFIG" 	, 
    	"INFO" 		,
    	"WARNING" 	, 
    	"SEVERE" 	,
    	"FATAL" 	};

    /** Static instance of the logger */
    private static Logger instance = null;

    /** Return an instance of the logger. */
    public static Logger instance(){
        if( instance == null ){
            String loggerType = GlobalProperties.getGlobalProperty( "rtjdds.logger.type" , "ConsoleLogger" );
            int level = Integer.parseInt( GlobalProperties.getGlobalProperty( "rtjdds.logger.level" , "4" ) );
            try{
            	Class loggerClass = Class.forName(loggerType);
//                Class loggerClass = Class.forName( "rtjdds.util."+loggerType);
                instance = (Logger) loggerClass.newInstance();
//                instance = (Logger) ImmortalMemory.instance().newInstance(loggerClass);
                if (BuildProperties.dbgProperties) System.out.println("[RTjDDS] Using logger " + loggerClass + " at level " + level );
            }catch( Exception e ){

                System.err.println("Logger.instance(): " +
                    "Unable to load logger of type " + loggerType + ". Loading NullLogger.");
                instance = new NullLogger();
                e.printStackTrace();

            }
            instance.setLevel(level);
        }
        return instance;
    }

    public abstract void log(String msg);
    public abstract void log(int level, String msg);
    public abstract void log(String thisFunction, String msg);
    public abstract void log(int level, Class thisClass, String thisFunction, String msg);
    public abstract void log(int level, Class thisClass, String thisFunction, String msg, Throwable e);
    public abstract void log(int level, Class thisClass, String thisFunction, Throwable e);

    protected int level=0;
    protected void setLevel( int level ){
        this.level=level;
    }
    
	public synchronized void printMemStats() {
		MemoryArea mem = RealtimeThread.getCurrentMemoryArea();
		instance().log("Current memory is "+mem+" used="+(mem.memoryConsumed()/1024)+" Kb remaining="+(mem.memoryRemaining()/1024)+" Kb");
		instance().log("Used Immortal Memory "+(ImmortalMemory.instance().memoryConsumed()/1024)+ "Kb remaining "+(ImmortalMemory.instance().memoryRemaining()/1024)+" Kb");
	}

    public static void writeln(long a){
        write(a);
        writeln();
    }
   public static void writeln(){
        System.out.write( '\n' );
        System.out.flush();
    }
    public static void write(long a){
        if(a < 0){
            a = -a;
            System.out.write( '-' );
        }
        for( long i = 10000000000L ; i > 0 ; i /= 10 ){
            byte b = (byte) (a/i);
            System.out.write( b + '0' );
            a -= (b*i);
        }
        //System.out.flush();
    }
    public static void printTracePoint( int pos ){
        System.out.write( 'C' );
        System.out.write( 'p' );
        System.out.write( 'o' );
        System.out.write( 's' );
        System.out.write( ' ' );
        write( pos );
        writeln();
    }

//    private static long memAreaSizes[];
//    synchronized public static void printMemStats(int code, MemoryArea ma){
//        /*
//        if( !ZenBuildProperties.dbgMap[code] )
//            return;
//        if( memAreaSizes == null )
//            memAreaSizes = new long[10];*/
//        long mem = ma.memoryConsumed();
//        long rem = ma.memoryRemaining();/*
//        if( memAreaSizes[code] >= mem )
//            return;
//        memAreaSizes[code] = mem;
//        */
//        write(code);
//        System.out.write( ',' );
//        write(mem);
//        System.out.write( ',' );
//        write(rem);
//        if(ma instanceof ScopedMemory){
//            System.out.write( ',' );
//            write(((ScopedMemory)ma).getReferenceCount());
//        }
//        System.out.write( '\n' );
//        System.out.write( '\n' );
//        System.out.flush();
//    }

    public static void printThreadStack(){
            System.out.println("Current thread is " + RealtimeThread.currentRealtimeThread());
            System.out.println("cur mem area: " +  RealtimeThread.getCurrentMemoryArea());

            int curInd = RealtimeThread.getMemoryAreaStackDepth()-1;
            System.out.println("cur mem stack pos: " + curInd);

            for(int i = curInd; i >= 0; --i)
                System.out.println("mem area at pos " + i + " is " + RealtimeThread.getOuterMemoryArea(i));
    }

}

class NullLogger extends Logger{
    protected NullLogger(){}
    public void log(String msg) {}
    public void log(int level, String msg) {}
    public void log(String thisFunction, String msg) {}
    public void log(int level, Class thisClass, String thisFunction, String msg) {}
    public void log(int level, Class thisClass, String thisFunction, String msg, Throwable e) {}
    public void log(int level, Class thisClass, String thisFunction, Throwable e) {}
}
