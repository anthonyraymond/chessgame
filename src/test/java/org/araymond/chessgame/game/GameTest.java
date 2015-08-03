package org.araymond.chessgame.game;

import org.araymond.chessgame.ChessGameTestUtils;
import org.araymond.chessgame.exceptions.*;
import org.araymond.chessgame.model.board.Square;
import org.araymond.chessgame.model.pieces.Piece;
import org.araymond.chessgame.model.pieces.PieceType;
import org.araymond.chessgame.model.player.PlayerSet;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Anthony on 12/07/2015.
 */
public class GameTest {

    private static PlayerSet playerSet;

    @BeforeClass
    public static void setUp() {
        playerSet = ChessGameTestUtils.createValidPlayerSet();
    }


    @Test
    public void shouldStartByWhiteTurn() {
        Game game = new Game(playerSet);
        game.startGame();

        assertThat(game.getCurrentPlayerTurn()).isEqualTo(playerSet.getWhitePlayer());
        game.endGame();
    }

    @Test
    public void shouldNotMoveWithNullPlayer() {
        Game game = new Game(playerSet);
        game.startGame();

        try {
            game.movePiece(null, "a2", "a3");
            fail();
        } catch (ChessMoveException e) {
            assertThat(e.getMessage()).isNotNull();
        } finally {
            game.endGame();
        }
    }

    @Test
    public void shouldNotMovePiecePlayerBelongsToAnotherPlayer() {
        Game game = new Game(playerSet);
        game.startGame();

        game.movePiece(playerSet.getWhitePlayer(), "b2", "b4");
        game.movePiece(playerSet.getBlackPlayer(), "b7", "b5");
        try {
            game.movePiece(playerSet.getWhitePlayer(), "b8", "c6");
            fail();
        } catch (ChessMoveException e) {
            assertThat(e.getMessage()).isNotNull();
            assertThat(e.getMessage().contains("does not belong to"));
        } finally {
            game.endGame();
        }
    }

