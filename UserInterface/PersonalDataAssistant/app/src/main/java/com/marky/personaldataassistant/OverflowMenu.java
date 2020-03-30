package com.marky.personaldataassistant;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class OverflowMenu extends AppCompatActivity {

    final Context context = this;

    public void goSettings(View view) {
        Intent showSettings = new Intent(OverflowMenu.this, SettingsActivity.class);
        startActivity(showSettings);
        finish();
    }

    public void goHelp(View view) {
        Intent showHelp = new Intent(OverflowMenu.this, HelpActivity.class);
        startActivity(showHelp);
        finish();
    }

    public void showPopUp(View view) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set title
        alertDialogBuilder.setTitle(R.string.ui_action_exit);

        // set dialog message
        alertDialogBuilder
                .setMessage(R.string.exit_question)
                .setCancelable(false)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity
                        OverflowMenu.this.finish();
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            goSettings(findViewById(R.id.action_settings));
            return true;
        }

        else if (id == R.id.action_help) {
            goHelp(findViewById(R.id.action_help));
            return true;
        }
        else if (id == R.id.action_exit) {
            // CLOSE ACTIVITY
            showPopUp(findViewById(R.id.action_exit));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
