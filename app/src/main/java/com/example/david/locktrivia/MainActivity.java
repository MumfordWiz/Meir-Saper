package com.example.david.locktrivia;


import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Intent;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;
//meir franco

public class MainActivity extends AppCompatActivity {
    boolean turnOn = true;
    boolean checked;
    int wrongAnswers, rightAnswers;
    String wAnswers, rAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        checked = SP.getBoolean("onOff", false);
        wrongAnswers = SP.getInt("wrongCounter", 0);
        rightAnswers = SP.getInt("rightCounter", 0);

        wAnswers = Integer.toString(wrongAnswers);
        rAnswers = Integer.toString(rightAnswers);

        final View settings = (View)findViewById(R.id.button2);
        final View stats = (View)findViewById(R.id.button3);
        final View info = (View)findViewById(R.id.button4);
        //final View wrongIcon = (View)findViewById(R.id.wrongicon);
        //final View rightIcon = (View)findViewById(R.id.righticon);
        //final TextView wrong = (TextView)findViewById(R.id.wronganswers);
        //final TextView right = (TextView)findViewById(R.id.rightanswers);
        //final View logo = (View)findViewById(R.id.logot);

        //wrong.setVisibility(View.GONE);
        //right.setVisibility(View.GONE);
        //wrongIcon.setVisibility(View.GONE);
        //rightIcon.setVisibility(View.GONE);
        //logo.setVisibility(View.GONE);
        settings.setVisibility(View.GONE);
        stats.setVisibility(View.GONE);
        info.setVisibility(View.GONE);


        final View onButton = (View)findViewById(R.id.button);

        if(checked)
        {
            startService(new Intent(this, MyService.class));
            Button tg = (Button)findViewById(R.id.button);
            Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.locked);
            tg.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
            tg.setBackgroundResource(R.drawable.onbutton);
            tg.setText("On");
        }else {
            stopService(new Intent(this, MyService.class));
        }
        Handler handler = new Handler();
        ObjectAnimator transAnimation= ObjectAnimator.ofFloat(onButton, "y", -500, 750);
        transAnimation.setDuration(3000);//set duration
        transAnimation.start();//start animation

        handler.postDelayed(new Runnable() {
            public void run() {
                settings.setVisibility(View.VISIBLE);
                stats.setVisibility(View.VISIBLE);
                info.setVisibility(View.VISIBLE);
                //wrong.setVisibility(View.VISIBLE);
                //right.setVisibility(View.VISIBLE);
                //rightIcon.setVisibility(View.VISIBLE);
                //wrongIcon.setVisibility(View.VISIBLE);
                //logo.setVisibility(View.VISIBLE);


                //wrong.setText(wAnswers);
                //right.setText(rAnswers);
                //logo.setVisibility(View.VISIBLE);
                ;
            }
        }, 2430);

       // transAnimation.setRepeatCount(Animation.INFINITE);

    }



    public void switchService(View v) {
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor editor =  SP.edit();


        Button tg = (Button)findViewById(R.id.button);

        if(!checked){
            checked = true;
            Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.locked);
            tg.setCompoundDrawablesWithIntrinsicBounds( null, drawable, null, null );
            tg.setText("On");
            //tg.setTextColor(Color.WHITE);
            tg.setBackgroundResource(R.drawable.onbutton);
            editor.putBoolean("onOff", checked);
            editor.apply();
            startService(new Intent(this, MyService.class));

        }else
        {
            checked = false;
            turnOn = true;
            Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.unlocked);
            tg.setCompoundDrawablesWithIntrinsicBounds( null, drawable, null, null );
            tg.setText("Off");
            //tg.setTextColor(Color.WHITE);
            tg.setBackgroundResource(R.drawable.offbutton);
            editor.putBoolean("onOff", checked);
            editor.apply();
            stopService(new Intent(this, MyService.class));
        }

    }

    public void goToSettings(View v) {
        startActivity(new Intent(this, MySettings.class));
        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
    }

    public void showInfo(View v)
    {
        startActivity(new Intent(this, info.class));
        overridePendingTransition(0, 0);

    }

    public void reset(View v)
    {
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor editor =  SP.edit();

        editor.putInt("wrongCounter", 0);
        editor.putInt("rightCounter", 0);
        editor.commit();

        wrongAnswers = SP.getInt("wrongCounter", 0);
        rightAnswers = SP.getInt("rightCounter", 0);

        //TextView wrong = (TextView)findViewById(R.id.wronganswers);
        //TextView right = (TextView)findViewById(R.id.rightanswers);

        wAnswers = Integer.toString(wrongAnswers);
        rAnswers = Integer.toString(rightAnswers);

        //wrong.setText(wAnswers);
        //right.setText(rAnswers);
        startActivity(new Intent(this, Stats.class));
        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );




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

        checked = SP.getBoolean("onOff", false);
        if (checked) {
            startService(new Intent(this, MyService.class));
        } else {
            stopService(new Intent(this, MyService.class));
        }


    }

}
