package com.master.android.meitu;

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

}
