package com.simplefunctions.functions.integer;

import com.simplefunctions.base.Function;
import com.simplefunctions.base.FunctionMetrics;
import com.simplefunctions.base.IFunction;
import com.simplefunctions.base.InvalidDataTypeException;
import com.simplefunctions.dataTypes.BooleanType;
import com.simplefunctions.dataTypes.IntegerType;
import com.simplefunctions.functions.base.EvalMethod;
import com.simplefunctions.functions.base.FunctionTypeBase;
import com.simplefunctions.functions.base.MetricsMethod;
import com.simplefunctions.functions.literals.FunList;

/**
 * Buran.
 *
 * @author: ${USER} Date: 24.06.13 Time: 02:17
 */
public class FunIntCmp extends FunctionTypeBase {

    private final IntTest testOp;

    public static final FunIntCmp EQ = new FunIntCmp(IntTest.eq);
    public static final FunIntCmp LE = new FunIntCmp(IntTest.le);
    public static final FunIntCmp GT = new FunIntCmp(IntTest.gt);

    private FunIntCmp(IntTest op) {
        this.testOp = op;
    }

    public static IFunction eq(IFunction int1, IFunction int2) {
        return new Function(EQ, FunList.c(int1, int2));
    }

    public static IFunction le(IFunction int1, IFunction int2) {
        return new Function(LE, FunList.c(int1, int2));
    }

    public static IFunction gt(IFunction int1, IFunction int2) {
        return new Function(GT, FunList.c(int1, int2));
    }

    @MetricsMethod
    public FunctionMetrics metrics(IntegerType int1, IntegerType int2)
            throws InvalidDataTypeException {
        final BooleanType.BoolRange range;
        switch (this.testOp) {
            case eq:
                if (int1.intersect(int2)) {
                    if (int1.isFixRange() && int2.isFixRange()) {
                        range = BooleanType.BoolRange.trueOnly;
                    } else {
                        range = BooleanType.BoolRange.trueOrFalse;
                    }
                } else {
                    range = BooleanType.BoolRange.falseOnly;
                }
                break;
            case gt:
                if (int1.isStrictlyGreaterThan(int2)) {
                    range = BooleanType.BoolRange.trueOnly;
                } else {
                    if (int1.isStrictlyLesserThan(int2)) {
                        range = BooleanType.BoolRange.falseOnly;
                    } else {
                        range = BooleanType.BoolRange.trueOrFalse;
                    }
                }
                break;
            case le:
                if (int1.isStrictlyLesserThan(int2)) {
                    range = BooleanType.BoolRange.trueOnly;
                } else {
                    if (int1.isStrictlyGreaterThan(int2)) {
                        range = BooleanType.BoolRange.falseOnly;
                    } else {
                        range = BooleanType.BoolRange.trueOrFalse;
                    }
                }
                break;
            default:
                throw new IllegalArgumentException("unknown test operator");
        }
        return FunctionMetrics.c(1, 1, new BooleanType(range));
    }

    @EvalMethod
    public Object run(Long v1, Long v2) {
        switch (this.testOp) {
            case eq:
                return v1.equals(v2);
            case gt:
                return v1 > v2;
            case le:
                return v1 < v2;
            default:
                throw new IllegalArgumentException("unknown test operator");
        }
    }
}
