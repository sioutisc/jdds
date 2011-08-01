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


/**
 *  From OMG RTPS Standard v2.1 p44: Provides information about the final destination
 *  of subsequent Entity Submessages. This Submessage is primarily used for relaying 
 *  RTPS Submessages. This is not discussed in the current specification.
 * <p>
 * From OMG RTPS Standard v2.1 p56: This message is sent from an RTPS Writer to an 
 * RTPS Reader to modify the GuidPrefix used to interpret the Reader entityIds appearing
 *  in the Submessages that follow it.
 *  
 * @author Christos Sioutis <christos.sioutis@gmail.com>
 *
 */

public class InfoDestination extends Submessage {
	@RTPSAttribute public GuidPrefix guidPrefix;
	
	public InfoDestination(SubmessageFlag endinannessFlag
						  ,GuidPrefix guidPrefix
						  ){
		
	}
}
