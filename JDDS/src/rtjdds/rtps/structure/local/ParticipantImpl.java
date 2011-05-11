package rtjdds.rtps.structure.local;

import java.io.IOException;
import java.net.InetAddress;

import org.omg.CORBA.Context;
import org.omg.CORBA.ContextList;
import org.omg.CORBA.DomainManager;
import org.omg.CORBA.ExceptionList;
import org.omg.CORBA.NVList;
import org.omg.CORBA.NamedValue;
import org.omg.CORBA.Object;
import org.omg.CORBA.Policy;
import org.omg.CORBA.Request;
import org.omg.CORBA.SetOverrideType;

import rtjdds.rtps.database.Database;
import rtjdds.rtps.exceptions.RTjDDSException;
import rtjdds.rtps.messages.RTPSHeader;
import rtjdds.rtps.messages.elements.EntityId;
import rtjdds.rtps.messages.elements.GUID;
import rtjdds.rtps.messages.elements.GuidPrefix;
import rtjdds.rtps.messages.elements.ProtocolVersion;
import rtjdds.rtps.messages.elements.VendorId;
import rtjdds.rtps.publication.PublisherImpl;
import rtjdds.rtps.receive.ChannelSelector;
import rtjdds.rtps.receive.DatagramChannelSelector;
import rtjdds.rtps.receive.MulticastReceiverPool;
import rtjdds.rtps.receive.PipeSelector;
import rtjdds.rtps.receive.ReceiverPool;
import rtjdds.rtps.receive.ReceiverPoolLeaderFollower;
import rtjdds.rtps.receive.UnicastReceiverPool;
import rtjdds.rtps.structure.EntityImpl;
import rtjdds.rtps.structure.TopicImpl;
import rtjdds.rtps.structure.TopicProfile;
import rtjdds.rtps.subscription.SubscriberImpl;
import rtjdds.rtps.types.VendorId_t;
import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;
import rtjdds.util.collections.Copyable;
import DDS.ContentFilteredTopic;
import DDS.DomainParticipantListener;
import DDS.DomainParticipantQos;
import DDS.DomainParticipantQosHolder;
import DDS.Duration_t;
import DDS.InstanceHandleSeqHolder;
import DDS.MultiTopic;
import DDS.ParticipantBuiltinTopicDataHolder;
import DDS.Publisher;
import DDS.PublisherListener;
import DDS.PublisherQos;
import DDS.PublisherQosHolder;
import DDS.StatusCondition;
import DDS.Subscriber;
import DDS.SubscriberListener;
import DDS.SubscriberQos;
import DDS.SubscriberQosHolder;
import DDS.Time_tHolder;
import DDS.Topic;
import DDS.TopicBuiltinTopicDataHolder;
import DDS.TopicDescription;
import DDS.TopicListener;
import DDS.TopicQos;
import DDS.TopicQosHolder;

public class ParticipantImpl extends EntityImpl implements DDS.DomainParticipant, Copyable {

	/* the domain identifier (0 if not specified) */
	private int _domainID = 0;
	
	/* the header to attach to each message */
	private RTPSHeader _header = null;
	
	/* the QoS */
	private DomainParticipantQos _qos = null;
	
	/* the listener */
	private DomainParticipantListener _listener = null;
	
	/* version and vendor (from global properties) */
	private ProtocolVersion _protocolVersion = 
		new ProtocolVersion(GlobalProperties.protocolVersion_t);
	private VendorId _vendorId = 
		new VendorId(new VendorId_t(GlobalProperties.VENDORID));
	
	/* Database for all the entities managed by this Participant */
	private Database _db = null;
	
	/* User Traffic Receivers (multicast and unicast) */
	private ReceiverPool _mcastRcvPool = null;
	//TODO add unicast
	
	/* Built-in Traffic Receiver (multicast and unicast) */
	
	
	/**
	 * Constructs a new Participant with the given domainID and
	 * the default policies.
	 * 
	 * @param prefix
	 * @param domainID
	 * @throws RTjDDSException
	 * @throws IOException 
	 */
	public ParticipantImpl(GuidPrefix prefix, int domainID, DomainParticipantQos qos, DomainParticipantListener listener) throws RTjDDSException {
		
		super(new GUID(prefix,EntityId.participant));
		_domainID = domainID;
		_db = new Database();
		
		GlobalProperties.logger.printMemStats();
		
		_listener = listener;
		
		_qos = qos;
		
		/* the header attache to each outgoing message */
		_header = new RTPSHeader(prefix);
//		
//		/* a shutdown hook to release resources when the application ends */
//		Runtime.getRuntime().addShutdownHook(new Thread() {
//            public void run() {
//            	GlobalProperties.logger.log(Logger.INFO,this.getClass(),"shutdown()","Shutdown hook activated");
//                delete_contained_entities();
//            }
//        });;
	}
	
