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
public class MoveValidatorPawnFirstMoveTest {
    private Player white;
    private Player black;
    private MoveValidator moveValidatorPawnFirstMove;

    @Before
    public void setUp() {
        white = new Player("Anthony", PlayerType.WHITE);
        black = new Player("Opponent", PlayerType.BLACK);
        moveValidatorPawnFirstMove = new MoveValidatorPawnFirstMove();
    }

    @Test
    public void shouldReturnTrueWhenCallingIsValidMoveWithWhitePlayerValidPath() {
        Direction[] path = {Direction.NORTH, Direction.NORTH};
        Board board = new SpecialBoardBuilder().withPiece("b1", PieceType.PAWN, white).build();

        assertThat(moveValidatorPawnFirstMove.isMoveValid(board.getSquareByName("b1").getPiece(), path)).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenCallingIsValidMoveWithBlackPlayerValidPath() {
        Direction[] path = {Direction.SOUTH, Direction.SOUTH};
        Board board = new SpecialBoardBuilder().withPiece("a7", PieceType.PAWN, black).build();

        assertThat(moveValidatorPawnFirstMove.isMoveValid(board.getSquareByName("a7").getPiece(), path)).isTrue();
    }

    @Test
    public void shouldNotBeAValidPathWhenAnotherPieceInOnTheWay() {
        Direction[] path = {Direction.NORTH, Direction.NORTH};
        Board board = new SpecialBoardBuilder()
                .withPiece("b2", PieceType.PAWN, white)
                .withPiece("b3", PieceType.PAWN, black)
                .build();
        assertThat(moveValidatorPawnFirstMove.isMoveValid(board.getSquareByName("b2").getPiece(), path)).isFalse();
    }

    /**
     * Can't ever capture with pawn first move
     */
    @Test
    public void shouldNotBeAValidCaptureMoveSincePawnCantCaptureWithSpecialFirstMove() {
        Direction[] path = {Direction.NORTH, Direction.NORTH};
        Board board = new SpecialBoardBuilder()
                .withPiece("b1", PieceType.PAWN, white)
                .withPiece("b3", PieceType.PAWN, black)
                .build();

        assertThat(moveValidatorPawnFirstMove.isCaptureValid(board.getSquareByName("b1").getPiece(), path)).isFalse();
    }

}
