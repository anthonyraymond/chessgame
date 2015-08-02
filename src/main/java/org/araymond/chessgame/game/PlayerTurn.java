package org.araymond.chessgame.game;

import org.araymond.chessgame.exceptions.ChessModelIntegrityException;
import org.araymond.chessgame.game.countdown.CountDown;
import org.araymond.chessgame.game.countdown.CountDownEndAction;
import org.araymond.chessgame.model.player.Player;
import org.araymond.chessgame.model.player.PlayerSet;

/**
 * Created by Anthony on 12/07/2015.
 */
class PlayerTurn {
    private final PlayerSet playerSet;
    private Player currentPlayer;
    private CountDown countDown;

    public PlayerTurn(PlayerSet playerSet, int turnTimeout) {
        if (playerSet == null) {
            throw new ChessModelIntegrityException("Cannot build a GameState without a PlayerSet", new IllegalArgumentException());
        }

        this.playerSet = playerSet;

        this.countDown = new CountDown(new ChangePlayerTurnOnCountDownEnds(), turnTimeout);
        this.currentPlayer = playerSet.getWhitePlayer();
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    void nextTurn() {
        if (this.currentPlayer == playerSet.getWhitePlayer()) {
            this.currentPlayer = playerSet.getBlackPlayer();
        } else {
            this.currentPlayer = playerSet.getWhitePlayer();
        }
        countDown.restart();
    }

    void start() {
        this.countDown.restart();
    }

    void stop() {
        this.countDown.end();
    }

    private class ChangePlayerTurnOnCountDownEnds implements CountDownEndAction {

        public ChangePlayerTurnOnCountDownEnds() {
        }

        @Override
        public void onCountDownEnds() {
            nextTurn();
        }
    }
}
