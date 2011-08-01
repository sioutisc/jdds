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

package jdds.rtps.structure.entity;

import java.awt.List;
import java.util.Vector;

import RTPS.ENTITYID_PARTICIPANT;
import RTPS.EntityId_t;
import RTPS.Locator_t;
import RTPS.PROTOCOLVERSION;
import RTPS.ProtocolVersion_t;
import RTPS.VENDORID;
import RTPS.VendorId_t;

import jdds.rtps.RTPSAttribute;


/**
 * From OMG RTPS Standard v2.1 p13: Container of all RTPS entities that share 
 * common properties and are located in a single address space.
 * 
 * @author Christos Sioutis <christos.sioutis@gmail.com>
 *
 */

public class Participant extends Entity {
	@RTPSAttribute public ProtocolVersion_t protocolVersion = PROTOCOLVERSION.value;
	@RTPSAttribute public VendorId_t vendorId = VENDORID.JDDS;
	@RTPSAttribute public Vector<Locator_t> defaultUnicastLocatorList = new Vector<Locator_t>();
	@RTPSAttribute public Vector<Locator_t> defaultMulticastLocatorList = new Vector<Locator_t>();
	
	private Vector<Endpoint> endpoints_ = new Vector<Endpoint>();
	
	public Participant(){
		this.getGuid_t().entityId = ENTITYID_PARTICIPANT.value;
	}

}
