package com.example.meujogodavelhaapp.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceFragmentCompat;

import com.example.meujogodavelhaapp.R;

public class PrefFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferencex);
    }
}