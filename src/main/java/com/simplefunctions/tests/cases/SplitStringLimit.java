package com.simplefunctions.tests.cases;

import com.simplefunctions.base.IFunction;
import com.simplefunctions.functions.literals.FunInt;
import com.simplefunctions.functions.literals.FunString;
import com.simplefunctions.functions.string.FunStrSplit;
import com.simplefunctions.tests.TestCase;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * Buran.
 *
 * @author: ${USER} Date: 28.06.13 Time: 00:51
 */
public class SplitStringLimit extends TestCase {

    private static final String STR1 = "Lorem ipsum dolor sit amet consetetur sadipscing elitr " +
            "sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat sed " +
            "diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita " +
            "kasd gubergren no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum " +
            "dolor sit amet consetetur sadipscing elitr sed diam nonumy eirmod tempor invidunt ut" +
            " labore et dolore magna aliquyam erat sed diam voluptua. At vero eos et accusam et " +
            "justo duo dolores et ea rebum. Stet clita kasd gubergren no sea takimata sanctus est" +
            " Lorem ipsum dolor sit amet.";
    private static final char SPLIT_CHAR = ' ';
    private static final int LIMIT = 5;

    @Override
    public String getCaseName() {
        return "Split string at space character and limit splits";
    }

    @Override
    public IFunction createFunction() {
        return FunStrSplit.cLimit(SPLIT_CHAR, FunString.c(STR1), FunInt.c(LIMIT));
    }

    @Override
    public Object asJava() throws UnsupportedEncodingException {
        final String[] splits = STR1.split("\\" + SPLIT_CHAR, LIMIT);
        return Arrays.asList(splits);
    }
}
