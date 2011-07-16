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

package rtps.messages.submessage.entity;

import rtps.RTPSAttribute;
import rtps.messages.submessage.*;
import rtps.messages.submessage.attribute.*;

/**
 * From OMG RTPS Standard v2.1 p44: Provides information on the state of a 
 * Reader to a Writer. AckNack messages are sent by a Reader to one or more Writers.
 * <p>
 * From OMG RTPS Standard v2.1 p46: This Submessage is used to communicate the state of a Reader to a Writer. 
 * The Submessage allows the Reader to inform the Writer about the sequence 
 * numbers it has received and which ones it is still missing. This Submessage
 * can be used to do both positive and negative acknowledgments.
 * 
 * @author Christos Sioutis <christos.sioutis@gmail.com>
 *
 */

public class AckNack extends Submessage {
	
	public SubmessageHeader header;
	/**EntityId Identifies the Reader entity that acknowledges receipt 
	 * of certain sequence numbers and/or requests to receive 
	 * certain sequence numbers. */
	@RTPSAttribute public EntityId readerId;
	/** Identifies the Writer entity that is the target of the AckNack message.
	 *  This is the Writer Entity that is being asked to re-send some sequence 
	 *  numbers or is being informed of the reception of certain sequence numbers. */
	@RTPSAttribute public EntityId writerId;
	/** Communicates the state of the reader to the writer. All sequence numbers
	 *  up to the one prior to readerSNState.base are confirmed as received by
	 *  the reader. The sequence numbers that appear in the set indicate missing
	 *  sequence numbers on the reader side. The ones that do not appear in the
	 *  set are undetermined (could be received or not). */
	@RTPSAttribute public SequenceNumberSet readerSNState;
	/** A counter that is incremented each time a new AckNack message is sent. 
	 *  Provides the means for a Writer to detect duplicate AckNack messages that 
	 *  can result from the presence of redundant communication paths. */
	@RTPSAttribute public Count count;
		
	public AckNack(SubmessageFlag endianessFlag
			      ,SubmessageFlag finalFlag
			      ,EntityId readerId
			      ,EntityId writerId
			      ,SequenceNumberSet readerSNState
			      ,Count count
			      ){
		
		SubmessageFlag[] flags = {endianessFlag,finalFlag};
		
		// TODO: store the appropriate parameters
		
	}
}
