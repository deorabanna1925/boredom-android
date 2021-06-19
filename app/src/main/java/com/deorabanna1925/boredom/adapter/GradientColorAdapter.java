package com.deorabanna1925.boredom.adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deorabanna1925.boredom.databinding.ItemGradientColorBinding;

import java.util.ArrayList;

public class GradientColorAdapter extends RecyclerView.Adapter<GradientColorAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> arrayList;

    public GradientColorAdapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public GradientColorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGradientColorBinding binding = ItemGradientColorBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GradientColorAdapter.ViewHolder holder, int position) {
        String code = arrayList.get(position);
        holder.code.setText(code);
        if(code.length()<7){
            code = "#ffffff";
        }
        holder.code.setBackgroundColor(Color.parseColor(code));
        String finalCode = code;
        holder.code.setOnClickListener(view -> {
            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("hexCode", finalCode);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(context, "Copy to Clipboard", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView code;

        public ViewHolder(@NonNull ItemGradientColorBinding binding) {
            super(binding.getRoot());

            code = binding.code;

        }

    }
}
