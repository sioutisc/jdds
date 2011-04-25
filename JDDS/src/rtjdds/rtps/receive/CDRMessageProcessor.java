//================================================================
//
//Receiver.java - Created by kerush, 2006 24-ott-06 5:22:39 
//
//================================================================
//
//R T  j  D D S  version 0.1
//
//Copyright (C) 2006 by Vincenzo Caruso
//<bitclash[at]gmail.com>
//
//This program is free software; you can redistribute it and/or
//modify it under  the terms of  the GNU General Public License
//as published by the Free Software Foundation;either version 2
//of the License, or (at your option) any later version.
//
//This program is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or  FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU General Public License for more details.
//
//A copy of the GNU  General Public License  is available in the
//file named LICENSE,  that  should  be  shipped along with this
//package;  if not,  write to the Free Software Foundation, Inc., 
//51 Franklin Street, Fifth Floor,   Boston, MA  02110-1301, USA.
//
//================================================================
package rtjdds.rtps.receive;

import rtjdds.rtps.messages.AckNack;
import rtjdds.rtps.messages.Data;
import rtjdds.rtps.messages.DataFrag;
import rtjdds.rtps.messages.Gap;
import rtjdds.rtps.messages.HeartBeat;
import rtjdds.rtps.messages.HeartBeatFrag;
import rtjdds.rtps.messages.InfoTimestamp;
import rtjdds.rtps.messages.MalformedSubmessageException;
import rtjdds.rtps.messages.NackFrag;
import rtjdds.rtps.messages.NoKeyData;
import rtjdds.rtps.messages.NoKeyDataFrag;
import rtjdds.rtps.messages.Pad;
import rtjdds.rtps.messages.RTPSHeader;
import rtjdds.rtps.messages.Submessage;
import rtjdds.rtps.messages.elements.Count;
import rtjdds.rtps.messages.elements.EntityId;
import rtjdds.rtps.messages.elements.FragmentNumber;
import rtjdds.rtps.messages.elements.FragmentNumberSet;
import rtjdds.rtps.messages.elements.GuidPrefix;
import rtjdds.rtps.messages.elements.KeyHashPrefix;
import rtjdds.rtps.messages.elements.KeyHashSuffix;
import rtjdds.rtps.messages.elements.LongWrapperSubmessageElement;
import rtjdds.rtps.messages.elements.ParameterList;
import rtjdds.rtps.messages.elements.ProtocolId;
import rtjdds.rtps.messages.elements.ProtocolVersion;
import rtjdds.rtps.messages.elements.SequenceNumber;
import rtjdds.rtps.messages.elements.SequenceNumberSet;
import rtjdds.rtps.messages.elements.SerializedData;
import rtjdds.rtps.messages.elements.ShortWrapperSubmessageElement;
import rtjdds.rtps.messages.elements.StatusInfo;
import rtjdds.rtps.messages.elements.SubmessageElement;
import rtjdds.rtps.messages.elements.Timestamp;
import rtjdds.rtps.messages.elements.VendorId;
import rtjdds.rtps.portable.InputPacket;
import rtjdds.rtps.types.ACKNACK;
import rtjdds.rtps.types.Count_tHelper;
import rtjdds.rtps.types.DATA;
import rtjdds.rtps.types.DATA_FRAG;
import rtjdds.rtps.types.EntityId_tHelper;
import rtjdds.rtps.types.FragmentNumber_tHelper;
import rtjdds.rtps.types.GAP;
import rtjdds.rtps.types.GuidPrefix_tHelper;
import rtjdds.rtps.types.HEARTBEAT;
import rtjdds.rtps.types.HEARTBEAT_FRAG;
import rtjdds.rtps.types.INFO_DST;
import rtjdds.rtps.types.INFO_REPLY;
import rtjdds.rtps.types.INFO_SRC;
import rtjdds.rtps.types.INFO_TS;
import rtjdds.rtps.types.KeyHashPrefix_tHelper;
import rtjdds.rtps.types.KeyHashSuffix_tHelper;
import rtjdds.rtps.types.NACK_FRAG;
import rtjdds.rtps.types.NOKEY_DATA;
import rtjdds.rtps.types.NOKEY_DATA_FRAG;
import rtjdds.rtps.types.PAD;
import rtjdds.rtps.types.ProtocolId_tHelper;
import rtjdds.rtps.types.ProtocolVersion_tHelper;
import rtjdds.rtps.types.SequenceNumber_tHelper;
import rtjdds.rtps.types.Time_tHelper;
import rtjdds.rtps.types.VendorId_tHelper;
import rtjdds.util.BitUtility;
import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;

