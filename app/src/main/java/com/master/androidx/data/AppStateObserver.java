package com.master.androidx.data;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.master.androidx.util.Toasts;

public class AppStateObserver implements Observer<AppState> {

    @Override
    public void onChanged(@Nullable AppState appState) {
        if (appState != null) {
            if (appState.isFailed()) {
                Toasts.shortToast(appState.toString());
            }
        }
    }

}
