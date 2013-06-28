package com.simplefunctions.tests.cases;

import com.simplefunctions.base.IFunction;
import com.simplefunctions.functions.bool.FunBoolOp;
import com.simplefunctions.functions.collection.FunColEach;
import com.simplefunctions.functions.collection.FunColFilter;
import com.simplefunctions.functions.common.FunChain;
import com.simplefunctions.functions.common.FunPrepared;
import com.simplefunctions.functions.integer.FunIntCmp;
import com.simplefunctions.functions.literals.FunFunTt;
import com.simplefunctions.functions.literals.FunInt;
import com.simplefunctions.functions.literals.FunString;
import com.simplefunctions.functions.string.FunStrLen;
import com.simplefunctions.functions.string.FunStrSplit;
import com.simplefunctions.functions.string.FunStrTrim;
import com.simplefunctions.functions.string.FunStrUpper;
import com.simplefunctions.tests.TestCase;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Buran.
 *
 * @author: ${USER} Date: 28.06.13 Time: 00:51
 */
public class SplitFilterLenInfer extends TestCase {

    private static final String STR1 = "Hallo welt, ich bin ein satz";
    private static final char SPLIT_CHAR = ' ';
    private static final long LESSER = 800000;
    private static final long MORE = 400000;

    @Override
    public String getCaseName() {
        return "Split string at space character, trim, filter words by length and uppercase and " +
                "infer result";
    }

    @Override
    public IFunction createFunction() {
        IFunction splitString = FunStrSplit.c(SPLIT_CHAR, FunString.c(STR1));
        IFunction trimmedStrings = FunColEach.c(splitString, FunFunTt.c(FunStrTrim.SINGLETON));

        final FunPrepared cmpGtFun =
                FunPrepared.build(FunIntCmp.GT).fromOuter().fun(FunInt.c(MORE)).get();
        final FunPrepared cmpLeFun =
                FunPrepared.build(FunIntCmp.LE).fromOuter().fun(FunInt.c(LESSER)).get();
        final FunPrepared cmpLeGtFun =
                FunPrepared.build(FunBoolOp.AND).nested(cmpGtFun, 0).nested(cmpLeFun, 0).get();

        final FunChain fun = FunChain.c(FunStrLen.SINGLETON, cmpLeGtFun);

        final IFunction filtered = FunColFilter.c(trimmedStrings, FunFunTt.c(fun));
        return FunColEach.c(filtered, FunFunTt.c(FunStrUpper.SINGLETON));
    }

    @Override
    public Object asJava() throws UnsupportedEncodingException {
        final String[] splits = STR1.split("\\" + SPLIT_CHAR);
        final List<String> results = new ArrayList<>();
        for (final String split : splits) {
            final String splitTrim = split.trim();
            final int length = splitTrim.length();
            final boolean lengthOk = length > MORE && length < LESSER;
            if (lengthOk) {
                results.add(splitTrim.toUpperCase(Locale.forLanguageTag("en-US")));
            }
        }
        return results;
    }
}
