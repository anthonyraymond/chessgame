package org.araymond.chessgame.model.moves.validators;

import org.araymond.chessgame.model.moves.Direction;
import org.araymond.chessgame.model.player.Player;

/**
 * Created by Anthony on 21/06/2015.
 */
public final class MoveValidatorKing extends MoveValidator {

    private final Direction[][] allowedMove = {
            new Direction[]{Direction.NORTH},
            new Direction[]{Direction.EAST},
            new Direction[]{Direction.SOUTH},
            new Direction[]{Direction.WEST},
            new Direction[]{Direction.NORTHEAST},
            new Direction[]{Direction.NORTHWEST},
            new Direction[]{Direction.SOUTHEAST},
            new Direction[]{Direction.SOUTHWEST}
    };

    @Override
    protected Direction[][] getAllowedDisplacementForPiece(Player player) {
        return this.allowedMove;
    }

}