	public Database getDatabase() {
		return _db;
	}
	
	public RTPSHeader getHeader() {
		return _header;
	}
	
	// ----------------------------------------------------------
	// Methods from Copyable
	// ----------------------------------------------------------

	public void copyFrom(Copyable obj) {
		if (obj instanceof ParticipantImpl) {
			ParticipantImpl src = (ParticipantImpl) obj;
			super.copyFrom(src);
			_domainID = src._domainID;
			_protocolVersion.copyFrom(src._protocolVersion);
			_vendorId.copyFrom(src._vendorId);
		}
		else {
			GlobalProperties.logger.log(Logger.SEVERE,this.getClass(),"copyFrom()",
					"cannot copy from a not ParticipantImpl instance");
		}
	}

	public void copyTo(Copyable obj) {
		if (obj instanceof ParticipantImpl) {
				ParticipantImpl dst = (ParticipantImpl) obj;
				super.copyTo(dst);
				dst._domainID = _domainID;
				_protocolVersion.copyTo(dst._protocolVersion);
				_vendorId.copyTo(dst._vendorId);
		}
		else {
			GlobalProperties.logger.log(Logger.SEVERE,this.getClass(),"copyFrom()",
					"cannot copy from a not ParticipantImpl instance");
		}
	}

	
	// ----------------------------------------------------------
	// Methods from DDS.DomainParticipant
	// ----------------------------------------------------------

	public Publisher create_publisher(PublisherQos qos, PublisherListener a_listener) {
		PublisherImpl publisher = new PublisherImpl(this, qos, a_listener);
		return publisher;
	}

	public int delete_publisher(Publisher p) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Subscriber create_subscriber(SubscriberQos qos, SubscriberListener a_listener) {
		SubscriberImpl subscriber = new SubscriberImpl(this,qos,a_listener);
		try {
			_db.registerSubscriber(subscriber);
		} catch (RTjDDSException e) {
			GlobalProperties.logger.log(Logger.SEVERE,this.getClass(),"create_subscriber()",
			"cannot create a new subscriber: "+e.getMessage());
		}
		return subscriber;
	}

	public int delete_subscriber(Subscriber s) {
		try {
			_db.removeSubscriber((SubscriberImpl) s);
			return 1;
		} catch (RTjDDSException e) {
			GlobalProperties.logger.log(Logger.SEVERE,this.getClass(),"create_subscriber()",
					"cannot delete the subscriber: "+e.getMessage());
			return -1;
		}
	}

	public Subscriber get_builtin_subscriber() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * NOT STANDARD !! TO BE MODIFIED!!!lisherImpl
	 * @param topic_name
	 * @param topicId
	 * @param type_name
	 * @param qos
	 * @param a_listener
	 * @return
	 */
	public Topic create_topic(String topic_name, EntityId topicId, String type_name, int dataLength, TopicQos qos, TopicListener a_listener) {
		try {
			TopicImpl newTopic = new TopicImpl(this,topicId,topic_name,type_name,dataLength,qos,new TopicProfile(),a_listener);
			_db.registerTopic(newTopic);
			return newTopic;
		} catch (RTjDDSException e) {
			return null;
		}
	}

	public Topic create_topic(String topic_name, String type_name, TopicQos qos, TopicListener a_listener) {
		return null;
	}

	public int delete_topic(Topic a_topic) {
		try {
			_db.removeTopic(((TopicImpl)a_topic).getTopicId());
		} catch (RTjDDSException e) {
			e.printStackTrace();
		}
		return 0;
	}
	

	public int set_qos(DomainParticipantQos qos) {
		_qos = qos;
		return 0;
	}

	public void get_qos(DomainParticipantQos qos) {
		qos = _qos;
	}

	public int set_listener(DomainParticipantListener a_listener, int mask) {
		_listener = a_listener;
		return 0;
	}

	public DomainParticipantListener get_listener() {
		return _listener;
	}
	
	public int get_domain_id() {
		return _domainID;
	}
	
