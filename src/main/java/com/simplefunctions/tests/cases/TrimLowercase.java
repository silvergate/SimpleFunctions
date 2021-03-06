package com.simplefunctions.tests.cases;

import com.simplefunctions.base.IFunction;
import com.simplefunctions.functions.literals.FunString;
import com.simplefunctions.functions.string.FunStrLower;
import com.simplefunctions.functions.string.FunStrTrim;
import com.simplefunctions.functions.string.FunStrUpper;
import com.simplefunctions.tests.TestCase;

import java.util.Locale;

/**
 * Buran.
 *
 * @author: ${USER} Date: 28.06.13 Time: 00:51
 */
public class TrimLowercase extends TestCase {

    private static final String TEST_STR = "  TITLE " +
            "ÄÄÖüüÜöö " +
            "{} " +
            "HaLLo " +
            "welt " +
            "" +
            " ";

    private static final String LANG_TAG = "tr-TR";

    @Override
    public String getCaseName() {
        return "String Lowercase, trim and uppercase";
    }

    @Override
    public IFunction createFunction() {
        return FunStrUpper
                .c(FunStrTrim.c(FunStrLower.c(FunString.c(TEST_STR), FunString.c(LANG_TAG))),
                        FunString.c(LANG_TAG));
    }

    @Override
    public Object asJava() {
        return TEST_STR.toLowerCase(Locale.forLanguageTag(LANG_TAG)).trim()
                .toUpperCase(Locale.forLanguageTag(LANG_TAG));
    }
}
