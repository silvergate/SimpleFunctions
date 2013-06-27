package com.simplefunctions.functions.common;

import com.simplefunctions.base.*;

/**
 * Buran.
 *
 * @author: ${USER} Date: 27.06.13 Time: 22:57
 */
public class FunChain implements IFunctionType {

    private final IFunctionType[] functionTypes;

    private FunChain(IFunctionType[] functionTypes) {
        if (functionTypes.length == 0) {
            throw new IllegalArgumentException("functionTypes.length==0");
        }
        this.functionTypes = functionTypes;
    }

    public static FunChain c(IFunctionType... functionTypes) {
        return new FunChain(functionTypes);
    }

    @Override
    public FunctionMetrics calcMetrics(final IDataType inputDataType)
            throws InvalidDataTypeException, ComplexityOverflowException {
        Complexity curComplexity = new Complexity(0, 0);
        IDataType cutDataType = inputDataType;
        FunctionMetrics curMetrics = null;

        for (final IFunctionType funType : this.functionTypes) {
            curMetrics = funType.calcMetrics(cutDataType);
            curComplexity = curComplexity.combine(curMetrics.getComplexity());
        }

        return new FunctionMetrics(curComplexity, curMetrics.getOutputType());
    }

    @Override
    public Object eval(final IEvaluable input) {
        IEvaluable currentValue = input;
        for (final IFunctionType funType : this.functionTypes) {
            final Object value = funType.eval(currentValue);
            currentValue = new IEvaluable() {
                @Override
                public Object eval() {
                    return value;
                }
            };
        }

        return currentValue.eval();
    }
}