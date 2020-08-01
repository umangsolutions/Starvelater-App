package com.example.starvelater.adapters.userdashboard_adapters;

import android.graphics.drawable.GradientDrawable;

public class UtilityHelperClass {

    String title;
    int image;
    GradientDrawable gradientDrawable;

    public UtilityHelperClass(String title, int image, GradientDrawable gradientDrawable) {
        this.title = title;
        this.image = image;
        this.gradientDrawable = gradientDrawable;
    }

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }

    public GradientDrawable getGradientDrawable() {
        return gradientDrawable;
    }
}
