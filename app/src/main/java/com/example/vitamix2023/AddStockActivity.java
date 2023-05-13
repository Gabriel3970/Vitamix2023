package com.example.vitamix2023;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.vitamix2023.DB.AppDataBase;
import com.example.vitamix2023.DB.LoginDAO;
import com.example.vitamix2023.databinding.AddstockActivityBinding;

import java.util.List;

public class AddStockActivity extends AppCompatActivity {

    AddstockActivityBinding binding;

    EditText mName;
    EditText mFocus;
    EditText mPrice;

    Button mSubmit;

    LoginDAO mVitaminDAO;
    List<Vitamin> mVitaminList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addstock_activity);

        binding = AddstockActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //Wire
        mName = binding.editTextVitaminName;
        mFocus = binding.editTextVitaminFocus;
        mPrice = binding.editTextVitaminPrice;
        mSubmit = binding.Submitbutton;

        //DAO
        mVitaminDAO = Room.databaseBuilder(this, AppDataBase.class,AppDataBase.VITAMINS_TABLE)
                .allowMainThreadQueries()
                .build().LoginDAO();

        Vitamin newVitamin = new Vitamin(mName.toString(),mFocus.toString(),mPrice.toString());

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVitaminDAO.insert(newVitamin);
            }
        });


    }

    @Override
    public String toString() {
        return "AddStockActivity{" +
                "mName=" + mName +
                ", mFocus=" + mFocus +
                ", mPrice=" + mPrice +
                '}';
    }
}
