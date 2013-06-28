package com.simplefunctions.tests;

import com.simplefunctions.tests.cases.*;

/**
 * Buran.
 *
 * @author: ${USER} Date: 23.06.13 Time: 18:12
 */
public class TestOne {

    public static void main(String[] args)

            throws Exception {

        TestRunner tr = new TestRunner();
        tr.runTest(new TrimLowercase());
        tr.runTest(new MulToBinary64());
        tr.runTest(new ConvUtf8And32Concat());
        tr.runTest(new SplitString());
        tr.runTest(new SplitStringNoDuplicates());
        tr.runTest(new SplitStringLimit());
        tr.runTest(new SplitAndAddString());
        tr.runTest(new IfIntGtTest());

    }
}
