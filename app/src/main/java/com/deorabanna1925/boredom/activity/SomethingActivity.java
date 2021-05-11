package com.deorabanna1925.boredom.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.deorabanna1925.boredom.model.ModelSomething;
import com.deorabanna1925.boredom.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SomethingActivity extends AppCompatActivity {

    private TextView tvSomethingActivity;
    private TextView tvSomethingType;
    private TextView tvSomethingParticipants;
    private TextView tvSomethingPrice;
    private TextView tvSomethingLink;
    private TextView tvSomethingKey;
    private TextView tvSomethingAccessibility;

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_something);

        tvSomethingActivity = findViewById(R.id.tv_something_activity);
        tvSomethingType = findViewById(R.id.tv_something_type);
        tvSomethingParticipants = findViewById(R.id.tv_something_participants);
        tvSomethingPrice = findViewById(R.id.tv_something_price);
        tvSomethingLink = findViewById(R.id.tv_something_link);
        tvSomethingKey = findViewById(R.id.tv_something_key);
        tvSomethingAccessibility = findViewById(R.id.tv_something_accessibility);

        getSomethingData();

    }

    private void getSomethingData() {
//        arrayList = new ArrayList<>();
        showDlg(this);
        String url = "https://www.boredapi.com/api/";
        url += "activity";
        final RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.getCache().clear();
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            hideDlg();
            try {
                JSONObject jsonObject = new JSONObject(response);
//                arrayList.clear();
                if (jsonObject != null) {
                    if (jsonObject.has("error")) {

                        String error = jsonObject.getString("error");
                        Toast.makeText(this, "" + error.toString(), Toast.LENGTH_SHORT).show();

                    } else {

                        String activity = jsonObject.getString("activity");
                        String type = jsonObject.getString("type");
                        int participants = jsonObject.getInt("participants");
                        double price = jsonObject.getDouble("price");
                        String link = jsonObject.getString("link");
                        long key = jsonObject.getLong("key");
                        double accessibility = jsonObject.getDouble("accessibility");

                        tvSomethingActivity.setText(activity);
                        tvSomethingType.setText(type);
                        tvSomethingParticipants.setText(String.valueOf(participants));
                        tvSomethingPrice.setText(String.valueOf(price));
                        tvSomethingLink.setText(link);
                        tvSomethingKey.setText(String.valueOf(key));
                        tvSomethingAccessibility.setText(String.valueOf(accessibility));
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> hideDlg());
        queue.add(request);
    }

    private void showDlg(Context c) {

        try {
            if (dialog == null) {
                dialog = new ProgressDialog(c);
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            } else {
                dialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hideDlg() {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateNew(View view) {
        getSomethingData();
    }
}