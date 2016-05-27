package com.example.josefgharib.mealcontrol.ACT.MainAct;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.josefgharib.mealcontrol.ACT.APIAct.APIActivity;
import com.example.josefgharib.mealcontrol.BE.BEMeal;
import com.example.josefgharib.mealcontrol.BLL.DBBLL;
import com.example.josefgharib.mealcontrol.BLL.ActivityHelpers.Features;
import com.example.josefgharib.mealcontrol.BLL.ActivityHelpers.MealAdapter;
import com.example.josefgharib.mealcontrol.R;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {
    private DBBLL db;
    private Features feats = Features.getInstance();
    ArrayList<BEMeal> meals = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = DBBLL.getInstance(this);
        updateList();
        feats.setUpActionbar(getSupportActionBar(), this);

    }

    public void updateList()
    {
        meals = db.mealList();
        ListView lw = (ListView) findViewById(R.id.mealListView);
        ListAdapter adp = new MealAdapter(this, meals);
        lw.setAdapter(adp);
    }

    public void resetMeals(View v)
    {
        feats.createToast(this,db.resetMeals());
        updateList();
    }

    public void openAddActivity(View v)
    {
        Intent intent = new Intent(this, MealAddPop.class);
        this.startActivity(intent);
    }

    public void openAPIActivity(View v)
    {
        Intent intent = new Intent(this, APIActivity.class);
        this.startActivity(intent);
    }

    public void closeAndMainActivity(View v){}

    @Override
    public void onResume()
    {
        super.onResume();
        updateList();
    }
}
