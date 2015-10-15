package com.domkick1.tictactoe;

public class Move
{

    private int row;
    private int col;
    private char turn;
    private int rating;


    public Move(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public Move() {
        this(0,0);
    }
    public Move(int rating) {
        this();
        this.rating = rating;
    }
    
    public int getCol() {
        return col;
    }
    public int getRow() {
        return row;
    }
    public int getRating() {
        return rating;
    }
    public void setCol(int col) {
        this.col = col;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public boolean equals(Object o){
        Move move = (Move) o;
        if(row == move.row && col == move.col){
            return true;
        }
        else{
            return false;
        }
    }
}
