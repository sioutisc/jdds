//================================================================
//
//	NetworkInfo.java - Created by kerush, 2006 23-nov-06 3:28:29 
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

/** try to determine MAC address of local network card; this is done
using a shell to run ifconfig (linux) or ipconfig (windows). The
output of the processes will be parsed.

<p>

To run the whole thing, just type java NetworkInfo

<p>

Current restrictions:

<ul>
   <li>Will probably not run in applets

   <li>Tested Windows / Linux only

   <li>Tested J2SDK 1.4 only

   <li>If a computer has more than one network adapters, only
       one MAC address will be returned

   <li>will not run if user does not have permissions to run
       ifconfig/ipconfig (e.g. under linux this is typically
       only permitted for root)
</ul>

*/

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.text.ParseException;
import java.util.StringTokenizer;

public final class NetworkInfo {
	
private final static String getMacAddress(InetAddress addr) throws IOException {
	
	String os = System.getProperty("os.name");

	try {
		if(os.startsWith("Windows")) {
			return windowsParseMacAddress(windowsRunIpConfigCommand(),addr);
		} else if(os.startsWith("Linux")) {
			return linuxParseMacAddress(linuxRunIfConfigCommand(), addr);
		} else {
			throw new IOException("unknown operating system: " + os);
		}
	} catch(ParseException ex) {
		ex.printStackTrace();
		throw new IOException(ex.getMessage());
	}
	
}


/*
 * Linux stuff
 */
private final static String linuxParseMacAddress(String ipConfigResponse, InetAddress addr) throws ParseException {
	String localHost = null;
	localHost = addr.getHostAddress();

	StringTokenizer tokenizer = new StringTokenizer(ipConfigResponse, "\n");
	String lastMacAddress = null;

	while(tokenizer.hasMoreTokens()) {
		String line = tokenizer.nextToken().trim();
		boolean containsLocalHost = line.indexOf(localHost) >= 0;

		// see if line contains IP address
		if(containsLocalHost && lastMacAddress != null) {
			return lastMacAddress;
		}

		// see if line contains MAC address
		int macAddressPosition = line.indexOf("HWaddr");
		if(macAddressPosition <= 0) continue;

		String macAddressCandidate = line.substring(macAddressPosition + 6).trim();
		if(linuxIsMacAddress(macAddressCandidate)) {
			lastMacAddress = macAddressCandidate;
			continue;
		}
	}

	ParseException ex = new ParseException
		("cannot read MAC address for " + localHost + " from [" + ipConfigResponse + "]", 0);
	ex.printStackTrace();
	throw ex;
}


private final static boolean linuxIsMacAddress(String macAddressCandidate) {
	// TODO: use a smart regular expression
	if(macAddressCandidate.length() != 17) return false;
	return true;
}


private final static String linuxRunIfConfigCommand() throws IOException {
	Process p = Runtime.getRuntime().exec("ifconfig");
	//Process p = Runtime.getRuntime().exec("sudo ifconfig");
	InputStream stdoutStream = new BufferedInputStream(p.getInputStream());

	StringBuffer buffer= new StringBuffer();
	for (;;) {
		int c = stdoutStream.read();
		if (c == -1) break;
		buffer.append((char)c);
	}
	String outputText = buffer.toString();

	stdoutStream.close();

	return outputText;
}



/*
 * Windows stuff
 */
private final static String windowsParseMacAddress(String ipConfigResponse, InetAddress addr) throws ParseException {
	String localHost = null;
	localHost = addr.getHostAddress();

	StringTokenizer tokenizer = new StringTokenizer(ipConfigResponse, "\n");
	String lastMacAddress = null;

	while(tokenizer.hasMoreTokens()) {
		String line = tokenizer.nextToken().trim();

		// see if line contains IP address
		if(line.endsWith(localHost) && lastMacAddress != null) {
			return lastMacAddress;
		}

		// see if line contains MAC address
		int macAddressPosition = line.indexOf(":");
		if(macAddressPosition <= 0) continue;

		String macAddressCandidate = line.substring(macAddressPosition + 1).trim();
		if(windowsIsMacAddress(macAddressCandidate)) {
			lastMacAddress = macAddressCandidate;
			continue;
		}
	}

	ParseException ex = new ParseException("cannot read MAC address from [" + ipConfigResponse + "]", 0);
	ex.printStackTrace();
	throw ex;
}


private final static boolean windowsIsMacAddress(String macAddressCandidate) {
	// TODO: use a smart regular expression
	if(macAddressCandidate.length() != 17) return false;

	return true;
}


private final static String windowsRunIpConfigCommand() throws IOException {
	Process p = Runtime.getRuntime().exec("ipconfig /all");
	InputStream stdoutStream = new BufferedInputStream(p.getInputStream());

	StringBuffer buffer= new StringBuffer();
	for (;;) {
		int c = stdoutStream.read();
		if (c == -1) break;
		buffer.append((char)c);
	}
	String outputText = buffer.toString();

	stdoutStream.close();

	return outputText;
}



/*
 * Main
 */
public final static void main(String[] args) {
	try {
		System.out.println("Network infos");
		InetAddress addr = InetAddress.getByName("127.0.0.1");
		System.out.println("  Operating System: " + System.getProperty("os.name"));
		System.out.println("  IP/Localhost: " + addr.getHostAddress());
		System.out.println("  MAC Address: " + getMacAddress(addr));
	} catch(Throwable t) {
		t.printStackTrace();
	}
}
}
