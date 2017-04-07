package com.meir.david.locktrivia;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class chooseNumber extends AppCompatActivity {

    int quesNumber = 5;
    final int INFINITY = 500;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        quesNumber = SP.getInt("quesnumber", quesNumber);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_number);
    }

    public void onePressed(View v)
    {
        quesNumber = 1;
        getOut();

    }

    public void threePressed(View v)
    {
        quesNumber = 3;
        getOut();

    }

    public void fivePressed(View v)
    {
        quesNumber = 5;
        getOut();

    }

    public void eightPressed(View v)
    {
        quesNumber = 8;
        getOut();

    }

    public void tenPressed(View v)
    {
        quesNumber = 10;
        getOut();

    }

    public void infinityPressed(View v)
    {
        quesNumber = INFINITY;
        getOut();

    }


    public void getOut(){

        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor editor = SP.edit();
        editor.putInt("quesnumber", quesNumber);
        editor.putInt("counter", quesNumber);
        editor.apply();

        finish();
        overridePendingTransition( R.anim.slide_out_right, R.anim.slide_in_right);
    }
}
