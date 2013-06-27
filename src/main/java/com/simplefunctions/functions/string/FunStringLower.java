package com.simplefunctions.functions.string;

import com.simplefunctions.base.*;
import com.simplefunctions.dataTypes.StringType;
import com.simplefunctions.functions.base.*;
import com.simplefunctions.functions.literals.FunList;
import com.simplefunctions.functions.literals.FunString;

import java.util.Locale;

/**
 * Buran.
 *
 * @author: ${USER} Date: 23.06.13 Time: 14:28
 */
public class FunStringLower extends FunctionTypeBase {

    public static final Locale DEFAULT_LOCALE = Locale.US;

    public static FunStringLower SINGLETON = new FunStringLower();

    private FunStringLower() {
    }

    public static IFunction c(IFunction string, /* IETF BCP 47 */ IFunction locale) {
        return new Function(SINGLETON, FunList.c(string, locale));
    }

    public static IFunction c(IFunction input) {
        return new Function(SINGLETON, input);
    }

    @MetricsMethod
    public FunctionMetrics metrics(StringType inputDataType,
            @TypeParam(false) StringType localeType) throws InvalidDataTypeException {
        return new FunctionMetrics(new Complexity(inputDataType.getMaxLen(),
                FunString.getStringMemory(inputDataType.getMaxLen())), inputDataType);
    }

    @EvalMethod
    public Object run(String input, @Param(false) String localeStr) {
        final Locale locale;
        if (localeStr == null) {
            locale = DEFAULT_LOCALE;
        } else {
            locale = Locale.forLanguageTag(localeStr);
        }
        return input.toLowerCase(locale);
    }
}
