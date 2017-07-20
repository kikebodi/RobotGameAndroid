package com.kikebodi.assignment.objects;

import java.util.NoSuchElementException;

/**
 * Created by Kike Bodi on 20/07/2017.
 * Cortrium
 * bodi.inf@gmail.com
 */

public class Robot {
    private Direction currentDirection;
    private Position currentPosition;

    public Robot(int x, int y, Direction dir) throws Position.BoardNonDefinedException, Position.OutOfBoardException {
        this.currentDirection = dir;
        this.currentPosition = new Position(x,y);
    }

    public void turnRight() {
        switch (currentDirection){
            case NORTH:
                currentDirection = Direction.EAST;
                break;
            case EAST:
                currentDirection = Direction.SOUTH;
                break;
            case SOUTH:
                currentDirection = Direction.WEST;
                break;
            case WEST:
                currentDirection = Direction.NORTH;
                break;
            default:
                throw new NoSuchElementException();
        }
    }

    public void turnLeft() {
        switch (currentDirection){
            case NORTH:
                currentDirection = Direction.WEST;
                break;
            case EAST:
                currentDirection = Direction.NORTH;
                break;
            case SOUTH:
                currentDirection = Direction.EAST;
                break;
            case WEST:
                currentDirection = Direction.SOUTH;
                break;
            default:
                throw new NoSuchElementException();
        }
    }

    public void setDirection(Direction direction) {
        currentDirection = direction;
    }

    public Direction getDirection() {
        return currentDirection;
    }
}
