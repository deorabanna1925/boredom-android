package com.deorabanna1925.boredom.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.deorabanna1925.boredom.shared.Prefs;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

public class TvShowsActivity extends AppCompatActivity {

    private ActivityTvShowsBinding binding;

    private static final String SEARCH_HISTORY_KEY = "_SEARCH_HISTORY_KEY";
    private static final int MAX_HISTORY_ITEMS = 10;

    private TvShowsAdapter adapter;
    private Prefs prefs;

    @Override
    protected void onResume() {
        super.onResume();
        getSupportActionBar().hide();
        prefs = new Prefs(this);
        showSearchHistory();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTvShowsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.searchBar.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                getTvShowData(textView.getText().toString());
                return true;
            }
            return false;
        });

        binding.clearSearch.setOnClickListener(v -> {
            binding.searchBar.setText(null);
        });

        binding.searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length()==0){
                    showSearchHistory();
                    binding.clearSearch.setVisibility(View.GONE);
                }else {
                    binding.clearSearch.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void showSearchHistory() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        binding.recyclerView.setNestedScrollingEnabled(false);
        binding.recyclerView.setHasFixedSize(true);
        ArrayList<ModelTvShow> searchHistory = getSearchHistory();
        Collections.reverse(searchHistory);
        binding.recyclerView.setAdapter(new TvShowsAdapter(this, searchHistory));
        binding.progressBar.setVisibility(View.GONE);
    }

    private void getTvShowData(String query) {
        binding.progressBar.setVisibility(View.VISIBLE);
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

    public void addToSearchHistory(ModelTvShow modelTvShow) {
        ArrayList<ModelTvShow> searchArrayList = getSearchHistory();
        for(int i=0;i<searchArrayList.size();i++){
            ModelTvShow tvShow = searchArrayList.get(i);
            if(modelTvShow.getShow().getId().toString().equals(tvShow.getShow().getId().toString())) {
                searchArrayList.remove(i);
            }
        }
        searchArrayList.add(modelTvShow);
        if (searchArrayList.size() > MAX_HISTORY_ITEMS) searchArrayList.remove(0);
        Type listType = new TypeToken<ArrayList<ModelTvShow>>() {}.getType();
        String json = new Gson().toJson(searchArrayList, listType);
        prefs.setString(SEARCH_HISTORY_KEY,json);
    }

    private ArrayList<ModelTvShow> getSearchHistory() {
        String previousSearch = prefs.getString(SEARCH_HISTORY_KEY);
        if (previousSearch.equals("")) return new ArrayList<>();
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<ModelTvShow>>() {
        }.getType();
        return gson.fromJson(previousSearch, listType);
    }

}