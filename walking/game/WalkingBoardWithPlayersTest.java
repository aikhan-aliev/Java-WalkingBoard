package walking.game;


import static check.CheckThat.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import check.*;
public class WalkingBoardWithPlayersTest{
    @Test
    void walk1() {
        int[][] first = {
            {2, 2, 2, 2},
            {2, 3, 3, 2},
            {2, 3, 3, 2},
            {2, 2, 2, 2}
        };
        WalkingBoardWithPlayers Game1 = new WalkingBoardWithPlayers(first, 4);
        
        int[] steps = {2, 2, 2, 2};  // Each player takes one step, repeat 2 times
        for (int i = 0; i < 3; i++)
        {
            Game1.walk(steps);
        }
        int[] scores = Game1.walk(steps);
        assertEquals(4, scores.length);
        for (int score : scores)
        {
            assertTrue(score >= 6); // at least score should be 6 for each of them
        }
    }
    @Test
    void walk2() {
         int[][] second = {
            {1, 1, 1, 1, 1},
            {1, 2, 2, 2, 1},
            {1, 2, 3, 2, 1},
            {1, 2, 2, 2, 1},
            {1, 1, 1, 1, 1}
        };
        WalkingBoardWithPlayers Game2 = new WalkingBoardWithPlayers(second, 5);
        int[] steps = {3, 3, 3, 3, 3};
        for (int i = 0; i < 3; i++)
        {
            Game2.walk(steps);
        }
        int[] scores = Game2.walk(steps);
        assertEquals(5, scores.length);
        for (int score : scores)
        {
            assertTrue(score >= 9); // at least score should be 9
        }
    }
}