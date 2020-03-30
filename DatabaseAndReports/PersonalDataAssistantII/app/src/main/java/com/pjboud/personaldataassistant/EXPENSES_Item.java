package com.pjboud.personaldataassistant;

/**
 * Created by Marky on 2017-12-04.
 */

public class EXPENSES_Item {

    //MEMBER ATTRIBUTES
    private int projectID;
    private String date;
    private String description;
    private Float amount;

    public EXPENSES_Item() {
    }

    public EXPENSES_Item(String date_desc, String desc, Float total) {
        date = date_desc;
        description = desc;
        amount = total;
    }

    public int getProjectId() {
        return projectID;
    }
    public void setProjectId(int id) {
        projectID = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date_desc) {
        date = date_desc;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String desc) {
        description = desc;
    }

    public Float getAmount() {
        return amount;
    }
    public void setAmount(Float total) {
        amount = total;
    }

}
