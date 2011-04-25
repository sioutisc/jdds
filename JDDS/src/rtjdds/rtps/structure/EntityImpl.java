package rtjdds.rtps.structure;

import rtjdds.rtps.messages.elements.GUID;
import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;
import rtjdds.util.collections.Copyable;
import rtjdds.util.collections.PoolManagedObject;
import DDS.StatusCondition;

public abstract class EntityImpl extends PoolManagedObject implements DDS.Entity, Copyable{
	
	protected static int _instanceCount = 0; 
	
	protected GUID _guid = null;
	
	
	/**
	 * Standard Constructor.
	 * @param guid
	 */
	public EntityImpl(GUID guid) {
		_instanceCount++;
		_guid = guid;
	}
	
	/**
	 * Lazy constructor, used to initialize objects
	 * in the pool.
	 *
	 */
	public EntityImpl() {
		_instanceCount++;
	}
		
	public GUID getGuid() {
		return _guid;
	}
	
	public boolean equals(Object obj) {
		return _guid.equals(obj);
	}
	
	//////////////////////////////////////////////////////////
	// methods from Copyable
	////////////////////////////////////////////////////////

	public void copyFrom(Copyable obj) {
		if (obj instanceof EntityImpl) {
			EntityImpl src = (EntityImpl) obj;
			_guid.copyFrom(src._guid);
		}
		else {
			GlobalProperties.logger.log(Logger.SEVERE,this.getClass(),"copyFrom()",
					"cannot copy from a not EntityImpl instance");
		}
	}

	public void copyTo(Copyable obj) {
		if (obj instanceof EntityImpl) {
			EntityImpl dst = (EntityImpl) obj;
			_guid.copyTo(dst._guid);
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
