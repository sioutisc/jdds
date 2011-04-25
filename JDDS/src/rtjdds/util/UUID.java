//================================================================
//
//	UUID.java - Created by kerush, 2006 23-nov-06 3:47:46 
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


import java.io.Serializable;

/**
 * A <code>UUID</code> is a 128-bit universally unique identifier.
 * The most significant long can be decomposed into the following
 * unsigned fields:
 * <pre>
 * 0xFFFFFFFF00000000 time_low
 * 0x00000000FFFF0000 time_mid
 * 0x000000000000F000 version
 * 0x0000000000000FFF time_hi
 * </pre>
 * <p/>The least significant long can be decomposed into the following
 * unsigned fields:
 * <pre>
 * 0xC000000000000000 variant
 * 0x3FFF000000000000 clock_seq
 * 0x0000FFFFFFFFFFFF node
 * </pre>
 * <p/>The variant field must be 0x2. The version field must be either 0x1 or 0x4.
 *
 * <p/>If the version field is 0x4, then the most significant bit of the node
 * field must be set to 1, and the remaining fields are set to values
 * produced by a cryptographically strong pseudo-random number generator.
 *
 * <p/>If the version field is 0x1, then the node field is set to an IEEE 802
 * address (MAC), the clock_seq field is set to a 14-bit random number, and the
 * time_low, time_mid, and time_hi fields are set to the least, middle and
 * most significant bits (respectively) of a 60-bit timestamp measured in
 * 100-nanosecond units since midnight, October 15, 1582 UTC.
 *
 * @see         rtjdds.util.GUIDPrefixFactory
 */
public final class UUID implements Serializable {

    /**
     *  The null UUID. Useful for comparisons.
     */
    public static final UUID nullUUID = new UUID(0L, 0L);

    /**                                      
     * The most significant 64 bits.
     */
    private long mostSig;

    /**
     * The least significant 64 bits.
     */
    private long leastSig;

    /**
     * Simple constructor. This constructor does not check to ensure that the
     * values provided produce a valid UUID.
     *
     * @param mostSig the most significant 64 bits
     * @param leastSig the least significant 64 bits
     */
    public UUID(long mostSig, long leastSig) {
        this.mostSig = mostSig;
        this.leastSig = leastSig;
    }

    /**
     * Simple constructor. This constructor does not check to ensure that the
     * values provided produce a valid UUID.
     *
     * @param bytes the 128 bits of
     */
    public UUID(byte [] bytes) {
        if(bytes.length != 16) {
            throw new IllegalArgumentException("bytes must be 16 bytes in length");
        }

        long mostSig = 0;
        for (int i = 0; i < 8; i++) {
            mostSig = (mostSig << 8) | (bytes[i] & 0xff);
        }

        long leastSig = 0;
        for (int i = 8; i < 16; i++) {
            leastSig = (leastSig << 8) | (bytes[i] & 0xff);
        }

        this.mostSig = mostSig;
        this.leastSig = leastSig;
    }

    public UUID(String uuid) {
        if(36 != uuid.length()) {
            throw new IllegalArgumentException("uuid must be 36 characters in length");
        }

        byte bytes [] = new byte [16];

        boolean hi = true;
        for(int eachChar = 0, offset = 0; eachChar < 36; eachChar++) {
            char aChar = uuid.charAt(eachChar);
            switch(eachChar) {
            case 8:
            case 13:
            case 18:
            case 23:
                if('-' != uuid.charAt(eachChar)) {
                    throw new IllegalArgumentException("uuid has an illegal character at position " + eachChar);
                }
                break;

            default :
                aChar = Character.toLowerCase(aChar);

                int aNibble;
                if (('0' <= aChar) && ('9' >= aChar))
                    aNibble = aChar - '0';
                else if (('a' <= aChar) && ('f' >= aChar))
                    aNibble = aChar - 'a' + 10;
                else
                    throw new IllegalArgumentException("uuid has an illegal character at position " + eachChar);

                if (hi) {
                    aNibble <<= 4;
                }

                bytes[offset] |= aNibble;

                if(!hi) {
                    offset++;
                }

                hi = !hi;
            }
        }

        mostSig = 0;
        for (int i = 0; i < 8; i++) {
            mostSig = (mostSig << 8) | (bytes[i] & 0xff);
        }

        leastSig = 0;
        for (int i = 8; i < 16; i++) {
            leastSig = (leastSig << 8) | (bytes[i] & 0xff);
        }
    }

    /**
     * Returns the most significant 64 bits of the UUID.
     *
     * @return long return most significant bits
     */
    public long getMostSignificantBits() {
        return mostSig;
    }

    /**
     * Returns the least significant 64 bits of the UUID.
     *
     * @return long least significant bits
     */
    public long getLeastSignificantBits() {
        return leastSig;
    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (int)((mostSig >> 32) ^ mostSig ^ (leastSig >> 32) ^ leastSig);
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object target) {
        if (this == target) {
            return true;
        }

        if (target instanceof UUID) {
            return equals((UUID) target);
        } else {
            return false;
        }
    }

    /**
     * UUIDs are equal if they represent the same 128-bit value.
     *
     * @param sid UUID seed
     * @return boolean true if equals
     */
    public boolean equals(UUID sid) {
        if(null == sid) {
            return false;
        }

        return (mostSig == sid.mostSig && leastSig == sid.leastSig);
    }

    /**
     * Returns a 36-character string of six fields separated by hyphens,
     * with each field represented in lowercase hexadecimal with the same
     * number of digits as in the field. The order of fields is: time_low,
     * time_mid, version and time_hi treated as a single field, variant and
     * clock_seq treated as a single field, and node.
     *
     * ie. XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX
     *
     * @return String return value
     */
    public String toString() {
        return (digits(mostSig >> 32, 8) + "-" +
                digits(mostSig >> 16, 4) + "-" +
                digits(mostSig, 4) + "-" +
                digits(leastSig >> 48, 4) + "-" +
                digits(leastSig, 12));
    }

    /**
     * Returns val represented by the specified number of hex digits
     *
     * @param val value
     * @param digits
     * @return String return value
     */
    private static String digits(long val, int digits) {
        long hi = 1L << (digits * 4);
        return Long.toHexString(hi | (val & (hi - 1))).substring(1);
    }
}
