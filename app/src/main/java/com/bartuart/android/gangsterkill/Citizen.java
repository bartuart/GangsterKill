package com.bartuart.android.gangsterkill;

import java.util.Random;

public class Citizen {

    private int resourceID;

    private final int CITIZEN_RESOURCE_ID = R.mipmap.citizen;
    private final int GANGSTER_RESOURCE_ID = R.mipmap.gangster;

    public float x;
    public float y;
    private float k;
    private float b;

    private float speed;

    public static Random kRandomizer = new Random(100);
    public static Random bRandomizer = new Random(50);

    public static Random speedRandomizer = new Random(50);


    public Citizen(int resourceID, int k, int b, int speed, int parentViewWidth, int parentViewHeight){
        this.resourceID = resourceID;
        this.x = parentViewWidth;
        this.y = parentViewHeight;
        this.k = k;
        this.b = b;
        this.speed = speed;
    }

    public void setNewPosition(int parentViewWidth, int parentViewHeight){
        /*this.x = parentViewWidth - (this.x + this.speed);
        if(this.x < 0) this.x = - this.x;

        this.y = parentViewHeight - (this.k * this.x - this.b);
        if(this.y < 0) this.y = - this.y; */

        this.x = parentViewWidth - 25;
        this.y = parentViewHeight - 45;
    }
}
