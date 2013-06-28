package com.simplefunctions.functions.literals;

import com.simplefunctions.base.*;
import com.simplefunctions.dataTypes.CollectionType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * Buran.
 *
 * @author: ${USER} Date: 23.06.13 Time: 23:00
 */
public class FunCollection implements IFunctionType {

    private final IFunction[] functions;
    private final boolean ordered;
    private final boolean unique;
    private final IDataType elementType;

    public static IFunction c(boolean ordered, boolean unique, IDataType elementType,
            IFunction... functions) {
        return new Function(new FunCollection(ordered, unique, elementType, functions),
                FunVoid.FUN);
    }

    private static IDataType inferDataType(IFunction... functions)
            throws InvalidDataTypeException, ComplexityOverflowException {
        IDataType currentType = null;
        for (final IFunction function : functions) {
            final FunctionMetrics metrics = function.calcMetrics();
            final IDataType type = metrics.getOutputType();
            if (currentType == null) {
                currentType = type;
            } else {
                currentType = currentType.combine(type);
            }
        }
        return currentType;
    }

    public static IFunction list(IFunction... functions)
            throws InvalidDataTypeException, ComplexityOverflowException {
        return new Function(new FunCollection(true, false, inferDataType(functions), functions),
                FunVoid.FUN);
    }

    public static IFunction unique(IFunction... functions)
            throws InvalidDataTypeException, ComplexityOverflowException {
        return new Function(new FunCollection(false, true, inferDataType(functions), functions),
                FunVoid.FUN);
    }

    private FunCollection(boolean ordered, boolean unique, IDataType elementType,
            IFunction... functions) {
        if (ordered && unique) {
            throw new IllegalArgumentException("(ordered && unique)");
        }
        this.ordered = ordered;
        this.unique = unique;
        this.elementType = elementType;
        this.functions = functions;
    }

    @Override
    public FunctionMetrics calcMetrics(IDataType inputDataType)
            throws InvalidDataTypeException, ComplexityOverflowException {
        Complexity curComp = new Complexity(0, 0);
        for (final IFunction element : this.functions) {
            final FunctionMetrics metrics = element.calcMetrics();
            curComp.combine(metrics.getComplexity());
            // TODO: Überprüfen ob element type kompatible ist.
            //metrics.getOutputType()
        }

        return new FunctionMetrics(curComp,
                new CollectionType(this.ordered, this.unique, this.functions.length,
                        this.functions.length, this.elementType));
    }

    @Override
    public Object eval(IEvaluable input) {
        final Collection<Object> results;
        if (this.ordered) {
            results = new ArrayList<>();
        } else {
            if (this.unique) {
                results = new HashSet<>();
            } else {
                results = new ArrayList<>();
            }
        }

        for (final IFunction element : this.functions) {
            results.add(element.eval());
        }

        return results;
    }

}
