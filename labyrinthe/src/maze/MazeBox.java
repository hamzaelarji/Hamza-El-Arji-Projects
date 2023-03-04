package maze;

import java.util.ArrayList;

import graph.VertexInterface;

/**
*This class represents a box in a maze. It implements the VertexInterface interface.
*The box has a position (x,y) in the maze and a type (departure, arrival, empty, or wall).
*The class also contains a list of its successors (boxes that can be reached from this box).
*/
public abstract class MazeBox implements VertexInterface {
	/**
	 * The x-coordinate of the box
	 */
	private final int x;
	/**
	 * The y-coordinate of the box
	 */
	private final int y;
	/**
	 * The maze to which the box belongs
	 */
	public Maze maze;
	/**
	 * The size of the hexagonal array
	 */
	private final int size=10;
	/**
	 * The 2D array of MazeBoxes that represents the maze
	 */
	public MazeBox[][] hexagonalArray = new MazeBox[size][size];
	/**
	 * The list of VertexInterfaces that are successors of the current box
	 */
	ArrayList<VertexInterface> successors;
	
	/**
	 * Constructs a new MazeBox object with the specified coordinates and maze
	 * 
	 * @param x the x-coordinate of the box
	 * @param y the y-coordinate of the box
	 * @param maze the maze to which the box belongs
	 */
	public MazeBox(int x, int y, Maze maze) {
		this.x = x;
		this.y = y;
		this.maze = maze;
		this.successors = new ArrayList<>();
	}
	
	
	/**
	 * Returns the string representation of the MazeBox in the format "(x, y)"
	 * 
	 * @return the string representation of the MazeBox
	 */
	
	public String toString() {
		return"(" + x + "," + y + ")";
	}
	
	/**
	 * Returns the x-coordinate of the MazeBox
	 * 
	 * @return the x-coordinate of the MazeBox
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Returns the y-coordinate of the MazeBox
	 * 
	 * @return the y-coordinate of the MazeBox
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Returns the type of MazeBox (Departure, Arrival, Empty, Wall) as a string
	 * 
	 * @param box the MazeBox to check
	 * @return the type of box as a string
	 */
	public String typeOfBox(MazeBox box) {
		String t="";
		if(isDepartureBox()) {
			t="D";
		}
		if(isArrivalBox()) {
			t="A";
		}
		if(isEmptyBox()) {
			t="E";
		}
		if(isWallBox()) {
			t="W";
		}
		return t;
	}
	
	 public boolean isDepartureBox() {
	        return false;
	    }
	 public boolean isArrivalBox() {
	        return false;
	    }
	 public boolean isEmptyBox() {
	        return false;
	    }
	 public boolean isWallBox() {
	        return false;
	    }
	
}

