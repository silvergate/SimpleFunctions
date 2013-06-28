package com.simplefunctions.tests.cases;

import com.simplefunctions.base.ComplexityOverflowException;
import com.simplefunctions.base.IFunction;
import com.simplefunctions.base.IFunctionType;
import com.simplefunctions.base.InvalidDataTypeException;
import com.simplefunctions.functions.collection.FunColFilter;
import com.simplefunctions.functions.common.FunPrepared;
import com.simplefunctions.functions.integer.FunIntCmp;
import com.simplefunctions.functions.literals.FunCollection;
import com.simplefunctions.functions.literals.FunFunTt;
import com.simplefunctions.functions.literals.FunInt;
import com.simplefunctions.tests.TestCase;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Buran.
 *
 * @author: ${USER} Date: 28.06.13 Time: 00:51
 */
public class IntFilterTest2 extends TestCase {

    private static final long[] INTS =
            new long[]{4, 345632, 33, 343, 998988, 21, 983, -12, -2123, 234232, 20, 3, 5, 129, 330,
                    22, 88833, 3233, 3, 3234};
    private static final int LESSER_THAN = -2222222;

    @Override
    public String getCaseName() {
        return "Filters a list of integers (inferred result)";
    }

    @Override
    public IFunction createFunction() throws InvalidDataTypeException, ComplexityOverflowException {
        /* Filter function (? < LESSER_THAN) */
        IFunctionType filter =
                FunPrepared.build(FunIntCmp.LE).fromOuter().fun(FunInt.c(LESSER_THAN)).get();
        /* Filter */
        return FunColFilter.c(FunCollection.list(FunInt.fromArray(INTS)), FunFunTt.c(filter));
    }

    @Override
    public Object asJava() throws UnsupportedEncodingException {
        final List<Long> results = new ArrayList<>();
        for (final long value : INTS) {
            if (value < LESSER_THAN) {
                results.add(value);
            }
        }
        return results;
    }
}
