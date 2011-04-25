//================================================================
//
//	CDRInputPacket.java - Created by kerush, 2006 21-ott-06 7:40:03 
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
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import rtjdds.util.GlobalProperties;
import rtjdds.util.Logger;

/**
 * A CDR-encoded byte buffer.<br/>
 * This implementation is just a wrapper around a <code>java.nio.ByteBuffer</code> 
 * 
 * @author kerush
 *
 */
public class CDRInputBuffer extends InputPacket {
	
	public static final int BYTE = 1;
	public static final int SHORT = 2;
	public static final int LONG = 4;
	public static final int LONGLONG = 8;
	
	private ByteBuffer _buffer = null;
	
	/**
	 * Constructs a new <code>CDRInputPacket</code> over the given buffer
	 * of bytes, with the length of the buffer itself and the
	 * given default endianess setted to <i>Big Endian</i> (that is the Java
	 * default).
	 * @param buffer
	 */
	public CDRInputBuffer(byte[] buffer) {
		_buffer = ByteBuffer.wrap(buffer);
	}
	
	public CDRInputBuffer(int sizeInBytes) {
		_buffer = ByteBuffer.allocate(sizeInBytes);
	}
	
	/**
	 * Constructs a new <code>CDRInputPacket</code> over the given buffer
	 * of bytes, with the length of the buffer itself and the
	 * given endianess.
	 * @param buffer
	 * @param isLittleEndian
	 */
	public CDRInputBuffer(byte[] buffer, ByteOrder order) {
		this(buffer);
		_buffer.order(order); 
	}
	
	/**
	 * Sets the buffer of the packet to the given one.<br/>
	 * The cursor is resetted to the initial position
	 * @param buffer
	 */
	public void setBuffer(byte[] buffer) {
		_buffer = ByteBuffer.wrap(buffer);
		_buffer.rewind();
	}
	
	/**
	 * Returns the internal byte array used as reading buffer
	 * @return
	 */
	public byte[] getBuffer() {
		return _buffer.array();
	}
	
	/**
	 * Sets the length of the buffer 
	 * @param currPos
	 */
	public void setLength(int newLength) {
		_buffer.limit(newLength);
	}
	
	/**
	 * Returns the length of the internal buffer
	 * @return
	 */
	public int getLength() {
		return _buffer.limit();
	}
	
	/**
	 * Sets the endianess for message interpretation.
	 * @param value
	 */
	public void setEndianess(ByteOrder order) {
		_buffer.order(order);
	}
	
	public ByteOrder getEndianess() {
		return _buffer.order();
	}
	
	/**
	 * Sets the cursor at the given position
	 * @param pos
	 */
	public void setCursorPosition(int pos) {
		try{
			_buffer.position(pos);
		}
		catch (IllegalArgumentException e) {
			GlobalProperties.logger.log(Logger.WARN, this.getClass(), "setCursor()", 
				"Trying to set the cursor to an unallowed position");
		}
	}
	
	/**
	 * Returns the current position of the cursor
	 * @return
	 */
	public int getCursorPosition() {
		return _buffer.position();
	}
	
	/////////////////////
	// READING METHODS //
	/////////////////////
	
	/**
	 * Base method on which all others reading methods are
	 * based, except the <code>read_octet_array()<code>.
	 * It returns the byte at the current cursor position
	 * and then increment the position. If the cursor is
	 * at the end of the buffer, it doesn't increment and
	 * returns always the same value, until the cursor is
	 * moved back.
	 */
	public byte read_octet() {
		byte retVal = (byte)0;
		try {
			retVal = _buffer.get();
		}
		catch (BufferUnderflowException ex) {
			
		}
		return retVal;
	}
	
	/**
	 * Reads an array of bytes from the packet
	 */
	public void read_octet_array(byte[] v, int offset, int length) {
		if (currPos+length <= limit) {
			System.arraycopy(buffer,currPos,v,offset,length);
			currPos += length; 
		}
		else {
// 			copy only the rest
			System.arraycopy(buffer,currPos,v,offset,limit-currPos);
			currPos = limit;
//			 log the event...
			if (!bufferFull) {
				GlobalProperties.logger.log(Logger.WARN, this.getClass(), "read_octet_array()", 
					"CDRInputPacket buffer is full, message will be truncated");
//				 the message will be truncated and no more writes are possible
				bufferFull = true;
			}
		}
	}
	
	public boolean read_boolean() {
		return read_octet()!=0;
	}
	
	public char read_char() {
		return (char)read_octet();
	}
	
	public char read_wchar() {
		return (char)read_short();
	}
	
	public short read_short() {
        byte b1 = read_octet();
        byte b2 = read_octet();

        short ret = 0;
        if (isLittleEndian) {
            ret |= b2 & 0xFF;
            ret <<= 8;
            ret |= b1 & 0xFF;
            return ret;
        } else {
            ret |= b1 & 0xFF;
            ret <<= 8;
            ret |= b2 & 0xFF;
            return ret;
        }
	}
	
	public short read_ushort() {
		return read_short();
	}
	
	public int read_long() {
		byte b1 = read_octet();
        byte b2 = read_octet();
        byte b3 = read_octet();
        byte b4 = read_octet();

        int ret = 0;
        if (isLittleEndian) {
            ret |= b4 & 0xFF;
            ret <<= 8;
            ret |= b3 & 0xFF;
            ret <<= 8;
            ret |= b2 & 0xFF;
            ret <<= 8;
            ret |= b1 & 0xFF;
            return ret;
        } else {
            ret |= b1 & 0xFF;
            ret <<= 8;
            ret |= b2 & 0xFF;
            ret <<= 8;
            ret |= b3 & 0xFF;
            ret <<= 8;
            ret |= b4 & 0xFF;
            return ret;
        }
	}
	
