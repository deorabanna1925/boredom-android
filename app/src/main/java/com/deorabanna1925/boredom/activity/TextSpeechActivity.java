package com.deorabanna1925.boredom.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import androidx.appcompat.app.AppCompatActivity;

import com.deorabanna1925.boredom.databinding.ActivityTextSpeechBinding;

import java.util.Locale;

public class TextSpeechActivity extends AppCompatActivity {

    private ActivityTextSpeechBinding binding;

    @SuppressLint("ObsoleteSdkInt")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTextSpeechBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.speak.setOnClickListener(view -> {
            if (binding.text.getText().toString().length() == 0) {
                binding.text.setError("");
                return;
            }

            TextToSpeech tts = new TextToSpeech(this, i -> {

            });
            tts.setLanguage(Locale.US);
            tts.speak(binding.text.getText(), TextToSpeech.QUEUE_FLUSH, null, null);

        });

    }
}