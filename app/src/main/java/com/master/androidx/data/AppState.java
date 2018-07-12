package com.master.androidx.data;

public class AppState {

    private int status;
    private String message;

    public AppState(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static AppState running() {
        return new AppState(0, "running");
    }

    public static AppState success() {
        return new AppState(1, "success");
    }

    public static AppState failed() {
        return new AppState(-1, "failed");
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
        return "APP " + status + " " + message;
    }

}
