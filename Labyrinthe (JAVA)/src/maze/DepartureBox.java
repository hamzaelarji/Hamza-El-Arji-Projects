package maze;
/**
 * A class representing a departure box in a maze.
 */
public class DepartureBox extends MazeBox {
    
    /**
     * Constructs a new DepartureBox object with the specified coordinates and maze.
     * 
     * @param x the x-coordinate of the box in the maze
     * @param y the y-coordinate of the box in the maze
     * @param maze the maze the box belongs to
     */
    public DepartureBox(int x, int y, Maze maze) {
        super(x, y, maze);                                      
    }
    public boolean isDepartureBox() {
        return true;
    }

}
