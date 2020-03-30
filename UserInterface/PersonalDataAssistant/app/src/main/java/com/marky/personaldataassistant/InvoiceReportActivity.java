package com.marky.personaldataassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Marky on 2017-11-14.
 */

public class InvoiceReportActivity extends OverflowMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoicereport);
    }

    public void goBack(View view) {
        Intent showInvoice = new Intent(InvoiceReportActivity.this, InvoiceActivity.class);
        startActivity(showInvoice);
        finish();
    }

    @Override
    protected void onStart () {
        super.onStart();
        overridePendingTransition(R.anim.settings_in, R.anim.welcome_out2);
    }

}
