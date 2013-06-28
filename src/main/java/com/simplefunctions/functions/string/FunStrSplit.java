package com.simplefunctions.functions.string;

import com.simplefunctions.base.*;
import com.simplefunctions.dataTypes.CollectionType;
import com.simplefunctions.dataTypes.IntegerType;
import com.simplefunctions.dataTypes.StringType;
import com.simplefunctions.functions.base.*;
import com.simplefunctions.functions.literals.FunList;
import com.simplefunctions.functions.literals.FunString;

import java.util.Arrays;

/**
 * Buran.
 *
 * @author: ${USER} Date: 23.06.13 Time: 14:28
 */
public class FunStrSplit extends FunctionTypeBase {

    private final char splitChar;

    public static IFunction c(char splitChar, IFunction input) {
        return new Function(new FunStrSplit(splitChar), input);
    }

    public static IFunction cLimit(char splitChar, IFunction input, IFunction limit) {
        return new Function(new FunStrSplit(splitChar), FunList.c(input, limit));
    }

    private FunStrSplit(char splitChar) {
        this.splitChar = splitChar;
    }

    @MetricsMethod
    public FunctionMetrics metrics(StringType inputDataType, @TypeParam(false) IntegerType limit)
            throws InvalidDataTypeException {
        final int maxNumOfElements;
        if ((limit != null) && (limit.getMaxValue() < Integer.MAX_VALUE)) {
            maxNumOfElements = (int) limit.getMaxValue();
        } else {
            maxNumOfElements = inputDataType.getMaxLen();
        }

        final IDataType elementType = new StringType(0, inputDataType.getMaxLen());
        final IDataType collection = CollectionType.list(0, maxNumOfElements, elementType);
        return FunctionMetrics
                .c(maxNumOfElements, FunString.getStringMemory(inputDataType.getMaxLen()),
                        collection);
    }

    @EvalMethod()
    public Object run(String input, @Param(false) Long limit) {
        final String[] result;
        if ((limit == null) || (limit > Integer.MAX_VALUE)) {
            result = input.split("\\" + this.splitChar);
        } else {
            result = input.split("\\" + this.splitChar, limit.intValue());
        }
        return Arrays.asList(result);
    }
}
