package com.deorabanna1925.boredom.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

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
    private ArrayList<String> languagesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGitHubTrendingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("GitHub Trending");

        setPeriodSpinner();
        getLanguages();

        binding.apply.setOnClickListener(v -> getData());

        String url = "https://api.trending-github.com/github/repositories?period=daily";
        getTrendingData(url);

    }

    private void getLanguages() {
        String url = "https://api.trending-github.com/github/languages";
        final RequestQueue queue = Volley.newRequestQueue(this);
        queue.getCache().clear();
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);
                Gson gson = new Gson();
                String jsonOutput = jsonArray.toString();
                Type listType = new TypeToken<ArrayList<String>>() {
                }.getType();
                languagesList = gson.fromJson(jsonOutput, listType);
                setLanguageSpinner();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
        });
        queue.add(request);
    }

    private void setPeriodSpinner() {
        String[] items = new String[]{"daily", "weekly", "monthly"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        binding.period.setAdapter(adapter);
    }

    private void setLanguageSpinner() {
        languagesList.add(0, "all");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, languagesList);
        binding.language.setAdapter(adapter);
    }

    private void getData() {
        ArrayList<ModelGitHub> arrayList = new ArrayList<>();
        GitHubTrendingAdapter adapter = new GitHubTrendingAdapter(GitHubTrendingActivity.this, arrayList);
        binding.recyclerView.setAdapter(adapter);
        String period = binding.period.getSelectedItem().toString();
        String language = binding.language.getSelectedItem().toString();
        String url;
        if (language.equals("all")) {
            url = "https://api.trending-github.com/github/repositories?period=" + period;
        } else {
            url = "https://api.trending-github.com/github/repositories?period=" + period + "&language=" + language;
        }
        getTrendingData(url);

    }

    private void getTrendingData(String url) {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setNestedScrollingEnabled(false);
        binding.recyclerView.setHasFixedSize(true);
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