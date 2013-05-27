package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * Freight cars are designed to handle a variety of goods. For the purposes of
 * this assignment we assume there are three freight car types of interest,
 * characterised by the kinds of goods they are designed to carry:
 * 
 * "G" - General goods "R" - Refrigerated goods "D" - Dangerous materials
 * 
 * @author Ahmed n8468184
 */
public class FreightCar extends RollingStock {
	private String goodsType;

	
	/**
	 * Constructs a freight car object.
	 * 
	 * @param grossWeight
	 *            the freight car's gross weight (fully-laden), in tonnes
	 * @param goodsType
	 *            the type of goods the car is designed to carry (either the
	 *            letters "G", "R" or "D", uppercase or lowercase)
	 * @throws TrainException
	 */
	public FreightCar(Integer grossWeight, String goodsType) throws TrainException {
		super(grossWeight);

		String upperCaseValue = goodsType.toUpperCase();
		if ((!upperCaseValue.equals("G")) && (!upperCaseValue.equals("R"))
				&& (!upperCaseValue.equals("D"))) {
			throw new TrainException("Invalid FreightCar classification code ");
		}

		this.goodsType = upperCaseValue;
	}

	
	/**
	 * Returns the type of goods this carriage was designed to carry. (Simulates
	 * someone checking the label on the freight car to determine what's
	 * inside.)
	 * 
	 * @return the goodsType (G", "R" or "D")
	 */
	public String goodsType() {
		return goodsType;
	}

	
	@Override
	public String toString() {
		return "Freight(" + goodsType + ")";
	}

}
