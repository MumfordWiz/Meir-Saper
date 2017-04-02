package com.example.david.locktrivia;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Stats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
    }

    public void reset(View v) {

    }

    public void goBack(View v)
    {
        finish();
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
    }

}
