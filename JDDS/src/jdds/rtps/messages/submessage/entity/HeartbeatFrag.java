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
import rtps.messages.submessage.Submessage;
import rtps.messages.submessage.SubmessageFlag;
import rtps.messages.submessage.attribute.Count;
import rtps.messages.submessage.attribute.EntityId;
import rtps.messages.submessage.attribute.FragmentNumber;
import rtps.messages.submessage.attribute.SequenceNumber;

/**
 * From OMG RTPS Standard v2.1 p43: For fragmented data, describes what fragments
 * are available in a Writer. HeartbeatFrag messages are sent by a Writer 
 * (NO_KEY Writer or WITH_KEY Writer) to one or more Readers (NO_KEY Reader or
 * WITH_KEY Reader).
 * <p>
 * From OMG RTPS Standard v2.1 p55: When fragmenting data and until all fragments are 
 * available, the HeartbeatFrag Submessage is sent from an RTPS Writer to an RTPS Reader
 * to communicate which fragments the Writer has available. This enables reliable
 * communication at the fragment level.
 * 
 * @author Christos Sioutis <christos.sioutis@gmail.com>
 *
 */

public class HeartbeatFrag extends Submessage{
	@RTPSAttribute public EntityId readerId;
	@RTPSAttribute public EntityId writerId;
	@RTPSAttribute public SequenceNumber writerSN;
	@RTPSAttribute public FragmentNumber lastFragmentNum;
	@RTPSAttribute public Count count;
	
	public HeartbeatFrag(SubmessageFlag endiannessFlag
						,EntityId readerId
						,EntityId writerId
						,SequenceNumber writerSN
						,FragmentNumber lastFragmentNum
						,Count count
						){
	}

}
