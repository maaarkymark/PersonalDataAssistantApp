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

public class HOURS extends SQLiteOpenHelper {

    //TASK 1: DEFINE THE DATABASE AND TABLE
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "personalDataAssistantII";
    private static final String DATABASE_TABLE = "hours_Items";


    //TASK 2: DEFINE THE COLUMN NAMES FOR THE TABLE
    private static final String PROJECT_ID = "projectID";
    private static final String START_TIME = "start";
    private static final String END_TIME = "end";
    private static final String DESCRIPTION = "description";

    private int hoursCount;

    public HOURS(Context context){
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String table = " CREATE TABLE " + DATABASE_TABLE + "("
                + PROJECT_ID + " INTEGER FOREIGN KEY AUTOINCREMENT, " + START_TIME + " TEXT, "
                + END_TIME + " TEXT, " + DESCRIPTION + " TEXT, " + ")";

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
    // Adding new hours
    public void addHoursItem(HOURS_Item hours) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //ADD KEY-VALUE PAIR INFORMATION FOR START_TIME
        values.put(START_TIME, hours.getStartTime()); // start time

        //ADD KEY-VALUE PAIR INFORMATION FOR END_TIME
        values.put(END_TIME, hours.getEndTime()); // end time

        //ADD KEY-VALUE PAIR INFORMATION FOR DESCRIPTION
        values.put(DESCRIPTION, hours.getDescription()); // description

        // INSERT THE ROW IN THE TABLE
        db.insert(DATABASE_TABLE, null, values); // table, null, valuesToInsert
        hoursCount++;

        // CLOSE THE DATABASE CONNECTION
        db.close();
    }

    public List<HOURS_Item> getAllHours() {

        //GET ALL THE HOURS ITEMS ON THE LIST
        List<HOURS_Item> hoursList = new ArrayList<HOURS_Item>();

        //SELECT ALL QUERY FROM THE TABLE
        String selectQuery = "SELECT  * FROM " + DATABASE_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null); // sql, sqlargs, throwError

        // LOOP THROUGH THE HOURS LIST
        if (cursor.moveToFirst()) {
            do {
                HOURS_Item hours = new HOURS_Item();
                hours.setProjectId(cursor.getInt(0));
                hours.setStartTime(cursor.getString(1));
                hours.setEndTime(cursor.getString(2));
                hours.setDescription(cursor.getString(3));
                hoursList.add(hours);
            } while (cursor.moveToNext());
        }

        // RETURN THE LIST OF HOURS FROM THE TABLE
        return hoursList;
    }

    public void clearAll(List<HOURS_Item> list) {
        //GET ALL THE LIST HOURS ITEMS AND CLEAR THEM
        list.clear();

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE, null, new String[]{}); // db, whereclause, where arguments
        db.close();
    }

    public void updateHours(HOURS_Item hours) {
        // updating row
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(START_TIME, hours.getStartTime());
        values.put(END_TIME, hours.getEndTime());
        values.put(DESCRIPTION, hours.getDescription());
        db.update(DATABASE_TABLE, values, PROJECT_ID + " = ?", new String[]{String.valueOf(hours.getProjectId())}); // table, values, where, whereargs
        db.close();
    }

}
