package org.araymond.chessgame.model.board;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import org.araymond.chessgame.exceptions.ChessInvalidPositionException;
import org.araymond.chessgame.exceptions.ChessModelIntegrityException;
import org.araymond.chessgame.model.moves.Direction;
import org.araymond.chessgame.model.pieces.Piece;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Anthony on 21/06/2015.
 */
public final class Square implements Observer {

    private String name;
    private Square north;
    private Square east;
    private Square south;
    private Square west;
    private Piece piece;

    Square(String name) {
        if (Strings.isNullOrEmpty(name)) {
            throw new ChessModelIntegrityException("Cannot build a Square without an empty or null name.", new IllegalArgumentException());
        }
        if (!name.matches("[a-h]{1}[1-8]{1}")) {
            throw new ChessModelIntegrityException("Square name is incorrect, must match \"[a-h]{1}[1-8]{1}\". Was : " + name, new IllegalArgumentException());
        }
        this.name = name;
    }

    public Piece getPiece() {
        return this.piece;
    }

    void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean isEmpty() {
        return piece == null;
    }

    public String getName() {
        return name;
    }

    public Square getNeighbour(Direction direction) {
        Square destination;

        destination = null;

        switch (direction) {
            case NORTH:
                destination = this.north;
                break;
            case EAST:
                destination = this.east;
                break;
            case SOUTH:
                destination = this.south;
                break;
            case WEST:
                destination = this.west;
                break;
            case NORTHEAST:
                destination = this.north;
                if (destination != null) {
                    destination = destination.east;
                }
                break;
            case NORTHWEST:
                destination = this.north;
                if (destination != null) { //if destination is null, return null at the first step. (prevent NPE)
                    destination = destination.west;
                }
                break;
            case SOUTHEAST:
                destination = this.south;
                if (destination != null) {
                    destination = destination.east;
                }
                break;
            case SOUTHWEST:
                destination = this.south;
                if (destination != null) {
                    destination = destination.west;
                }
                break;
        }

        return destination;
    }

    void setNeighbour(Direction direction, Square square) {

        switch (direction) {
            case NORTH:
                this.north = square;
                square.south = this;
                break;
            case EAST:
                this.east = square;
                square.west = this;
                break;
            case SOUTH:
                this.south = square;
                square.north = this;
                break;
            case WEST:
                this.west = square;
                square.east = this;
                break;
            default:
                throw new ChessInvalidPositionException("Unhandled Direction : " + direction.getValue(), new IllegalArgumentException());
        }
    }

    public boolean isOccupiedBySamePlayer(Square square2) {
        if (this.isEmpty() || square2.isEmpty()) {
            return false;
        }
        if (this.getPiece().getPlayer() == null || square2.getPiece().getPlayer() == null) {
            throw new ChessModelIntegrityException("Cannot compare two piece's owner if one of piece's owner are null", new IllegalArgumentException());
        }
        return this.getPiece().getPlayer().equals(square2.getPiece().getPlayer());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;

        return Objects.equal(name, square.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Piece) {
            Piece piece = (Piece) o;
            if (piece.getSquare().equals(this)) {
                this.setPiece(piece);
            } else {
                this.setPiece(null);
            }
        }
    }
}
