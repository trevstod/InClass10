package com.example.treven.inclass10;

public class Contact{

    String name, eMail, phone;

    public Contact() {
        //Empty Constructor
    }

    public Contact(String name, String eMail, String phone) {
        this.name = name;
        this.eMail = eMail;
        this.phone = phone;
    }

    public String getname() {
        return name;
    }

    public void setname(String lName) {
        this.name = lName;
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
