package com.lucio.androidv2.network;

import com.google.gson.annotations.SerializedName;

public class Error {
    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
