package com.bartuart.android.gangsterkill;

import android.content.Context;
import android.widget.TextView;

public class GangsterKillGame {

    private static final int GANGSTER_TOTAL_COUNT = 4;
    private static final int CITIZEN_TOTAL_COUNT = 4;

    private static final int CITIZEN_RESOURCE_ID = R.mipmap.citizen;
    private static final int GANGSTER_RESOURCE_ID = R.mipmap.gangster;

    private static final int DELTA_POINTS_FOR_CITIZEN_KILL = -2;
    private static final int DELTA_POINTS_FOR_GANGSTER_KILL = 1;
    private static final int DELTA_POINTS_FOR_NOT_KILLED_GANGSTER = -1;

    public static int gameScores = 0;

    private static TextView scoreTextView = null;
    private static Context mainActivityContext = null;

    public static void setContent(TextView pScoreTextView, Context pContext){
        scoreTextView = pScoreTextView;
        mainActivityContext = pContext;
    }

    public static void updateScore(int citizenImageResourceID){
        if(citizenImageResourceID == CITIZEN_RESOURCE_ID){
            gameScores = gameScores + DELTA_POINTS_FOR_CITIZEN_KILL;
        }

        if(citizenImageResourceID == GANGSTER_RESOURCE_ID){
            gameScores = gameScores + DELTA_POINTS_FOR_GANGSTER_KILL;
        }

        scoreTextView.setText(mainActivityContext.getResources().getString(R.string.score_text) + " " + gameScores);
    }
}
