package com.meir.david.locktrivia;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

    public void sportClick(View v) {

        Button sport = (Button) v;

        if(sportsButton && counter > 1 )
        {
            sportsButton = false;
            counter--;

            sport.setBackgroundResource(R.drawable.categorywindow);
        }
        else if(!sportsButton)
        {
            counter++;
            sportsButton = true;
            sport.setBackgroundResource(R.drawable.categorywindowpressed);
        }

    }
    public void foodClick(View v) {
        Button food = (Button) v;


        if(foodButton && counter > 1)
        {
            counter--;
            foodButton = false;
            food.setBackgroundResource(R.drawable.categorywindow);
        }
        else if(!foodButton)
        {
            counter++;
            foodButton = true;
            food.setBackgroundResource(R.drawable.bottombuttoncategorypressed);
        }

    }
    public void scienceClick(View v) {
        Button science = (Button) v;

        if(scienceButton && counter > 1)
        {
            counter--;
            scienceButton = false;
            science.setBackgroundResource(R.drawable.categorywindow);
        }
        else if(!scienceButton)
        {
            counter++;
            scienceButton = true;
            science.setBackgroundResource(R.drawable.categorywindowpressed);
        }

    }
    public void moviesClick(View v) {
        Button movies = (Button) v;

        if(moviesButton && counter > 1)
        {
            counter--;
            moviesButton = false;
            movies.setBackgroundResource(R.drawable.categorywindow);
        }
        else if(!moviesButton)
        {
            counter++;
            moviesButton = true;
            movies.setBackgroundResource(R.drawable.categorywindowpressed);
        }


    }
    public void historyClick(View v) {
        Button history = (Button) v;
        if(historyButton && counter > 1)
        {
            counter--;
            historyButton = false;
            history.setBackgroundResource(R.drawable.categorywindow);
        }
        else if(!historyButton)
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
