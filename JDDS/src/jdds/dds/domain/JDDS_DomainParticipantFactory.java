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

package jdds.dds.domain;

import java.util.Collection;

import org.omg.dds.core.Bootstrap;
import org.omg.dds.core.status.Status;
import org.omg.dds.domain.DomainParticipant;
import org.omg.dds.domain.DomainParticipantFactory;
import org.omg.dds.domain.DomainParticipantFactoryQos;
import org.omg.dds.domain.DomainParticipantListener;
import org.omg.dds.domain.DomainParticipantQos;

public class JDDS_DomainParticipantFactory extends DomainParticipantFactory {

	@Override
	public Bootstrap getBootstrap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DomainParticipant createParticipant() {
		return new JDDS_DomainParticipant();
	}

	@Override
	public DomainParticipant createParticipant(int domainId) {
		return new JDDS_DomainParticipant(domainId);
	}

	@Override
	public DomainParticipant createParticipant(int domainId,
			DomainParticipantQos qos, DomainParticipantListener listener,
			Collection<Class<? extends Status<?, ?>>> statuses) {
		return new JDDS_DomainParticipant(domainId,qos,listener,statuses);
	}

	@Override
	public DomainParticipant createParticipant(int domainId,
			String qosLibraryName, String qosProfileName,
			DomainParticipantListener listener,
			Collection<Class<? extends Status<?, ?>>> statuses) {
		return new JDDS_DomainParticipant(domainId,qosLibraryName,qosProfileName,listener,statuses);
	}

	@Override
	public DomainParticipant lookupParticipant(int domainId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DomainParticipantFactoryQos getQos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setQos(DomainParticipantFactoryQos qos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DomainParticipantQos getDefaultParticipantQos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDefaultParticipantQos(DomainParticipantQos qos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDefaultParticipantQos(String qosLibraryName,
			String qosProfileName) {
		// TODO Auto-generated method stub
		
	}

}
