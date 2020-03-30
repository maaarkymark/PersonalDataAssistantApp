package com.pjboud.personaldataassistant;

/**
 * Created by Marky on 2017-12-04.
 */

public class TODO_Item {

    //MEMBER ATTRIBUTES
    private int projectID;
    private int completed;
    private String task;
    private String due;

    public TODO_Item() {
    }

    public TODO_Item(int is_completed, String task_desc, String date) {
        completed = is_completed;
        task = task_desc;
        due = date;
    }

    public int getProjectId() {
        return projectID;
    }
    public void setProjectId(int id) {
        projectID = id;
    }

    public int getIs_completed() {
        return completed;
    }

    public void setIs_completed(int is_completed) {
        completed = is_completed;
    }

    public String getTask() {
        return task;
    }
    public void setTask(String task_desc) {
        task = task_desc;
    }

    public String getDue() {
        return due;
    }
    public void setDue(String date) {
        due = date;
    }

}
