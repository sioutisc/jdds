//================================================================
//
//	InfoTimestamp.java - Created by kerush, 2006 24-ott-06 2:52:44 
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
package rtjdds.rtps.messages;

import javax.realtime.AbsoluteTime;
import javax.realtime.Clock;

import rtjdds.rtps.messages.elements.Timestamp;
import rtjdds.rtps.portable.CDROutputPacket;
import rtjdds.rtps.types.INFO_TS;
import rtjdds.rtps.types.Time_t;

public class InfoTimestamp extends Submessage {
	
	protected Timestamp t;

	public InfoTimestamp(Timestamp t) {
		super(INFO_TS.value);
		this.t = t;
		if (t != null) {
			super.setFlagAt(1,true);
		}
	}
	
	public static InfoTimestamp now() {
		//TODO change with System.nanoTime()
		AbsoluteTime now = Clock.getRealtimeClock().getTime();
		int seconds = (int) (now.getMilliseconds() * 1000);
		int nanosecs = now.getNanoseconds();
		Time_t time = new Time_t(seconds, nanosecs);
		Timestamp ts = new Timestamp(time);
		return new InfoTimestamp(ts);
	}

	protected void writeBody(CDROutputPacket os) {
		if (super.getFlagAt(1)) {
			this.t.write(os);
		}
	}

	/**
	 * @return Returns the t.
	 */
	public Timestamp getT() {
		return t;
	}
	
}
