package com.example.aquatracker.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 6/26/2017.
 */

public class User implements Parcelable{

    private static final String TAG = "User";

    private String address;
    private String deviceno;
    private String email;
    private String password;
    private String undergroundLevel;
    private String overheadLevel;
    private String mainLevel;

    public User(String address, String deviceno, String email, String password, String undergroundLevel, String overheadLevel, String mainLevel) {
        this.address = address;
        this.deviceno = deviceno;
        this.email = email;
        this.password = password;
        this.undergroundLevel = undergroundLevel;
        this.overheadLevel = overheadLevel;
        this.mainLevel = mainLevel;
    }

    protected User(Parcel in) {
        address = in.readString();
        deviceno = in.readString();
        email = in.readString();
        password = in.readString();
        undergroundLevel = in.readString();
        overheadLevel = in.readString();
        mainLevel = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public static String getTAG() {
        return TAG;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeviceno() {
        return deviceno;
    }

    public void setDeviceno(String deviceno) {
        this.deviceno = deviceno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUndergroundLevel() {
        return undergroundLevel;
    }

    public void setUndergroundLevel(String undergroundLevel) {
        this.undergroundLevel = undergroundLevel;
    }

    public String getOverheadLevel() {
        return overheadLevel;
    }

    public void setOverheadLevel(String overheadLevel) {
        this.overheadLevel = overheadLevel;
    }

    public String getMainLevel() {
        return mainLevel;
    }

    public void setMainLevel(String mainLevel) {
        this.mainLevel = mainLevel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(address);
        dest.writeString(deviceno);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(undergroundLevel);
        dest.writeString(overheadLevel);
        dest.writeString(mainLevel);
    }
}
