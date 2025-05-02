package walking.game;

import walking.game.player.Player;
import walking.game.player.MadlyRotatingBuccaneer;

public class WalkingBoardWithPlayers extends WalkingBoard
{
    private Player[] players;
    private int round;
    public static final int SCORE_EACH_STEP = 13;

    public WalkingBoardWithPlayers(int[][] board, int playerCount)
    {
        super(board);
        initPlayers(playerCount);
    }

    public WalkingBoardWithPlayers(int size, int playerCount)
    {
        super(size);
        initPlayers(playerCount);
    }

    private void initPlayers(int playerCount)
    {
        if (playerCount < 2) {
            throw new IllegalArgumentException("Error");
        }
        players = new Player[playerCount];
        players[0] = new MadlyRotatingBuccaneer(); // first player
        for (int i = 1; i < playerCount; i++) {
            players[i] = new Player(); // others
        }
    }

    public int[] walk(int... stepCounts)
    {
        int[] scores = new int[players.length];
        for (int i = 0; i < stepCounts.length; i++) {
            players[i].turn();
            int steps = Math.min(stepCounts[i], SCORE_EACH_STEP); // find the lowest one
            players[i].addToScore(steps);
            scores[i] = players[i].getScore();
        }
        
        round++;
        return scores;
    }
}