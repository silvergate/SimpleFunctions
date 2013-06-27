package com.simplefunctions.tests.cases;

import com.simplefunctions.base.IFunction;
import com.simplefunctions.dataTypes.Binary;
import com.simplefunctions.functions.bit.FunBitConcat;
import com.simplefunctions.functions.literals.FunString;
import com.simplefunctions.functions.string.FunStringToBinary;
import com.simplefunctions.tests.TestCase;

import java.io.UnsupportedEncodingException;

/**
 * Buran.
 *
 * @author: ${USER} Date: 28.06.13 Time: 00:51
 */
public class ConvUtf8And32Concat extends TestCase {

    private static final String STR1 = "Hallo ich bin";
    private static final String STR2 = "eine Welt";

    @Override
    public String getCaseName() {
        return "Convert to UTF-8 and UTF-32 big endian and combine";
    }

    @Override
    public IFunction createFunction() {
        IFunction toUtf8 = FunStringToBinary.cUtf8(FunString.c(STR1));
        IFunction toUtf32 = FunStringToBinary.cUtf32Be(FunString.c(STR2));
        return FunBitConcat.c(toUtf8, toUtf32);
    }

    @Override
    public Object asJava() throws UnsupportedEncodingException {
        final byte[] toUtf8 = STR1.getBytes("UTF-8");
        final byte[] toUtf32 = STR2.getBytes("UTF-32BE");
        final byte[] result = new byte[toUtf8.length + toUtf32.length];
        System.arraycopy(toUtf8, 0, result, 0, toUtf8.length);
        System.arraycopy(toUtf32, 0, result, toUtf8.length, toUtf32.length);
        return new Binary(result);
    }
}
