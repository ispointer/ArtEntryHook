package com.aantik.arthook.runtime.model;

public enum Field {

    THE_UNSAFE("theUnsafe"),
    ART_METHOD("artMethod");

    private final String mName;

    Field(String mName) {
        this.mName = mName;
    }

    public String getName() {
        return mName;
    }
}