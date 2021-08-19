package com.deorabanna1925.boredom.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.deorabanna1925.boredom.databinding.ActivityRandomUserBinding;

public class RandomUserActivity extends AppCompatActivity {

    private ActivityRandomUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRandomUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }
}