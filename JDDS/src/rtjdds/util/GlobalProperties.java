//================================================================
//
//	GlobalProperties.java - Created by kerush, 2006 21-ott-06 8:42:18 
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

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import javax.realtime.ImmortalMemory;

import jtools.time.HighResTime;
import jtools.time.HighResTimer;

import rtjdds.rtps.messages.elements.ProtocolId;
import rtjdds.rtps.messages.elements.ProtocolVersion;
import rtjdds.rtps.messages.elements.VendorId;
import rtjdds.rtps.receive.PipeSelector;
import rtjdds.rtps.types.ProtocolId_t;
import rtjdds.rtps.types.ProtocolVersion_t;
import rtjdds.rtps.types.VendorId_t;

public final class GlobalProperties {
	
	/* reference to immortal memory, all the objects in this class are allocated in immortal */
	public static final ImmortalMemory immortalMem = ImmortalMemory.instance();
	
	/* The Logger */
	public static final Logger logger = Logger.instance();
	
	/* number of bytes for the receiver byte[] buffer (DEFAULT 16 KB)  */
	public static final int BUFFER_SIZE = 32*1024;	

	/* TODO togliere questa schifezza! */
	public static final PipeSelector pipeSelector = new PipeSelector(BUFFER_SIZE, 12);
	public static final  HighResTimer timer = new HighResTimer();
	
	public static int THREAD_POOL_SIZE = 5;

	/* size of the memory scope used by UDP receiver for message buffers */
	public static final long SCRATCH_SCOPE_SIZE = BUFFER_SIZE * 10;
	
	/* PSM ports TODO add in configuration */
	public static final int PB = 7400;
	public static final int DG = 250;
	public static final int PG = 2;
	public static final int d0 = 0;
	public static final int d1 = 10;
	public static final int d2 = 1;
	public static final int d3 = 11;
	public static final String DEFAULT_MULTICAST_ADDRESS = "239.255.0.2";
	
	/* has to be setted by the Ant install process */
	public static final String installDir = "/home/kerush/workspace/rtjdds/";
	
	/* Appears in the header of each RTPS-WP message */
	public static final ProtocolId_t protocolId_t = new ProtocolId_t(new byte[]{'R','T','P','S'});
	public static final ProtocolId protocolId = new ProtocolId(protocolId_t);
	
	/*  220 is a FRIEND number: x is FRIEND iif x = sum { y : (x%y==0), y<x } */
	public static final byte[] VENDORID = new byte[] {2,20};
	public static final VendorId_t vendorId_t= new VendorId_t(VENDORID);
	public static final VendorId vendorId= new VendorId(vendorId_t);
	
	/* RTPS_WP Protocol Versions */
	public static final byte ProtocolVersionMajor = 2;
	public static final byte ProtocolVersionMinor = 0;
	public static final ProtocolVersion_t protocolVersion_t = new ProtocolVersion_t(ProtocolVersionMajor, ProtocolVersionMinor);
	public static final ProtocolVersion protocolVersion = new ProtocolVersion(protocolVersion_t);
	
	/* Version of R T j D D S */
	public static final String rtjddsVersionString = "0.1"; 
	
	public static final String rtjddsVersion = 
		"R T j D D S Version " +rtjddsVersionString + ", UNSTABLE\n";

	public static final String rtjddsStartupMessage = 
		  "--\n" + rtjddsVersion + "\n"
        + "Copyright by:\n"
        + "\tVincenzo Caruso <bitclash@gmail.com>\n"
        + "Thanks to:\n"
        + "\tAngelo Corsaro <SELEX-SI> \n"
//        + "SELEX - Sistemi Integrati,\n"
//        + "\tDEIS - University of Bologna\n"
        + "\nTHIS IS FREE SOFTWARE AND COMES WITH ABSOLUTELY NO WARRANTY\n";
	
	
	
	
    
//THIS CODE IS CALLED AT CLASS LOADING TIME
//initialize everything in order
//1) global properties
//2) memory regions
    static {
    	immortalMem.enter(new Runnable() { public void run() {
    		init();
    	}});
    }
	
	//*********************//
	//	CODE FROM RT Zen   //
	//*********************//
	
	
    // Global properties [install dir,user dir,working dir]
    // allocated in immortal memory because it doesnt change after loading.
    protected static Properties globalProperties; 

    protected static int bufferSize = 9 * 1024;

    private static boolean isInit = false;

