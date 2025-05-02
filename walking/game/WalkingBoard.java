package walking.game;


import walking.game.util.Direction;

public class WalkingBoard
{
    private int[][] tiles;
    private int x;
    private int y;
    public final static int BASE_TILE_SCORE = 3;

    //Setting the values
    public WalkingBoard(int size)
    {
        this.tiles = new int[size][size];
        this.x = 0;
        this.y = 0;

        for (int i = 0; i < size; ++i)
        {
            for (int j = 0; j < size; ++j)
            {
                tiles[i][j] = BASE_TILE_SCORE;
            }
        }

    }

    public WalkingBoard(int[][] tiles)
    {
        this.tiles = new int[tiles.length][];
        for (int i = 0; i < tiles.length; i++)
        {
            this.tiles[i] = new int[tiles[i].length];
            for (int j = 0; j < tiles[i].length; j++)
            {
                this.tiles[i][j] = tiles[i][j] < BASE_TILE_SCORE ? BASE_TILE_SCORE : tiles[i][j];
            }
        }
        this.x = 0;
        this.y = 0;
    }

    public int[][] getTiles()
    {
        int[][] copy = new int[tiles.length][];
        for (int i = 0; i < tiles.length; i++)
        {
            copy[i] = new int[tiles[i].length];
            for (int j = 0; j < tiles[i].length; j++)
            {
                copy[i][j] = tiles[i][j];  // Copy each element
            }
        }
        return copy;
    }

    public int[] getPosition()
    {
        int[] arr = {x, y};
        return arr;
    }

    public boolean isValidPosition(int x, int y)
    {
        return x >= 0 && x < tiles.length && y >= 0 && y < tiles[x].length;
    }

    public int getTile(int x, int y)
    {
        if (!isValidPosition(x, y))
        {
            throw new IllegalArgumentException("Error");
        }
        return tiles[x][y];
    }

    public static int getYStep(Direction direction)
    {
        switch (direction)
        {
            case UP: return -1;
            case DOWN: return 1;
            default: return 0;
        }
    }

    public static int getXStep(Direction direction)
    {
        switch (direction)
        {
            case RIGHT: return 1;
            case LEFT: return -1;
            default: return 0;
        }
    }

    public int moveAndSet(Direction direction, int value)
    {
        int newX = x + getXStep(direction);
        int newY = y + getYStep(direction);

        //check for tiles boarders
        if (newX < 0 || newX >= tiles.length || newY < 0 || newY >= tiles.length)
        {
            throw new IllegalArgumentException("error");
        }
        x = newX;
        y = newY;
        int oldValue = tiles[x][y];
        tiles[newX][newY] = value;

        return oldValue;
    }

}