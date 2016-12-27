package com.melvin.preferencescreenfull;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.content.SharedPreferences;

/**
 * Created by Melvin on 2016/12/27.
 */

public class MySetting extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private EditTextPreference mEtPreference;
    private ListPreference mListPreference;
    private CheckBoxPreference mCheckPreference;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        addPreferencesFromResource(R.xml.preference);
        initPreference();
    }

    private void initPreference() {
        mEtPreference = (EditTextPreference) findPreference(Consts.EDIT_KEY);
        mListPreference = (ListPreference)findPreference(Consts.LIST_KEY);
        mCheckPreference = (CheckBoxPreference)findPreference(Consts.CHECKOUT_KEY);
    }
    @Override
    protected void onResume() {
        super.onResume();

        // Setup the initial values
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        mListPreference.setSummary(sharedPreferences.getString(Consts.LIST_KEY, ""));
        mEtPreference.setSummary(sharedPreferences.getString(Consts.EDIT_KEY, "linc"));

        // Set up a listener whenever a key changes
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister the listener whenever a key changes
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(Consts.EDIT_KEY)) {
            mEtPreference.setSummary(
                    sharedPreferences.getString(key, "20"));
        } else if(key.equals(Consts.LIST_KEY)) {
            mListPreference.setSummary(sharedPreferences.getString(key, ""));
        }
    }
}
