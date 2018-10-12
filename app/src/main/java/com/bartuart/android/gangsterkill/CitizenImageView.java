package com.bartuart.android.gangsterkill;

import android.animation.TimeAnimator;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;

import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class CitizenImageView extends AppCompatImageView {

    private TimeAnimator mTimeAnimator;
    private long mCurrentPlayTime;

    private final int MAX_SPEED_VALUE = 25;
    private int randomSpeedX;
    private int randomSpeedY;

    private boolean isAnimationStarted;

    private int imageResourceID = R.mipmap.citizen;

    //private TextView score_text;


    public CitizenImageView(Context context) {
        super(context);
        init();
    }

    public CitizenImageView(Context context, int imageResourceID) {
        super(context);
        this.imageResourceID = imageResourceID;
        init();
    }

    public CitizenImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CitizenImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init(){
        setImageResource(this.imageResourceID);

        randomSpeedX = new Random().nextInt(MAX_SPEED_VALUE);
        randomSpeedY = new Random().nextInt(MAX_SPEED_VALUE);

        isAnimationStarted = false;

        //View rootView = ((Activity)getContext()).getWindow().getDecorView().findViewById(R.id.game_scrore_text_view);
        //score_text = ((Activity)getContext()).getWindow().getDecorView().findViewById(R.id.game_scrore_text_view);

        setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int score = 0;
                //score_text.setText("Score: " + score++);
                //Toast.makeText(getContext(), "Yees, it works!",
                 //       Toast.LENGTH_LONG).show();
                GangsterKillGame.updateScore(imageResourceID);
            }
        });
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        mTimeAnimator = new TimeAnimator();
        mTimeAnimator.setTimeListener(new TimeAnimator.TimeListener() {
            @Override
            public void onTimeUpdate(TimeAnimator animation, long totalTime, long deltaTime) {
                if (!isLaidOut()) {
                    // Ignore all calls before the view has been measured and laid out.
                    return;
                }
                updateState(deltaTime);
                invalidate();
            }
        });
        mTimeAnimator.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mTimeAnimator.cancel();
        mTimeAnimator.setTimeListener(null);
        mTimeAnimator.removeAllListeners();
        mTimeAnimator = null;
    }

    /**
     * Pause the animation if it's running
     */
    public void pause() {
        if (mTimeAnimator != null && mTimeAnimator.isRunning()) {
            // Store the current play time for later.
            mCurrentPlayTime = mTimeAnimator.getCurrentPlayTime();
            mTimeAnimator.pause();
        }
    }

    /**
     * Resume the animation if not already running
     */
    public void resume() {
        if (mTimeAnimator != null && mTimeAnimator.isPaused()) {
            mTimeAnimator.start();
            // Why set the current play time?
            // TimeAnimator uses timestamps internally to determine the delta given
            // in the TimeListener. When resumed, the next delta received will the whole
            // pause duration, which might cause a huge jank in the animation.
            // By setting the current play time, it will pick of where it left off.
            mTimeAnimator.setCurrentPlayTime(mCurrentPlayTime);
        }
    }

    /**
     * Progress the animation by moving the stars based on the elapsed time
     * @param deltaMs time delta since the last frame, in millis
     */
    private void updateState(float deltaMs) {

        if(!isAnimationStarted){
            setX(new Random().nextInt(((View)getParent()).getWidth() - getWidth()));
            setY(new Random().nextInt(((View)getParent()).getHeight() - getHeight()));
            isAnimationStarted = true;
        }

        if(getX() + randomSpeedX > ((View)getParent()).getWidth() - getWidth() || getX() + randomSpeedX < 0)
            randomSpeedX = randomSpeedX * -1;

        if(getY() + randomSpeedY > ((View)getParent()).getHeight() - getHeight() || getY() + randomSpeedY < 0)
            randomSpeedY = randomSpeedY * -1;

        setX(getX() + randomSpeedX);
        setY(getY() + randomSpeedY);
    }
}
