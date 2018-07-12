package com.master.androidx.https;

public class AppException extends RuntimeException {

    private static String appMessage(int code, String message) {
        return "APP " + code + " " + message;
    }

    private int code;
    private String message;

    public AppException(int code, String message) {
        super(appMessage(code, message));
        this.code = code;
        this.message = message;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }

}
