package com.example.treven.inclass10;

import java.io.Serializable;

/**
 * Created by Treven on 4/9/18.
 */

public class Contact implements Serializable {

    String name, email, phone, image;

    public Contact(){

    }

    public Contact(String name, String email, String phone, String image) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