	public int enable() /*throws RTjDDSException*/ {
		/* receive threads multicast*/
		try {
			/* TODO modify here */
//			_mcastRcvPool = new UnicastReceiverPool(1, _domainID, _db);

			/* multicast channels */
//			int port = GlobalProperties.PB + GlobalProperties.DG * _domainID + GlobalProperties.d0;
//			ChannelSelector selector = new DatagramChannelSelector(
//					InetAddress.getByName(GlobalProperties.DEFAULT_MULTICAST_ADDRESS),
//					new int[]{port, port+1, port+2});

			
			ChannelSelector selector = GlobalProperties.pipeSelector;
			
			_mcastRcvPool = new ReceiverPoolLeaderFollower(
					GlobalProperties.THREAD_POOL_SIZE, 1, true, _db, selector);
					
		} catch (IOException e) {
//			throw new RTjDDSException(e)
			e.printStackTrace();
		}
		_mcastRcvPool.start();
		return 0;
	}
	
	/* used to shutdown */
	public int delete_contained_entities() {
		_mcastRcvPool.shutdown();
		return 0;
	}
	
	/**
	 * MENO PRIORITARI, IMPLEMENTARE IN SEGUITO....
	 */

	public Topic find_topic(String topic_name, Duration_t timeout) {
		// TODO Auto-generated method stub
		return null;
	}

	public TopicDescription lookup_topicdescription(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public ContentFilteredTopic create_contentfilteredtopic(String name, Topic related_topic, String filter_expression, String[] filter_parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	public int delete_contentfilteredtopic(ContentFilteredTopic a_contentfilteredtopic) {
		// TODO Auto-generated method stub
		return 0;
	}

	public MultiTopic create_multitopic(String name, String type_name, String subscription_expression, String[] expression_parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	public int delete_multitopic(MultiTopic a_multitopic) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int ignore_participant(int handle) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int ignore_topic(int handle) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int ignore_publication(int handle) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int ignore_subscription(int handle) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int assert_liveliness() {
		return 0;
		// TODO Auto-generated method stub
		
	}

	public int set_default_publisher_qos(PublisherQos qos) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void get_default_publisher_qos(PublisherQos qos) {
		// TODO Auto-generated method stub
		
	}

	public int set_default_subscriber_qos(SubscriberQos qos) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void get_default_subscriber_qos(SubscriberQos qos) {
		// TODO Auto-generated method stub
		
	}

	public int set_default_topic_qos(TopicQos qos) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void get_default_topic_qos(TopicQos qos) {
		// TODO Auto-generated method stub
		
	}

	public StatusCondition get_statuscondition() {
		// TODO Auto-generated method stub
		return null;
	}

	public int get_status_changes() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Publisher create_publisher(PublisherQos qos,
			PublisherListener a_listener, int mask) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subscriber create_subscriber(SubscriberQos qos,
			SubscriberListener a_listener, int mask) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Topic create_topic(String topic_name, String type_name,
			TopicQos qos, TopicListener a_listener, int mask) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int get_qos(DomainParticipantQosHolder qos) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_default_publisher_qos(PublisherQosHolder qos) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_default_subscriber_qos(SubscriberQosHolder qos) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_default_topic_qos(TopicQosHolder qos) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_discovered_participants(
			InstanceHandleSeqHolder participant_handles) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_discovered_participant_data(
			ParticipantBuiltinTopicDataHolder participant_data,
			int participant_handle) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_discovered_topics(InstanceHandleSeqHolder topic_handles) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_discovered_topic_data(
			TopicBuiltinTopicDataHolder topic_data, int topic_handle) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean contains_entity(int a_handle) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int get_current_time(Time_tHolder current_time) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_instance_handle() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Request _create_request(Context ctx, String operation,
			NVList arg_list, NamedValue result) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Request _create_request(Context ctx, String operation,
			NVList arg_list, NamedValue result, ExceptionList exclist,
			ContextList ctxlist) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object _duplicate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DomainManager[] _get_domain_managers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object _get_interface_def() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Policy _get_policy(int policy_type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int _hash(int maximum) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean _is_a(String repositoryIdentifier) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean _is_equivalent(Object other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean _non_existent() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void _release() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Request _request(String operation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object _set_policy_override(Policy[] policies,
			SetOverrideType set_add) {
		// TODO Auto-generated method stub
		return null;
	}

}
