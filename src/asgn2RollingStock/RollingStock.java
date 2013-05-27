package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * Rolling stock are the individual carriages from which a train is constructed.
 * This abstract class defines characteristics which they all share, most
 * notably having a known gross weight, measured here in tonnes. (There are, of
 * course many other important shared characteristics of railway carriages, such
 * as identifying codes, a certain number of wheels, the track gauge they're
 * designed for, etc, but we don't need these for this assignment.)
 * 
 * @author Cameron n8328064
 */

public abstract class RollingStock extends Object {

	private static final int MIN_WEIGHT = 1;
	private int carriageGrossWeight;

	/**
	 * Constructs a railway carriage with a specific gross weight (i.e., the
	 * carriage's weight when fully laden). We assume that this weight does not
	 * change once shunting operations have begun. (Freight carriages are
	 * assumed to arrive at the marshalling yard already loaded, and we consider
	 * the weight of passengers to be negligible compared to the weight of the
	 * carriage itself.)
	 * 
	 * @param grossWeight
	 *            the carriage's gross weight in tonnes
	 * @throws TrainException
	 *             if the gross weight is not positive
	 * 
	 */
	public RollingStock(Integer grossWeight) throws TrainException {
		CheckGrossWeight(grossWeight);
		carriageGrossWeight = grossWeight;
	}

	
	/**
	 * CheckGrossWeight - This function Checks that the given gross weight is
	 * positive.
	 * 
	 * @param grossWeight
	 *            the carriage's gross weight in tonnes
	 * @throws TrainException
	 *             if the gross weight is not positive
	 */
	private void CheckGrossWeight(int grossWeight) throws TrainException {
		if (grossWeight < MIN_WEIGHT) {
			throw new TrainException("The gross weight must be positive");
		}

	}

	
	/**
	 * getGrossWeight - This function returns the gross weight.
	 * 
	 * @return the carriage's gross weight, in tonnes
	 */
	public Integer getGrossWeight() {
		return carriageGrossWeight;
	}

	
	/**
	 * toString - This is an abstract function will be specified for each
	 * rolling stock by the RollingStock class which will return a
	 * human-readable description of this railway carriage.
	 * 
	 * @return a printable description of the rolling stock
	 */
	public abstract String toString();

	
}
