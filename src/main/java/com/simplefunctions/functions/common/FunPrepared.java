package com.simplefunctions.functions.common;

import com.simplefunctions.base.*;
import com.simplefunctions.dataTypes.ParamListType;
import com.simplefunctions.functions.base.DataUtils;
import com.simplefunctions.functions.base.TypeUtils;
import com.simplefunctions.functions.literals.FunList;

import java.util.ArrayList;
import java.util.List;

/**
 * Buran.
 *
 * @author: ${USER} Date: 27.06.13 Time: 22:57
 */
public class FunPrepared implements IFunctionType {

    private static class Arg {
        private final IFunction function;
        private final IFunctionType functionType;
        private final int outerParamNum;

        private Arg(IFunction function, IFunctionType functionType, int outerParamNum) {
            this.function = function;
            this.functionType = functionType;
            this.outerParamNum = outerParamNum;
        }

        private IFunction getFunction() {
            return function;
        }

        private IFunctionType getFunctionType() {
            return functionType;
        }

        private int getOuterParamNum() {
            return outerParamNum;
        }
    }

    public static class Builder {
        private final IFunctionType innerFunction;
        private final List<Arg> functions = new ArrayList<>();

        public Builder(IFunctionType innerFunction) {
            this.innerFunction = innerFunction;
        }

        public Builder fun(IFunction fun) {
            this.functions.add(new Arg(fun, null, -1));
            return this;
        }

        public Builder fromOuter() {
            if (this.functions.contains(null)) {
                throw new IllegalStateException("Inner function position already set.");
            }
            this.functions.add(null);
            return this;
        }

        public Builder nested(IFunctionType functionType, int paramNum) {
            this.functions.add(new Arg(null, functionType, paramNum));
            return this;
        }

        public FunPrepared get() {
            return new FunPrepared(this.innerFunction, this.functions.toArray(new Arg[]{}));
        }
    }

    public static Builder build(IFunctionType innerFunction) {
        return new Builder(innerFunction);
    }

    private final IFunctionType innerFunction;
    private final Arg[] functions;

    private FunPrepared(IFunctionType innerFunction, Arg[] functions) {
        this.innerFunction = innerFunction;
        this.functions = functions;
    }

    @Override
    public FunctionMetrics calcMetrics(IDataType inputDataType)
            throws InvalidDataTypeException, ComplexityOverflowException {
        final List<IDataType> resultFunctions = new ArrayList<>();
        for (final Arg function : this.functions) {
            if (function == null) {
                resultFunctions.add(inputDataType);
            } else {
                if (function.getFunction() != null) {
                    final FunctionMetrics metrics = function.getFunction().calcMetrics();
                    resultFunctions.add(metrics.getOutputType());
                } else {
                    final int index = function.getOuterParamNum();
                    final IDataType outerParamType = TypeUtils.getTypeAtIndex(inputDataType, index);
                    final IFunctionType type = function.getFunctionType();
                    final FunctionMetrics metrics = type.calcMetrics(outerParamType);
                    resultFunctions.add(metrics.getOutputType());
                }
            }
        }

        final ParamListType plType = ParamListType.c(resultFunctions.toArray(new IDataType[]{}));
        return this.innerFunction.calcMetrics(plType);
    }

    @Override
    public Object eval(final IEvaluable input) {
        final List<IEvaluable> resultFunctions = new ArrayList<>();
        for (final Arg function : this.functions) {
            if (function == null) {
                resultFunctions.add(input);
            } else {
                if (function.getFunction() != null) {
                    resultFunctions.add(function.getFunction());
                } else {
                    final IEvaluable evaluable = new IEvaluable() {
                        @Override
                        public Object eval() {

                            final Object data =
                                    DataUtils.getDataAtIndex(input, function.getOuterParamNum());
                            return function.getFunctionType().eval(new IEvaluable() {
                                @Override
                                public Object eval() {
                                    return data;
                                }
                            });
                        }
                    };
                    resultFunctions.add(evaluable);
                }
            }
        }
        return this.innerFunction
                .eval(FunList.cNoMeta(resultFunctions.toArray(new IEvaluable[]{})));
    }
}