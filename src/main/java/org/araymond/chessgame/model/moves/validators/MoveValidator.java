package org.araymond.chessgame.model.moves.validators;

import org.araymond.chessgame.exceptions.ChessMoveException;
import org.araymond.chessgame.model.board.Square;
import org.araymond.chessgame.model.moves.Direction;
import org.araymond.chessgame.model.pieces.Piece;
import org.araymond.chessgame.model.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Anthony on 21/06/2015.
 */
public abstract class MoveValidator {
    /**
     * Return an array of <code>Direction[]</code> which contains all possible <code>Move</code> for a specific move type
     *
     * @return an array of <code>Direction[]</code> which contains all possible <code>Move</code> for a specific move type
     */
    protected abstract Direction[][] getAllowedDisplacementForPiece(Player player);

    /**
     * Determine if the given path would lead to an existing Square (and not outside the board).
     *
     * @param origin Square where to start the path.
     * @param path   path to test.
     * @return boolean
     */
    private boolean isPathInbound(Square origin, Direction[] path) {
        Square dest = origin;

        for (Direction dir : path) {
            dest = dest.getNeighbour(dir);
            if (dest == null) {
                return false;
            }
        }

        return true;
    }

    /**
     * Called for each Square but the last where the piece will walk onto.<br>
     * Determine if the Square is a valid square to walk through.<br>
     * A queen can't walk through pieces but a Knight can.
     *
     * @param waypoint a Square where the Piece will walk onto.
     * @return true if the Square is a valid waypoint.
     */
    boolean isWaypointValid(Square waypoint) {
        return waypoint.isEmpty();
    }

    /**
     * Determine if the move is valid for a capture with a specific MoveValidator.
     *
     * @param path Path to be followed to move the Piece
     * @return true if the capture path is valid for a move.
     */
    boolean isPathValidForCapture(Player player, Direction[] path) {
        for (Direction[] dir : this.getAllowedDisplacementForPiece(player)) {
            if (Arrays.equals(dir, path)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determine if the move is valid for a move with a specific MoveValidator.
     *
     * @param path Path to be followed to move the Piece
     * @return true if the move path is valid for a move.
     */
    boolean isPathValidForMove(Player player, Direction[] path) {
        for (Direction[] dir : this.getAllowedDisplacementForPiece(player)) {
            if (Arrays.equals(dir, path)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determine if a move can be done by the given {@code Piece} according to the given {@code Direction[]}.
     *
     * @param piece The piece to ensure it can move.
     * @param path  Path the piece should follow.
     * @return true if the path is valid for the {@code Piece}
     */
    final boolean isMoveValid(Piece piece, Direction[] path) {
        if (piece == null) {
            throw new ChessMoveException("Cannot validate a move with a null Piece", new IllegalArgumentException());
        }
        if (path == null || path.length == 0) {
            throw new ChessMoveException("Cannot validate a move which is empty or null", new IllegalArgumentException());
        }
        if (!this.isPathInbound(piece.getSquare(), path) || !this.isPathValidForMove(piece.getPlayer(), path)) {
            return false;
        }

        Square dest = piece.getSquare();

        for (int i = 0; i < path.length; i++) {
            dest = dest.getNeighbour(path[i]);
            //For square which are not first or last in way, we need to check if the waypont is valid, because some piece are able to walk on non empty square (Knight).
            if (i != path.length - 1) {
                if (!this.isWaypointValid(dest)) {
                    return false;
                }
            }
        }

        if (!dest.isEmpty()) {
            throw new ChessMoveException("Cannot move to a Square that contains a Piece");
        }

        return true;
    }

    /**
     * Determine if a capture can be done by the given {@code Piece} according to the given {@code Direction[]}.
     *
     * @param piece The piece to ensure it can move to capture another.
     * @param path  Path the piece should follow.
     * @return true if the path is valid for the {@code Piece}
     */
    final boolean isCaptureValid(Piece piece, Direction[] path) {
        if (piece == null) {
            throw new ChessMoveException("Cannot validate a capture with a null Piece", new IllegalArgumentException());
        }
        if (path == null || path.length == 0) {
            throw new ChessMoveException("Cannot validate a capture which is empty or null", new IllegalArgumentException());
        }
        if (!this.isPathInbound(piece.getSquare(), path) || !this.isPathValidForCapture(piece.getPlayer(), path)) {
            return false;
        }

        Square dest = piece.getSquare();

        for (int i = 0; i < path.length; i++) {
            dest = dest.getNeighbour(path[i]);
            //For square which are not first or last in way, we need to check if the waypont is valid
            if (i != path.length - 1) {
                if (!this.isWaypointValid(dest)) {
                    return false;
                }
            }
        }

        if (dest.isEmpty() || piece.getSquare().isOccupiedBySamePlayer(dest)) {
            throw new ChessMoveException("Cannot capture to Square that contains no Piece or one of your Pieces");
        }

        return true;
    }


    public final List<Direction[]> findValidCapturePathsTo(Piece piece, Square dest) {
        if (piece == null) {
            throw new ChessMoveException("Cannot move a null piece.", new IllegalArgumentException());
        }
        if (dest == null) {
            throw new ChessMoveException("Tried to move to a null Square.", new IllegalArgumentException());
        }
        if (dest.isEmpty()) {
            throw new ChessMoveException("Cannot capture an empty square.", new IllegalArgumentException());
        }
        if (piece.getSquare().equals(dest)) {
            throw new ChessMoveException("Origin Square is the same a the destination Square, logical error.", new IllegalArgumentException());
        }
        if (piece.getSquare().isOccupiedBySamePlayer(dest)) {
            throw new ChessMoveException("Cannot capture a Piece that belongs to the same player.", new IllegalArgumentException());
        }

        List<Direction[]> paths = new ArrayList<>();
        Square destBuffer;

        for (Direction[] path : this.getAllowedDisplacementForPiece(piece.getPlayer())) {
            destBuffer = piece.getSquare();
            for (Direction dir : path) {
                if (destBuffer != null) {
                    destBuffer = destBuffer.getNeighbour(dir);
                }
            }
            if (destBuffer == dest && isCaptureValid(piece, path)) {
                paths.add(path);
            }
        }

        return Collections.unmodifiableList(paths);
    }

    public final List<Direction[]> findValidMovePathsTo(Piece piece, Square dest) {
        if (piece == null) {
            throw new ChessMoveException("Cannot move a null piece.", new IllegalArgumentException());
        }
        if (dest == null) {
            throw new ChessMoveException("Tried to move to a null Square.", new IllegalArgumentException());
        }
        if (!dest.isEmpty()) {
            throw new ChessMoveException("Tried to move to an occupied square", new IllegalArgumentException());
        }
        if (piece.getSquare().equals(dest)) {
            throw new ChessMoveException("Origin Square is the same a the destination Square, logical error.", new IllegalArgumentException());
        }

        List<Direction[]> paths = new ArrayList<>();
        Square destBuffer;

        for (Direction[] path : this.getAllowedDisplacementForPiece(piece.getPlayer())) {
            destBuffer = piece.getSquare();
            for (Direction dir : path) {
                if (destBuffer != null) {
                    destBuffer = destBuffer.getNeighbour(dir);
                }
            }
            if (destBuffer == dest && isMoveValid(piece, path)) {
                paths.add(path);
            }
        }

        return Collections.unmodifiableList(paths);
    }
}

