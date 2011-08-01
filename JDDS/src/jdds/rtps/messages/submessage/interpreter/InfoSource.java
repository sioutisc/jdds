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


package jdds.rtps.messages.submessage.interpreter;

import jdds.rtps.RTPSAttribute;
import jdds.rtps.messages.submessage.Submessage;
import jdds.rtps.messages.submessage.SubmessageFlag;
import jdds.rtps.messages.submessage.attribute.GuidPrefix;
import jdds.rtps.messages.submessage.attribute.ProtocolVersion;
import jdds.rtps.messages.submessage.attribute.VendorId;


/**
 * From OMG RTPS Standard v2.1 p44: Provides information about the source from 
 * which subsequent Entity Submessages originated. This Submessage is primarily 
 * used for relaying RTPS Submessages. This is not discussed in the current specification.
 * 
 * From OMG RTPS Standard v2.1 p58: This message modifies the logical source of the
 * Submessages that follow.
 * 
 * @author Christos Sioutis <christos.sioutis@gmail.com>
 *
 */

public class InfoSource extends Submessage {
	@RTPSAttribute public ProtocolVersion protocolVersion;
	@RTPSAttribute public VendorId vendorId;
	@RTPSAttribute public GuidPrefix guidPrefix;
	
	public InfoSource(SubmessageFlag endiannessFlag
					 ,ProtocolVersion protocolVersion
					 ,VendorId vendorId
					 ,GuidPrefix guidPrefix
					 ){
		
	}
}
