package com.aantik.arthook.runtime.model;

public enum Method {

    GET_LONG("getLong"),
    PUT_LONG("putLong"),
    ADDRESS_SIZE("addressSize");

    private final String mName;

    Method(String mName) {
        this.mName = mName;
    }

    public String getName() {
        return mName;
    }
}