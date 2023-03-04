package mazeExceptions;

/**
 * An exception class that is thrown when there is an error reading a maze file.
 */
public class MazeReadingException extends Exception {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructs a new MazeReadingException with a default message of "Erreur fichier".
	 */
	public MazeReadingException() {
		super("Erreur fichier");
	}
}