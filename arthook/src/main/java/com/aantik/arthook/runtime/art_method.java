package com.aantik.arthook.runtime;

/*
 * Created by aantik
 * 10/14/2026 1:38 AM
 *
 *   ⋆   ႔ ႔
 *     ᠸ^ ^ ⸝⸝
 *      |、˜〵
 *      じしˍ,)⁐̤ᐷ
 *
 * Fox Mode 🍺
 */


import android.annotation.SuppressLint;

import com.aantik.arthook.logc.log;
import com.aantik.arthook.reflector.reflect;
import com.aantik.arthook.runtime.model.Class;
import com.aantik.arthook.runtime.model.Field;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

@SuppressWarnings("ALL")
@SuppressLint("DiscouragedPrivateApi")
public final class art_method {

    private art_method() {
        
    }

    private static String hex(long mValue) {
        return "0x" + Long.toHexString(mValue);
    }

    public static void hook(Method mOriginal, Method mTarget) {
        try {

            /**
             * @NObug :(
             */

            // Load Unsafe
            java.lang.Class<?> mClass = java.lang.Class.forName(Class.UNSAFE.getName());
            Object mUnsafe = reflect.get(mClass, Field.THE_UNSAFE.getName());

            // Get ART field
            java.lang.Class<?> mExecutable = java.lang.Class.forName(Class.EXECUTABLE.getName());
            java.lang.reflect.Field mArtField = reflect.field(mExecutable, Field.ART_METHOD.getName());

            // Get constructors
            Constructor<?>[] mConstructors = Throwable.class.getDeclaredConstructors();
            if (mConstructors.length < 2) return;

            Constructor<?> mFirst = mConstructors[0];
            Constructor<?> mSecond = mConstructors[1];

            // Get Unsafe methods
            Method mGet = reflect.method(mClass, com.aantik.arthook.runtime.model.Method.GET_LONG.getName(), Object.class, long.class);

            Method mPut = reflect.method(mClass, com.aantik.arthook.runtime.model.Method.PUT_LONG.getName(), long.class, long.class);

            Method mSize = reflect.method(mClass, com.aantik.arthook.runtime.model.Method.ADDRESS_SIZE.getName());

            int mStep = reflect.<Integer>call(mSize, mUnsafe);
            log.d("Pointer Size: " + mStep);

            if (mStep != 4 && mStep != 8) return;

            // Get ART Addres
            long mOriginalAddr = reflect.getLong(mArtField, mOriginal);
            long mTargetAddr = reflect.getLong(mArtField, mTarget);
            long mFirstAddr = reflect.getLong(mArtField, mFirst);
            long mSecondAddr = reflect.getLong(mArtField, mSecond);

            log.d("Original ART : " + hex(mOriginalAddr));

            log.d("Target ART : " + hex(mTargetAddr));

            log.d("Constructor 1 ART : " + hex(mFirstAddr));

            log.d("Constructor 2 ART : " + hex(mSecondAddr));

            // Calculate offset
            long mDistance = Math.abs(mSecondAddr - mFirstAddr);
            long mOffset = mDistance - mStep;

            log.d("ART Distance : " + mDistance);

            log.d("Initial Offset : " + hex(mOffset));

            // Find candidate offset
            boolean mFound = false;

            long i = mOffset;

            for ( ; i >= 0; ) {

                long mValue1 = reflect.<Long>call(mGet, mUnsafe, null, mFirstAddr + i);

                long mValue2 = reflect.<Long>call(mGet, mUnsafe, null, mSecondAddr + i);

                if (mValue1 != 0 && mValue2 != 0 && mValue1 != mValue2) {
                    mOffset = i;
                    mFound = true;
                    break;
                }

                i = i - mStep;
            }

            if (!mFound) {
                log.d("Oop's Offset not found");
                return;
            }

            log.d("Candidate Offset : " + hex(mOffset));

            // Read original entry
            long mEntry = reflect.<Long>call(mGet, mUnsafe, null, mOriginalAddr + mOffset);

            log.d("Original Entry : " + hex(mEntry));
            log.d("Target Entry Addr : " + hex(mTargetAddr + mOffset));

            // Write entry
            reflect.call(mPut, mUnsafe, mTargetAddr + mOffset, mEntry);

            log.d("Memory write completed (-_-) 👌");

        } catch (Exception e) {
            log.tr(e);
        }
    }
}
