package com.bartuart.android.gangsterkill;

import android.animation.TimeAnimator;
import android.content.Context;
import android.util.AttributeSet;

import android.support.v7.widget.AppCompatImageView;
import android.view.View;

public class CitizenImageView extends AppCompatImageView {

    private TimeAnimator mTimeAnimator;
    private long mCurrentPlayTime;

    private int x_incremental = 2;
    private int y_incremental = 2;


    public CitizenImageView(Context context) {
        super(context);
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
        setImageResource(R.mipmap.gangster);
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

        //float maxValue1 = getX();
        //int maxValue2 = Math.round(getX());

        //int parentWidth = ((View)getParent()).getWidth();



        if(getX() + x_incremental > ((View)getParent()).getWidth() - getWidth() || getX() + x_incremental < 0)
            x_incremental = x_incremental * -1;

        if(getY() + y_incremental > ((View)getParent()).getHeight() - getHeight() || getY() + y_incremental < 0)
            y_incremental = y_incremental * -1;


        setX(getX() + x_incremental);
        setY(getY() + y_incremental);
    }
}
