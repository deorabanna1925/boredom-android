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

                int[] lightColors = generateColor(hexCode,"#ffffff",20);
                int[] darkColors = generateColor("#000000",hexCode,19);

                binding.colorVariationLight.removeAllViews();
                binding.colorVariationDark.removeAllViews();
                for (int lightColor : lightColors) {
                    View view = new View(this);
                    view.setLayoutParams(new ActionBar.LayoutParams(100, 100));
                    view.setBackgroundColor(Color.parseColor("#" + Integer.toHexString(lightColor)));
                    binding.colorVariationLight.addView(view);
                }
                for (int darkColor : darkColors) {
                    View view = new View(this);
                    view.setLayoutParams(new ActionBar.LayoutParams(100, 100));
                    view.setBackgroundColor(Color.parseColor("#" + Integer.toHexString(darkColor)));
                    binding.colorVariationDark.addView(view);
                }

                binding.progressBar.setVisibility(View.GONE);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> binding.progressBar.setVisibility(View.GONE));
        queue.add(request);
    }

    private int[] generateColor(String startColor, String endColor, int count) {
//        the beginning of your gradient
        int[] start = getRGB(startColor);
//        the end of your gradient
        int[] end = getRGB(endColor);
//        the number of colors to compute
        //        alpha blending amount
        double alpha = 0.0;
        int[] saida = new int[count];
        for (int i = 0; i < count; i++) {
            int[] c = new int[3];
            alpha += 1.0 / count;
            c[0] = (int) (start[0] * alpha + (1 - alpha) * end[0]);
            c[1] = (int) (start[1] * alpha + (1 - alpha) * end[1]);
            c[2] = (int) (start[2] * alpha + (1 - alpha) * end[2]);

            saida[i] = getHex(c[0], c[1], c[2]);
        }
        return saida;
    }

    private int getHex(int r, int g, int b) {
        return Color.rgb(r, g, b);
    }

    private int[] getRGB(String hexColor) {
        int color = Color.parseColor(hexColor);
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        return new int[]{r, g, b};
    }

    private void findMoreColors(int light,String hex,int dark) {
        hex = "#" + hex;
//        create 15 shades of color by mixing white
        String[] lightColors = new String[light];
        for (int i = 0; i < light; i++) {
            lightColors[i] = mixColors("#FFFFFF", hex, (float) i / light);
        }
//        create 15 shades of color by mixing black
        String[] darkColors = new String[dark];
        for (int i = 0; i < dark; i++) {
            darkColors[i] = mixColors("#000000", hex, (float) i / dark);
        }

        binding.colorVariationLight.removeAllViews();
        for (String lightColor : lightColors) {
            View view = new View(this);
            view.setBackgroundColor(Color.parseColor(lightColor));
            view.setLayoutParams(new ActionBar.LayoutParams(100, 100));
            binding.colorVariationLight.addView(view);
        }
        binding.colorVariationDark.removeAllViews();
        for (String darkColor : darkColors) {
            View view = new View(this);
            view.setBackgroundColor(Color.parseColor(darkColor));
            view.setLayoutParams(new ActionBar.LayoutParams(100, 100));
            binding.colorVariationDark.addView(view);
        }




    }

    private String mixColors(String s, String hex, float v) {
        int color1 = Color.parseColor(s);
        int color2 = Color.parseColor(hex);
        float[] hsv1 = new float[3];
        float[] hsv2 = new float[3];
        Color.colorToHSV(color1, hsv1);
        Color.colorToHSV(color2, hsv2);
        float h = (hsv2[0] - hsv1[0]) * v + hsv1[0];
        float s1 = (hsv2[1] - hsv1[1]) * v + hsv1[1];
        float b = (hsv2[2] - hsv1[2]) * v + hsv1[2];
        return String.format("#%02x%02x%02x", Math.round(Color.red(Color.HSVToColor(new float[]{h, s1, b}))), Math.round(Color.green(Color.HSVToColor(new float[]{h, s1, b}))), Math.round(Color.blue(Color.HSVToColor(new float[]{h, s1, b}))));
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