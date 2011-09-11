package jdds.dds.core;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.omg.dds.core.Duration;
import org.omg.dds.core.GuardCondition;
import org.omg.dds.core.InstanceHandle;
import org.omg.dds.core.Time;
import org.omg.dds.core.WaitSet;
import org.omg.dds.core.modifiable.ModifiableDuration;
import org.omg.dds.core.modifiable.ModifiableInstanceHandle;
import org.omg.dds.core.modifiable.ModifiableTime;
import org.omg.dds.core.policy.QosPolicy;
import org.omg.dds.core.policy.QosPolicy.Id;
import org.omg.dds.core.status.DataAvailableStatus;
import org.omg.dds.core.status.DataOnReadersStatus;
import org.omg.dds.core.status.InconsistentTopicStatus;
import org.omg.dds.core.status.LivelinessChangedStatus;
import org.omg.dds.core.status.LivelinessLostStatus;
import org.omg.dds.core.status.OfferedDeadlineMissedStatus;
import org.omg.dds.core.status.OfferedIncompatibleQosStatus;
import org.omg.dds.core.status.PublicationMatchedStatus;
import org.omg.dds.core.status.RequestedDeadlineMissedStatus;
import org.omg.dds.core.status.RequestedIncompatibleQosStatus;
import org.omg.dds.core.status.SampleLostStatus;
import org.omg.dds.core.status.SampleRejectedStatus;
import org.omg.dds.core.status.Status;
import org.omg.dds.core.status.SubscriptionMatchedStatus;
import org.omg.dds.domain.DomainParticipantFactory;
import org.omg.dds.sub.InstanceState;
import org.omg.dds.sub.SampleState;
import org.omg.dds.sub.ViewState;
import org.omg.dds.topic.BuiltinTopicKey;
import org.omg.dds.topic.ParticipantBuiltinTopicData;
import org.omg.dds.topic.PublicationBuiltinTopicData;
import org.omg.dds.topic.SubscriptionBuiltinTopicData;
import org.omg.dds.topic.TopicBuiltinTopicData;
import org.omg.dds.type.TypeSupport;
import org.omg.dds.type.dynamic.DynamicDataFactory;
import org.omg.dds.type.dynamic.DynamicTypeFactory;

public class Bootstrap extends org.omg.dds.core.Bootstrap{
	
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
			public Set<InstanceState> notAliveInstanceStateSet() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Set<Class<? extends Status<?, ?>>> noStatusKinds() {
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
				return null;
			}
			
			@Override
			public TopicBuiltinTopicData newTopicBuiltinTopicData() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public ModifiableTime newTime(long time, TimeUnit units) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public <TYPE> SubscriptionMatchedStatus<TYPE> newSubscriptionMatchedStatus() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public SubscriptionBuiltinTopicData newSubscriptionBuiltinTopicData() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public <TYPE> SampleRejectedStatus<TYPE> newSampleRejectedStatus() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public <TYPE> SampleLostStatus<TYPE> newSampleLostStatus() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public <TYPE> RequestedIncompatibleQosStatus<TYPE> newRequestedIncompatibleQosStatus() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public <TYPE> RequestedDeadlineMissedStatus<TYPE> newRequestedDeadlineMissedStatus() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public <TYPE> PublicationMatchedStatus<TYPE> newPublicationMatchedStatus() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public PublicationBuiltinTopicData newPublicationBuiltinTopicData() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public ParticipantBuiltinTopicData newParticipantBuiltinTopicData() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public <TYPE> OfferedIncompatibleQosStatus<TYPE> newOfferedIncompatibleQosStatus() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public <TYPE> OfferedDeadlineMissedStatus<TYPE> newOfferedDeadlineMissedStatus() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public <TYPE> LivelinessLostStatus<TYPE> newLivelinessLostStatus() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public <TYPE> LivelinessChangedStatus<TYPE> newLivelinessChangedStatus() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public ModifiableInstanceHandle newInstanceHandle() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public <TYPE> InconsistentTopicStatus<TYPE> newInconsistentTopicStatus() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public GuardCondition newGuardCondition() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public ModifiableDuration newDuration(long duration, TimeUnit unit) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public DataOnReadersStatus newDataOnReadersStatus() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public <TYPE> DataAvailableStatus<TYPE> newDataAvailableStatus() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public BuiltinTopicKey newBuiltinTopicKey() {
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
			public Id getQosPolicyId(Class<? extends QosPolicy<?, ?>> policyClass) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public DomainParticipantFactory getParticipantFactory() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public DynamicDataFactory getDataFactory() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Set<ViewState> anyViewStateSet() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Set<SampleState> anySampleStateSet() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Set<InstanceState> anyInstanceStateSet() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Set<Class<? extends Status<?, ?>>> allStatusKinds() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
	

}
