package com.deorabanna1925.boredom.activity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.deorabanna1925.boredom.adapter.TvShowsAdapter;
import com.deorabanna1925.boredom.databinding.ActivityTvShowsBinding;
import com.deorabanna1925.boredom.model.ModelTvShow;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TvShowsActivity extends AppCompatActivity {

    private ActivityTvShowsBinding binding;
    private TvShowsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTvShowsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        binding.progressBar.setVisibility(View.GONE);

        binding.searchBar.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                getTvShowData(textView.getText().toString());
                return true;
            }
            return false;
        });

    }

    private void getTvShowData(String query) {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        binding.recyclerView.setNestedScrollingEnabled(false);
        binding.recyclerView.setHasFixedSize(true);
        String url = "https://api.tvmaze.com/search/shows?q=" + query;
        final RequestQueue queue = Volley.newRequestQueue(this);
        queue.getCache().clear();
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);
                binding.progressBar.setVisibility(View.GONE);
                Gson gson = new Gson();
                String jsonOutput = jsonArray.toString();
                Type listType = new TypeToken<ArrayList<ModelTvShow>>() {
                }.getType();
                ArrayList<ModelTvShow> arrayList = gson.fromJson(jsonOutput, listType);
                adapter = new TvShowsAdapter(TvShowsActivity.this, arrayList);
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