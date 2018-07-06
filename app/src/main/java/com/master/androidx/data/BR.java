package com.master.androidx.data;

public class BR<T> {

    public int code;
    public String message;
    public T data;

    public BR(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BR(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> BR<T> create(T data) {
        return new BR<>(0, "ok", data);
    }

    public static <T> BR<T> success() {
        return new BR<>(0, "ok");
    }

    public static <T> BR<T> error() {
        return new BR<>(-1, "error");
    }

    public static <T> BR<T> empty() {
        return new BR<>(-2, "empty");
    }

    public boolean isSuccessful() {
        return code == 0;
    }

}
