package com.pjboud.personaldataassistant;

/**
 * Created by Marky on 2017-12-04.
 */

public class Settings_Item {

    //MEMBER ATTRIBUTES
    private int invoiceNumber;
    private String firstName;
    private String lastName;
    private Float payRate;

    public Settings_Item() {
    }

    public Settings_Item(String fName, String lName, Float rate) {
        firstName = fName;
        lastName = lName;
        payRate = rate;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }
    public void setInvoiceNumber(int id) {
        invoiceNumber = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String fName) {
        firstName = fName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lName) {
        lastName = lName;
    }

    public Float getPayRate() {
        return payRate;
    }
    public void setPayRate(Float rate) {
        payRate = rate;
    }

}
