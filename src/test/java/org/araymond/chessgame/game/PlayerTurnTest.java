package org.araymond.chessgame.game;

import org.araymond.chessgame.ChessGameTestUtils;
import org.araymond.chessgame.model.player.PlayerSet;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThat;

/**
 * Created by Anthony on 12/07/2015.
 */
public class PlayerTurnTest {
    private static PlayerSet playerSet;

    @BeforeClass
    public static void setUp() {
        playerSet = ChessGameTestUtils.createValidPlayerSet();
    }


    @Test
    public void shouldStartByWhiteTurn() {
        PlayerTurn playerTurn = new PlayerTurn(playerSet, 30000);
        playerTurn.start();

        assertThat(playerTurn.getCurrentPlayer()).isEqualTo(playerSet.getWhitePlayer());
    }


    @Test
    public void shouldSwitchPlayerAfterEachTurn() {
        PlayerTurn playerTurn = new PlayerTurn(playerSet, 30000);
        playerTurn.start();

        assertThat(playerTurn.getCurrentPlayer()).isEqualTo(playerSet.getWhitePlayer());
        playerTurn.nextTurn();
        assertThat(playerTurn.getCurrentPlayer()).isEqualTo(playerSet.getBlackPlayer());
        playerTurn.nextTurn();
        assertThat(playerTurn.getCurrentPlayer()).isEqualTo(playerSet.getWhitePlayer());
        playerTurn.nextTurn();
        assertThat(playerTurn.getCurrentPlayer()).isEqualTo(playerSet.getBlackPlayer());
    }

    @Test(timeout = 1000)
    public void shouldSwitchPlayerAfterTurnTimeout() throws InterruptedException {
        PlayerTurn playerTurn = new PlayerTurn(playerSet, 10);
        playerTurn.start();

        assertThat(playerTurn.getCurrentPlayer()).isEqualTo(playerSet.getWhitePlayer());
        Thread.sleep(15);
        assertThat(playerTurn.getCurrentPlayer()).isEqualTo(playerSet.getBlackPlayer());
        Thread.sleep(15);
        assertThat(playerTurn.getCurrentPlayer()).isEqualTo(playerSet.getWhitePlayer());
        playerTurn.nextTurn();
        assertThat(playerTurn.getCurrentPlayer()).isEqualTo(playerSet.getBlackPlayer());
        playerTurn.nextTurn();
        assertThat(playerTurn.getCurrentPlayer()).isEqualTo(playerSet.getWhitePlayer());
        playerTurn.nextTurn();
        assertThat(playerTurn.getCurrentPlayer()).isEqualTo(playerSet.getBlackPlayer());
        Thread.sleep(15);
        assertThat(playerTurn.getCurrentPlayer()).isEqualTo(playerSet.getWhitePlayer());
    }


}
