package com.simplefunctions.functions.integer;

import com.simplefunctions.base.Function;
import com.simplefunctions.base.FunctionMetrics;
import com.simplefunctions.base.IFunction;
import com.simplefunctions.base.InvalidDataTypeException;
import com.simplefunctions.dataTypes.Binary;
import com.simplefunctions.dataTypes.BitType;
import com.simplefunctions.dataTypes.IntegerType;
import com.simplefunctions.functions.base.EvalMethod;
import com.simplefunctions.functions.base.FunctionTypeBase;
import com.simplefunctions.functions.base.MetricsMethod;
import com.simplefunctions.functions.common.BitWidth;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Buran.
 *
 * @author: ${USER} Date: 24.06.13 Time: 02:17
 */
public class FunIntToBinary extends FunctionTypeBase {

    private final BitWidth width;

    private FunIntToBinary(BitWidth width) {
        this.width = width;
    }

    public static final FunIntToBinary BIT8 = new FunIntToBinary(BitWidth.bit8);
    public static final FunIntToBinary BIT16 = new FunIntToBinary(BitWidth.bit16);
    public static final FunIntToBinary BIT32 = new FunIntToBinary(BitWidth.bit32);
    public static final FunIntToBinary BIT64 = new FunIntToBinary(BitWidth.bit64);

    public static IFunction c8(IFunction int1) {
        return new Function(BIT8, int1);
    }


    public static IFunction c16(IFunction int1) {
        return new Function(BIT16, int1);
    }


    public static IFunction c32(IFunction int1) {
        return new Function(BIT32, int1);
    }


    public static IFunction c64(IFunction int1) {
        return new Function(BIT64, int1);
    }

    @MetricsMethod
    public FunctionMetrics metrics(IntegerType int1) throws InvalidDataTypeException {
        short numOfBits = this.width.getNumOfBits();
        int numOfBytes = numOfBits / 8;
        return FunctionMetrics.c(numOfBytes, numOfBytes, new BitType(numOfBits, numOfBits, true));
    }

    @EvalMethod
    public Object run(Long v1) {
        final int numOfBytes = this.width.getNumOfBits() / 8;
        final ByteBuffer buffer = ByteBuffer.allocate(numOfBytes);
        /* For documentation only: Big-endian is default. */
        buffer.order(ByteOrder.BIG_ENDIAN);
        switch (this.width) {
            case bit8:
                buffer.put(v1.byteValue());
                break;
            case bit16:
                buffer.putShort(v1.shortValue());
                break;
            case bit32:
                buffer.putInt(v1.intValue());
                break;
            case bit64:
                buffer.putLong(v1);
                break;
        }
        return new Binary(buffer.array());
    }
}
