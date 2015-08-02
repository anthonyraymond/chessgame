package org.araymond.chessgame.model.pieces;

import org.araymond.chessgame.exceptions.ChessModelIntegrityException;
import org.araymond.chessgame.model.board.Square;
import org.araymond.chessgame.model.moves.validators.*;
import org.araymond.chessgame.model.player.Player;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Anthony on 21/06/2015.
 */
public final class PieceFactory {
    private final static int POINT_PAWN = 1;
    private final static int POINT_KNIGHT = 3;
    private final static int POINT_BISHOP = 3;
    private final static int POINT_ROOK = 6;
    private final static int POINT_QUEEN = 6;
    private final static int POINT_KING = 0;

    private static final MoveValidatorPawn moveValidatorPawn = new MoveValidatorPawn();
    private static final MoveValidatorPawnFirstMove moveValidatorPawnFirstMove = new MoveValidatorPawnFirstMove();
    private static final MoveValidatorKnight moveValidatorKnight = new MoveValidatorKnight();
    private static final MoveValidatorDiagonal moveValidatorDiagonal = new MoveValidatorDiagonal();
    private static final MoveValidatorLine moveValidatorLine = new MoveValidatorLine();
    private static final MoveValidatorKing moveValidatorKing = new MoveValidatorKing();

    private PieceFactory() {
    }

    public static void putPawn(Player player, Square square) {
        if (player == null) {
            throw new ChessModelIntegrityException("Cannot plant a Pawn with a null Square.", new NullPointerException());
        }
        if (square == null) {
            throw new ChessModelIntegrityException("Cannot plant a Pawn with a null Player.", new NullPointerException());
        }
        new Piece(player, PieceType.PAWN, square, POINT_PAWN, Arrays.asList(moveValidatorPawn, moveValidatorPawnFirstMove));
    }


    public static void putKnight(Player player, Square square) {
        if (player == null) {
            throw new ChessModelIntegrityException("Cannot plant a Knight with a null Square.", new NullPointerException());
        }
        if (square == null) {
            throw new ChessModelIntegrityException("Cannot plant a Knight with a null Player.", new NullPointerException());
        }
        new Piece(player, PieceType.KNIGHT, square, POINT_KNIGHT, Collections.singletonList(moveValidatorKnight));
    }

    public static void putBishop(Player player, Square square) {
        if (player == null) {
            throw new ChessModelIntegrityException("Cannot plant a Bishop with a null Square.", new NullPointerException());
        }
        if (square == null) {
            throw new ChessModelIntegrityException("Cannot plant a Bishop with a null Player.", new NullPointerException());
        }
        new Piece(player, PieceType.BISHOP, square, POINT_BISHOP, Collections.singletonList(moveValidatorDiagonal));
    }

    public static void putRook(Player player, Square square) {
        if (player == null) {
            throw new ChessModelIntegrityException("Cannot plant a Rook with a null Square.", new NullPointerException());
        }
        if (square == null) {
            throw new ChessModelIntegrityException("Cannot plant a Rook with a null Player.", new NullPointerException());
        }
        new Piece(player, PieceType.ROOK, square, POINT_ROOK, Collections.singletonList(moveValidatorLine));
    }

    public static void putQueen(Player player, Square square) {
        if (player == null) {
            throw new ChessModelIntegrityException("Cannot plant a Queen with a null Square.", new NullPointerException());
        }
        if (square == null) {
            throw new ChessModelIntegrityException("Cannot plant a Queen with a null Player.", new NullPointerException());
        }
        new Piece(player, PieceType.QUEEN, square, POINT_QUEEN, Arrays.asList(moveValidatorLine, moveValidatorDiagonal));
    }

    public static void putKing(Player player, Square square) {
        if (player == null) {
            throw new ChessModelIntegrityException("Cannot plant a King with a null Square.", new NullPointerException());
        }
        if (square == null) {
            throw new ChessModelIntegrityException("Cannot plant a King with a null Player.", new NullPointerException());
        }
        new Piece(player, PieceType.KING, square, POINT_KING, Collections.singletonList(moveValidatorKing));
    }

}
