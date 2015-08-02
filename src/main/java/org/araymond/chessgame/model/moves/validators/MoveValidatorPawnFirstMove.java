package org.araymond.chessgame.model.moves.validators;

import org.araymond.chessgame.model.moves.Direction;
import org.araymond.chessgame.model.player.Player;
import org.araymond.chessgame.model.player.PlayerType;

/**
 * Created by Anthony on 21/06/2015.
 */
public final class MoveValidatorPawnFirstMove extends MoveValidator {

    private final Direction[][] allowedWhiteMove = {
            new Direction[]{Direction.NORTH, Direction.NORTH}
    };
    private final Direction[][] allowedBlackMove = {
            new Direction[]{Direction.SOUTH, Direction.SOUTH}
    };

    @Override
    protected Direction[][] getAllowedDisplacementForPiece(Player player) {
        if (player.getType() == PlayerType.WHITE) {
            return this.allowedWhiteMove;
        }
        return allowedBlackMove;
    }

    @Override
    protected boolean isPathValidForCapture(Player player, Direction[] path) {
        return false;
    }
}
