package com.simplefunctions.dataTypes;

import java.util.Arrays;

/**
 * Buran.
 *
 * @author: ${USER} Date: 27.06.13 Time: 18:18
 */
public class Binary {
    private final int bitSubstract;
    private final byte[] data;

    public Binary(int bitSubstract, byte[] data) {
        if (bitSubstract < 0) {
            throw new IllegalArgumentException("(bitSubstract<0)");
        }
        if (data.length * 8 - bitSubstract < 0) {
            throw new IllegalArgumentException("(data.length * 8 - bitSubstract<0)");
        }
        this.bitSubstract = bitSubstract;
        this.data = data;
    }

    public Binary(byte[] data) {
        this(0, data);
    }

    public int getNumOfBits() {
        return this.data.length * 8 - bitSubstract;
    }

    public int getNumberOfBytesCeil() {
        return (int) Math.ceil((double) getNumOfBits() / 8d);
    }

    public boolean isByteAligned() {
        final int numOfBits = getNumOfBits();
        return (numOfBits % 8) == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Binary binary = (Binary) o;

        if (bitSubstract != binary.bitSubstract) return false;
        if (!Arrays.equals(data, binary.data)) return false;

        return true;
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public int hashCode() {
        int result = bitSubstract;
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "Binary{" +
                "bitSubstract=" + bitSubstract +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
