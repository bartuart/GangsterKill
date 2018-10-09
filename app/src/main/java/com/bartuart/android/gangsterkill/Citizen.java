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

    public static Random speedRandomizer = new Random(3);


    public Citizen(int resourceID, int k, int b, int speed, int parentViewWidth, int parentViewHeight, int viewCoordinateX, int viewCoordinateY){
        this.resourceID = resourceID;
        this.x = viewCoordinateX + 40;
        this.y = viewCoordinateX + 40;
        this.k = 1;
        this.b = 0;
        this.speed = 4;
    }

    public void setNewPosition(int parentViewWidth, int parentViewHeight, int viewCoordinateX, int viewCoordinateY){

        int min_x_value = viewCoordinateX;
        int max_x_value = viewCoordinateX + parentViewWidth - 50;
        if(this.x + this.speed <= min_x_value || this.x + this.speed >= max_x_value){
            this.speed = -1 * this.speed;
            this.k = this.k * -1;
        }
        this.x = this.x + this.speed;


        int min_y_value = viewCoordinateX;
        int max_y_value = viewCoordinateX + parentViewHeight - 50;
        //this.y = this.k * this.x + this.b;
        if(this.k * this.x + this.b <= min_y_value || this.k * this.x + this.b >= max_y_value){
            //this.y = min_y_value + this.y;
            this.k = this.k * -1;
        } else
            this.y = this.k * this.x + this.b;
    }
}
