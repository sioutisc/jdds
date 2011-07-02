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

import java.util.TreeSet;

import RTPS.SequenceNumber_t;

import rtps.RTPSAttribute;
import rtps.messages.submessage.attribute.SequenceNumber;
import rtps.structure.cache.CacheChange;
import rtps.structure.entity.RTPSMethod;

/**
 * From OMG RTPS Standard v2.1 p13: Container class used to temporarily store and
 * manage sets of changes to data-objects. On the Writer side it contains the
 * history of the changes to data-objects made by the Writer. It is not necessary
 * that the full history of all changes ever made is maintained. Rather what is
 * needed is the partial history required to service existing and future matched
 * RTPS Reader endpoints. The partial history needed depends on the DDS QoS and the
 * state of the communications with the matched Reader endpoints. On the Reader
 * side it contains the history of the changes to data-objects made by the matched
 * RTPS Writer endpoints. It is not necessary that the full history of all changes
 * ever received is maintained. Rather what is needed is a partial history
 * containing the superposition of the changes received from the matched writers as
 * needed to satisfy the needs of the corresponding DDS DataReader. The rules for
 * this superposition and the amount of partial history required depend on the DDS
 * QoS and the state of the communication with the matched RTPS Writer endpoints.
 * 
 * @author Christos Sioutis <christos.sioutis@gmail.com>
 *
 */

public class HistoryCache {
	
	@RTPSAttribute TreeSet<CacheChange> changes = new TreeSet<CacheChange>();
	
	@RTPSMethod synchronized void add_change(CacheChange a_change){
		changes.add(a_change);
	}
	
	@RTPSMethod synchronized void remove_change(CacheChange a_change){
		changes.remove(a_change);
	}
		
	@RTPSMethod synchronized SequenceNumber get_seq_num_min(){
		return changes.first().sequenceNumber;
	}
	
	@RTPSMethod synchronized SequenceNumber get_seq_num_max(){
		return changes.last().sequenceNumber
		;		
	}

}
