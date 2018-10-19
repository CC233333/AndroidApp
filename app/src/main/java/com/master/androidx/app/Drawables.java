package com.master.androidx.app;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;

public class Drawables {

    public static Drawable getDrawable(Context context, int drawableRes) {
        return ContextCompat.getDrawable(context, drawableRes);
    }

    public static Drawable wrapTint(Drawable drawable, int color) {
        Drawable drawableWrapper = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawableWrapper, color);
        drawableWrapper.invalidateSelf();
        return drawableWrapper;
    }

    public static Drawable wrapTintList(Drawable drawable, ColorStateList tint) {
        Drawable drawableWrapper = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(drawableWrapper, tint);
        drawableWrapper.invalidateSelf();
        return drawableWrapper;
    }

    public static Drawable mutateTint(Drawable drawable, int color) {
        Drawable drawableWrapper = DrawableCompat.wrap(drawable).mutate();
        DrawableCompat.setTint(drawableWrapper, color);
        drawableWrapper.invalidateSelf();
        return drawableWrapper;
    }

    public static Drawable mutateTintList(Drawable drawable, ColorStateList tint) {
        Drawable drawableWrapper = DrawableCompat.wrap(drawable).mutate();
        DrawableCompat.setTintList(drawableWrapper, tint);
        drawableWrapper.invalidateSelf();
        return drawableWrapper;
    }

    public static Drawable wrapTintSelector(Drawable drawable, int color1, int color2) {
        int[] colors = new int[]{color1, color2};
        int[][] states = new int[2][];
        states[0] = new int[]{android.R.attr.state_pressed};
        states[1] = new int[]{};
        ColorStateList colorList = new ColorStateList(states, colors);
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(states[0], drawable);
        stateListDrawable.addState(states[1], drawable);

        Drawable drawableWrapper = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(drawableWrapper, colorList);
        drawableWrapper.invalidateSelf();
        return drawableWrapper;
    }

}
