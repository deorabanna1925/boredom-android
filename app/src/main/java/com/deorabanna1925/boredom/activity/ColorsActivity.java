package com.deorabanna1925.boredom.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.deorabanna1925.boredom.databinding.ActivityColorsBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ColorsActivity extends AppCompatActivity {

    private ActivityColorsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityColorsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        binding.generateNew.setOnClickListener(view -> getColorData());

        getColorData();

    }

    private void getColorData() {
        binding.progressBar.setVisibility(View.VISIBLE);
        String url = "http://www.colourlovers.com/api/colors/random&format=json";
        final RequestQueue queue = Volley.newRequestQueue(this);
        queue.getCache().clear();
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                String hex = jsonObject.getString("hex");

                JSONObject rgb = jsonObject.getJSONObject("rgb");
                JSONObject hsv = jsonObject.getJSONObject("hsv");

                int r = rgb.getInt("red");
                int g = rgb.getInt("green");
                int b = rgb.getInt("blue");

                int h = hsv.getInt("hue");
                int s = hsv.getInt("saturation");
                int v = hsv.getInt("value");

                String hexCode = "#" + hex;

                binding.hexValue.setText(hexCode);

                binding.hexValue.setOnClickListener(view -> {
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("hexCode", hexCode);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(this, "Copy to Clipboard", Toast.LENGTH_SHORT).show();
                });

                String rgbValue = "RGB(" + r + ", " + g + ", " + b + ")";
                binding.rgbValue.setText(rgbValue);
                String hsvValue = "HSV(" + h + ", " + s + ", " + v + ")";
                binding.hsvValue.setText(hsvValue);

                getImageData(hex);

                findTextColorOnBackground(hex);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> binding.progressBar.setVisibility(View.GONE));
        queue.add(request);
    }

    private void findTextColorOnBackground(String hex) {
        int color = Color.parseColor("#" + hex);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int luminance = (int) Math.sqrt(
                red * red * .241 +
                        green * green * .691 +
                        blue * blue * .068);
        if (luminance > 130) {
            binding.hexValue.setTextColor(Color.BLACK);
            binding.rgbValue.setTextColor(Color.BLACK);
            binding.hsvValue.setTextColor(Color.BLACK);
        } else {
            binding.hexValue.setTextColor(Color.WHITE);
            binding.rgbValue.setTextColor(Color.WHITE);
            binding.hsvValue.setTextColor(Color.WHITE);
        }
    }

    private void getImageData(String hex) {
        String url = "https://php-noise.com/noise.php?hex=" + hex + "&json";
        final RequestQueue queue = Volley.newRequestQueue(this);
        queue.getCache().clear();
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            binding.progressBar.setVisibility(View.GONE);
            try {
                JSONObject jsonObject = new JSONObject(response);
                String uri = jsonObject.getString("uri");
                Glide.with(this).load(uri).into(binding.imageBackground);
                String hexCode = "#" + hex;
                binding.colorImage.setColorFilter(Color.parseColor(hexCode));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> binding.progressBar.setVisibility(View.GONE));
        queue.add(request);
    }
}