    // Reading properties from file:
    // 1. first try to locate rtjdds.properties in the install directory
    // 2. else try to locate rtjdds.properties in the user's home directory
    // 3. last try to locate rtjdds.properties in the current invocation directory
    private static synchronized void init() {
        if (isInit) return;
        isInit = true;

        /* Prints a nice startup message */
        System.out.println(rtjddsStartupMessage+"--");
        
        if (BuildProperties.dbgProperties) System.out.println(
        		"[RTjDDS] The current memory region in GlobalProperties.init() is " + 
        		javax.realtime.RealtimeThread.getCurrentMemoryArea());
        
        // Creation of the properties object (in immortal memory)
        Properties tmpProperties = new Properties();
        
        String propertiesFileName = "rtjdds.properties";
        
        // 1.
        try {
        	// reading properties file from install directory
            FileInputStream in = new FileInputStream(new File(installDir + File.separator + propertiesFileName));
            tmpProperties.load(in);
            if (BuildProperties.dbgProperties) System.out.println("[RTjDDS] Properties loaded from " + installDir + File.separator + propertiesFileName);
        } catch (Exception exception) {
            if (BuildProperties.dbgProperties) System.out.println("[RTjDDS] Unable to load default properties from the installation directory. (" + installDir + ")");
        }
        // 2.
        try {
        	// reading properties file from user's home directory
            FileInputStream in = new FileInputStream(new File(System.getProperty("user.home") + File.separator + propertiesFileName));
            tmpProperties.load(in);
            if (BuildProperties.dbgProperties) System.out.println("[RTjDDS] Properties loaded from " + System.getProperty("user.home") + File.separator + propertiesFileName);
        } catch (Exception exception) {
            if (BuildProperties.dbgProperties) System.out.println("[RTjDDS] Unable to load default properties from user's home directory. (" + System.getProperty("user.home") + ")");
        }
        // 3.
        try {
        	// reading properties file from current directory
            FileInputStream in = new FileInputStream(new File(System.getProperty("user.dir") + File.separator + propertiesFileName));
            tmpProperties.load(in);
            if (BuildProperties.dbgProperties) System.out.println("[RTjDDS] Properties loaded from "  + System.getProperty("user.dir") + File.separator + propertiesFileName);
        } catch (Exception exception) {
            if (BuildProperties.dbgProperties) System.out.println("[RTjDDS] Unable to load default properties from the working directory. (" + System.getProperty("user.dir") + ")");
        }

        // loading properties in IMMORTAL MEMORY!
        try {
//            globalProperties = (Properties) immortalMem.newInstance(Properties.class);
        	globalProperties = new Properties();
            java.util.Enumeration propsEnum = tmpProperties.propertyNames();
            Class[] refArgs = new Class[] { String.class };
            java.lang.reflect.Constructor refConstr = String.class.getConstructor(refArgs);
            while (propsEnum.hasMoreElements()) {
                String key = (String) propsEnum.nextElement();
                String val = (String) tmpProperties.get(key);
                Object[] refVal = new Object[] {key};
                String immKey = (String) ImmortalMemory.instance().newInstance(refConstr, refVal);
                refVal = new Object[] {val};
                String immVal = (String) ImmortalMemory.instance().newInstance(refConstr, refVal);
                GlobalProperties.globalProperties.setProperty(immKey, immVal);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        if (BuildProperties.dbgProperties) if (BuildProperties.dbgProperties) System.out.println("[RTjDDS] Global properties have been loaded");
        System.out.flush();
        // All properties loaded
    }

    /**
     * This method isused to access the STATIC properties set by the user. The
     * properties obtained in the following order. Last one takes priority. 1)
     * zen.properties in the installation directory (Loaded when Class is
     * loaded) 2) zen.properties in the user home directory (Loaded when Class
     * is loaded) 3) zen.properties in the current directory (Loaded when Class
     * is loaded)
     *
     * @param property
     *            The property that is required
     * @return The value of the property if it is set in props or
     *         System.properties, null otherwise
     */
    public static String getGlobalProperty(String property, String defaultValue) {
        if (!isInit) init();
        String propertyValue = defaultValue;
        propertyValue = globalProperties.getProperty(property, propertyValue);
        propertyValue = System.getProperty(property, propertyValue);
        return propertyValue;
    }
    
    /*  */
//
//    // do nothing, everything is configured statical by the
//    // init method at loading time...
//    public GlobalProperties() {
//    	//
//    }
//
//    /**
//     * This method isused to access properties set by the user. The properties
//     * obtained in the following order. Last one takes priority. 
//     * 1) rtjdds.properties in the installation directory (Loaded when Class is loaded) <br/> 
//     * 2) rtjdds.properties in the user home directory (Loaded when Class is loaded) <br/> 
//     * 3) rtjdds.properties in the current directory (Loaded when Class is loaded) <br/> 
//     * 4) System properties (Loaded from command line by Java)  <br/>
//     *
//     * @param property
//     *            The property that is required
//     * @return The value of the property if it is set in props or
//     *         System.properties, null otherwise
//     */
//    public String getProperty(String property, String defaultValue) {
//        String propertyValue = defaultValue;
//        // first try to load property from the file loaded one
//        propertyValue = globalProperties.getProperty(property,propertyValue);
//        // then try the system's properties
//        propertyValue = System.getProperty(property, propertyValue);
//        return propertyValue;
//    }
//
//    /**
//     * Dinamically add properties
//     * 
//     * @param props
//     */
//    public void addProperties(Properties props) {
//        if (props != null) {
//            Enumeration keys = props.keys();
//            while (keys.hasMoreElements()) {
//                String element = ((String) keys.nextElement()) + "";
//                globalProperties.put(element, props.getProperty(element) + "");
//            }
//        }
//    }

}
