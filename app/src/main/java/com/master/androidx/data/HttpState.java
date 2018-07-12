package com.master.androidx.data;

public class HttpState {

    private int code;
    private String message;

    public HttpState(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }


    @Override
    public String toString() {
        return "HTTP " + code + " " + message;
    }

}