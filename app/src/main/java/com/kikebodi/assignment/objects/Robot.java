package com.kikebodi.assignment.objects;

/**
 * Created by Kike Bodi on 20/07/2017.
 * Cortrium
 * bodi.inf@gmail.com
 */

public class Robot {
    private Direction currentDirection;
    private Position currentPosition;

    public Robot(int x, int y, Direction dir) throws Position.BoardNonDefinedException {
        this.currentDirection = dir;
        this.currentPosition = new Position(x,y);
    }
}
