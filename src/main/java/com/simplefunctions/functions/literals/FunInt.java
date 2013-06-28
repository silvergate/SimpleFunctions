package com.simplefunctions.functions.literals;

import com.simplefunctions.base.Function;
import com.simplefunctions.base.FunctionMetrics;
import com.simplefunctions.base.IFunction;
import com.simplefunctions.base.InvalidDataTypeException;
import com.simplefunctions.dataTypes.IntegerType;
import com.simplefunctions.dataTypes.VoidType;
import com.simplefunctions.functions.base.EvalMethod;
import com.simplefunctions.functions.base.FunctionTypeBase;
import com.simplefunctions.functions.base.MetricsMethod;

/**
 * Buran.
 *
 * @author: ${USER} Date: 23.06.13 Time: 17:28
 */
public class FunInt extends FunctionTypeBase {

    public static IFunction c(long value) {
        return new Function(new FunInt(value), FunVoid.FUN);
    }

    public static IFunction[] fromArray(long... values) {
        final IFunction[] results = new IFunction[values.length];
        for (int i = 0; i < values.length; i++) {
            results[i] = c(values[i]);
        }
        return results;
    }

    public FunInt(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    private final long value;

    @MetricsMethod
    public FunctionMetrics metrics(VoidType inputDataType) throws InvalidDataTypeException {
        return FunctionMetrics.c(1, 8, new IntegerType(this.value, this.value));
    }

    @EvalMethod()
    public Object run() {
        return getValue();
    }
}
