package com.bartuart.android.gangsterkill;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView scoreTextView = findViewById(R.id.game_scrore_text_view);
        RelativeLayout game_layout = findViewById(R.id.main_game_field);
        GangsterKillGame.setContent(scoreTextView, MainActivity.this, game_layout);

        Button start_button = findViewById(R.id.start_stop_button);
        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GangsterKillGame.startGame();
            }
        });

        Button reset_button = findViewById(R.id.reset_stop_button);
        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GangsterKillGame.resetGame();
            }
        });
    }
}
