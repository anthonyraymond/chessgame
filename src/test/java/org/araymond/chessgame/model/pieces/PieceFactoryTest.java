package org.araymond.chessgame.model.pieces;

import org.araymond.chessgame.ChessGameTestUtils;
import org.araymond.chessgame.exceptions.ChessModelIntegrityException;
import org.araymond.chessgame.model.board.SpecialBoardBuilder;
import org.araymond.chessgame.model.board.Square;
import org.araymond.chessgame.model.player.Player;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Anthony on 02/08/2015.
 */
public class PieceFactoryTest {
    private static Player playerWhite;
    private Square square;

    @BeforeClass
    public static void setUpStatic() {
        playerWhite = ChessGameTestUtils.createValidPlayerSet().getWhitePlayer();
    }

    @Before
    public void setUp() {
        square = new SpecialBoardBuilder().build().getSquareByName("a1");
    }

    @Test
    public void shouldFailPlantingPawnWithNullPlayer() {
        try {
            PieceFactory.putPawn(null, square);
            fail();
        } catch (ChessModelIntegrityException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldFailPlantingPawnWithNullSquare() {
        try {
            PieceFactory.putPawn(playerWhite, null);
            fail();
        } catch (ChessModelIntegrityException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldCreateValidPawnAndPlantIt() {
        PieceFactory.putPawn(playerWhite, square);
        Piece piece = square.getPiece();
        assertThat(piece).isNotNull();
        assertThat(piece.getType()).isEqualTo(PieceType.PAWN);
        assertThat(piece.getSquare()).isEqualTo(square);
        assertThat(piece.getPlayer()).isEqualTo(playerWhite);
    }


    @Test
    public void shouldFailPlantingKnightWithNullPlayer() {
        try {
            PieceFactory.putKnight(null, square);
            fail();
        } catch (ChessModelIntegrityException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldFailPlantingKnightWithNullSquare() {
        try {
            PieceFactory.putKnight(playerWhite, null);
            fail();
        } catch (ChessModelIntegrityException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldCreateValidKnightAndPlantIt() {
        PieceFactory.putKnight(playerWhite, square);
        Piece piece = square.getPiece();
        assertThat(piece).isNotNull();
        assertThat(piece.getType()).isEqualTo(PieceType.KNIGHT);
        assertThat(piece.getSquare()).isEqualTo(square);
        assertThat(piece.getPlayer()).isEqualTo(playerWhite);
    }


    @Test
    public void shouldFailPlantingBishopWithNullPlayer() {
        try {
            PieceFactory.putBishop(null, square);
            fail();
        } catch (ChessModelIntegrityException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldFailPlantingBishopWithNullSquare() {
        try {
            PieceFactory.putBishop(playerWhite, null);
            fail();
        } catch (ChessModelIntegrityException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldCreateValidBishopAndPlantIt() {
        PieceFactory.putBishop(playerWhite, square);
        Piece piece = square.getPiece();
        assertThat(piece).isNotNull();
        assertThat(piece.getType()).isEqualTo(PieceType.BISHOP);
        assertThat(piece.getSquare()).isEqualTo(square);
        assertThat(piece.getPlayer()).isEqualTo(playerWhite);
    }


    @Test
    public void shouldFailPlantingRookWithNullPlayer() {
        try {
            PieceFactory.putRook(null, square);
            fail();
        } catch (ChessModelIntegrityException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldFailPlantingRookWithNullSquare() {
        try {
            PieceFactory.putRook(playerWhite, null);
            fail();
        } catch (ChessModelIntegrityException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldCreateValidRookAndPlantIt() {
        PieceFactory.putRook(playerWhite, square);
        Piece piece = square.getPiece();
        assertThat(piece).isNotNull();
        assertThat(piece.getType()).isEqualTo(PieceType.ROOK);
        assertThat(piece.getSquare()).isEqualTo(square);
        assertThat(piece.getPlayer()).isEqualTo(playerWhite);
    }


    @Test
    public void shouldFailPlantingKingWithNullPlayer() {
        try {
            PieceFactory.putKing(null, square);
            fail();
        } catch (ChessModelIntegrityException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldFailPlantingKingWithNullSquare() {
        try {
            PieceFactory.putKing(playerWhite, null);
            fail();
        } catch (ChessModelIntegrityException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldCreateValidKingAndPlantIt() {
        PieceFactory.putKing(playerWhite, square);
        Piece piece = square.getPiece();
        assertThat(piece).isNotNull();
        assertThat(piece.getType()).isEqualTo(PieceType.KING);
        assertThat(piece.getSquare()).isEqualTo(square);
        assertThat(piece.getPlayer()).isEqualTo(playerWhite);
    }


    @Test
    public void shouldFailPlantingQueenWithNullPlayer() {
        try {
            PieceFactory.putQueen(null, square);
            fail();
        } catch (ChessModelIntegrityException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldFailPlantingQueenWithNullSquare() {
        try {
            PieceFactory.putQueen(playerWhite, null);
            fail();
        } catch (ChessModelIntegrityException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldCreateValidQueenAndPlantIt() {
        PieceFactory.putQueen(playerWhite, square);
        Piece piece = square.getPiece();
        assertThat(piece).isNotNull();
        assertThat(piece.getType()).isEqualTo(PieceType.QUEEN);
        assertThat(piece.getSquare()).isEqualTo(square);
        assertThat(piece.getPlayer()).isEqualTo(playerWhite);
    }
}
