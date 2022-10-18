package com.deorabanna1925.boredom.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.deorabanna1925.boredom.databinding.ActivityInstaViewerBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class InstaViewerActivity extends AppCompatActivity {

    private ActivityInstaViewerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInstaViewerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSearch.setOnClickListener(v -> {
            String username = binding.etSearch.getText().toString();
            if (username.isEmpty()) {
                binding.etSearch.setError("Enter Username");
                return;
            }
            binding.etSearch.setError(null);
            getData(username);
        });
    }

    private void getData(String username) {
        String url = "https://www.instagram.com/" + username + "/?__a=1";
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.getCache().clear();
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONObject user = jsonObject.getJSONObject("graphql").getJSONObject("user");
                String name = user.getString("full_name");
                String bio = user.getString("biography");
                String followers = user.getJSONObject("edge_followed_by").getString("count");
                String following = user.getJSONObject("edge_follow").getString("count");
                String posts = user.getJSONObject("edge_owner_to_timeline_media").getString("count");
                String profilePic = user.getString("profile_pic_url_hd");
                String isPrivate = user.getString("is_private");
                String isVerified = user.getString("is_verified");
                String id = user.getString("id");
                String username1 = user.getString("username");
                String externalUrl = user.getString("external_url");
                String businessCategoryName = user.getString("business_category_name");
                String businessEmail = user.getString("business_email");
                String businessPhoneNumber = user.getString("business_phone_number");
                String businessAddressJson = user.getString("business_address_json");
                String highlightReelCount = user.getString("highlight_reel_count");
                String hasChannel = user.getString("has_channel");
                String channelUrl = user.getString("channel_url");
                String connectedFbPage = user.getString("connected_fb_page");
                String edgeFelixVideoTimelineCount = user.getString("edge_felix_video_timeline_count");
                String edgeFelixVideoTimelinePageInfoHasNextPage = user.getJSONObject("edge_felix_video_timeline").getJSONObject("page_info").getString("has_next_page");
                String edgeFelixVideoTimelinePageInfoEndCursor = user.getJSONObject("edge_felix_video_timeline").getJSONObject("page_info").getString("end_cursor");
                String edgeFelixVideoTimelineEdgesNodeVideoUrl = user.getJSONObject("edge_felix_video_timeline").getJSONArray("edges").getJSONObject(0).getJSONObject("node").getString("video_url");
                String edgeFelixVideoTimelineEdgesNodeVideoViewCount = user.getJSONObject("edge_felix_video_timeline").getJSONArray("edges").getJSONObject(0).getJSONObject("node").getString("video_view_count");
                String edgeFelixVideoTimelineEdgesNodeThumbnailSrc = user.getJSONObject("edge_felix_video_timeline").getJSONArray("edges").getJSONObject(0).getJSONObject("node").getString("thumbnail_src");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            Toast.makeText(this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

}