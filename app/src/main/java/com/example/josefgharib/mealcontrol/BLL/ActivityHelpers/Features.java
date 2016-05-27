package com.example.josefgharib.mealcontrol.BLL.ActivityHelpers;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.josefgharib.mealcontrol.R;

/**
 * Created by josefgharib on 04/05/16.
 */
public class Features {

    private static Features instance = null;

    public static Features getInstance() {
        if(instance == null) {
            instance = new Features();
        }
        return instance;
    }

    public void createToast(Context context, String toasttxt)
    {
        Toast.makeText(context, toasttxt, Toast.LENGTH_SHORT).show();
    }

    public void setUpActionbar(ActionBar actionBar,Context con)
    {
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);

        ActionBar.LayoutParams lp1 = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View customNav = LayoutInflater.from(con).inflate(R.layout.action_bar, null);

        actionBar.setCustomView(customNav, lp1);
    }

    public void closeKeyboard(Context context){
        Activity activity = (Activity) context;
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)context.getSystemService(context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void setPopWindowSize(Context context){
        Activity activity = (Activity) context;
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        activity.getWindow().setLayout((int)Math.floor(width*0.8),(int)Math.floor(height * .5));
    }

    public boolean checkOnlineConnectivity(Context context){
        Activity activity = (Activity) context;
        ConnectivityManager conman = (ConnectivityManager) activity.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conman.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnectedOrConnecting())
            return true;
        else
            return false;
    }
}
