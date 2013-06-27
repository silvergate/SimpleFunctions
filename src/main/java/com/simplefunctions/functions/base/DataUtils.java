package com.simplefunctions.functions.base;

import com.simplefunctions.base.IDataType;
import com.simplefunctions.base.IEvaluable;
import com.simplefunctions.base.IFunction;
import com.simplefunctions.dataTypes.ParamList;
import com.simplefunctions.dataTypes.ParamListType;
import com.sun.istack.internal.Nullable;

/**
 * Buran.
 *
 * @author: ${USER} Date: 24.06.13 Time: 00:15
 */
public class DataUtils {
    public static IFunction getFunctionAtIndex(IDataType type, IFunction input, int index) {
        if (type instanceof ParamListType) {
            final Object eval = input.eval();
            return ((IFunction[]) eval)[index];
        } else {
            if (index != 0) {
                throw new IllegalArgumentException("Has 1 input argument. Index must be 0.");
            }
            return input;
        }
    }

    public static Object getDataAtIndex(IFunction input, int index) {
        final Object eval = input.eval();

        if (eval instanceof ParamList) {
            /* Multiple params */
            final ParamList paramList = (ParamList) eval;
            final IEvaluable[] innerFunctions = paramList.getFunctions();
            return innerFunctions[index].eval();
        } else {
            /* Single param */
            if (index != 0) {
                throw new IllegalArgumentException("Has 1 input argument. Index must be 0.");
            }
            return input.eval();
        }
    }

    @Nullable
    public static Object getDataAtIndexOptional(IEvaluable input, int index) {
        final Object eval = input.eval();

        if (eval instanceof ParamList) {
            /* Multiple params */
            final ParamList paramList = (ParamList) eval;
            final IEvaluable[] innerFunctions = paramList.getFunctions();
            if (index >= innerFunctions.length) {
                return null;
            }
            return innerFunctions[index].eval();
        } else {
            /* Single param */
            if (index > 0) {
                return null;
            }
            return input.eval();
        }
    }

    public static <T extends Object> T getDataAtIndex(IFunction input, int index, Class<T> clazz) {
        final Object data = getDataAtIndex(input, index);
        return (T) data;
    }

    public static <T extends Object> T getDataAtIndex(IDataType type, IFunction input, int index,
            Class<T> clazz) {
        final IFunction fun = getFunctionAtIndex(type, input, index);
        return (T) fun.eval();
    }

}
