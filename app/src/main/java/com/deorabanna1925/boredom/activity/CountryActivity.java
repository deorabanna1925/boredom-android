package com.deorabanna1925.boredom.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

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
import java.util.Locale;

public class CountryActivity extends AppCompatActivity {

    private ActivityCountryBinding binding;
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
        binding.progressBar.setVisibility(View.VISIBLE);
        String url = "https://restcountries.eu/rest/v2/";
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
                adapter = new CountryAdapter(CountryActivity.this, arrayList);
                binding.viewpager.setAdapter(adapter);
                binding.viewpager.setOffscreenPageLimit(1);
                new TabLayoutMediator(binding.tabs, binding.viewpager,
                        (tab, position) -> tab.setText(arrayList.get(position).getName())
                ).attach();

                binding.searchBar.setVisibility(View.GONE);
/*                binding.searchBar.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if(charSequence.toString().trim().length() != 0){
                            ArrayList<ModelCountry> searchResult = new ArrayList<>();
                            for(ModelCountry country : arrayList){
                                if(country.getName().toLowerCase(Locale.ROOT).contains(charSequence.toString().toLowerCase(Locale.ROOT))){
                                    searchResult.add(country);
                                }
                            }
                            adapter = new CountryAdapter(CountryActivity.this, searchResult);
                            binding.viewpager.setAdapter(adapter);
                            binding.viewpager.setOffscreenPageLimit(1);
                            new TabLayoutMediator(binding.tabs, binding.viewpager,
                                    (tab, position) -> tab.setText(searchResult.get(position).getName())
                            ).attach();
                        }else{
                            adapter = new CountryAdapter(CountryActivity.this, arrayList);
                            binding.viewpager.setAdapter(adapter);
                            binding.viewpager.setOffscreenPageLimit(1);
                            new TabLayoutMediator(binding.tabs, binding.viewpager,
                                    (tab, position) -> tab.setText(arrayList.get(position).getName())
                            ).attach();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });*/

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            binding.progressBar.setVisibility(View.GONE);
        });
        queue.add(request);
    }
}