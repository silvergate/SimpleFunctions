package com.simplefunctions.functions.literals;

import com.simplefunctions.base.Function;
import com.simplefunctions.base.FunctionMetrics;
import com.simplefunctions.base.IFunction;
import com.simplefunctions.base.InvalidDataTypeException;
import com.simplefunctions.dataTypes.StringType;
import com.simplefunctions.dataTypes.VoidType;
import com.simplefunctions.functions.base.EvalMethod;
import com.simplefunctions.functions.base.FunctionTypeBase;
import com.simplefunctions.functions.base.MetricsMethod;

/**
 * Buran.
 *
 * @author: ${USER} Date: 23.06.13 Time: 17:28
 */
public class FunString extends FunctionTypeBase {

    public static IFunction c(String value) {
        return new Function(new FunString(value), FunVoid.FUN);
    }

    public FunString(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private final String value;

    public static long getStringMemory(int stringLength) {
        return stringLength * 4;
    }

    @MetricsMethod
    public FunctionMetrics metrics(VoidType inputDataType) throws InvalidDataTypeException {
        return FunctionMetrics.c(1, getStringMemory(this.value.length()),
                new StringType(this.value.length(), this.value.length()));
    }

    @EvalMethod()
    public Object run() {
        return getValue();
    }
}
