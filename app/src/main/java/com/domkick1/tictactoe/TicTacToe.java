package com.domkick1.tictactoe;

import java.util.ArrayList;

/**
 * Write a description of class TicTacToe here.
 * 
 * @author dominik schmidtlein
 * @version (a version number or a date)
 */
public class TicTacToe
{
    public static final int ILLEGAL = 0;
    public static final int ONGOING = 1;
    public static final int TIE = 2;
    public static final int X_WON = 3;
    public static final int O_WON = 4;
    public static final int SIZE = 3;
    public static final char BLANK = ' ';
    public static final char X = 'x';
    public static final char O = 'o';
    private char turn;
    private int status = ONGOING;
    private char[][] grid;
    private PredictionMove predictionMove;
    private int numberOfPlayers;

    /**
     * Constructor for objects of class Move
     */
    public TicTacToe(int numberOfPlayers)
    {
        // initialise instance variables
        grid = new char[3][3];
        predictionMove = new PredictionMove();
        this.numberOfPlayers = numberOfPlayers;
        turn = X;
        for(int i = 0;i<SIZE;i++)
            for(int j = 0;j<SIZE;j++)
                grid[i][j] = BLANK;
    }
    public TicTacToe(TicTacToe ticTacToe){
        this.grid = new char[3][3];
        for(int i = 0;i<SIZE;i++)
            for(int j = 0;j<SIZE;j++)
                this.grid[i][j] = ticTacToe.grid[i][j];
        this.turn = ticTacToe.turn;
    }
    
    public char getTurn() {
        return turn;
    }
    public int getStatus(){
        return status;
    }
    public void toggleTurn() {
        turn = (turn == X) ? O : X;
    }
    private boolean canPlay(Move m) {
        return grid[m.getRow()][m.getCol()] == BLANK;
    }
    public void play(Move m) {
    	if(!this.canPlay(m))
    		return;
    	grid[m.getRow()][m.getCol()] = turn;
        toggleTurn();
    	if(isWinner()) {
            this.status = (turn == X) ? O_WON : X_WON;
        }
    	else if(isFull()) {
            this.status = TIE;
        }
        if(this.status == ONGOING && numberOfPlayers == 1 && this.turn == O){
            machinePlay();
        }
    }

    public void simPlay(Move m) {
        if(!this.canPlay(m))
            return;
        grid[m.getRow()][m.getCol()] = turn;
        toggleTurn();
        if(isWinner()) {
            this.status = (turn == X) ? O_WON : X_WON;
        }
        else if(isFull()) {
            this.status = TIE;
        }
    }

    public ArrayList<Move> generateMoves() {
    	ArrayList<Move> moves = new ArrayList<Move>();
        for(int i = 0;i < SIZE;i++) {
            for(int j = 0;j < SIZE;j++) {
                Move tempMove = new Move(i,j);
                if(this.canPlay(tempMove))
                    moves.add(tempMove);
            }
        }
        return moves;
    }
    public void machinePlay() {
    	//Move move = randomMove();
    	Move move = predictionMove.getMove(this);
        play(move);
    }

    private Move firstAvailableMove() {
        return this.generateMoves().get(0);
    }
    private Move randomMove() {
        ArrayList<Move> moves = generateMoves();
        return generateMoves().get((int)(Math.random()*(moves.size())));
    }
    public char[][] getGrid(){
    	return grid;
    }
    private boolean isWinner() {
    	//check horizontal win
    	for(int i = 0;i < SIZE;i++)
    		if(grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2] && grid[i][0] != BLANK)
    			return true;
    	for(int i = 0;i<SIZE;i++)
    		if(grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i] && grid[0][i] != BLANK)
    			return true;
    	if(grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2] && grid[1][1] != BLANK)
    		return true;
    	if(grid[2][0] == grid[1][1] && grid[1][1] == grid[0][2] && grid[1][1] != BLANK)
    		return true;
    	return false;
    }
    private boolean isFull() {
        for(int i = 0;i < SIZE;i++)
            for(int j = 0;j < SIZE;j++)
                if(grid[i][j] == BLANK)
                    return false;
        return true;
    }
}
