package com.example.david.locktrivia;


import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;
import android.media.MediaPlayer;
//meir franco

public class MainActivity extends AppCompatActivity {
    boolean turnOn = true;
    boolean checked;
    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
//        mp = MediaPlayer.create(getApplicationContext(), R.raw.lock);

        checked = SP.getBoolean("onOff", false);

        final View settings = (View)findViewById(R.id.button2);
        final View stats = (View)findViewById(R.id.button3);
        final View info = (View)findViewById(R.id.button4);
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
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        Handler handler = new Handler();
        ObjectAnimator transAnimation= ObjectAnimator.ofFloat(onButton, "translationY", -1500, (height/16));
        transAnimation.setDuration(3000);//set duration
        transAnimation.start();//start animation

        handler.postDelayed(new Runnable() {
            public void run() {
                settings.setVisibility(View.VISIBLE);
                stats.setVisibility(View.VISIBLE);
                info.setVisibility(View.VISIBLE);

            }
        }, 2430);


    }



    public void switchService(View v) {
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor editor =  SP.edit();


        Button tg = (Button)findViewById(R.id.button);
        if(mp != null)
        {
            mp.stop();
            mp.release();
        }
        mp = MediaPlayer.create(getApplicationContext(), R.raw.lock);
        if(mp != null) {
            mp.start();
        }

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

    public void GoToStats(View v)
    {
        startActivity(new Intent(this, Stats.class));
        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );

    }


    @Override
    public void onResume()
    {
        super.onResume();

        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        checked = SP.getBoolean("onOff", false);
        if (checked) {
            startService(new Intent(this, MyService.class));
        } else {
            stopService(new Intent(this, MyService.class));
        }


    }

}
