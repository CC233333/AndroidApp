package com.master.android.datasource;

public class Response<T> {

    public int code;
    public String message;
    public T data;

    public Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Response<T> error() {
        return new Response<>(-1, "error", null);
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(200, "message", data);
    }

    public static <T> Response<T> success(int code, String message, T data) {
        return new Response<>(code, message, data);
    }

}
