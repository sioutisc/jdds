//================================================================
//
//	Database.java - Created by kerush, 2006 5-dic-06 12:55:24 
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
package rtjdds.rtps.database;

import rtjdds.rtps.exceptions.CollectionsException;
import rtjdds.rtps.exceptions.RTjDDSException;
import rtjdds.rtps.messages.elements.EntityId;
import rtjdds.rtps.publication.Writer;
import rtjdds.rtps.structure.TopicImpl;
import rtjdds.rtps.subscription.Reader;
import rtjdds.rtps.subscription.SubscriberImpl;
import rtjdds.rtps.subscription.WriterProxy;
import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;
import rtjdds.util.collections.RTHashtable;
import rtjdds.util.collections.RTMap;

public class Database {
	
	private RTMap _topics = null;
	
	private RTMap _readers = null;
	
	private RTMap _writers = null;
	
	private RTMap _readerproxies = null;
	
	private RTMap _writerproxies = null;
	
	private RTMap _publishers = null;
	
	private RTMap _subscribers = null;
	
	/* damned rtsj */
	private RTjDDSException _exception = null;
	
	/**
	 * Uses the passed profile to construct the Object Pool for
	 * all the entities managed by a Participant.
	 * 
	 * @param profile
	 * @throws RTjDDSException
	 */
	public Database(final DBProfile profile) throws RTjDDSException {
		
		profile.MEM_AREA.enter( new Runnable() { public void run() {
			
			try {
				GlobalProperties.logger.printMemStats();
				_topics = new RTHashtable(profile.MAX_TOPICS, new TopicIdHasher());
				GlobalProperties.logger.printMemStats();
				_readers = new RTHashtable(profile.MAX_LOCAL_READERS, new EntityIdHasher());
				GlobalProperties.logger.printMemStats();
				_writers = new RTHashtable(profile.MAX_LOCAL_WRITERS, new EntityIdHasher());
				GlobalProperties.logger.printMemStats();
				_readerproxies = new RTHashtable(profile.MAX_REMOTE_READERS, new GUIDHasher());
				GlobalProperties.logger.printMemStats();
				_writerproxies = new RTHashtable(profile.MAX_REMOTE_WRITERS, new GUIDHasher());
				GlobalProperties.logger.printMemStats();
				_publishers = new RTHashtable(profile.MAX_PUBLISHERS, new EntityIdHasher());
				GlobalProperties.logger.printMemStats();
				_subscribers = new RTHashtable(profile.MAX_SUBSCRIBERS, new EntityIdHasher());
				
			} 
			catch (CollectionsException e) {
				_exception = e;
			}
			
			
		}});
		if ( _exception != null ) {
			throw _exception;
		}
		
	}
	
	/**
	 * Uses the default DBProfile to initialize the EntityPool.
	 * @throws RTjDDSException
	 */
	public Database() throws RTjDDSException {
		this(new DBProfile());
	}
	
	///////////////////////////////////////////////////////////////
	//	topic-related methods
	///////////////////////////////////////////////////////////////
	
