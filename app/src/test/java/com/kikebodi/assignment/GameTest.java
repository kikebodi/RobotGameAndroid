package com.kikebodi.assignment;

import com.kikebodi.assignment.objects.Direction;
import com.kikebodi.assignment.objects.Position;
import com.kikebodi.assignment.objects.Robot;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Kike Bodi on 20/07/2017.
 * Cortrium
 * bodi.inf@gmail.com
 */

public class GameTest {

    @BeforeClass
    public static void resetBoard() {
        Position.resetBoard();
    }

    @Test
    public void runGame() throws Position.BoardNonDefinedException, Position.OutOfBoardException {
        String input1 = "5 5\n" +
                "0 0 E\n" +
                "RFLFFLRF";
        String input2 = "5 5\n" +
                "1 2 N\n" +
                "RFRFFRFRF";

        Robot myRobot = executeCommands(input1);
        Assert.assertEquals("",new Position(3,1), myRobot.getPosition());
        Assert.assertEquals("",Direction.EAST, myRobot.getDirection());

        myRobot = executeCommands(input2);
        Assert.assertEquals("",new Position(1,3), myRobot.getPosition());
        Assert.assertEquals("",Direction.NORTH, myRobot.getDirection());
    }

    private Robot executeCommands(String input) throws Position.BoardNonDefinedException, Position.OutOfBoardException {
        String[] commands = input.split("\n");
        if(commands.length != 3) Assert.fail("Not enough commands");

        String[] initValues = commands[0].split(" ");
        int maxX = Integer.valueOf(initValues[0]);
        int maxY = Integer.valueOf(initValues[1]);
        Position.setBoardSize(maxX,maxY);

        String[] robotState = commands[1].split(" ");
        int posX = Integer.valueOf(robotState[0]);
        int posY = Integer.valueOf(robotState[1]);
        Direction dir = getDirection(robotState[2]);
        Robot myRobot = new Robot(posX,posY,dir);
        for(int i=0;i<commands[2].length();i++){
            switch (commands[2].charAt(i)){
                case 'R':
                    myRobot.turnRight();
                    break;
                case 'L':
                    myRobot.turnLeft();
                    break;
                case 'F':
                    myRobot.move();
                    break;
                default:
                    //do nothing
            }
        }
        return myRobot;
    }

    private Direction getDirection(String s) {
        switch (s){
            case "N":
                return Direction.NORTH;
            case "E":
                return Direction.EAST;
            case "S":
                return Direction.SOUTH;
            case "W":
                return Direction.WEST;
            default:
                return null;
        }
    }
}
