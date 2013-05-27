package asgn2Exceptions;

/**
 * A simple class for exceptions thrown by railway shunting and boarding operations.
 * @author Cameron n8328064
 */

@SuppressWarnings("serial")
public class TrainException extends Exception {

	/**
	 * Constructs a new TrainException object.
	 * 
	 * @param message
	 *            - An informative message about the problem found which
	 *            describes the cause of the problem
	 */
	public TrainException(String message) {
		super("Train Exception: " + message);
	}

}
