package com.pjboud.personaldataassistant;

/**
 * Created by Marky on 2017-12-04.
 */

public class HOURS_Item {

    //MEMBER ATTRIBUTES
    private int projectID;
    private String start;
    private String end;
    private String description;

    public HOURS_Item() {
    }

    public HOURS_Item(String start_time, String end_time, String desc) {
        start = start_time;
        end = end_time;
        description = desc;
    }

    public int getProjectId() {
        return projectID;
    }
    public void setProjectId(int id) {
        projectID = id;
    }

    public String getStartTime() {
        return start;
    }

    public void setStartTime(String start_time) {
        start = start_time;
    }

    public String getEndTime() {
        return end;
    }
    public void setEndTime(String end_time) {
        end = end_time;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String desc) {
        description = desc;
    }

}
