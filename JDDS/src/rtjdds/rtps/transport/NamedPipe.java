//================================================================
//
//	NamedPipe.java - Created by kerush, 2007 06/mar/07 10:46:26
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
package rtjdds.rtps.transport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.FileChannel;

import test.latency.PingPublisher;

public class NamedPipe extends File {

	private NamedPipe(String name) {
		super(name);
	}
	
	public FileChannel getInputChannel() throws FileNotFoundException  {
		FileInputStream is = new FileInputStream(this);
		return is.getChannel();
	}
	
	public FileChannel getOutputChannel() throws FileNotFoundException  {
		FileOutputStream os = new FileOutputStream(this);
		return os.getChannel();
	}
	
	public static NamedPipe createPipe(String name) throws IOException {
		Runtime.getRuntime().exec("mkfifo "+name);
		NamedPipe pipe = new NamedPipe(name);
		if (pipe.exists())
			return new NamedPipe(name);
		else
			throw new IOException("Errors while creating the pipe");
	}
	
}
