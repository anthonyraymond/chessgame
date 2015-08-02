package org.araymond.chessgame.model.player;

/**
 * Created by Anthony on 21/06/2015.
 */
public enum PlayerType {
    WHITE("White"), BLACK("Black"), SPECTATOR("Spectator");

    private final String value;

    PlayerType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
