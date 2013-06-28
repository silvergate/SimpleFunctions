package com.simplefunctions.dataTypes;

import com.simplefunctions.base.IDataType;
import com.simplefunctions.base.IFunctionType;

/**
 * Buran.
 *
 * @author: ${USER} Date: 23.06.13 Time: 14:12
 */
public class FunctionTypeType extends DataType {

    private final IFunctionType functionType;

    public FunctionTypeType(IFunctionType functionType) {
        this.functionType = functionType;
    }

    public IFunctionType getValue() {
        return this.functionType;
    }

    @Override
    public boolean isValid(Object data) {
        if (!isTypeNotNull(data, IFunctionType.class)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FunctionTypeType{}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FunctionTypeType that = (FunctionTypeType) o;

        if (!functionType.equals(that.functionType)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return functionType.hashCode();
    }

    @Override
    protected IDataType combineSameType(IDataType type) {
        return AltType.combine(this, type);
    }
}
