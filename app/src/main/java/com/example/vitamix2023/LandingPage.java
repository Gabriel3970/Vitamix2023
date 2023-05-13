package com.example.vitamix2023;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.vitamix2023.DB.AppDataBase;
import com.example.vitamix2023.DB.LoginDAO;
import com.example.vitamix2023.databinding.LandingPageBinding;
import com.example.vitamix2023.databinding.LoginActivityBinding;

import java.util.List;

public class LandingPage extends AppCompatActivity {
    private static final String USER_ID_KEY = "com.example.vitamix2023.userIdKey";
    private static final String PREFERENCES_KEY = "com.example.vitamix2023.PREFERENCES_KEY";
    LandingPageBinding binding;

    TextView mGreeting;
    TextView mUserDisplay;

    Button mStock;
    Button mCart;
    Button mAddStock;
    Button mLogout;
    Button misAdmin;

    private int mUserId = -1;

    private SharedPreferences mPreferences = null;

    private LoginDAO mLoginDAO;
    private LoginDAO mVitaminDAO;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        binding = LandingPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mStock = binding.stockButton;
        mAddStock = binding.addItems;
        mGreeting = binding.Greeting;
        mCart = binding.cartButton;
        mUserDisplay = binding.UserDisplay;
        mLogout = binding.LogOutBtn;
        misAdmin = binding.AdminButton;

        getDatabase();
        getVitaminDatabase();
//        checkForUser();
//        addUserToPreference(mUserId);
//        loginUser(mUserId);
//        Change greeting to coincide with current logged in user

//        mGreeting.setText(mUser.getUsername());

        //Stock button
        mStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LandingPage.this,StockActivity.class);

                startActivity(i);
            }
        });

        //Cart Button
        mCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LandingPage.this,CartActivity.class);

                startActivity(i);
            }
        });

        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });

        mAddStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LandingPage.this,AddStockActivity.class);
                startActivity(i);
            }
        });
    }

    private void loginUser(int userId) {
        mUser = mLoginDAO.getUserId(userId);
    }

    private void logoutUser(){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setMessage("Logout?");

        alertBuilder.setPositiveButton(getString(R.string.yes),
            new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog,int which){
                    Intent intent = new Intent(LandingPage.this,MainActivity.class);
                    startActivity(intent);
                }
        });
        alertBuilder.setNegativeButton(getString(R.string.no),
                new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog,int which){

                    }
                });

        alertBuilder.show();
    }


    private void clearUserFromIntent(){
        getIntent().putExtra(USER_ID_KEY,-1);
    }

    private void clearUserFromPref(){
        addUserToPreference(-1);
    }

    //TODO: Finish this
    private void addUserToPreference(int userId) {
        if(mPreferences == null){
            getPrefs();
        }
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(USER_ID_KEY,userId);
    }

    private void getDatabase(){
        mLoginDAO = Room.databaseBuilder(this, AppDataBase.class,AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build().LoginDAO();
    }

    private void getVitaminDatabase(){
        mVitaminDAO = Room.databaseBuilder(this, AppDataBase.class,AppDataBase.VITAMINS_TABLE)
                .allowMainThreadQueries()
                .build().LoginDAO();
    }

    private void checkForUser() {
        //do we have a user in the intent?
        mUserId = getIntent().getIntExtra(USER_ID_KEY,-1);
        //do we have a user in the preferences?
        if(mUserId != -1){
            return;
        }

        if(mPreferences == null){
            getPrefs();
        }

        mUserId = mPreferences.getInt(USER_ID_KEY,-1);

        if(mUserId != 1){
            return;
        }

        List<User> users = mLoginDAO.getAllUsers();
        if(users.size() <= 0){
            User defaultUser = new User("daclink","dac123","no");
            mLoginDAO.insert(defaultUser);
        }
    }

    private void getPrefs() {
        SharedPreferences preferences = this.getSharedPreferences(PREFERENCES_KEY,Context.MODE_PRIVATE);
    }

    public static Intent intentFactory(Context context){
        Intent intent = new Intent(context,LoginActivity.class);

        return intent;
    }
}
