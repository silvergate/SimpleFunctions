package com.simplefunctions.functions.literals;

import com.simplefunctions.base.Function;
import com.simplefunctions.base.FunctionMetrics;
import com.simplefunctions.base.IFunction;
import com.simplefunctions.base.InvalidDataTypeException;
import com.simplefunctions.dataTypes.Binary;
import com.simplefunctions.dataTypes.BitType;
import com.simplefunctions.dataTypes.VoidType;
import com.simplefunctions.functions.base.EvalMethod;
import com.simplefunctions.functions.base.FunctionTypeBase;
import com.simplefunctions.functions.base.MetricsMethod;

/**
 * Buran.
 *
 * @author: ${USER} Date: 23.06.13 Time: 17:28
 */
public class FunBit extends FunctionTypeBase {

    public static IFunction c(Binary value) {
        return new Function(new FunBit(value), FunVoid.FUN);
    }

    public FunBit(Binary value) {
        this.value = value;
    }

    public Binary getValue() {
        return value;
    }

    private final Binary value;

    @MetricsMethod
    public FunctionMetrics metrics(VoidType inputDataType) throws InvalidDataTypeException {
        return FunctionMetrics.c(1, this.value.getNumberOfBytesCeil(),
                new BitType(this.value.getNumOfBits(), this.value.getNumOfBits(),
                        this.value.isByteAligned()));
    }

    @EvalMethod()
    public Object run() {
        return getValue();
    }
}
