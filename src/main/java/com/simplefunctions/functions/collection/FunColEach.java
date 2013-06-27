package com.simplefunctions.functions.collection;

import com.simplefunctions.base.*;
import com.simplefunctions.dataTypes.CollectionType;
import com.simplefunctions.functions.base.EvalMethod;
import com.simplefunctions.functions.base.FunctionTypeBase;
import com.simplefunctions.functions.base.MetricsMethod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * Buran.
 *
 * @author: ${USER} Date: 27.06.13 Time: 20:36
 */
public class FunColEach extends FunctionTypeBase {

    private final IFunctionType functionType;
    private final boolean uniqueResults;

    private FunColEach(IFunctionType functionType, boolean uniqueResults) {
        this.functionType = functionType;
        this.uniqueResults = uniqueResults;
    }

    public static IFunction c(IFunctionType functionType, IFunction collection) {
        return new Function(new FunColEach(functionType, false), collection);
    }

    public static IFunction unique(IFunctionType functionType, IFunction collection) {
        return new Function(new FunColEach(functionType, true), collection);
    }

    @MetricsMethod
    public FunctionMetrics metrics(CollectionType col)
            throws InvalidDataTypeException, ComplexityOverflowException {
        final IDataType element = col.getElementType();
        final FunctionMetrics singleMetrics = this.functionType.calcMetrics(element);
        final Complexity singleComplexity = singleMetrics.getComplexity();

        final Complexity combinedComplexity = singleComplexity.multiply(col.getMaxElements());
        final CollectionType finalCollectionType =
                new CollectionType(!this.uniqueResults, this.uniqueResults, col.getMinElements(),
                        col.getMaxElements(), singleMetrics.getOutputType());
        return new FunctionMetrics(combinedComplexity, finalCollectionType);
    }

    @EvalMethod
    public Object run(Collection<?> inCol) {
        Collection<Object> result;
        if (this.uniqueResults) {
            result = new HashSet<>();
        } else {
            result = new ArrayList<>();
        }
        for (final Object element : inCol) {
            final Object elemResult = this.functionType.eval(new IEvaluable() {
                @Override
                public Object eval() {
                    return element;
                }

            });
            result.add(elemResult);
        }
        return result;
    }

}