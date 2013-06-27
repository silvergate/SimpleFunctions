package com.simplefunctions.functions.string;

import com.simplefunctions.base.Function;
import com.simplefunctions.base.FunctionMetrics;
import com.simplefunctions.base.IFunction;
import com.simplefunctions.base.InvalidDataTypeException;
import com.simplefunctions.dataTypes.StringType;
import com.simplefunctions.functions.base.EvalMethod;
import com.simplefunctions.functions.base.FunctionTypeBase;
import com.simplefunctions.functions.base.MetricsMethod;
import com.simplefunctions.functions.literals.FunString;

/**
 * Buran.
 *
 * @author: ${USER} Date: 23.06.13 Time: 14:28
 */
public class FunStringTrim extends FunctionTypeBase {

    public static final FunStringTrim SINGLETON = new FunStringTrim();

    public static IFunction c(IFunction input) {
        return new Function(SINGLETON, input);
    }

    private FunStringTrim() {
    }

    @MetricsMethod
    public FunctionMetrics metrics(StringType inputDataType) throws InvalidDataTypeException {
        return FunctionMetrics
                .c(inputDataType.getMaxLen(), FunString.getStringMemory(inputDataType.getMaxLen()),
                        new StringType(0, inputDataType.getMaxLen()));
    }

    @EvalMethod()
    public Object run(String input) {
        return input.trim();
    }
}
