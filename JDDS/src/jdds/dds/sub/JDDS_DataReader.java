package jdds.dds.sub;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.omg.dds.core.Duration;
import org.omg.dds.core.InstanceHandle;
import org.omg.dds.core.ModifiableInstanceHandle;
import org.omg.dds.core.ServiceEnvironment;
import org.omg.dds.core.StatusCondition;
import org.omg.dds.core.status.LivelinessChangedStatus;
import org.omg.dds.core.status.RequestedDeadlineMissedStatus;
import org.omg.dds.core.status.RequestedIncompatibleQosStatus;
import org.omg.dds.core.status.SampleLostStatus;
import org.omg.dds.core.status.SampleRejectedStatus;
import org.omg.dds.core.status.Status;
import org.omg.dds.core.status.SubscriptionMatchedStatus;
import org.omg.dds.sub.DataReader;
import org.omg.dds.sub.DataReaderListener;
import org.omg.dds.sub.DataReaderQos;
import org.omg.dds.sub.QueryCondition;
import org.omg.dds.sub.ReadCondition;
import org.omg.dds.sub.Sample;
import org.omg.dds.sub.Sample.Iterator;
import org.omg.dds.sub.Subscriber;
import org.omg.dds.sub.Subscriber.DataState;
import org.omg.dds.topic.PublicationBuiltinTopicData;
import org.omg.dds.topic.TopicDescription;

public class JDDS_DataReader<TYPE> implements DataReader<TYPE>{

	@Override
	public DataReaderListener<TYPE> getListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setListener(DataReaderListener<TYPE> listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setListener(DataReaderListener<TYPE> listener,
			Collection<Class<? extends Status>> statuses) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DataReaderQos getQos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setQos(DataReaderQos qos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setQos(String qosLibraryName, String qosProfileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Class<? extends Status>> getStatusChanges() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InstanceHandle getInstanceHandle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void retain() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ServiceEnvironment getEnvironment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <OTHER> DataReader<OTHER> cast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReadCondition<TYPE> createReadCondition(DataState states) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryCondition<TYPE> createQueryCondition(String queryExpression,
			List<String> queryParameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryCondition<TYPE> createQueryCondition(DataState states,
			String queryExpression, List<String> queryParameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void closeContainedEntities() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TopicDescription<TYPE> getTopicDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SampleRejectedStatus getSampleRejectedStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LivelinessChangedStatus getLivelinessChangedStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RequestedDeadlineMissedStatus getRequestedDeadlineMissedStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RequestedIncompatibleQosStatus getRequestedIncompatibleQosStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubscriptionMatchedStatus getSubscriptionMatchedStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SampleLostStatus getSampleLostStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void waitForHistoricalData(Duration maxWait) throws TimeoutException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void waitForHistoricalData(long maxWait, TimeUnit unit)
			throws TimeoutException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<InstanceHandle> getMatchedPublications() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PublicationBuiltinTopicData getMatchedPublicationData(
			InstanceHandle publicationHandle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<TYPE> read() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<TYPE> read(org.omg.dds.sub.DataReader.Selector<TYPE> query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<TYPE> read(int maxSamples) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sample<TYPE>> read(List<Sample<TYPE>> samples) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sample<TYPE>> read(List<Sample<TYPE>> samples,
			org.omg.dds.sub.DataReader.Selector<TYPE> selector) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<TYPE> take() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<TYPE> take(int maxSamples) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<TYPE> take(org.omg.dds.sub.DataReader.Selector<TYPE> query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sample<TYPE>> take(List<Sample<TYPE>> samples) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sample<TYPE>> take(List<Sample<TYPE>> samples,
			org.omg.dds.sub.DataReader.Selector<TYPE> query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean readNextSample(Sample<TYPE> sample) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean takeNextSample(Sample<TYPE> sample) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TYPE getKeyValue(TYPE keyHolder, InstanceHandle handle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModifiableInstanceHandle lookupInstance(
			ModifiableInstanceHandle handle, TYPE keyHolder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InstanceHandle lookupInstance(TYPE keyHolder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StatusCondition<DataReader<TYPE>> getStatusCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subscriber getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public org.omg.dds.sub.DataReader.Selector<TYPE> select() {
		// TODO Auto-generated method stub
		return null;
	}

}
