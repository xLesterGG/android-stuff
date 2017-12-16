package com.example.lesgo.a4t222;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lesgo on 10/25/2016.
 */
public class ProfileName implements Parcelable{
    private String mName;
    private String mNationality;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmNationality() {
        return mNationality;
    }

    public void setmNationality(String mNationality) {
        this.mNationality = mNationality;
    }

    public ProfileName(String mName, String mNationality) {
        this.mName = mName;
        this.mNationality = mNationality;
    }

    public ProfileName(){
        this.mName ="";
        this.mNationality = "";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.getmName());
        parcel.writeString(this.getmNationality());
    }

    public ProfileName(Parcel in)
    {
        this.mName = in.readString();
        this.mNationality = in.readString();
    }

    public static final Creator<ProfileName> CREATOR = new Creator<ProfileName>(){
        @Override
        public ProfileName createFromParcel(Parcel parcel) {
            return new ProfileName(parcel);
        }

        @Override
        public ProfileName[] newArray(int i) {
            return new ProfileName[i];
        }


    };
}
