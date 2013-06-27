package com.simplefunctions.base;

/**
 * Buran.
 *
 * @author: ${USER} Date: 23.06.13 Time: 14:12
 */
public interface IFunctionType {
    FunctionMetrics calcMetrics(IDataType inputDataType)
            throws InvalidDataTypeException, ComplexityOverflowException;

    Object eval(IEvaluable input);
}
