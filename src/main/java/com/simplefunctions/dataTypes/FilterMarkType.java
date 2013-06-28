package com.simplefunctions.dataTypes;

/**
 * Buran.
 *
 * @author: ${USER} Date: 23.06.13 Time: 17:33
 */
public class FilterMarkType extends DataType {

    private FilterMarkType() {
    }

    public static final FilterMarkType SINGLETON = new FilterMarkType();

    @Override
    public boolean isValid(Object data) {
        return isTypeNotNull(data, FilterMark.class);
    }

    @Override
    public String toString() {
        return "FilterMarkType{}";
    }
}
