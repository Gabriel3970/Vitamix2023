package com.example.vitamix2023.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.vitamix2023.User;
import com.example.vitamix2023.Vitamin;

import java.util.List;

@Dao
public interface LoginDAO {

    @Insert
    void insert(User... users);

    @Update
    void update(User... users);

    @Delete
    void delete(User users);

    @Query("SELECT * FROM " + AppDataBase.USER_TABLE + " ORDER BY mUserId DESC")
    List<User> getAllUsers();

    @Query("SELECT * FROM " + AppDataBase.USER_TABLE + " WHERE mUsername = :username")
    List<User> getUser(String username);

    @Query("SELECT * FROM " + AppDataBase.USER_TABLE + " WHERE mUserId = :Id")
    List<User> getUserbyID(int Id);

    @Query(" SELECT * FROM " + AppDataBase.USER_TABLE + " WHERE mUserId = :Id")
    User getUserId(int Id);

    @Query(" SELECT * FROM " + AppDataBase.VITAMINS_TABLE + " WHERE mVitaminId = :Id")
    List<Vitamin> getVitaminbyID(int Id);

    @Query(" SELECT * FROM " + AppDataBase.VITAMINS_TABLE)
    List<Vitamin> getAllVitamins();

    @Insert
    void insert(Vitamin... vitamins);

    @Update
    void update(Vitamin... vitamins);

    @Delete
    void delete(Vitamin... vitamins);

}
