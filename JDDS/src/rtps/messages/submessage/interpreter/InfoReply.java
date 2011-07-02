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

package rtps.messages.submessage.interpreter;

import rtps.RTPSAttribute;
import rtps.messages.submessage.Submessage;
import rtps.messages.submessage.SubmessageFlag;
import rtps.messages.submessage.attribute.LocatorList;


/**
 * From OMG RTPS Standard v2.1 p44: Provides information about where to reply to the 
 * entities that appear in subsequent Submessages
 * 
 * From OMG RTPS Standard v2.1 p57: This message is sent from an RTPS Reader to an 
 * RTPS Writer. It contains explicit information on where to send a reply to the 
 * Submessages that follow it within the same message.
 * 
 * @author Christos Sioutis <christos.sioutis@gmail.com>
 *
 */

public class InfoReply extends Submessage {
	@RTPSAttribute public LocatorList multicastLocatorList;
	@RTPSAttribute public LocatorList unicastLocatorList;	
	
	public InfoReply(SubmessageFlag endiannessFlag
					,SubmessageFlag multicastFlag
					,LocatorList unicastLocatorList
					,LocatorList multicastLocatorList
					){
	}
	
}
