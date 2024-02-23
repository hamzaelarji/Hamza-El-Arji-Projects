package maze;
/**
 * The EmptyBox class represents an empty box in a maze.
 * It inherits from the MazeBox class.
 */
public class EmptyBox extends MazeBox {

	/**
	 * Constructs an instance of the EmptyBox class with the specified coordinates and maze.
	 *
	 * @param x    the x coordinate of the empty box
	 * @param y    the y coordinate of the empty box
	 * @param maze the maze to which the empty box belongs
	 */
	public EmptyBox(int x, int y, Maze maze) {
		super(x, y, maze);
	}
	public boolean isEmptyBox() {
        return true;
    }


}
