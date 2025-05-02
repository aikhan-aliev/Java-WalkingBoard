package walking.game;

import static check.CheckThat.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import check.*;

import walking.game.WalkingBoard;
import walking.game.util.Direction;

public class WalkingBoardTest {

    @ParameterizedTest
    @CsvSource({
            "5",
            "7",
            "10"
    })
    @DisableIfHasBadStructure
    public void testSimpleInit(int size) {
        WalkingBoard board = new WalkingBoard(size);
        int[][] tiles = board.getTiles();

        assertNotNull(tiles);
        assertEquals(size, tiles.length);

        for (int i = 0; i < size; i++) {
            assertNotNull(tiles[i]);
            assertEquals(size, tiles[i].length);

            for (int j = 0; j < size; j++) {
                assertEquals(WalkingBoard.BASE_TILE_SCORE, tiles[i][j]);
            }
        }

        assertEquals(WalkingBoard.BASE_TILE_SCORE, board.getTile(0, 0));
        assertEquals(WalkingBoard.BASE_TILE_SCORE, board.getTile(size - 1, size - 1));

    }

    @ParameterizedTest
    @CsvSource({
            "0, 0, 4",
            "2, 3, 8",
            "4, 4, 9"
    })
    public void testCustomInit(int x, int y, int expected) {
        int[][] customTiles = new int[6][6];
        customTiles[x][y] = expected;
        WalkingBoard board = new WalkingBoard(customTiles);
        int[][] tiles = board.getTiles();

        assertEquals(expected, board.getTile(x, y));

        tiles[x][y] = 0;
        assertEquals(expected, board.getTile(x, y));
    }
    
    @Test
    public void testMoves()
    {
        WalkingBoard board = new WalkingBoard(8);
        assertEquals(WalkingBoard.BASE_TILE_SCORE, board.getTile(0, 0));

        board.moveAndSet(Direction.RIGHT, 12);
        assertEquals(12, board.getTile(1, 0));
        board.moveAndSet(Direction.DOWN, 22);
        assertEquals(22, board.getTile(1, 1));
        board.moveAndSet(Direction.LEFT, 32);
        assertEquals(32, board.getTile(0, 1));
        board.moveAndSet(Direction.UP, 42);
        assertEquals(42, board.getTile(0, 0));
        board.moveAndSet(Direction.RIGHT, 52);
        assertEquals(52, board.getTile(1, 0));

        //check for exception
        assertThrows(IllegalArgumentException.class, () -> {
            board.moveAndSet(Direction.UP, -1000);
        }, "Invalid position should throw an IllegalArgumentException.");
        ;

        assertArrayEquals(new int[]{1, 0}, board.getPosition());
    }
}