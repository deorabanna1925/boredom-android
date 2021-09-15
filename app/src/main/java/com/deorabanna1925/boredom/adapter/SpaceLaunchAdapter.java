package com.deorabanna1925.boredom.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
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

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class SpaceLaunchAdapter extends RecyclerView.Adapter<SpaceLaunchAdapter.ViewHolder> {

    private final String DATE_FORMAT = "yyyy-MM-dd";
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
//        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
//        SimpleDateFormat output = new SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.ENGLISH);
//        try {
//            // parse input
//            Date newDate = input.parse(model.getNet());
//            holder.countdown.setText(output.format(newDate));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        ZonedDateTime dateTime = ZonedDateTime.parse(model.getNet());
        String res = dateTime.withZoneSameInstant(ZoneId.of("IST")).format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        holder.dateAndTime.setText(res);
        countDownStart(holder,model.getNet());
        if (model.getMission() != null) {
            holder.missionName.setText(model.getMission().getName());
            holder.missionDescription.setText(model.getMission().getDescription());
        }

    }

    private void countDownStart(ViewHolder holder, String releaseDate) {
        Handler handler = new Handler(Looper.getMainLooper());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    handler.postDelayed(this, 1000);
                    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
                    Calendar c = Calendar.getInstance();
                    c.setTime(Objects.requireNonNull(dateFormat.parse(releaseDate)));
                    Date date = c.getTime();
                    String newDate = dateFormat.format(date.getTime());
                    Date event_date = dateFormat.parse(newDate);
                    Date current_date = new Date();
                    if (!current_date.after(event_date)) {
                        assert event_date != null;
                        long diff = event_date.getTime() - current_date.getTime();
                        long Days = diff / (24 * 60 * 60 * 1000);
                        long Hours = diff / (60 * 60 * 1000) % 24;
                        long Minutes = diff / (60 * 1000) % 60;
                        long Seconds = diff / 1000 % 60;

                        @SuppressLint("DefaultLocale")
                        String countdown = String.format("%02dd, %02dh, %02dm, %02ds", Days, Hours, Minutes, Seconds);
                        countdown = "T- " + countdown;
                        holder.countdown.setText(countdown);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 0);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView name;
        public TextView staus;
        public TextView countdown;
        public TextView dateAndTime;
        public TextView missionName;
        public TextView missionDescription;

        public ViewHolder(@NonNull ItemSpaceLaunchesBinding binding) {
            super(binding.getRoot());
            image = binding.image;
            name = binding.name;
            staus = binding.staus;
            countdown = binding.countdown;
            dateAndTime = binding.dateAndTime;
            missionName = binding.missionName;
            missionDescription = binding.missionDescription;
        }

    }
}
