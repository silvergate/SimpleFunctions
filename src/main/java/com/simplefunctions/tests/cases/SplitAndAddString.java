package com.simplefunctions.tests.cases;

import com.simplefunctions.base.IFunction;
import com.simplefunctions.base.IFunctionType;
import com.simplefunctions.dataTypes.Binary;
import com.simplefunctions.functions.collection.FunColEach;
import com.simplefunctions.functions.common.FunChain;
import com.simplefunctions.functions.common.FunPrepared;
import com.simplefunctions.functions.literals.FunString;
import com.simplefunctions.functions.string.*;
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
public class SplitAndAddString extends TestCase {

    private static final String STR1 = "Hello world, greetings from functional programming";
    private static final String STR2 = "BEFORE:";
    private static final char SPLIT_CHAR = ' ';

    @Override
    public String getCaseName() {
        return "Split string add a string before each element, trim, lowercase and to UTF-8";
    }

    @Override
    public IFunction createFunction() {
        IFunctionType addTextInFrontFunction =
                FunPrepared.build(FunStrConcat.SINGLETON).fun(FunString.c(STR2)).inner().get();
        IFunctionType chainedLoopFunction =
                FunChain.c(addTextInFrontFunction, FunStringTrim.SINGLETON,
                        FunStringLower.SINGLETON, FunStringToBinary.UTF8);
        return FunColEach.c(chainedLoopFunction, FunStringSplit.c(SPLIT_CHAR, FunString.c(STR1)));
    }

    @Override
    public Object asJava() throws UnsupportedEncodingException {
        final String[] splits = STR1.split("\\" + SPLIT_CHAR);
        final List<Binary> results = new ArrayList<>();
        for (final String element : splits) {
            final String withTextInFront = STR2 + element;
            final String trimmed = withTextInFront.trim();
            final String lowerCase = trimmed.toLowerCase(Locale.forLanguageTag("en-US"));
            final byte[] toBinaryUtf8 = lowerCase.getBytes("UTF-8");
            results.add(new Binary(toBinaryUtf8));
        }
        return results;
    }
}
