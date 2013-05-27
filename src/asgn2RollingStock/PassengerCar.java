package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * A passenger car is designed to carry people and has a fixed seating capacity.
 * We assume that the train is a long-distance one in which all passengers are
 * assigned a seat (unlike your peak-hour, metropolitan commuting experience!).
 * 
 * @author Ahmed n8468184
 */
public class PassengerCar extends RollingStock {

	private int seatsAbility; // Number of seats on a passenger car
	private int onBoard = 0; // Number of passengers on a passenger car
	private final int MINIMUM_PASSENGERS = 0;
	private final int MINIMUM_SEATS = 0;
	private final int MINIMUM_DEPARTING = 0;

	
	/**
	 * Constructs a passenger car with a known weight and a fixed number of
	 * seats. (We allow a passenger car to have zero seats, although it would
	 * not be very useful.)
	 * 
	 * @param grossWeight
	 *            the carriage's gross weight in tonnes (ignoring the weight of
	 *            passengers, which we treat as negligible)
	 * @param numberOfSeats
	 *            how many seats are available in the carriage
	 * @throws TrainException
	 *             if the gross weight is not positive or if the number of seats
	 *             is negative
	 */
	public PassengerCar(Integer grossWeight, Integer numberOfSeats)
			throws TrainException {
		super(grossWeight);

		if (numberOfSeats < MINIMUM_SEATS) {
			throw new TrainException(
					"The number of seats must not be negative.");
		}

		seatsAbility = numberOfSeats;
	}

	
	/**
	 * Adds the given number of new passengers to the number on board the
	 * carriage. If there are too many new passengers for the number of spare
	 * seats the left over people are not boarded.
	 * 
	 * @param newPassengers
	 *            the number of people who wish to board the carriage
	 * @return the number of people who were unable to board the carriage
	 *         because they couldn't get a seat
	 * @throws TrainException
	 *             if the number of new passengers is negative
	 */
	public Integer board(Integer newPassengers) throws TrainException {

		if (newPassengers < MINIMUM_PASSENGERS) {
			throw new TrainException(
					"The number of new passengers must not be negative.");
		}

		// If there will be leftover passengers after boarding
		if ((newPassengers + onBoard) > seatsAbility) {
			int unableBoarding = (newPassengers + onBoard) - seatsAbility;
			onBoard = seatsAbility;
			return unableBoarding;
			// Else there's no leftover passenger
		} else {
			onBoard += newPassengers;
		}
		return 0;
	}

	
	/**
	 * Removes the given number of passengers from this carriage. Attempting to
	 * remove more passengers than are on board is not allowed.
	 * 
	 * @param departingPassengers
	 *            the number of passengers alighting from the carriage
	 * @throws TrainException
	 *             if the number of departing passengers is negative or if the
	 *             number of departing passengers exceeds the number on board
	 */
	public void alight(Integer departingPassengers) throws TrainException {

		if (departingPassengers < MINIMUM_DEPARTING) {
			throw new TrainException(
					"The number of departing passengers must not be negative.");
		}

		if (departingPassengers > onBoard) {
			throw new TrainException(
					"The number of departing passengers exceeds the number on board.");
		}

		onBoard -= departingPassengers;
	}

	
	/**
	 * Returns the number of passengers currently on board this carriage.
	 * 
	 * @return the number of passengers on board
	 */
	public Integer numberOnBoard() {
		return onBoard;

	}

	
	/**
	 * Returns the number of seats installed on this carriage.
	 * 
	 * @return the number of seats on this carriage
	 */
	public Integer numberOfSeats() {
		return seatsAbility;
	}

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Passenger(" + onBoard + "/" + seatsAbility + ")";
	}

}
