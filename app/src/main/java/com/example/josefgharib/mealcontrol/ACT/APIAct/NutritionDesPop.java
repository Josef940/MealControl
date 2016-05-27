package com.example.josefgharib.mealcontrol.ACT.APIAct;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.josefgharib.mealcontrol.BLL.ActivityHelpers.Features;
import com.example.josefgharib.mealcontrol.BE.BEFoodNutrition;
import com.example.josefgharib.mealcontrol.BLL.APIBLL;
import com.example.josefgharib.mealcontrol.R;

/**
 * Created by josefgharib on 21/05/16.
 */
public class NutritionDesPop extends Activity {

    private Features feats = Features.getInstance();
    int foodId;
    private APIBLL api = APIBLL.getInstance();
    BEFoodNutrition food;
    TextView name,kcal, protein,carbs,fat,loading;
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_desc);

        feats.setPopWindowSize(this);

        Bundle bundleData = getIntent().getExtras();
        foodId = bundleData.getInt("id");
        initActivity();
        new GetNutrition().execute(foodId);
    }

    private void initActivity(){
        name = (TextView) findViewById(R.id.fooddesname);
        kcal = (TextView) findViewById(R.id.kcaltxt);
        protein = (TextView) findViewById(R.id.proteintxt);
        carbs = (TextView) findViewById(R.id.carbtxt);
        fat = (TextView) findViewById(R.id.fattxt);
        loading = (TextView) findViewById(R.id.loadingtxt);
        layout = (LinearLayout) findViewById(R.id.nutlayout);
        layout.setVisibility(View.INVISIBLE);
    }
    private void finishedLoading(){
        name.setText(food.getMname());
        kcal.setText(Integer.toString(food.getMkcal()));
        protein.setText(Integer.toString(food.getMproteins()));
        carbs.setText(Integer.toString(food.getMcarbs()));
        fat.setText(Integer.toString(food.getMfat()));
        loading.setVisibility(View.INVISIBLE);
        layout.setVisibility(View.VISIBLE);

    }

    private class GetNutrition extends AsyncTask<Integer, Void, Void> {

        @Override
        protected Void doInBackground(Integer... params) {
            food = api.getNutrition(params[0]);
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            if(food != null) {
                finishedLoading();
            }else{

            }
        }
    }
}
