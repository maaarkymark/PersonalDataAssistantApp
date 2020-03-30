package com.marky.personaldataassistant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Marky on 2017-11-14.
 */

public class WorkHoursActivity extends OverflowMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workhours);
    }

    public void goBack(View view) {
        Intent showMain = new Intent(WorkHoursActivity.this, MainActivity.class);
        startActivity(showMain);
        finish();
    }

    @Override
    protected void onStart () {
        super.onStart();
        overridePendingTransition(R.anim.workhours_in, R.anim.main_out2);
    }

}
