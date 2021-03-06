package com.simplefunctions.dataTypes;

import com.simplefunctions.base.IDataType;

/**
 * Buran.
 *
 * @author: ${USER} Date: 23.06.13 Time: 14:12
 */
public class BooleanType extends DataType {

    public static enum BoolRange {
        trueOnly,
        falseOnly,
        trueOrFalse
    }

    private final BoolRange range;

    public BooleanType(BoolRange range) {
        this.range = range;
    }

    public BoolRange getRange() {
        return range;
    }

    @Override
    public boolean isValid(Object data) {
        if (!isTypeNotNull(data, Boolean.class)) {
            return false;
        }
        final Boolean cast = (Boolean) data;
        if (cast) {
            if (this.range == BoolRange.falseOnly) {
                return false;
            }
        } else {
            if (this.range == BoolRange.trueOnly) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BooleanType that = (BooleanType) o;

        if (range != that.range) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return range.hashCode();
    }

    @Override
    public String toString() {
        return "BooleanType{" +
                "range=" + range +
                '}';
    }

    @Override
    protected IDataType combineSameType(IDataType type) {
        final BoolRange otherRange = ((BooleanType) type).getRange();
        final BoolRange thisRange = getRange();

        boolean canBeTrue;
        boolean canBeFalse;
        boolean canBeBoth;
        canBeTrue = (otherRange == BoolRange.trueOnly) || (thisRange == BoolRange.trueOnly);
        canBeBoth = (otherRange == BoolRange.trueOrFalse) || (thisRange == BoolRange.trueOrFalse);
        canBeFalse = (otherRange == BoolRange.falseOnly) || (thisRange == BoolRange.falseOnly);

        final BoolRange newRange;
        if (canBeTrue && (!canBeBoth) && (!canBeFalse)) newRange = BoolRange.trueOnly;
        else if (canBeFalse && (!canBeBoth) && (!canBeTrue)) newRange = BoolRange.falseOnly;
        else newRange = BoolRange.trueOrFalse;

        return new BooleanType(newRange);
    }
}
