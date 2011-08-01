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
import jdds.rtps.messages.submessage.attribute.EntityId;
import jdds.rtps.messages.submessage.attribute.Flags;
import jdds.rtps.messages.submessage.attribute.ParameterList;
import jdds.rtps.messages.submessage.attribute.SequenceNumber;
import jdds.rtps.messages.submessage.attribute.SerializedPayload;

/**
 * From OMG RTPS Standard v2.1 p13: Represents the data that may be associated with
 * a change made to a data-object.
 * <p>
 * From OMG RTPS Standard v2.1 p47: Contains information regarding the value of
 * an application Date-object. Data Submessages are sent by Writers 
 * (NO_KEY Writer or WITH_KEY Writer) to Readers (NO_KEY Reader or WITH_KEY Reader).
 * 
 * From OMG RTPS Standard v2.1 p48: The Submessage notifies the RTPS Reader 
 * of a change to a data-object belonging to the RTPS Writer. The possible 
 * changes include both changes in value as well as changes to the lifecycle 
 * of the data-object.
 * 
 * @author Christos Sioutis <christos.sioutis@gmail.com>
 *
 */

public class Data extends Submessage {
	@RTPSAttribute public Flags extraFlags = new Flags();
	@RTPSAttribute public short octetsToInlineQos = 0;
	@RTPSAttribute public EntityId readerId;
	@RTPSAttribute public EntityId writerId;
	@RTPSAttribute public SequenceNumber writerSN;
	@RTPSAttribute public ParameterList inlineQos;
	@RTPSAttribute public SerializedPayload serializedData;
	
	public Data(SubmessageFlag endianessFlag
			   ,SubmessageFlag inlineQosFlag
			   ,SubmessageFlag dataFlag
			   ,SubmessageFlag keyFlag
			   ,EntityId readerId
			   ,EntityId writerId
			   ,SequenceNumber writerSN
			   ,ParameterList inlineQos
			   ,SerializedPayload serializedPayload
			   ){
		// TODO: construct object		
	}
}
