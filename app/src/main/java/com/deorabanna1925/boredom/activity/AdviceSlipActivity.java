package com.deorabanna1925.boredom.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.deorabanna1925.boredom.databinding.ActivityAdviceSlipBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class AdviceSlipActivity extends AppCompatActivity {

    private ActivityAdviceSlipBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdviceSlipBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        binding.generateNew.setOnClickListener(view -> getAdviceData());

        getAdviceData();
    }

    private void getAdviceData() {
        binding.progressBar.setVisibility(View.VISIBLE);
        String url = "https://api.adviceslip.com/advice";
        final RequestQueue queue = Volley.newRequestQueue(this);
        queue.getCache().clear();
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            binding.progressBar.setVisibility(View.GONE);
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONObject slipObject = jsonObject.getJSONObject("slip");
                String advice = slipObject.getString("advice");

                binding.adviceSlip.setText(advice);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            binding.progressBar.setVisibility(View.GONE);
        });
        queue.add(request);
    }
}