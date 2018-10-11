package com.bartuart.android.gangsterkill;

import android.animation.ObjectAnimator;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //public static int score_counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout game_layout = findViewById(R.id.main_game_field);
        CitizenImageView image_view = new CitizenImageView(MainActivity.this, R.mipmap.citizen);
        game_layout.addView(image_view);

        CitizenImageView image_view1 = new CitizenImageView(MainActivity.this, R.mipmap.gangster);
        game_layout.addView(image_view1);

        CitizenImageView image_view2 = new CitizenImageView(MainActivity.this, R.mipmap.citizen);
        game_layout.addView(image_view2);

        // Init
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {

            int scores = 0;
            TextView score_text_view = findViewById(R.id.game_scrore_text_view);

            @Override
            public void run() {
                score_text_view.setText("Score: " + scores++);
                handler.postDelayed(this, 5000);
            }
        };

        //Start
        handler.postDelayed(runnable, 5000);


    }

}
