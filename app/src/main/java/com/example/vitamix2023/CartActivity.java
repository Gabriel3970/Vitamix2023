package com.example.vitamix2023;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vitamix2023.databinding.CartPageBinding;
import com.example.vitamix2023.databinding.StockPageBinding;

public class CartActivity extends AppCompatActivity {
    CartPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_page);

        binding = CartPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}