/**
 * Concrete message decoding class according to CDR encapsulation.
 * 
 * @author kerush
 *
 */
public class CDRMessageProcessor extends MessageProcessor {
	
	/* packet in decoding phase */
	private InputPacket _packet = null;

	/* current submessage info */
	private byte _kind;
	private byte _flags;
	private short _submessageLength;
	private int _nextSubmessageHeader;
	
	/**
	 * Constructs a new <code>Receiver</code> of <code>Messages</code>.<br/>
	 * It operates following these rules:
	 * <ol>
	 * 	<li>If the FULL Submessage Header cannot be read, the packet is dropped</li>
	 *	<li>If the <code>submessageLength</code> is invalid, the rest of the message is invalid</li>
	 *	<li>A <code>Submessage</code> with unknown <code>SubmessageKind</code> MUST be ignored and
	 *	parsing should continue with the next Submessage. This point enables vendor-specific
	 * 	SubmessageKinds</li>
	 * <li>Unknown flags should be ignored</li>
	 * <li>A valid <code>submessageLength</code> field MUST always be used to find the next message,
	 * even for Submessages with known Kind</li>
	 * <li>A known but invalid Submessage invalidates the rest of the message</li>
	 * </ol>
	 * 
	 * 
	 */
	public CDRMessageProcessor(SubmessageDispatcher dispatcher) {
		super(dispatcher);
	}
	
