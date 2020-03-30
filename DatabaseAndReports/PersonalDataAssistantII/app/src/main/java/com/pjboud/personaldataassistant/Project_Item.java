package com.pjboud.personaldataassistant;

/**
 * Created by Marky on 2017-12-04.
 */

public class Project_Item {

    //MEMBER ATTRIBUTES
    private int projectID;
    private String projectName;

    public Project_Item() {
    }

    public Project_Item(String name) {
        projectName = name;
    }

    public int getProjectId() {
        return projectID;
    }
    public void setProjectId(int id) {
        projectID = id;
    }

    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String name) {
        projectName = name;
    }

}
