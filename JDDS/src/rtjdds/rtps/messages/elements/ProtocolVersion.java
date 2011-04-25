//================================================================
//
//	ProtocolVersion.java - Created by kerush, 2006 20-ott-06 1:29:17 
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
package rtjdds.rtps.messages.elements;

import org.omg.CORBA.portable.OutputStream;

import rtjdds.rtps.structure.local.ParticipantImpl;
import rtjdds.rtps.types.ProtocolVersion_t;
import rtjdds.rtps.types.ProtocolVersion_tHelper;
import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;
import rtjdds.util.collections.Copyable;

public class ProtocolVersion extends SubmessageElement implements Comparable, Copyable {

	protected ProtocolVersion_t value;
	
	public ProtocolVersion(ProtocolVersion_t value) {
		super(SubmessageElement.PROTOCOL_VERSION_SIZE);
		this.value = value;
	}
	
	/**
	 * @return Returns the value.
	 */
	public byte getMajor() {
		return value.major;
	}
	
	public byte getMinor() {
		return value.minor;
	}

	public void write(OutputStream os) {
		ProtocolVersion_tHelper.write(os, value);
	}
	
	public String toString() {
		return ""+value.major+"."+value.minor;
	}

	public int compareTo(Object arg0) {
		ProtocolVersion_t ver = null;
		if (arg0 instanceof ProtocolVersion_t) {
			ver = (ProtocolVersion_t) arg0;
		}
		else if (arg0 instanceof ProtocolVersion) {
			ver = ((ProtocolVersion) arg0).value;
		}
		// throws null pointer exception if class cast nok
		if (this.value.major > ver.major) {
			return 1;
		}
		else if (this.value.major > ver.major) {
			return -1;
		}
		else {
			// major version is the same...
			if (this.value.minor > ver.minor) {
				return 1;
			}
			else if (this.value.minor < ver.minor) {
				return -1;
			}
			else {
				return 0;
			}
		}
	}

	public void copyFrom(Copyable obj) {
		if (obj instanceof ProtocolVersion) {
			ProtocolVersion src = (ProtocolVersion) obj;;
			value.copyFrom(src);
		}
		else {
			GlobalProperties.logger.log(Logger.SEVERE,this.getClass(),"copyFrom()",
					"cannot copy from a not ProtocolVersion instance");
		}
	}

	public void copyTo(Copyable obj) {
		if (obj instanceof ProtocolVersion) {
			ProtocolVersion dst = (ProtocolVersion) obj;;
			value.copyTo(dst);
		}
		else {
			GlobalProperties.logger.log(Logger.SEVERE,this.getClass(),"copyTo()",
					"cannot copy from a not ProtocolVersion instance");
		}
	}
	
}
