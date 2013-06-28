package com.simplefunctions.base;

/**
 * Buran.
 *
 * @author: ${USER} Date: 23.06.13 Time: 14:11
 */
public interface IDataType {
    boolean isValid(Object data);

    IDataType combine(IDataType type);
}
