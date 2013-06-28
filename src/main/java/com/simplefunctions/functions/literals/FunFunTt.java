package com.simplefunctions.functions.literals;

import com.simplefunctions.base.*;
import com.simplefunctions.dataTypes.FunctionTypeType;
import com.simplefunctions.dataTypes.VoidType;
import com.simplefunctions.functions.base.EvalMethod;
import com.simplefunctions.functions.base.FunctionTypeBase;
import com.simplefunctions.functions.base.MetricsMethod;

/**
 * Buran.
 *
 * @author: ${USER} Date: 23.06.13 Time: 17:28
 */
public class FunFunTt extends FunctionTypeBase {

    public static IFunction c(IFunctionType value) {
        return new Function(new FunFunTt(value), FunVoid.FUN);
    }

    public FunFunTt(IFunctionType value) {
        this.value = value;
    }

    public IFunctionType getValue() {
        return value;
    }

    private final IFunctionType value;

    @MetricsMethod
    public FunctionMetrics metrics(VoidType inputDataType) throws InvalidDataTypeException {
        return FunctionMetrics.c(1, 8, new FunctionTypeType(getValue()));
    }

    @EvalMethod()
    public Object run() {
        return getValue();
    }
}