	public int read_ulong() {
		return read_long();
	}
	
	public long read_longlong() {
		byte b1 = read_octet();
        byte b2 = read_octet();
        byte b3 = read_octet();
        byte b4 = read_octet();
        byte b5 = read_octet();
        byte b6 = read_octet();
        byte b7 = read_octet();
        byte b8 = read_octet();

        long ret = 0;
        if (isLittleEndian) {
            ret += ((long) (b8 & 0xFF)) << 56;
            ret += ((long) (b7 & 0xFF)) << 48;
            ret += ((long) (b6 & 0xFF)) << 40;
            ret += ((long) (b5 & 0xFF)) << 32;
            ret += ((long) (b4 & 0xFF)) << 24;
            ret += ((long) (b3 & 0xFF)) << 16;
            ret += ((long) (b2 & 0xFF)) << 8;
            ret += ((long) (b1 & 0xFF)) << 0;
            return ret;
        } else {
            ret += ((long) (b1 & 0xFF)) << 56;
            ret += ((long) (b2 & 0xFF)) << 48;
            ret += ((long) (b3 & 0xFF)) << 40;
            ret += ((long) (b4 & 0xFF)) << 32;
            ret += ((long) (b5 & 0xFF)) << 24;
            ret += ((long) (b6 & 0xFF)) << 16;
            ret += ((long) (b7 & 0xFF)) << 8;
            ret += ((long) (b8 & 0xFF)) << 0;
            return ret;
        }
	}
	
	public long read_ulonglong() {
		return read_longlong();
	}
	
	public float read_float() {
		return Float.intBitsToFloat(read_long());
	}
	
	public double read_double() {
		return Double.longBitsToDouble(read_longlong());
	}
	
	public String read_string() {
		// first, we read the length of the string as a long (4 bytes) value
        int len = read_long();
        // to exclude the tailoring zero
        len--;
        // we read the string into an array of bytes
        byte buf[] = new byte[len];
        read_octet_array(buf, 0, len);
        // go ahead the tailoring '0'
        read_octet();
        // converts the array of bytes into a String object
        StringBuffer strbuf = new StringBuffer();
        for (int i = 0; i < len; i++)
            strbuf.append((char) buf[i]);
        return strbuf.toString();
	}
	
	public String read_wstring() {
		int len = read_long();
        int actualLen = len - 1;
        char[] buf = new char[actualLen];
        for (int i = 0; i < actualLen; i++) {
            buf[i] = read_wchar();
        }
        read_wchar(); // skip the null
        return new String(buf);
	}
	
	public void read_boolean_array(boolean[] value, int offset, int length) {
		int finish = offset + length;
		for (int i = offset; i < finish; i++) {
			value[i] = read_boolean();
		}
	}
	
	public void read_char_array(char[] value, int offset, int length) {
		int finish = offset + length;
		for (int i = offset; i < finish; i++) {
			value[i] = read_char();
		}
	}
	
	public void read_wchar_array(char[] value, int offset, int length) {
		int finish = offset + length;
		for (int i = offset; i < finish; i++) {
			value[i] = read_wchar();
		}
	}
	
	public void read_short_array(short[] value, int offset, int length) {
		int finish = offset + length;
		for (int i = offset; i < finish; i++) {
			value[i] = read_short();
		}
	}
	
	public void read_ushort_array(short[] value, int offset, int length) {
		read_short_array(value,offset,length);
	}
	
	public void read_long_array(int[] value, int offset, int length) {
		int finish = offset + length;
		for (int i = offset; i < finish; i++) {
			value[i] = read_short();
		}
	}
	
	public void read_ulong_array(int[] value, int offset, int length) {
		read_long_array(value,offset,length);
	}
	
	public void read_longlong_array(long[] value, int offset, int length) {
		int finish = offset + length;
		for (int i = offset; i < finish; i++) {
			value[i] = read_longlong();
		}	
	}
	
	public void read_ulonglong_array(long[] value, int offset, int length) {
		read_longlong_array(value,offset,length);
	}
	
	public void read_float_array(float[] value, int offset, int length) {
		int finish = offset + length;
		for (int i = offset; i < finish; i++) {
			value[i] = read_float();
		}
	}
	
	public void read_double_array(double[] value, int offset, int length) {
		int finish = offset + length;
		for (int i = offset; i < finish; i++) {
			value[i] = read_double();
		}
	}

	public int read() throws IOException {
		GlobalProperties.logger.log(Logger.WARN, this.getClass(), "read()", 
				"Method not implemented!");
		return 0;
	}
	
//	public Object read_Object() {
//		GlobalProperties.logger.log(Logger.WARN, this.getClass(), "read_Object()", 
//		"Method not implemented!");
//		return null;
//	}
//	
//	public TypeCode read_TypeCode() {
//		GlobalProperties.logger.log(Logger.WARN, this.getClass(), "read_TypeCode()", 
//		"Method not implemented!");
//		return null;
//	}
//	
//	public Any read_any() {
//		GlobalProperties.logger.log(Logger.WARN, this.getClass(), "read_any()", 
//		"Method not implemented!");
//		return null;
//	}
	
}
