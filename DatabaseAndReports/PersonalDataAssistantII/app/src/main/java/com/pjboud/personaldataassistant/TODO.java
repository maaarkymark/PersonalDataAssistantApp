package com.pjboud.personaldataassistant;

/**
 * Created by Marky on 2017-12-04.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class TODO extends SQLiteOpenHelper {

    //TASK 1: DEFINE THE DATABASE AND TABLE
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "personalDataAssistantII";
    private static final String DATABASE_TABLE = "toDo_Items";

    //TASK 2: DEFINE THE COLUMN NAMES FOR THE TABLE
    private static final String PROJECT_ID = "projectID";
    private static final String IS_COMPLETED = "completed";
    private static final String TASK = "task";
    private static final String DUE = "due";

    private int toDoCount;

    public TODO(Context context){
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String table = " CREATE TABLE " + DATABASE_TABLE + "("
                + PROJECT_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + IS_COMPLETED + " INTEGER, "
                + TASK + " TEXT, " + DUE + " TEXT, " + ")";

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
    // Adding new task
    public void addToDoItem(TODO_Item task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //ADD KEY-VALUE PAIR INFORMATION FOR
        //IS_COMPLETED VALUE: 0- NOT COMPLETED, 1 - IS COMPLETED
        values.put(IS_COMPLETED, task.getIs_completed());

        //ADD KEY-VALUE PAIR INFORMATION FOR THE TASK DESCRIPTION
        values.put(TASK, task.getTask()); // task name

        //ADD KEY-VALUE PAIR INFORMATION FOR DUE
        values.put(DUE, task.getDue()); // due

        // INSERT THE ROW IN THE TABLE
        db.insert(DATABASE_TABLE, null, values); // table, null, valuesToInsert
        toDoCount++;

        // CLOSE THE DATABASE CONNECTION
        db.close();
    }

    public List<TODO_Item> getAllToDo() {

        //GET ALL THE TODO ITEMS ON THE LIST
        List<TODO_Item> todoList = new ArrayList<TODO_Item>();

        //SELECT ALL QUERY FROM THE TABLE
        String selectQuery = "SELECT  * FROM " + DATABASE_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null); // sql, sqlargs, throwError

        // LOOP THROUGH THE TODO TASKS
        if (cursor.moveToFirst()) {
            do {
                TODO_Item task = new TODO_Item();
                task.setProjectId(cursor.getInt(0));
                task.setIs_completed(cursor.getInt(1));
                task.setTask(cursor.getString(2));
                task.setDue(cursor.getString(3));
                todoList.add(task);
            } while (cursor.moveToNext());
        }

        // RETURN THE LIST OF TODO FROM THE TABLE
        return todoList;
    }

    public void clearAll(List<TODO_Item> list) {
        //GET ALL THE LIST TODO ITEMS AND CLEAR THEM
        list.clear();

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE, null, new String[]{}); // db, whereclause, where arguments
        db.close();
    }

    public void updateToDo(TODO_Item task) {
        // updating row
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(IS_COMPLETED, task.getIs_completed());
        values.put(TASK, task.getTask());
        values.put(DUE, task.getDue());
        db.update(DATABASE_TABLE, values, PROJECT_ID + " = ?", new String[]{String.valueOf(task.getProjectId())}); // table, values, where, whereargs
        db.close();
    }

}

