package com.example.josefgharib.mealcontrol.BE;

/**
 * Created by josefgharib on 21/05/16.
 */
public class BEFoodSearch {

    private int mid;
    private String mname;
    private String mgroup;

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMgroup() {
        return mgroup;
    }

    public void setMgroup(String mgroup) {
        this.mgroup = mgroup;
    }

    public BEFoodSearch(int id, String name, String group){
        mid = id;
        mname = name;
        mgroup = group;
    }
}
