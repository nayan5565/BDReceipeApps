package com.example.nayan.newmybdreceipetest.model;

/**
 * Created by NAYAN on 11/1/2016.
 */
public class MCategory {
    String categoryTitle,categoryDetails,categoryPhoto,categoryThumb;
    int categoryTotalRecipe,categoryType,categoryUpdateAvailable,ordering, categoryId;


    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryDetails() {
        return categoryDetails;
    }

    public void setCategoryDetails(String categoryDetails) {
        this.categoryDetails = categoryDetails;
    }

    public String getCategoryPhoto() {
        return categoryPhoto;
    }

    public void setCategoryPhoto(String categoryPhoto) {
        this.categoryPhoto = categoryPhoto;
    }

    public String getCategoryThumb() {
        return categoryThumb;
    }

    public void setCategoryThumb(String categoryThumb) {
        this.categoryThumb = categoryThumb;
    }

    public int getCategoryTotalRecipe() {
        return categoryTotalRecipe;
    }

    public void setCategoryTotalRecipe(int categoryTotalRecipe) {
        this.categoryTotalRecipe = categoryTotalRecipe;
    }

    public int getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(int categoryType) {
        this.categoryType = categoryType;
    }

    public int getCategoryUpdateAvailable() {
        return categoryUpdateAvailable;
    }

    public void setCategoryUpdateAvailable(int categoryUpdateAvailable) {
        this.categoryUpdateAvailable = categoryUpdateAvailable;
    }

    public int getOrdering() {
        return ordering;
    }

    public void setOrdering(int ordering) {
        this.ordering = ordering;
    }
}
