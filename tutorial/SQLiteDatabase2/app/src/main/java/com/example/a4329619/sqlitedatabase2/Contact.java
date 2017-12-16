package com.example.a4329619.sqlitedatabase2;

/**
 * Created by 4329619 on 27/10/2016.
 */
public class Contact {
    public Contact(int id, String name, String contact) {
        this.id = id;
        this.name = name;
        Contact = contact;
    }

    public Contact(String name, String contact) {
        this.name = name;
        Contact = contact;
    }


    public Contact () {

    }
    int id;
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    String Contact;

}
