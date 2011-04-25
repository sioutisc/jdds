//================================================================
//
//	CDROutputPacket.java - Created by kerush, 2006 21-ott-06 7:40:03 
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
package rtjdds.rtps.portable;

import java.io.IOException;

import org.omg.CORBA.portable.InputStream;

import rtjdds.util.BitUtility;
import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;

/**
 * @author kerush
 *
 */
public class CDROutputPacket extends OutputPacket {
	
    public static final int BYTE = 1;
    public static final int SHORT = 2;
    public static final int LONG = 4;
    public static final int LONGLONG = 8;
    
    private byte buffer[];
    private boolean isLittleEndian = false;
    private int currPos;
    private int limit;
    
    private boolean bufferFull = false;

    
	public CDROutputPacket(byte[] buffer, int length, boolean isLittleEndian) {
		this.buffer = buffer;
		this.limit = length;
		this.isLittleEndian = isLittleEndian;
		this.currPos = 0;
	}
	
	public void setEndianess(boolean value) {
		isLittleEndian = value;
	}
	
	public boolean getEndianess() {
		return isLittleEndian;
	}
	
	/**
	 * Prints the packet on a <code>String</code>, using a graphical
	 * view of 4 bytes per line, showing the single bits (32 bits per line)<br/>
	 * Wacthout, this method is computationally heavy (each byte has 
	 * to be parsed 8 times in order to have the bitted value). Do 
	 * not use it in realtime mode. 
	 * @return the String containing the bit representation of this buffer
	 */
	public String toBitString() {
		String out = "0...............8...............16..............24..............32\n";
		out 	  += "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+\n";
		for (int i=0; i<limit; i+=4) {
			out += "|";
			for (int j=0; j<4; j++) {
				for (int k=0; k<8 && i+j<buffer.length; k++) {
					out += BitUtility.getBitAt(buffer[i+j],k);
					if (j != 3  || k != 7) out += " ";
				}
			}
			out += "|\n";
			out += "+---------------+---------------+---------------+---------------+\n";
		}
		return out;
	}

	public InputStream create_input_stream() {
		return new CDRInputBuffer(buffer,limit,isLittleEndian);
	}
	
	public CDRInputBuffer create_cdr_input_stream() {
		return new CDRInputBuffer(buffer,limit,isLittleEndian);
	}
	
	/**
	 * Returns the internal byte array used as writing buffer
	 * @return
	 */
	public byte[] getBuffer() {
		return this.buffer;
	}
	
	private boolean checkBounds() {
		if (currPos < limit) {
			return true;
		}
		else {
			// log the event...
			// bufferFull = (length == limit)
			if (!bufferFull) {
//				GlobalProperties.logger.log(Logger.WARN, this.getClass(), "checkBounds()", 
//					"CDROutputPacket buffer is full, message will be truncated");
//				 the message will be truncated and no more writes are possible
				bufferFull = true;
			}
			return false;
		}
	}
	
	public int getLength() {
		return limit;
	}
	
	public void setLength(int newLength) {
		this.limit = newLength;
	}
	
	/**
	 * Sets the cursor at the given position
	 * @param pos
	 */
	public void setCursorPosition(int pos) {
		if (0 <= pos && pos < limit) {
			currPos = pos;
			bufferFull = false;
		}
		else {
			GlobalProperties.logger.log(Logger.WARN, this.getClass(), "setCursor()", 
			"Trying to set the cursor to an unallowed position, pos="+pos+" limit="+limit);
		}
	}
	
	/**
	 * Returns the current position of the cursor
	 * @return
	 */
	public int getCursorPosition() {
		return currPos;
	}
	

	/////////////////////
	// WRITING METHODS //
	/////////////////////
	
	/** 
	 * ALL write methods in this class are based upon
	 * <code>write_octet()</code> except methods that
	 * writes an array of values.
	 *  (non-Javadoc)
	 * @see org.omg.CORBA.portable.OutputStream#write_octet(byte)
	 */
	public void write_octet(byte value) {
		if (checkBounds()) {
			buffer[currPos] = value;
			currPos++;
		}
	}

	public void write_boolean(boolean value) {
		write_octet(value ? (byte) 1 : (byte) 0);
	}

	public void write_char(char value) {
		write_octet((byte)(value & 0xFF));
	}

	public void write_wchar(char value) {
        write_short((short)value);
	}

	/*
	 * Takes care of endianess.
	 *  (non-Javadoc)
	 * @see org.omg.CORBA.portable.OutputStream#write_short(short)
	 */
	public void write_short(short value) {
		byte b1 = (byte) ((value >>> 8) & 0xFF);
        byte b2 = (byte) (value & 0xFF);
        if (isLittleEndian) {
            write_octet(b2);
            write_octet(b1);
        } else {
        	write_octet(b2);
            write_octet(b1);
        }
	}

	public void write_ushort(short value) {
		write_short(value);
	}

	public void write_long(int value) {
		byte b1 = (byte) ((value >>> 24) & 0xFF);
        byte b2 = (byte) ((value >>> 16) & 0xFF);
        byte b3 = (byte) ((value >>> 8) & 0xFF);
        byte b4 = (byte) (value & 0xFF);
        if (isLittleEndian) {
            write_octet(b4);
            write_octet(b3);
            write_octet(b2);
            write_octet(b1);
        } else {
        	write_octet(b1);
        	write_octet(b2);
        	write_octet(b3);
        	write_octet(b4);
        }
	}

