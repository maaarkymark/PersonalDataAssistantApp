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

public class EXPENSES extends SQLiteOpenHelper {

    //TASK 1: DEFINE THE DATABASE AND TABLE
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "personalDataAssistantII";
    private static final String DATABASE_TABLE = "expenses_Items";


    //TASK 2: DEFINE THE COLUMN NAMES FOR THE TABLE
    private static final String PROJECT_ID = "projectID";
    private static final String DATE = "date";
    private static final String DESCRIPTION = "description";
    private static final String AMOUNT = "amount";

    private int expensesCount;

    public EXPENSES(Context context){
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String table = " CREATE TABLE " + DATABASE_TABLE + "("
                + PROJECT_ID + " INTEGER FOREIGN KEY AUTOINCREMENT, " + DATE + " TEXT, "
                + DESCRIPTION + " TEXT, " + AMOUNT + " REAL, " + ")";

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
    // Adding new expense
    public void addExpensesItem(EXPENSES_Item expense) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //ADD KEY-VALUE PAIR INFORMATION FOR DATE
        values.put(DATE, expense.getDate()); // date

        //ADD KEY-VALUE PAIR INFORMATION FOR DESCRIPTION
        values.put(DESCRIPTION, expense.getDescription()); // description

        //ADD KEY-VALUE PAIR INFORMATION FOR AMOUNT
        values.put(AMOUNT, expense.getAmount()); // amount

        // INSERT THE ROW IN THE TABLE
        db.insert(DATABASE_TABLE, null, values); // table, null, valuesToInsert
        expensesCount++;

        // CLOSE THE DATABASE CONNECTION
        db.close();
    }

    public List<EXPENSES_Item> getAllExpenses() {

        //GET ALL EXPENSES ITEMS ON THE LIST
        List<EXPENSES_Item> expensesList = new ArrayList<EXPENSES_Item>();

        //SELECT ALL QUERY FROM THE TABLE
        String selectQuery = "SELECT  * FROM " + DATABASE_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null); // sql, sqlargs, throwError

        // LOOP THROUGH THE EXPENSES LIST
        if (cursor.moveToFirst()) {
            do {
                EXPENSES_Item expense = new EXPENSES_Item();
                expense.setProjectId(cursor.getInt(0));
                expense.setDate(cursor.getString(1));
                expense.setDescription(cursor.getString(2));
                expense.setAmount(cursor.getFloat(3));
                expensesList.add(expense);
            } while (cursor.moveToNext());
        }

        // RETURN THE LIST OF EXPENSES FROM THE TABLE
        return expensesList;
    }

    public void clearAll(List<EXPENSES_Item> list) {
        //GET ALL THE LIST EXPENSES ITEMS AND CLEAR THEM
        list.clear();

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE, null, new String[]{}); // db, whereclause, where arguments
        db.close();
    }

    public void updateExpense(EXPENSES_Item expense) {
        // updating row
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DATE, expense.getDate());
        values.put(DESCRIPTION, expense.getDescription());
        values.put(AMOUNT, expense.getAmount());
        db.update(DATABASE_TABLE, values, PROJECT_ID + " = ?", new String[]{String.valueOf(expense.getProjectId())}); // table, values, where, whereargs
        db.close();
    }

}
