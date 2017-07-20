package com.kikebodi.assignment;

import com.kikebodi.assignment.objects.Direction;
import com.kikebodi.assignment.objects.Position;
import com.kikebodi.assignment.objects.Robot;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Kike Bodi on 20/07/2017.
 * Cortrium
 * bodi.inf@gmail.com
 */

public class TDD {

    @Before
    public void resetBoard() {
        Position.resetBoard();
    }

    @Test
    public void createBoardGame() throws Position.BoardNonDefinedException, Position.OutOfBoardException {
        Position.setBoardSize(5,5);
        Position pos = new Position(3,3);
        Assert.assertNotNull("Couldn't create a position in the board",pos);
    }

    @Test(expected = IllegalArgumentException.class)
    public void defineNonValidBoard(){
        Position.setBoardSize(0,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void defineNonValidBoard2(){
        Position.setBoardSize(1,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void defineNonValidBoard3(){
        Position.setBoardSize(0,1);
    }

    @Test(expected = Position.BoardNonDefinedException.class)
    public void createPositionInNonDefinedBoard() throws Position.BoardNonDefinedException, Position.OutOfBoardException {
        Position position = new Position(3,3);
    }

    @Test
    public void placeRobot() throws Position.BoardNonDefinedException, Position.OutOfBoardException {
        Position.setBoardSize(5,5);
        Robot myRobot = new Robot(0,0, Direction.NORTH);
        Assert.assertNotNull(myRobot);
    }

    @Test(expected = Position.OutOfBoardException.class)
    public void placeInvalidPosition() throws Position.BoardNonDefinedException, Position.OutOfBoardException {
        Position.setBoardSize(5,5);
        Robot myRobot = new Robot(-1,3, Direction.NORTH);
        Assert.assertNotNull(myRobot);
    }

    @Test(expected = Position.OutOfBoardException.class)
    public void placeInvalidPosition2() throws Position.BoardNonDefinedException, Position.OutOfBoardException {
        Position.setBoardSize(5,5);
        Robot myRobot = new Robot(3,-1, Direction.NORTH);
        Assert.assertNotNull(myRobot);
    }

    @Test
    public void checkDirectionsAfterTurning() throws Position.BoardNonDefinedException, Position.OutOfBoardException {
        Position.setBoardSize(5,5);
        Robot myRobot = new Robot(2,2,Direction.NORTH);
        turnRight(myRobot);

        myRobot.setDirection(Direction.SOUTH);
        turnLeft(myRobot);
    }

    private void turnRight(Robot mRobot){
        mRobot.turnRight();
        Assert.assertEquals("Robot doesn't turn right correctly",Direction.EAST,mRobot.getDirection());
        mRobot.turnRight();
        Assert.assertEquals("Robot doesn't turn right correctly",Direction.SOUTH,mRobot.getDirection());
        mRobot.turnRight();
        Assert.assertEquals("Robot doesn't turn right correctly",Direction.WEST,mRobot.getDirection());
        mRobot.turnRight();
        Assert.assertEquals("Robot doesn't turn right correctly",Direction.NORTH,mRobot.getDirection());
    }

    private void turnLeft(Robot mRobot){
        mRobot.turnLeft();
        Assert.assertEquals("Robot doesn't turn left correctly",Direction.EAST,mRobot.getDirection());
        mRobot.turnLeft();
        Assert.assertEquals("Robot doesn't turn left correctly",Direction.NORTH,mRobot.getDirection());
        mRobot.turnLeft();
        Assert.assertEquals("Robot doesn't turn left correctly",Direction.WEST,mRobot.getDirection());
        mRobot.turnLeft();
        Assert.assertEquals("Robot doesn't turn left correctly",Direction.SOUTH,mRobot.getDirection());
    }

    @Test
    public void ReportPosition() throws Position.BoardNonDefinedException, Position.OutOfBoardException {
        Position.setBoardSize(5,5);
        Robot myRobot = new Robot(2,2,Direction.NORTH);
        Assert.assertEquals("Robot doesn't return default position",new Position(2,2),myRobot.getPosition());

        myRobot.setPosition(new Position(4,4));
        Assert.assertEquals("Robot doesn't return current position", new Position(4,4),myRobot.getPosition());
        Assert.assertNotSame("Robot doesn't know it's position",new Position(3,4),myRobot.getPosition());
    }

    @Test
    public void TestMovement() throws Position.BoardNonDefinedException, Position.OutOfBoardException {
        Position.setBoardSize(5,5);
        Robot mRobot = new Robot(0,0,Direction.NORTH);

        mRobot.move();
        Assert.assertEquals("Robot doesn't move",new Position(0,1),mRobot.getPosition());
        mRobot.move();
        Assert.assertEquals("Robot doesn't move",new Position(0,2),mRobot.getPosition());
        mRobot.move();
        Assert.assertEquals("Robot doesn't move",new Position(0,3),mRobot.getPosition());
        mRobot.move();
        Assert.assertEquals("Robot doesn't move",new Position(0,4),mRobot.getPosition());
        mRobot.move();
        Assert.assertEquals("Robot went out of the room",new Position(0,4),mRobot.getPosition());

        mRobot = new Robot(0,0,Direction.EAST);

        mRobot.move();
        Assert.assertEquals("Robot doesn't move",new Position(1,0),mRobot.getPosition());
        mRobot.move();
        Assert.assertEquals("Robot doesn't move",new Position(2,0),mRobot.getPosition());
        mRobot.move();
        Assert.assertEquals("Robot doesn't move",new Position(3,0),mRobot.getPosition());
        mRobot.move();
        Assert.assertEquals("Robot doesn't move",new Position(4,0),mRobot.getPosition());
        mRobot.move();
        Assert.assertEquals("Robot went out of the room",new Position(4,0),mRobot.getPosition());
    }
}
