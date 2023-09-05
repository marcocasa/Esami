package it.uniba.di.sms1819.tourapp;

import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.PreferenceCategory;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

import it.uniba.di.sms1819.tourapp.Models.Category;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override

    public void onCreatePreferences(Bundle bundle, String s) {
        PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(getActivity());
        setPreferenceScreen(screen);

        PreferenceCategory preferenceCategory = new PreferenceCategory(screen.getContext());
        preferenceCategory.setTitle(getResources().getString(R.string.show_in_map));
        screen.addPreference(preferenceCategory);



        for (Category category : Instance.categoryList) {
            CheckBoxPreference checkBoxPref = new CheckBoxPreference(screen.getContext());
            checkBoxPref.setKey(category.id);
            checkBoxPref.setTitle(category.name.get(Common.getLocale()));
            checkBoxPref.setChecked(preferenceCategory.isEnabled());
            preferenceCategory.addPreference(checkBoxPref);
        }
    }
}
