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

import jdds.rtps.RTPSAttribute;
import jdds.rtps.messages.submessage.Submessage;
import jdds.rtps.messages.submessage.SubmessageFlag;
import jdds.rtps.messages.submessage.attribute.Count;
import jdds.rtps.messages.submessage.attribute.EntityId;
import jdds.rtps.messages.submessage.attribute.FragmentNumber;
import jdds.rtps.messages.submessage.attribute.FragmentNumberSet;
import jdds.rtps.messages.submessage.attribute.SequenceNumber;


/**
 * From OMG RTPS Standard v2.1 p44: Provides information on the state 
 * of a Reader to a Writer, more specifically what fragments the Reader 
 * is still missing. NackFrag messages are sent by a Reader to one or 
 * more Writers.
 * <p>
 * From OMG RTPS Standard v2.1 p60: The NackFrag Submessage is used to communicate 
 * the state of a Reader to a Writer. When a data change is sent as a series of fragments,
 * the NackFrag Submessage allows the Reader to inform the Writer about specific fragment
 * numbers it is still missing. This Submessage can only contain negative acknowledgements.
 * Note this differs from an AckNack Submessage, which includes both positive and negative 
 * acknowledgements. 
 * 
 * @author Christos Sioutis <christos.sioutis@gmail.com>
 *
 */

public class NackFrag extends Submessage {
	@RTPSAttribute public EntityId readerId;
	@RTPSAttribute public EntityId writerId;
	@RTPSAttribute public FragmentNumber fragmentNumberState;
	@RTPSAttribute public Count count;
	@RTPSAttribute public SequenceNumber writerSN;
	
	public NackFrag(SubmessageFlag endiannessFlag
			,EntityId readerId
			,EntityId writerId
			,SequenceNumber writerSN
			,FragmentNumberSet lastFragmentNumberState
			,Count count
			){
}
	
}
