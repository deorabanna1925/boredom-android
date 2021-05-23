package com.deorabanna1925.boredom.model;

import java.util.ArrayList;

public class ModelGradient {

    private String name;
    private ArrayList<String> colors;

    public ModelGradient() {
    }

    public ModelGradient(String name, ArrayList<String> colors) {
        this.name = name;
        this.colors = colors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getColors() {
        return colors;
    }

    public void setColors(ArrayList<String> colors) {
        this.colors = colors;
    }
}
