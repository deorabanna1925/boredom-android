package com.deorabanna1925.boredom.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.deorabanna1925.boredom.databinding.ActivitySomethingBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class SomethingActivity extends AppCompatActivity {

    private ActivitySomethingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySomethingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSomethingData();

    }

    private void getSomethingData() {
        binding.progressBar.setVisibility(View.VISIBLE);
        String url = "https://www.boredapi.com/api/";
        url += "activity";
        final RequestQueue queue = Volley.newRequestQueue(this);
        queue.getCache().clear();
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            binding.progressBar.setVisibility(View.GONE);
            try {
                JSONObject jsonObject = new JSONObject(response);
                if (jsonObject.has("error")) {
                    String error = jsonObject.getString("error");
                    Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show();
                } else {

                    String activity = jsonObject.getString("activity");
                    String type = jsonObject.getString("type");
                    int participants = jsonObject.getInt("participants");
                    double price = jsonObject.getDouble("price");
                    String link = jsonObject.getString("link");
                    long key = jsonObject.getLong("key");
                    double accessibility = jsonObject.getDouble("accessibility");

                    binding.tvSomethingActivity.setText(activity);
                    binding.tvSomethingType.setText(type);
                    binding.tvSomethingParticipants.setText(String.valueOf(participants));
                    binding.tvSomethingPrice.setText(String.valueOf(price));
                    binding.tvSomethingLink.setText(link);
                    binding.tvSomethingKey.setText(String.valueOf(key));
                    binding.tvSomethingAccessibility.setText(String.valueOf(accessibility));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> binding.progressBar.setVisibility(View.GONE));
        queue.add(request);
    }

    public void generateNew(View view) {
        getSomethingData();
    }
}