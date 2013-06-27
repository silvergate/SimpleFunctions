package com.simplefunctions.functions.common;

/**
 * Buran.
 *
 * @author: ${USER} Date: 27.06.13 Time: 18:27
 */
public enum BitWidth {
    bit8(8),
    bit16(16),
    bit32(32),
    bit64(64);

    short numOfBits;

    private BitWidth(int numOfBits) {
        this.numOfBits = (short) numOfBits;
    }

    public short getNumOfBits() {
        return numOfBits;
    }
}
