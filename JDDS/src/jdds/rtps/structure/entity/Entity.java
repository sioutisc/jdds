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

import jdds.rtps.RTPSAttribute;
import RTPS.GUID_t;



/**
 * From OMG RTPS Standard v2.1 p12: Base class for all RTPS entities. RTPS Entity
 * represents the class of objects that are visible to other RTPS Entities on the
 * network. As such, RTPS Entity objects have a globally-unique identifier (GUID)
 * and can be referenced inside RTPS messages.
 * 
 * @author Christos Sioutis <christos.sioutis@gmail.com>
 *
 */

public class Entity {
	//RTPS Attributes
	@RTPSAttribute protected GUID_t guid = new GUID_t();
	
	public GUID_t getGuid_t(){
		return guid;
	}
	
}
