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
import static org.junit.Assert.assertTrue;

/**
 * Created by Anthony on 21/06/2015.
 */
public class MoveValidatorKnightTest {
    private Player white;
    private Player black;
    private MoveValidator moveValidatorKnight;

    @Before
    public void setUp() {
        white = new Player("Anthony", PlayerType.WHITE);
        black = new Player("Opponent", PlayerType.BLACK);
        moveValidatorKnight = new MoveValidatorKnight();
    }


    @Test
    public void shouldBeAValidMove() {
        Direction[] path = {Direction.NORTH, Direction.NORTH, Direction.EAST};
        Board board = new SpecialBoardBuilder().withPiece("b1", PieceType.KNIGHT, white).build();

        assertThat(moveValidatorKnight.isMoveValid(board.getSquareByName("b1").getPiece(), path)).isTrue();
    }

    @Test
    public void shouldBeAValidCaptureWithPieceInPath() {
        Direction[] path = {Direction.NORTH, Direction.NORTH, Direction.EAST};
        Board board = new SpecialBoardBuilder()
                .withPiece("b1", PieceType.KNIGHT, white)
                .withPiece("b2", PieceType.KNIGHT, white)
                .build();
        assertTrue(moveValidatorKnight.isMoveValid(board.getSquareByName("b1").getPiece(), path));
    }


    @Test
    public void shouldBeAValidCapture() {
        Direction[] path = {Direction.NORTH, Direction.NORTH, Direction.EAST};
        Board board = new SpecialBoardBuilder()
                .withPiece("b1", PieceType.KNIGHT, white)
                .withPiece("c3", PieceType.KNIGHT, black)
                .build();

        assertTrue(moveValidatorKnight.isCaptureValid(board.getSquareByName("b1").getPiece(), path));
    }

    @Test
    public void ShouldBeAValidCaptureWithPieceInPath() {
        Direction[] path = {Direction.NORTH, Direction.NORTH, Direction.EAST};
        Board board = new SpecialBoardBuilder()
                .withPiece("b1", PieceType.KNIGHT, white)
                .withPiece("b2", PieceType.KNIGHT, white)
                .withPiece("c3", PieceType.KNIGHT, black)
                .build();
        assertTrue(moveValidatorKnight.isCaptureValid(board.getSquareByName("b1").getPiece(), path));
    }

}
