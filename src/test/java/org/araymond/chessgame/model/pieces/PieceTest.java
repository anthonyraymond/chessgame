package org.araymond.chessgame.model.pieces;

import org.araymond.chessgame.exceptions.ChessMoveException;
import org.araymond.chessgame.model.board.Board;
import org.araymond.chessgame.model.board.SpecialBoardBuilder;
import org.araymond.chessgame.model.board.Square;
import org.araymond.chessgame.model.player.Player;
import org.araymond.chessgame.model.player.PlayerType;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Anthony on 22/06/2015.
 */
public class PieceTest {
    private Player white;
    private Player black;

    @Before
    public void setUp() {
        white = new Player("Anthony", PlayerType.WHITE);
        black = new Player("Opponent", PlayerType.BLACK);
    }

    @Test
    public void shouldNotMoveWithNullDest() {
        String strOrigin = "a2";
        Board board = new SpecialBoardBuilder().withPiece(strOrigin, PieceType.PAWN, white).build();

        Square origin = board.getSquareByName(strOrigin);
        Piece piece = origin.getPiece();
        try {
            piece.moveTo(null);
            fail();
        } catch (ChessMoveException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldNotMoveWithDestEqualsToOrigin() {
        String strOrigin = "a2";
        Board board = new SpecialBoardBuilder().withPiece(strOrigin, PieceType.PAWN, white).build();

        Square origin = board.getSquareByName(strOrigin);
        Piece piece = origin.getPiece();
        try {
            piece.moveTo(origin);
            fail();
        } catch (ChessMoveException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldNotMoveWithPieceToNonEmptySquare() {
        String strOrigin = "a2";
        String strDest = "a3";
        Board board = new SpecialBoardBuilder().withPiece(strOrigin, PieceType.PAWN, white).withPiece(strDest, PieceType.PAWN, black).build();

        Square origin = board.getSquareByName(strOrigin);
        Square dest = board.getSquareByName(strDest);
        Piece piece = origin.getPiece();

        try {
            piece.moveTo(dest);
            fail();
        } catch (ChessMoveException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldNotMoveWithPieceBelongToSamePlayerAtDest() {
        String strOrigin = "a2";
        String strDest = "a3";
        Board board = new SpecialBoardBuilder().withPiece(strOrigin, PieceType.PAWN, white).withPiece(strDest, PieceType.PAWN, white).build();

        Square origin = board.getSquareByName(strOrigin);
        Square dest = board.getSquareByName(strDest);
        Piece piece = origin.getPiece();

        try {
            piece.moveTo(dest);
            fail();
        } catch (ChessMoveException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldMovePawn() {
        String strOrigin = "a2";
        String strDest = "a3";
        Board board = new SpecialBoardBuilder().withPiece(strOrigin, PieceType.PAWN, white).build();

        Square origin = board.getSquareByName(strOrigin);
        Square dest = board.getSquareByName(strDest);
        Piece piece = origin.getPiece();

        piece.moveTo(dest);

        assertThat(origin.getPiece()).isNull();
        assertThat(piece.getSquare()).isEqualTo(dest);
        assertThat(dest.getPiece()).isEqualTo(piece);
    }


    @Test
    public void shouldNotCaptureAPieceBelongsToSamePlayer() {
        String strOrigin = "a2";
        String strDest = "b3";
        Board board = new SpecialBoardBuilder().withPiece(strOrigin, PieceType.PAWN, white).withPiece(strDest, PieceType.PAWN, white).build();

        Square origin = board.getSquareByName(strOrigin);
        Square dest = board.getSquareByName(strDest);

        try {
            origin.getPiece().captureTo(dest);
            fail();
        } catch (ChessMoveException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldNotCaptureWithDestEqualsToOrigin() {
        String strOrigin = "a2";
        Board board = new SpecialBoardBuilder().withPiece(strOrigin, PieceType.PAWN, white).build();

        Square origin = board.getSquareByName(strOrigin);
        Piece piece = origin.getPiece();
        try {
            piece.captureTo(origin);
            fail();
        } catch (ChessMoveException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldNotCaptureAEmptySquare() {
        String strOrigin = "a2";
        String strDest = "b3";
        Board board = new SpecialBoardBuilder().withPiece(strOrigin, PieceType.PAWN, white).build();

        Square origin = board.getSquareByName(strOrigin);
        Square dest = board.getSquareByName(strDest);

        try {
            origin.getPiece().captureTo(dest);
            fail();
        } catch (ChessMoveException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldNotCaptureWithNullDest() {
        String strOrigin = "a2";
        Board board = new SpecialBoardBuilder().withPiece(strOrigin, PieceType.PAWN, white).build();

        Square origin = board.getSquareByName(strOrigin);

        try {
            origin.getPiece().captureTo(null);
            fail();
        } catch (ChessMoveException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldCapturePawn() {
        String strOrigin = "a2";
        String strDest = "b3";
        Board board = new SpecialBoardBuilder().withPiece(strOrigin, PieceType.PAWN, white).withPiece(strDest, PieceType.PAWN, black).build();

        Square origin = board.getSquareByName(strOrigin);
        Square dest = board.getSquareByName(strDest);
        Piece piece = origin.getPiece();

        piece.captureTo(dest);

        assertThat(origin.getPiece()).isNull();
        assertThat(piece.getSquare()).isEqualTo(dest);
        assertThat(dest.getPiece()).isEqualTo(piece);
    }
}
