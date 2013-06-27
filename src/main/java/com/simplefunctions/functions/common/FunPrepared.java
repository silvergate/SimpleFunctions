package com.simplefunctions.functions.common;

import com.simplefunctions.base.*;
import com.simplefunctions.dataTypes.ParamListType;
import com.simplefunctions.functions.literals.FunList;

import java.util.ArrayList;
import java.util.List;

/**
 * Buran.
 *
 * @author: ${USER} Date: 27.06.13 Time: 22:57
 */
public class FunPrepared implements IFunctionType {

    public static class Builder {
        private final IFunctionType innerFunction;
        private final List<IFunction> functions = new ArrayList<>();

        public Builder(IFunctionType innerFunction) {
            this.innerFunction = innerFunction;
        }

        public Builder fun(IFunction fun) {
            this.functions.add(fun);
            return this;
        }

        public Builder inner() {
            if (this.functions.contains(null)) {
                throw new IllegalStateException("Inner function position already set.");
            }
            this.functions.add(null);
            return this;
        }

        public FunPrepared get() {
            if (!this.functions.contains(null)) {
                throw new IllegalStateException("No inner function position is set.");
            }
            return new FunPrepared(this.innerFunction, this.functions.toArray(new IFunction[]{}));
        }
    }

    public static Builder build(IFunctionType innerFunction) {
        return new Builder(innerFunction);
    }

    private final IFunctionType innerFunction;
    private final IFunction[] functions;

    private FunPrepared(IFunctionType innerFunction, IFunction[] functions) {
        this.innerFunction = innerFunction;
        this.functions = functions;
    }

    @Override
    public FunctionMetrics calcMetrics(IDataType inputDataType)
            throws InvalidDataTypeException, ComplexityOverflowException {
        final List<IDataType> resultFunctions = new ArrayList<>();
        for (final IFunction function : this.functions) {
            if (function == null) {
                resultFunctions.add(inputDataType);
            } else {
                final FunctionMetrics metrics = function.calcMetrics();
                resultFunctions.add(metrics.getOutputType());
            }
        }

        final ParamListType plType = ParamListType.c(resultFunctions.toArray(new IDataType[]{}));
        return this.innerFunction.calcMetrics(plType);
    }

    @Override
    public Object eval(final IEvaluable input) {
        final List<IEvaluable> resultFunctions = new ArrayList<>();
        for (final IFunction function : this.functions) {
            if (function == null) {
                resultFunctions.add(input);
            } else {
                resultFunctions.add(function);
            }
        }
        return this.innerFunction
                .eval(FunList.cNoMeta(resultFunctions.toArray(new IEvaluable[]{})));
    }
}