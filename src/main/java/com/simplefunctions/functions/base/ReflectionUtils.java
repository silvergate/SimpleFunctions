package com.simplefunctions.functions.base;

import com.simplefunctions.base.*;
import com.sun.istack.internal.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Buran.
 *
 * @author: ${USER} Date: 24.06.13 Time: 01:58
 */
public class ReflectionUtils {

    private static Method findMethod(Class<?> thisClass, Class<? extends Annotation> annotation) {
        final Method[] methods = thisClass.getDeclaredMethods();
        Method foundMethod = null;
        for (final Method method : methods) {
            final Annotation evalMethod = method.getAnnotation(annotation);
            if (evalMethod != null) {
                if (foundMethod == null) {
                    foundMethod = method;
                } else {
                    throw new IllegalStateException("Duplicate methods");
                }
            }
        }
        return foundMethod;
    }

    @Nullable
    private static <T extends Annotation> T findParamAnnotation(Method method, int index,
            Class<T> clazz) {
        final Annotation[] annoArray = method.getParameterAnnotations()[index];
        T param = null;
        for (final Annotation singleAnno : annoArray) {
            if (singleAnno.annotationType().equals(clazz)) ;
            {
                if (param != null) {
                    throw new IllegalStateException("Duplicate param-annotation");
                }
                param = (T) singleAnno;
            }
        }
        return param;
    }


    public static FunctionMetrics calcMetrics(Object thisObject, IDataType inputDataType)
            throws InvalidDataTypeException, ComplexityOverflowException {
        final Method method = findMethod(thisObject.getClass(), MetricsMethod.class);
        if (method == null) {
            throw new IllegalStateException("Metrics-Method annotation not found.");
        }
        final Class<?>[] paramTypes = method.getParameterTypes();

        final List<IDataType> dataList = new ArrayList<>();
        int index = 0;
        for (final Class<?> paramType : paramTypes) {
            TypeParam param = findParamAnnotation(method, index, TypeParam.class);
            final Class<?> paramClass;
            paramClass = paramType;
            final boolean requited;
            if (param == null) {
                requited = true;
            } else {
                requited = param.value();
            }
            final IDataType data = TypeUtils.getTypeAtIndex(inputDataType, index);
            if (data == null) {
                if (requited) {
                    throw new IllegalStateException("Data required but not found");
                }
                dataList.add(null);
            } else {
                if (!paramClass.isAssignableFrom(data.getClass())) {
                    throw new IllegalStateException("Invalid data type");
                }
                dataList.add(data);
            }
            index++;
        }

        try {
            return (FunctionMetrics) method.invoke(thisObject, dataList.toArray());
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof InvalidDataTypeException) {
                throw (InvalidDataTypeException) e.getTargetException();
            }
            if (e.getTargetException() instanceof ComplexityOverflowException) {
                throw (ComplexityOverflowException) e.getTargetException();
            }
            throw new IllegalStateException(e);
        }
    }

    public static Object eval(Object thisObject, IEvaluable input) {
        final Method method = findMethod(thisObject.getClass(), EvalMethod.class);
        if (method == null) {
            throw new IllegalStateException("Eval-Method annotation not found.");
        }
        final Class<?>[] paramTypes = method.getParameterTypes();

        final List<Object> dataList = new ArrayList<>();
        int index = 0;
        for (final Class<?> paramType : paramTypes) {
            Param param = findParamAnnotation(method, index, Param.class);
            final Class<?> paramClass;
            paramClass = paramType;
            final boolean requited;
            if (param == null) {
                requited = true;
            } else {
                requited = param.value();
            }
            final Object data = DataUtils.getDataAtIndexOptional(input, index);
            if (data == null) {
                if (requited) {
                    throw new IllegalStateException("Data required but not found");
                }
                dataList.add(null);
            } else {
                if (!paramClass.isAssignableFrom(data.getClass())) {
                    throw new IllegalStateException("Invalid data type");
                }
                dataList.add(data);
            }
            index++;
        }

        try {
            return method.invoke(thisObject, dataList.toArray());
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof RuntimeException) {
                throw (RuntimeException) (e.getTargetException());
            }
            throw new IllegalStateException(e);
        }
    }
}
