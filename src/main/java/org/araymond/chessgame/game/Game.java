package org.araymond.chessgame.game;

import com.google.common.base.Strings;
import org.araymond.chessgame.exceptions.ChessGameIllegalStateException;
import org.araymond.chessgame.exceptions.ChessModelIntegrityException;
import org.araymond.chessgame.exceptions.ChessMoveException;
import org.araymond.chessgame.exceptions.ChessPlayerTurnException;
import org.araymond.chessgame.model.board.Board;
import org.araymond.chessgame.model.board.BoardFactory;
import org.araymond.chessgame.model.board.Square;
import org.araymond.chessgame.model.player.Player;
import org.araymond.chessgame.model.player.PlayerSet;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Anthony on 12/07/2015.
 */
public class Game {
    private final PlayerSet playerSet;
    private final Board board;
    private final Lock lock;
    private final GameState gameState;

    public Game(PlayerSet playerSet) {
        if (playerSet == null) {
            throw new ChessModelIntegrityException("Cannot build a Game without a playerSet", new IllegalArgumentException());
        }

        this.playerSet = playerSet;
        this.board = BoardFactory.createBoardWithPieces(playerSet);
        this.gameState = new GameState(playerSet, 30000);
        this.lock = new ReentrantLock();
    }

    public Board getBoard() {
        return this.board;
    }


    public void startGame() {
        this.gameState.start();
    }

    public void endGame() {
        this.gameState.end();
    }

    public void movePiece(Player player, String originName, String destName) {
        lock.lock();
        try {
            if (player == null || Strings.isNullOrEmpty(originName) || Strings.isNullOrEmpty(destName)) {
                throw new ChessMoveException("Cannot Move a piece Without a player or an origin or destination square name.", new IllegalArgumentException());
            }
            if (!this.gameState.isInProgress()) {
                throw new ChessGameIllegalStateException("Tried to move a piece with not started or ended game.");
            }
            if (!this.getCurrentPlayerTurn().equals(player)) {
                throw new ChessPlayerTurnException("Player tried to move a piece when not his turn to play");
            }


            Square origin = board.getSquareByName(originName);
            Square dest = board.getSquareByName(destName);

            if (!origin.getPiece().belongsTo(player)) {
                throw new ChessMoveException("Cannot move a piece that does not belong to the player.", new IllegalArgumentException());
            }

            if (origin.isEmpty()) {
                throw new ChessMoveException("Cannot move from an empty Square", new IllegalArgumentException());
            }

            if (dest.isEmpty()) {
                origin.getPiece().moveTo(dest);
            } else {
                origin.getPiece().captureTo(dest);
            }

            this.triggerNextTurn();
        } finally {
            lock.unlock();
        }
    }

    private void triggerNextTurn() {
        this.gameState.nextTurn();
    }

    public Player getCurrentPlayerTurn() {
        return this.gameState.getCurrentPlayerTurn();
    }

}
