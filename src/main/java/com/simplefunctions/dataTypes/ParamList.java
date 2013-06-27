package com.simplefunctions.dataTypes;

import com.simplefunctions.base.IEvaluable;

/**
 * Buran.
 *
 * @author: ${USER} Date: 27.06.13 Time: 20:59
 */
public class ParamList {
    private final IEvaluable[] functions;

    public ParamList(IEvaluable[] functions) {
        this.functions = functions;
    }

    public IEvaluable[] getFunctions() {
        return functions;
    }
}
