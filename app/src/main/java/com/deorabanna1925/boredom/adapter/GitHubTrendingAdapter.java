package com.deorabanna1925.boredom.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.deorabanna1925.boredom.databinding.ItemGithubTrendingBinding;
import com.deorabanna1925.boredom.model.ModelGitHub;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class GitHubTrendingAdapter extends RecyclerView.Adapter<GitHubTrendingAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ModelGitHub> arrayList;

    public GitHubTrendingAdapter(Context context, ArrayList<ModelGitHub> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public GitHubTrendingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGithubTrendingBinding binding = ItemGithubTrendingBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull GitHubTrendingAdapter.ViewHolder holder, int position) {
        ModelGitHub model = arrayList.get(position);

        CircularProgressDrawable progressDrawable = new CircularProgressDrawable(context);
        progressDrawable.setStrokeWidth(5f);
        progressDrawable.setCenterRadius(30f);
        progressDrawable.start();

        if(model.getAvatar()!=null){
            Glide.with(context)
                    .load(model.getAvatar())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.image);
        }

        holder.author.setText(model.getAuthor());
        holder.name.setText(model.getName());
        holder.description.setText(model.getDescription());
        holder.language.setText(model.getLanguage());
        holder.stars.setText(model.getStars().toString());
        holder.forks.setText(model.getForks().toString());
        holder.open.setOnClickListener(view -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(model.getUrl()));
            context.startActivity(browserIntent);
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CircleImageView image;
        public TextView author;
        public TextView name;
        public ImageView open;
        public TextView description;
        public TextView language;
        public TextView stars;
        public TextView forks;

        public ViewHolder(@NonNull ItemGithubTrendingBinding binding) {
            super(binding.getRoot());

            image = binding.image;
            author = binding.author;
            name = binding.name;
            open = binding.open;
            description = binding.description;
            language = binding.language;
            stars = binding.stars;
            forks = binding.forks;

        }

    }
}
