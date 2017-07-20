package com.kikebodi.assignment.objects;

import android.util.Log;

import java.util.NoSuchElementException;

/**
 * Created by Kike Bodi on 20/07/2017.
 * Cortrium
 * bodi.inf@gmail.com
 */

public class Robot {

    private final String TAG = Robot.class.getName();
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

    public void setPosition(Position position) {
        currentPosition = position;
    }

    public Direction getDirection() {
        return currentDirection;
    }

    public Position getPosition() {
        return currentPosition;
    }


    public void move() {
        int x = currentPosition.getX();
        int y = currentPosition.getY();

        switch (currentDirection){
            case NORTH:
                y++;
                break;
            case EAST:
                x++;
                break;
            case SOUTH:
                y--;
                break;
            case WEST:
                x--;
                break;
            default:
                throw new NoSuchElementException();
        }

        try{
            currentPosition = new Position(x,y);
        } catch (Position.OutOfBoardException e) {
            // Do not move
            Log.d(TAG,"The robot is at one the the edges. Do nothing");
        } catch (Position.BoardNonDefinedException e) {
            //this should never happen
            e.printStackTrace();
        }
    }
}
