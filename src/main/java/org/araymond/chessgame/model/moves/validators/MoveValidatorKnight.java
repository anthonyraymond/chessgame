package org.araymond.chessgame.model.moves.validators;

import org.araymond.chessgame.model.board.Square;
import org.araymond.chessgame.model.moves.Direction;
import org.araymond.chessgame.model.player.Player;

/**
 * Created by Anthony on 21/06/2015.
 */
public final class MoveValidatorKnight extends MoveValidator {

    private final Direction[][] allowedMove = {
            new Direction[]{Direction.NORTH, Direction.NORTH, Direction.EAST},
            new Direction[]{Direction.EAST, Direction.NORTH, Direction.NORTH},
            new Direction[]{Direction.NORTH, Direction.NORTH, Direction.WEST},
            new Direction[]{Direction.WEST, Direction.NORTH, Direction.NORTH},

            new Direction[]{Direction.EAST, Direction.EAST, Direction.NORTH},
            new Direction[]{Direction.NORTH, Direction.EAST, Direction.EAST},
            new Direction[]{Direction.EAST, Direction.EAST, Direction.SOUTH},
            new Direction[]{Direction.SOUTH, Direction.EAST, Direction.EAST},

            new Direction[]{Direction.WEST, Direction.WEST, Direction.NORTH},
            new Direction[]{Direction.NORTH, Direction.WEST, Direction.WEST},
            new Direction[]{Direction.WEST, Direction.WEST, Direction.SOUTH},
            new Direction[]{Direction.SOUTH, Direction.WEST, Direction.WEST},

            new Direction[]{Direction.SOUTH, Direction.SOUTH, Direction.EAST},
            new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.SOUTH},
            new Direction[]{Direction.SOUTH, Direction.SOUTH, Direction.WEST},
            new Direction[]{Direction.SOUTH, Direction.SOUTH, Direction.SOUTH}

    };

    @Override
    protected Direction[][] getAllowedDisplacementForPiece(Player player) {
        return this.allowedMove;
    }


    /**
     * A Knight can walk through pieces
     *
     * @param waypoint a Square where the Piece will walk onto.
     * @return true
     */
    @Override
    protected boolean isWaypointValid(Square waypoint) {
        return true;
    }

}
