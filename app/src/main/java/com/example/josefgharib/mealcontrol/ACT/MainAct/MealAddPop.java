package com.example.josefgharib.mealcontrol.ACT.MainAct;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.josefgharib.mealcontrol.BLL.ActivityHelpers.Features;
import com.example.josefgharib.mealcontrol.BE.BEMeal;
import com.example.josefgharib.mealcontrol.BLL.DBBLL;
import com.example.josefgharib.mealcontrol.R;

/**
 * Created by josefgharib on 01/05/16.
 */
public class MealAddPop extends Activity {
    DBBLL db;
    EditText title;
    EditText description;
    private Features feats = Features.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meal_add);

        feats.setPopWindowSize(this);

        title = (EditText) findViewById(R.id.addtitle);

        description = (EditText) findViewById(R.id.adddescription);

        // The passing of this context should not matter, but I'm not happy with this solution
        db = DBBLL.getInstance(this);
    }

    public void addMeal(View v) {
        BEMeal meal = new BEMeal(0,title.getText().toString(),description.getText().toString(),false);
        feats.createToast(this,db.addMeal(meal));
        finish();
    }
}
