package com.alqema.app_system.pref;

import android.content.Context;
import android.content.SharedPreferences;

import com.alqema.app_system.AppController;

import org.jetbrains.annotations.Contract;

import java.util.Objects;


public class AppSharedPreferences {
    private enum SharedPreferencesKeys {
        currentUserChattingUid,Value
    }

    private static volatile AppSharedPreferences Instance;
    private final SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private AppSharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
    }

    public static synchronized AppSharedPreferences getInstance() {
        if (Instance == null) {
            Instance = new AppSharedPreferences(Objects.requireNonNull(AppController.getInstance()));
        }
        return Instance;
    }

    //.. VAlUE
    public String getValue() {
        return sharedPreferences.getString(SharedPreferencesKeys.currentUserChattingUid.name(), "");
    }

    public void setValue(String uid) {
        editor = sharedPreferences.edit();
        editor.putString(SharedPreferencesKeys.currentUserChattingUid.name(), uid);
        editor.apply();
    }

    public void removeValue() {
        editor = sharedPreferences.edit();
        editor.remove(SharedPreferencesKeys.currentUserChattingUid.name());
        editor.apply();
    }

    //...

    // to clear the file
    public void clear() {
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
