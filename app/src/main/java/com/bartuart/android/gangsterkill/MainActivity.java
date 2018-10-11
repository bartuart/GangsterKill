package com.bartuart.android.gangsterkill;

import android.animation.ObjectAnimator;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout game_layout = findViewById(R.id.main_game_field);
        CitizenImageView image_view = new CitizenImageView(MainActivity.this, R.mipmap.citizen);
        game_layout.addView(image_view);

        CitizenImageView image_view1 = new CitizenImageView(this, R.mipmap.gangster);
        game_layout.addView(image_view1);

        CitizenImageView image_view2 = new CitizenImageView(this, R.mipmap.citizen);
        game_layout.addView(image_view2);

    }

}
