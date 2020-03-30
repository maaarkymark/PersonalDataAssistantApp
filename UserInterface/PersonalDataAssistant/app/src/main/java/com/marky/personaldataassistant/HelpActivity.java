package com.marky.personaldataassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Marky on 2017-11-14.
 */

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }

    public void goBack(View view) {
        Intent showHelp = new Intent(HelpActivity.this, WelcomeScreen.class);
        startActivity(showHelp);
        finish();
    }

    @Override
    protected void onStart () {
        super.onStart();
        overridePendingTransition(R.anim.help_in, R.anim.welcome_out2);
    }

}
