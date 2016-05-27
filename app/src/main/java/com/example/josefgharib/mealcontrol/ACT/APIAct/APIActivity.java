package com.example.josefgharib.mealcontrol.ACT.APIAct;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.josefgharib.mealcontrol.BE.BEFoodSearch;
import com.example.josefgharib.mealcontrol.BLL.APIBLL;
import com.example.josefgharib.mealcontrol.BLL.ActivityHelpers.Features;
import com.example.josefgharib.mealcontrol.BLL.ActivityHelpers.SearchAdapter;
import com.example.josefgharib.mealcontrol.R;

import java.util.ArrayList;

/**
 * Created by josefgharib on 21/05/16.
 */
public class APIActivity extends ActionBarActivity {
    private APIBLL api = APIBLL.getInstance();
    private Features feats = Features.getInstance();

    EditText searchText;
    TextView searchingtxt,noresultstxt;
    ArrayList<BEFoodSearch> searchresult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);
        feats.setUpActionbar(getSupportActionBar(), this);
        searchText = (EditText) findViewById(R.id.searchText);
        searchingtxt = (TextView) findViewById(R.id.searchingtxt);
        searchingtxt.setVisibility(View.INVISIBLE);
        noresultstxt = (TextView) findViewById(R.id.noresultstxt);
        noresultstxt.setVisibility(View.INVISIBLE);
        searchClicked(searchText);
    }

    public void searchClicked(EditText edittext){

        edittext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchForNutrition();
                    closeKeyboard();
                }
                return false;
            }
        });
    }

    private void closeKeyboard(){
        feats.closeKeyboard(this);
    }

    private void searchForNutrition(){
        if(feats.checkOnlineConnectivity(this))
            new SearchAPI().execute(searchText.getText().toString());
        else
            feats.createToast(this,"You have no internet acccess!");
    }

    private void updateList(){
    ListView lw = (ListView) findViewById(R.id.searchListView);
        if(searchresult == null){
            lw.setAdapter(null);
        }else {
            ListAdapter adp = new SearchAdapter(this, searchresult);
            lw.setAdapter(adp);
        }
    }

    public void openAddActivity(View v){finish();}
    public void closeAndMainActivity(View v){
        finish();
    }


    private class SearchAPI extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... params) {
            searchresult = api.searchResult(params[0]);
            return null;
        }

        @Override
        protected void onPreExecute(){
            if(searchingtxt.getVisibility() == View.INVISIBLE)
            searchingtxt.setVisibility(View.VISIBLE);

            if(noresultstxt.getVisibility() == View.VISIBLE)
                noresultstxt.setVisibility(View.INVISIBLE);
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            searchingtxt.setVisibility(View.INVISIBLE);
            updateList();
            if(searchresult == null)
              noresultstxt.setVisibility(View.VISIBLE);
        }
    }

    public void openAPIActivity(View v){}
}
