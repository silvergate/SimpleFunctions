package com.simplefunctions.functions.base;

/**
 * Buran.
 *
 * @author: ${USER} Date: 24.06.13 Time: 02:22
 */
public class MathUtils {

    public static boolean isAddOverflow(long left, long right) {
        if (right > 0 ? left > Integer.MAX_VALUE - right : left < Integer.MIN_VALUE - right) {
            return true;
        }
        return false;
    }

    public static boolean isSubOverflow(long left, long right) {
        if (right > 0 ? left < Integer.MIN_VALUE + right : left > Integer.MAX_VALUE + right) {
            return true;
        }
        return false;
    }


    public static boolean isMulOverflow(long left, long right) {
        if (right > 0 ? left > Integer.MAX_VALUE / right || left < Integer.MIN_VALUE / right :
                (right < -1 ? left > Integer.MIN_VALUE / right || left < Integer.MAX_VALUE / right :
                        right == -1 && left == Integer.MIN_VALUE)) {
            return true;
        }
        return false;
    }

    public static boolean isDivOverflow(long left, long right) {
        if ((left == Integer.MIN_VALUE) && (right == -1)) {
            return true;
        }
        return false;
    }

    public static boolean isAbsOverflow(long left) {
        if (left == Integer.MIN_VALUE) {
            return true;
        }
        return false;
    }

}
