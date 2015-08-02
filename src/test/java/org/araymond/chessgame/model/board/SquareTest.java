package org.araymond.chessgame.model.board;

import org.araymond.chessgame.exceptions.ChessInvalidPositionException;
import org.araymond.chessgame.exceptions.ChessModelIntegrityException;
import org.araymond.chessgame.model.moves.Direction;
import org.araymond.chessgame.model.pieces.PieceFactory;
import org.araymond.chessgame.model.player.Player;
import org.araymond.chessgame.model.player.PlayerType;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Anthony on 21/06/2015.
 */
public class SquareTest {
    private Player playerWhite;
    private Player playerBlack;

    @Before
    public void setUp() {
        playerWhite = new Player("Anthony", PlayerType.WHITE);
        playerBlack = new Player("Opponent", PlayerType.BLACK);
    }

    @Test
    public void shouldBuildWithValidName() {
        new Square("a1");
    }

    @Test
    public void shouldNotBuildWithEmptyName() {
        try {
            new Square("");
            fail();
        } catch (ChessModelIntegrityException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldNotBuildWithInvalidName() {
        try {
            new Square("a22");
            fail();
        } catch (ChessModelIntegrityException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldNotBuildWithNullName() {
        try {
            new Square(null);
            fail();
        } catch (ChessModelIntegrityException e) {
            assertThat(e.getMessage()).isNotNull();
        }
    }

    @Test
    public void shouldBeEmptySquareAfterBeingCreatedSquareEmpty() {
        assertThat(new Square("a1").isEmpty()).isTrue();
    }

    @Test
    public void shouldNotBeEmptyAfterPlantingAPiece() {
        Square square;

        square = new Square("a1");
        PieceFactory.putKing(playerWhite, square);

        assertThat(square.isEmpty()).isFalse();
    }

    @Test
    public void shouldBeEqualsByRef() {
        Square square;

        square = new Square("a1");

        assertThat(square).isEqualTo(square);
    }

    @Test
    public void shouldBeEqualsByName() {
        Square square;
        Square square2;

        square = new Square("a1");
        square2 = new Square("a1");

        assertThat(square).isEqualTo(square2);
    }

    @Test
    public void shouldNotBeEqualsWithDifferentNames() {
        Square square;
        Square square2;

        square = new Square("a1");
        square2 = new Square("b1");

        assertThat(square).isNotEqualTo(square2);
    }

    @Test
    public void shouldGetBackOnFirstSquareWhenCrawlingCircle() {
        Square square1 = new Square("a1");
        Square square2 = new Square("a2");
        Square square3 = new Square("b2");
        Square square4 = new Square("b1");

        square1.setNeighbour(Direction.NORTH, square2);
        square2.setNeighbour(Direction.EAST, square3);
        square3.setNeighbour(Direction.SOUTH, square4);
        square4.setNeighbour(Direction.WEST, square1);

        assertThat(square1.getNeighbour(Direction.NORTH).getNeighbour(Direction.EAST).getNeighbour(Direction.SOUTH).getNeighbour(Direction.WEST)).isEqualTo(square1);
    }

    @Test
    public void shouldBeOccupiedBySamePlayer() {
        Square square = new Square("a1");
        Square square2 = new Square("a2");
        PieceFactory.putKing(playerWhite, square);
        PieceFactory.putQueen(playerWhite, square2);

        assertThat(square.isOccupiedBySamePlayer(square2)).isTrue();
    }

    @Test
    public void shouldNotBeOccupiedBySamePlayer() {
        Square square = new Square("a1");
        Square square2 = new Square("a2");
        PieceFactory.putKing(playerWhite, square);
        PieceFactory.putQueen(playerBlack, square2);

        assertThat(square.isOccupiedBySamePlayer(square2)).isFalse();
    }

    @Test
    public void shouldFailWhenSettingSquareNeighbourWithNonHandledDirection() {
        try {
            Square square = new Square("a1");
            Square square2 = new Square("a2");
            square.setNeighbour(Direction.NORTHEAST, square2);
            fail();
        } catch (ChessInvalidPositionException e) {
            assertThat(e.getMessage()).isNotNull();
        }


    }

}