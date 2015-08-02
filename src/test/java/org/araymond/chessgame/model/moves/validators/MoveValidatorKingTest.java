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
public class MoveValidatorKingTest {
    private Player white;
    private Player black;
    private MoveValidator moveValidatorKing;

    @Before
    public void setUp() {
        white = new Player("Anthony", PlayerType.WHITE);
        black = new Player("Opponent", PlayerType.BLACK);
        moveValidatorKing = new MoveValidatorKing();
    }

    @Test
    public void shouldBeAValidMove() {
        Direction[] path = {Direction.NORTHEAST};
        Board board = new SpecialBoardBuilder().withPiece("b1", PieceType.KING, white).build();

        assertThat(moveValidatorKing.isMoveValid(board.getSquareByName("b1").getPiece(), path)).isTrue();
    }


    @Test
    public void shouldBeAValidCaptureMove() {
        Direction[] path = {Direction.NORTHEAST};
        Board board = new SpecialBoardBuilder()
                .withPiece("b1", PieceType.KING, white)
                .withPiece("c2", PieceType.KING, black)
                .build();

        assertThat(moveValidatorKing.isCaptureValid(board.getSquareByName("b1").getPiece(), path)).isTrue();
    }

}
