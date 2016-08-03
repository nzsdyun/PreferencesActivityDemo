package com.example.chenkun.preferencesactivitydemo;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.ToggleButton;

import com.example.chenkun.preferencesactivitydemo.com.example.chenkun.preferences.PreferenceFragmentCustom;
import com.example.chenkun.preferencesactivitydemo.com.example.chenkun.preferences.PreferenceFragmentOne;
import com.example.chenkun.preferencesactivitydemo.com.example.chenkun.util.ToastUtil;

public class MainActivity extends Activity {
    private ToggleButton mToggleButton;
    private Switch mSwitch;
    private FrameLayout mPreferenceLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        toggleButtonTest();
    }

    private void toggleButtonTest() {
        mToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    ToastUtil.showToast(MainActivity.this, R.string.toggle_on);
                } else {
                    ToastUtil.showToast(MainActivity.this, R.string.toggle_off);
                }

            }
        });
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    ToastUtil.showToast(MainActivity.this, R.layout.layout_toast, R.string.toggle_on);
                } else {
                    ToastUtil.showToast(MainActivity.this, R.layout.layout_toast, R.string.toggle_off);
                }
            }
        });
    }

    public void Click(View v) {
        switch (v.getId()) {
            case R.id.preference_settings:
                startPreferencesSettings(new PreferenceFragmentOne(), "preference one");
                break;
            case R.id.preference_heads:
                PreferenceWithHeads.startPreferenceHead(this);
                break;
            case R.id.preference_custom:
                startPreferencesSettings(new PreferenceFragmentCustom(), "preference custom");
                break;
        }
    }

    private void startPreferencesSettings(PreferenceFragment fragment, String tag) {
        mPreferenceLayout.setVisibility(View.VISIBLE);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transition = fragmentManager.beginTransaction();
        transition.addToBackStack(tag);
        transition.replace(R.id.content, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mPreferenceLayout.getVisibility() == View.VISIBLE) {
            mPreferenceLayout.setVisibility(View.GONE);
        }
    }

    private void initViews() {
        mToggleButton = (ToggleButton) findViewById(R.id.toggle_button);
        mSwitch = (Switch) findViewById(R.id.switch_button);

        mPreferenceLayout = (FrameLayout) findViewById(R.id.content);
        //FIXME:touch event intercept
        mPreferenceLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
    }
}
