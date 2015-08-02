package org.araymond.chessgame.model.moves.validators;

import org.araymond.chessgame.model.board.Board;
import org.araymond.chessgame.model.board.SpecialBoardBuilder;
import org.araymond.chessgame.model.moves.Direction;
import org.araymond.chessgame.model.pieces.PieceType;
import org.araymond.chessgame.model.player.Player;
import org.araymond.chessgame.model.player.PlayerType;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 21/06/2015.
 */
public class MoveValidatorLineTest {
    private Player white;
    private Player black;
    private MoveValidator moveValidatorLine;

    @Before
    public void setUp() {
        white = new Player("Anthony", PlayerType.WHITE);
        black = new Player("Opponent", PlayerType.BLACK);
        moveValidatorLine = new MoveValidatorLine();
    }

    @Test
    public void shouldBeAValidMove() {
        Direction[] path = {Direction.NORTH, Direction.NORTH, Direction.NORTH};
        Board board = new SpecialBoardBuilder().withPiece("b1", PieceType.ROOK, white).build();

        assertThat(moveValidatorLine.isMoveValid(board.getSquareByName("b1").getPiece(), path)).isTrue();
    }

    @Test
    public void shouldBeAValidCapture() {
        Direction[] path = {Direction.NORTH, Direction.NORTH, Direction.NORTH};
        Board board = new SpecialBoardBuilder()
                .withPiece("b1", PieceType.ROOK, white)
                .withPiece("b4", PieceType.ROOK, black)
                .build();

        assertThat(moveValidatorLine.isCaptureValid(board.getSquareByName("b1").getPiece(), path)).isTrue();
    }

}
