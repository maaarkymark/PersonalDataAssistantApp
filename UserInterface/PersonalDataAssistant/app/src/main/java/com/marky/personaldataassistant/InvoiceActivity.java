package com.marky.personaldataassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Marky on 2017-11-14.
 */

public class InvoiceActivity extends OverflowMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
    }

    public void goBack(View view) {
        Intent showMain = new Intent(InvoiceActivity.this, MainActivity.class);
        startActivity(showMain);
        finish();
    }

    public void goInvoiceReport(View view) {
        Intent showInvoiceReport = new Intent(InvoiceActivity.this, InvoiceReportActivity.class);
        startActivity(showInvoiceReport);
        finish();
    }

    @Override
    protected void onStart () {
        super.onStart();
        overridePendingTransition(R.anim.invoice_in, R.anim.main_out2);
    }

}
