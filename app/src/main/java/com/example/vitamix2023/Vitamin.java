package com.example.vitamix2023;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.vitamix2023.DB.AppDataBase;

@Entity(tableName = AppDataBase.VITAMINS_TABLE)
public class Vitamin {
    @PrimaryKey(autoGenerate = true)
    private int mVitaminId;

    private String mName;
    private String mFocus;
    private String mPrice;

    private int mUserId;

    public Vitamin(String name, String focus, String price) {
        this.mName = name;
        this.mFocus = focus;
        this.mPrice = price;
    }



    @Override
    public String toString() {
        return mName + '\'' +
                "Focus= " + mFocus + '\'' +
                "Price= " + mPrice + '\'';
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

    public int getVitaminId() {
        return mVitaminId;
    }

    public void setVitaminId(int vitaminId) {
        mVitaminId = vitaminId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getFocus() {
        return mFocus;
    }

    public void setFocus(String focus) {
        mFocus = focus;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }
}
