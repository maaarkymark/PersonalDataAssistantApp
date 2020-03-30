package com.pjboud.personaldataassistant;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageButton;

public class MainMenuActivity extends MainActivity {
    private ImageButton todoBtn;
    private ImageButton workHoursBtn;
    private ImageButton expensesBtn;
    private ImageButton invoiceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_menu);

        // Add project data
        if(!Data.projects.contains("select project"))
            Data.projects.add("select project");


        // Set references to the ImageButtons
        ImageButton todoBtn = (ImageButton) findViewById(R.id.ib_todo);
        ImageButton workHoursBtn = (ImageButton) findViewById(R.id.ib_work_hours);
        ImageButton expensesBtn = (ImageButton) findViewById(R.id.ib_expenses);
        ImageButton invoiceBtn = (ImageButton) findViewById(R.id.ib_invoice);

        // Register Listener events
        todoBtn.setOnClickListener(selectActivity);
        workHoursBtn.setOnClickListener(selectActivity);
        expensesBtn.setOnClickListener(selectActivity);
        invoiceBtn.setOnClickListener(selectActivity);
    }

    private View.OnClickListener selectActivity = new View.OnClickListener() {
        public void onClick(View btn) {
            String button = btn.getContentDescription().toString();
            Intent intent = null;
            switch(button) {
                case("todoBtn"):
                    intent = new Intent(MainMenuActivity.this, TodoActivity.class);
                    break;
                case("workHoursBtn"):
                    intent = new Intent(MainMenuActivity.this, WorkHoursActivity.class);
                    break;
                case("expensesBtn"):
                    intent = new Intent(MainMenuActivity.this, ExpensesActivity.class);
                    break;
                case("invoiceBtn"):
                    intent = new Intent(MainMenuActivity.this, InvoiceActivity.class);
                    break;            }
            startActivity(intent);
        }
    };
}

