package com.simplefunctions.functions.bool;

import com.simplefunctions.base.Function;
import com.simplefunctions.base.FunctionMetrics;
import com.simplefunctions.base.IFunction;
import com.simplefunctions.base.InvalidDataTypeException;
import com.simplefunctions.dataTypes.BooleanType;
import com.simplefunctions.functions.base.EvalMethod;
import com.simplefunctions.functions.base.FunctionTypeBase;
import com.simplefunctions.functions.base.MetricsMethod;
import com.simplefunctions.functions.literals.FunList;

/**
 * Buran.
 *
 * @author: ${USER} Date: 24.06.13 Time: 02:17
 */
public class FunBoolOp extends FunctionTypeBase {

    private final BoolOp op;

    public static final FunBoolOp AND = new FunBoolOp(BoolOp.and);
    public static final FunBoolOp OR = new FunBoolOp(BoolOp.or);

    private FunBoolOp(BoolOp op) {
        this.op = op;
    }

    public static IFunction and(IFunction int1, IFunction int2) {
        return new Function(AND, FunList.c(int1, int2));
    }

    public static IFunction or(IFunction int1, IFunction int2) {
        return new Function(OR, FunList.c(int1, int2));
    }

    @MetricsMethod
    public FunctionMetrics metrics(BooleanType b1, BooleanType b2) throws InvalidDataTypeException {
        final BooleanType.BoolRange r1 = b1.getRange();
        final BooleanType.BoolRange r2 = b2.getRange();

        final boolean canTrue1 =
                (r1 == BooleanType.BoolRange.trueOrFalse) || (r1 == BooleanType.BoolRange.trueOnly);
        final boolean canTrue2 =
                (r2 == BooleanType.BoolRange.trueOrFalse) || (r2 == BooleanType.BoolRange.trueOnly);
        final boolean canFalse1 = (r1 == BooleanType.BoolRange.trueOrFalse) ||
                (r1 == BooleanType.BoolRange.falseOnly);
        final boolean canFalse2 = (r2 == BooleanType.BoolRange.trueOrFalse) ||
                (r2 == BooleanType.BoolRange.falseOnly);

        final boolean canTrue;
        final boolean canFalse;
        switch (this.op) {
            case and:
                canTrue = canTrue1 && canTrue2;
                canFalse = canFalse1 || canFalse2;
                break;
            case or:
                canTrue = canTrue1 || canTrue2;
                canFalse = canFalse1 || canFalse2;
                break;
            default:
                throw new IllegalArgumentException("unknown operator");
        }

        final BooleanType.BoolRange newRange;
        if (canFalse && canTrue) {
            newRange = BooleanType.BoolRange.trueOrFalse;
        } else {
            if (canFalse) {
                newRange = BooleanType.BoolRange.falseOnly;
            } else if (canTrue) {
                newRange = BooleanType.BoolRange.trueOrFalse;
            } else {
                throw new IllegalStateException("Implementation error");
            }
        }
        return FunctionMetrics.c(1, 1, new BooleanType(newRange));
    }

    @EvalMethod
    public Object run(Boolean v1, Boolean v2) {
        switch (this.op) {
            case and:
                return v1 && v2;
            case or:
                return v1 || v2;
            default:
                throw new IllegalArgumentException("unknown test operator");
        }
    }
}
