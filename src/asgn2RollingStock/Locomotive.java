package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * A locomotive is a railway carriage with the ability to propel itself and pull
 * (or push) other carriages. Thus the primary distinguishing characteristic of
 * a locomotive is how much weight it can pull.
 * 
 * This class extends the RollingStock,
 * 
 * @author Cameron n8328064
 */
public class Locomotive extends RollingStock {
	private final int MAXIMUM_WEIGHT_FACTOR = 100;
	private int powerClass;
	private char engineType;

	
	/**
	 * Constructs a new locomotive object with a fixed gross weight and
	 * classification code.
	 * 
	 * @param grossWeight
	 *            the locomotive's (fully-laden) weight in tonnes
	 * @param classification
	 *            the locomotive's two-character classification code
	 * @throws TrainException
	 *             if the locomotive's weight is not strictly positive or if its
	 *             classification code is invalid
	 */
	public Locomotive(Integer grossWeight, String classification)
			throws TrainException {
		super(grossWeight);

		int power = CheckPower(classification);

		if ((power == 0) || !CheckEngine(classification)) {
			throw new TrainException("Invalid Locomotive classification code ");
		}

		powerClass = power;
		engineType = classification.toUpperCase().charAt(1);

	}

	
	/**
	 * Checks whether the engine type input is valid. I.e., only the letters
	 * 'E', 'D', or 'S'. If they are input as lowercase they will be changed to
	 * uppercase.
	 * 
	 * @param classification
	 *            represents the classification code for the locomotive
	 * @return the boolean true or false depending on if the engine type input
	 *         was valid
	 */
	private boolean CheckEngine(String classification) {
		// takes the 2nd character from the string classification and stores its
		// uppercase value in variable engine
		char engine = classification.toUpperCase().charAt(1);
		// returns true if it's a valid engine type
		if ((engine == 'E') || (engine == 'D') || (engine == 'S'))
			return true;

		return false;
	}

	
	/**
	 * Checks whether the engine power input is valid. I.e., only an integer
	 * between 1 and 9 inclusive.
	 * 
	 * @param classification
	 * @return the integer of the engine power from the classification string if
	 *         it's valid, or the integer 0 if it was invalid
	 */
	private int CheckPower(String classification) {
		final int VALUE_OF_1 = 49; // the ASCII value of 1
		final int VALUE_OF_9 = 57; // the ASCII value of 9
		final int ACCEPTBLE_LENGTH = 2; // acceptable length of classification
										// string

		if (classification.length() != ACCEPTBLE_LENGTH)
			return 0;

		String powerString = String.valueOf(classification.charAt(0));
		int valueOfPower = Integer.valueOf(powerString.charAt(0));

		if ((valueOfPower >= VALUE_OF_1) && (valueOfPower <= VALUE_OF_9)) {
			return Integer.valueOf(powerString).intValue();
		}

		return 0;
	}

	
	/**
	 * Returns how much total weight the locomotive can pull (including itself),
	 * calculated as explained above.
	 * 
	 * @return the locomotive's "pulling power" in tonnes
	 */
	public Integer power() {
		return powerClass * MAXIMUM_WEIGHT_FACTOR;

	}

	
	@Override
	public String toString() {
		return "Loco(" + powerClass + engineType + ")";
	}

}
