package mazeExceptions;

/**
 * Thrown when there is an incorrect number of start or end positions in the maze.
 */
public class NoPath extends Exception {
	
	private static final long serialVersionUID = 1L;
	

	public NoPath() {
		super("No solution possible");
		System.err.println("No solution possible");
		
	}
}