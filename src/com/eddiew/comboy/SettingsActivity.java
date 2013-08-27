package com.eddiew.comboy;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

public class SettingsActivity extends PreferenceActivity {//update this to preferenceFragment some time
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//load prefs from XML
		addPreferencesFromResource(R.xml.preferences);
		
	}
}
