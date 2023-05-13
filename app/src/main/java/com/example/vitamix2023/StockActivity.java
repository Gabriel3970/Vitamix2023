package com.example.vitamix2023;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.vitamix2023.DB.AppDataBase;
import com.example.vitamix2023.DB.LoginDAO;
import com.example.vitamix2023.databinding.MainActivityBinding;
import com.example.vitamix2023.databinding.StockPageBinding;

import java.util.List;

public class StockActivity extends AppCompatActivity {
    private static final String USER_ID_KEY = "com.example.vitamix2023.userIdKey";

    //Declare buttons
    StockPageBinding binding;

    private LoginDAO mUserDAO;
    private LoginDAO mVitaminDAO;

    private TextView mStockDisplay;
    private int mUserId = -1;

    private List<Vitamin> mVitaminList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_page);

        binding = StockPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Add default vitamins
        Vitamin FishOil = new Vitamin("Omega-3 Fish Oil (80 Capsules)","All-Around Health","$49.99");
        Vitamin Magnesium = new Vitamin("Magic Magnesium (50 Capsules)","Body Enzyme Reactions","$25.99");
        Vitamin VitaminD = new Vitamin("Vitamin D From The Sun(60 Capsules)","Bone & Immune Health","$19.99");
        Vitamin Probiotics = new Vitamin("Bacteria Buddies: Probiotics (50 Capsultes)","Digestion","$19.99");

        mVitaminDAO = Room.databaseBuilder(this, AppDataBase.class,AppDataBase.VITAMINS_TABLE)
                .allowMainThreadQueries()
                .build().LoginDAO();

        mVitaminDAO.insert(FishOil);
        mVitaminDAO.insert(Magnesium);
        mVitaminDAO.insert(VitaminD);
        mVitaminDAO.insert(Probiotics);

        mStockDisplay = binding.StockDisplay;
        refreshDisplay();
    }

    private void refreshDisplay(){
        String noVitMessage = "No Stock!";

        mVitaminList = mVitaminDAO.getAllVitamins();

        if(mVitaminList.size() <= 0){
            mStockDisplay.setText(noVitMessage);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for(Vitamin log: mVitaminList){
            sb.append(log.toString());
            sb.append("\n");
            sb.append("=-=-=-=-=-=-=-=-=");
            sb.append("\n");
        }

        mStockDisplay.setText(sb.toString());
    }

    public static Intent intentFactory(Context context, int userId){
        Intent intent = new Intent(context,StockActivity.class);
        intent.putExtra(USER_ID_KEY,userId);

        return intent;
    }
}
