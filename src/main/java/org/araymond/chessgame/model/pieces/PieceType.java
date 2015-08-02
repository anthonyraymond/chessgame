package org.araymond.chessgame.model.pieces;

/**
 * Created by Anthony on 21/06/2015.
 */
public enum PieceType {
    PAWN("Pawn"),
    KNIGHT("Knight"),
    BISHOP("Bishop"),
    ROOK("Rook"),
    QUEEN("Queen"),
    KING("King");

    private final String value;

    PieceType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
