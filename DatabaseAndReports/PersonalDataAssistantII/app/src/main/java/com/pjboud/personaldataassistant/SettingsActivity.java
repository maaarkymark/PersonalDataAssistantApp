package com.pjboud.personaldataassistant;

import android.os.Bundle;

public class SettingsActivity extends MainActivity {

    Settings myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        myDb = new Settings(this);
    }
}
