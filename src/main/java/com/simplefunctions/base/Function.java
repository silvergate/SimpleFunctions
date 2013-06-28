package com.simplefunctions.base;

/**
 * Buran.
 *
 * @author: ${USER} Date: 23.06.13 Time: 17:46
 */
public class Function implements IFunction {
    private final IFunctionType functionType;
    private final IFunction input;

    public Function(IFunctionType functionType, IFunction input) {
        this.functionType = functionType;
        this.input = input;
    }

    @Override
    public Object eval() {
        return this.functionType.eval(this.input);
    }

    @Override
    public FunctionMetrics calcMetrics()
            throws InvalidDataTypeException, ComplexityOverflowException {
        final FunctionMetrics inputMetrics = this.input.calcMetrics();
        final FunctionMetrics metrics = this.functionType.calcMetrics(inputMetrics.getOutputType());
        final Complexity combinedComplexity =
                inputMetrics.getComplexity().combine(metrics.getComplexity());
        return new FunctionMetrics(combinedComplexity, metrics.getOutputType());
    }

    @Override
    public IFunctionType getType() {
        return this.functionType;
    }

    @Override
    public String toString() {
        return "Function{" +
                "eval=" + eval() + '}';
    }
}
