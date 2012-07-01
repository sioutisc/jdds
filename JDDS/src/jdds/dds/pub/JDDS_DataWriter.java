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

package jdds.dds.pub;

import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.omg.dds.core.Bootstrap;
import org.omg.dds.core.Duration;
import org.omg.dds.core.InstanceHandle;
import org.omg.dds.core.StatusCondition;
import org.omg.dds.core.Time;
import org.omg.dds.core.modifiable.ModifiableInstanceHandle;
import org.omg.dds.core.status.LivelinessLostStatus;
import org.omg.dds.core.status.OfferedDeadlineMissedStatus;
import org.omg.dds.core.status.OfferedIncompatibleQosStatus;
import org.omg.dds.core.status.PublicationMatchedStatus;
import org.omg.dds.core.status.Status;
import org.omg.dds.pub.DataWriter;
import org.omg.dds.pub.DataWriterListener;
import org.omg.dds.pub.DataWriterQos;
import org.omg.dds.pub.Publisher;
import org.omg.dds.topic.SubscriptionBuiltinTopicData;
import org.omg.dds.topic.Topic;

public class JDDS_DataWriter<TYPE> implements DataWriter<TYPE>{
	Topic<TYPE> topic_;
	Publisher pub_;
	
	public JDDS_DataWriter(Publisher pub, Topic<TYPE> topic) {
		pub_ = pub;
		topic_ = topic;
	}


	@Override
	public Publisher getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataWriterListener<TYPE> getListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setListener(DataWriterListener<TYPE> listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DataWriterQos getQos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setQos(DataWriterQos qos) {
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
	public StatusCondition<DataWriter<TYPE>> getStatusCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Class<? extends Status<?, ?>>> getStatusChanges(
			Collection<Class<? extends Status<?, ?>>> statuses) {
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
	public Bootstrap getBootstrap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<TYPE> getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <OTHER> DataWriter<OTHER> cast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Topic<TYPE> getTopic() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void waitForAcknowledgments(Duration maxWait)
			throws TimeoutException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void waitForAcknowledgments(long maxWait, TimeUnit unit)
			throws TimeoutException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LivelinessLostStatus<TYPE> getLivelinessLostStatus(
			LivelinessLostStatus<TYPE> status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OfferedDeadlineMissedStatus<TYPE> getOfferedDeadlineMissedStatus(
			OfferedDeadlineMissedStatus<TYPE> status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OfferedIncompatibleQosStatus<TYPE> getOfferedIncompatibleQosStatus(
			OfferedIncompatibleQosStatus<TYPE> status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PublicationMatchedStatus<TYPE> getPublicationMatchedStatus(
			PublicationMatchedStatus<TYPE> status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void assertLiveliness() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<InstanceHandle> getMatchedSubscriptions(
			Collection<InstanceHandle> subscriptionHandles) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubscriptionBuiltinTopicData getMatchedSubscriptionData(
			SubscriptionBuiltinTopicData subscriptionData,
			InstanceHandle subscriptionHandle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InstanceHandle registerInstance(TYPE instanceData)
			throws TimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InstanceHandle registerInstance(TYPE instanceData,
			Time sourceTimestamp) throws TimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InstanceHandle registerInstance(TYPE instanceData,
			long sourceTimestamp, TimeUnit unit) throws TimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void unregisterInstance(InstanceHandle handle)
			throws TimeoutException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unregisterInstance(InstanceHandle handle, TYPE instanceData)
			throws TimeoutException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unregisterInstance(InstanceHandle handle, TYPE instanceData,
			Time sourceTimestamp) throws TimeoutException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unregisterInstance(InstanceHandle handle, TYPE instanceData,
			long sourceTimestamp, TimeUnit unit) throws TimeoutException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(TYPE instanceData) throws TimeoutException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(TYPE instanceData, Time sourceTimestamp)
			throws TimeoutException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(TYPE instanceData, long sourceTimestamp, TimeUnit unit)
			throws TimeoutException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(TYPE instanceData, InstanceHandle handle)
			throws TimeoutException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(TYPE instanceData, InstanceHandle handle,
			Time sourceTimestamp) throws TimeoutException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(TYPE instanceData, InstanceHandle handle,
			long sourceTimestamp, TimeUnit unit) throws TimeoutException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose(InstanceHandle instanceHandle) throws TimeoutException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose(InstanceHandle instanceHandle, TYPE instanceData)
			throws TimeoutException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose(InstanceHandle instanceHandle, TYPE instanceData,
			Time sourceTimestamp) throws TimeoutException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose(InstanceHandle instanceHandle, TYPE instanceData,
			long sourceTimestamp, TimeUnit unit) throws TimeoutException {
		// TODO Auto-generated method stub
		
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

}
