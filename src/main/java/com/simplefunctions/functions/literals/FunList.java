package com.simplefunctions.functions.literals;

import com.simplefunctions.base.*;
import com.simplefunctions.dataTypes.ParamList;
import com.simplefunctions.dataTypes.ParamListType;
import com.simplefunctions.dataTypes.VoidType;
import com.simplefunctions.functions.base.TypeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Buran.
 *
 * @author: ${USER} Date: 23.06.13 Time: 23:00
 */
public class FunList implements IFunctionType {

    private final ParamList functions;
    private final IFunction[] functionsFull;

    public static IFunction c(IFunction... functions) {
        return new Function(new FunList(functions, functions), FunVoid.FUN);
    }

    public static IFunction cNoMeta(IEvaluable... functions) {
        return new Function(new FunList(null, functions), FunVoid.FUN);
    }

    private FunList(IFunction[] functions, IEvaluable[] evaluables) {
        this.functions = new ParamList(evaluables);
        this.functionsFull = functions;
    }

    @Override
    public FunctionMetrics calcMetrics(IDataType inputDataType)
            throws InvalidDataTypeException, ComplexityOverflowException {
        TypeUtils.assertType(inputDataType, VoidType.class);
        final List<Complexity> complexityList = new ArrayList<>();
        final IDataType[] dataTypes = new IDataType[this.functions.getFunctions().length];
        int index = 0;
        for (final IFunction function : this.functionsFull) {
            final FunctionMetrics metrics = function.calcMetrics();
            complexityList.add(metrics.getComplexity());
            dataTypes[index] = metrics.getOutputType();
            index++;
        }
        return new FunctionMetrics(Complexity.combine(complexityList), ParamListType.c(dataTypes));
    }

    @Override
    public Object eval(IEvaluable input) {
        return this.functions;
    }

}
