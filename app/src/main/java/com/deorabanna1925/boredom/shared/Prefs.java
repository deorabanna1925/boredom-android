package com.deorabanna1925.boredom.shared;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    public final static String PREFS_NAME = "boredom_prefs";
    private static final String SEARCH_HISTORY_KEY = "_SEARCH_HISTORY_KEY";
    private static final String APP_THEME_KEY = "_APP_THEME_KEY";
    private final Context context;
    private final SharedPreferences prefs;


    public Prefs(Context context){
        this.context = context;
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void clear() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }

    public int getInt(String key) {
        return prefs.getInt(key,0);
    }
    public void setInt(String key, int value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }
    public boolean getBoolean(String key) {
        return prefs.getBoolean(key,false);
    }
    public void setBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }
    public String getString(String key) {
        return prefs.getString(key,"");
    }
    public void setString(String key, String value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getTvShowSearchHistory() {
        return prefs.getString(SEARCH_HISTORY_KEY,"");
    }
    public void setTvShowSearchHistory(String value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(SEARCH_HISTORY_KEY, value);
        editor.apply();
    }

    public int getTheme() {
        return prefs.getInt(APP_THEME_KEY,-1);
    }
    public void setTheme(int value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(APP_THEME_KEY, value);
        editor.apply();
    }

}
