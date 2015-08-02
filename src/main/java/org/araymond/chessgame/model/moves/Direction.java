package org.araymond.chessgame.model.moves;

/**
 * Created by Anthony on 21/06/2015.
 */
public enum Direction {
    NORTH("N"),
    EAST("E"),
    SOUTH("S"),
    WEST("W"),
    NORTHEAST("NE"),
    NORTHWEST("NW"),
    SOUTHEAST("SE"),
    SOUTHWEST("SW");

    static {
        NORTH.opposite = SOUTH;
        SOUTH.opposite = NORTH;
        EAST.opposite = WEST;
        WEST.opposite = EAST;
        NORTHEAST.opposite = SOUTHWEST;
        NORTHWEST.opposite = SOUTHEAST;
        SOUTHEAST.opposite = NORTHWEST;
        SOUTHWEST.opposite = NORTHEAST;
    }

    private final String value;
    private Direction opposite;

    Direction(String value) {
        this.value = value;
    }

    public Direction getOppositeDirection() {
        return opposite;
    }

    public String getValue() {
        return this.value;
    }
}
