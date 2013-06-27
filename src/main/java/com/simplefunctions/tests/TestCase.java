package com.simplefunctions.tests;

import com.simplefunctions.base.IFunction;

/**
 * Buran.
 *
 * @author: ${USER} Date: 28.06.13 Time: 00:49
 */
public abstract class TestCase {
    public abstract String getCaseName();

    public abstract IFunction createFunction();

    public abstract Object asJava() throws Exception;
}
