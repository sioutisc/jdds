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

package rtps.structure.cache;

import DDS.InstanceHandle_tHelper;
import RTPS.ChangeKind_t;
import RTPS.GUID_t;
import RTPS.InstanceHandle_t;
import RTPS.SequenceNumber_t;
import rtps.RTPSAttribute;
import rtps.messages.submessage.attribute.SequenceNumber;
import rtps.messages.submessage.entity.Data;

/**
 * From OMG RTPS Standard v2.1 p13: Represents an individual change made to a
 * data-object. Includes the creation, modification, and deletion of data-objects.
 * 
 * @author Christos Sioutis <christos.sioutis@gmail.com>
 *
 */

public class CacheChange implements Comparable<CacheChange> {
	@RTPSAttribute public ChangeKind_t kind = null;
	@RTPSAttribute public GUID_t writerGuid = null;
	@RTPSAttribute public InstanceHandle_t instanceHandle = null;
	@RTPSAttribute public SequenceNumber sequenceNumber = null;
	@RTPSAttribute public Data data_value = null;
	
	public CacheChange(ChangeKind_t ck, GUID_t g, InstanceHandle_t ih, SequenceNumber_t sn, Data d){
		kind = ck;
		writerGuid = g;
		instanceHandle = ih;
		sequenceNumber = new SequenceNumber(sn);
		data_value = d;
	}

	@Override
	public int compareTo(CacheChange o) {
		return this.sequenceNumber.compareTo(o.sequenceNumber);
	}

}
