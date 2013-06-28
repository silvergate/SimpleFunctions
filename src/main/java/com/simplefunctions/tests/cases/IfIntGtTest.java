package com.simplefunctions.tests.cases;

import com.simplefunctions.base.IFunction;
import com.simplefunctions.functions.integer.FunIntTest;
import com.simplefunctions.functions.literals.FunInt;
import com.simplefunctions.functions.literals.FunString;
import com.simplefunctions.functions.string.FunStrConcat;
import com.simplefunctions.functions.test.FunIf;
import com.simplefunctions.tests.TestCase;

/**
 * Buran.
 *
 * @author: ${USER} Date: 28.06.13 Time: 00:51
 */
public class IfIntGtTest extends TestCase {

    private static final String T1_TRUE = "(233 == 233)";
    private static final String T1_FALSE = "(233 != 233)";
    private static final String T2_TRUE = "(2323 < 3)";
    private static final String T2_FALSE = "(2323 > 3)";

    @Override
    public String getCaseName() {
        return "Test for equality and inequality of integer";
    }

    @Override
    public IFunction createFunction() {
        IFunction equalTrue =
                FunIf.c(FunIntTest.eq(FunInt.c(233), FunInt.c(233)), FunString.c(T1_TRUE),
                        FunString.c(T1_FALSE));
        IFunction leFalse =
                FunIf.c(FunIntTest.le(FunInt.c(2323), FunInt.c(3)), FunString.c(T2_TRUE),
                        FunString.c(T2_FALSE));
        return FunStrConcat.c(equalTrue, leFalse);
    }

    @Override
    public Object asJava() {
        final String equalTrue;
        if (233 == 233) equalTrue = T1_TRUE;
        else equalTrue = T1_FALSE;

        final String leFalse;
        if (2323 < 3) leFalse = T2_TRUE;
        else leFalse = T2_FALSE;

        return equalTrue + leFalse;
    }
}
