package com.simplefunctions.functions.base;

import com.simplefunctions.base.IDataType;
import com.simplefunctions.base.InvalidDataTypeException;
import com.simplefunctions.dataTypes.ParamListType;
import com.sun.istack.internal.Nullable;

/**
 * Buran.
 *
 * @author: ${USER} Date: 24.06.13 Time: 00:12
 */
public class TypeUtils {
    public static <T extends IDataType> T castType(IDataType type, Class<T> clazz)
            throws InvalidDataTypeException {
        if (!clazz.isAssignableFrom(type.getClass())) {
            throw new InvalidDataTypeException();
        }
        return (T) type;
    }

    public static <T extends IDataType> void assertType(IDataType type, Class<T> clazz)
            throws InvalidDataTypeException {
        if (!clazz.isAssignableFrom(type.getClass())) {
            throw new InvalidDataTypeException();
        }
    }

    public static int getNumOfParams(IDataType type) throws InvalidDataTypeException {
        if (type instanceof ParamListType) {
            ParamListType paramListType = castType(type, ParamListType.class);
            return paramListType.getNumOfTypes();
        } else {
            return 1;
        }
    }

    @Nullable
    public static IDataType getTypeAtIndex(IDataType type, int index) {
        if (type instanceof ParamListType) {
            final ParamListType paramListType = (ParamListType) type;
            if (index >= paramListType.getNumOfTypes()) {
                return null;
            }
            return paramListType.getType(index);
        } else {
            if (index != 0) {
                return null;
            }
            return type;
        }
    }

    @Nullable
    public static <T extends IDataType> T getTypeAtIndex(IDataType type, int index, Class<T> clazz)
            throws InvalidDataTypeException {
        final IDataType typeGot = getTypeAtIndex(type, index);
        if (typeGot == null) {
            return null;
        }
        if (!clazz.isAssignableFrom(typeGot.getClass())) {
            throw new InvalidDataTypeException();
        }
        return (T) typeGot;
    }

    public static IDataType getTypeAtIndexRequired(IDataType type, int index)
            throws InvalidDataTypeException {
        final IDataType typeGot = getTypeAtIndex(type, index);
        if (typeGot == null) {
            throw new InvalidDataTypeException();
        }
        return typeGot;
    }

    public static <T extends IDataType> T getTypeAtIndexRequired(IDataType type, int index,
            Class<T> clazz) throws InvalidDataTypeException {
        return (T) getTypeAtIndexRequired(type, index);
    }

}
