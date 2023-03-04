package maze;

/**
 * The {@code WallBox} class represents a wall in the maze. It extends the {@code MazeBox} class.
 */
public class WallBox extends MazeBox {
   
    /**
     * Constructs a new {@code WallBox} with the specified coordinates and maze.
     *
     * @param x the x-coordinate of the wall box
     * @param y the y-coordinate of the wall box
     * @param maze the maze containing the wall box
     */
    public WallBox(int x, int y, Maze maze) {
        super(x, y, maze);
    }
    public boolean isWallBox() {
        return true;
    }
}
