//================================================================
//
//	BitUtility.java - Created by kerush, 2006 22-ott-06 3:25:00 
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
package rtjdds.util;

/**
 * Contains various static methods usefuls when operating
 * with bits and bytes in java.
 * 
 * @author kerush
 *
 */
public class BitUtility {
	
	/**
	 * Extracts the value of the bit specified by
	 * its position in a byte
	 * 
	 * @param b
	 * 			The byte to analyze
	 * @param pos
	 * 			The position of the bit within the
	 * 			passed byte. Can range between 0 and 7,
	 * 			other values are permitted but the return
	 * 			value is always 0.
	 * @return 0 or 1
	 */
	public static int getBitAt(byte b, int pos) {
		switch (pos) {
			case 0:		return (b & 0x01) >> 0;
			case 1:		return (b & 0x02) >> 1;
			case 2:		return (b & 0x04) >> 2;
			case 3:		return (b & 0x08) >> 3;
			case 4:		return (b & 0x10) >> 4;
			case 5:		return (b & 0x20) >> 5;
			case 6:		return (b & 0x40) >> 6;
			case 7:		return (b & 0x80) >> 8;
			default:	return 0;
		}
	}
	
	/**
	 * Extracts the boolean value of the bit specified by
	 * its position in a byte
	 * 
	 * @param b
	 * 			The byte to analyze
	 * @param pos
	 * 			The position of the bit within the
	 * 			passed byte. Can range between 0 and 7,
	 * 			other values are permitted but the return
	 * 			value is always 0.
	 * @return true if the specified bit has value 1, false
	 * 		   otherwise
	 */
	public static boolean getFlagAt(byte b, int pos) {
		return (BitUtility.getBitAt(b,pos) != 0);
	}
	
	/**
	 * Modifies the value of the bit specified by
	 * its position in the given byte.<br/>
	 * Note that the return value has to be used
	 * as the result of the operation, since the
	 * method operates on a copy of the input byte.
	 * This copy will be returned mofified.<br/>
	 * A typical usage is the following:<br/>
	 * <code>
	 * 	flags = BitUtility.setBitAt(flags, 5, true);
	 * </code>
	 * 
	 * @param flags
	 * 			The byte to modify
	 * @param pos
	 * 			The position of the bit within the
	 * 			passed byte. Can range between 0 and 7,
	 * 			other values are permitted but the return
	 * 			value is always 0.
	 * @param value
	 * 			A boolean that indicates the value to
	 * 			set at the specified position. true means
	 * 			1, false means 0.
	 * @return  The resulting byte
	 */
	public static byte setBitAt(byte flags, int pos, boolean value) {
		if (value) {
			switch (pos) {
			case 0:	flags |= (byte)0x01; break;
			case 1:	flags |= (byte)0x02; break;
			case 2:	flags |= (byte)0x04; break;
			case 3:	flags |= (byte)0x08; break;
			case 4:	flags |= (byte)0x10; break;
			case 5:	flags |= (byte)0x20; break;
			case 6:	flags |= (byte)0x40; break;
			case 7:	flags |= (byte)0x80; break;
			}	
		}
		else {
			switch (pos) {
			case 0:	flags &= 0xFE; break;
			case 1:	flags &= 0xFD; break;
			case 2:	flags &= 0xFB; break;
			case 3:	flags &= 0xF7; break;
			case 4:	flags &= 0xEF; break;
			case 5:	flags &= 0xDF; break;
			case 6:	flags &= 0xBF; break;
			case 7:	flags &= 0x7F; break;
			}
		}
		return flags;
	}
	
	/**
	 * This method returns the decimal value of
	 * an array of booleans. If <code>arr.length</code>
	 * is higher than 8, the value is truncated at
	 * the 8th bit.<br/>
	 * @param arr
	 * @return sum over i, i range from 0 to 7, of arr[i]*2^i
	 */
	public static byte getByteValue (boolean[] arr) {
		if (arr.length < 8) {
			return 0;
		}
		byte out = 0;
		if (arr[0]) out = (byte)(out & 0x01);
		else if (arr[1]) out = (byte)(out & 0x02);
		else if (arr[2]) out = (byte)(out & 0x04);
		else if (arr[3]) out = (byte)(out & 0x08);
		else if (arr[4]) out = (byte)(out & 0x10);
		else if (arr[5]) out = (byte)(out & 0x20);
		else if (arr[6]) out = (byte)(out & 0x40);
		else if (arr[7]) out = (byte)(out & 0x80);
		return out;
	}
	
	/**
	 * Prints the passed byte on a String following
	 * the hexadecimal representation.
	 * 
	 * @param b
	 * @return
	 */
	public static String getHexString(byte b) {
		String out = "0x";
		out += Integer.toHexString((int)b);
		return out;
	}
	
	/**
	 * Prints the passed array of bytes on a String,
	 * using the hexadecimal representation.
	 * 
	 * @param b
	 * @return
	 */
	public static String getHexString(byte[] b) {
		String out = "0x";
		for (int i=0; i<b.length; i++) {
			String str = Integer.toHexString(Math.abs((int)b[i]));
			out += str;
		}
		return out;
	}
	
	/**
	 * Prints on a String the value of the passed byte
	 * represented bit by bit.
	 * 
	 * @param b
	 * @return
	 */
	public static String getBitByBitString(byte b) {
		String str = "";
		for (int i=0; i<8; i++) {
			str = ((b >> i) & 0x01) + str;
		}
		return str;
	}
	
	/**
	 * Prints the given array of bytes on a String
	 * using the nice bracked expression.
	 * 
	 * @param array
	 * @return
	 */
	public static String getNiceString(byte[] array) {
		String out = "{";
		for (int i=0; i<array.length; i++) {
			out += "["+array[i]+"]";
		}
		return out+"}";
	}
	
	/**
	 * Converts an <code>int</code> into an array of
	 * 4 bytes.
	 * TODO: add a switch for endianess
	 * @param value
	 * @return
	 */
	public static byte[] intToBytes(int value) {
		byte[] out = new byte[4];
		out[0] = (byte)(value >> 0) ;
		out[1] = (byte)(value >> 8) ;
		out[2] = (byte)(value >> 16);
		out[3] = (byte)(value >> 24);
		return out;
	}
	
	/**
	 * Converts the passed array of bytes into an
	 * <code>int</code> value. If the length of the
	 * array is greater than 4, only the first 4
	 * values are considered. 
	 * Assumes bytes[0] as the most significative byte.
	 * 
	 * TODO add a switch for endianess
	 * @param bytes
	 * @return
	 */
	public static int bytesToInt(byte[] bytes) {
		int out = 0;
		for (int i=0; i<bytes.length || i<3; i++) {
			out = (out << i*4) | bytes[i];
		}
		return out;
	}

}
