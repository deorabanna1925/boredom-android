package com.deorabanna1925.boredom.activity;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

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

    private String DATE_FORMAT = "yyyy-MM-dd";
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable runnable;

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

        if (getIntent().getStringExtra("next") != null) {
            getNextMovie(getIntent().getStringExtra("next"));
        } else {
            getNextMovie("");
        }

    }

    private void countDownStart(String releaseDate) {
        runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    handler.postDelayed(this, 1000);
                    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
                    Calendar c = Calendar.getInstance();
                    c.setTime(Objects.requireNonNull(dateFormat.parse(releaseDate)));
                    c.add(Calendar.DATE, 1);
                    Date date = c.getTime();
                    String newDate = dateFormat.format(date.getTime());
                    Date event_date = dateFormat.parse(newDate);
                    Date current_date = new Date();
                    if (!current_date.after(event_date)) {

                        long diff = event_date.getTime() - current_date.getTime();
                        long Days = diff / (24 * 60 * 60 * 1000);
                        long Hours = diff / (60 * 60 * 1000) % 24;
                        long Minutes = diff / (60 * 1000) % 60;
                        long Seconds = diff / 1000 % 60;

                        @SuppressLint("DefaultLocale")
                        String countdown = String.format("%02dd, %02dh, %02dm, %02ds", Days, Hours, Minutes, Seconds);

                        binding.countdown.setText(countdown);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 0);
    }

    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(runnable);
    }

    private void getNextMovie(String dateStr) {
        String url = "https://www.whenisthenextmcufilm.com/api" + dateStr;
        final RequestQueue queue = Volley.newRequestQueue(this);
        queue.getCache().clear();
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                String title = jsonObject.getString("title");
                String type = jsonObject.getString("type");
                String overview = jsonObject.getString("overview");
                String poster_url = jsonObject.getString("poster_url");
                int days_until = jsonObject.getInt("days_until");
                String releaseDate = jsonObject.getString("release_date");
                JSONObject followingProduction = jsonObject.getJSONObject("following_production");
                if (followingProduction.length() != 0) {
                    binding.generateNew.setVisibility(View.VISIBLE);
                } else {
                    binding.generateNew.setVisibility(View.GONE);
                }

                binding.movieName.setText(title);
                binding.releaseDate.setText(releaseDate);
                binding.overview.setText(overview);
                binding.type.setText(type);
                countDownStart(releaseDate);

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
                        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
                        Calendar c = Calendar.getInstance();
                        c.setTime(Objects.requireNonNull(dateFormat.parse(releaseDate)));
                        c.add(Calendar.DATE, 1);
                        Date date = c.getTime();
                        String newDate = dateFormat.format(date.getTime());

                        Intent intent = new Intent(WhenIsTheNextMcuFilmActivity.this,
                                WhenIsTheNextMcuFilmActivity.class);
                        intent.putExtra("next", "?date=" + newDate);
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