package com.kikebodi.assignment;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.GridView;

import com.kikebodi.assignment.objects.Direction;
import com.kikebodi.assignment.objects.ImageAdapter;
import com.kikebodi.assignment.objects.Position;
import com.kikebodi.assignment.objects.Robot;


//https://developer.android.com/guide/topics/ui/layout/gridview.html
public class MainActivity extends AppCompatActivity{
    private GridView gridview;
    private ImageAdapter imageAdapter;
    private int totalItems;
    private Robot myRobot;
    private final String TAG = MainActivity.class.getName();
    private final String input1 = "5 5\n" +
            "0 0 E\n" +
            "RFLFFLRF";
    private final String input2 = "5 5\n" +
            "1 2 N\n" +
            "RFRFFRFRF";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        createBoard();
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    public int getTotalItems(){
        return totalItems;
    }

    public int getTotalColumns(){
        return gridview.getNumColumns();
    }

    public Direction getRobotDirection(){
        return myRobot.getDirection();
    }

    private void fillGridView(int colums, int rows){
        totalItems = colums*rows;
        gridview = (GridView) findViewById(R.id.gridview);
        gridview.setNumColumns(colums);
        imageAdapter = new ImageAdapter(this);
        gridview.setAdapter(imageAdapter);
        //https://stackoverflow.com/questions/8418868/how-to-know-when-an-activity-finishes-a-layout-pass
        ViewTreeObserver vto = gridview.getViewTreeObserver();
        vto.addOnGlobalLayoutListener (new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                gridview.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                placeRobot();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        executeCommands();
                    }
                });

            }
        });
    }

    private void createBoard() {
        String[] commands = input1.split("\n");
        if (commands.length == 3) {
            String[] initValues = commands[0].split(" ");
            int maxX = Integer.valueOf(initValues[0]);
            int maxY = Integer.valueOf(initValues[1]);
            //Set board size
            Position.setBoardSize(maxX, maxY);
            fillGridView(maxX,maxY);
        }
    }

    private void placeRobot(){
        String[] commands = input1.split("\n");
        if (commands.length == 3) {
            String[] robotState = commands[1].split(" ");
            int posX = Integer.valueOf(robotState[0]);
            int posY = Integer.valueOf(robotState[1]);
            Direction dir = getDirection(robotState[2]);
            try {
                myRobot = new Robot(posX,posY,dir);
                ((ImageAdapter)gridview.getAdapter()).setCursor(posX,posY);
            } catch (Position.BoardNonDefinedException e) {
                e.printStackTrace();
            } catch (Position.OutOfBoardException e) {
                e.printStackTrace();
            }
        }
    }

    private void executeCommands(){
        String[] commands = input1.split("\n");
        if (commands.length == 3) {
            for (int i = 0; i < commands[2].length(); i++) {
                switch (commands[2].charAt(i)) {
                    case 'R':
                        myRobot.turnRight();
                        Log.d(TAG, "RIGHT");
                        break;
                    case 'L':
                        myRobot.turnLeft();
                        Log.d(TAG, "LEFT");
                        break;
                    case 'F':
                        myRobot.move();
                        Log.d(TAG, "FORWARD");
                        break;
                    default:
                        //do nothing
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "X: " + myRobot.getPosition().getX() + " Y: " + myRobot.getPosition().getY() + " Direction: " + myRobot.getDirection());
                ((ImageAdapter)gridview.getAdapter()).setCursor(myRobot.getPosition().getX(),myRobot.getPosition().getY());
            }
        }
    }

    private Direction getDirection(String s) {
        switch (s) {
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