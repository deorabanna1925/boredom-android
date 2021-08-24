package com.deorabanna1925.boredom.model;

public class ModelGitHub {

    private String author;
    private String name;
    private String langColor;
    private String description;
    private String url;
    private Integer stars;
    private Integer forks;
    private String language;
    private String avatar;

    public ModelGitHub() {
    }

    public ModelGitHub(String author, String name, String langColor, String description, String url, Integer stars, Integer forks, String language, String avatar) {
        this.author = author;
        this.name = name;
        this.langColor = langColor;
        this.description = description;
        this.url = url;
        this.stars = stars;
        this.forks = forks;
        this.language = language;
        this.avatar = avatar;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLangColor() {
        return langColor;
    }

    public void setLangColor(String langColor) {
        this.langColor = langColor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Integer getForks() {
        return forks;
    }

    public void setForks(Integer forks) {
        this.forks = forks;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