	/**
	 * Decodes the packet following the CDR representation.<br/>
	 * Once a submessage is decoded, it is passed to the dispatcher that notifies the
	 * respective <code>Reader</code> or <code>Writer</code>. 
	 * <ol>
	 * 	<li>If the FULL Submessage Header cannot be read, the packet is dropped</li>
	 *	<li>If the <code>submessageLength</code> is invalid, the rest of the message is invalid</li>
	 *	<li>A <code>Submessage</code> with unknown <code>SubmessageKind</code> MUST be ignored and
	 *	parsing should continue with the next Submessage. This point enables vendor-specific
	 * 	SubmessageKinds</li>
	 * <li>Unknown flags should be ignored</li>
	 * <li>A valid <code>submessageLength</code> field MUST always be used to find the next message,
	 * even for Submessages with known Kind</li>
	 * <li>A known but invalid Submessage invalidates the rest of the message</li>
	 * </ol>
	 * <!--
	 * <b>performance info: this method is SYNCHRONIZED, IT PUTS A LOCK ON THE ENTIRE OBJECT</b>
	 * -->
	 * 
	 */
	public void process(InputPacket packet) {
		
		_packet = packet;
		
		/* private variables that maintains status into the Decoder */
		ProtocolVersion sourceVersion = null;
		VendorId sourceVendorId = null;
		GuidPrefix sourceGuidPrefix = null;
		GuidPrefix destGuidPrefix = null;
//		Locator unicastReplyLocatorList = null;
//		Locator multicastReplyLocatorList = null
		/* if ts is null, there are no InfoTimestamp submsg */
		Timestamp ts = null;
		
		
		////////////////////////////
		//   CHECKING THE HEADER  //
		////////////////////////////
		RTPSHeader header = null;
		try {
			header = decodeRTPSHeader();
		} catch (MalformedSubmessageException e1) {
			return;
		}
		if (header.getVersion().compareTo(GlobalProperties.protocolVersion) > 0) {
			/* cannot accept messages from a greater version */
			return;
		}
		if (!header.getVendorId().equals(GlobalProperties.vendorId)) {
			/* cannot accept messages from other vendors */
			return;
		}
		// initializing the state of this receiver
		sourceVersion = header.getVersion();
		sourceVendorId = header.getVendorId();
		sourceGuidPrefix = header.getGuidPrefix();
		
		/* Anche i pacchetti provenienti da Participant non noti devono
		 * essere accettati, vanno direzionati al DISCOVERY SERVICE
		 * TODO: se il discovery è disabilitato, dropping dei messaggi che
		 * provengono da Participant non noti? Oppure boolean isDiscovery?
		 */
		
		////////////////////////////
		// CYCLE OVER SUBMESSAGES //
		////////////////////////////
		do {
			int submessageHeaderStart = packet.getCursorPosition();
			int submessageHeaderStop = submessageHeaderStart + Submessage.HEADER_SIZE;
			/* ensuring that there is enough space of the submessage header */
			if ( packet.getCursorPosition()+Submessage.HEADER_SIZE > packet.getLength() ) {
				return;
			}	
			/* reading the kind of the submessage */
			_kind = packet.read_octet();
			/* reading flags */ 
			_flags = packet.read_octet();
			/* setting packet endianess */
			packet.setEndianess( ! BitUtility.getFlagAt(_flags,0) );
			/* reading the SUBMESSAGE_LENGTH, otherwise called OCTETS_TO_NEXT_HEADER */
			_submessageLength = packet.read_short();
			_nextSubmessageHeader = submessageHeaderStop + _submessageLength;
			/* submessages are always aligned at 4, this check is done over submessageLength... */
			if ( (_submessageLength % 4) != 0 ) {
				return;
			}
			if ( _submessageLength <= 0 ) {
				return;
			}
			/* check if there is enough space for this submessage */
			if ( _nextSubmessageHeader > packet.getLength() ) {
				return;
			}
			/* SubmessageHeader is done, reading the packet content */
			try {

				switch (_kind) {
				case DATA.value: {
					Data data = decodeData();
					data.setTimestamp(ts);
					data.setSrcGuidPrefix(sourceGuidPrefix);
					_dispatcher.dispatch(data);
					break;
				}
				case DATA_FRAG.value: {
					DataFrag dataFrag = decodeDataFrag();
					dataFrag.setTimestamp(ts);
					dataFrag.setSrcGuidPrefix(sourceGuidPrefix);
					_dispatcher.dispatch(dataFrag);
					break;
				}
				case NOKEY_DATA.value: {
					NoKeyData data = decodeNoKeyData();
					
					data.setTimestamp(ts);
					data.setSrcGuidPrefix(sourceGuidPrefix);
					_dispatcher.dispatch(data);					
					
					break;
				}
				case NOKEY_DATA_FRAG.value: {
					NoKeyDataFrag dataFrag = decodeNoKeyDataFrag();
					dataFrag.setTimestamp(ts);
					dataFrag.setSrcGuidPrefix(sourceGuidPrefix);
					_dispatcher.dispatch(dataFrag);
					break;
				}
				case HEARTBEAT.value: {
					HeartBeat h = decodeHeartBeat();
					h.setTimestamp(ts);
					h.setSrcGuidPrefix(sourceGuidPrefix);
					_dispatcher.dispatch(h);
					break;
				}
				case ACKNACK.value: {
					AckNack a = decodeAckNack();
					a.setTimestamp(ts);
					a.setSrcGuidPrefix(sourceGuidPrefix);
					_dispatcher.dispatch(a);
					break;
				}
				case HEARTBEAT_FRAG.value: {
					HeartBeatFrag h = decodeHeartBeatFrag();
					h.setTimestamp(ts);
					h.setSrcGuidPrefix(sourceGuidPrefix);
					_dispatcher.dispatch(h);
					break;
				}
				case NACK_FRAG.value: {
					NackFrag n = decodeNackFrag();
					n.setTimestamp(ts);
					n.setSrcGuidPrefix(sourceGuidPrefix);
					_dispatcher.dispatch(n);
					break;
				}
				case GAP.value: {
					Gap g = decodeGap();
					g.setSrcGuidPrefix(sourceGuidPrefix);
					g.setTimestamp(ts);
					_dispatcher.dispatch(g);
					break;
				}
				/* PAD submessages have no meaning, they exists only for alignment purposes */
				case PAD.value: {
					//dispatcher.dispatch(decodePad(),sourceGuidPrefix,ts);
					break;
				}
				/* TIMESTAMP to be used for every following submessage */
				case INFO_TS.value: {
					// timestamps are widely used by RTI NDDS, is better to use it here too...
					// if ts is null, no problem, the check is done in the dispatch area
					ts = decodeInfoTimestamp().getT();
					break;
				}
				/* these are RELAY messages, ignored now... */
				case INFO_REPLY.value: {
					GlobalProperties.logger.log(Logger.INFO,CDRMessageProcessor.class,
							"decode()", "Submessage INFO_REPLY not supported, skipping...");
					break;
				}
				case INFO_DST.value: {
					GlobalProperties.logger.log(Logger.INFO,CDRMessageProcessor.class,
							"decode()", "Submessage INFO_DST not supported, skipping...");
					break;
				}
				case INFO_SRC.value: {
					GlobalProperties.logger.log(Logger.INFO,CDRMessageProcessor.class,
							"decode()", "Submessage INFO_SRC not supported, skipping...");
					break;
				}
				/* Unknown submsgs code are just ignored (compatibility with vendor-specific codes) */
				default: {
					GlobalProperties.logger.log(Logger.WARN,CDRMessageProcessor.class,
							"decode()", "Unknown Submessage Kind="+_kind+" at byte "
							+(packet.getCursorPosition()-1)+", skipping the submessage");
//					FIXME stop at the rigth end...
//					return;
					break;
				}
				} // switch
				
			} //try
			catch (MalformedSubmessageException e) {
				GlobalProperties.logger.log(Logger.WARN,CDRMessageProcessor.class,
						"decode()","Malformed Submessage Error:"+e.toString()+", " +
								"dropping the rest of the message");
				return;
			}
			/* going to the next header... */
			packet.setCursorPosition(_nextSubmessageHeader);
			
		} while (packet.getCursorPosition() < packet.getLength());
		
		
	} // decode()
	


