package com.simplefunctions.functions.literals;

import com.simplefunctions.base.Function;
import com.simplefunctions.base.FunctionMetrics;
import com.simplefunctions.base.IFunction;
import com.simplefunctions.base.InvalidDataTypeException;
import com.simplefunctions.dataTypes.BooleanType;
import com.simplefunctions.dataTypes.VoidType;
import com.simplefunctions.functions.base.EvalMethod;
import com.simplefunctions.functions.base.FunctionTypeBase;
import com.simplefunctions.functions.base.MetricsMethod;

/**
 * Buran.
 *
 * @author: ${USER} Date: 23.06.13 Time: 17:28
 */
public class FunBool extends FunctionTypeBase {

    public static IFunction c(boolean value) {
        return new Function(new FunBool(value), FunVoid.FUN);
    }

    public FunBool(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    private final boolean value;

    @MetricsMethod
    public FunctionMetrics metrics(VoidType inputDataType) throws InvalidDataTypeException {
        final BooleanType.BoolRange range;
        if (this.value) {
            range = BooleanType.BoolRange.trueOnly;
        } else {
            range = BooleanType.BoolRange.falseOnly;
        }
        return FunctionMetrics.c(1, 1, new BooleanType(range));
    }

    @EvalMethod()
    public Object run() {
        return getValue();
    }
}
