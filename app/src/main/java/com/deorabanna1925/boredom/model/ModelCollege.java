package com.deorabanna1925.boredom.model;

import java.util.ArrayList;

public class ModelCollege {

    private String country;
    private ArrayList<String> web_pages;
    private String alpha_two_code;
    private String name;
    private ArrayList<String> domains;

    public ModelCollege() {
    }

    public ModelCollege(String country, ArrayList<String> web_pages, String alpha_two_code, String name, ArrayList<String> domains) {
        this.country = country;
        this.web_pages = web_pages;
        this.alpha_two_code = alpha_two_code;
        this.name = name;
        this.domains = domains;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public ArrayList<String> getWeb_pages() {
        return web_pages;
    }

    public void setWeb_pages(ArrayList<String> web_pages) {
        this.web_pages = web_pages;
    }

    public String getAlpha_two_code() {
        return alpha_two_code;
    }

    public void setAlpha_two_code(String alpha_two_code) {
        this.alpha_two_code = alpha_two_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getDomains() {
        return domains;
    }

    public void setDomains(ArrayList<String> domains) {
        this.domains = domains;
    }
}
