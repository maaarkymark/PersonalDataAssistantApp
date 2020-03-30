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

public class Project extends SQLiteOpenHelper {

    //TASK 1: DEFINE THE DATABASE AND TABLE
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "personalDataAssistantII";
    private static final String DATABASE_TABLE = "project_Items";


    //TASK 2: DEFINE THE COLUMN NAMES FOR THE TABLE
    private static final String PROJECT_ID = "projectID";
    private static final String PROJECT_NAME = "projectName";

    private int projectCount;

    public Project(Context context){
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String table = " CREATE TABLE " + DATABASE_TABLE + "("
                + PROJECT_ID + " INTEGER FOREIGN KEY AUTOINCREMENT, "
                + PROJECT_NAME + " TEXT, " + ")";

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
    // Adding new project
    public void addProjectItem(Project_Item project) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //ADD KEY-VALUE PAIR INFORMATION FOR THE PROJECT NAME
        values.put(PROJECT_NAME, project.getProjectName()); // project name

        // INSERT THE ROW IN THE TABLE
        db.insert(DATABASE_TABLE, null, values); // table, null, valuesToInsert
        projectCount++;

        // CLOSE THE DATABASE CONNECTION
        db.close();
    }

    public List<Project_Item> getAllTasks() {

        //GET ALL THE PROJECT ITEMS ON THE LIST
        List<Project_Item> projectList = new ArrayList<Project_Item>();

        //SELECT ALL QUERY FROM THE TABLE
        String selectQuery = "SELECT  * FROM " + DATABASE_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null); // sql, sqlargs, throwError

        // LOOP THROUGH THE PROJECTS
        if (cursor.moveToFirst()) {
            do {
                Project_Item project = new Project_Item();
                project.setProjectId(cursor.getInt(0));
                project.setProjectName(cursor.getString(1));
                projectList.add(project);
            } while (cursor.moveToNext());
        }

        // RETURN THE LIST OF PROJECTS FROM THE TABLE
        return projectList;
    }

    public void clearAll(List<Project_Item> list) {
        //GET ALL THE LIST PROJECT ITEMS AND CLEAR THEM
        list.clear();

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE, null, new String[]{}); // db, whereclause, where arguments
        db.close();
    }

    public void updateProject(Project_Item project) {
        // updating row
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PROJECT_NAME, project.getProjectName());
        db.update(DATABASE_TABLE, values, PROJECT_ID + " = ?", new String[]{String.valueOf(project.getProjectId())}); // table, values, where, whereargs
        db.close();
    }

}
