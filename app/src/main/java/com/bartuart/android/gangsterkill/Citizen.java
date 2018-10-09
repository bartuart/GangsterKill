package com.bartuart.android.gangsterkill;

import java.util.Random;

public class Citizen {

    private int resourceID;

    private final int CITIZEN_RESOURCE_ID = R.mipmap.citizen;
    private final int GANGSTER_RESOURCE_ID = R.mipmap.gangster;

    private final float[] K_RANDOMIZER_ARRAY = {0, 0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1};
    private final int B_RANDOMIZER_VALUE = 10;
    private final int SPEED_RANDOMIZER_VALUE = 15;

    private Random kRandomizer = new Random();
    private Random bRandomizer = new Random();
    private Random speedRandomizer = new Random();


    public float x;
    public float y;
    private float k;
    private float b;
    private float speed;



    public Citizen(int resourceID, int viewCoordinateX){
        this.resourceID = resourceID;
        this.x = viewCoordinateX + 90;
        this.y = viewCoordinateX + 90;
        this.k = K_RANDOMIZER_ARRAY[kRandomizer.nextInt(K_RANDOMIZER_ARRAY.length)];
        this.b = bRandomizer.nextInt(this.B_RANDOMIZER_VALUE);
        this.speed = speedRandomizer.nextInt(SPEED_RANDOMIZER_VALUE);
    }

    public void setNewPosition(int parentViewWidth, int parentViewHeight, int viewCoordinateX, int viewCoordinateY){

        int min_x_value = viewCoordinateX;
        int max_x_value = viewCoordinateX + parentViewWidth - 50;
        if(this.x + this.speed <= min_x_value || this.x + this.speed >= max_x_value){
            this.speed = -1 * this.speed;
            //this.k = (1 / this.k);
            //this.k = -(1 / this.k);
        }
        this.x = this.x + this.speed;


        int min_y_value = viewCoordinateX;
        int max_y_value = viewCoordinateX + parentViewHeight - 90;
        if(this.k * this.x + this.b <= min_y_value || this.k * this.x + this.b >= max_y_value){
            //this.y = this.k * this.x + this.b;
            //this.y = this.y - 50;
            if(this.k * this.x + this.b <= min_y_value) {
                this.y = min_y_value + 90;
                this.k = (1 / this.k);
            }
            if(this.k * this.x + this.b >= max_y_value){
                //this.y = max_y_value - 90;
                this.k = (1 / this.k);
            }
            //this.y = this.k * this.x + this.b;
        }
        else
            this.y = this.k * this.x + this.b;
    }
}
