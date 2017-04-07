package com.meir.david.locktrivia;



import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MySettings extends AppCompatActivity {
    Button numQues;
    int quesNumber = 5;
    final int INFINITY = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_settings);
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        quesNumber = SP.getInt("quesnumber", quesNumber);
        numQues = (Button) findViewById(R.id.numQues);
        String forText;
        if (quesNumber == INFINITY) {
            forText = "Questions before exit: ∞" ;  // ∞
        }
        else {
            forText = "Questions before exit: " + quesNumber;
        }
        numQues.setText(forText);
        //layout_MySettings = (FrameLayout) findViewById(R.id.settingsAct);
        //layout_MySettings.getForeground().setAlpha(0);

    }


    public void goToCategory(View v) {
        startActivity(new Intent(this, PickCategory.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);


        Button b = (Button)v;
        b.setBackgroundResource(R.drawable.settingsrect);

    }

    public void goBack(View v)
    {
        finish();
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
    }

    public void choose(View v)
    {
        Button b = (Button)v;
        b.setBackgroundResource(R.drawable.settingsrect);
        startActivity(new Intent(this, chooseNumber.class));
        overridePendingTransition( R.anim.slide_in_left, R.anim.slide_out_left);
    }

    public void reset(View v)
    {
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor editor = SP.edit();

        editor.putBoolean("sports",true);
        editor.putBoolean("food", true);
        editor.putBoolean("history", true);
        editor.putBoolean("movies", true);
        editor.putBoolean("science", true);

        editor.putInt("quesnumber", 5);
        editor.putInt("counter", 5);

//        Button questions = (Button) findViewById(R.id.numQues);
//        questions.

        editor.commit();
        onResume();
    }



    @Override
    public void onResume(){
        super.onResume();

        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        quesNumber = SP.getInt("quesnumber", quesNumber);
        numQues = (Button) findViewById(R.id.numQues);
        String forText;
        if (quesNumber == INFINITY) {
            forText = "Questions before exit: ∞" ;  // ∞
        }
        else {
            forText = "Questions before exit: " + quesNumber;
        }
        numQues.setText(forText);

        findViewById(R.id.category).setBackgroundResource(R.drawable.settingsbutton);
        findViewById(R.id.numQues).setBackgroundResource(R.drawable.settingsbutton);
        findViewById(R.id.reset).setBackgroundResource(R.drawable.settingsbutton);

    }


}
