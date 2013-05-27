package asgn2Train;

import java.util.ArrayList;
import asgn2Exceptions.TrainException;
import asgn2RollingStock.FreightCar;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;
import asgn2RollingStock.RollingStock;

/**
 * A train is a sequence of carriages. This class defines various operations
 * that can be performed to prepare a long-distance train for departure.
 * 
 * We assume that a train can be assembled from any available rolling stock,
 * including locomotives, passenger cars and freight cars. However, they may be
 * configured in only a certain sequence:
 * 
 * 1.The first carriage must be a locomotive (and there can be only one
 * locomotive per train).
 * 
 * 2.This may be followed by zero or more passenger cars.
 * 
 * 3.These may be followed by zero or more freight cars.
 * 
 * Any other configurations of rolling stock are disallowed.
 * 
 * The process of preparing the train for departure occurs in two stages:
 * 
 * 1.The train is assembled from individual carriages. New carriages may be
 * added to the rear of the train only. (Similarly, carriages may be removed
 * from the rear of the train only.)
 * 
 * 2.Passengers board the train. For safety reasons, no carriage shunting
 * operations may be performed when any passengers are on board the train.
 * 
 * @author Ahmed n8468184, Cameron n8328064
 */
public class DepartingTrain extends Object {

	private int passengersOnTrain; // total number of passengers on train
	private int trainSeatsAbility; // total number of seats on train
	private boolean freightCarsExist; // Indicates if there are any freight cars
										// on the train

	private final int NO_SEATS = 0;
	private final int NO_PASSENGERS = 0;
	private final int INITIAL_INDEX = 0; // An initial index for the iterator
	private int trainIterator = INITIAL_INDEX;

	// Creates an arrayList train which we can add carriages to
	private ArrayList<RollingStock> train = new ArrayList<RollingStock>();

