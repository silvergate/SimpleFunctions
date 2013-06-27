package com.simplefunctions.functions.string;

/**
 * Buran.
 *
 * @author: ${USER} Date: 27.06.13 Time: 18:54
 */
public enum UtfType {
    utf8(1),
    utf16be(2),
    utf16le(2),
    utf32be(4),
    utf32le(4);

    private final short numOfBytes;

    private UtfType(int numOfBytes) {
        this.numOfBytes = (short) numOfBytes;
    }

    public short getNumOfBytes() {
        return numOfBytes;
    }
}
