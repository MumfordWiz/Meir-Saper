package com.meir.david.locktrivia;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

public class PickCategory extends Activity {

    boolean historyButton = false, foodButton = false, moviesButton = false, scienceButton = false;
    boolean sportsButton = false;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_category);
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        counter = SP.getInt(Consts.CATEGORY_COUNTER, 0);
        Button tempButton = (Button) findViewById(R.id.food);

        if(SP.getBoolean("food", false))
        {
            foodButton = true;
            tempButton.setBackgroundResource(R.drawable.bottombuttoncategorypressed);
        } else {
            tempButton.setBackgroundResource(R.drawable.categorywindow);
        }

        tempButton = (Button) findViewById(R.id.History);
        if(SP.getBoolean("history", false))
        {
            historyButton = true;
            tempButton.setBackgroundResource(R.drawable.categorywindowpressed);
        }else {
            tempButton.setBackgroundResource(R.drawable.categorywindow);
        }

        tempButton = (Button) findViewById(R.id.Sports);

        if(SP.getBoolean("sports", false))
        {
            sportsButton = true;
            tempButton.setBackgroundResource(R.drawable.categorywindowpressed);
        }else {
            tempButton.setBackgroundResource(R.drawable.categorywindow);
        }

        tempButton = (Button) findViewById(R.id.Movies);
        if(SP.getBoolean("movies", false))
        {
            moviesButton = true;
            tempButton.setBackgroundResource(R.drawable.categorywindowpressed);
        }else {
            tempButton.setBackgroundResource(R.drawable.categorywindow);
        }

        tempButton = (Button) findViewById(R.id.Science);
        if(SP.getBoolean("science", false))
        {
            scienceButton = true;
            tempButton.setBackgroundResource(R.drawable.categorywindowpressed);
        }else {
            tempButton.setBackgroundResource(R.drawable.categorywindow);
        }
    }
    public void lastCat() {
        new AlertDialog.Builder(this)
                .setTitle("Categories")
                .setMessage("You must choose at least one category")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
    public void sportClick(View v) {

        Button sport = (Button) v;

        if(sportsButton)
        {
            if (counter == 1) {
                lastCat();
                return;
            }
            sportsButton = false;
            counter--;

            sport.setBackgroundResource(R.drawable.categorywindow);
        }
        else
        {
            counter++;
            sportsButton = true;
            sport.setBackgroundResource(R.drawable.categorywindowpressed);
        }

    }
    public void foodClick(View v) {
        Button food = (Button) v;


        if(foodButton)
        {
            if (counter == 1) {
                lastCat();
                return;
            }
            counter--;
            foodButton = false;
            food.setBackgroundResource(R.drawable.categorywindow);
        }
        else
        {
            counter++;
            foodButton = true;
            food.setBackgroundResource(R.drawable.bottombuttoncategorypressed);
        }

    }
    public void scienceClick(View v) {
        Button science = (Button) v;

        if(scienceButton)
        {
            if (counter == 1) {
                lastCat();
                return;
            }
            counter--;
            scienceButton = false;
            science.setBackgroundResource(R.drawable.categorywindow);
        }
        else
        {
            counter++;
            scienceButton = true;
            science.setBackgroundResource(R.drawable.categorywindowpressed);
        }

    }
    public void moviesClick(View v) {
        Button movies = (Button) v;

        if(moviesButton)
        {
            if (counter == 1) {
                lastCat();
                return;
            }
            counter--;
            moviesButton = false;
            movies.setBackgroundResource(R.drawable.categorywindow);
        }
        else
        {
            counter++;
            moviesButton = true;
            movies.setBackgroundResource(R.drawable.categorywindowpressed);
        }


    }
    public void historyClick(View v) {
        Button history = (Button) v;
        if(historyButton)
        {
            if (counter == 1) {
                lastCat();
                return;
            }
            counter--;
            historyButton = false;
            history.setBackgroundResource(R.drawable.categorywindow);
        }
        else
        {
            counter++;
            historyButton = true;
            history.setBackgroundResource(R.drawable.categorywindowpressed);
        }


    }

    public void imdone(View v)
    {

        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor editor = SP.edit();
        editor.putBoolean("sports",sportsButton);
        editor.putBoolean("food", foodButton);
        editor.putBoolean("history", historyButton);
        editor.putBoolean("movies", moviesButton);
        editor.putBoolean("science", scienceButton);
        editor.putInt(Consts.CATEGORY_COUNTER, counter);

        editor.apply();

        finish();
        overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_right);
    }
}
