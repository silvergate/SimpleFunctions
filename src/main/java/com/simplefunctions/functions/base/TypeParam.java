package com.simplefunctions.functions.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Buran.
 *
 * @author: ${USER} Date: 24.06.13 Time: 00:29
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.PARAMETER})
public @interface TypeParam {
    boolean value() default true;   /* Required */
}