	public void write_ulong(int value) {
		write_long(value);
	}

	public void write_longlong(long value) {
		byte b1 = (byte) ((value >>> 56) & 0xFF);
        byte b2 = (byte) ((value >>> 48) & 0xFF);
        byte b3 = (byte) ((value >>> 40) & 0xFF);
        byte b4 = (byte) ((value >>> 32) & 0xFF);
		byte b5 = (byte) ((value >>> 24) & 0xFF);
        byte b6 = (byte) ((value >>> 16) & 0xFF);
        byte b7 = (byte) ((value >>> 8) & 0xFF);
        byte b8 = (byte) (value & 0xFF);
        if (isLittleEndian) {
        	write_octet(b8);
            write_octet(b7);
            write_octet(b6);
            write_octet(b5);
            write_octet(b4);
            write_octet(b3);
            write_octet(b2);
            write_octet(b1);
        } else {
        	write_octet(b1);
        	write_octet(b2);
        	write_octet(b3);
        	write_octet(b4);
        	write_octet(b8);
            write_octet(b7);
            write_octet(b6);
            write_octet(b5);
        }
	}

	public void write_ulonglong(long value) {
		write_longlong(value);
	}

	public void write_float(float value) {
		write_long(Float.floatToIntBits(value));
	}
 
	public void write_double(double value) {
		write_longlong(Double.doubleToLongBits(value));
	}

	public void write_string(String value) {
		int length = value.length();
		// first writes the string length plus one
        write_long(length + 1);
        // then each character of the string as bytes
        for (int i = 0; i < length; i++) {
            write_octet((byte) (value.charAt(i) & 0xff));
        }
        // finally the tailor zero
        write_octet((byte) 0);
	}

	public void write_wstring(String value) {
		int length = value.length();
		// first writes the string length plus one
		write_long(length + 1);
		// then each character of the string as a short (16 bits)
        for (int i = 0; i < length - 1; i++) {
            short v = (short) value.charAt(i);
            write_short(v);
        }
        // then the tailor zero (as a 16 bits short)
        write_short((short)0);
	}

	public void write_boolean_array(boolean[] value, int offset, int length) {
		for (int i = 0; i < length; i++) {
            write_octet(value[i + offset] ? (byte) 1 : (byte) 0);
        }
	}

	public void write_char_array(char[] value, int offset, int length) {
		for (int i = 0; i < length; i++) {
            write_octet((byte) (value[i + offset] & 0xff));
        }
	}

	public void write_wchar_array(char[] value, int offset, int length) {
		short svalue = 0;
        for (int i = 0; i < length; i++) {
            svalue = (short) value[offset + i];
            write_short(svalue);
        }
	}

	/*
	 * Here we use the system's array copy, instead of a loop
	 * that will introduce overhead...
	 * This method is strongly used by the application, so is
	 * better to improve its performances.
	 *  (non-Javadoc)
	 * @see org.omg.CORBA.portable.OutputStream#write_octet_array(byte[], int, int)
	 */
	public void write_octet_array(byte[] value, int offset, int length) {
		if (currPos+length <= limit) {
			System.arraycopy(value,offset,buffer,currPos,length);
			currPos += length;
		}
		else {
			// log the event...
			if (!bufferFull) {
//				GlobalProperties.logger.log(Logger.WARN, this.getClass(), "write_octet_array()", 
//					"CDROutputPacket buffer is full, message will be truncated");
//				 the message will be truncated and no more writes are possible
				bufferFull = true;
			}
		}
	}

	public void write_short_array(short[] value, int offset, int length) {
		int finish = offset + length;
        for (int i = offset; i < finish; i++) {
            write_short(value[i]);
        }
	}

	public void write_ushort_array(short[] value, int offset, int length) {
		write_short_array(value, offset, length);
	}

	public void write_long_array(int[] value, int offset, int length) {
		int finish = offset + length;
		for (int i = offset; i < finish; i++) {
            write_long(value[i]);
        }
	}

	public void write_ulong_array(int[] value, int offset, int length) {
		write_long_array(value, offset, length);
	}

	public void write_longlong_array(long[] value, int offset, int length) {
		int finish = offset + length;
        for (int i = offset; i < finish; i++) {
            write_longlong(value[i]);
        }
	}

	public void write_ulonglong_array(long[] value, int offset, int length) {
		write_longlong_array(value, offset, length);
	}

	public void write_float_array(float[] value, int offset, int length) {
		int finish = offset + length;
        for (int i = offset; i < finish; i++) {
            write_float(value[i]);
        }
	}

	public void write_double_array(double[] value, int offset, int length) {
		int finish = offset + length;
        for (int i = offset; i < finish; i++) {
            write_double(value[i]);
        }
    }

	public void write(int arg0) throws IOException {
		write_long(arg0);
//		GlobalProperties.logger.log(Logger.WARN, this.getClass(), "write(int arg0)", 
//				"Method not implemented!");
	}

//	public void write_Object(Object value) {
//		GlobalProperties.logger.log(Logger.WARN, this.getClass(), "write_Object()", 
//		"Method not implemented!");
//	}
//
//	public void write_TypeCode(TypeCode value) {
//		GlobalProperties.logger.log(Logger.WARN, this.getClass(), "write_TypeCode()", 
//		"Method not implemented!");
//	}
//
//	public void write_any(Any value) {
//		GlobalProperties.logger.log(Logger.WARN, this.getClass(), "write_anys()", 
//		"Method not implemented!");
//	}

}
