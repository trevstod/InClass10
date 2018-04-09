package com.example.treven.inclass10;


import java.util.ArrayList;

public class Contact{

    String fName, lName, eMail, phone;

    public Contact(String fName, String lName, String eMail, String phone) {
        this.fName = fName;
        this.lName = lName;
        this.eMail = eMail;
        this.phone = phone;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
