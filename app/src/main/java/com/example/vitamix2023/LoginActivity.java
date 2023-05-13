package com.example.vitamix2023;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.vitamix2023.DB.AppDataBase;
import com.example.vitamix2023.DB.LoginDAO;
import com.example.vitamix2023.databinding.LoginActivityBinding;

import java.util.List;

//In order to keep track of the intance we have, we used sharedPreferences. Watch Dr. C's gymlog adding users video

public class LoginActivity extends AppCompatActivity {
    private static final String USER_ID_KEY = "com.example.vitamix2023.IdKey";
    public boolean isAdmin = false;
    private static final String PREFERENCES_KEY = "com.example.vitamix2023.PREFERENCES_KEY";

    LoginActivityBinding binding;
    EditText mUsername;
    EditText mPassword;

    Button login;

    LoginDAO mLoginDAO;

    private int mUserId = -1;

    List<User> mUserList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        binding = LoginActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mUsername = binding.mainUserNameEditText;
        mPassword = binding.mainPasswordEditText;
        login = binding.buttonLogin;

        User test1 = new User("testuser1","testuser1","no");
        User test2 = new User("admin2","admin2","yes");

        mLoginDAO = Room.databaseBuilder(this, AppDataBase.class,AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build().LoginDAO();

        checkForUser();

        mLoginDAO.insert(test1);
        mLoginDAO.insert(test2);


        //Login button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,LandingPage.class);

                mUserList = mLoginDAO.getUser(mUsername.getText().toString());

                for(User user: mUserList){
                    if(mPassword.getText().toString().equals(user.getPassword().toString())){
                        if(user.getMisAdmin().equals("yes")){
                            isAdmin = true;
                        }else{
                            isAdmin = false;
                        }
                        startActivity(i);
                    }
                }
            }
        });
    }

    private void checkForUser() {
        //do we have a user in the intent?
        mUserId = getIntent().getIntExtra(USER_ID_KEY,-1);
        //do we have a user in the preferences?
        if(mUserId != -1){
            return;
        }

        SharedPreferences preferences = this.getSharedPreferences(PREFERENCES_KEY,Context.MODE_PRIVATE);

        mUserId = preferences.getInt(USER_ID_KEY,-1);

        if(mUserId != 1){
            return;
        }

        List<User> users = mLoginDAO.getAllUsers();
        if(users.size() <= 0){
            User defaultUser = new User("daclink","dac123","no");
            mLoginDAO.insert(defaultUser);
        }
    }


    public static Intent intentFactory(Context context, int userId){
        Intent intent = new Intent(context,StockActivity.class);
        intent.putExtra(USER_ID_KEY,userId);

        return intent;
    }

}
