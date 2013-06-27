package com.simplefunctions.functions.bit;

import com.simplefunctions.base.Function;
import com.simplefunctions.base.FunctionMetrics;
import com.simplefunctions.base.IFunction;
import com.simplefunctions.base.InvalidDataTypeException;
import com.simplefunctions.dataTypes.Binary;
import com.simplefunctions.dataTypes.BitType;
import com.simplefunctions.functions.base.EvalMethod;
import com.simplefunctions.functions.base.FunctionTypeBase;
import com.simplefunctions.functions.base.MetricsMethod;
import com.simplefunctions.functions.literals.FunList;

/**
 * Buran.
 *
 * @author: ${USER} Date: 27.06.13 Time: 19:21
 */
public class FunBitConcat extends FunctionTypeBase {


    private FunBitConcat() {
    }

    public static final FunBitConcat SINGLETON = new FunBitConcat();

    public static IFunction c(IFunction binary1, IFunction binary2) {
        return new Function(SINGLETON, FunList.c(binary1, binary2));
    }

    @MetricsMethod
    public FunctionMetrics metrics(BitType bt1, BitType bt2) throws InvalidDataTypeException {
        if (!bt1.isByteAligned()) {
            throw new InvalidDataTypeException();
        }
        if (!bt2.isByteAligned()) {
            throw new InvalidDataTypeException();
        }

        int numOfMaxBits = bt1.getMaxBits() + bt2.getMaxBits();
        int numOfMinBits = bt1.getMinBits() + bt2.getMinBits();
        int maxBytes = numOfMaxBits / 8;
        return FunctionMetrics.c(maxBytes, maxBytes, new BitType(numOfMinBits, numOfMaxBits, true));
    }

    @EvalMethod
    public Object run(Binary b1, Binary b2) {
        final byte[] result = new byte[(b1.getNumOfBits() + b2.getNumOfBits()) / 8];
        System.arraycopy(b1.getData(), 0, result, 0, b1.getNumOfBits() / 8);
        System.arraycopy(b2.getData(), 0, result, b1.getNumOfBits() / 8, b2.getNumOfBits() / 8);
        return new Binary(result);
    }
}
