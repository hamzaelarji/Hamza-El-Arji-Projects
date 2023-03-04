package maze;
/**
 * The ArrivalBox class represents a box in a maze that is marked as the destination point.
 * This class inherits from the MazeBox class.
 */
public class ArrivalBox extends MazeBox {
    
    /**
     * Constructs a new ArrivalBox object with the specified coordinates.
     *
     * @param x    the x-coordinate of the arrival box
     * @param y    the y-coordinate of the arrival box
     * @param maze the maze to which the arrival box belongs
     */
    public ArrivalBox(int x, int y, Maze maze) {
        super(x, y, maze);
    }
    public boolean isArrivalBox() {
        return true;
    }
	
}
