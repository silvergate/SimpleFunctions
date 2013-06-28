package com.simplefunctions.functions.string;

import com.simplefunctions.base.Function;
import com.simplefunctions.base.FunctionMetrics;
import com.simplefunctions.base.IFunction;
import com.simplefunctions.base.InvalidDataTypeException;
import com.simplefunctions.dataTypes.Binary;
import com.simplefunctions.dataTypes.BitType;
import com.simplefunctions.dataTypes.StringType;
import com.simplefunctions.functions.base.EvalMethod;
import com.simplefunctions.functions.base.FunctionTypeBase;
import com.simplefunctions.functions.base.MetricsMethod;

import java.io.UnsupportedEncodingException;

/**
 * Buran.
 *
 * @author: ${USER} Date: 24.06.13 Time: 02:17
 */
public class FunStrToBinary extends FunctionTypeBase {

    private final UtfType utfType;

    private FunStrToBinary(UtfType utfType) {
        this.utfType = utfType;
    }

    public static final FunStrToBinary UTF8 = new FunStrToBinary(UtfType.utf8);
    public static final FunStrToBinary UTF16BE = new FunStrToBinary(UtfType.utf16be);
    public static final FunStrToBinary UTF16LE = new FunStrToBinary(UtfType.utf16le);
    public static final FunStrToBinary UTF32BE = new FunStrToBinary(UtfType.utf32be);
    public static final FunStrToBinary UTF32LE = new FunStrToBinary(UtfType.utf32le);

    public static IFunction cUtf8(IFunction int1) {
        return new Function(UTF8, int1);
    }


    public static IFunction cUtf16Be(IFunction int1) {
        return new Function(UTF16BE, int1);
    }

    public static IFunction cUtf16Le(IFunction int1) {
        return new Function(UTF16LE, int1);
    }

    public static IFunction cUtf32Be(IFunction int1) {
        return new Function(UTF32BE, int1);
    }

    public static IFunction cUtf32Le(IFunction int1) {
        return new Function(UTF32LE, int1);
    }

    @MetricsMethod
    public FunctionMetrics metrics(StringType str) throws InvalidDataTypeException {
        final short minNumOfBytes = this.utfType.getNumOfBytes();
        final short maxNumOfBytes = 4;

        final int minBytes = str.getMinLen() * minNumOfBytes;
        final int maxBytes = str.getMaxLen() * maxNumOfBytes;

        return FunctionMetrics.c(maxBytes, maxBytes, new BitType(minBytes * 8, maxBytes * 8, true));
    }

    @EvalMethod
    public Object run(String s1) throws UnsupportedEncodingException {

        final String encoding;
        switch (this.utfType) {
            case utf8:
                encoding = "UTF-8";
                break;
            case utf16be:
                encoding = "UTF-16BE";
                break;
            case utf16le:
                encoding = "UTF-16LE";
                break;
            case utf32be:
                encoding = "UTF-32BE";
                break;
            case utf32le:
                encoding = "UTF-32LE";
                break;
            default:
                throw new IllegalArgumentException("Unknown encoding");
        }

        final byte[] binary = s1.getBytes(encoding);
        return new Binary(binary);
    }
}
