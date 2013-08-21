package com.eddiew.comboy;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class SettingsFragment extends PreferenceFragment {
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//load prefs from XML
		addPreferencesFromResource(R.xml.preferences);
		
	}
}
