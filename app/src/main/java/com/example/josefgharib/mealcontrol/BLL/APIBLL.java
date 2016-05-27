package com.example.josefgharib.mealcontrol.BLL;

import com.example.josefgharib.mealcontrol.BE.BEFoodNutrition;
import com.example.josefgharib.mealcontrol.BE.BEFoodSearch;
import com.example.josefgharib.mealcontrol.DAL.API;

import java.util.ArrayList;

/**
 * Created by josefgharib on 21/05/16.
 */
public class APIBLL {

    private static APIBLL instance = null;
    public static APIBLL getInstance() {
        if(instance == null) {
            instance = new APIBLL();
        }
        return instance;
    }

    private static API api = API.getInstance();

    public ArrayList<BEFoodSearch> searchResult(String searchText){
        return api.searchResult(searchText);
    }

    public BEFoodNutrition getNutrition(int id){
        return api.getNutrition(id);
    }
}
