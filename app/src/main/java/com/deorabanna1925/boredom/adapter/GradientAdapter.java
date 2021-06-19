package com.deorabanna1925.boredom.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.deorabanna1925.boredom.databinding.ItemGradientBinding;
import com.deorabanna1925.boredom.model.ModelGradient;

import java.util.ArrayList;

public class GradientAdapter extends RecyclerView.Adapter<GradientAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ModelGradient> arrayList;

    public GradientAdapter(Context context, ArrayList<ModelGradient> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public GradientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGradientBinding binding = ItemGradientBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GradientAdapter.ViewHolder holder, int position) {
        ModelGradient model = arrayList.get(position);
        holder.name.setText(model.getName());

        int[] array = new int[model.getColors().size()];
        for (int i = 0; i < model.getColors().size(); i++) {
            array[i] = Color.parseColor(model.getColors().get(i).length() < 7 ? "#ffffff" : model.getColors().get(i));
        }

        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT, array);
        gd.setCornerRadius(0f);
        holder.name.setBackground(gd);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.name.getLayoutParams();
        params.height = 400;
        holder.name.setLayoutParams(params);

        holder.recyclerView.setVisibility(View.GONE);
        holder.colors.setOnClickListener(view -> {
            if(holder.recyclerView.getVisibility()== View.GONE){
                holder.recyclerView.setVisibility(View.VISIBLE);
            }else{
                holder.recyclerView.setVisibility(View.GONE);
            }
        });
        holder.rotate45Left.setOnClickListener(view -> {
            switch (gd.getOrientation()) {
                case LEFT_RIGHT:
                    gd.setOrientation(GradientDrawable.Orientation.BL_TR);
                    break;
                case BL_TR:
                    gd.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
                    break;
                case BOTTOM_TOP:
                    gd.setOrientation(GradientDrawable.Orientation.BR_TL);
                    break;
                case BR_TL:
                    gd.setOrientation(GradientDrawable.Orientation.RIGHT_LEFT);
                    break;
                case RIGHT_LEFT:
                    gd.setOrientation(GradientDrawable.Orientation.TR_BL);
                    break;
                case TR_BL:
                    gd.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
                    break;
                case TOP_BOTTOM:
                    gd.setOrientation(GradientDrawable.Orientation.TL_BR);
                    break;
                case TL_BR:
                    gd.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
                    break;
            }
            gd.setCornerRadius(0f);
            holder.name.setBackground(gd);
        });
        holder.rotate90.setOnClickListener(view -> {
            switch (gd.getOrientation()) {
                case LEFT_RIGHT:
                case BL_TR:
                    gd.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
                    break;
                case BOTTOM_TOP:
                case BR_TL:
                    gd.setOrientation(GradientDrawable.Orientation.RIGHT_LEFT);
                    break;
                case RIGHT_LEFT:
                case TR_BL:
                    gd.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
                    break;
                case TOP_BOTTOM:
                case TL_BR:
                    gd.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
                    break;
            }
            gd.setCornerRadius(0f);
            holder.name.setBackground(gd);
        });
        holder.rotate45Right.setOnClickListener(view -> {
            switch (gd.getOrientation()) {
                case LEFT_RIGHT:
                    gd.setOrientation(GradientDrawable.Orientation.TL_BR);
                    break;
                case TL_BR:
                    gd.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
                    break;
                case TOP_BOTTOM:
                    gd.setOrientation(GradientDrawable.Orientation.TR_BL);
                    break;
                case TR_BL:
                    gd.setOrientation(GradientDrawable.Orientation.RIGHT_LEFT);
                    break;
                case RIGHT_LEFT:
                    gd.setOrientation(GradientDrawable.Orientation.BR_TL);
                    break;
                case BR_TL:
                    gd.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
                    break;
                case BOTTOM_TOP:
                    gd.setOrientation(GradientDrawable.Orientation.BL_TR);
                    break;
                case BL_TR:
                    gd.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
                    break;
            }
            gd.setCornerRadius(0f);
            holder.name.setBackground(gd);
        });
        holder.fullscreen.setOnClickListener(view -> {
            if(params.height == 400){
                params.height = 800;
                holder.name.setLayoutParams(params);
            }else {
                params.height = 400;
                holder.name.setLayoutParams(params);
            }
        });

        holder.recyclerView.setLayoutManager(
                new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
        );
        holder.recyclerView.setNestedScrollingEnabled(false);
        holder.recyclerView.setHasFixedSize(true);

        holder.recyclerView.setAdapter(
                new GradientColorAdapter(context,model.getColors())
        );



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public ImageView colors;
        public ImageView rotate45Left;
        public ImageView rotate90;
        public ImageView rotate45Right;
        public ImageView fullscreen;

        public RecyclerView recyclerView;

        public ViewHolder(@NonNull ItemGradientBinding binding) {
            super(binding.getRoot());

            name = binding.name;
            colors = binding.colors;
            rotate45Left = binding.rotate45Left;
            rotate90 = binding.rotate90;
            rotate45Right = binding.rotate45Right;
            fullscreen = binding.fullscreen;
            recyclerView = binding.recyclerView;

        }

    }
}
