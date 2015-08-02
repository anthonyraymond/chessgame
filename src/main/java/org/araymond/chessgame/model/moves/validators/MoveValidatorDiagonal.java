package org.araymond.chessgame.model.moves.validators;

import org.araymond.chessgame.model.moves.Direction;
import org.araymond.chessgame.model.player.Player;

/**
 * Created by Anthony on 21/06/2015.
 */
public final class MoveValidatorDiagonal extends MoveValidator {

    private final Direction[][] allowedMove = {
            new Direction[]{Direction.NORTHEAST},
            new Direction[]{Direction.NORTHEAST, Direction.NORTHEAST},
            new Direction[]{Direction.NORTHEAST, Direction.NORTHEAST, Direction.NORTHEAST},
            new Direction[]{Direction.NORTHEAST, Direction.NORTHEAST, Direction.NORTHEAST, Direction.NORTHEAST,},
            new Direction[]{Direction.NORTHEAST, Direction.NORTHEAST, Direction.NORTHEAST, Direction.NORTHEAST, Direction.NORTHEAST},
            new Direction[]{Direction.NORTHEAST, Direction.NORTHEAST, Direction.NORTHEAST, Direction.NORTHEAST, Direction.NORTHEAST, Direction.NORTHEAST},
            new Direction[]{Direction.NORTHEAST, Direction.NORTHEAST, Direction.NORTHEAST, Direction.NORTHEAST, Direction.NORTHEAST, Direction.NORTHEAST, Direction.NORTHEAST},

            new Direction[]{Direction.NORTHWEST},
            new Direction[]{Direction.NORTHWEST, Direction.NORTHWEST},
            new Direction[]{Direction.NORTHWEST, Direction.NORTHWEST, Direction.NORTHWEST},
            new Direction[]{Direction.NORTHWEST, Direction.NORTHWEST, Direction.NORTHWEST, Direction.NORTHWEST,},
            new Direction[]{Direction.NORTHWEST, Direction.NORTHWEST, Direction.NORTHWEST, Direction.NORTHWEST, Direction.NORTHWEST},
            new Direction[]{Direction.NORTHWEST, Direction.NORTHWEST, Direction.NORTHWEST, Direction.NORTHWEST, Direction.NORTHWEST, Direction.NORTHWEST},
            new Direction[]{Direction.NORTHWEST, Direction.NORTHWEST, Direction.NORTHWEST, Direction.NORTHWEST, Direction.NORTHWEST, Direction.NORTHWEST, Direction.NORTHWEST},

            new Direction[]{Direction.SOUTHEAST},
            new Direction[]{Direction.SOUTHEAST, Direction.SOUTHEAST},
            new Direction[]{Direction.SOUTHEAST, Direction.SOUTHEAST, Direction.SOUTHEAST},
            new Direction[]{Direction.SOUTHEAST, Direction.SOUTHEAST, Direction.SOUTHEAST, Direction.SOUTHEAST,},
            new Direction[]{Direction.SOUTHEAST, Direction.SOUTHEAST, Direction.SOUTHEAST, Direction.SOUTHEAST, Direction.SOUTHEAST},
            new Direction[]{Direction.SOUTHEAST, Direction.SOUTHEAST, Direction.SOUTHEAST, Direction.SOUTHEAST, Direction.SOUTHEAST, Direction.SOUTHEAST},
            new Direction[]{Direction.SOUTHEAST, Direction.SOUTHEAST, Direction.SOUTHEAST, Direction.SOUTHEAST, Direction.SOUTHEAST, Direction.SOUTHEAST, Direction.SOUTHEAST},

            new Direction[]{Direction.SOUTHWEST},
            new Direction[]{Direction.SOUTHWEST, Direction.SOUTHWEST},
            new Direction[]{Direction.SOUTHWEST, Direction.SOUTHWEST, Direction.SOUTHWEST},
            new Direction[]{Direction.SOUTHWEST, Direction.SOUTHWEST, Direction.SOUTHWEST, Direction.SOUTHWEST,},
            new Direction[]{Direction.SOUTHWEST, Direction.SOUTHWEST, Direction.SOUTHWEST, Direction.SOUTHWEST, Direction.SOUTHWEST},
            new Direction[]{Direction.SOUTHWEST, Direction.SOUTHWEST, Direction.SOUTHWEST, Direction.SOUTHWEST, Direction.SOUTHWEST, Direction.SOUTHWEST},
            new Direction[]{Direction.SOUTHWEST, Direction.SOUTHWEST, Direction.SOUTHWEST, Direction.SOUTHWEST, Direction.SOUTHWEST, Direction.SOUTHWEST, Direction.SOUTHWEST}
    };

    @Override
    protected Direction[][] getAllowedDisplacementForPiece(Player player) {
        return this.allowedMove;
    }

}
