package com.simplefunctions.base;

/**
 * Buran.
 *
 * @author: ${USER} Date: 23.06.13 Time: 17:44
 */
public interface IFunction extends IEvaluable {
    FunctionMetrics calcMetrics() throws InvalidDataTypeException, ComplexityOverflowException;

    IFunctionType getType();
}
