package com.simplefunctions.functions.string;

import com.simplefunctions.base.Function;
import com.simplefunctions.base.FunctionMetrics;
import com.simplefunctions.base.IFunction;
import com.simplefunctions.base.InvalidDataTypeException;
import com.simplefunctions.dataTypes.StringType;
import com.simplefunctions.functions.base.EvalMethod;
import com.simplefunctions.functions.base.FunctionTypeBase;
import com.simplefunctions.functions.base.MetricsMethod;
import com.simplefunctions.functions.literals.FunList;
import com.simplefunctions.functions.literals.FunString;

import java.text.MessageFormat;

/**
 * Buran.
 *
 * @author: ${USER} Date: 23.06.13 Time: 14:28
 */
public class FunStrConcat extends FunctionTypeBase {

    public static final FunStrConcat SINGLETON = new FunStrConcat();

    public static IFunction c(IFunction str1, IFunction str2) {
        return new Function(SINGLETON, FunList.c(str1, str2));
    }

    private FunStrConcat() {
    }

    @MetricsMethod
    public FunctionMetrics metrics(StringType s1, StringType s2) throws InvalidDataTypeException {
        return FunctionMetrics.c(s1.getMaxLen() + s2.getMaxLen(),
                FunString.getStringMemory(s1.getMaxLen()) +
                        FunString.getStringMemory(s2.getMaxLen()),
                new StringType(s1.getMinLen() + s2.getMinLen(), s1.getMaxLen() + s2.getMaxLen()));
    }

    @EvalMethod()
    public Object run(String str1, String str2) {
        return MessageFormat.format("{0}{1}", str1, str2);
    }
}
