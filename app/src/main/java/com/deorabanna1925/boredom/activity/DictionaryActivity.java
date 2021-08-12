package com.deorabanna1925.boredom.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.deorabanna1925.boredom.databinding.ActivityDictionaryBinding;

public class DictionaryActivity extends AppCompatActivity {

    private ActivityDictionaryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDictionaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }
}