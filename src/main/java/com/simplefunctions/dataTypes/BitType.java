package com.simplefunctions.dataTypes;

/**
 * Buran.
 *
 * @author: ${USER} Date: 23.06.13 Time: 14:12
 */
public class BitType extends DataType {
    private final int maxBits;
    private final int minBits;
    private final boolean byteAligned;

    @Deprecated
    public static final int MAX_NUM_OF_BITS = Integer.MAX_VALUE;

    public BitType(int minBits, int maxBits, boolean byteAligned) {
        this.byteAligned = byteAligned;
        if (minBits < 0) {
            throw new IllegalArgumentException();
        }
        this.maxBits = maxBits;
        this.minBits = minBits;
    }

    public int getMaxBits() {
        return maxBits;
    }

    public int getMinBits() {
        return minBits;
    }

    public boolean isByteAligned() {
        return byteAligned;
    }

    @Override
    public boolean isValid(Object data) {
        if (!isTypeNotNull(data, Binary.class)) {
            return false;
        }
        final int numOfBits = ((Binary) data).getNumOfBits();
        if (this.isByteAligned()) {
            if (((Binary) data).getNumOfBits() % 8 != 0) {
                return false;
            }
        }
        if (((Binary) data).getData().length * 8 < numOfBits) {
            return false;
        }
        return ((numOfBits > this.minBits) && (numOfBits < this.maxBits));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BitType that = (BitType) o;

        if (maxBits != that.maxBits) return false;
        if (minBits != that.minBits) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (maxBits ^ (maxBits >>> 32));
        result = 31 * result + (int) (minBits ^ (minBits >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "BitType{" +
                "maxBits=" + maxBits +
                ", minBits=" + minBits +
                '}';
    }
}
