package com.deorabanna1925.boredom.activity;

import android.os.Bundle;

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
    private ArrayList<ModelCountry> arrayList;
    private CountryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCountryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
        getCountryData();

    }

    private void getCountryData() {
        arrayList = new ArrayList<>();
        String url = "https://restcountries.eu/rest/v2/";
        final RequestQueue queue = Volley.newRequestQueue(this);
        queue.getCache().clear();
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);

                Gson gson = new Gson();
                String jsonOutput = jsonArray.toString();
                Type listType = new TypeToken<ArrayList<ModelCountry>>() {
                }.getType();
                ArrayList<ModelCountry> arrayList = gson.fromJson(jsonOutput, listType);
                adapter = new CountryAdapter(CountryActivity.this, arrayList);
                binding.viewpager.setAdapter(adapter);
                binding.viewpager.setOffscreenPageLimit(1);
                new TabLayoutMediator(binding.tabs, binding.viewpager,
                        (tab, position) -> {
                            tab.setText(arrayList.get(position).getName());
                        }
                ).attach();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
//            progressBar.setVisibility(View.GONE);
        });
        queue.add(request);
    }
}