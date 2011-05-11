//================================================================
//
//	Proxy.java - Created by kerush, 2006 1-dic-06 12:03:28 
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
package rtjdds.rtps.structure.remote;

import java.net.SocketAddress;

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

import rtjdds.rtps.messages.elements.EntityId;
import rtjdds.rtps.structure.local.Endpoint;
import rtjdds.rtps.transport.Sender;
import rtjdds.util.collections.PoolManagedObject;
import DDS.StatusCondition;

/**
 * Si riferisce ad un participant remoto (ParticipantProxy)
 * @author kerush
 *
 */
public class Proxy extends Endpoint {

	protected SocketAddress _remoteSocket = null;
	
	public Proxy(EntityId entityId, SocketAddress remoteSocket, int topicKind, int reliabilityKind) {
		
		super(null, entityId, topicKind, reliabilityKind);
		
		_remoteSocket = remoteSocket;
		
	}

	public int enable() {
		// TODO Auto-generated method stub
		return 0;
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
