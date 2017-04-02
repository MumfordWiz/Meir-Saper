package com.example.david.locktrivia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Stats extends AppCompatActivity {

    int wrongAnswers, rightAnswers;
    String wAnswers, rAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_stats);

        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        wrongAnswers = SP.getInt("wrongCounter", 0);
        rightAnswers = SP.getInt("rightCounter", 0);

        wAnswers = Integer.toString(wrongAnswers);
        rAnswers = Integer.toString(rightAnswers);

        TextView wrong = (TextView)findViewById(R.id.wronganswers);
        TextView right = (TextView)findViewById(R.id.rightanswers);
        wrong.setText(wAnswers);
        right.setText(rAnswers);
    }

    public void reset(View v) {
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor editor =  SP.edit();

        editor.putInt("wrongCounter", 0);
        editor.putInt("rightCounter", 0);
        editor.commit();

        wrongAnswers = SP.getInt("wrongCounter", 0);
        rightAnswers = SP.getInt("rightCounter", 0);

        wAnswers = Integer.toString(wrongAnswers);
        rAnswers = Integer.toString(rightAnswers);


        TextView wrong = (TextView)findViewById(R.id.wronganswers);
        TextView right = (TextView)findViewById(R.id.rightanswers);
        wrong.setText(wAnswers);
        right.setText(rAnswers);


    }
    @Override
    public void onResume()
    {
        super.onResume();

        //TextView wrong = (TextView)findViewById(R.id.wronganswers);
        //TextView right = (TextView)findViewById(R.id.rightanswers);


        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());


        wrongAnswers = SP.getInt("wrongCounter", 0);
        rightAnswers = SP.getInt("rightCounter", 0);

        wAnswers = Integer.toString(wrongAnswers);
        rAnswers = Integer.toString(rightAnswers);

        //wrong.setText(wAnswers);
        //right.setText(rAnswers);

    }
    public void goBack(View v)
    {
        finish();
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
    }

}
