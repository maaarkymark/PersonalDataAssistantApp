package com.pjboud.personaldataassistant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marky on 2017-12-04.
 */

public class Settings extends SQLiteOpenHelper {

    //TASK 1: DEFINE THE DATABASE AND TABLE
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "personalDataAssistantII";
    private static final String DATABASE_TABLE = "settings_Items";


    //TASK 2: DEFINE THE COLUMN NAMES FOR THE TABLE
    private static final String INVOICE_NUMBER = "invoiceNumber";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String PAY_RATE = "payRate";

    private int settingsCount;

    public Settings(Context context){
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String table = " CREATE TABLE " + DATABASE_TABLE + "("
                + INVOICE_NUMBER +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + FIRST_NAME + " TEXT, "
                + LAST_NAME + " TEXT, " + PAY_RATE + " REAL, " + ")";

        db.execSQL(table); // execute a query string
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        // DROP OLDER TABLE IF EXISTS
        database.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);

        // CREATE TABLE AGAIN
        onCreate(database);
    }


    //********** DATABASE OPERATIONS:  ADD, EDIT, DELETE
    // Adding new settings
    public void addSettingsItem(Settings_Item settings) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //ADD KEY-VALUE PAIR INFORMATION FOR FIRST_NAME
        values.put(FIRST_NAME, settings.getFirstName()); // first name

        //ADD KEY-VALUE PAIR INFORMATION FOR LAST_NAME
        values.put(LAST_NAME, settings.getLastName()); // last name

        //ADD KEY-VALUE PAIR INFORMATION FOR PAY_RATE
        values.put(PAY_RATE, settings.getPayRate()); // pay rate

        // INSERT THE ROW IN THE TABLE
        db.insert(DATABASE_TABLE, null, values); // table, null, valuesToInsert
        settingsCount++;

        // CLOSE THE DATABASE CONNECTION
        db.close();
    }

    public List<Settings_Item> getAllSettings() {

        //GET ALL THE SETTINGS ITEMS ON THE LIST
        List<Settings_Item> settingsList = new ArrayList<Settings_Item>();

        //SELECT ALL QUERY FROM THE TABLE
        String selectQuery = "SELECT  * FROM " + DATABASE_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null); // sql, sqlargs, throwError

        // LOOP THROUGH THE Settings List
        if (cursor.moveToFirst()) {
            do {
                Settings_Item settings = new Settings_Item();
                settings.setInvoiceNumber(cursor.getInt(0));
                settings.setFirstName(cursor.getString(1));
                settings.setLastName(cursor.getString(2));
                settings.setPayRate(cursor.getFloat(3));
                settingsList.add(settings);
            } while (cursor.moveToNext());
        }

        // RETURN THE LIST OF SETTINGS FROM THE TABLE
        return settingsList;
    }

    public void clearAll(List<Settings_Item> list) {
        //GET ALL THE LIST SETTINGS ITEMS AND CLEAR THEM
        list.clear();

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE, null, new String[]{}); // db, whereclause, where arguments
        db.close();
    }

    public void updateSettings(Settings_Item settings) {
        // updating row
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIRST_NAME, settings.getFirstName());
        values.put(LAST_NAME, settings.getLastName());
        values.put(PAY_RATE, settings.getPayRate());
        db.update(DATABASE_TABLE, values, INVOICE_NUMBER + " = ?", new String[]{String.valueOf(settings.getInvoiceNumber())}); // table, values, where, whereargs
        db.close();
    }

}
