package com.simplefunctions.functions.integer;

import com.simplefunctions.base.Function;
import com.simplefunctions.base.FunctionMetrics;
import com.simplefunctions.base.IFunction;
import com.simplefunctions.base.InvalidDataTypeException;
import com.simplefunctions.dataTypes.IntegerType;
import com.simplefunctions.dataTypes.StringType;
import com.simplefunctions.functions.base.EvalMethod;
import com.simplefunctions.functions.base.FunctionTypeBase;
import com.simplefunctions.functions.base.MetricsMethod;
import com.simplefunctions.functions.literals.FunString;

/**
 * Buran.
 *
 * @author: ${USER} Date: 24.06.13 Time: 02:17
 */
public class FunIntToString extends FunctionTypeBase {

    public static final int DEFAULT_RADIX = 10;
    public static final int MIN_RADIX = 2;
    public static final int MAX_RADIX = 36;

    private final int radix;

    private FunIntToString(int radix) {
        if (radix < MIN_RADIX) {
            throw new IllegalArgumentException("radix<MIN_RADIX");
        }
        if (radix > MAX_RADIX) {
            throw new IllegalArgumentException("radix>MAX_RADIX");
        }
        this.radix = radix;
    }

    public static final FunIntToString SINGLETON = new FunIntToString(DEFAULT_RADIX);

    public static IFunction c(IFunction int1) {
        return new Function(SINGLETON, int1);
    }

    public static IFunction radix(int radix, IFunction int1) {
        return new Function(new FunIntToString(radix), int1);
    }

    @MetricsMethod
    public FunctionMetrics metrics(IntegerType int1) throws InvalidDataTypeException {
        final String minStr = Long.toString(int1.getMinValue(), this.radix);
        final String maxStr = Long.toString(int1.getMaxValue(), this.radix);
        final int maxLen = Math.max(minStr.length(), maxStr.length());
        return FunctionMetrics
                .c(maxLen, FunString.getStringMemory(maxLen), new StringType(1, maxLen));
    }

    @EvalMethod
    public Object run(Long v1) {
        return Long.toString(v1, this.radix);
    }
}
