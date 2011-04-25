//================================================================
//
//	ConsoleLogger.java - Created by kerush, 2006 4-nov-06 2:57:28 
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
//================================================================
package rtjdds.util;

import java.io.PrintStream;

public class ConsoleLogger extends Logger
{
    protected PrintStream printStream;

    public ConsoleLogger()
    {
        printStream = System.out;
    }

    public ConsoleLogger(PrintStream printStream)
    {
        this.printStream = printStream;
    }

    public synchronized void log(int level, Class thisClass, String thisFunction, String msg) {
        if( level >= this.level ){
        	printStream.print("[RTjDDS] ");
            printStream.print( Logger.levelLabels[level] );
            printStream.print(":");

            if (thisClass != null)
            {
                printStream.print(thisClass.getName());
                printStream.print(" : ");
            }

            if (thisFunction != null)
            {
                printStream.print(thisFunction);
                printStream.print(" : ");
            }

            printStream.println(msg);
        }
    }
    
    public void log(String msg) {
        log(null, msg);
    }
    
    public void log(String thisFunction, String msg) {
        log(Logger.INFO, null, thisFunction, msg);
    }
    
    public void log(int level, String msg) {
    	log(level, null, null,msg);
    }

    public void log(int level, Class thisClass, String thisFunction, String msg, Throwable e)
    {
        log(level, thisClass, thisFunction, msg);
        printStream.println(e);
    }

    public void log(int level, Class thisClass, String thisFunction, Throwable e)
    {
        log(level, thisClass, thisFunction, "", e);
    }
}