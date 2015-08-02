package org.araymond.chessgame.model.board;

import org.araymond.chessgame.exceptions.ChessInvalidPositionException;
import org.araymond.chessgame.model.moves.Direction;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anthony on 21/06/2015.
 */
public final class Board {
    private final Map<String, Square> boardSquares;

    Board() {
        this.boardSquares = new HashMap<>();
        Square firstSquare; // The first Square is the bottom left one (a1)

        firstSquare = this.createEmptyBoard();

        fillMap(firstSquare);
    }

    private void fillMap(Square firstSquare) {
        Square buffer;
        do {
            buffer = firstSquare;
            do {
                //adding each square in the column to the Map
                this.boardSquares.put(buffer.getName(), buffer);
            } while ((buffer = buffer.getNeighbour(Direction.EAST)) != null); //going to the square on the east

        } while ((firstSquare = firstSquare.getNeighbour(Direction.NORTH)) != null); //going to the line upstairs
    }

    private Square createEmptyBoard() {
        StringBuilder sb;
        Square firstSquare;
        Square buffer;

        //a1 is the most bottom left square of a board.
        sb = new StringBuilder("a1");
        firstSquare = new Square(sb.toString());
        buffer = firstSquare;

        //Creating the first column (starting with bottom left corner (a1) to the top left corner (a8)
        for (int i = 1; i < 8; ++i) {
            //replace the digit in the string by his value + 1
            sb.setCharAt(1, (char) (sb.charAt(1) + 1));
            buffer.setNeighbour(Direction.NORTH, new Square(sb.toString()));
            buffer = buffer.getNeighbour(Direction.NORTH);
        }

        //Now we got our first column, we can append each line one by one, starting from the top left corner.
        for (int line = 0; line < 8; ++line) {

            for (int column = 1; column < 8; ++column) {
                //we're going to the very right next column, so we increment le letter
                sb.setCharAt(0, (char) (sb.charAt(0) + 1));
                buffer.setNeighbour(Direction.EAST, new Square(sb.toString()));
                buffer = buffer.getNeighbour(Direction.EAST);

                //The first line does not have a line above so we don't need to link it
                if (line != 0) {
                    buffer.setNeighbour(Direction.NORTH,
                            //Our above neighbour is the square at the east of the one at the northWest
                            buffer.getNeighbour(Direction.WEST).getNeighbour(Direction.NORTH).getNeighbour(Direction.EAST));
                }
            }

            //Come back to the first column
            while (buffer.getNeighbour(Direction.WEST) != null) {
                buffer = buffer.getNeighbour(Direction.WEST);
            }
            //We are back to the first column, so we are at 'a' letter
            sb.setCharAt(0, 'a');

            //Go to the line below
            buffer = buffer.getNeighbour(Direction.SOUTH);
            //We goes one lne below, so we need to decrement the digit in the squareId
            sb.setCharAt(1, (char) (sb.charAt(1) - 1));
        }

        return firstSquare;
    }

    public Square getSquareByName(String squareName) {
        if (!this.boardSquares.containsKey(squareName)) {
            throw new ChessInvalidPositionException(squareName + " position does not exists within Board", new IllegalArgumentException());
        }
        return this.boardSquares.get(squareName);
    }
}
