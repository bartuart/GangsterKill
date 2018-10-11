package com.bartuart.android.gangsterkill;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CitizenAnimationView mAnimationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RelativeLayout game_layout = findViewById(R.id.main_game_field);
        CitizenImageView image_view = new CitizenImageView(this);
        game_layout.addView(image_view);

        //mAnimationView = (CitizenAnimationView) findViewById(R.id.main_game_field);





        //imageView = findViewById(R.id.citizen_image_view);

        //info:
        //https://medium.com/@patrick_iv/continuous-animation-using-timeanimator-5b8a903603fb


        /*ObjectAnimator animation = ObjectAnimator.ofFloat(imageView, "translationX", 300f);
        animation.setDuration(2000);
        animation.start();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView current_view = (ImageView)v;
                current_view.setImageResource(R.mipmap.gangster);
            }
        });

        /*im.setX(200);
        im.setY(150);
        im.setVisibility(View.GONE);

        im.setX(250);
        im.setY(150);
        im.setVisibility(View.VISIBLE);*/
    }

    /*@Override
    protected void onResume() {
        super.onResume();
        mAnimationView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAnimationView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAnimationView.pause();
        mAnimationView = null;
    }*/

}
