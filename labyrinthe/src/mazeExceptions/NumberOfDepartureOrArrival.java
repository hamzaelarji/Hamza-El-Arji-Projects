package mazeExceptions;

/**
 * Thrown when there is an incorrect number of start or end positions in the maze.
 */
public class NumberOfDepartureOrArrival extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructs a new NumberOfDepartureOrArrival exception with a custom error message.
	 * @param type the type of the error ("departure" or "arrival")
	 * @param count the number of start or end positions given
	 */
	public NumberOfDepartureOrArrival(String type, int count) {
		super("Erreur mauvais nombre de Start ou End");
		String message = "";
		if (type.equals("departure")) {
			message = "Error, wrong number of departure, you gave " + count + " departure";
		}
		if (type.equals("arrival")) {
			message = "Error, wrong number of arrival, you gave " + count;	
		}
		System.err.println(message);
	}
}