	/* submessage-specific decoding methods */
	
	private RTPSHeader decodeRTPSHeader() throws MalformedSubmessageException {
		ProtocolId protocol = null;
		ProtocolVersion version = null;
		VendorId vendorId = null;
		GuidPrefix guidPrefix = null;
		
		if (_packet.getLength() >= SubmessageElement.RTPS_HEADER_SIZE) {
			// reading protoId
			protocol = new ProtocolId(ProtocolId_tHelper.read(_packet));
			if (protocol.equals(GlobalProperties.protocolId)) {
				// reading version, vendor and GUIDprefix
				version = new ProtocolVersion(ProtocolVersion_tHelper.read(_packet));
				vendorId = new VendorId(VendorId_tHelper.read(_packet));
				guidPrefix = new GuidPrefix(GuidPrefix_tHelper.read(_packet));
			}
			else {
				throw new MalformedSubmessageException("RTPS Protocol string not correct");
			}
		}
		else {
			throw new MalformedSubmessageException("RTPS Header non found in the message");
		}
		
		return new RTPSHeader(protocol, version, vendorId, guidPrefix);
	}
	
	
	
	private Data decodeData() throws MalformedSubmessageException {
		EntityId readerId = new EntityId(EntityId_tHelper.read(_packet));
		EntityId writerId = new EntityId(EntityId_tHelper.read(_packet));
		SequenceNumber sn = new SequenceNumber(SequenceNumber_tHelper.read(_packet));
		KeyHashPrefix khp = null;
		KeyHashSuffix khs = null;
		byte[] bytes = null;
		// KeyHash sets H
		if (BitUtility.getFlagAt(_flags,3)) {
			khp = new KeyHashPrefix(KeyHashPrefix_tHelper.read(_packet));
			khs = new KeyHashSuffix(KeyHashSuffix_tHelper.read(_packet));
		}
		// statusInfo sets I
		StatusInfo si = null;
		if (BitUtility.getFlagAt(_flags,4)) {
			si = new StatusInfo(_packet.read_long());
		}
		// inlineQoS sets Q
		ParameterList qos = null;
		if (BitUtility.getFlagAt(_flags,1)) {
			qos = new ParameterList(_packet);
		}
		// serializedData sets D
		SerializedData serializedData = null;
		if (BitUtility.getFlagAt(_flags,2)) {
			int dataLength = _nextSubmessageHeader - _packet.getCursorPosition();
//			bytes = new byte[dataLength];
//			packet.read_octet_array(bytes,0,dataLength);
			serializedData = new SerializedData(_packet,dataLength);
		}
		return new Data(readerId,writerId,sn,khp,khs,si,qos,serializedData);
	}
	
