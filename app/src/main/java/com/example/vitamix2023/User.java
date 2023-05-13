package com.example.vitamix2023;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.vitamix2023.DB.AppDataBase;

@Entity(tableName = AppDataBase.USER_TABLE)
public class User {
    @PrimaryKey(autoGenerate = true)
    private int mUserId;

    private String mUsername;
    private String mPassword;
    private String misAdmin;


    public User(String username, String password, String misAdmin) {
        this.mUsername = username;
        this.mPassword = password;
        this.misAdmin = misAdmin;
    }

    @Override
    public String toString() {
        return "Login{" +
                "mUsername='" + mUsername + '\'' +
                "mPassword='" + mPassword + '\'' +
                "mAdmin = " + misAdmin + '\n' +
                '}';
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getMisAdmin() {
        return misAdmin;
    }

    public void setMisAdmin(String misAdmin) {
        this.misAdmin = misAdmin;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }
}
