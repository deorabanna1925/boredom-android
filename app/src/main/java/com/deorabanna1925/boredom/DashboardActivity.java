package com.deorabanna1925.boredom;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.deorabanna1925.boredom.databinding.ActivityDashboardBinding;
import com.deorabanna1925.boredom.fragment.ExploreFragment;
import com.deorabanna1925.boredom.fragment.HomeFragment;
import com.deorabanna1925.boredom.fragment.SettingsFragment;
import com.deorabanna1925.boredom.shared.Prefs;

import java.util.Calendar;

public class DashboardActivity extends AppCompatActivity {

    private ActivityDashboardBinding binding;
    private ActionBar actionBar;
    private Prefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = new Prefs(this);
        getDefaultTheme(prefs.getTheme());
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupActionBar();
        setupDefaultHomeScreen();
        setupBottomNavigation();

    }

    private void getDefaultTheme(int theme) {
        switch (theme) {
            case 1:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case 2:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case -1:
            default:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
        }
    }

    private void setupDefaultHomeScreen() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        binding.bottomNavigation.setSelectedItemId(R.id.bottom_nav_home);
        actionBar.setTitle(getHomeTitle());
    }

    private String getHomeTitle() {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        if(timeOfDay < 12){
            return "Good Morning";
        }else if(timeOfDay < 16){
            return "Good Afternoon";
        }else if(timeOfDay < 21){
            return "Good Evening";
        }else {
            return "Good Night";
        }
    }

    private void setupActionBar() {
        actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(getHomeTitle());
    }

    @SuppressLint("NonConstantResourceId")
    private void setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.bottom_nav_home:
                    selectedFragment = new HomeFragment();
                    actionBar.setTitle(getHomeTitle());
                    break;
                case R.id.bottom_nav_explore:
                    selectedFragment = new ExploreFragment();
                    actionBar.setTitle("Explore");
                    break;
                case R.id.bottom_nav_settings:
                    selectedFragment = new SettingsFragment();
                    actionBar.setTitle("Settings");
                    break;
            }

            assert selectedFragment != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            return true;
        });
    }

}