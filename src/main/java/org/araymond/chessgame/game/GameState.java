package org.araymond.chessgame.game;

import org.araymond.chessgame.exceptions.ChessGameIllegalStateException;
import org.araymond.chessgame.exceptions.ChessModelIntegrityException;
import org.araymond.chessgame.model.player.Player;
import org.araymond.chessgame.model.player.PlayerSet;

/**
 * Created by Anthony on 14/07/2015.
 */
public class GameState {

    private PlayerTurn playerTurn;
    private GameStateEnum state;

    GameState(PlayerSet playerSet, int turnTimeOut) {
        if (playerSet == null) {
            throw new ChessModelIntegrityException("Cannot build a GameState without a PlayerSet", new IllegalArgumentException());
        }

        this.playerTurn = new PlayerTurn(playerSet, turnTimeOut);
        this.state = GameStateEnum.NOT_STARTED;
    }

    void start() {
        if (this.state != GameStateEnum.NOT_STARTED) {
            throw new ChessGameIllegalStateException("Tried to start a game which was started already or over.");
        }
        this.state = GameStateEnum.IN_PROGRESS;
        this.playerTurn.start();
    }

    void end() {
        if (state == GameStateEnum.OVER) {
            throw new ChessGameIllegalStateException("Tied to stop a game which was already stopped");
        } else if (state == GameStateEnum.NOT_STARTED) {
            throw new ChessGameIllegalStateException("Tied to stop a game which was never started");
        }
        this.state = GameStateEnum.OVER;
        this.playerTurn.stop();
    }

    public boolean isInProgress() {
        return this.state == GameStateEnum.IN_PROGRESS;
    }

    public Player getCurrentPlayerTurn() {
        return this.playerTurn.getCurrentPlayer();
    }

    void nextTurn() {
        this.playerTurn.nextTurn();
    }

    private enum GameStateEnum {
        NOT_STARTED, IN_PROGRESS, OVER
    }
}
