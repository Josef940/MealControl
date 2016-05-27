package com.example.josefgharib.mealcontrol.BLL;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.josefgharib.mealcontrol.BE.BEMeal;
import com.example.josefgharib.mealcontrol.DAL.DB;

import java.util.ArrayList;

/**
 * Created by josefgharib on 01/05/16.
 */
public class DBBLL {
    private static DB db;
    private static DBBLL instance = null;
    private final String ERROR = "An error ocurred";

    public static DBBLL getInstance(Context context) {
        if(instance == null) {
            instance = new DBBLL();
            db = new DB(context);
        }
        return instance;
    }

    public String addMeal(BEMeal meal) {
        try {
            db.insert(meal);
        }
        catch(SQLiteException e) {
            Log.v("SQLite exception: ",""+e);
            return ERROR;
        }
        return "Meal added to list";
    }



    public ArrayList<BEMeal> mealList()
    {
        ArrayList<BEMeal> meals;
        try
        {
            meals = db.selectAll();
        }
        catch(SQLiteException e)
        {
            Log.v("SQLite exception: ",""+e);
            return null;
        }

        return meals;
    }

    public String deleteMeal(int Id)
    {
        try {
            db.deleteMeal(Id);
        } catch (SQLiteException e) {
            return ERROR;
        }
        return "Meal deleted";
    }

    public String mealDone(int id)
    {
        try {
            db.setMealDone(id);
        }
        catch(SQLiteException e) {
            Log.v("SQLite exception: ",""+e);
            return ERROR;
        }
        return "Meal checked!";
    }

    public String resetMeals()
    {
        try
        {
            db.resetMeals();
        }
        catch(SQLiteException e)
        {
            Log.v("SQLite exception: ",""+e);
            return ERROR;
        }
        return "Meals reset";
    }

    //Wasted my time, SQLite generates ID automatically and perfectly :( (keeping for possible later use)
    /*
    private int generateId()
    {
        ArrayList<BEMeal> meals;
        try
        {
           meals = db.selectAll();
        }
        catch(SQLiteException e)
        {
            Log.v("SQLite exception: ",""+e);
            return 0;
        }

        int id = 1;
        boolean taken = true;
        while(taken)
        {
            for (BEMeal m : meals)
            {
                if(id == m.getmId())
                    id++;
                    break;
            }
            taken = false;
        }

        return id;
    }
    */
}
