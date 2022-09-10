package com.deorabanna1925.boredom.activity;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.deorabanna1925.boredom.databinding.ActivityColorsBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class ColorsActivity extends AppCompatActivity {

    private ActivityColorsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityColorsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();

        String color = getRandomHexColor();
        binding.backgroundColor.setBackgroundColor(Color.parseColor(color));
        binding.hex.setText(color);
        binding.hex.setTextColor(Color.parseColor(findTextColorOnBackground(color)));
        int[] rgbCode = convertHexToRgb(color);
        String rgb = "rgb(" + rgbCode[0] + ", " + rgbCode[1] + ", " + rgbCode[2] + ")";
        binding.rgb.setText(rgb);
        binding.rgb.setTextColor(Color.parseColor(findTextColorOnBackground(color)));
        int[] hsvCode = convertHexToHsv(color);
        String hsv = "hsv(" + hsvCode[0] + ", " + hsvCode[1] + ", " + hsvCode[2] + ")";
        binding.hsv.setText(hsv);
        binding.hsv.setTextColor(Color.parseColor(findTextColorOnBackground(color)));

        binding.allShadesTitle.setTextColor(Color.parseColor(color));
        binding.allShadesCard.setCardBackgroundColor(Color.parseColor(findTextColorOnBackground(color)));

        getImageData(color);
        String removedHashFromHex = color.replace("#", "");
        Glide.with(this).load("https://seewhizz.com/noise.php?hex=").into(binding.colorImage);

        String[] lightColors = generateColor(color,"#ffffff",10);
        String[] darkColors = generateColor("#000000",color,10);
        ArrayList<String> colors = new ArrayList<>();
        Collections.addAll(colors, lightColors);
        colors.add(color);
        Collections.addAll(colors, darkColors);
        binding.allShades.removeAllViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,8,0,8);
        tvParams.setMargins(16,32,16,32);
        params.gravity = Gravity.CENTER;
        for (String hexColor : colors) {
            LinearLayout view = new LinearLayout(this);
            TextView textView = new TextView(this);
            textView.setText(hexColor.toUpperCase(Locale.ENGLISH));
            textView.setLayoutParams(tvParams);
            textView.setTextSize(32);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.parseColor(findTextColorOnBackground(hexColor)));
            view.addView(textView);
            view.setLayoutParams(params);
            view.setBackgroundColor(Color.parseColor(hexColor));
            binding.allShades.addView(view);
        }
    }

    private String findTextColorOnBackground(String hex) {
        int color = Color.parseColor(hex);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int luminance = (int) Math.sqrt(
                red * red * .241 +
                        green * green * .691 +
                        blue * blue * .068);
        if (luminance > 130) {
            return "#000000";
        } else {
            return "#ffffff";
        }
    }

    private String getRandomHexColor() {
        StringBuilder hexCode = new StringBuilder("#");
        for (int i = 0; i < 6; i++) {
            int random = (int) (Math.random() * 16);
            hexCode.append(Integer.toHexString(random));
        }
        return hexCode.toString();
    }

    private int[] convertHexToRgb(String hex) {
        int red = Integer.parseInt(hex.substring(1, 3), 16);
        int green = Integer.parseInt(hex.substring(3, 5), 16);
        int blue = Integer.parseInt(hex.substring(5, 7), 16);
        return new int[]{red, green, blue};
    }

    private int[] convertHexToHsv(String hex){
        int[] rgb = convertHexToRgb(hex);
        int red = rgb[0];
        int green = rgb[1];
        int blue = rgb[2];
        float[] hsv = new float[3];
        Color.RGBToHSV(red, green, blue, hsv);
        int hue = (int) hsv[0];
        int saturation = (int) (hsv[1] * 100);
        int value = (int) (hsv[2] * 100);
        return new int[]{hue,saturation,value};
    }

    private void getImageData(String hex) {
        hex = hex.substring(1);
        String url = "https://php-noise.com/noise.php?hex=" + hex + "&json";
        final RequestQueue queue = Volley.newRequestQueue(this);
        queue.getCache().clear();
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                String uri = jsonObject.getString("uri");
                Glide.with(this).load(Uri.parse(uri)).into(binding.colorImage);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show());
        queue.add(request);
    }

    private String[] generateColor(String startColor, String endColor, int count) {
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
        return convertIntToHex(saida);
    }

    private String[] convertIntToHex(int[] saida) {
        String[] hex = new String[saida.length];
        for (int i = 0; i < saida.length; i++) {
            hex[i] = String.format("#%06X", (0xFFFFFF & saida[i]));
        }
        return hex;
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

}