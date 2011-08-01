/* ********************************************************************* *
 *                                                                       *
 *   =============================================================       *
 *   Copyright 2011                                                      *
 *   Christos Sioutis <christos.sioutis@gmail.com>                       *
 *   =============================================================       *
 *                                                                       *
 *   This file is part of jdds.                                          *
 *                                                                       *
 *   jdds is free software: you can redistribute it and/or               *
 *   modify it under the terms of the GNU General Public License         *
 *   as published by the Free Software Foundation, either version 3 of   *
 *   the License, or (at your option) any later version.                 *
 *                                                                       *
 *   jdds is distributed in the hope that it will be useful,             *
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of      *
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the       *
 *   GNU General Public License for more details.                        *
 *                                                                       *
 *   You should have received a copy of the GNU General Public           *
 *   License along with jdds.                                            *
 *   If not, see <http://www.gnu.org/licenses/>.                         *
 *                                                                       *
 * ********************************************************************* */

package jdds.rtps.messages.message;

import java.util.Vector;

import RTPS.GUIDPREFIX_UNKNOWN;
import RTPS.GuidPrefix_t;
import RTPS.LOCATOR_INVALID;
import RTPS.Locator_t;
import RTPS.PROTOCOLVERSION;
import RTPS.ProtocolVersion_t;
import RTPS.TIME_INVALID;
import RTPS.Time_t;
import RTPS.VENDORID_UNKNOWN;
import RTPS.VendorId_t;
import jdds.rtps.RTPSAttribute;
import jdds.rtps.messages.message.Header;

/**
 * From OMG RTPS Standard v2.1 p35: The interpretation and meaning of a Submessage
 * within a Message may depend on the previous Submessages contained within that 
 * same Message. Therefore, the receiver of a Message must maintain state from 
 * previously deserialized Submessages in the same Message. This state is modeled
 * as the state of an RTPS Receiver that is reset each time a new message is
 * processed and provides context for the interpretation of each Submessage.
 *
 * @author Christos Sioutis <christos.sioutis@gmail.com>
 */
public class Receiver {
	
	@RTPSAttribute ProtocolVersion_t sourceVersion = PROTOCOLVERSION.value;
	@RTPSAttribute VendorId_t sourceVendorId = VENDORID_UNKNOWN.value;
	@RTPSAttribute GuidPrefix_t sourceGuidPrefix = GUIDPREFIX_UNKNOWN.value;
	@RTPSAttribute GuidPrefix_t destGuidPrefix;
	@RTPSAttribute Vector<Locator_t> unicastReplyLocatorList = new Vector<Locator_t>();
	@RTPSAttribute Vector<Locator_t> multicastReplyLocatorList = new Vector<Locator_t>();
	@RTPSAttribute boolean haveTimestamp;
	@RTPSAttribute Time_t timestamp;
	
	public Receiver(GuidPrefix_t destination){
		destGuidPrefix = destination;
		unicastReplyLocatorList.add(LOCATOR_INVALID.value);
		multicastReplyLocatorList.add(LOCATOR_INVALID.value);
		haveTimestamp = false;
		timestamp = TIME_INVALID.value;
	}
	
	public Receiver(Header header){
		sourceGuidPrefix = header.guidPrefix;
		sourceVersion = header.version;
		sourceVendorId = header.vendorId;
		haveTimestamp = false;
	}

}
