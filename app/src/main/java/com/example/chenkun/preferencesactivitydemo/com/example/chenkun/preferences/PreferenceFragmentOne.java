package com.example.chenkun.preferencesactivitydemo.com.example.chenkun.preferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import com.example.chenkun.preferencesactivitydemo.R;

/**
 * Preferences Fragment
 *
 * @author sky
 */
public class PreferenceFragmentOne extends PreferenceFragment
        implements SharedPreferences.OnSharedPreferenceChangeListener {
    private static final String TAG = PreferenceFragmentOne.class.getSimpleName();
    private static final String WIFI_KEY = "wifi_setting";
    private static final String NETWORK_KEY = "network_setting";
    private static final String NAME_KEY = "name_setting";
    private static final String LIST_KEY = "list_setting";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //FIXME: the third parameter indicates whether the default value is reset every time
        PreferenceManager.setDefaultValues(getActivity(), R.xml.fragment_one, false);
        addPreferencesFromResource(R.xml.fragment_one);
        getPreferenceValues();
    }

    /**
     * reading Preferences
     */
    private void getPreferenceValues() {
        SharedPreferences spf = PreferenceManager.getDefaultSharedPreferences(getActivity());
        boolean wifiSetting = spf.getBoolean(WIFI_KEY, false);
        boolean networkSetting = spf.getBoolean(NETWORK_KEY, false);
        String nameSetting = spf.getString(NAME_KEY, "sky");
        String listItemSetting = spf.getString(LIST_KEY, "alpha");
        Log.i(TAG, "wifi setting:" + wifiSetting + ", network setting:"
                + networkSetting + ", name setting:" + nameSetting + ", list setting：" + listItemSetting);
        Preference wifiPreference = findPreference(WIFI_KEY);
        Preference networkPreference = findPreference(NETWORK_KEY);
        Preference namePreference = findPreference(NAME_KEY);
        Preference listPreference = findPreference(LIST_KEY);
        wifiPreference.setSummary(wifiSetting ?
                R.string.wifi_summary_on : R.string.wifi_summary_off);
        networkPreference.setSummary(networkSetting ?
                R.string.network_summary_on : R.string.network_summary_off);
        namePreference.setSummary(nameSetting);
        listPreference.setSummary(listItemSetting);

    }

    @Override
    public void onResume() {
        super.onResume();
        //FIXME: listening for preference changes
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference preference = findPreference(key);
        if (TextUtils.equals(WIFI_KEY, key)
                || TextUtils.equals(NETWORK_KEY, key)) {
            Log.i(TAG, "key:" + preference.getKey() + ", value:"
                    + sharedPreferences.getBoolean(key, false) + ", title:" + preference.getTitle()
                    + ", summary:" + preference.getSummary() + "");
            if (TextUtils.equals(WIFI_KEY, key)) {
                preference.setSummary(sharedPreferences.getBoolean(key, false) ?
                        R.string.wifi_summary_on : R.string.wifi_summary_off);
            } else if (TextUtils.equals(NETWORK_KEY, key)) {
                preference.setSummary(sharedPreferences.getBoolean(key, false) ?
                        R.string.network_summary_on : R.string.network_summary_off);
            }
        } else if (TextUtils.equals(NAME_KEY, key)
                || TextUtils.equals(LIST_KEY, key)) {
            Log.i(TAG, "key:" + preference.getKey() + ", value:"
                    + sharedPreferences.getString(key, "sky") + ", title:" + preference.getTitle()
                    + ", summary:" + preference.getSummary() + "");
            preference.setSummary(sharedPreferences.getString(key, "alpha"));
        }

    }
}
