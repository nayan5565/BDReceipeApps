package com.example.nayan.newmybdreceipetest.model;

import java.util.ArrayList;

/**
 * Created by NAYAN on 11/2/2016.
 */
public class MCategoryObject {
    private int totalCategory,totalVideo,adShowTime;
    private String letter;
    private ArrayList<MCategory> categories;

    public int getTotalCategory() {
        return totalCategory;
    }

    public void setTotalCategory(int totalCategory) {
        this.totalCategory = totalCategory;
    }

    public int getTotalVideo() {
        return totalVideo;
    }

    public void setTotalVideo(int totalVideo) {
        this.totalVideo = totalVideo;
    }

    public int getAdShowTime() {
        return adShowTime;
    }

    public void setAdShowTime(int adShowTime) {
        this.adShowTime = adShowTime;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public ArrayList<MCategory> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<MCategory> categories) {
        this.categories = categories;
    }
}
