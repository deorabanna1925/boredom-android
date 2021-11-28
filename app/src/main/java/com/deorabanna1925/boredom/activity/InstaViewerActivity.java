package com.deorabanna1925.boredom.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.deorabanna1925.boredom.databinding.ActivityInstaViewerBinding;

public class InstaViewerActivity extends AppCompatActivity {

    private ActivityInstaViewerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInstaViewerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }
}