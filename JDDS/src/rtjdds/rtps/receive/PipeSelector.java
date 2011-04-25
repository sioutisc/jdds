//================================================================
//
//	PipeSelector.java - Created by kerush, 2007 06/mar/07 10:55:41
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
package rtjdds.rtps.receive;

import java.nio.ByteBuffer;
import java.nio.channels.spi.AbstractSelector;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import rtjdds.rtps.transport.MailBox;
import rtjdds.util.concurrent.lf.Event;

/**
 * Integrare nel NIO... AbstractSelectableChannel e AbstractSelector...
 * @author kerush
 *
 */
public class PipeSelector extends ChannelSelector {
	
	private MailBox _boxes[] = null;
	private Set _selectedKeySet = new HashSet();
	
	public PipeSelector(int size, int prio) {
		_boxes = new MailBox[prio];
		for (int i = 0; i<prio; i++) {
			_boxes[i] = new MailBox(size, i);
		}
	}
	
    public synchronized Event getNextEvent() {
		while (_selectedKeySet.size() < 1) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Iterator it = _selectedKeySet.iterator();
		MailBox out = (MailBox) it.next();
		/*
		MailBox out = null;
		MailBox tmp = null;
		while (it.hasNext()) {
			tmp = (MailBox)it.next();
			if (out == null) out = tmp;
			if (tmp.getPriority() > out.getPriority()) out = tmp; 
		}
		*/
		_selectedKeySet.remove(out);
		return new SelectedPipeEvent(out);
	}

	public void unblock() {
		// TODO Auto-generated method stub
		
	}
	
	public void write(byte[] data, int prio) {
		_boxes[prio].write(data);
		synchronized (this) {
			_selectedKeySet.add(_boxes[prio]);
			notifyAll();
		}
	}
	
	public synchronized void select() {
		while (_selectedKeySet.size() >= 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public synchronized MailBox getHigherPrioritySelectedMailBox() {
		int prio = _boxes.length;
		while (prio >= _boxes.length) {
			Iterator it = _selectedKeySet.iterator();
			while (it.hasNext()) {
				MailBox mbox = (MailBox)it.next();
				if (mbox.getPriority() < prio) {
					prio = mbox.getPriority();
				}
			}
		}
		MailBox out = _boxes[prio];
		_selectedKeySet.remove(out);
		return out;
	}

}
