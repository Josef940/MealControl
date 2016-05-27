package com.example.josefgharib.mealcontrol.BE;

/**
 * Created by josefgharib on 21/05/16.
 */
public class BEFoodNutrition {

    private String mname;
    private int mproteins;
    private int mcarbs;
    private int mfat;
    private int mkcal;

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public int getMproteins() {
        return mproteins;
    }

    public void setMproteins(int mproteins) {
        this.mproteins = mproteins;
    }

    public int getMcarbs() {
        return mcarbs;
    }

    public void setMcarbs(int mcarbs) {
        this.mcarbs = mcarbs;
    }

    public int getMfat() {
        return mfat;
    }

    public void setMfat(int mfat) {
        this.mfat = mfat;
    }

    public int getMkcal() {
        return mkcal;
    }

    public void setMkcal(int mkcal) {
        this.mkcal = mkcal;
    }

    public BEFoodNutrition(String name, int proteins, int carbs, int fat, int kcal){
        mname = name;
        mproteins = proteins;
        mcarbs = carbs;
        mfat = fat;
        mkcal = kcal;
    }
}
