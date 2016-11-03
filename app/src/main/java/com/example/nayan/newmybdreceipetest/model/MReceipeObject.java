package com.example.nayan.newmybdreceipetest.model;

import java.util.ArrayList;

/**
 * Created by NAYAN on 11/3/2016.
 */
public class MReceipeObject {
    private String adShowTime;
    private ArrayList<MReceipe>recipes;

    public String getAdShowTime() {
        return adShowTime;
    }

    public void setAdShowTime(String adShowTime) {
        this.adShowTime = adShowTime;
    }

    public ArrayList<MReceipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(ArrayList<MReceipe> recipes) {
        this.recipes = recipes;
    }
}
