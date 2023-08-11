package com.alqema.app_system;

import android.app.Application;

public class AppController extends Application {
    private static AppController appController;

    @Override
    public void onCreate() {
        super.onCreate();
        appController = this;
    }

    public static AppController getInstance() {
        if (appController != null) {
            return appController;
        }
        return null;
    }

}
