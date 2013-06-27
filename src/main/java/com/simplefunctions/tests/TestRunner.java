package com.simplefunctions.tests;

import com.simplefunctions.base.ComplexityOverflowException;
import com.simplefunctions.base.FunctionMetrics;
import com.simplefunctions.base.IFunction;
import com.simplefunctions.base.InvalidDataTypeException;

/**
 * Buran.
 *
 * @author: ${USER} Date: 28.06.13 Time: 00:55
 */
public class TestRunner {
    public void runTest(TestCase testCase)
            throws InvalidDataTypeException, ComplexityOverflowException, Exception {
        final Object javaResult = testCase.asJava();
        final IFunction function = testCase.createFunction();

        System.out.println("================================================================");
        System.out.println("Test: " + testCase.getCaseName());
        System.out.println("================================================================");
        System.out.println("Java Result: ");
        System.out.println(javaResult);
        System.out.println("Function Result: ");
        System.out.println(function.eval());
        final FunctionMetrics metrics = function.calcMetrics();
        System.out
                .println("Function worst case CPU complexity: " + metrics.getComplexity().getCpu());
        System.out.println(
                "Function worst case memory complexity: " + metrics.getComplexity().getMemory());
        System.out.println("Function output type: " + metrics.getOutputType());
        System.out.println("================================================================");
    }
}
