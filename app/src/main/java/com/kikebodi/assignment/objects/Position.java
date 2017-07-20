package com.kikebodi.assignment.objects;

/**
 * Created by Kike Bodi on 20/07/2017.
 * Cortrium
 * bodi.inf@gmail.com
 */

public class Position {
    public static int max_x = -1;
    public static int max_y = -1;
    private int x;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private int y;

    public Position(int x, int y) throws BoardNonDefinedException, OutOfBoardException {
        if(max_x<0 || max_y<0) throw new BoardNonDefinedException();

        if(x<=max_x && y<=max_y && x>-1 && y>-1){
            this.x = x;
            this.y = y;
        }else{
            throw new OutOfBoardException();
        }
    }

    /**
     * Override this methods for Assert.Equals
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj){
        if(obj != null){
            Position position = (Position) obj;
            return  (x == position.getX() && y == position.getY());
        }else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = ((y*2222)+(x*3333));
        return result;
    }

    public static void setBoardSize(int x, int y) throws IllegalArgumentException{
        if(x<1 || y<1) throw new IllegalArgumentException();

        max_x = x-1;
        max_y = y-1;
    }

    public static void resetBoard(){
        max_x = -1;
        max_y = -1;
    }

    public class BoardNonDefinedException extends Exception {
        public BoardNonDefinedException() {
            super();
        }
    }

    public class OutOfBoardException extends Exception {
        public OutOfBoardException() {
            super();
        }
    }



}
