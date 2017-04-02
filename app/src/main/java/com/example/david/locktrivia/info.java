package com.example.david.locktrivia;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

public class info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_info);
        setContentView(R.layout.content_info);


        ImageView leftArrow = (ImageView)findViewById(R.id.leftarrow);
        ImageView rightArrow = (ImageView)findViewById(R.id.rightarrow);

        Handler handler = new Handler();
        ObjectAnimator transAnimationleft= ObjectAnimator.ofFloat(leftArrow, "x", 750, 680);
        ObjectAnimator transAnimationright= ObjectAnimator.ofFloat(rightArrow, "x", 107, 177);
        transAnimationleft.setDuration(1300);
        transAnimationright.setDuration(1300);//set duration
        transAnimationright.start();
        transAnimationleft.start();
        transAnimationright.setRepeatCount(Animation.INFINITE);
        transAnimationleft.setRepeatCount(Animation.INFINITE);//start animation

    }

    public void getOut(View v)
    {
        finish();
    }

}