	public void registerTopic(TopicImpl topic) throws RTjDDSException {
		_topics.putUnique(topic.getTopicId(), topic);
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"registerTopic()",
				"Registered new topic '"+topic.get_name()+"', size="+_topics.size()+
				" freePos"+_topics.freePos());
	}
	
	public TopicImpl lookupTopic(EntityId topic) {
		TopicImpl out = (TopicImpl) _topics.get(topic);
		if (out == null) {
			GlobalProperties.logger.log(Logger.INFO,this.getClass(),"lookupTopic()", "Topic '"+topic+" not found");
		}
		return out;
	}
	
	public void removeTopic(EntityId topic) throws RTjDDSException {
		TopicImpl out = (TopicImpl) _topics.remove(topic);
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"removeTopic()", 
				"Removed topic '"+out == null ? "NULL" : out.get_name());
	}
	
	///////////////////////////////////////////////////////////////
	//	local writers related methods
	///////////////////////////////////////////////////////////////
	
	public void registerWriter(Writer writer) throws RTjDDSException {
		_writers.putUnique(writer.getGuid().getEntityId(), writer);
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"registerWriter()",
				"Registered new writer "+writer.getGuid()+", size="+_writers.size()+
				" freePos"+_writers.freePos());
	}
	
	public Writer lookupWriter(Writer writer) {
		Writer out = (Writer) _writers.get(writer);
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"lookupWriter()", "Looked-up Writer'"+out.getGuid());
		return out;
	}
	
	public void removeWriter(Writer writer) throws RTjDDSException {
		Writer out = (Writer) _writers.remove(writer);
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"lookupWriter()", 
				"Removed Writer '"+out == null ? "NULL" : out.getGuid().toString());
	}
	
	///////////////////////////////////////////////////////////////
	//	local readers related methods
	///////////////////////////////////////////////////////////////
	
	public void registerReader(Reader reader) throws RTjDDSException {
		_readers.putUnique(reader.getGuid().getEntityId(), reader);
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"registerReader()",
				"Registered new reader "+reader.getGuid()+", size="+_readers.size()+
				" freePos"+_readers.freePos());
	}
	
	public Reader lookupReader(Reader reader) {
		Reader out = (Reader) _readers.get(reader);
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"lookupReader()", "Looked-up Reader'"+out.getGuid());
		return out;
	}
	
	public void removeReader(Reader reader) throws RTjDDSException {
		Reader out = (Reader) _readers.remove(reader);
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"lookupReader()", 
				"Removed Reader'"+out == null ? "NULL" : out.getGuid().toString());
	}
	
	///////////////////////////////////////////////////////////////
	//	remote writers related methods
	///////////////////////////////////////////////////////////////
	
	public void registerWriterProxy(WriterProxy proxy) throws RTjDDSException {
		_writerproxies.putUnique(proxy.getGuid().getEntityId(), proxy);
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"registerWriterProxy()",
				"Registered new Writer Proxy "+proxy.getGuid()+", size="+_writerproxies.size()+
				" freePos"+_writerproxies.freePos());
	}
	
	public WriterProxy lookupWriterProxy (WriterProxy proxy) {
		WriterProxy out = (WriterProxy) _writerproxies.get(proxy);
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"lookupWriterProxy()", "Looked-up Writer Proxy'"+out.getGuid());
		return out;
	}
	
	public void removeWriterProxy(WriterProxy proxy) throws RTjDDSException {
		WriterProxy out = (WriterProxy) _writerproxies.remove(proxy);
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"lookupWriterProxy()", 
				"Removed WriterProxy'"+out == null ? "NULL" : out.getGuid().toString());
	}
	
	///////////////////////////////////////////////////////////////
	//	remote readers related methods
	///////////////////////////////////////////////////////////////
	/*
	public void registerReaderProxy(ReaderProxy proxy) throws RTjDDSException {
		_readerproxies.putUnique(proxy.getGuid().getEntityId(), proxy);
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"registerReaderProxy()",
				"Registered new Reader Proxy "+proxy.getGuid()+", size="+_readerproxies.size()+
				" freePos"+_readerproxies.freePos());
	}
	
	public ReaderProxy lookupReaderProxy (ReaderProxy proxy) {
		ReaderProxy out = (ReaderProxy) _readerproxies.get(proxy);
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"lookupReaderProxy()", "Looked-up Reader Proxy'"+out.getGuid());
		return out;
	}
	
	public void removeReaderProxy(ReaderProxy proxy) throws RTjDDSException {
		ReaderProxy out = (ReaderProxy) _readerproxies.remove(proxy);
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"lookupReaderProxy()", 
				"Removed ReaderProxy'"+out == null ? "NULL" : out.getGuid().toString());
	}
	*/
	
	///////////////////////////////////////////////////////////////
	//	publisher related methods
	///////////////////////////////////////////////////////////////
	/*
	public void registerPublisher(PublisherImpl publisher) throws RTjDDSException {
		_publishers.putUnique(publisher.getGuid().getEntityId(), publisher);
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"registerPublisherImpl()",
				"Registered new Publisher "+publisher.getGuid()+", size="+_writerproxies.size()+
				" freePos"+_publishers.freePos());
	}
	
	public PublisherImpl lookupPublisher(PublisherImpl publisher) {
		PublisherImpl out = (PublisherImpl) _publishers.get(publisher);
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"lookupPublisher()", "Looked-up PublisherImpl'"+out.getGuid());
		return out;
	}
	
	public void removePublisher(PublisherImpl publisher) throws RTjDDSException {
		PublisherImpl out = (PublisherImpl) _publishers.remove(publisher);
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"lookupPublisher()",
				"Removed PublisherImpl'"+out == null ? "NULL" : out.getGuid().toString());
	}
	*/
	///////////////////////////////////////////////////////////////
	//	subscriber related methods
	///////////////////////////////////////////////////////////////
	
	public void registerSubscriber (SubscriberImpl subscriber) throws RTjDDSException {
		_subscribers.putUnique(subscriber.getGuid().getEntityId(), subscriber);
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"registerPublisherImpl()",
				"Registered new Publisher "+subscriber.getGuid()+", size="+_subscribers.size()+
				" freePos"+_subscribers.freePos());
	}
	
	public SubscriberImpl lookupSubscriber (SubscriberImpl subscriber) {
		SubscriberImpl out = (SubscriberImpl) _subscribers.get(subscriber);
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"lookupsubscriber()", "Looked-up subscriber'"+out.getGuid());
		return out;
	}
	
	public void removeSubscriber (SubscriberImpl subscriber) throws RTjDDSException {
		SubscriberImpl out = (SubscriberImpl) _subscribers.remove(subscriber);
		GlobalProperties.logger.log(Logger.INFO,this.getClass(),"lookupSubscriber()",
				"Removed Subscriber'"+out == null ? "NULL" : out.getGuid().toString());
	}
		

}
