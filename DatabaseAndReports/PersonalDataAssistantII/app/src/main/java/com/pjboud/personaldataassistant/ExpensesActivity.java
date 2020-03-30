package com.pjboud.personaldataassistant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class ExpensesActivity extends MainActivity {
    Spinner spinner;
    ArrayAdapter dataAdapter;
    ListView listView;
    String selectedProject;
    EXPENSES myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        myDb = new EXPENSES(this);
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
                     //       Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Create Adapter
        final ArrayAdapter<String> expensesAdapter =
                new ArrayAdapter<String>(
                        this,
                        R.layout.activity_expenses_item,
                        R.id.tv_expenses_item,
                        Data.expenses);

        listView = findViewById(R.id.lv_expenses);
        listView.setAdapter(expensesAdapter);

        // ********************************************************
        // Set Button variables
        Button recordBtn = (Button) findViewById(R.id.record);
        Button mainMenuBtn = (Button) findViewById(R.id.main_menu);

        // Set Record Button listener
        recordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view.getContext());

                EditText expenseDesc = findViewById(R.id.et_expense_desc);
                String expenseDescValue = expenseDesc.getText().toString();
                EditText expense = findViewById(R.id.et_expense);
                String expenseValue = expense.getText().toString();

                if (selectedProject == null) {
                    Toast.makeText(getBaseContext(), "You Must Select a Project",
                            Toast.LENGTH_LONG).show();
                } else if(expenseDescValue.matches("")) {
                    Toast.makeText(getBaseContext(), "Enter an Expense Description",
                            Toast.LENGTH_LONG).show();

                } else if (!isPositive(expense)) {
                    Toast.makeText(getBaseContext(), "Enter a PositiveNumber",
                            Toast.LENGTH_LONG).show();
                    expense.setText("");
                } else {
                    Data.expenses.add(expenseDescValue + ": $" + expenseValue);
                    expense.setText("");
                    expenseDesc.setText("");
                    listView.invalidateViews();  // Refresh the ListView
                }
            }
        });

        // Set Main Menu Button listener
        mainMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ExpensesActivity.this, MainMenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
