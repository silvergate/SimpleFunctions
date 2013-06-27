package com.simplefunctions.functions.integer;

import com.simplefunctions.base.Function;
import com.simplefunctions.base.FunctionMetrics;
import com.simplefunctions.base.IFunction;
import com.simplefunctions.base.InvalidDataTypeException;
import com.simplefunctions.dataTypes.IntegerType;
import com.simplefunctions.functions.base.EvalMethod;
import com.simplefunctions.functions.base.FunctionTypeBase;
import com.simplefunctions.functions.base.MathUtils;
import com.simplefunctions.functions.base.MetricsMethod;
import com.simplefunctions.functions.literals.FunList;

/**
 * Buran.
 *
 * @author: ${USER} Date: 24.06.13 Time: 02:17
 */
public class FunIntArithmOp extends FunctionTypeBase {

    private final ArithmeticOp op;

    public static final FunIntArithmOp ADD = new FunIntArithmOp(ArithmeticOp.add);
    public static final FunIntArithmOp SUB = new FunIntArithmOp(ArithmeticOp.sub);
    public static final FunIntArithmOp MUL = new FunIntArithmOp(ArithmeticOp.mul);
    public static final FunIntArithmOp DIV = new FunIntArithmOp(ArithmeticOp.div);
    public static final FunIntArithmOp MOD = new FunIntArithmOp(ArithmeticOp.mod);

    private FunIntArithmOp(ArithmeticOp op) {
        this.op = op;
    }

    public static IFunction add(IFunction int1, IFunction int2) {
        return new Function(ADD, FunList.c(int1, int2));
    }

    public static IFunction sub(IFunction int1, IFunction int2) {
        return new Function(SUB, FunList.c(int1, int2));
    }

    public static IFunction mul(IFunction int1, IFunction int2) {
        return new Function(MUL, FunList.c(int1, int2));
    }

    public static IFunction div(IFunction int1, IFunction int2) {
        return new Function(DIV, FunList.c(int1, int2));
    }

    public static IFunction mod(IFunction int1, IFunction int2) {
        return new Function(MOD, FunList.c(int1, int2));
    }

    @MetricsMethod
    public FunctionMetrics metrics(IntegerType int1, IntegerType int2)
            throws InvalidDataTypeException {

        final boolean overflow;
        final int cpu;
        switch (this.op) {
            case add:
                overflow = MathUtils.isAddOverflow(int1.getMinValue(), int2.getMinValue()) ||
                        MathUtils.isAddOverflow(int1.getMaxValue(), int2.getMaxValue());
                cpu = 1;
                break;
            case sub:
                overflow = MathUtils.isSubOverflow(int1.getMinValue(), int2.getMaxValue()) ||
                        MathUtils.isAddOverflow(int1.getMaxValue(), int2.getMinValue());
                cpu = 1;
                break;
            case mul:
                overflow = MathUtils.isMulOverflow(int1.getMinValue(), int2.getMinValue()) ||
                        MathUtils.isAddOverflow(int1.getMaxValue(), int2.getMaxValue());
                cpu = 2;
                break;
            case div:
                if (int2.canContain(0)) {
                    /* Prevent div by zero */
                    throw new InvalidDataTypeException();
                }
                overflow = MathUtils.isDivOverflow(int1.getMinValue(), int2.getMaxValue()) ||
                        MathUtils.isAddOverflow(int1.getMaxValue(), int2.getMinValue());
                cpu = 2;
                break;
            case mod:
                cpu = 2;
                overflow = false;
                break;
            default:
                throw new IllegalArgumentException("Unknown operation");
        }

        final long minVal;
        final long maxVal;
        if (overflow) {
            minVal = Long.MIN_VALUE;
            maxVal = Long.MAX_VALUE;
        } else {
            switch (this.op) {
                case add:
                    // (-10, 15) + (-60, 10)
                    minVal = int1.getMinValue() + int2.getMinValue();
                    maxVal = int1.getMaxValue() + int2.getMaxValue();
                    break;
                case sub:
                    minVal = int1.getMinValue() - int2.getMaxValue();
                    maxVal = int1.getMaxValue() - int2.getMinValue();
                    break;
                case mul:
                    //TODO: Korrigieren, ist nicht korrekt bei negativen.
                    minVal = int1.getMinValue() * int2.getMinValue();
                    maxVal = int1.getMaxValue() * int2.getMaxValue();
                    break;
                case div:
                    //TODO: Korrigieren, ist nicht korrekt bei negativen.
                    minVal = int1.getMinValue() / int2.getMaxValue();
                    maxVal = int1.getMaxValue() / int2.getMinValue();
                    break;
                case mod:
                    if (int1.canBeNegative()) {
                        minVal = -(int2.getMaxValue() - 1);
                        maxVal = int2.getMaxValue() - 1;
                    } else {
                        minVal = 0;
                        maxVal = int2.getMaxValue() - 1;
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unknown operation");
            }
        }

        return FunctionMetrics.c(cpu, IntegerType.MEMORY * 2, new IntegerType(minVal, maxVal));
    }

    @EvalMethod
    public Object run(Long v1, Long v2) {
        switch (this.op) {
            case add:
                return v1 + v2;
            case sub:
                return v1 - v2;
            case mul:
                return v1 * v2;
            case div:
                return v1 / v2;
            case mod:
                return v1 % v2;
            default:
                throw new IllegalArgumentException("Unknown operation");
        }
    }
}
