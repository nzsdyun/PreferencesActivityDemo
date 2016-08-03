package com.example.chenkun.preferencesactivitydemo.com.example.chenkun.preferences;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by sky on 8/3/2016.
 */
public class PreferenceFragmentCustom extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        addPreferencesFromResource(R.xml.preference_custom);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout parent = new LinearLayout(getActivity());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        PreferenceCustom preferenceCustom = new PreferenceCustom(getActivity());
        preferenceCustom.setChecked(true);
        preferenceCustom.setKey("custom_setting");
        parent.addView(preferenceCustom.getView(null, null), lp);
        return parent;
    }
}
