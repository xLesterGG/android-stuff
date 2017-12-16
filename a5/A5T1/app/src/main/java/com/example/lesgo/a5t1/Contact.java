package com.example.lesgo.a5t1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lesgo on 10/31/2016.
 */
public class Contact implements Parcelable{
    int id;
    String name;
    String contact;
    String email;

    public Contact(int id, String name, String contact, String email) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.email = email;
    }

    public Contact(String name, String contact, String email) {
        this.name = name;
        this.contact = contact;
        this.email = email;
    }

    public Contact() {
    }

    public Contact(int id) {
        this.id = id;
    }

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
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.contact);
        parcel.writeString(this.email);

    }

    public Contact(Parcel in){
        this.id= in.readInt();
        this.name = in.readString();
        this.contact = in.readString();
        this.email = in.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>(){
        @Override
        public Contact createFromParcel(Parcel parcel) {
            return new Contact(parcel);
        }

        @Override
        public Contact[] newArray(int i) {
            return new Contact[i];
        }

    };
}
