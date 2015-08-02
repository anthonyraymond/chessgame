package org.araymond.chessgame.game;

import org.araymond.chessgame.ChessGameTestUtils;
import org.araymond.chessgame.exceptions.ChessGameIllegalStateException;
import org.araymond.chessgame.model.player.PlayerSet;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Anthony on 14/07/2015.
 */
public class GameStateTest {
    private static PlayerSet playerSet;

    @BeforeClass
    public static void setUp() {
        playerSet = ChessGameTestUtils.createValidPlayerSet();
    }


    @Test
    public void shouldNotStopIfNotStarted() {
        GameState gameState = new GameState(playerSet, 500);
        try {
            gameState.end();
            fail();
        } catch (ChessGameIllegalStateException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldNotStopIfStoppedAlready() {
        GameState gameState = new GameState(playerSet, 500);
        try {
            gameState.start();
            gameState.end();
            gameState.end();
            fail();
        } catch (ChessGameIllegalStateException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldStopIfInProgress() {
        GameState gameState = new GameState(playerSet, 500);
        assertThat(gameState.isInProgress()).isFalse();
        gameState.start();
        assertThat(gameState.isInProgress()).isTrue();
        gameState.end();
        assertThat(gameState.isInProgress()).isFalse();
    }

    @Test
    public void shouldNotStartIfStoppedAlready() {
        GameState gameState = new GameState(playerSet, 500);
        try {
            gameState.start();
            gameState.end();
            gameState.start();
            fail();
        } catch (ChessGameIllegalStateException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldNotStartIfInProgressAlready() {
        GameState gameState = new GameState(playerSet, 500);
        try {
            gameState.start();
            gameState.start();
            fail();
        } catch (ChessGameIllegalStateException e) {
            assertThat(e.getMessage()).isNotNull();
        } finally {
            gameState.end();
        }
    }

    @Test
    public void shouldStartIfNotStartedAlready() {
        GameState gameState = new GameState(playerSet, 500);
        assertThat(gameState.isInProgress()).isFalse();
        gameState.start();
        assertThat(gameState.isInProgress()).isTrue();
        gameState.end();
    }
}
