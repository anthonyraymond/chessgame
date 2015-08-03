package org.araymond.chessgame.model.player;

import org.araymond.chessgame.exceptions.ChessModelIntegrityException;

/**
 * Created by Anthony on 14/07/2015.
 */
public class PlayerSet {
    private final Player whitePlayer;
    private final Player blackPlayer;

    public PlayerSet(Player whitePlayer, Player blackPlayer) {
        if (blackPlayer == null || !blackPlayer.isBlack()) {
            throw new ChessModelIntegrityException("BlackPlayer  was null or type was not BLACK", new IllegalArgumentException());
        }
        if (whitePlayer == null || !whitePlayer.isWhite()) {
            throw new ChessModelIntegrityException("WhitePlayer or player type was null or type was not WHITE", new IllegalArgumentException());
        }

        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }
}
