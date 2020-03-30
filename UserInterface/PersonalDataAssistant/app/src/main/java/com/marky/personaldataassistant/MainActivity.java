package com.marky.personaldataassistant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * Created by Marky on 2017-11-13.
 */

public class MainActivity extends OverflowMenu {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_main);

        Button backBtn = findViewById(R.id.button6);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showWelcome = new Intent(MainActivity.this, WelcomeScreen.class);
                startActivity(showWelcome);
                finish();
            }
        });
    }

    public void goToDo(View view) {
        Intent showToDo = new Intent(MainActivity.this, ToDoActivity.class);
        startActivity(showToDo);
        finish();
    }

    public void goWorkHours(View view) {
        Intent showWorkHours = new Intent(MainActivity.this, WorkHoursActivity.class);
        startActivity(showWorkHours);
        finish();
    }

    public void goExpenses(View view) {
        Intent showExpenses = new Intent(MainActivity.this, ExpensesActivity.class);
        startActivity(showExpenses);
        finish();
    }

    public void goInvoice(View view) {
        Intent showInvoice = new Intent(MainActivity.this, InvoiceActivity.class);
        startActivity(showInvoice);
        finish();
    }

    @Override
    protected void onStart () {
        super.onStart();
        overridePendingTransition(R.anim.main_in, R.anim.welcome_out1);
    }

}