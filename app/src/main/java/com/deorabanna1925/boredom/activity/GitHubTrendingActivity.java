package com.deorabanna1925.boredom.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.deorabanna1925.boredom.databinding.ActivityGitHubTrendingBinding;

public class GitHubTrendingActivity extends AppCompatActivity {

    private ActivityGitHubTrendingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGitHubTrendingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}