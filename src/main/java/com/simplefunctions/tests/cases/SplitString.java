package com.simplefunctions.tests.cases;

import com.simplefunctions.base.IFunction;
import com.simplefunctions.functions.literals.FunString;
import com.simplefunctions.functions.string.FunStringSplit;
import com.simplefunctions.tests.TestCase;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * Buran.
 *
 * @author: ${USER} Date: 28.06.13 Time: 00:51
 */
public class SplitString extends TestCase {

    private static final String STR1 = "  Ich bin ein Text mit   lücken ";
    private static final char SPLIT_CHAR = ' ';

    @Override
    public String getCaseName() {
        return "Split string at space character";
    }

    @Override
    public IFunction createFunction() {
        return FunStringSplit.c(SPLIT_CHAR, FunString.c(STR1));
    }

    @Override
    public Object asJava() throws UnsupportedEncodingException {
        final String[] splits = STR1.split("\\" + SPLIT_CHAR);
        return Arrays.asList(splits);
    }
}