	private DataFrag decodeDataFrag() throws MalformedSubmessageException {
		EntityId readerId = new EntityId(EntityId_tHelper.read(_packet));
		EntityId writerId = new EntityId(EntityId_tHelper.read(_packet));
		SequenceNumber sn = new SequenceNumber(SequenceNumber_tHelper.read(_packet));
		KeyHashPrefix khp = null;
		KeyHashSuffix khs = null; 
		// KeyHash sets H
		if (BitUtility.getFlagAt(_flags,2)) {
			khp = new KeyHashPrefix(KeyHashPrefix_tHelper.read(_packet));
			khs = new KeyHashSuffix(KeyHashSuffix_tHelper.read(_packet));
		}
		// inlineQoS sets Q
		ParameterList qos = null;
		if (BitUtility.getFlagAt(_flags,1)) {
			qos = new ParameterList(_packet);
		}
		FragmentNumber fsn = new FragmentNumber(FragmentNumber_tHelper.read(_packet));
		ShortWrapperSubmessageElement fis = new ShortWrapperSubmessageElement(_packet.read_short());
		ShortWrapperSubmessageElement fsize = new ShortWrapperSubmessageElement(_packet.read_short());
		LongWrapperSubmessageElement sampleSize = new LongWrapperSubmessageElement(_packet.read_long());
		SerializedData serializedData = new SerializedData(_packet, _nextSubmessageHeader - _packet.getCursorPosition());
		return new DataFrag(readerId,writerId,sn,khp,khs,qos,fsn,fis,fsize,sampleSize,serializedData);
	}
	
	private NoKeyData decodeNoKeyData() throws MalformedSubmessageException {
		EntityId readerId = new EntityId(EntityId_tHelper.read(_packet));
		EntityId writerId = new EntityId(EntityId_tHelper.read(_packet));
		SequenceNumber sn = new SequenceNumber(SequenceNumber_tHelper.read(_packet));
		// ParameterList inlineQoS
		ParameterList inlineQoS = null;
		if (BitUtility.getFlagAt(_flags,1)) {
			inlineQoS = new ParameterList(_packet);
		}
		// Serialized Data serializedData
		SerializedData serializedData = null;
		if (BitUtility.getFlagAt(_flags,2)) {
			serializedData = new SerializedData(_packet,_nextSubmessageHeader-_packet.getCursorPosition());
		}
		return new NoKeyData(readerId,writerId,sn,inlineQoS,serializedData);
	}
	
