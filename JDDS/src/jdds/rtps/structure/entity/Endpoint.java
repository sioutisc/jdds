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

import java.util.Vector;

import jdds.rtps.RTPSAttribute;

import RTPS.Locator_t;
import RTPS.ReliabilityKind_t;
import RTPS.TopicKind_t;


/**
 * From OMG RTPS Standard v2.1 p12: Specialization of RTPS Entity representing the
 * objects that can be communication endpoints. That is, the objects that can be
 * the sources or destinations of RTPS messages.
 * 
 * @author Christos Sioutis <christos.sioutis@gmail.com>
 *
 */

public class Endpoint extends Entity {
	@RTPSAttribute public TopicKind_t topicKind = null;
	@RTPSAttribute public ReliabilityKind_t reliabilityLevel = null;
	@RTPSAttribute public Vector<Locator_t> unicastLocatorList = new Vector<Locator_t>();
	@RTPSAttribute public Vector<Locator_t> multicastLocatorList = new Vector<Locator_t>();

	public Endpoint(Participant participant) {
		this.getGuid_t().guidPrefix = participant.getGuid_t().guidPrefix;
	}

}
