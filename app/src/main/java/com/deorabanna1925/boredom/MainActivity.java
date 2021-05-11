package com.deorabanna1925.boredom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.deorabanna1925.boredom.activity.ColorsActivity;
import com.deorabanna1925.boredom.activity.SomethingActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        

    }

    public void openColors(View view) {
        startActivity(new Intent(MainActivity.this, ColorsActivity.class));
    }

    public void openSomething(View view) {
        startActivity(new Intent(MainActivity.this, SomethingActivity.class));
    }
}