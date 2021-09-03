package com.deorabanna1925.boredom.activity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.deorabanna1925.boredom.databinding.ActivityTextSpeechBinding;

import java.util.Locale;

public class TextSpeechActivity extends AppCompatActivity {

    private ActivityTextSpeechBinding binding;
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTextSpeechBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Text to Speech");

        textToSpeech = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                int result = textToSpeech.setLanguage(Locale.ENGLISH);
                if (result == TextToSpeech.LANG_MISSING_DATA
                        || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(this, "Language Not Supported", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.viewAudioPlay.setOnClickListener(view -> speakNow());

    }

    private void speakNow() {
        float pitch = (float) binding.seekBarPitch.getProgress() / 50;
        if (pitch < 0.1) pitch = 0.1f;
        float speed = (float) binding.seekBarSpeed.getProgress() / 50;
        if (speed < 0.1) speed = 0.1f;

        textToSpeech.setPitch(pitch);
        textToSpeech.setSpeechRate(speed);
        textToSpeech.speak(binding.text.getText().toString(), TextToSpeech.QUEUE_FLUSH, null,null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}