package com.master.androidx.app;

import android.arch.lifecycle.MutableLiveData;

public final class Bus extends MutableLiveData<Object> {

    private static final class Holder {
        private static final Bus INSTANCE = new Bus();
    }

    private Bus() {
    }

    public static Bus instance() {
        return Holder.INSTANCE;
    }

}
