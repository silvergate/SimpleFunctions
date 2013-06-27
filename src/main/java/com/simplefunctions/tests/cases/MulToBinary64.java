package com.simplefunctions.tests.cases;

import com.simplefunctions.base.IFunction;
import com.simplefunctions.dataTypes.Binary;
import com.simplefunctions.functions.integer.FunIntArithmOp;
import com.simplefunctions.functions.integer.FunIntToBinary;
import com.simplefunctions.functions.literals.FunInt;
import com.simplefunctions.tests.TestCase;

import java.nio.ByteBuffer;

/**
 * Buran.
 *
 * @author: ${USER} Date: 28.06.13 Time: 00:51
 */
public class MulToBinary64 extends TestCase {

    private static final long VAL1 = 334;
    private static final long VAL2 = 3223;

    @Override
    public String getCaseName() {
        return "Multiplicate values and then convert to binary";
    }

    @Override
    public IFunction createFunction() {
        return FunIntToBinary.c64(FunIntArithmOp.mul(FunInt.c(VAL1), FunInt.c(VAL2)));
    }

    @Override
    public Object asJava() {
        long val = VAL1 * VAL2;
        ByteBuffer bb = ByteBuffer.allocate(8);
        bb.putLong(val);
        return new Binary(bb.array());
    }
}