	/**
	 * Constructs a (potential) train object containing no carriages (yet).
	 * 
	 * @author Ahmed n8468184
	 */
	public DepartingTrain() {
		train.clear();

		// Resets all variables
		passengersOnTrain = 0;
		freightCarsExist = false;
		trainSeatsAbility = 0;
		trainIterator = INITIAL_INDEX;

	}

	
	/**
	 * Adds a new carriage to the end of the train. However, a new carriage may
	 * be added only if the resulting train configuration is valid, as per the
	 * rules listed above. Furthermore, shunting operations may not be performed
	 * if there are passengers on the train.
	 * 
	 * @param newCarriage
	 *            the new carriage to be added
	 * @throws TrainException
	 *             if adding the new carriage would produce an invalid train
	 *             configuration, or if there are passengers on the train
	 * 
	 * @author Ahmed n8468184
	 */
	public void addCarriage(RollingStock newCarriage) throws TrainException {

		if (train.isEmpty()) {

			if (newCarriage instanceof Locomotive) {
				train.add(newCarriage);
			} else {
				throw new TrainException(
						"Invalid train configuration: Locomotive must be the first carriage.");
			}

		} else if (passengersOnTrain > NO_PASSENGERS) {
			throw new TrainException(
					"There are passengers on the train. No carriage shunting operations may be performed.");

		} else if (newCarriage instanceof Locomotive) {
			throw new TrainException(
					"Invalid train configuration: There can only be one locomotive per train.");

		} else if (newCarriage instanceof PassengerCar) {
			// If there's no freight cars already attached
			if (!freightCarsExist) {
				train.add(newCarriage);
				trainSeatsAbility += ((PassengerCar) newCarriage)
						.numberOfSeats();
			} else {
				throw new TrainException(
						"Invalid train configuration: Freight cars already exist.");
			}

		} else if (newCarriage instanceof FreightCar) {
			freightCarsExist = true;
			train.add(newCarriage);
		} else {
			throw new TrainException(
					"Invalid train configuration: Unacceptable carriage name.");
		}

	}

	
	/**
	 * Adds the given number of people to passenger carriages on the train. We
	 * seat the passengers in order of the passenger cars from left to right,
	 * and the method returns the number of any leftover passengers.
	 * 
	 * @param newPassengers
	 *            the number of people wish to board the train
	 * @return the number of people who were unable to board the train because
	 *         they couldn't get a seat
	 * @throws TrainException
	 *             if the number of new passengers is negative
	 * 
	 * @author Ahmed n8468184
	 */
	public Integer board(Integer newPassengers) throws TrainException {

		if (newPassengers < NO_PASSENGERS) {
			throw new TrainException(
					"The number of new train passengers must by positive");
		}

		if (trainSeatsAbility == NO_SEATS) {
			throw new TrainException("There are no seats on the train.");
		}

		// If there will be leftover passengers after boarding all passenger
		// cars
		if ((newPassengers + passengersOnTrain) > trainSeatsAbility) {
			int unableBoarding = (newPassengers + passengersOnTrain)
					- trainSeatsAbility;
			passengersOnTrain = trainSeatsAbility;

			// Goes through each carriage on the train
			for (int index = 0; index < train.size(); index++) {
				RollingStock temporaryRollingStock = train.get(index);
				// If it is a a passenger car
				if (temporaryRollingStock instanceof PassengerCar) {
					// Board the car with number of available seats
					((PassengerCar) temporaryRollingStock)
							.board(((PassengerCar) temporaryRollingStock)
									.numberOfSeats()
									- ((PassengerCar) temporaryRollingStock)
											.numberOnBoard());
				}
			}
			return unableBoarding;

			// Else there won't be leftover passengers on the whole train
		} else {
			passengersOnTrain += newPassengers;

			// boolean done indicates if we have finished checking if a
			// particular passenger car will fill up
			boolean done = false;
			// Goes through each carriage on the train
			for (int index = 0; index < train.size() && !done; index++) {
				RollingStock temporaryRollingStock = train.get(index);

				// If it is a passenger car
				if (temporaryRollingStock instanceof PassengerCar) {
					// And if there will be leftover passengers from boarding it
					if ((newPassengers + ((PassengerCar) temporaryRollingStock)
							.numberOnBoard()) > ((PassengerCar) temporaryRollingStock)
							.numberOfSeats()) {
						// Set local variable getseat to the number of available
						// seats
						int getseat = (((PassengerCar) temporaryRollingStock)
								.numberOfSeats() - ((PassengerCar) temporaryRollingStock)
								.numberOnBoard());
						// And board the passenger car with the number of
						// available seats
						((PassengerCar) temporaryRollingStock).board(getseat);
						// The number of new passengers has now been reduced by
						// that number of available seats on the passenger car
						newPassengers -= getseat;

						// Else there won't be leftover passengers after
						// boarding this car
					} else {
						// Finished checking if a certain passenger car will
						// fill up
						done = true;
						// Board the rest of the new passengers
						((PassengerCar) temporaryRollingStock)
								.board(newPassengers);
					}
				}
			}
			return NO_PASSENGERS; // There will be no passengers leftover from
									// boarding
		}
	}

	
	/**
	 * Removes the last carriage from the train. (This may be the locomotive if
	 * it is the only item of rolling stock on the train.) However, shunting
	 * operations may not be performed if there are passengers on the train.
	 * 
	 * @throws TrainException
	 *             if there is no rolling stock on the "train", or if there are
	 *             passengers on the train.
	 * 
	 * @author Cameron n8328064
	 */
	public void removeCarriage() throws TrainException {
		if (train.isEmpty()) {
			throw new TrainException("No rolling stock on the train.");
		}
		if (passengersOnTrain > NO_PASSENGERS) {
			throw new TrainException(
					"There are passengers on the train. No carriage shunting operations may be performed ");
		}

		// Removes the last carriage on the train
		RollingStock deleteCarriage = train.remove(train.size() - 1);

		boolean doneLookingFreight = false;
		boolean freightFound = false;

		// Goes through each carriage of the train
		for (int index = 0; index < train.size() && !doneLookingFreight; index++) {
			RollingStock temporaryRollingStock = train.get(index);
			if (temporaryRollingStock instanceof FreightCar) {
				// There is at least one freight carriage on the train
				freightFound = true;
				doneLookingFreight = true;
			}
		}
		if (!freightFound) {
			freightCarsExist = false;
		}

		// reduces the total number of seats on the train if a passenger car is
		// removed
		if (deleteCarriage instanceof PassengerCar) {
			trainSeatsAbility -= ((PassengerCar) deleteCarriage)
					.numberOfSeats();
		}

	}

	
	/**
	 * Returns the first carriage on the train (which must be a locomotive).
	 * Special value null is returned if there are no carriages on the train at
	 * all.
	 * 
	 * @return the first carriage in the train, or null if there are no
	 *         carriages
	 * 
	 * @author Cameron n8328064
	 */
	public RollingStock firstCarriage() {
		if (train.isEmpty()) {
			return null;
		} else {
			// Set the iterator index back to 0, regardless of where it was at
			trainIterator = INITIAL_INDEX;
			Locomotive locomotive = (Locomotive) train.get(trainIterator);
			trainIterator++;
			return locomotive;
		}
	}

	
	/**
	 * Returns the first carriage on the train (which must be a locomotive).
	 * Special value null is returned if there are no carriages on the train at
	 * all.
	 * 
	 * @return the train's next carriage after the one returned by the
	 *         immediately preceding call to either firstCarriage or
	 *         nextCarriage, or null if there is no such carriage
	 * 
	 * @author Cameron n8328064
	 */
	public RollingStock nextCarriage() {
		// If there is another carriage
		if (trainIterator < train.size()) {
			RollingStock rollingStock = train.get(trainIterator);
			trainIterator++;
			return rollingStock;
		} else {
			return null;
		}
	}

	
	/**
	 * Returns the total number of seats on the train (whether occupied or not),
	 * counting all passenger cars.
	 * 
	 * @return the number of seats on the train
	 * 
	 * @author Ahmed n8468184
	 */
	public Integer numberOfSeats() {
		return trainSeatsAbility;

	}

	
	/**
	 * Returns the total number of passengers currently on the train, counting
	 * all passenger cars.
	 * 
	 * @return the number of passengers on the train
	 * 
	 * @author Cameron n8328064
	 */
	public Integer numberOnBoard() {
		return passengersOnTrain;
	}

	
	/**
	 * Returns a human-readable description of the entire train. This has the
	 * form of a hyphen-separated list of carriages, starting with the
	 * locomotive on the left. The description is thus a string "a-b-...-z",
	 * where a is the human-readable description of the first carriage (the
	 * locomotive), b is the description of the second carriage, etc, until the
	 * description of the last carriage z. (Note that there should be no hyphen
	 * after the last carriage.) For example, a possible train description may
	 * be "Loco(6D)-Passenger(13/24)-Passenger(16/16)-Freight(G)".
	 * 
	 * In the degenerate case of a "train" with no carriages, the empty string
	 * is returned.
	 * 
	 * @author Ahmed n8468184
	 */
	public String toString() {
		// Creates an empty string will be added to
		String description = "";
		// For the number of carriages on the train
		for (int index = 0; index < train.size(); index++) {
			RollingStock temporaryRollingStock = train.get(index);
			// Add to string description the description of the current carriage
			description += temporaryRollingStock.toString();
			// If this wasn't the last carriage
			if (index < train.size() - 1) {
				description += "-";
			}
		}
		return description;
	}

	
	/**
	 * Returns whether or not the train is capable of moving. A train can move
	 * if its locomotive's pulling power equals or exceeds the train's total
	 * weight (including the locomotive itself).
	 * 
	 * In the degenerate case of a "train" which doesn't have any rolling stock
	 * at all yet, the method returns true.
	 * 
	 * @return true if the train can move (or contains no carriages), false
	 *         otherwise
	 * 
	 * @author Ahmed n8468184
	 */
	public boolean trainCanMove() {
		int totalWeight = 0;
		if (train.isEmpty()) {
			return true;
		}

		// For the number of carriages on the train
		for (int index = 0; index < train.size(); index++) {
			RollingStock temporaryRollingStock = train.get(index);
			// Add the current carriages weight onto the total
			totalWeight += temporaryRollingStock.getGrossWeight();
		}

		// Checks whether or not the locomotive's pulling power is greater than
		// the total weight of the train
		return (((Locomotive) train.get(INITIAL_INDEX)).power() >= totalWeight);

	}
}
