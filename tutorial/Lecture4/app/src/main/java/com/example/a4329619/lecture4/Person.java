package com.example.a4329619.lecture4;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 4329619 on 15/9/2016.
 */
public class Person implements Parcelable{


    String firstName;
    String surName;
    String state;


    public Person(String firstName, String surName, String state) {
        this.firstName = firstName;
        this.surName = surName;
        this.state = state;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String toString(){
        return("Name"+ firstName + surName + "\nState: " + state);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.firstName);
        parcel.writeString(this.surName);
        parcel.writeString(this.state);

    }

    public Person(Parcel in){
        this.firstName= in.readString();
        this.surName = in.readString();
        this.state = in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel parcel) {
            return new Person(parcel);
        }

        @Override
        public Person[] newArray(int i) {
            return new Person[i];
        }
    };


}
