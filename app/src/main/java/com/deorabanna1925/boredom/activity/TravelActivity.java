package com.deorabanna1925.boredom.activity;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.deorabanna1925.boredom.databinding.ActivityTravelBinding;

public class TravelActivity extends AppCompatActivity {

    private ActivityTravelBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTravelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

    }
}