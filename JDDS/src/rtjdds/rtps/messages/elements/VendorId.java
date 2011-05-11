//================================================================
//
//	VendorId.java - Created by kerush, 2006 20-ott-06 1:27:17 
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

import java.util.Arrays;

import org.omg.CORBA.portable.OutputStream;

import rtjdds.rtps.types.VendorId_t;
import rtjdds.rtps.types.VendorId_tHelper;
import rtjdds.util.BitUtility;
import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;
import rtjdds.util.collections.Copyable;

/**
 * @author kerush
 *
 */
public class VendorId extends SubmessageElement implements Copyable {
	
	protected VendorId_t value;
	
	public VendorId(VendorId_t value) {
		super(SubmessageElement.VENDOR_ID_SIZE);
		this.value = value;
	}
	
	public VendorId_t getValue() {
		return value;
	}
	
	public boolean equals(Object obj) {
		return Arrays.equals(value.vendorId,((VendorId)obj).value.vendorId);
	}
	
	public void write(OutputStream os) {
		VendorId_tHelper.write(os, value);
	}
	
	public String toString() {
		return BitUtility.getHexString(value.vendorId);
	}

	public void copyFrom(Copyable obj) {
		if (obj instanceof VendorId) {
			VendorId src = (VendorId) obj;
			value = src.value;
			//value.copyFrom(src);
		}
		else {
			GlobalProperties.logger.log(Logger.SEVERE,this.getClass(),"copyFrom()",
					"cannot copy from a not VendorId instance");
		}
	}

	public void copyTo(Copyable obj) {
		if (obj instanceof VendorId) {
			VendorId dst = (VendorId) obj;;
			//value.copyTo(dst);
			dst.value = value;
		}
		else {
			GlobalProperties.logger.log(Logger.SEVERE,this.getClass(),"copyFrom()",
					"cannot copy from a not VendorId instance");
		}
	}
	
	
	
}
