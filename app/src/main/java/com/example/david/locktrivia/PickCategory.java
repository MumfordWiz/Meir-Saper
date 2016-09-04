package com.example.david.locktrivia;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class PickCategory extends Activity {

    boolean historyButton = false, foodButton = false, moviesButton = false, scienceButton = false;
    boolean sportsButton = false;
    int counter = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_category);
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        Button tempButton = (Button) findViewById(R.id.food);
        if(SP.getBoolean("food", true))
        {
            foodButton = true;
            counter++;
            tempButton.setBackgroundResource(R.drawable.bottombuttoncategorypressed);
        }

        tempButton = (Button) findViewById(R.id.History);
        if(SP.getBoolean("history", true))
        {
            historyButton = true;
            counter++;
            tempButton.setBackgroundResource(R.drawable.categorywindowpressed);
        }

        tempButton = (Button) findViewById(R.id.Sports);

        if(SP.getBoolean("sports", true))
        {
            sportsButton = true;
            counter++;
            tempButton.setBackgroundResource(R.drawable.categorywindowpressed);
        }

        tempButton = (Button) findViewById(R.id.Movies);
        if(SP.getBoolean("movies", true))
        {
            moviesButton = true;
            counter++;
            tempButton.setBackgroundResource(R.drawable.categorywindowpressed);
        }

        tempButton = (Button) findViewById(R.id.Science);
        if(SP.getBoolean("science", true))
        {
            scienceButton = true;
            counter++;
            tempButton.setBackgroundResource(R.drawable.categorywindowpressed);
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



//        if (isAllUnchecked(v)) {
//            sport.setBackgroundResource(R.drawable.categorywindowpressed);
//            return;
//        }
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor editor = SP.edit();
//        if (sport.isChecked()) {
//            editor.putBoolean("sports", true);
//        }
//        else {
//            editor.putBoolean("sports", false);
//        }
        editor.commit();

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
//        if (isAllUnchecked(v)) {
//            food.setBackgroundResource(R.drawable.categorywindowpressed);
//            return;
//        }
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor editor = SP.edit();
//        if (food.isChecked()) {
//            editor.putBoolean("food", true);
//        }
//        else {
//            editor.putBoolean("food", false);
//        }
        editor.commit();

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
//        if (isAllUnchecked(v)) {
//            science.setBackgroundResource(R.drawable.categorywindowpressed);
//            return;
//        }
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor editor = SP.edit();
//        if (science.isChecked()) {
//            editor.putBoolean("science", true);
//        }
//        else {
//            editor.putBoolean("science", false);
//        }
        editor.apply();

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
//        if (isAllUnchecked(v)) {
//            movies.setBackgroundResource(R.drawable.categorywindowpressed);
//            return;
//        }
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor editor = SP.edit();
//        if (movies.isChecked()) {
//            editor.putBoolean("movies", true);
//        }
//        else {
//            editor.putBoolean("movies", false);
//        }
        editor.apply();

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
//        if (isAllUnchecked(v)) {
//            history.setBackgroundResource(R.drawable.categorywindowpressed);
//            return;
//        }
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor editor = SP.edit();
//        if (history.isChecked()) {
//            editor.putBoolean("history", true);
//        }
//        else {
//            editor.putBoolean("history", false);
//        }
        editor.apply();

    }

    public void imdone(View v)
    {

        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor editor = SP.edit();
//        editor.putInt("quesnumber", quesNumber);
//        editor.putInt("counter", quesNumber);

        editor.putBoolean("sports",sportsButton);
        editor.putBoolean("food", foodButton);
        editor.putBoolean("history", historyButton);
        editor.putBoolean("movies", moviesButton);
        editor.putBoolean("science", scienceButton);
        editor.apply();

        finish();
        overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_right);
    }
}
