package com.simplefunctions.dataTypes;

/**
 * Buran.
 *
 * @author: ${USER} Date: 23.06.13 Time: 17:33
 */
public class VoidType extends DataType {

    private VoidType() {
    }

    public static final VoidType SINGLETON = new VoidType();

    @Override
    public boolean isValid(Object data) {
        return data == null;
    }

    @Override
    public String toString() {
        return "VoidType{}";
    }
}
