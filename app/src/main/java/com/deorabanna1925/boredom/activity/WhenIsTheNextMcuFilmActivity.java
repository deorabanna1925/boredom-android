package com.deorabanna1925.boredom.activity;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.deorabanna1925.boredom.databinding.ActivityWhenIsTheNextMcuFilmBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class WhenIsTheNextMcuFilmActivity extends AppCompatActivity {

    private ActivityWhenIsTheNextMcuFilmBinding binding;
    private CircularProgressDrawable progressDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWhenIsTheNextMcuFilmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setTitle("When is the next MCU film ?");

        progressDrawable = new CircularProgressDrawable(this);
        progressDrawable.setStrokeWidth(5f);
        progressDrawable.setCenterRadius(30f);
        progressDrawable.start();

        if(getIntent().getStringExtra("next")!=null){
            getNextMovie(getIntent().getStringExtra("next"));
        }else{
            getNextMovie("");
        }

    }

    private void getNextMovie(String dateStr) {
        String url = "https://www.whenisthenextmcufilm.com/api" + dateStr;
        final RequestQueue queue = Volley.newRequestQueue(this);
        queue.getCache().clear();
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                String title = jsonObject.getString("title");
                String poster_url = jsonObject.getString("poster_url");
                int days_until = jsonObject.getInt("days_until");
                String release_date = jsonObject.getString("release_date");
                JSONObject followingProduction = jsonObject.getJSONObject("following_production");
                if(followingProduction.length()!=0){
                    binding.generateNew.setVisibility(View.VISIBLE);
                }else{
                    binding.generateNew.setVisibility(View.GONE);
                }

                String movieRelease = title + " releases in " + days_until + " days!";
                binding.movieRelease.setText(movieRelease);

                Glide.with(this)
                        .load(poster_url)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .placeholder(progressDrawable)
                        .skipMemoryCache(true)
                        .into(binding.image);

                Glide.with(this)
                        .load(poster_url)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .apply(bitmapTransform(new BlurTransformation(25, 3)))
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(binding.backgroundImage);

                binding.backgroundImage.setAlpha(0.3f);

                binding.generateNew.setOnClickListener(view -> {
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                        Calendar c = Calendar.getInstance();
                        c.setTime(Objects.requireNonNull(dateFormat.parse(release_date)));
                        c.add(Calendar.DATE, 1);
                        Date date = c.getTime();
                        String newDate = dateFormat.format(date.getTime());

                        Intent intent = new Intent(WhenIsTheNextMcuFilmActivity.this,
                                WhenIsTheNextMcuFilmActivity.class);
                        intent.putExtra("next","?date="+newDate);
                        startActivity(intent);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
//            binding.progressBar.setVisibility(View.GONE);
        });
        queue.add(request);
    }

}