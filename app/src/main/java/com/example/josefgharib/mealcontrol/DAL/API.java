package com.example.josefgharib.mealcontrol.DAL;

import android.util.Log;

import com.example.josefgharib.mealcontrol.BE.BEFoodNutrition;
import com.example.josefgharib.mealcontrol.BE.BEFoodSearch;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by josefgharib on 21/05/16.
 */
public class API {

    //String JSONresponse;
    JSONObject JSONresponse;
    JSONObject JSONsingleResponse;
    final String APIKEY = "DEMO_KEY";
    boolean searching = false;

    private static API instance = null;

    public static API getInstance() {
        if(instance == null) {
            instance = new API();
        }
        return instance;
    }

    private String searchString(String searchtext) {
        return "http://api.nal.usda.gov/ndb/search/?format=json&q="+searchtext+"&sort=n&max=25&offset=0&api_key="+APIKEY;
    }

    private String getFoodURL(int id) {
        return "http://api.nal.usda.gov/ndb/reports/?ndbno="+Integer.toString(id)+"&type=f&format=json&api_key="+APIKEY;
    }


    private static SyncHttpClient syncClient(){
        return new SyncHttpClient();
    }

    public ArrayList<BEFoodSearch> searchResult(String searchText) {
        syncClient().get(searchString(searchText), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONresponse = response;
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                JSONresponse = null;
            }
        });
        if(JSONresponse == null){
            return null;
        }
        ArrayList<BEFoodSearch> searchresult = new ArrayList<>();
        try{
            JSONObject fullobject = JSONresponse.getJSONObject("list");
            JSONArray itemlist = fullobject.getJSONArray("item");
            for (int i = 0; i<itemlist.length(); i++) {
                JSONObject item = (JSONObject) itemlist.get(i);
                searchresult.add(new BEFoodSearch(item.getInt("ndbno"),item.getString("name"),item.getString("group")));
            }
        }catch(JSONException e){
            Log.v("JSON error: ", e.getMessage());
        }
        return searchresult;
    }


    public BEFoodNutrition getNutrition(int id){
        syncClient().get(getFoodURL(id), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONsingleResponse = response;
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                JSONsingleResponse = null;
            }
        });
        if(JSONsingleResponse == null){
            return null;
        }
        BEFoodNutrition singleFood = null;
        try{
            JSONObject foodobject = JSONsingleResponse.getJSONObject("report").getJSONObject("food");
            String name = foodobject.getString("name");
            JSONArray nutrientarray = foodobject.getJSONArray("nutrients");
            int proteins = 0;
            int carbs = 0;
            int fat = 0;
            int kcal = 0;
            for (int i = 0; i<nutrientarray.length(); i++) {
                JSONObject nutrient = (JSONObject)nutrientarray.get(i);
                int nutId = nutrient.getInt("nutrient_id");

                switch(nutId){
                    case 203:
                        proteins = nutrient.getInt("value");
                        break;
                    case 204:
                        fat = nutrient.getInt("value");
                        break;
                    case 205:
                        carbs = nutrient.getInt("value");
                        break;
                    case 208:
                        kcal = nutrient.getInt("value");
                        break;
                }
            }
            singleFood = new BEFoodNutrition(name,proteins,carbs,fat,kcal);
        }catch(JSONException e){
            Log.v("JSON error: ", e.getMessage());
            return null;
        }

        return singleFood;
    }

}
