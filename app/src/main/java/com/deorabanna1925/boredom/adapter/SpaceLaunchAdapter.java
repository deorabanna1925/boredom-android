package com.deorabanna1925.boredom.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.deorabanna1925.boredom.databinding.ItemSpaceLaunchesBinding;
import com.deorabanna1925.boredom.model.ModelSpaceLaunch;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SpaceLaunchAdapter extends RecyclerView.Adapter<SpaceLaunchAdapter.ViewHolder> {

    private final String DATE_FORMAT = "dd MMM yyyy, hh:mm a";
    private Context context;
    private ArrayList<ModelSpaceLaunch> arrayList;

    public SpaceLaunchAdapter(Context context, ArrayList<ModelSpaceLaunch> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public SpaceLaunchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSpaceLaunchesBinding binding = ItemSpaceLaunchesBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull SpaceLaunchAdapter.ViewHolder holder, int position) {
        ModelSpaceLaunch model = arrayList.get(position);

        CircularProgressDrawable progressDrawable = new CircularProgressDrawable(context);
        progressDrawable.setStrokeWidth(5f);
        progressDrawable.setCenterRadius(30f);
        progressDrawable.start();

        if (model.getImage() != null) {
            Glide.with(context)
                    .load(model.getImage())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(progressDrawable)
                    .skipMemoryCache(true)
                    .into(holder.image);
        }

        holder.name.setText(model.getName());
        holder.staus.setText(model.getStatus().getName());
        ZonedDateTime dateTime = ZonedDateTime.parse(model.getNet());
        ZonedDateTime zonedDateTime = dateTime.withZoneSameInstant(ZoneId.of("IST"));
        String res = zonedDateTime.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        holder.dateAndTime.setText(res);
        if (model.getMission() != null) {
            holder.missionName.setText(model.getMission().getName());
            holder.missionDescription.setText(model.getMission().getDescription());
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView name;
        public TextView staus;
        public TextView dateAndTime;
        public TextView missionName;
        public TextView missionDescription;

        public ViewHolder(@NonNull ItemSpaceLaunchesBinding binding) {
            super(binding.getRoot());
            image = binding.image;
            name = binding.name;
            staus = binding.staus;
            dateAndTime = binding.dateAndTime;
            missionName = binding.missionName;
            missionDescription = binding.missionDescription;
        }

    }
}
