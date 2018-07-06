package com.master.androidx.meitu.entity;

import java.util.List;

public class UserResponse {

    public boolean result;
    public boolean isEnd;
    public List<UserModel> discoverList;

    public UserResponse(boolean result) {
        this.result = result;
    }

    public static UserResponse error(Throwable throwable) {
        return new UserResponse(false);
    }


    /**
     * -1 网络错误
     */
    public int code;

    public int code() {
        return code;
    }

    public boolean isSuccessful() {
        return code == 0;
    }

}
