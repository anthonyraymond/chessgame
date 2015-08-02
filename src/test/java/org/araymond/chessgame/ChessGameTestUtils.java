package org.araymond.chessgame;

import org.araymond.chessgame.model.player.Player;
import org.araymond.chessgame.model.player.PlayerSet;
import org.araymond.chessgame.model.player.PlayerType;

/**
 * Created by Anthony on 14/07/2015.
 */
public class ChessGameTestUtils {

    public static PlayerSet createValidPlayerSet() {
        return new PlayerSet(new Player("white player", PlayerType.WHITE), new Player("black player", PlayerType.BLACK));
    }


}
