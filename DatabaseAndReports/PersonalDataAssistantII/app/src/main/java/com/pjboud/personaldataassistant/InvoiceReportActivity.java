package com.pjboud.personaldataassistant;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class InvoiceReportActivity extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_invoice_report);
    }

    @Override
    public void onStart() {
        super.onStart();

        // Create HOURS Adapter
        final ArrayAdapter<String> invoiceHoursAdapter =
                new ArrayAdapter<String>(
                        this,
                        R.layout.activity_invoice_labour_items,
                        R.id.labour_desc,
                        Data.workHours);

        ListView hoursListView = findViewById(R.id.lv_invoice_labour);
        hoursListView.setAdapter(invoiceHoursAdapter);

        // Create Expense Adapter
        final ArrayAdapter<String> invoiceExpenseAdapter =
                new ArrayAdapter<String>(
                        this,
                        R.layout.activity_invoice_expense_items,
                        R.id.expense_desc,
                        Data.expenses);

        ListView expensesListView = findViewById(R.id.lv_invoice_expenses);
        expensesListView.setAdapter(invoiceExpenseAdapter);
    }
}
