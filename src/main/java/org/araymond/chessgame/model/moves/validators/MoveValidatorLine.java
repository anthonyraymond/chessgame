package org.araymond.chessgame.model.moves.validators;

import org.araymond.chessgame.model.moves.Direction;
import org.araymond.chessgame.model.player.Player;

/**
 * Created by Anthony on 21/06/2015.
 */
public final class MoveValidatorLine extends MoveValidator {

    private final Direction[][] allowedMove = {
            new Direction[]{Direction.NORTH},
            new Direction[]{Direction.NORTH, Direction.NORTH},
            new Direction[]{Direction.NORTH, Direction.NORTH, Direction.NORTH},
            new Direction[]{Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.NORTH,},
            new Direction[]{Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.NORTH},
            new Direction[]{Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.NORTH},
            new Direction[]{Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.NORTH},

            new Direction[]{Direction.EAST},
            new Direction[]{Direction.EAST, Direction.EAST},
            new Direction[]{Direction.EAST, Direction.EAST, Direction.EAST},
            new Direction[]{Direction.EAST, Direction.EAST, Direction.EAST, Direction.EAST,},
            new Direction[]{Direction.EAST, Direction.EAST, Direction.EAST, Direction.EAST, Direction.EAST},
            new Direction[]{Direction.EAST, Direction.EAST, Direction.EAST, Direction.EAST, Direction.EAST, Direction.EAST},
            new Direction[]{Direction.EAST, Direction.EAST, Direction.EAST, Direction.EAST, Direction.EAST, Direction.EAST, Direction.EAST},

            new Direction[]{Direction.SOUTH},
            new Direction[]{Direction.SOUTH, Direction.SOUTH},
            new Direction[]{Direction.SOUTH, Direction.SOUTH, Direction.SOUTH},
            new Direction[]{Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH,},
            new Direction[]{Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH},
            new Direction[]{Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH},
            new Direction[]{Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH},

            new Direction[]{Direction.WEST},
            new Direction[]{Direction.WEST, Direction.WEST},
            new Direction[]{Direction.WEST, Direction.WEST, Direction.WEST},
            new Direction[]{Direction.WEST, Direction.WEST, Direction.WEST, Direction.WEST,},
            new Direction[]{Direction.WEST, Direction.WEST, Direction.WEST, Direction.WEST, Direction.WEST},
            new Direction[]{Direction.WEST, Direction.WEST, Direction.WEST, Direction.WEST, Direction.WEST, Direction.WEST},
            new Direction[]{Direction.WEST, Direction.WEST, Direction.WEST, Direction.WEST, Direction.WEST, Direction.WEST, Direction.WEST},
    };

    @Override
    protected Direction[][] getAllowedDisplacementForPiece(Player player) {
        return this.allowedMove;
    }

}
