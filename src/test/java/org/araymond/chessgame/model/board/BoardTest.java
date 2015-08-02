package org.araymond.chessgame.model.board;

import org.araymond.chessgame.ChessGameTestUtils;
import org.araymond.chessgame.exceptions.ChessInvalidPositionException;
import org.araymond.chessgame.model.player.PlayerSet;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Anthony on 21/06/2015.
 */
public class BoardTest {
    private static PlayerSet playerSet;

    @BeforeClass
    public static void setUp() {
        playerSet = ChessGameTestUtils.createValidPlayerSet();
    }


    @Test
    public void shouldFindSquareByExistingName() {
        Board board = BoardFactory.createBoardWithPieces(playerSet);

        Square a1 = board.getSquareByName("a1");

        assertThat(a1.getName()).isEqualTo("a1");
    }

    @Test
    public void shouldNotFindSquareByNonExistingName() {
        try {
            Board board = BoardFactory.createBoardWithPieces(playerSet);
            board.getSquareByName("z250");
            fail();
        } catch (ChessInvalidPositionException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }


    @Test
    public void shouldNotFindSquareByNullName() {
        try {
            Board board = BoardFactory.createBoardWithPieces(playerSet);
            board.getSquareByName(null);
            fail();
        } catch (ChessInvalidPositionException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }
}
