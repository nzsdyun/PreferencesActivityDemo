package com.example.chenkun.preferencesactivitydemo;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.util.Log;

import java.util.List;

public class PreferenceWithHeads extends PreferenceActivity {
    private static final String TAG = PreferenceWithHeads.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            addPreferencesFromResource(R.xml.preference_head_old);
        }
    }

    public static void startPreferenceHead(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, PreferenceWithHeads.class);
        context.startActivity(intent);
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        return true;
    }

    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.fragment_head, target);
    }

    public static class PrefHeadOne extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Log.i(TAG, " Arguments:" + getArguments());
            addPreferencesFromResource(R.xml.preference_head_one);
        }
    }

    public static class PrefHeadTwo extends  PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Log.i(TAG, " Arguments:" + getArguments());
            addPreferencesFromResource(R.xml.preference_head_two);
        }
    }

}
