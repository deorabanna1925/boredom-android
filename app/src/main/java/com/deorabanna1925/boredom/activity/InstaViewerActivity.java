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

        binding.btnSearch.setOnClickListener(v -> {
            String username = binding.etSearch.getText().toString();
            if (username.isEmpty()) {
                binding.etSearch.setError("Enter Username");
                return;
            }
            binding.etSearch.setError(null);
            getData(username);
        });
    }

    private void getData(String username) {
        String url = "https://www.instagram.com/" + username + "/?__a=1";

    }
}