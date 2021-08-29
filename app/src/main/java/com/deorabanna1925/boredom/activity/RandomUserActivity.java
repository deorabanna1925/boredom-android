package com.deorabanna1925.boredom.activity;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.deorabanna1925.boredom.databinding.ActivityRandomUserBinding;
import com.deorabanna1925.boredom.model.ModelRandomUser;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class RandomUserActivity extends AppCompatActivity {

    private ActivityRandomUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRandomUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        getData();

    }

    private void getData() {
//        binding.progressBar.setVisibility(View.VISIBLE);
        String url = "https://randomuser.me/api/";
        final RequestQueue queue = Volley.newRequestQueue(this);
        queue.getCache().clear();
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
//            binding.progressBar.setVisibility(View.GONE);
            try {
                if (isJSONValid(response)) {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    Gson gson = new Gson();
                    JSONObject jObject = jsonArray.getJSONObject(0);
                    String jsonOutput = jObject.toString();
                    if (isJSONValid(jsonOutput)) {
                        ModelRandomUser randomUser = gson.fromJson(jsonOutput, ModelRandomUser.class);
                        setData(randomUser);
                    }else {
                        Toast.makeText(this, "Try Again!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "Try Again!", Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {/*binding.progressBar.setVisibility(View.GONE)*/});
        queue.add(request);
    }

    @SuppressLint("SetTextI18n")
    private void setData(ModelRandomUser randomUser) {

        CircularProgressDrawable progressDrawable = new CircularProgressDrawable(this);
        progressDrawable.setStrokeWidth(5f);
        progressDrawable.setCenterRadius(30f);
        progressDrawable.start();

        Glide.with(this)
                .load(randomUser.getPicture().getLarge())
                .transition(DrawableTransitionOptions.withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(progressDrawable)
                .skipMemoryCache(true)
                .into(binding.image);

        Glide.with(this)
                .load(randomUser.getPicture().getLarge())
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(bitmapTransform(new BlurTransformation(25, 3)))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(binding.imageBackground);


        String fullName =
                randomUser.getName().getTitle() + ". " +
                        randomUser.getName().getFirst() + " " +
                        randomUser.getName().getLast();
        binding.fullName.setText(fullName);
        binding.age.setText(randomUser.getDob().getAge().toString());
        binding.gender.setText(randomUser.getGender());
        binding.email.setText(randomUser.getEmail());
        binding.phone.setText(randomUser.getPhone());
        String street = randomUser.getLocation().getStreet().getNumber().toString() + ", " + randomUser.getLocation().getStreet().getName();
        binding.street.setText(street);
        binding.city.setText(randomUser.getLocation().getCity());
        binding.state.setText(randomUser.getLocation().getState());
        binding.country.setText(randomUser.getLocation().getCountry());
        binding.postCode.setText(randomUser.getLocation().getPostcode().toString());

    }

    public boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

}