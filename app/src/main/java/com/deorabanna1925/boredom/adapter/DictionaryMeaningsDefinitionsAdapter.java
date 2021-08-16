package com.deorabanna1925.boredom.adapter;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deorabanna1925.boredom.databinding.ItemDictionaryMeaningsDefinitionsBinding;
import com.deorabanna1925.boredom.model.ModelDictionary;

import java.util.ArrayList;

public class DictionaryMeaningsDefinitionsAdapter extends RecyclerView.Adapter<DictionaryMeaningsDefinitionsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ModelDictionary.Meanings.Definitions> arrayList;

    public DictionaryMeaningsDefinitionsAdapter(Context context, ArrayList<ModelDictionary.Meanings.Definitions> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public DictionaryMeaningsDefinitionsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDictionaryMeaningsDefinitionsBinding binding = ItemDictionaryMeaningsDefinitionsBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DictionaryMeaningsDefinitionsAdapter.ViewHolder holder, int position) {
        ModelDictionary.Meanings.Definitions model = arrayList.get(position);

        holder.definition.setText(model.getDefinition());
        holder.example.setText(model.getExample());

        String syn = "Synonyms :\n";
        for (String synonyms : model.getSynonyms()) {
            syn += " • ";
            syn += synonyms;
            boldText(syn,0,10,holder.synonyms);
            syn += "\n";
        }

        String ant = "Antonyms :\n";
        for (String antonyms : model.getAntonyms()) {
            ant += " • ";
            ant += antonyms;
            boldText(ant,0,10,holder.antonyms);
            ant += "\n";
        }

    }

    private void boldText(String TEXT, int INT_START, int INT_END, TextView view) {
        SpannableStringBuilder str = new SpannableStringBuilder(TEXT);
        str.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), INT_START, INT_END, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        view.setText(str);
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView definition;
        public TextView example;
        public TextView synonyms;
        public TextView antonyms;

        public ViewHolder(@NonNull ItemDictionaryMeaningsDefinitionsBinding binding) {
            super(binding.getRoot());
            definition = binding.definition;
            example = binding.example;
            synonyms = binding.synonyms;
            antonyms = binding.antonyms;
        }

    }
}
