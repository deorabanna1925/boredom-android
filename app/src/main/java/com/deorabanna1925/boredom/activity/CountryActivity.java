package com.deorabanna1925.boredom.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.deorabanna1925.boredom.adapter.CountryAdapter;
import com.deorabanna1925.boredom.databinding.ActivityCountryBinding;
import com.deorabanna1925.boredom.model.ModelCountry;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CountryActivity extends AppCompatActivity {

    private ActivityCountryBinding binding;
    private CountryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCountryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        getCountryData();

    }

    private void getCountryData() {
        binding.progressBar.setVisibility(View.VISIBLE);
        String url = "https://gist.githubusercontent.com/deorabanna1925/977d502cfcc08f3abc4daa1e3861e9b3/raw/d300dd1f99e131d7febd4f18ba001f1155d43b0a/countries.json";
        final RequestQueue queue = Volley.newRequestQueue(this);
        queue.getCache().clear();
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);
                binding.progressBar.setVisibility(View.GONE);
                Gson gson = new Gson();
                String jsonOutput = jsonArray.toString();
                Type listType = new TypeToken<ArrayList<ModelCountry>>() {
                }.getType();
                ArrayList<ModelCountry> arrayList = gson.fromJson(jsonOutput, listType);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    arrayList.sort((o1, o2) -> o1.getName().getCommon().compareTo(o2.getName().getCommon()));
                }
                adapter = new CountryAdapter(CountryActivity.this, arrayList);
                binding.viewpager.setAdapter(adapter);
                binding.viewpager.setOffscreenPageLimit(1);
                new TabLayoutMediator(binding.tabs, binding.viewpager,
                        (tab, position) -> tab.setText(arrayList.get(position).getName().getCommon())
                ).attach();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            binding.progressBar.setVisibility(View.GONE);
        });
        queue.add(request);
    }

}
