package com.aantik.arthook.runtime.model;

public enum Class {

    UNSAFE("sun.misc.Unsafe"),
    EXECUTABLE("java.lang.reflect.Executable");

    private final String mName;

    Class(String mName) {
        this.mName = mName;
    }

    public String getName() {
        return mName;
    }
}