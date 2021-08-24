package com.deorabanna1925.boredom.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.deorabanna1925.boredom.adapter.GitHubTrendingAdapter;
import com.deorabanna1925.boredom.databinding.ActivityGitHubTrendingBinding;
import com.deorabanna1925.boredom.model.ModelGitHub;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class GitHubTrendingActivity extends AppCompatActivity {

    private ActivityGitHubTrendingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGitHubTrendingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("GitHub Trending");

        getData();

    }

    private void getData() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setNestedScrollingEnabled(false);
        binding.recyclerView.setHasFixedSize(true);
        String url = "https://api.trending-github.com/github/repositories?period=daily";
        final RequestQueue queue = Volley.newRequestQueue(this);
        queue.getCache().clear();
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);
                binding.progressBar.setVisibility(View.GONE);
                Gson gson = new Gson();
                String jsonOutput = jsonArray.toString();
                Type listType = new TypeToken<ArrayList<ModelGitHub>>() {
                }.getType();
                ArrayList<ModelGitHub> arrayList = gson.fromJson(jsonOutput, listType);
                GitHubTrendingAdapter adapter = new GitHubTrendingAdapter(GitHubTrendingActivity.this, arrayList);
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