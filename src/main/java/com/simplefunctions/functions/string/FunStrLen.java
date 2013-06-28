package com.simplefunctions.functions.string;

import com.simplefunctions.base.Function;
import com.simplefunctions.base.FunctionMetrics;
import com.simplefunctions.base.IFunction;
import com.simplefunctions.base.InvalidDataTypeException;
import com.simplefunctions.dataTypes.IntegerType;
import com.simplefunctions.dataTypes.StringType;
import com.simplefunctions.functions.base.EvalMethod;
import com.simplefunctions.functions.base.FunctionTypeBase;
import com.simplefunctions.functions.base.MetricsMethod;

/**
 * Buran.
 *
 * @author: ${USER} Date: 23.06.13 Time: 14:28
 */
public class FunStrLen extends FunctionTypeBase {

    public static final FunStrLen SINGLETON = new FunStrLen();

    public static IFunction c(IFunction input) {
        return new Function(SINGLETON, input);
    }

    private FunStrLen() {
    }

    @MetricsMethod
    public FunctionMetrics metrics(StringType inputDataType) throws InvalidDataTypeException {
        return FunctionMetrics
                .c(1, 1, new IntegerType(inputDataType.getMinLen(), inputDataType.getMaxLen()));
    }

    @EvalMethod()
    public Object run(String input) {
        return (long) (input.length());
    }
}
