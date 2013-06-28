package com.simplefunctions.functions.test;

import com.simplefunctions.base.*;
import com.simplefunctions.dataTypes.AltType;
import com.simplefunctions.dataTypes.BooleanType;
import com.simplefunctions.functions.base.DataUtils;
import com.simplefunctions.functions.base.MetricsMethod;
import com.simplefunctions.functions.base.ReflectionUtils;
import com.simplefunctions.functions.literals.FunList;

/**
 * Buran.
 *
 * @author: ${USER} Date: 23.06.13 Time: 14:28
 */
public class FunIf implements IFunctionType {

    public static final FunIf SINGLETON = new FunIf();

    public static IFunction c(IFunction test, IFunction trueFun, IFunction falseFun) {
        return new Function(SINGLETON, FunList.c(test, trueFun, falseFun));
    }

    private FunIf() {
    }

    @MetricsMethod
    public FunctionMetrics metrics(BooleanType test, IDataType trueFun, IDataType falseFun)
            throws InvalidDataTypeException {
        final IDataType type;
        switch (test.getRange()) {
            case falseOnly:
                type = falseFun;
                break;
            case trueOnly:
                type = trueFun;
                break;
            case trueOrFalse:
                type = new AltType(trueFun, falseFun);
                break;
            default:
                throw new IllegalArgumentException("Unknown boolean range");
        }

        return new FunctionMetrics(new Complexity(0, 0), type);
    }

    @Override
    public FunctionMetrics calcMetrics(IDataType inputDataType)
            throws InvalidDataTypeException, ComplexityOverflowException {
        return ReflectionUtils.calcMetrics(this, inputDataType);
    }

    @Override
    public Object eval(IEvaluable input) {
        final Boolean testResult = (Boolean) DataUtils.getDataAtIndex(input, 0);
        if (testResult) {
            return DataUtils.getDataAtIndex(input, 1);
        } else {
            return DataUtils.getDataAtIndex(input, 2);
        }
    }
}
