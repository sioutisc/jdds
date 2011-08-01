/* ********************************************************************* *
 *                                                                       *
 *   =============================================================       *
 *   Copyright 2011                                                      *
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

package jdds.rtps.messages.submessage.attribute;

import java.util.Collection;
import java.util.Vector;

import jdds.rtps.messages.submessage.SubmessageElement;

public class NumberSet<E extends Diff<E>> extends SubmessageElement {
	public static final int MAX_SET_INTERVAL = 256;
		
	private E base;
	private Vector<E> set = new Vector<E>();
	
	public NumberSet(E base){
		this.base = base;
	}
	
	public NumberSet(E b, Collection<E> c) throws IndexOutOfBoundsException {
		this.base = b;
		for(E e: c){
			add(e);
		}
	}
	
	public void add(E e) throws IndexOutOfBoundsException {
		if(e.diff(base) > MAX_SET_INTERVAL){
			throw new IndexOutOfBoundsException();
		}
		set.add(e);
	}
	
	public String toString(){
		StringBuffer s = new StringBuffer();
		s.append(base.toString()+"\n");
		for(E e: set){
			s.append("--"+e.toString()+ "\n");
		}
		return s.toString();
	}

}
