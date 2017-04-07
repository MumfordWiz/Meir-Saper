package com.meir.david.locktrivia;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
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
        //leftArrow.layout();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        leftArrow.setY(height/(2.1f));
        rightArrow.setY(height/(2.1f));
        Handler handler = new Handler();
        ObjectAnimator transAnimationleft= ObjectAnimator.ofFloat(leftArrow, "translationX",width/(1.3f), width/(1.6f));
        ObjectAnimator transAnimationright= ObjectAnimator.ofFloat(rightArrow, "translationX", width/(38f), width/(6.2f));
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
        overridePendingTransition(0,0);
    }

}
