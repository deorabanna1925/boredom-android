package com.deorabanna1925.boredom.shared;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    public final static String PREFS_NAME = "boredom_prefs";
    private final Context context;
    private final SharedPreferences prefs;


    public Prefs(Context context){
        this.context = context;
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void setString(String key, String value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key) {
        return prefs.getString(key,"");
    }

    public void clear() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }

}
