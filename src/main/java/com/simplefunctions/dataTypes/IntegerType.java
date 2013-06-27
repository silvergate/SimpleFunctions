package com.simplefunctions.dataTypes;

/**
 * Buran.
 *
 * @author: ${USER} Date: 23.06.13 Time: 14:12
 */
public class IntegerType extends DataType {
    private final long maxValue;
    private final long minValue;

    public static final int MEMORY = 8;

    public IntegerType(long minValue, long maxValue) {
        this.maxValue = maxValue;
        this.minValue = minValue;
    }

    public long getMaxValue() {
        return maxValue;
    }

    public long getMinValue() {
        return minValue;
    }

    public boolean canBeNegative() {
        return (getMinValue() < 0) && (getMaxValue() < 0);
    }

    public boolean canContain(long number) {
        return (this.minValue <= number) && (this.maxValue >= number);
    }

    @Override
    public boolean isValid(Object data) {
        if (!isTypeNotNull(data, Number.class)) {
            return false;
        }
        final Number castData = getData(data, Number.class);
        final long value = castData.longValue();
        return ((value > this.minValue) && (value < this.maxValue));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntegerType that = (IntegerType) o;

        if (maxValue != that.maxValue) return false;
        if (minValue != that.minValue) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (maxValue ^ (maxValue >>> 32));
        result = 31 * result + (int) (minValue ^ (minValue >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "IntegerType{" +
                "maxValue=" + maxValue +
                ", minValue=" + minValue +
                '}';
    }
}
