package com.example.josefgharib.mealcontrol.BLL.ActivityHelpers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.josefgharib.mealcontrol.ACT.APIAct.NutritionDesPop;
import com.example.josefgharib.mealcontrol.BE.BEFoodSearch;
import com.example.josefgharib.mealcontrol.R;

import java.util.ArrayList;

/**
 * Created by josefgharib on 21/05/16.
 */
public class SearchAdapter extends ArrayAdapter<BEFoodSearch> {

    private Context context;
    public SearchAdapter(Context context, ArrayList<BEFoodSearch> resource)
    {
        super(context, R.layout.search_row, resource);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View searchView = layoutInflater.inflate(R.layout.search_row, parent, false);

        final BEFoodSearch food = getItem(position);
        TextView foodName = (TextView) searchView.findViewById(R.id.foodName);
        TextView foodGroup = (TextView) searchView.findViewById(R.id.foodGroup);

        foodName.setText(food.getMname());
        foodGroup.setText(food.getMgroup());

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NutritionDesPop.class);
                intent.putExtra("id",food.getMid());
                context.startActivity(intent);
            }
        });
        return searchView;
    }
}
