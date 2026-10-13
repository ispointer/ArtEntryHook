package com.aantik.arthook;


/*
 * Created by aantik
 * 6/07/2026 1:20 PM
 *
 *   ⋆    ႔ ႔
 *     ᠸ^ ^ ⸝⸝
 *       |、˜〵
 *      じしˍ,)⁐̤ᐷ
 *
 * Fox Mode 🍺
 */

import com.aantik.arthook.logc.log;
import com.aantik.arthook.reflector.reflect;
import android.app.Activity;
import android.util.Log;
import com.aantik.arthook.runtime.art_method;
import java.lang.reflect.Method;

public class Hooker {

    // Hook for fake VIP 🍻🍺
    public static boolean isFakeVip() {
        return true;
    }

    public static void HookCall() {
        try {

            Method source = reflect.method(Hooker.class, "isFakeVip");

            Class<?> targetClass = Class.forName("com.art.hookexample.isVip");

            Method target = reflect.method(targetClass, "isVip");

            art_method.hook(source, target);

        } catch (Exception e) {
            log.d("hook init : " + e.getMessage());
        }
    }
}

