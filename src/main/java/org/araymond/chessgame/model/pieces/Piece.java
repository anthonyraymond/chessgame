package org.araymond.chessgame.model.pieces;

import org.araymond.chessgame.exceptions.ChessModelIntegrityException;
import org.araymond.chessgame.exceptions.ChessMoveException;
import org.araymond.chessgame.model.board.Square;
import org.araymond.chessgame.model.moves.Direction;
import org.araymond.chessgame.model.moves.validators.MoveValidator;
import org.araymond.chessgame.model.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by Anthony on 21/06/2015.
 */
public final class Piece extends Observable {
    private final Player player;
    private final int points;
    private final List<MoveValidator> moveValidators;
    private PieceType type;
    private Square square;

    Piece(Player player, PieceType pieceType, Square square, int points, List<MoveValidator> moveValidators) {
        if (player == null || pieceType == null || square == null || moveValidators == null) {
            throw new ChessModelIntegrityException("Cannot build a Piece without a Player, PieceType, a Square and MoveValidators.", new IllegalArgumentException());
        }
        this.player = player;
        this.type = pieceType;
        this.square = square;
        this.points = points;
        this.moveValidators = moveValidators;

        this.addObserver(square);
        this.setChanged();
        this.notifyObservers();
    }

    public Player getPlayer() {
        return player;
    }

    public PieceType getType() {
        return type;
    }

    public Square getSquare() {
        return square;
    }

    public Direction[] moveTo(Square dest) {
        if (dest == null) {
            throw new ChessMoveException("Tried to move to a null Square.", new IllegalArgumentException());
        }
        if (!dest.isEmpty()) {
            throw new ChessMoveException("Tried to move to an occupied square.", new IllegalArgumentException());
        }
        if (this.square.equals(dest)) {
            throw new ChessMoveException("Origin Square is the same a the destination Square, logical error.", new IllegalArgumentException());
        }
        if (this.getSquare().isOccupiedBySamePlayer(dest)) {
            throw new ChessMoveException("Cannot capture Piece that belongs to the same player.", new IllegalArgumentException());
        }

        Square origin = this.square;
        Direction[] path;

        path = this.findValidMovePathTo(dest);
        this.square = dest;

        this.addObserver(dest);
        this.setChanged();
        this.notifyObservers();
        this.deleteObserver(origin);

        return path;
    }

    public Direction[] captureTo(Square dest) {
        if (dest == null) {
            throw new ChessMoveException("Tried to move to a null Square.", new IllegalArgumentException());
        }
        if (dest.isEmpty()) {
            throw new ChessMoveException("Tried to capture a empty square.", new IllegalArgumentException());
        }
        if (this.square.equals(dest)) {
            throw new ChessMoveException("Origin Square is the same a the destination Square, logical error.", new IllegalArgumentException());
        }
        if (this.getSquare().isOccupiedBySamePlayer(dest)) {
            throw new ChessMoveException("Cannot capture Piece that belongs to the same player.", new IllegalArgumentException());
        }

        Square origin = this.square;
        Direction[] path;

        path = this.findValidCapturePathTo(dest);
        this.square = dest;

        // notify the square that we have changed position.
        this.addObserver(dest);
        this.setChanged();
        this.notifyObservers();
        this.deleteObserver(origin);

        return path;
    }

    private Direction[] findValidCapturePathTo(Square dest) {
        if (dest.isEmpty()) {
            throw new ChessMoveException("Tried to capture a empty square.", new IllegalArgumentException());
        }

        List<Direction[]> availablePathToDest = new ArrayList<>();

        for (MoveValidator moveValidator : this.moveValidators) {
            availablePathToDest.addAll(moveValidator.findValidCapturePathsTo(this, dest));
        }

        if (availablePathToDest.size() == 0) {
            throw new ChessMoveException("No path from " + this.getSquare().getName() + " to " + dest.getName() + " for a " + this.getType().getValue());
        }

        return availablePathToDest.get(0);
    }


    private Direction[] findValidMovePathTo(Square dest) {
        if (!dest.isEmpty()) {
            throw new ChessMoveException("Tried to move to an occupied square.", new IllegalArgumentException());
        }

        List<Direction[]> availablePathToDest = new ArrayList<>();

        for (MoveValidator moveValidator : this.moveValidators) {
            availablePathToDest.addAll(moveValidator.findValidMovePathsTo(this, dest));
        }

        if (availablePathToDest.size() == 0) {
            throw new ChessMoveException("No path from " + this.getSquare().getName() + " to " + dest.getName() + " for a " + this.getType().getValue());
        }

        return availablePathToDest.get(0);
    }

}
