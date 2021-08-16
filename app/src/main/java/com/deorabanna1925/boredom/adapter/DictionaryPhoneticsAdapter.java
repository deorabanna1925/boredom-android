package com.deorabanna1925.boredom.adapter;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deorabanna1925.boredom.databinding.ItemDictionaryPhoneticsBinding;
import com.deorabanna1925.boredom.model.ModelDictionary;

import java.io.IOException;
import java.util.ArrayList;

public class DictionaryPhoneticsAdapter extends RecyclerView.Adapter<DictionaryPhoneticsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ModelDictionary.Phonetics> arrayList;

    public DictionaryPhoneticsAdapter(Context context, ArrayList<ModelDictionary.Phonetics> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public DictionaryPhoneticsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDictionaryPhoneticsBinding binding = ItemDictionaryPhoneticsBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DictionaryPhoneticsAdapter.ViewHolder holder, int position) {
        ModelDictionary.Phonetics model = arrayList.get(position);

        holder.text.setText(model.getText());

        holder.audio.setOnClickListener(view -> {
            if(model.getAudio()!=null){
                String audioUrl = "http:" + model.getAudio();
                playAudio(audioUrl,1.0f);
            }else {
                Toast.makeText(context, "No Audio", Toast.LENGTH_SHORT).show();
            }
        });

        holder.audio.setOnLongClickListener(view -> {
            if(model.getAudio()!=null){
                String audioUrl = "http:" + model.getAudio();
                playAudio(audioUrl,0.5f);
            }else {
                Toast.makeText(context, "No Audio", Toast.LENGTH_SHORT).show();
            }
            return true;
        });

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

        public TextView text;
        public ImageView audio;

        public ViewHolder(@NonNull ItemDictionaryPhoneticsBinding binding) {
            super(binding.getRoot());
            text = binding.text;
            audio = binding.audio;
        }

    }
}
