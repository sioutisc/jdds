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

package jdds.rtps.structure.entity;

import jdds.rtps.RTPSAttribute;
import jdds.rtps.structure.cache.HistoryCache;

/**
 * From OMG RTPS Standard v2.1 p13: Specialization of RTPS Endpoint representing
 * the objects that can be the sources of messages communicating CacheChanges.
 * 
 * @author Christos Sioutis <christos.sioutis@gmail.com>
 *
 */
public class Writer extends Endpoint {
	@RTPSAttribute public HistoryCache writer_cache = new HistoryCache();
	
	public Writer(Participant participant){
		super(participant);
	}
	
	@RTPSMethod public void new_change(){
		
	}

	
}
