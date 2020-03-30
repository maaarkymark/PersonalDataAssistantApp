package com.pjboud.personaldataassistant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

public class InvoiceActivity extends MainActivity {
    Spinner spinner;
    ArrayAdapter dataAdapter;
    String selectedProject;
    DatePicker datePicker1;
    DatePicker datePicker2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    public void onStart() {
        super.onStart();

        // ******* Project Spinner ***********************************
        // get Spinner reference
        spinner = findViewById(R.id.spinner);

        // Create array adapter for spinner
        dataAdapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                Data.projects);

        // Drop down style will be listview with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);

        // Attach data adapter to spinner
        spinner.setAdapter(dataAdapter);

        // Set Listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!parent.getItemAtPosition(position).toString().equals("select project")) {
                    selectedProject = parent.getItemAtPosition(position).toString();
                    //Toast.makeText(getBaseContext(), parent.getItemAtPosition(position).toString(),
                    //        Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // ********************************************************
        // Set DatePicker, TimePicker and Button variables
        datePicker1 = (DatePicker) findViewById(R.id.datePicker1);
        datePicker2 = (DatePicker) findViewById(R.id.datePicker2);

        // Set Button variables
        Button createInvoiceBtn = (Button) findViewById(R.id.create_invoice);

        // Set Record Button listener
        createInvoiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Integer startDate = datePicker1.getYear()
                         + datePicker1.getMonth()
                         + datePicker1.getDayOfMonth();
                 Integer endDate = datePicker2.getYear()
                         + datePicker2.getMonth()
                         + datePicker2.getDayOfMonth();

                if (selectedProject == null) {
                    Toast.makeText(getBaseContext(), "You Must Select a Project",
                            Toast.LENGTH_LONG).show();
                } else if (endDate <= startDate) {
                    Toast.makeText(getBaseContext(), "Start Date must be before End Date",
                            Toast.LENGTH_LONG).show();
                } else {

                    // pass selected project to next activity

                    // pass date range to next activity

                    // startActivity(intent) to InvoiceReportActivity()
                    Intent intent = new Intent(InvoiceActivity.this, InvoiceReportActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}
