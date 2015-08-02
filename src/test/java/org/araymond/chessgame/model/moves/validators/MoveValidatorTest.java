package org.araymond.chessgame.model.moves.validators;

import org.araymond.chessgame.exceptions.ChessMoveException;
import org.araymond.chessgame.model.board.Board;
import org.araymond.chessgame.model.board.SpecialBoardBuilder;
import org.araymond.chessgame.model.moves.Direction;
import org.araymond.chessgame.model.pieces.Piece;
import org.araymond.chessgame.model.pieces.PieceType;
import org.araymond.chessgame.model.player.Player;
import org.araymond.chessgame.model.player.PlayerType;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Anthony on 12/07/2015.
 */
public class MoveValidatorTest {

    private DefaultMoveValidator moveValidator = new DefaultMoveValidator();
    private Player white;
    private Player black;

    @Before
    public void setUp() {
        white = new Player("Anthony", PlayerType.WHITE);
        black = new Player("Opponent", PlayerType.BLACK);
    }

    @Test
    public void shouldFailIsMoveValidWithNullPiece() {
        try {
            Direction[] path = {Direction.NORTH};
            moveValidator.isMoveValid(null, path);
            fail();
        } catch (ChessMoveException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldFailIsMoveValidWithNullDirections() {
        Board board = new SpecialBoardBuilder().withPiece("a1", PieceType.PAWN, white).build();
        try {
            Piece piece = board.getSquareByName("a1").getPiece();
            moveValidator.isMoveValid(piece, null);
            fail();
        } catch (ChessMoveException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldFailIsMoveValidWithEmptyDirections() {
        Board board = new SpecialBoardBuilder().withPiece("a1", PieceType.PAWN, white).build();
        Direction[] path = {};
        try {
            Piece piece = board.getSquareByName("a1").getPiece();
            moveValidator.isMoveValid(piece, path);
            fail();
        } catch (ChessMoveException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldFailIsMoveValidWithDestOccupied() {
        Board board = new SpecialBoardBuilder().withPiece("a1", PieceType.PAWN, white).withPiece("a2", PieceType.PAWN, black).build();
        Direction[] path = {Direction.NORTH};
        Piece piece = board.getSquareByName("a1").getPiece();
        try {
            moveValidator.isMoveValid(piece, path);
            fail();
        } catch (ChessMoveException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldFailIsMoveValidWithDestOutBound() {
        Board board = new SpecialBoardBuilder().withPiece("a8", PieceType.PAWN, white).build();
        Piece piece = board.getSquareByName("a8").getPiece();
        Direction[] path = {Direction.NORTH};
        assertThat(moveValidator.isMoveValid(piece, path)).isFalse();
    }


    @Test
    public void shouldFailIsMoveValidWithUnknownPathForMoveValidator() {
        Board board = new SpecialBoardBuilder().withPiece("a1", PieceType.PAWN, white).build();
        Piece piece = board.getSquareByName("a1").getPiece();
        Direction[] path = new Direction[]{Direction.EAST};
        assertThat(moveValidator.isMoveValid(piece, path)).isFalse();
    }


    @Test
    public void shouldNotBeAValidMoveWithAnotherPieceOnTheWayOnTwoStepPath() {
        Board board = new SpecialBoardBuilder().withPiece("a1", PieceType.PAWN, white).withPiece("a2", PieceType.PAWN, black).withPiece("a3", PieceType.PAWN, black).build();
        Piece piece = board.getSquareByName("a1").getPiece();
        Direction[] path = new Direction[]{Direction.NORTH, Direction.NORTH};
        assertThat(moveValidator.isMoveValid(piece, path)).isFalse();
    }

    @Test
    public void shouldNotBeAValidMoveWithAnotherPieceOnTheWayAtFirstWaypoint() {
        Board board = new SpecialBoardBuilder().withPiece("a1", PieceType.PAWN, white).withPiece("a2", PieceType.PAWN, black).withPiece("a4", PieceType.PAWN, black).build();
        Piece piece = board.getSquareByName("a1").getPiece();
        Direction[] path = new Direction[]{Direction.NORTH, Direction.NORTH, Direction.NORTH};
        assertThat(moveValidator.isMoveValid(piece, path)).isFalse();
    }

    @Test
    public void shouldNotBeAValidMoveWithAnotherPieceOnTheWayAtLastWaypoint() {
        Board board = new SpecialBoardBuilder().withPiece("a1", PieceType.PAWN, white).withPiece("a3", PieceType.PAWN, black).withPiece("a4", PieceType.PAWN, black).build();
        Piece piece = board.getSquareByName("a3").getPiece();
        Direction[] path = new Direction[]{Direction.NORTH, Direction.NORTH, Direction.NORTH};
        assertThat(moveValidator.isMoveValid(piece, path)).isFalse();
    }


    @Test
    public void shouldFailIsCaptureValidWithNullPiece() {
        try {
            Direction[] path = {Direction.NORTH};
            moveValidator.isCaptureValid(null, path);
            fail();
        } catch (ChessMoveException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldFailIsCaptureValidWithNullDirections() {
        Board board = new SpecialBoardBuilder().withPiece("a1", PieceType.PAWN, white).build();
        Piece piece = board.getSquareByName("a1").getPiece();
        try {
            moveValidator.isCaptureValid(piece, null);
            fail();
        } catch (ChessMoveException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldFailIsCaptureValidWithEmptyDirections() {
        Board board = new SpecialBoardBuilder().withPiece("a1", PieceType.PAWN, white).build();
        Piece piece = board.getSquareByName("a1").getPiece();
        Direction[] path = {};
        try {
            moveValidator.isCaptureValid(piece, path);
            fail();
        } catch (ChessMoveException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldFailIsCaptureValidWithDestNotOccupied() {
        Board board = new SpecialBoardBuilder().withPiece("a1", PieceType.PAWN, white).build();
        Direction[] path = {Direction.NORTH};
        Piece piece = board.getSquareByName("a1").getPiece();
        try {
            moveValidator.isCaptureValid(piece, path);
            fail();
        } catch (ChessMoveException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldFailIsCaptureValidWithDestOccupiedBySamePlayer() {
        Board board = new SpecialBoardBuilder().withPiece("a1", PieceType.PAWN, white).withPiece("a2", PieceType.PAWN, white).build();
        Direction[] path = {Direction.NORTH};
        Piece piece = board.getSquareByName("a1").getPiece();
        try {
            moveValidator.isCaptureValid(piece, path);
            fail();
        } catch (ChessMoveException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldFailIsCaptureValidWithDestOutBound() {
        Board board = new SpecialBoardBuilder().withPiece("a8", PieceType.PAWN, white).build();
        Piece piece = board.getSquareByName("a8").getPiece();
        Direction[] path = {Direction.NORTH};
        assertThat(moveValidator.isCaptureValid(piece, path)).isFalse();
    }


    @Test
    public void shouldFailIsCaptureValidWithUnknownPathForMoveValidator() {
        Board board = new SpecialBoardBuilder().withPiece("a1", PieceType.PAWN, white).build();
        Piece piece = board.getSquareByName("a1").getPiece();
        Direction[] path = new Direction[]{Direction.EAST};
        assertThat(moveValidator.isCaptureValid(piece, path)).isFalse();
    }


    @Test
    public void shouldNotBeAValidCaptureMoveWithAnotherPieceOnTheWayOnTwoStepPath() {
        Board board = new SpecialBoardBuilder().withPiece("a1", PieceType.PAWN, white).withPiece("a2", PieceType.PAWN, black).withPiece("a3", PieceType.PAWN, black).build();
        Piece piece = board.getSquareByName("a1").getPiece();
        Direction[] path = new Direction[]{Direction.NORTH, Direction.NORTH};
        assertThat(moveValidator.isCaptureValid(piece, path)).isFalse();
    }

    @Test
    public void shouldNotBeAValidCaptureMoveWithAnotherPieceOnTheWayAtFirstWaypoint() {
        Board board = new SpecialBoardBuilder().withPiece("a1", PieceType.PAWN, white).withPiece("a2", PieceType.PAWN, black).withPiece("a4", PieceType.PAWN, black).build();
        Piece piece = board.getSquareByName("a1").getPiece();
        Direction[] path = new Direction[]{Direction.NORTH, Direction.NORTH, Direction.NORTH};
        assertThat(moveValidator.isCaptureValid(piece, path)).isFalse();
    }

    @Test
    public void shouldNotBeAValidCaptureMoveWithAnotherPieceOnTheWayAtLastWaypoint() {
        Board board = new SpecialBoardBuilder().withPiece("a1", PieceType.PAWN, white).withPiece("a3", PieceType.PAWN, black).withPiece("a4", PieceType.PAWN, black).build();
        Piece piece = board.getSquareByName("a3").getPiece();
        Direction[] path = new Direction[]{Direction.NORTH, Direction.NORTH, Direction.NORTH};
        assertThat(moveValidator.isCaptureValid(piece, path)).isFalse();
    }


    private class DefaultMoveValidator extends MoveValidator {

        private Direction[][] allowedMove = {
                new Direction[]{Direction.NORTH},
                new Direction[]{Direction.NORTH, Direction.NORTH},
                new Direction[]{Direction.NORTH, Direction.NORTH, Direction.NORTH},
                new Direction[]{Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.NORTH}
        };

        @Override
        protected Direction[][] getAllowedDisplacementForPiece(Player player) {
            return allowedMove;
        }
    }
}
