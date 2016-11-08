package com.example.nayan.newmybdreceipetest.model;

/**
 * Created by NAYAN on 11/3/2016.
 */
public class MReceipe {
    private String Title,Photo,Thumb,Ingredients,Process,PPhoto,CategoryTitle,SearchTag;
    private int Id,CategoryId,TypeOne,TypeTwo,TypeThree,TypeFour,TypeFive,Fav;

    public int getFav() {
        return Fav;
    }

    public void setFav(int fav) {
        Fav = fav;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public String getThumb() {
        return Thumb;
    }

    public void setThumb(String thumb) {
        Thumb = thumb;
    }

    public String getIngredients() {
        return Ingredients;
    }

    public void setIngredients(String ingredients) {
        Ingredients = ingredients;
    }

    public String getProcess() {
        return Process;
    }

    public void setProcess(String process) {
        Process = process;
    }

    public String getPPhoto() {
        return PPhoto;
    }

    public void setPPhoto(String PPhoto) {
        this.PPhoto = PPhoto;
    }

    public String getCategoryTitle() {
        return CategoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        CategoryTitle = categoryTitle;
    }

    public String getSearchTag() {
        return SearchTag;
    }

    public void setSearchTag(String searchTag) {
        SearchTag = searchTag;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public int getTypeOne() {
        return TypeOne;
    }

    public void setTypeOne(int typeOne) {
        TypeOne = typeOne;
    }

    public int getTypeTwo() {
        return TypeTwo;
    }

    public void setTypeTwo(int typeTwo) {
        TypeTwo = typeTwo;
    }

    public int getTypeThree() {
        return TypeThree;
    }

    public void setTypeThree(int typeThree) {
        TypeThree = typeThree;
    }

    public int getTypeFour() {
        return TypeFour;
    }

    public void setTypeFour(int typeFour) {
        TypeFour = typeFour;
    }

    public int getTypeFive() {
        return TypeFive;
    }

    public void setTypeFive(int typeFive) {
        TypeFive = typeFive;
    }
}
