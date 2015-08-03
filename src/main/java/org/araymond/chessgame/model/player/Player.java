package org.araymond.chessgame.model.player;

import com.google.common.base.Strings;
import org.araymond.chessgame.exceptions.ChessModelIntegrityException;

/**
 * Created by Anthony on 21/06/2015.
 */
public final class Player {

    private PlayerType type;
    private String name;

    public Player(String name, PlayerType playerType) {
        if (Strings.isNullOrEmpty(name) || playerType == null) {
            throw new ChessModelIntegrityException("Cannot build a Player without a name or PlayerType", new IllegalArgumentException());
        }
        this.name = name;
        this.type = playerType;
    }

    /*public PlayerType getType() {
        return this.type;
    }*/

    public String getName() {
        return this.name;
    }

    public boolean isWhite() {
        return this.type == PlayerType.WHITE;
    }

    public boolean isBlack() {
        return this.type == PlayerType.BLACK;
    }

}
