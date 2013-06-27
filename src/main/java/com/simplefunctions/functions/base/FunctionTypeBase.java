package com.simplefunctions.functions.base;

import com.simplefunctions.base.*;

/**
 * Buran.
 *
 * @author: ${USER} Date: 23.06.13 Time: 14:29
 */
public abstract class FunctionTypeBase implements IFunctionType {

    @Override
    public final FunctionMetrics calcMetrics(IDataType inputDataType)
            throws InvalidDataTypeException, ComplexityOverflowException {
        return ReflectionUtils.calcMetrics(this, inputDataType);
    }

    @Override
    public final Object eval(IEvaluable input) {
        return ReflectionUtils.eval(this, input);
    }
}
