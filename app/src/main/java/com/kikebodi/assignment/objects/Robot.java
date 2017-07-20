package com.kikebodi.assignment.objects;

/**
 * Created by Kike Bodi on 20/07/2017.
 * Cortrium
 * bodi.inf@gmail.com
 */

public class Robot {

    private int x;
    private int y;
    private Direction currentDirection;

    public Robot(int x, int y, Direction dir){
        this.x = x;
        this.y = y;
        this.currentDirection = dir;
    }
}
