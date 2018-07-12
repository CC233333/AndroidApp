package com.master.androidx.data;

public class RR {

    public int code;
    public String message;

    public RR(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static RR success() {
        return new RR(0, "ok");
    }

    public static RR error() {
        return new RR(-1, "error");
    }

    public boolean isSuccessful() {
        return code == 0;
    }


}
