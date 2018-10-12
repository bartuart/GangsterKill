package com.bartuart.android.gangsterkill;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GangsterKillGame {

    private static final int GANGSTER_TOTAL_COUNT = 2;
    private static final int CITIZEN_TOTAL_COUNT = 2;

    private static final int CITIZEN_RESOURCE_ID = R.mipmap.citizen;
    private static final int GANGSTER_RESOURCE_ID = R.mipmap.gangster;

    private static final int DELTA_POINTS_FOR_CITIZEN_KILL = -2;
    private static final int DELTA_POINTS_FOR_GANGSTER_KILL = 1;
    private static final int DELTA_POINTS_FOR_NOT_KILLED_GANGSTER = -1;

    private static final int MAX_SPEED_VALUE = 25;

    public static int gameScores = 0;

    private static final int GAME_INTERVAL = 5000;
    private static final Handler gameHandler = new Handler();
    private static Runnable gameRunnable;



    private static boolean isCitizensCreated = false;

    private static TextView scoreTextView = null;
    private static Context mainActivityContext = null;
    private static RelativeLayout citizenLayout = null;
    private static AppCompatActivity mainActivityReference;

    public static void setContent(TextView pScoreTextView, AppCompatActivity pMainActivityReference, RelativeLayout pCitizenRelativeLayout){
        scoreTextView = pScoreTextView;
        mainActivityReference = pMainActivityReference;
        mainActivityContext = mainActivityReference.getApplicationContext();
        citizenLayout = pCitizenRelativeLayout;
    }

    public static void updateScore(int citizenImageResourceID, ImageView currentImageView){
        if(citizenImageResourceID == CITIZEN_RESOURCE_ID){
            gameScores = gameScores + DELTA_POINTS_FOR_CITIZEN_KILL;
        }

        if(citizenImageResourceID == GANGSTER_RESOURCE_ID){
            gameScores = gameScores + DELTA_POINTS_FOR_GANGSTER_KILL;
        }
        citizenLayout.removeView(currentImageView);
        scoreTextView.setText(mainActivityContext.getResources().getString(R.string.score_text) + " " + gameScores);

        if(gameScores < 0 ){
            scoreTextView.setTextColor(mainActivityContext.getResources().getColor(R.color.colorScoreTextLose));
        }
    }


    private static void reset(){
        gameScores = 0;
        citizenLayout.removeAllViews();
        scoreTextView.setTextColor(mainActivityContext.getResources().getColor(R.color.colorScoreTextWin));
        scoreTextView.setText(mainActivityContext.getResources().getString(R.string.score_text) + " " + gameScores);
    }

    private static void resume(){
        if(isCitizensCreated){
            for(int i = 0; i < citizenLayout.getChildCount(); i++){
                CitizenImageView current_image_view = (CitizenImageView)citizenLayout.getChildAt(i);
                if(current_image_view != null){
                    if(current_image_view.getImageResourceID() == GANGSTER_RESOURCE_ID){
                        gameScores = gameScores + DELTA_POINTS_FOR_NOT_KILLED_GANGSTER;
                    }
                }
            }
            citizenLayout.removeAllViews();
            isCitizensCreated = false;
        } else {
            for(int i = 0; i < GANGSTER_TOTAL_COUNT; i++){
                citizenLayout.addView(new CitizenImageView(mainActivityContext, GANGSTER_RESOURCE_ID, MAX_SPEED_VALUE));
            }

            for(int i = 0; i < CITIZEN_TOTAL_COUNT; i++){
                citizenLayout.addView(new CitizenImageView(mainActivityContext, CITIZEN_RESOURCE_ID, MAX_SPEED_VALUE));
            }
            isCitizensCreated = true;
        }
    }

    public static void startGame(){
        reset();
        gameRunnable = new Runnable() {
            @Override
            public void run() {
                resume();
                gameHandler.postDelayed(this, GAME_INTERVAL);
            }
        };

        //Start
        gameHandler.postDelayed(gameRunnable, GAME_INTERVAL);
    }

    public static void resetGame(){
        reset();
        gameHandler.removeCallbacks(gameRunnable);
        gameRunnable = null;
    }
}
