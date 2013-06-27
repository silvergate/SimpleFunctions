package com.simplefunctions.dataTypes;

/**
 * Buran.
 *
 * @author: ${USER} Date: 23.06.13 Time: 14:12
 */
public class StringType extends DataType {
    private final int maxLen;
    private final int minLen;

    public StringType(int minLen, int maxLen) {
        if (maxLen < minLen) {
            throw new IllegalArgumentException("maxLen<minLen");
        }
        this.maxLen = maxLen;
        this.minLen = minLen;
    }

    public int getMaxLen() {
        return maxLen;
    }

    public int getMinLen() {
        return minLen;
    }

    @Override
    public boolean isValid(Object data) {
        if (!isTypeNotNull(data, String.class)) {
            return false;
        }
        final String castData = getData(data, String.class);
        final int strLen = castData.length();
        return ((strLen > this.minLen) && (strLen < this.maxLen));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StringType that = (StringType) o;

        if (maxLen != that.maxLen) return false;
        if (minLen != that.minLen) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = maxLen;
        result = 31 * result + minLen;
        return result;
    }

    @Override
    public String toString() {
        return "StringType{" +
                "maxLen=" + maxLen +
                ", minLen=" + minLen +
                '}';
    }
}
