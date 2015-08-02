package org.araymond.chessgame.model.board;

import org.araymond.chessgame.model.pieces.PieceFactory;
import org.araymond.chessgame.model.pieces.PieceType;
import org.araymond.chessgame.model.player.Player;

/**
 * Created by Anthony on 21/06/2015.
 */
public class SpecialBoardBuilder {
    private final Board board;

    public SpecialBoardBuilder() {
        board = new Board();
    }

    public SpecialBoardBuilder withPiece(String pos, PieceType pieceType, Player player) {
        if (pieceType == PieceType.PAWN) {
            PieceFactory.putPawn(player, board.getSquareByName(pos));
        } else if (pieceType == PieceType.BISHOP) {
            PieceFactory.putBishop(player, board.getSquareByName(pos));
        } else if (pieceType == PieceType.KING) {
            PieceFactory.putKing(player, board.getSquareByName(pos));
        } else if (pieceType == PieceType.QUEEN) {
            PieceFactory.putQueen(player, board.getSquareByName(pos));
        } else if (pieceType == PieceType.ROOK) {
            PieceFactory.putRook(player, board.getSquareByName(pos));
        } else if (pieceType == PieceType.KNIGHT) {
            PieceFactory.putKnight(player, board.getSquareByName(pos));
        }
        return this;
    }

    public Board build() {
        return this.board;
    }
}
