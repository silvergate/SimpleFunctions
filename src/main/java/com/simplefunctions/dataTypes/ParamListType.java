package com.simplefunctions.dataTypes;

import com.simplefunctions.base.IDataType;

import java.util.Arrays;

/**
 * Buran.
 *
 * @author: ${USER} Date: 23.06.13 Time: 18:24
 */
public class ParamListType extends DataType {

    public static ParamListType c(IDataType... types) {
        return new ParamListType(types);
    }

    protected ParamListType(IDataType[] types) {
        this.types = types;
    }

    private final IDataType[] types;

    public int getNumOfTypes() {
        return this.types.length;
    }

    public IDataType getType(int index) {
        return this.types[index];
    }

    @Override
    public boolean isValid(Object data) {
        //TODO:
        return true;
    }

    @Override
    public String toString() {
        return "ParamListType{" +
                "types=" + Arrays.toString(types) +
                '}';
    }
}
