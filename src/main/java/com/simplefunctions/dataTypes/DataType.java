package com.simplefunctions.dataTypes;

import com.simplefunctions.base.IDataType;

/**
 * Buran.
 *
 * @author: ${USER} Date: 23.06.13 Time: 14:16
 */
public abstract class DataType implements IDataType {
    protected boolean isTypeNotNull(Object data, Class<?> clazz) {
        if (data == null) {
            return false;
        }
        return (clazz.isAssignableFrom(data.getClass()));
    }

    protected <T extends Object> T getData(Object data, Class<?> clazz) {
        return (T) data;
    }

    @Override
    public IDataType combine(IDataType type) {
        if (type.getClass().equals(getClass())) {
            return combineSameType(type);
        } else {
            return AltType.combine(this, type);
        }
    }

    protected abstract IDataType combineSameType(IDataType type);
}
