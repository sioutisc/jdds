//================================================================
//
//	DomainParticipantFactory.java - Created by kerush, 2006 8-dic-06 4:49:05 
//
//================================================================
//
//					  R T  j  D D S  version 0.1
//
//				Copyright (C) 2006 by Vincenzo Caruso
//					   <bitclash[at]gmail.com>
//
// This program is free software; you can redistribute it and/or
// modify it under  the terms of  the GNU General Public License
// as published by the Free Software Foundation;either version 2
// of the License, or (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or  FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// A copy of the GNU  General Public License  is available in the
// file named LICENSE,  that  should  be  shipped along with this
// package;  if not,  write to the Free Software Foundation, Inc., 
// 51 Franklin Street, Fifth Floor,   Boston, MA  02110-1301, USA.
//
//================================================================
package rtjdds.rtps.structure.local;

import java.io.IOException;

import rtjdds.rtps.exceptions.RTjDDSException;
import rtjdds.rtps.messages.elements.GuidPrefix;
import rtjdds.util.GUIDPrefixFactory;
import DDS.DomainParticipant;
import DDS.DomainParticipantListener;
import DDS.DomainParticipantQos;

public class DomainParticipantFactory /*implements DDS.DomainParticipantFactory*/ {

	public static DomainParticipant create_participant(int domainId,
			DomainParticipantQos qos, DomainParticipantListener a_listener) {
		
		ParticipantImpl participant = null;
		
		GuidPrefix prefix = GUIDPrefixFactory.newGuidPrefix();
		
		try {
			participant =  new ParticipantImpl(prefix,domainId,qos,a_listener);
		} catch (RTjDDSException e) {
			e.printStackTrace();
		} 
		
		return participant;
		
	}

	public static int delete_participant(DomainParticipant a_participant) {
		// TODO Auto-generated method stub
		return 0;
	}

	public DomainParticipant lookup_participant(int domainId) {
		// TODO Auto-generated method stub
		return null;
	}

	public int set_default_participant_qos(DomainParticipantQos qos) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void get_default_participant_qos(DomainParticipantQos qos) {
		// TODO Auto-generated method stub

	}

}
