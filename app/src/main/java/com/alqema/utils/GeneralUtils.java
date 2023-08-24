package com.alqema.utils;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.navigation.NavOptions;

import com.alqema.R;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class GeneralUtils {
    private static volatile GeneralUtils Instance;

    private GeneralUtils() {
    }

    public static synchronized GeneralUtils getInstance() {
        if (Instance == null) {
            Instance = new GeneralUtils();
        }
        return Instance;
    }

    private void print(String text) {
        System.out.println(text);
    }

    public void showSnackBar(View view, String text) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show();
    }

    public void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static String formatTimeStamp(Long timeStamp) {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        return format.format(timeStamp);
    }

    public static NavOptions getNavOptions() {
        return new NavOptions.Builder()
                .setEnterAnim(R.anim.slide_in_left)
                .setExitAnim(R.anim.slide_out_right)
                .setPopEnterAnim(R.anim.slide_in_right)
                .setPopExitAnim(R.anim.slide_out_left)
                .build();
    }

    public Animation getAppRecyclerAnimation(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.slide_left_right_loop);
    }
}
