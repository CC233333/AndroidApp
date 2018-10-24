package com.master.promise.utils;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.ColorInt;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

public class StatusBarUtils {

    public static void setTranslucentStatus(boolean translucent, Activity activity) {
        if (ApiUtils.api19()) {
            Window window = activity.getWindow();
            if (translucent) {
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            } else {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }
    }

    public static void setStatusBarColor(Activity activity, int color) {
        if (ApiUtils.api19()) {
            boolean isLightColor = toGrey(color) > 225;
            setStatusBarColor(activity.getWindow(), color);
            setStatusBarLight(activity.getWindow(), isLightColor);
        }
    }

    private static int toGrey(@ColorInt int color) {
        int blue = Color.blue(color);
        int green = Color.green(color);
        int red = Color.red(color);
        return (red * 38 + green * 75 + blue * 15) >> 7;
    }

    private static void setStatusBarColor(Window window, int color) {
        if (ApiUtils.api19()) {
            if (ApiUtils.api23()) {
                setStatusBarColor23(window, color);
            } else if (ApiUtils.api21() && !isEMUI()) {
                setStatusBarColor21(window, color);
            } else {
                setStatusBarColor19(window, color);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private static void setStatusBarColor23(Window window, int color) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(color);

        View contentView = window.findViewById(android.R.id.content);
        if (contentView != null) {
            contentView.setForeground(null);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static void setStatusBarColor21(Window window, int color) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(color);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static void setStatusBarColor19(Window window, int color) {

    }

    private static void setStatusBarLight(Window window, boolean isLight) {
        if (ApiUtils.api23()) {
            setStatusBarLight23(window, isLight);
        } else if (isMIUI()) {
            setMiUiStatusBarLight(window, isLight);
        } else if (isFlyme()) {
            setFlymeStatusBarLight(window, isLight);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private static void setStatusBarLight23(Window window, boolean isLight) {
        View decor = window.getDecorView();
        int visibility = decor.getSystemUiVisibility();
        if (isLight) {
            visibility |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        } else {
            visibility &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        }
        decor.setSystemUiVisibility(visibility);

    }

    private static void setMiUiStatusBarLight(Window window, boolean isLight) {
        Class<? extends Window> clazz = window.getClass();
        try {
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            int darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(window, isLight ? darkModeFlag : 0, darkModeFlag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setFlymeStatusBarLight(Window window, boolean isLight) {
        WindowManager.LayoutParams params = window.getAttributes();
        try {
            Field darkFlag = WindowManager.LayoutParams.class.
                    getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
            Field meizuFlags = WindowManager.LayoutParams.class.
                    getDeclaredField("meizuFlags");
            darkFlag.setAccessible(true);
            meizuFlags.setAccessible(true);
            int bit = darkFlag.getInt(null);
            int value = meizuFlags.getInt(params);
            if (isLight) {
                value |= bit;
            } else {
                value &= ~bit;
            }
            meizuFlags.setInt(params, value);
            window.setAttributes(params);
            darkFlag.setAccessible(false);
            meizuFlags.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* 华为 */
    private static boolean isEMUI() {
        File file = new File(Environment.getRootDirectory(), "build.prop");
        if (file.exists()) {
            Properties properties = new Properties();
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                properties.load(fis);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return properties.containsKey("ro.build.hw_emui_api_level");
        }
        return false;
    }

    /* 小米 */
    private static boolean isMIUI() {
        File file = new File(Environment.getRootDirectory(), "build.prop");
        try (FileInputStream is = new FileInputStream(file)) {
            Properties prop = new Properties();
            prop.load(is);
            return prop.getProperty("ro.miui.ui.version.code") != null
                    || prop.getProperty("ro.miui.ui.version.name") != null
                    || prop.getProperty("ro.miui.internal.storage") != null;
        } catch (final IOException e) {
            return false;
        }
    }

    /* 魅族 */
    private static boolean isFlyme() {
        return Build.DISPLAY.startsWith("Flyme");
    }

}
