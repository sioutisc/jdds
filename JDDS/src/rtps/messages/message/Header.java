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

package rtps.messages.message;

import RTPS.GuidPrefix_t;
import RTPS.PROTOCOLVERSION;
import RTPS.ProtocolId_t;
import RTPS.ProtocolVersion_t;
import RTPS.VENDORID;
import RTPS.VendorId_t;
import rtps.RTPSAttribute;


public class Header {
	@RTPSAttribute public ProtocolId_t protocol = ProtocolId_t.PROTOCOL_RTPS;
	@RTPSAttribute public ProtocolVersion_t version = PROTOCOLVERSION.value;
	@RTPSAttribute public VendorId_t vendorId = VENDORID.JDDS;
	@RTPSAttribute public GuidPrefix_t guidPrefix;
	
	public Header(GuidPrefix_t prefix){
		guidPrefix = prefix;
	}

}
