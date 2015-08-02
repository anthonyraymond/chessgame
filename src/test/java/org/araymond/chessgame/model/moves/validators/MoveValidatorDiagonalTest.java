package org.araymond.chessgame.model.moves.validators;

import org.araymond.chessgame.model.board.Board;
import org.araymond.chessgame.model.board.SpecialBoardBuilder;
import org.araymond.chessgame.model.moves.Direction;
import org.araymond.chessgame.model.pieces.PieceType;
import org.araymond.chessgame.model.player.Player;
import org.araymond.chessgame.model.player.PlayerType;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThat;

/**
 * Created by Anthony on 21/06/2015.
 */
public class MoveValidatorDiagonalTest {
    private Player white;
    private Player black;
    private MoveValidator moveValidatorDiagonal;

    @Before
    public void setUp() {
        white = new Player("Anthony", PlayerType.WHITE);
        black = new Player("Opponent", PlayerType.BLACK);
        moveValidatorDiagonal = new MoveValidatorDiagonal();
    }


    @Test
    public void shouldBeAValidMove() {
        Direction[] path = {Direction.NORTHEAST, Direction.NORTHEAST, Direction.NORTHEAST};
        Board board = new SpecialBoardBuilder().withPiece("b1", PieceType.BISHOP, white).build();

        assertThat(moveValidatorDiagonal.isMoveValid(board.getSquareByName("b1").getPiece(), path)).isTrue();
    }


    @Test
    public void shouldBeAValidCapture() {
        Direction[] path = {Direction.NORTHEAST, Direction.NORTHEAST, Direction.NORTHEAST};
        Board board = new SpecialBoardBuilder()
                .withPiece("b1", PieceType.BISHOP, white)
                .withPiece("e4", PieceType.BISHOP, black)
                .build();

        assertThat(moveValidatorDiagonal.isCaptureValid(board.getSquareByName("b1").getPiece(), path)).isTrue();
    }

}
