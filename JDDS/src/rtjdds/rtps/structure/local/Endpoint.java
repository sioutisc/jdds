package rtjdds.rtps.structure.local;

import rtjdds.rtps.messages.elements.EntityId;
import rtjdds.rtps.messages.elements.GUID;
import rtjdds.rtps.structure.EntityImpl;
import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;
import rtjdds.util.collections.Copyable;
import DDS.StatusCondition;


/**
 * An Endpoint exposes itself on the domain in order
 * to receive or publish data on a topic.
 * 
 * @author kerush
 *
 */
public abstract class Endpoint extends EntityImpl implements Copyable {
	
	/* reference to the participant */
	protected ParticipantImpl _participant = null;

	/* WITH_KEY or NO_KEY */
	protected int _topicKind;
	
	/* RELIABLE or UNRELIABLE */
	protected int _reliabilityKind; 
	
	public Endpoint(ParticipantImpl participant, EntityId entityId, int topicKind, int reliabilityKind) {
		super(new GUID(participant.getGuid().getPrefix(), entityId));
		this._topicKind = topicKind;
		this._reliabilityKind = reliabilityKind;
	}
	
	//////////////////////////////////////////////////////////
	// methods from Copyable
	////////////////////////////////////////////////////////

	public void copyFrom(Copyable obj) {
		if (obj instanceof Endpoint) {
			Endpoint src = (Endpoint) obj;
			super.copyFrom(src);
			// TODO: forse copiare solo riferimento
			_participant.copyFrom(src);
			_topicKind = src._topicKind;
			_reliabilityKind = src._reliabilityKind;
		}
		else {
			GlobalProperties.logger.log(Logger.SEVERE,this.getClass(),"copyFrom()",
					"cannot copy from a not EntityImpl instance");
		}
	}

	public void copyTo(Copyable obj) {
		if (obj instanceof Endpoint) {
			Endpoint dst = (Endpoint) obj;
			super.copyTo(dst);
			// TODO: forse copiare solo riferimento
			_participant.copyTo(dst);
			dst._topicKind = _topicKind;
			dst._reliabilityKind = _reliabilityKind;
		}
		else {
			GlobalProperties.logger.log(Logger.SEVERE,this.getClass(),"copyFrom()",
					"cannot copy from a not EntityImpl instance");
		}
	}

	////////////////////////////////////////////////////////
	// methods from DDS::Entity
	////////////////////////////////////////////////////////

	public abstract int enable();

	public abstract StatusCondition get_statuscondition();

	public abstract int get_status_changes();

}
