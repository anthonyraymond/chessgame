package org.araymond.chessgame.model.player;

import org.araymond.chessgame.exceptions.ChessModelIntegrityException;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Anthony on 14/07/2015.
 */
public class PlayerSetTest {
    private static Player playerWhite;
    private static Player playerBlack;

    @BeforeClass
    public static void setUp() {
        playerWhite = new Player("Anthony", PlayerType.WHITE);
        playerBlack = new Player("Opponent", PlayerType.BLACK);
    }

    @Test
    public void shouldNotBuildWithoutWhitePlayer() {
        try {
            new PlayerSet(null, playerBlack);
            fail();
        } catch (ChessModelIntegrityException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldNotBuildWithoutBlackPlayer() {
        try {
            new PlayerSet(playerWhite, null);
            fail();
        } catch (ChessModelIntegrityException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldNotBuildWithPlayerTwoBlackPlayers() {
        try {
            new PlayerSet(playerBlack, playerBlack);
            fail();
        } catch (ChessModelIntegrityException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldNotBuildWithTwoWhitePlayers() {
        try {
            new PlayerSet(playerWhite, playerWhite);
            fail();
        } catch (ChessModelIntegrityException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }
}
