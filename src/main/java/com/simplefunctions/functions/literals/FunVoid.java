package com.simplefunctions.functions.literals;


import com.simplefunctions.base.*;
import com.simplefunctions.dataTypes.VoidType;
import com.simplefunctions.functions.base.EvalMethod;
import com.simplefunctions.functions.base.FunctionTypeBase;
import com.simplefunctions.functions.base.MetricsMethod;

/**
 * Buran.
 *
 * @author: ${USER} Date: 23.06.13 Time: 17:51
 */
public class FunVoid extends FunctionTypeBase {

    public static final FunVoid SINGLETON = new FunVoid();

    private static final class FunVoidClass implements IFunction {

        @Override
        public Object eval() {
            return SINGLETON.eval(null);
        }

        @Override
        public FunctionMetrics calcMetrics()
                throws InvalidDataTypeException, ComplexityOverflowException {
            return SINGLETON.calcMetrics(VoidType.SINGLETON);
        }

        @Override
        public IFunctionType getType() {
            return SINGLETON;
        }
    }

    ;

    public static final IFunction FUN = new FunVoidClass();

    @MetricsMethod
    public FunctionMetrics metrics(VoidType inputDataType) throws InvalidDataTypeException {
        return FunctionMetrics.c(1, 1, VoidType.SINGLETON);
    }

    @EvalMethod()
    public Object run() {
        return null;
    }
}
