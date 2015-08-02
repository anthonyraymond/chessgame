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
public class MoveValidatorPawnTest {
    private Player white;
    private Player black;
    private MoveValidator moveValidatorPawn;

    @Before
    public void setUp() {
        white = new Player("Anthony", PlayerType.WHITE);
        black = new Player("Opponent", PlayerType.BLACK);
        moveValidatorPawn = new MoveValidatorPawn();
    }


    @Test
    public void shouldReturnTrueWhenCallingIsMoveValidForAWhitePlayerAndAValidPath() {
        Direction[] path = {Direction.NORTH};
        Board board = new SpecialBoardBuilder().withPiece("b1", PieceType.PAWN, white).build();

        assertThat(moveValidatorPawn.isMoveValid(board.getSquareByName("b1").getPiece(), path)).isTrue();
    }


    @Test
    public void shouldReturnTrueWhenCallingIsMoveValidForABlackPlayerAndAValidPath() {
        Direction[] path = {Direction.SOUTH};
        Board board = new SpecialBoardBuilder().withPiece("a7", PieceType.PAWN, black).build();

        assertThat(moveValidatorPawn.isMoveValid(board.getSquareByName("a7").getPiece(), path)).isTrue();
    }


    @Test
    public void shouldReturnTrueWhenCallingIsCaptureValidForAWhitePlayerAndAValidPath() {
        Direction[] path = {Direction.NORTHEAST};
        Board board = new SpecialBoardBuilder().withPiece("b1", PieceType.PAWN, white).withPiece("c2", PieceType.PAWN, black).build();
        assertThat(moveValidatorPawn.isCaptureValid(board.getSquareByName("b1").getPiece(), path)).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenCallingIsCaptureValidForABlackPlayerAndAValidPath() {
        Direction[] path = {Direction.SOUTHEAST};
        Board board = new SpecialBoardBuilder().withPiece("a7", PieceType.PAWN, black).withPiece("b6", PieceType.PAWN, white).build();
        assertThat(moveValidatorPawn.isCaptureValid(board.getSquareByName("a7").getPiece(), path)).isTrue();
    }

}
