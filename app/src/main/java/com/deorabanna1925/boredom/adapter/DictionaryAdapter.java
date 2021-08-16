package com.deorabanna1925.boredom.adapter;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.deorabanna1925.boredom.databinding.ItemDictionaryBinding;
import com.deorabanna1925.boredom.model.ModelDictionary;

import java.io.IOException;
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

        holder.word.setText(model.getWord());
        holder.phonetic.setText(model.getPhonetic());

        holder.phonetics.setLayoutManager(new LinearLayoutManager(context));
        holder.phonetics.setNestedScrollingEnabled(false);
        holder.phonetics.setHasFixedSize(true);
        holder.phonetics.setAdapter(new DictionaryPhoneticsAdapter(context,model.getPhonetics()));

        holder.meanings.setLayoutManager(new LinearLayoutManager(context));
        holder.meanings.setNestedScrollingEnabled(false);
        holder.meanings.setHasFixedSize(true);
        holder.meanings.setAdapter(new DictionaryMeaningsAdapter(context,model.getMeanings()));

        holder.origin.setText(model.getOrigin());

    }

    private void playAudio(String audioUrl,float speed) {

        // initializing media player
        MediaPlayer mediaPlayer = new MediaPlayer();

        // below line is use to set the audio
        // stream type for our media player.
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        // below line is use to set our
        // url to our media player.
        try {
            mediaPlayer.setDataSource(audioUrl);
            // below line is use to prepare
            // and start our media player.
            mediaPlayer.prepare();
            mediaPlayer.start();
            PlaybackParams params = mediaPlayer.getPlaybackParams();
            params.setSpeed(speed);
            mediaPlayer.setPlaybackParams(params);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView word;
        public TextView phonetic;
        public RecyclerView phonetics;
        public TextView origin;
        public RecyclerView meanings;

        public ViewHolder(@NonNull ItemDictionaryBinding binding) {
            super(binding.getRoot());
            word = binding.word;
            phonetic = binding.phonetic;
            phonetics = binding.phonetics;
            origin = binding.origin;
            meanings = binding.meanings;
        }

    }
}
