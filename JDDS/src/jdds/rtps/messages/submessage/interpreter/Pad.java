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

package jdds.rtps.messages.submessage.interpreter;

import jdds.rtps.messages.submessage.Submessage;

/**
 * From OMG RTPS Standard v2.1 p44: Used to add padding to a Message if needed for memory alignment.
 * <p>
 * From OMG RTPS Standard v2.1 p62: The purpose of this Submessage is to allow the introduction of 
 * any padding necessary to meet any desired memory- alignment requirements. Its has no other meaning.
 * 
 * @author Christos Sioutis <christos.sioutis@gmail.com>
 *
 */

public class Pad extends Submessage {

}
