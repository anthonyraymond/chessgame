package org.araymond.chessgame.model.moves.validators;

import org.araymond.chessgame.model.moves.Direction;
import org.araymond.chessgame.model.player.Player;

/**
 * Created by Anthony on 21/06/2015.
 */
public final class MoveValidatorPawn extends MoveValidator {

    private final Direction[][] allowedWhiteMove = {
            new Direction[]{Direction.NORTH},     //regular move
            new Direction[]{Direction.NORTHEAST}, //Only when taking other piece
            new Direction[]{Direction.NORTHWEST}  //Only when taking other piece
    };
    private final Direction[][] allowedBlackMove = {
            new Direction[]{Direction.SOUTH},     //regular move
            new Direction[]{Direction.SOUTHEAST}, //Only when taking other piece
            new Direction[]{Direction.SOUTHWEST}  //Only when taking other piece
    };

    @Override
    protected Direction[][] getAllowedDisplacementForPiece(Player player) {
        if (player.isWhite()) {
            return this.allowedWhiteMove;
        }
        return allowedBlackMove;
    }


    @Override
    protected boolean isPathValidForCapture(Player player, Direction[] path) {
        if (player.isWhite()) {
            return (path.length == 1 && (path[0] == Direction.NORTHEAST || path[0] == Direction.NORTHWEST));
        }
        //black player case
        return (path.length == 1 && (path[0] == Direction.SOUTHEAST || path[0] == Direction.SOUTHWEST));
    }

    @Override
    protected boolean isPathValidForMove(Player player, Direction[] path) {
        if (player.isWhite()) {
            return (path.length == 1 && path[0] == Direction.NORTH);
        }
        //black player case
        return (path.length == 1 && path[0] == Direction.SOUTH);
    }

}