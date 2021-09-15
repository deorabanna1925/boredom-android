package com.deorabanna1925.boredom.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.deorabanna1925.boredom.adapter.SpaceLaunchAdapter;
import com.deorabanna1925.boredom.databinding.ActivitySpaceLaunchBinding;
import com.deorabanna1925.boredom.model.ModelSpaceLaunch;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SpaceLaunchActivity extends AppCompatActivity {

    private ActivitySpaceLaunchBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySpaceLaunchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getUpcomingLaunchData();

    }

    private void getUpcomingLaunchData() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setNestedScrollingEnabled(false);
        binding.recyclerView.setHasFixedSize(true);
        String url = "https://ll.thespacedevs.com/2.2.0/launch/upcoming/?format=json";
        final RequestQueue queue = Volley.newRequestQueue(this);
        queue.getCache().clear();
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            binding.progressBar.setVisibility(View.GONE);
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                Gson gson = new Gson();
                String jsonOutput = jsonArray.toString();
                Type listType = new TypeToken<ArrayList<ModelSpaceLaunch>>() {
                }.getType();
                ArrayList<ModelSpaceLaunch> arrayList = gson.fromJson(jsonOutput, listType);
                SpaceLaunchAdapter adapter = new SpaceLaunchAdapter(SpaceLaunchActivity.this, arrayList);
                binding.recyclerView.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            binding.progressBar.setVisibility(View.GONE);
        });
        queue.add(request);
    }

}