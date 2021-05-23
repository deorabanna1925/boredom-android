package com.deorabanna1925.boredom.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deorabanna1925.boredom.R;
import com.deorabanna1925.boredom.model.ModelGradient;
import com.deorabanna1925.boredom.model.ModelSomething;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gradient, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GradientAdapter.ViewHolder holder, int position) {
        ModelGradient model = arrayList.get(position);
        holder.name.setText(model.getName());

        int[] array = new int[model.getColors().size()];
        for (int i = 0; i < model.getColors().size(); i++) {
            array[i] = Color.parseColor(model.getColors().get(i).length() == 4 ? "#ffffff" : model.getColors().get(i));
        }

        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.RIGHT_LEFT, array);
        gd.setCornerRadius(0f);
        holder.name.setBackground(gd);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.item_gradient_color_name);

        }

    }
}
