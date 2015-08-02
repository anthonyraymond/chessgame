package org.araymond.chessgame.model.board;

import org.araymond.chessgame.exceptions.ChessModelIntegrityException;
import org.araymond.chessgame.model.pieces.PieceFactory;
import org.araymond.chessgame.model.player.PlayerSet;

/**
 * Created by Anthony on 21/06/2015.
 */
public final class BoardFactory {
    /**
     * Create a new board and returns it.
     *
     * @return a Board
     */
    public static Board createBoardWithPieces(PlayerSet playerSet) {
        if (playerSet == null) {
            throw new ChessModelIntegrityException("Cannot instantiate a board without a playerSet", new IllegalArgumentException());
        }
        Board board = new Board();

        //first most bottom line, from a1 to h1
        PieceFactory.putRook(playerSet.getWhitePlayer(), board.getSquareByName("a1"));
        PieceFactory.putKnight(playerSet.getWhitePlayer(), board.getSquareByName("b1"));
        PieceFactory.putBishop(playerSet.getWhitePlayer(), board.getSquareByName("c1"));
        PieceFactory.putQueen(playerSet.getWhitePlayer(), board.getSquareByName("d1"));
        PieceFactory.putKing(playerSet.getWhitePlayer(), board.getSquareByName("e1"));
        PieceFactory.putBishop(playerSet.getWhitePlayer(), board.getSquareByName("f1"));
        PieceFactory.putKnight(playerSet.getWhitePlayer(), board.getSquareByName("g1"));
        PieceFactory.putRook(playerSet.getWhitePlayer(), board.getSquareByName("h1"));

        //second most bottom line, from a2 to h2
        for (char i = 'a'; i < 'i'; ++i) {
            PieceFactory.putPawn(playerSet.getWhitePlayer(), board.getSquareByName(i + "2"));
        }


        //second top line, from a7 to h7
        for (char i = 'a'; i < 'i'; ++i) {
            PieceFactory.putPawn(playerSet.getBlackPlayer(), board.getSquareByName(i + "7"));
        }

        //first line, from a8 to h8
        PieceFactory.putRook(playerSet.getBlackPlayer(), board.getSquareByName("a8"));
        PieceFactory.putKnight(playerSet.getBlackPlayer(), board.getSquareByName("b8"));
        PieceFactory.putBishop(playerSet.getBlackPlayer(), board.getSquareByName("c8"));
        PieceFactory.putQueen(playerSet.getBlackPlayer(), board.getSquareByName("d8"));
        PieceFactory.putKing(playerSet.getBlackPlayer(), board.getSquareByName("e8"));
        PieceFactory.putBishop(playerSet.getBlackPlayer(), board.getSquareByName("f8"));
        PieceFactory.putKnight(playerSet.getBlackPlayer(), board.getSquareByName("g8"));
        PieceFactory.putRook(playerSet.getBlackPlayer(), board.getSquareByName("h8"));

        return board;
    }

}
