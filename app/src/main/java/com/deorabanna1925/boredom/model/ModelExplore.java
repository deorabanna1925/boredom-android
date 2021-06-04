package com.deorabanna1925.boredom.model;

import android.app.Activity;

public class ModelExplore {

    private String title;
    private String sub_title;
    private Class<?> activity;

    public ModelExplore() {
    }

    public ModelExplore(String title, String sub_title, Class<?> activity) {
        this.title = title;
        this.sub_title = sub_title;
        this.activity = activity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public Class<?> getActivity() {
        return activity;
    }

    public void setActivity(Class<?> activity) {
        this.activity = activity;
    }
}
