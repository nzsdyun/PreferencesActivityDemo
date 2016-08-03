package com.example.chenkun.preferencesactivitydemo.com.example.chenkun.util;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Custom Toast, you can use your layout or use traditional toast
 * @author sky
 */
public class ToastUtil {
    private static final String TAG = ToastUtil.class.getSimpleName();

    /**
     * you can customize the layout, but layout you first include a TextView
     * @param layoutId layout id
     * @param text     Toast text
     * @param duration Toast show duration
     * @param gravity  Gravity
     */
    private static void doShowToast(Context context, int layoutId, CharSequence text
            , int duration, int gravity) {
        //default
        if (layoutId <= 0) {
            //FIXME: this should be the default one layout.
            Toast.makeText(context, text, duration).show();
        } else {
            try {
                ViewGroup viewGroup = (ViewGroup) LayoutInflater
                        .from(context).inflate(layoutId, null);
                Toast toast = new Toast(context);
                toast.setView(viewGroup);
                TextView tv = (TextView) viewGroup.getChildAt(0);
                if (tv != null) {
                    tv.setText(text);
                } else {
                    toast.setText(text);
                }
                toast.setDuration(duration);
                if (gravity != 0)
                    toast.setGravity(gravity, 0, 0);
                toast.show();
            } catch (Exception e) {
                Log.e(TAG, "create toast view failed:" + e.getMessage());
            }
        }
    }

    /**
     * doShowToast(Context context, int layoutId, int gravity
     * , final CharSequence text, int duration)
     */
    private static void doShowToast(Context context, int layoutId
            , int gravity, final int textId, int duration) {
        doShowToast(context, layoutId, getString(context, textId), duration, gravity);
    }

    //TODO: traditional toast call
    public static void showToast(Context context, int contentId) {
        doShowToast(context, 0, getString(context, contentId), Toast.LENGTH_SHORT, 0);
    }

    public static void showToast(Context context, CharSequence content) {
        doShowToast(context, 0, content, Toast.LENGTH_SHORT, 0);
    }

    //TODO: custom toast call
    public static void showToast(Context context, int layoutId, int contentId) {
        doShowToast(context, layoutId, getString(context, contentId), Toast.LENGTH_SHORT, 0);
    }

    public static void showToast(Context context, int layoutId, CharSequence content) {
        doShowToast(context, layoutId, content, Toast.LENGTH_SHORT, 0);
    }

    public static void showToast(Context context, int layoutId, int contentId, int duration) {
        doShowToast(context, layoutId, getString(context, contentId), duration, 0);
    }

    public static void showToast(Context context, int layoutId, CharSequence content, int duration) {
        doShowToast(context, layoutId, content, duration, 0);
    }

    /**
     * get String context
     * @param context @see Context
     * @param resId   resource id
     * @return String content
     */
    private static String getString(Context context, int resId) {
        return context.getResources().getString(resId);
    }
}
