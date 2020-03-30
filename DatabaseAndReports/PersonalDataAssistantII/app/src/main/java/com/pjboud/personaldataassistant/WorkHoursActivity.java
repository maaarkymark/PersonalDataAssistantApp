package com.pjboud.personaldataassistant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class WorkHoursActivity extends MainActivity {

    String selectedProject;
    DatePicker datepicker; // object for datepicker
    int year , month , day;  // declaring variables for year, month and day
    TimePicker timepicker1; // object for from timepicker
    TimePicker timepicker2; // object for to timepicker
    Spinner spinner;
    ArrayAdapter dataAdapter;
    ListView listView;
    HOURS myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_work_hours);
        myDb = new HOURS(this);
    }

    @Override
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

        // Create Adapter
        final ArrayAdapter<String> workHoursAdapter =
                new ArrayAdapter<String>(
                        this,
                        R.layout.activity_work_hours_item,
                        R.id.tv_work_hours_item,
                        Data.workHours);

        listView = findViewById(R.id.lv_work_hours);
        listView.setAdapter(workHoursAdapter);

        // ********************************************************
        // Set DatePicker, TimePicker and Button variables
        datepicker = (DatePicker) findViewById(R.id.datePicker);
        timepicker1 = (TimePicker) findViewById(R.id.timePicker);
        timepicker2 = (TimePicker) findViewById(R.id.timePicker2);
        timepicker1.setIs24HourView(true);
        timepicker2.setIs24HourView(true);

        Button recordBtn = (Button) findViewById(R.id.record);
        Button mainMenuBtn = (Button) findViewById(R.id.main_menu);

        // Set Record Button listener
        recordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view.getContext());
                EditText workDesc = findViewById(R.id.et_work_desc);
                String workDescValue = workDesc.getText().toString();
                String date = datepicker.getDayOfMonth() + "-" +
                        datepicker.getMonth() + "-" +
                        datepicker.getYear();
                Double fromTime = (timepicker1.getCurrentHour() + (timepicker1.getCurrentMinute() / 60.0));
                Double toTime = (timepicker2.getCurrentHour() + (timepicker2.getCurrentMinute() / 60.0));
                Double hours =  round((toTime - fromTime), 1);

                if(workDescValue.matches("")) {
                    Toast.makeText(getBaseContext(), "Enter a Work Description", Toast.LENGTH_LONG).show();
                } else if (selectedProject == null) {
                    Toast.makeText(getBaseContext(), "You Must Select a Project", Toast.LENGTH_LONG).show();
                } else if (toTime <= fromTime) {
                    Toast.makeText(getBaseContext(), "Start time muste be before End time", Toast.LENGTH_LONG).show();
                } else {
                    Data.workHours.add(date + " " + workDescValue + " " + hours.toString() );
                    workDesc.setText("");
                    listView.invalidateViews();  // Reftresh the ListView
                }
            }
        });

        // Set Main Menu Button listener
        mainMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(WorkHoursActivity.this, MainMenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