    @Test
    public void shouldNotMoveWithNotStartedGame() {
        Game game = new Game(playerSet);

        try {
            game.movePiece(playerSet.getWhitePlayer(), "a2", "a3");
            fail();
        } catch (ChessGameIllegalStateException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldNotMoveWithNonExistingOrigin() {
        Game game = new Game(playerSet);
        game.startGame();

        try {
            game.movePiece(playerSet.getWhitePlayer(), "z2", "a3");
            fail();
        } catch (ChessInvalidPositionException e) {
            assertThat(e.getMessage()).isNotNull();
        } finally {
            game.endGame();
        }
    }

    @Test
    public void shouldNotMoveWhenNotPlayerTurn() {
        Game game = new Game(playerSet);
        game.startGame();

        try {
            game.movePiece(playerSet.getBlackPlayer(), "a2", "a3");
            fail();
        } catch (ChessPlayerTurnException e) {
            assertThat(e.getMessage()).isNotNull();
        } finally {
            game.endGame();
        }
    }

    @Test
    public void shouldNotMoveWithNonExistingDest() {
        Game game = new Game(playerSet);
        game.startGame();

        try {
            game.movePiece(playerSet.getWhitePlayer(), "a2", "q3");
            fail();
        } catch (ChessInvalidPositionException e) {
            assertThat(e.getMessage()).isNotNull();
        } finally {
            game.endGame();
        }
    }

    @Test
    public void shouldMovePiece() {
        Game game = new Game(playerSet);
        game.startGame();
        Piece pieceToMove = game.getBoard().getSquareByName("a2").getPiece();

        try {
            assertThat(game.getCurrentPlayerTurn()).isEqualTo(playerSet.getWhitePlayer());
            game.movePiece(playerSet.getWhitePlayer(), "a2", "a3");
            assertThat(game.getCurrentPlayerTurn()).isEqualTo(playerSet.getBlackPlayer());

            assertThat(game.getBoard().getSquareByName("a2").isEmpty()).isTrue();
            assertThat(game.getBoard().getSquareByName("a3").getPiece()).isEqualTo(pieceToMove);
        } finally {
            game.endGame();
        }
    }

    @Test
    public void shouldSwitchPlayerAfterAMove() {
        Game game = new Game(playerSet);
        game.startGame();

        try {
            assertThat(game.getCurrentPlayerTurn()).isEqualTo(playerSet.getWhitePlayer());
            game.movePiece(playerSet.getWhitePlayer(), "a2", "a3");
            assertThat(game.getCurrentPlayerTurn()).isEqualTo(playerSet.getBlackPlayer());
        } finally {
            game.endGame();
        }
    }


    @Test(timeout = 500)
    public void shouldNotMoveSamePieceTwiceOnConcurrentAccess() {
        Game game = new Game(playerSet);
        game.startGame();

        ExecutorService executors = Executors.newFixedThreadPool(50);
        Collection<Future> futures = new ArrayList<>();
        List<Exception> exception = new ArrayList<>();
        Piece pieceToMove = game.getBoard().getSquareByName("a2").getPiece();

        for (int i = 0; i < 50; ++i) {
            futures.add(executors.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        game.movePiece(playerSet.getWhitePlayer(), "a2", "a3");
                    } catch (ChessException e) {
                        exception.add(e);
                    }
                }
            }));
        }

        try {
            for (Future future : futures) {
                future.get();
            }
            //should have raised 49 exception, since only one thread should have move the piece
            assertThat(exception).hasSize(49);
            assertThat(game.getCurrentPlayerTurn()).isEqualTo(playerSet.getBlackPlayer());
            assertThat(game.getBoard().getSquareByName("a3").getPiece()).isEqualTo(pieceToMove);
            assertThat(game.getBoard().getSquareByName("a2").isEmpty()).isTrue();
        } catch (InterruptedException | ExecutionException e) {
            fail();
        } finally {
            game.endGame();
        }
    }


    @Test
    public void shouldCapturePiece() {
        Game game = new Game(playerSet);
        game.startGame();

        try {
            Piece pieceToMove = game.getBoard().getSquareByName("b8").getPiece();

            game.movePiece(playerSet.getWhitePlayer(), "g1", "f3");
            game.movePiece(playerSet.getBlackPlayer(), "b8", "c6");
            game.movePiece(playerSet.getWhitePlayer(), "f3", "e5");

            game.movePiece(playerSet.getBlackPlayer(), "c6", "e5");

            assertThat(game.getBoard().getSquareByName("c6").isEmpty()).isTrue();
            assertThat(game.getBoard().getSquareByName("e5").getPiece()).isEqualTo(pieceToMove);
        } finally {
            game.endGame();
        }
    }

    @Test
    public void shouldSwitchPlayerAfterACapture() {
        Game game = new Game(playerSet);
        game.startGame();

        try {
            game.movePiece(playerSet.getWhitePlayer(), "g1", "f3");
            game.movePiece(playerSet.getBlackPlayer(), "b8", "c6");
            game.movePiece(playerSet.getWhitePlayer(), "f3", "e5");

            game.movePiece(playerSet.getBlackPlayer(), "c6", "e5");

            assertThat(game.getCurrentPlayerTurn()).isEqualTo(playerSet.getWhitePlayer());
        } finally {
            game.endGame();
        }
    }

    @Test(timeout = 500)
    public void shouldNotCaptureSamePieceTwiceOnConcurrentAccess() {
        Game game = new Game(playerSet);
        game.startGame();

        ExecutorService executors = Executors.newFixedThreadPool(50);
        Collection<Future> futures = new ArrayList<>();
        List<Exception> exception = new ArrayList<>();

        Piece pieceToMove = game.getBoard().getSquareByName("b8").getPiece();

        game.movePiece(playerSet.getWhitePlayer(), "g1", "f3");
        game.movePiece(playerSet.getBlackPlayer(), "b8", "c6");
        game.movePiece(playerSet.getWhitePlayer(), "f3", "e5");

        for (int i = 0; i < 50; ++i) {
            futures.add(executors.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        game.movePiece(playerSet.getBlackPlayer(), "c6", "e5");
                    } catch (ChessException e) {
                        exception.add(e);
                    }
                }
            }));
        }

        try {
            for (Future future : futures) {
                future.get();
            }
            //should have raised 49 exception, since only one thread should have move the piece
            assertThat(exception).hasSize(49);
            assertThat(game.getCurrentPlayerTurn()).isEqualTo(playerSet.getWhitePlayer());
            assertThat(game.getBoard().getSquareByName("e5").getPiece()).isEqualTo(pieceToMove);
            assertThat(game.getBoard().getSquareByName("c6").isEmpty()).isTrue();
        } catch (InterruptedException | ExecutionException e) {
            fail();
        } finally {
            game.endGame();
        }
    }

    @Test
    public void shouldMoveWithBlackPlayerInvertedMoves() {
        Game game = new Game(playerSet);
        game.startGame();

        try {
            game.movePiece(playerSet.getWhitePlayer(), "a2", "a3");
            game.movePiece(playerSet.getBlackPlayer(), "a7", "a6");
        } finally {
            game.endGame();
        }
    }


    @Test
    public void shouldCapturePawn() {
        Game game = new Game(playerSet);
        game.startGame();

        try {
            game.movePiece(playerSet.getWhitePlayer(), "a2", "a3");
            game.movePiece(playerSet.getBlackPlayer(), "b7", "b6");
            game.movePiece(playerSet.getWhitePlayer(), "a3", "a4");
            game.movePiece(playerSet.getBlackPlayer(), "b6", "b5");
            game.movePiece(playerSet.getWhitePlayer(), "a4", "b5");

            Square square = game.getBoard().getSquareByName("b5");
            assertThat(square.getPiece().getType() == PieceType.PAWN);
            assertThat(square.getPiece().getPlayer().equals(playerSet.getWhitePlayer()));
        } finally {
            game.endGame();
        }
    }

}
