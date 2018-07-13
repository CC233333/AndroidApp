package com.master.androidx.vm;

public class ResultState {

    private int status;
    private String message;

    public ResultState(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ResultState running() {
        return new ResultState(0, "running");
    }

    public static ResultState success() {
        return new ResultState(1, "success");
    }

    public static ResultState failed() {
        return new ResultState(-1, "failed");
    }

    public static ResultState failed(String msg) {
        return new ResultState(-1, msg);
    }

    public static ResultState failed(int status, String msg) {
        return new ResultState(status, msg);
    }

    public boolean isRunning() {
        return status == 0;
    }

    public boolean isSuccess() {
        return status == 1;
    }

    public boolean isFailed() {
        return status == -1;
    }

    public boolean isEnding() {
        return status == -2;
    }

    @Override
    public String toString() {
        return "Error " + status + " " + message;
    }

    public String getMessage() {
        return message;
    }

}
