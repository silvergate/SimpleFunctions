package com.simplefunctions.functions.common;

import com.simplefunctions.base.*;

/**
 * Buran.
 *
 * @author: ${USER} Date: 27.06.13 Time: 22:57
 */
public class FunNil implements IFunctionType {

    public static final FunNil SINGLETON = new FunNil();

    private FunNil() {
    }

    @Override
    public FunctionMetrics calcMetrics(final IDataType inputDataType)
            throws InvalidDataTypeException, ComplexityOverflowException {
        return new FunctionMetrics(new Complexity(1, 1), inputDataType);
    }

    @Override
    public Object eval(final IEvaluable input) {
        return input.eval();
    }
}