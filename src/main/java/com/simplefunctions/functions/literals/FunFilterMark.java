package com.simplefunctions.functions.literals;


import com.simplefunctions.base.*;
import com.simplefunctions.dataTypes.FilterMark;
import com.simplefunctions.dataTypes.FilterMarkType;
import com.simplefunctions.dataTypes.VoidType;
import com.simplefunctions.functions.base.EvalMethod;
import com.simplefunctions.functions.base.FunctionTypeBase;
import com.simplefunctions.functions.base.MetricsMethod;

/**
 * Buran.
 *
 * @author: ${USER} Date: 23.06.13 Time: 17:51
 */
public class FunFilterMark extends FunctionTypeBase {

    public static final FunFilterMark SINGLETON = new FunFilterMark();

    private static final class FunFilterMarkClass implements IFunction {

        @Override
        public Object eval() {
            return SINGLETON.eval(null);
        }

        @Override
        public FunctionMetrics calcMetrics()
                throws InvalidDataTypeException, ComplexityOverflowException {
            return SINGLETON.calcMetrics(FilterMarkType.SINGLETON);
        }

        @Override
        public IFunctionType getType() {
            return SINGLETON;
        }
    }

    ;

    public static final IFunction FUN = new FunFilterMarkClass();

    @MetricsMethod
    public FunctionMetrics metrics(VoidType inputDataType) throws InvalidDataTypeException {
        return FunctionMetrics.c(1, 1, FilterMarkType.SINGLETON);
    }

    @EvalMethod()
    public Object run() {
        return FilterMark.SINGLETON;
    }
}
