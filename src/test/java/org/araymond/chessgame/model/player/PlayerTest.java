package org.araymond.chessgame.model.player;

import org.araymond.chessgame.exceptions.ChessModelIntegrityException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Anthony on 21/06/2015.
 */
public class PlayerTest {

    @Test
    public void shouldBuildWithNameAntType() {
        Player player = new Player("John", PlayerType.BLACK);
        assertThat(player.getName()).isEqualTo("John");
        assertThat(player.getType()).isEqualTo(PlayerType.BLACK);
    }

    @Test
    public void shouldNotBuildWithoutType() {
        try {
            new Player("John", null);
            fail();
        } catch (ChessModelIntegrityException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldNotBuildWithEmptyName() {
        try {
            new Player("", PlayerType.BLACK);
            fail();
        } catch (ChessModelIntegrityException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldNotBuildWithoutName() {
        try {
            new Player(null, PlayerType.BLACK);
            fail();
        } catch (ChessModelIntegrityException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }


}
