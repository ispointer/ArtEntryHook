package com.aantik.arthook.logc;

/*
 * Created by aantik
 * 10/14/2025 4:43 AM
 *
 *   ⋆   ႔ ႔
 *     ᠸ^ ^ ⸝⸝
 *      |、˜〵
 *      じしˍ,)⁐̤ᐷ
 *
 * Fox Mode 🍺
 */

import android.util.Log;

public final class log {

    private static final String TAG = "ArtLogger";

    private log() {
        /// Ooop's
    }

    public static <T> void d(T sMessage) {
        Log.d(TAG, String.valueOf(sMessage));
    }

    public static <T> void i(T sMessage) {
        Log.i(TAG, String.valueOf(sMessage));
    }

    public static <T> void e(T sMessage) {
        Log.e(TAG, String.valueOf(sMessage));
    }

    public static <T> void w(T sMessage) {
        Log.w(TAG, String.valueOf(sMessage));
    }

    public static <T> void v(T sMessage) {
        Log.v(TAG, String.valueOf(sMessage));
    }

    public static <T> void wtf(T sMessage) {
        Log.wtf(TAG, String.valueOf(sMessage));
    }

    public static <T> void tr(T e) {
        Log.e(TAG, "Oops", (Throwable) e);
    }

}