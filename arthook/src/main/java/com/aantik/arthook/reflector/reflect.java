package com.aantik.arthook.reflector;

/*
 * Created by aantik
 * 10/14/2026 1:56 AM
 *
 *   ⋆   ႔ ႔
 *     ᠸ^ ^ ⸝⸝
 *      |、˜〵
 *      じしˍ,)⁐̤ᐷ
 *
 * Fox Mode 🍺
 */

import com.aantik.arthook.logc.log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class reflect {

    private reflect() {
        log.d("Oop's");
    }

    /**
     * @Reflector MainCore
     * @param mObj
     * @param mField
     * @return
     * @param <T>
     */

    @SuppressWarnings("unchecked")
    public static <T> T get(Object mObj, String mField) {
        try {
            Field field = mObj.getClass().getDeclaredField(mField);
            field.setAccessible(true);
            return (T) field.get(mObj);
        } catch (Exception e) {
            log.d("Field Get : " + e.getMessage());
            return null;
        }
    }

    public static <T> void set(Object mObj, String mField, T mValue) {
        try {
            Field field = mObj.getClass().getDeclaredField(mField);
            field.setAccessible(true);
            field.set(mObj, mValue);
        } catch (Exception e) {
            log.d("Field Set : " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T call(Object mObj, String mMethod, Class<?>[] mParams, Object... mArrays) {
        try {
            Method method = mObj.getClass().getDeclaredMethod(mMethod, mParams);
            method.setAccessible(true);
            return (T) method.invoke(mObj, mArrays);
        } catch (Exception e) {
            log.d("Method Call : " + e.getMessage());
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(Class<?> mClass, String mField) {
        try {
            Field field = mClass.getDeclaredField(mField);
            field.setAccessible(true);
            return (T) field.get(null);
        } catch (Exception e) {
            log.tr(e);
            return null;
        }
    }

    /*@SuppressWarnings("unchecked")
    public static <T> T get(Object mObj, String mField, Object mTarget) {
        try {
            Field field = mObj.getClass().getDeclaredField(mField);
            field.setAccessible(true);
            return (T) field.get(mTarget);
        } catch (Exception e) {
            log.tr(e);
            return null;
        }
    }*/

    @SuppressWarnings("unchecked")
    public static <T> T call(Method mMethod, Object mObj, Object... mArgs) {
        try {
            mMethod.setAccessible(true);
            return (T) mMethod.invoke(mObj, mArgs);
        } catch (Exception e) {
            log.tr(e);
            return null;
        }
    }

    /**
     *
     * @helper Reflector
     * @param mClass
     * @param mName
     * @return
     */

    public static Field field(Class<?> mClass, String mName) {
        try {
            Field mField = mClass.getDeclaredField(mName);
            mField.setAccessible(true);
            return mField;
        } catch (Exception e) {
            log.tr(e);
            return null;
        }
    }

    public static Method method(Class<?> mClass, String mName, Class<?>... mParams) {
        try {
            Method mMethod = mClass.getDeclaredMethod(mName, mParams);
            mMethod.setAccessible(true);
            return mMethod;
        } catch (Exception e) {
            log.tr(e);
            return null;
        }
    }

    public static Long getLong(Field mField, Object mObj) {
        try {
            mField.setAccessible(true);
            return mField.getLong(mObj);
        } catch (Exception e) {
            log.tr(e);
            return null;
        }
    }
}