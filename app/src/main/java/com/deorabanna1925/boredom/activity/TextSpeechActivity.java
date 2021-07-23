package com.deorabanna1925.boredom.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.deorabanna1925.boredom.databinding.ActivityTextSpeechBinding;

public class TextSpeechActivity extends AppCompatActivity {

    private ActivityTextSpeechBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTextSpeechBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Convert Text to Speech
        //Convert Speech to Text

    }
}