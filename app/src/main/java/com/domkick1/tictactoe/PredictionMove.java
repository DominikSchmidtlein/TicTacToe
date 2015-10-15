package com.domkick1.tictactoe;

import java.util.ArrayList;

/**
 * Created by dominikschmidtlein on 10/15/2015.
 */



public class PredictionMove {
    public static final int WIN = 3;
    public static final int DRAW = 0;
    public static final int LOSS = -6;

    public Move getMove(TicTacToe ticTacToe) {
        if(ticTacToe.getStatus() == TicTacToe.O_WON)
            return new Move(WIN * (ticTacToe.generateMoves().size() + 1));
        else if(ticTacToe.getStatus() == TicTacToe.X_WON)
            return new Move(LOSS * (ticTacToe.generateMoves().size() + 1));
        else if(ticTacToe.getStatus() == TicTacToe.TIE)
            return new Move(DRAW * (ticTacToe.generateMoves().size() + 1));
        ArrayList<Move> moves = ticTacToe.generateMoves();
        Move bestMove = moves.get(0);
        Move worstMove = moves.get(0);
        int netRating = 0;
        for(Move move: moves) {
            TicTacToe simBoard = new TicTacToe(ticTacToe);
            simBoard.simPlay(move);
            move.setRating(getMove(simBoard).getRating());
            netRating += move.getRating();
            if(move.getRating() > bestMove.getRating())
                bestMove = move;
            if(move.getRating() < worstMove.getRating())
                worstMove = move;
        }
        if(ticTacToe.getTurn() == TicTacToe.X){
            worstMove.setRating(netRating);
            return worstMove;
        }
        bestMove.setRating(netRating);
        return bestMove;
    }
}