	private NoKeyDataFrag decodeNoKeyDataFrag() throws MalformedSubmessageException {
		EntityId readerId = new EntityId(EntityId_tHelper.read(_packet));
		EntityId writerId = new EntityId(EntityId_tHelper.read(_packet));
		SequenceNumber sn = new SequenceNumber(SequenceNumber_tHelper.read(_packet));
		ParameterList inlineQoS = null;
		if (BitUtility.getFlagAt(_flags,1)) {
			inlineQoS = new ParameterList(_packet);
		}
		FragmentNumber fragmentStartingNum = new FragmentNumber(FragmentNumber_tHelper.read(_packet));
		short fragmentsInSubmessage = _packet.read_short();
		short fragmentSize = _packet.read_short();
		int sampleSize = _packet.read_long();
		SerializedData serializedData = new SerializedData(_packet,_nextSubmessageHeader-_packet.getCursorPosition());
		return new NoKeyDataFrag(readerId,writerId,sn,inlineQoS,fragmentStartingNum,fragmentsInSubmessage,fragmentSize,sampleSize,serializedData);
	}
	
	private HeartBeat decodeHeartBeat() throws MalformedSubmessageException {
		EntityId readerId = new EntityId(EntityId_tHelper.read(_packet));
		EntityId writerId = new EntityId(EntityId_tHelper.read(_packet));
		SequenceNumber firstSN = new SequenceNumber(SequenceNumber_tHelper.read(_packet));
		SequenceNumber lastSN = new SequenceNumber(SequenceNumber_tHelper.read(_packet));
		Count count = new Count(Count_tHelper.read(_packet));
		return new HeartBeat(readerId,writerId,firstSN,lastSN,count);
	}
	
	private AckNack decodeAckNack() throws MalformedSubmessageException {
		EntityId readerId = new EntityId(EntityId_tHelper.read(_packet));
		EntityId writerId = new EntityId(EntityId_tHelper.read(_packet));
		SequenceNumberSet sns = SequenceNumberSet.read(_packet);
		Count count = new Count(Count_tHelper.read(_packet));
		return new AckNack(readerId,writerId,sns,count);
	}
	
	private HeartBeatFrag decodeHeartBeatFrag() throws MalformedSubmessageException {
		EntityId readerId = new EntityId(EntityId_tHelper.read(_packet));
		EntityId writerId = new EntityId(EntityId_tHelper.read(_packet));
		SequenceNumber writerSN = new SequenceNumber(SequenceNumber_tHelper.read(_packet));
		FragmentNumber fn = new FragmentNumber(FragmentNumber_tHelper.read(_packet));
		Count count = new Count(Count_tHelper.read(_packet));
		return new HeartBeatFrag(readerId,writerId,writerSN,fn,count);
	}
	
	private NackFrag decodeNackFrag() throws MalformedSubmessageException {
		EntityId readerId = new EntityId(EntityId_tHelper.read(_packet));
		EntityId writerId = new EntityId(EntityId_tHelper.read(_packet));
		SequenceNumber writerSN = new SequenceNumber(SequenceNumber_tHelper.read(_packet));
		FragmentNumberSet fragmentNumberState = FragmentNumberSet.read(_packet);
		Count count = new Count(Count_tHelper.read(_packet));
		return new NackFrag(readerId,writerId,writerSN,fragmentNumberState,count);
	}
	
	private Gap decodeGap() throws MalformedSubmessageException {
		EntityId readerId = new EntityId(EntityId_tHelper.read(_packet));
		EntityId writerId = new EntityId(EntityId_tHelper.read(_packet));
		SequenceNumber gapStart = new SequenceNumber(SequenceNumber_tHelper.read(_packet));
		SequenceNumberSet gapList = SequenceNumberSet.read(_packet);
		return new Gap(readerId,writerId,gapStart,gapList);
	}
	
	private Pad decodePad() throws MalformedSubmessageException {
		// PAD is just a 32 bits gap (made by its header)
		return new Pad();
	}
	
	private InfoTimestamp decodeInfoTimestamp() throws MalformedSubmessageException {
		Timestamp ts = null;
		if (BitUtility.getFlagAt(_flags,1)) {
			ts = new Timestamp(Time_tHelper.read(_packet));
		}
		return new InfoTimestamp(ts);
	}
	
	
}