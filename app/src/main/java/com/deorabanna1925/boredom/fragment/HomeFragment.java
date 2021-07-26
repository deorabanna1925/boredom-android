package com.deorabanna1925.boredom.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.deorabanna1925.boredom.databinding.FragmentHomeBinding;

import java.util.Calendar;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        getDayTime();

        return view;
    }

    private void getDayTime() {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        if(timeOfDay < 12){
            binding.lottieView.setProgress(0.8f);
        }else if(timeOfDay < 16){
            binding.lottieView.setProgress(1.0f);
        }else if(timeOfDay < 21){
            binding.lottieView.setProgress(0.2f);
        }else {
            binding.lottieView.setProgress(0.5f);
        }
    }

}