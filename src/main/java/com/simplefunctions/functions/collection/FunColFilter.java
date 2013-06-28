package com.simplefunctions.functions.collection;

import com.simplefunctions.base.*;
import com.simplefunctions.dataTypes.BooleanType;
import com.simplefunctions.dataTypes.CollectionType;
import com.simplefunctions.dataTypes.FunctionTypeType;
import com.simplefunctions.functions.base.EvalMethod;
import com.simplefunctions.functions.base.FunctionTypeBase;
import com.simplefunctions.functions.base.MetricsMethod;
import com.simplefunctions.functions.literals.FunList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * Buran.
 *
 * @author: ${USER} Date: 27.06.13 Time: 20:36
 */
public class FunColFilter extends FunctionTypeBase {

    private final boolean uniqueResults;

    private FunColFilter(boolean uniqueResults) {
        this.uniqueResults = uniqueResults;
    }

    public static IFunction c(IFunction collection, IFunction filter) {
        return new Function(new FunColFilter(false), FunList.c(filter, collection));
    }

    public static IFunction unique(IFunction collection, IFunction filter) {
        return new Function(new FunColFilter(true), FunList.c(filter, collection));
    }

    @MetricsMethod
    public FunctionMetrics metrics(FunctionTypeType ftt, CollectionType col)
            throws InvalidDataTypeException, ComplexityOverflowException {
        final IDataType element = col.getElementType();

        /* Number of elements */
        final FunctionMetrics singleMetrics = ftt.getValue().calcMetrics(element);
        final IDataType booleanOutput = singleMetrics.getOutputType();
        if (!(booleanOutput instanceof BooleanType)) {
            throw new InvalidDataTypeException();
        }
        final BooleanType btOutput = (BooleanType) booleanOutput;
        final int minElements;
        final int maxElements;
        switch (btOutput.getRange()) {
            case trueOrFalse:
                minElements = 0;
                maxElements = col.getMaxElements();
                break;
            case trueOnly:
                if ((!col.isUnique()) && (this.uniqueResults)) {
                    minElements = Math.min(1, col.getMinElements());
                } else {
                    minElements = col.getMinElements();
                }
                maxElements = col.getMaxElements();
                break;
            case falseOnly:
                minElements = 0;
                maxElements = 0;
                break;
            default:
                throw new IllegalArgumentException("Unknown boolean range");
        }

        final Complexity singleComplexity = singleMetrics.getComplexity();
        final Complexity combinedComplexity = singleComplexity.multiply(maxElements);
        final CollectionType finalCollectionType =
                new CollectionType(!this.uniqueResults, this.uniqueResults, minElements,
                        maxElements, element);
        return new FunctionMetrics(combinedComplexity, finalCollectionType);
    }

    @EvalMethod
    public Object run(IFunctionType functionType, Collection<?> inCol) {
        Collection<Object> result;
        if (this.uniqueResults) {
            result = new HashSet<>();
        } else {
            result = new ArrayList<>();
        }
        for (final Object element : inCol) {
            final Object elemResult = functionType.eval(new IEvaluable() {
                @Override
                public Object eval() {
                    return element;
                }

            });
            final Boolean elemResultBool = (Boolean) elemResult;
            if (elemResultBool) {
                result.add(element);
            }
        }
        return result;
    }

}