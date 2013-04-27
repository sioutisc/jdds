/* ********************************************************************* *
 *                                                                       *
 *   =============================================================       *
 *   Copyright 2012                                                      *
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

package jdds.dds.core;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.omg.dds.core.Duration;
import org.omg.dds.core.GuardCondition;
import org.omg.dds.core.InstanceHandle;
import org.omg.dds.core.ModifiableInstanceHandle;
import org.omg.dds.core.ModifiableTime;
import org.omg.dds.core.QosProvider;
import org.omg.dds.core.ServiceEnvironment;
import org.omg.dds.core.Time;
import org.omg.dds.core.WaitSet;
import org.omg.dds.core.policy.PolicyFactory;
import org.omg.dds.core.status.Status;
import org.omg.dds.domain.DomainParticipantFactory;
import org.omg.dds.type.TypeSupport;
import org.omg.dds.type.builtin.KeyedBytes;
import org.omg.dds.type.builtin.KeyedString;
import org.omg.dds.type.dynamic.DynamicDataFactory;
import org.omg.dds.type.dynamic.DynamicTypeFactory;

import jdds.dds.domain.JDDS_DomainParticipantFactory;
import jdds.dds.type.JDDS_TypeSupport;


public class JDDS_ServiceEnvironment extends ServiceEnvironment{
	
	static final DomainParticipantFactory factory_ = new JDDS_DomainParticipantFactory();

	@Override
	public ServiceProviderInterface getSPI() {
		// TODO Auto-generated method stub
		return new ServiceProviderInterface() {
			
			@Override
			public Duration zeroDuration() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Set<Class<? extends Status>> noStatusKinds() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public InstanceHandle nilHandle() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public WaitSet newWaitSet() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public <TYPE> TypeSupport<TYPE> newTypeSupport(Class<TYPE> type,
					String registeredName) {
				// TODO Auto-generated method stub
				return new JDDS_TypeSupport<TYPE>();
			}
			
			@Override
			public ModifiableTime newTime(long time, TimeUnit units) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public QosProvider newQosProvider(String uri, String profile) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public KeyedString newKeyedString() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public KeyedBytes newKeyedBytes() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public ModifiableInstanceHandle newInstanceHandle() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public GuardCondition newGuardCondition() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Duration newDuration(long duration, TimeUnit unit) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Time invalidTime() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Duration infiniteDuration() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public DynamicTypeFactory getTypeFactory() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public PolicyFactory getPolicyFactory() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public DomainParticipantFactory getParticipantFactory() {
				// TODO Auto-generated method stub
				return factory_;
			}
			
			@Override
			public DynamicDataFactory getDynamicDataFactory() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Set<Class<? extends Status>> allStatusKinds() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
	

}
