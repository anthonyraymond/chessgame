package org.araymond.chessgame.model.board;

import org.araymond.chessgame.ChessGameTestUtils;
import org.araymond.chessgame.exceptions.ChessModelIntegrityException;
import org.araymond.chessgame.model.moves.Direction;
import org.araymond.chessgame.model.pieces.PieceType;
import org.araymond.chessgame.model.player.PlayerType;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Anthony on 21/06/2015.
 */
public class BoardFactoryTest {
    private Board board;

    @Before
    public void setUp() {
        board = BoardFactory.createBoardWithPieces(ChessGameTestUtils.createValidPlayerSet());
    }

    @Test
    public void shouldCreateBoardWithoutPlayerSet() {
        try {
            BoardFactory.createBoardWithPieces(null);
            fail();
        } catch (ChessModelIntegrityException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldCreateAValidBoard() {
        Square start,
                buffer;
        StringBuilder sb;
        String expectedSquareName;
        int line,
                column;

        start = board.getSquareByName("a1");
        buffer = start;

        //This part test that each square exists
        for (line = 7; line >= 0; --line) {
            for (column = 0; column < 8; ++column) {

                sb = new StringBuilder(buffer.getName());
                if (line != 7) {
                    expectedSquareName = String.valueOf(sb.charAt(0)) + String.valueOf((char) (sb.charAt(1) - 1));
                    assertThat(buffer.getNeighbour(Direction.SOUTH).getName()).isEqualTo(expectedSquareName);
                }
                if (line != 0) {
                    expectedSquareName = String.valueOf(sb.charAt(0)) + String.valueOf((char) (sb.charAt(1) + 1));
                    assertThat(buffer.getNeighbour(Direction.NORTH).getName()).isEqualTo(expectedSquareName);
                }

                if (column != 7) {
                    buffer = buffer.getNeighbour(Direction.EAST);
                }
            }

            for (int i = 0; i < 7; ++i) {
                buffer = buffer.getNeighbour(Direction.WEST);
            }

            buffer = buffer.getNeighbour(Direction.NORTH);
        }

        //This part test each directions moves
        Square a1 = start.getNeighbour(Direction.NORTH)
                .getNeighbour(Direction.EAST)
                .getNeighbour(Direction.SOUTH)
                .getNeighbour(Direction.WEST);
        Square b2 = start.getNeighbour(Direction.NORTH)
                .getNeighbour(Direction.EAST);
        Square d8 = start.getNeighbour(Direction.NORTH)
                .getNeighbour(Direction.EAST)
                .getNeighbour(Direction.EAST)
                .getNeighbour(Direction.EAST)
                .getNeighbour(Direction.EAST)
                .getNeighbour(Direction.EAST)
                .getNeighbour(Direction.WEST)
                .getNeighbour(Direction.WEST)
                .getNeighbour(Direction.WEST)
                .getNeighbour(Direction.EAST)
                .getNeighbour(Direction.NORTH)
                .getNeighbour(Direction.NORTH)
                .getNeighbour(Direction.NORTH)
                .getNeighbour(Direction.NORTH)
                .getNeighbour(Direction.NORTH)
                .getNeighbour(Direction.SOUTH)
                .getNeighbour(Direction.NORTH)
                .getNeighbour(Direction.NORTH);
        Square e5 = start.getNeighbour(Direction.NORTHEAST) // b2
                .getNeighbour(Direction.NORTHEAST)          // c3
                .getNeighbour(Direction.NORTHEAST)          // d4
                .getNeighbour(Direction.NORTHEAST)          // e5
                .getNeighbour(Direction.NORTHEAST)          // f6
                .getNeighbour(Direction.NORTHWEST)          // e7
                .getNeighbour(Direction.SOUTHWEST)          // d6
                .getNeighbour(Direction.SOUTHEAST);         // e5
        Square h8 = start.getNeighbour(Direction.NORTHEAST)
                .getNeighbour(Direction.NORTHEAST)
                .getNeighbour(Direction.NORTHEAST)
                .getNeighbour(Direction.NORTHEAST)
                .getNeighbour(Direction.NORTHEAST)
                .getNeighbour(Direction.NORTHEAST)
                .getNeighbour(Direction.NORTHEAST);

        assertThat(a1.getName()).isEqualTo("a1");
        assertThat(b2.getName()).isEqualTo("b2");
        assertThat(d8.getName()).isEqualTo("d8");
        assertThat(e5.getName()).isEqualTo("e5");
        assertThat(h8.getName()).isEqualTo("h8");
    }

    @Test
    public void shouldPlantPieces() {
        assertThat(board.getSquareByName("a1").getPiece().getType()).isEqualTo(PieceType.ROOK);
        assertThat(board.getSquareByName("b1").getPiece().getType()).isEqualTo(PieceType.KNIGHT);
        assertThat(board.getSquareByName("c1").getPiece().getType()).isEqualTo(PieceType.BISHOP);
        assertThat(board.getSquareByName("d1").getPiece().getType()).isEqualTo(PieceType.QUEEN);
        assertThat(board.getSquareByName("e1").getPiece().getType()).isEqualTo(PieceType.KING);
        assertThat(board.getSquareByName("f1").getPiece().getType()).isEqualTo(PieceType.BISHOP);
        assertThat(board.getSquareByName("g1").getPiece().getType()).isEqualTo(PieceType.KNIGHT);
        assertThat(board.getSquareByName("h1").getPiece().getType()).isEqualTo(PieceType.ROOK);

        for (char i = 'a'; i <= 'h'; i++) {
            assertThat(board.getSquareByName(i + "2").getPiece().getType()).isEqualTo(PieceType.PAWN);
        }

        for (char i = 'a'; i <= 'h'; i++) {
            assertThat(board.getSquareByName(i + "7").getPiece().getType()).isEqualTo(PieceType.PAWN);
        }

        assertThat(board.getSquareByName("a8").getPiece().getType()).isEqualTo(PieceType.ROOK);
        assertThat(board.getSquareByName("b8").getPiece().getType()).isEqualTo(PieceType.KNIGHT);
        assertThat(board.getSquareByName("c8").getPiece().getType()).isEqualTo(PieceType.BISHOP);
        assertThat(board.getSquareByName("d8").getPiece().getType()).isEqualTo(PieceType.QUEEN);
        assertThat(board.getSquareByName("e8").getPiece().getType()).isEqualTo(PieceType.KING);
        assertThat(board.getSquareByName("f8").getPiece().getType()).isEqualTo(PieceType.BISHOP);
        assertThat(board.getSquareByName("g8").getPiece().getType()).isEqualTo(PieceType.KNIGHT);
        assertThat(board.getSquareByName("h8").getPiece().getType()).isEqualTo(PieceType.ROOK);
    }

    @Test
    public void shouldPlantPieceForCorrectPlayer() {
        for (char i = 'a'; i <= 'h'; i++) {
            assertThat(board.getSquareByName(i + "1").getPiece().getPlayer().getType()).isEqualTo(PlayerType.WHITE);
        }
        for (char i = 'a'; i <= 'h'; i++) {
            assertThat(board.getSquareByName(i + "2").getPiece().getPlayer().getType()).isEqualTo(PlayerType.WHITE);
        }

        for (char i = 'a'; i <= 'h'; i++) {
            assertThat(board.getSquareByName(i + "7").getPiece().getPlayer().getType()).isEqualTo(PlayerType.BLACK);
        }
        for (char i = 'a'; i <= 'h'; i++) {
            assertThat(board.getSquareByName(i + "8").getPiece().getPlayer().getType()).isEqualTo(PlayerType.BLACK);
        }
    }

    @Test
    public void shouldMiddlePartOfBoardBeEmpty() {
        for (char i = 'a'; i <= 'h'; i++) {
            assertThat(board.getSquareByName(i + "3").getPiece()).isNull();
        }
        for (char i = 'a'; i <= 'h'; i++) {
            assertThat(board.getSquareByName(i + "4").getPiece()).isNull();
        }
        for (char i = 'a'; i <= 'h'; i++) {
            assertThat(board.getSquareByName(i + "5").getPiece()).isNull();
        }
        for (char i = 'a'; i <= 'h'; i++) {
            assertThat(board.getSquareByName(i + "6").getPiece()).isNull();
        }
    }
}
