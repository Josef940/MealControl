package com.example.josefgharib.mealcontrol.BLL.ActivityHelpers;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.josefgharib.mealcontrol.ACT.MainAct.MainActivity;
import com.example.josefgharib.mealcontrol.ACT.MainAct.MealDesPop;
import com.example.josefgharib.mealcontrol.BE.BEMeal;
import com.example.josefgharib.mealcontrol.BLL.DBBLL;
import com.example.josefgharib.mealcontrol.R;

import java.util.ArrayList;

/**
 * Created by josefgharib on 01/04/16.
 */
public class MealAdapter extends ArrayAdapter<BEMeal> {

    private Context context;
    private DBBLL db;
    private MainActivity mainActivity;
    public MealAdapter(Context context, ArrayList<BEMeal> resource)
    {
          super(context, R.layout.meal_row, resource);
        this.context = context;
        db = DBBLL.getInstance(context);
        mainActivity = (MainActivity) context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View mealView = layoutInflater.inflate(R.layout.meal_row, parent, false);

        final BEMeal meal = getItem(position);
        TextView mealNumber = (TextView) mealView.findViewById(R.id.mealNumber);
        TextView mealTitle = (TextView) mealView.findViewById(R.id.mealTitle);

        mealNumber.setText("Meal " + (position+1));
        mealTitle.setText(meal.getmTitle());

        Switch mealswitch = (Switch) mealView.findViewById(R.id.mealswitch);
        if(meal.ismDone()) {
            mealswitch.setVisibility(View.INVISIBLE);

            ImageView checkmark = (ImageView) mealView.findViewById(R.id.checkmark);
            checkmark.setVisibility(View.VISIBLE);

            if(Build.VERSION.SDK_INT>=16) {
                LinearLayout row_layout = (LinearLayout) mealView.findViewById(R.id.row_layout);
                Drawable background = ContextCompat.getDrawable(getContext(), R.drawable.meal_done_shape);
                row_layout.setBackground(background);
            }
        }else {
            mealswitch.setTextOff("");
            mealswitch.setTextOn("");
            mealswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        db.mealDone(meal.getmId());
                        mainActivity.updateList();
                    }
                }
            });
        }

        mealView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(context, MealDesPop.class);
                intent.putExtra("meal",meal);
                context.startActivity(intent);
                return false;
            }
        });

        return mealView;
    }
}
