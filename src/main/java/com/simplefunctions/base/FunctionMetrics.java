package com.simplefunctions.base;

/**
 * Buran.
 *
 * @author: ${USER} Date: 23.06.13 Time: 14:25
 */
public class FunctionMetrics {
    private final Complexity complexity;
    private final IDataType outputType;

    public static FunctionMetrics c(long cpu, long memory, IDataType outputType) {
        return new FunctionMetrics(new Complexity(cpu, memory), outputType);
    }

    public FunctionMetrics(Complexity complexity, IDataType outputType) {
        this.complexity = complexity;
        this.outputType = outputType;
    }

    public Complexity getComplexity() {
        return complexity;
    }

    public IDataType getOutputType() {
        return outputType;
    }
}
