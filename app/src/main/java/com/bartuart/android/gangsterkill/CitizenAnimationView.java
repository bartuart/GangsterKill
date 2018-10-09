package com.bartuart.android.gangsterkill;

import android.animation.TimeAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

public class CitizenAnimationView extends View {

    private static final int BASE_SPEED_DP_PER_S = 200;
    private static final int COUNT = 4;
    private static final int SEED = 1337;

    private int [] coordinates;

    private final Citizen[] citizens = new Citizen[COUNT];
    private final Citizen[] gangsters = new Citizen[COUNT];
    private final Random mRnd = new Random(SEED);

    private TimeAnimator mTimeAnimator;
    private Drawable citizenDrawable;
    private Drawable gangsterDrawable;

    private float mBaseSpeed;
    private float mBaseSize;
    private long mCurrentPlayTime;

    /** @see View#View(Context) */
    public CitizenAnimationView(Context context) {
        super(context);
        init();
    }

    /** @see View#View(Context, AttributeSet) */
    public CitizenAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /** @see View#View(Context, AttributeSet, int) */
    public CitizenAnimationView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        citizenDrawable = ContextCompat.getDrawable(getContext(), R.mipmap.citizen);
        gangsterDrawable = ContextCompat.getDrawable(getContext(), R.mipmap.gangster);
        mBaseSize = Math.max(citizenDrawable.getIntrinsicWidth(), citizenDrawable.getIntrinsicHeight()) / 2f;
        mBaseSpeed = BASE_SPEED_DP_PER_S * getResources().getDisplayMetrics().density;
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        super.onSizeChanged(width, height, oldw, oldh);

        coordinates = new int[2];
        getLocationOnScreen(coordinates);

        // The starting position is dependent on the size of the view,
        // which is why the model is initialized here, when the view is measured.
        for (int i = 0; i < citizens.length; i++) {
            final Citizen citizen = new Citizen(R.mipmap.citizen,
                    coordinates[0]);
            //initializeStar(citizen, width, height);
            citizens[i] = citizen;
        }

        for (int i = 0; i < gangsters.length; i++) {
            final Citizen gangster = new Citizen(R.mipmap.gangster,
                    coordinates[0]);
            //initializeStar(citizen, width, height);
            gangsters[i] = gangster;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        final int viewHeight = getHeight();
        for (final Citizen citizen : citizens) {
            // Ignore the star if it's outside of the view bounds
            final float starSize = mBaseSize;
           if (citizen.y + starSize < 0 || citizen.y - starSize > viewHeight) {
                continue;
            }

            // Save the current canvas state
            final int save = canvas.save();

            // Move the canvas to the center of the star
            canvas.translate(citizen.x, citizen.y);

            // Rotate the canvas based on how far the star has moved
            //final float progress = (citizen.y + starSize) / viewHeight;
            //canvas.rotate(360 * progress);

            // Prepare the size and alpha of the drawable
            final int size = Math.round(starSize);
            citizenDrawable.setBounds(-size, -size, size, size);
            //mDrawable.setAlpha(Math.round(255 * citizen.alpha));

            // Draw the star to the canvas
            citizenDrawable.draw(canvas);

            // Restore the canvas to it's previous position and rotation
            canvas.restoreToCount(save);
        }

        for (final Citizen gangster : gangsters) {
            // Ignore the star if it's outside of the view bounds
            final float starSize = mBaseSize;
            if (gangster.y + starSize < 0 || gangster.y - starSize > viewHeight) {
                continue;
            }

            // Save the current canvas state
            final int save = canvas.save();

            // Move the canvas to the center of the star
            canvas.translate(gangster.x, gangster.y);

            // Rotate the canvas based on how far the star has moved
            //final float progress = (citizen.y + starSize) / viewHeight;
            //canvas.rotate(360 * progress);

            // Prepare the size and alpha of the drawable
            final int size = Math.round(starSize);
            gangsterDrawable.setBounds(-size, -size, size, size);
            //mDrawable.setAlpha(Math.round(255 * citizen.alpha));

            // Draw the star to the canvas
            gangsterDrawable.draw(canvas);

            // Restore the canvas to it's previous position and rotation
            canvas.restoreToCount(save);
        }
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
        // Converting to seconds since PX/S constants are easier to understand
        //final float deltaSeconds = deltaMs / 1000f;
        final int viewWidth = getWidth();
        final int viewHeight = getHeight();

        for (final Citizen citizen : citizens) {
            citizen.setNewPosition(viewWidth, viewHeight, coordinates[0], coordinates[1]);
        }

        for (final Citizen gangster : gangsters) {
            gangster.setNewPosition(viewWidth, viewHeight, coordinates[0], coordinates[1]);
        }
    }
}
