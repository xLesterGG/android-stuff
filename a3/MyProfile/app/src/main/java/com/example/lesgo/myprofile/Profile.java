package com.example.lesgo.myprofile;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by lesgo on 9/29/2016.
 */
public class Profile implements Parcelable{

    String name;
    String profession;
    String contact;
    Boolean bat=false;

    public Profile(String name, String profession, String contact, Boolean bate) {
        this.name = name;
        this.profession = profession;
        this.contact = contact;
        this.bat = bate;

        Log.d("LOG111",bate.toString());


    }

    public Profile() {
        this.name = "";
        this.profession = "";
        this.contact = "";
        this.bat = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Boolean getBat() {
        return bat;
    }

    public void setBat(Boolean bat) {
        this.bat = bat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {

        parcel.writeString(this.name);
        parcel.writeString(this.profession);
        parcel.writeString(this.contact);
        parcel.writeByte((byte) (bat ? 1 : 0));

    }

    public Profile(Parcel in)
    {
        this.name= in.readString();
        this.profession = in.readString();
        this.contact = in.readString();
        this.bat = in.readByte() != 0;
    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>(){
        @Override
        public Profile createFromParcel(Parcel parcel) {
            return new Profile(parcel);
        }

        @Override
        public Profile[] newArray(int i) {
            return new Profile[i];
        }


    };
}
