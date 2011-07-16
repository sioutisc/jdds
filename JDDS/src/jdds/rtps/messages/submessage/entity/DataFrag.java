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

import rtjdds.rtps.messages.elements.ParameterList;
import rtps.RTPSAttribute;
import rtps.messages.submessage.Submessage;
import rtps.messages.submessage.SubmessageFlag;
import rtps.messages.submessage.attribute.EntityId;
import rtps.messages.submessage.attribute.Flags;
import rtps.messages.submessage.attribute.FragmentNumber;
import rtps.messages.submessage.attribute.SequenceNumber;
import rtps.messages.submessage.attribute.SerializedPayload;


/**
 * From OMG RTPS Standard v2.1 p43: Equivalent to Data, but only contains a part
 * of the new value (one or more fragments). Allows data to be transmitted as
 * multiple fragments to overcome transport message size limitations
 * <p>
 * From OMG RTPS Standard v2.1 p49: The DataFrag Submessage extends the Data 
 * Submessage by enabling the serializedData to be fragmented and sent as multiple
 * DataFrag Submessages. The fragments contained in the DataFrag Submessages are 
 * then re-assembled by the RTPS Reader. 
 *  
 * @author Christos Sioutis <christos.sioutis@gmail.com>
 *
 */

public class DataFrag extends Submessage {
	@RTPSAttribute public Flags extraFlags = new Flags();
	@RTPSAttribute public short octetsToInlineQos = 0;
	@RTPSAttribute public EntityId readerId;
	@RTPSAttribute public EntityId writerId;
	@RTPSAttribute public SequenceNumber writerSN;
	@RTPSAttribute public ParameterList inlineQos;
	@RTPSAttribute public SerializedPayload serializedData;
	@RTPSAttribute public FragmentNumber fragmentStartingNum;
	@RTPSAttribute public short fragmentsInSubmessage;
	@RTPSAttribute public long dataSize;
	@RTPSAttribute public long fragmentSize;
	
	public DataFrag(SubmessageFlag endianessFlag
			   	   ,SubmessageFlag inlineQosFlag
				   ,EntityId readerId
				   ,EntityId writerId
				   ,SequenceNumber writerSN
				   ,FragmentNumber fragmentStartingNum
				   ,short fragmentsInSubmessage
				   ,long dataSize
				   ,short fragmentSize
				   ,ParameterList inlineQos
				   ,SerializedPayload serializedPayload
				   ){
		//TODO: Construct object
	}
}
