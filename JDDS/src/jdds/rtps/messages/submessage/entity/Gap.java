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

package jdds.rtps.messages.submessage.entity;

import RTPS.SequenceNumberSet;
import jdds.rtps.RTPSAttribute;
import jdds.rtps.messages.submessage.Submessage;
import jdds.rtps.messages.submessage.SubmessageFlag;
import jdds.rtps.messages.submessage.attribute.EntityId;
import jdds.rtps.messages.submessage.attribute.SequenceNumber;


/**
 * From OMG RTPS Standard v2.1 p43: Describes the information that is no 
 * longer relevant to Readers. Gap messages are sent by a Writer to one 
 * or more Readers.
 * <p>
 * From OMG RTPS Standard v2,1 p52: This Submessage is sent from an RTPS Writer
 * to an RTPS Reader and indicates to the RTPS Reader that a range of sequence 
 * numbers is no longer relevant. The set may be a contiguous range of sequence 
 * numbers or a specific set of sequence numbers.
 * 
 * @author Christos Sioutis <christos.sioutis@gmail.com>
 *
 */

public class Gap extends Submessage {
	@RTPSAttribute public EntityId readerId;
	@RTPSAttribute public EntityId writerId;
	@RTPSAttribute public SequenceNumber gapStart;
	@RTPSAttribute public SequenceNumberSet gapList;
	
	public Gap(SubmessageFlag endiannessFlag
			  ,EntityId readerId
			  ,EntityId writerId
			  ,SequenceNumber gapStart
			  ,SequenceNumberSet gapList
			  ){
		
	}
}
