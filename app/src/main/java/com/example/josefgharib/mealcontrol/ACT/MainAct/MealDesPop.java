package com.example.josefgharib.mealcontrol.ACT.MainAct;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.josefgharib.mealcontrol.BE.BEMeal;
import com.example.josefgharib.mealcontrol.BLL.ActivityHelpers.Features;
import com.example.josefgharib.mealcontrol.BLL.DBBLL;
import com.example.josefgharib.mealcontrol.R;

/**
 * Created by josefgharib on 26/04/16.
 */
public class MealDesPop extends Activity {

    TextView title, description;
    DBBLL db;
    int id;

    private Features feats = Features.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mealdes_window);
        feats.setPopWindowSize(this);
        db = DBBLL.getInstance(this);

        title = (TextView) findViewById(R.id.popuptitle);
        description = (TextView) findViewById(R.id.popupdescription);
        Bundle bundleData = getIntent().getExtras();
        BEMeal meal = bundleData.getParcelable("meal");
        id = meal.getmId();
        title.setText(meal.getmTitle());
        description.setText(meal.getmDescription());
    }

    public void deleteMeal(View v)
    {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage("Delete meal?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        feats.createToast(getBaseContext(),db.deleteMeal(id));
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}
