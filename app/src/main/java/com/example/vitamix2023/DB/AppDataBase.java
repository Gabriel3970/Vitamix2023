package com.example.vitamix2023.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.vitamix2023.User;
import com.example.vitamix2023.Vitamin;

@Database(entities = {User.class, Vitamin.class}, version=3)
public abstract class AppDataBase extends RoomDatabase {
    public static final String DATABASE_NAME = "UserLog.db";
    public static final String USER_TABLE = "userlog_table";
    public static final String VITAMINS_TABLE = "vitamins_table";



    private static volatile AppDataBase instance;
    private static volatile AppDataBase VitaminsInstance;
    private static final Object LOCK = new Object();

    public abstract LoginDAO LoginDAO();


    public static AppDataBase getInstance(Context context){
        if(instance == null){
            synchronized (LOCK){
                if(instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class,DATABASE_NAME).build();
                }
            }
        }
        if(VitaminsInstance == null){
            synchronized (LOCK){
                if(VitaminsInstance == null){
                    VitaminsInstance = Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class,VITAMINS_TABLE).build();
                }
            }
        }
        return instance;
    }

}
