package com.simplefunctions.tests.cases;

import com.simplefunctions.base.IFunction;
import com.simplefunctions.functions.collection.FunColEach;
import com.simplefunctions.functions.common.FunNil;
import com.simplefunctions.functions.literals.FunFunTt;
import com.simplefunctions.functions.literals.FunString;
import com.simplefunctions.functions.string.FunStrSplit;
import com.simplefunctions.tests.TestCase;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Buran.
 *
 * @author: ${USER} Date: 28.06.13 Time: 00:51
 */
public class SplitStringNoDuplicates extends TestCase {

    private static final String STR1 = " ich bin ein text, aber text ich bin mit doubletten ";
    private static final char SPLIT_CHAR = ' ';

    @Override
    public String getCaseName() {
        return "Split string at space character no duplicate element";
    }

    @Override
    public IFunction createFunction() {
        return FunColEach
                .unique(FunStrSplit.c(SPLIT_CHAR, FunString.c(STR1)), FunFunTt.c(FunNil.SINGLETON));
    }

    @Override
    public Object asJava() throws UnsupportedEncodingException {
        final String[] splits = STR1.split("\\" + SPLIT_CHAR);
        final Set<String> splitsSet = new HashSet<>();
        splitsSet.addAll(Arrays.asList(splits));
        return splitsSet;
    }
}
