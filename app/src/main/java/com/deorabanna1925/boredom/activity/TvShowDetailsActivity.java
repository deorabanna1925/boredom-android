package com.deorabanna1925.boredom.activity;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.deorabanna1925.boredom.adapter.TvShowsCastAdapter;
import com.deorabanna1925.boredom.adapter.TvShowsEpisodeAdapter;
import com.deorabanna1925.boredom.adapter.TvShowsSeasonAdapter;
import com.deorabanna1925.boredom.databinding.ActivityTvShowDetailsBinding;
import com.deorabanna1925.boredom.model.ModelTvShow;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class TvShowDetailsActivity extends AppCompatActivity {

    private ActivityTvShowDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTvShowDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        int id = getIntent().getIntExtra("id", 1);

        getTvShowData(String.valueOf(id));
        getTvShowCastData(String.valueOf(id));
        getTvShowSeasonData(String.valueOf(id));
        getTvShowAllEpisodeData(String.valueOf(id));

    }

    public void seasonNameViewAll(String title, boolean show) {
        binding.seasonName.setText(title);
        if (show) {
            binding.viewAll.setVisibility(View.VISIBLE);
        } else {
            binding.viewAll.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void getTvShowData(String id) {

        CircularProgressDrawable progressDrawable = new CircularProgressDrawable(this);
        progressDrawable.setStrokeWidth(5f);
        progressDrawable.setCenterRadius(30f);
        progressDrawable.start();

        String url = "https://api.tvmaze.com/shows/" + id;
        final RequestQueue queue = Volley.newRequestQueue(this);
        queue.getCache().clear();
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                Gson gson = new Gson();
                String jsonOutput = jsonObject.toString();
                Type listType = new TypeToken<ModelTvShow.Show>() {
                }.getType();
                ModelTvShow.Show model = gson.fromJson(jsonOutput, listType);

                if (model.getImage() != null) {

                    Glide.with(this)
                            .load(model.getImage().getOriginal())
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .placeholder(progressDrawable)
                            .skipMemoryCache(true)
                            .into(binding.image);

                    Glide.with(this)
                            .load(model.getImage().getMedium())
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .apply(bitmapTransform(new BlurTransformation(25, 3)))
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .into(binding.imageBackground);

                } else {
                    Glide.with(this)
                            .load("https://static.tvmaze.com/images/no-img/no-img-portrait-text.png")
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .placeholder(progressDrawable)
                            .skipMemoryCache(true)
                            .into(binding.image);

                    Glide.with(this)
                            .load("https://static.tvmaze.com/images/no-img/no-img-portrait-text.png")
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .apply(bitmapTransform(new BlurTransformation(25, 3)))
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .into(binding.imageBackground);
                }

                if (model.getName() != null) {
                    binding.name.setText(model.getName());
                }
                if (model.getStatus() != null) {
                    binding.status.setText(model.getStatus());
                }
                if (model.getSummary() != null) {
                    binding.summary.setText(Html.fromHtml(model.getSummary()));
                }

                seasonNameViewAll("All Episodes", false);

                binding.viewAll.setOnClickListener(v -> {
                    int i = getIntent().getIntExtra("id", 1);
                    seasonNameViewAll("All Episodes", false);
                    getTvShowAllEpisodeData(String.valueOf(i));
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
//            binding.progressBar.setVisibility(View.GONE);
        });
        queue.add(request);
    }

    private void getTvShowSeasonData(String id) {
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        binding.recyclerView.setNestedScrollingEnabled(false);
        binding.recyclerView.setHasFixedSize(true);
        String url = "https://api.tvmaze.com/shows/" + id + "/seasons";
        final RequestQueue queue = Volley.newRequestQueue(this);
        queue.getCache().clear();
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);
                Gson gson = new Gson();
                String jsonOutput = jsonArray.toString();
                Type listType = new TypeToken<ArrayList<ModelTvShow.Season>>() {
                }.getType();
                ArrayList<ModelTvShow.Season> arrayList = gson.fromJson(jsonOutput, listType);
                TvShowsSeasonAdapter adapter = new TvShowsSeasonAdapter(TvShowDetailsActivity.this, arrayList);
                binding.recyclerView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
//            binding.progressBar.setVisibility(View.GONE);
        });
        queue.add(request);
    }

    private void getTvShowCastData(String id) {
        binding.recyclerViewCast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerViewCast.setNestedScrollingEnabled(false);
        binding.recyclerViewCast.setHasFixedSize(true);
        String url = "https://api.tvmaze.com/shows/" + id + "/cast";
        final RequestQueue queue = Volley.newRequestQueue(this);
        queue.getCache().clear();
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);
                Gson gson = new Gson();
                String jsonOutput = jsonArray.toString();
                Type listType = new TypeToken<ArrayList<ModelTvShow.Cast>>() {
                }.getType();
                ArrayList<ModelTvShow.Cast> arrayList = gson.fromJson(jsonOutput, listType);
                TvShowsCastAdapter adapter = new TvShowsCastAdapter(TvShowDetailsActivity.this, arrayList);
                binding.recyclerViewCast.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {});
        queue.add(request);
    }

    private void getTvShowAllEpisodeData(String id) {
        String url = "https://api.tvmaze.com/shows/" + id + "/episodes";
        getTvShowEpisodeData(url);
    }

    private void getTvShowEpisodeData(String url) {
        final RequestQueue queue = Volley.newRequestQueue(this);
        queue.getCache().clear();
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);
                Gson gson = new Gson();
                String jsonOutput = jsonArray.toString();
                Type listType = new TypeToken<ArrayList<ModelTvShow.Episode>>() {
                }.getType();
                ArrayList<ModelTvShow.Episode> arrayList = gson.fromJson(jsonOutput, listType);
                TvShowsEpisodeAdapter adapter = new TvShowsEpisodeAdapter(TvShowDetailsActivity.this, arrayList);
                binding.viewPager.setAdapter(adapter);
                binding.viewPager.setOffscreenPageLimit(3);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
//            binding.progressBar.setVisibility(View.GONE);
        });
        queue.add(request);
    }

    public void getTvShowSeasonEpisodeData(String id) {
        String url = "https://api.tvmaze.com/seasons/" + id + "/episodes";
        getTvShowEpisodeData(url);
    }
}