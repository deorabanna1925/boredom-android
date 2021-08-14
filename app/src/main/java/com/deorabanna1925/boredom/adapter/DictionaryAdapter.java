package com.deorabanna1925.boredom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deorabanna1925.boredom.databinding.ItemDictionaryBinding;
import com.deorabanna1925.boredom.model.ModelDictionary;

import java.util.ArrayList;

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ModelDictionary> arrayList;

    public DictionaryAdapter(Context context, ArrayList<ModelDictionary> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public DictionaryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDictionaryBinding binding = ItemDictionaryBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DictionaryAdapter.ViewHolder holder, int position) {
        ModelDictionary model = arrayList.get(position);

        Toast.makeText(context, "" + model.getWord(), Toast.LENGTH_SHORT).show();
//        holder.word.setText(model.getWord());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView word;

        public ViewHolder(@NonNull ItemDictionaryBinding binding) {
            super(binding.getRoot());
            word = binding.word;
        }

    }
}
