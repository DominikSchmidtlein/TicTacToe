package com.domkick1.tictactoe;

import java.util.ArrayList;
import java.util.Scanner;
import android.widget.Button;

/**
 * Write a description of class TicTacToe here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TicTacToe
{
    private static final int ILLEGAL = 0;
    private static final int ONGOING = 1;
    private static final int TIE = 2;
    private static final int X_WON = 3;
    private static final int O_WON = 4;
    private static final int SIZE = 3;
    private static final String BLANK = "";
    private static final String X = "x";
    private static final String O = "o";
	private static final String SPACEBAR = " ";
	private static final int INDEX_ZERO = 0;
    private boolean gameOver;
    private String turn;
    private Button[][] grid;
	private Scanner scanner;
	private int result;

    /**
     * Constructor for objects of class Move
     */
    public TicTacToe(Button[][] grid)
    {
        // initialise instance variables
        turn = X;
        this.grid = grid;
        
        for(int i = 0;i<SIZE;i++){ 
            for(int j = 0;j<SIZE;j++){
                setChar(i,j,BLANK);
            }
        }
        
    }
	
	public void setChar(int row,int col,String s){
    	grid[row][col].setText(s);
    }
    
    public String getTurn() {
        return turn;
    }
    public void toggleTurn() {
        if (turn == X) {
            turn = O;
        }
        else {
            turn = X;
        }
    }
    private boolean canPlay(Move m) {
        if (grid[m.getRow()][m.getCol()].getText().toString() == BLANK) {
            return true;
        }
        else {
            return false;
        }
    }
    public int play(Move m) {
    	if(!this.canPlay(m)){
    		return ILLEGAL;
    	}
    	grid[m.getRow()][m.getCol()].setText(turn);
    	
    	if(isWinner()){
    		if(turn == X) {
    			return X_WON;
    		}
    		else {
    			return O_WON;
    		}
    	}
    	else if(isFull()){
    		return TIE;
    	}
    	
    	toggleTurn();
    	return ONGOING;
    }

    private ArrayList<Move> generateMoves() {
    	ArrayList<Move> moves = new ArrayList<Move>();
        for(int i = 0;i < SIZE;i++) {
            for(int j = 0;j < SIZE;j++) {
                Move tempMove = new Move(i,j);
                if(this.canPlay(tempMove)){
                    moves.add(tempMove);
                }
            }
        }
        return moves;
    }
    public int machinePlay() {
    	//Move move = randomMove();
    	Move move = firstAvailableMove();
    	if(move == null){
    		return TIE;
    	}
    	else{
    		return play(move);
    	}
    }
    private Move firstAvailableMove() {
        return this.generateMoves().get(0);
    }
    private Move randomMove() {
        ArrayList<Move> moves = generateMoves();
        return generateMoves().get((int)(Math.random()*(moves.size())));
    }
    public Button[][] getGrid(){
    	return grid;
    }
    private boolean isWinner() {
    	//check horizontal win
    	for(int i = 0;i < SIZE;i++){
    		if(grid[i][0].getText() == turn &&
    				grid[i][1].getText() == turn && 
    				grid[i][2].getText() == turn) {
    			return true;
    		}
    	}
    	for(int i = 0;i<SIZE;i++){
    		if(grid[0][i].getText() == turn &&
    				grid[1][i].getText() == turn &&
    				grid[2][i].getText() == turn){
    			return true;
    		}
    	}
    	if(grid[0][0].getText() == turn &&
    			grid[1][1].getText() == turn &&
    			grid[2][2].getText() == turn) {
    		return true;
    	}
    	if(grid[2][0].getText() == turn &&
    			grid[1][1].getText() == turn &&
    			grid[0][2].getText() == turn) {
    		return true;
    	}
    	return false;
    }
    private boolean isFull() {
        for(int i = 0;i < SIZE;i++){
            for(int j = 0;j < SIZE;j++){
                if(grid[i][j].getText() == BLANK){
                    return false;
                }
            }
        }
        return true;
    }
}
