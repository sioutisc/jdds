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
import rtps.messages.submessage.attribute.Timestamp;



/**
 * From OMG RTPS Standard v2.1 p44: Provides a source timestamp for subsequent Entity Submessages
 * 
 * From OMG RTPS Standard v2.1 p59: This Submessage is used to send a timestamp which applies to 
 * the Submessages that follow within the same message.
 * 
 * @author Christos Sioutis <christos.sioutis@gmail.com>
 *
 */

public class InfoTimestamp extends Submessage {
	@RTPSAttribute Timestamp timestamp;
	
	public InfoTimestamp(SubmessageFlag endiannessFlag
						,SubmessageFlag invalidateFlag
						,Timestamp timestamp
						){
		
	}
}
