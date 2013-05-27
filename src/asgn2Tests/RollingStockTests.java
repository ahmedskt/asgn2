package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.FreightCar;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;
import asgn2RollingStock.RollingStock;

/**
 * This class will test all of the rolling stock classes
 * 
 * @author Ahmed n8468184, Cameron n8328064
 *
 */
public class RollingStockTests {

	Locomotive locomotive;
	PassengerCar passengerCar;
	FreightCar freightCar;
	RollingStock rollingStock;

	
	// ------ Locomotive - Constructor - grossWeight Tests ------
	//
	// Note: These tests also apply to the grossWeight variable in the
	// constructor of the other classes - FreightCar and PassengerCar -
	// since they are inherited from the same parent class - RollingStock

	/**
	 * Tests the invalid gross weight just below the low boundary
	 * 
	 * @author Ahmed n8468184
	 */
	@Test(expected = TrainException.class)
	public void testinvalidLowBoundaryWeightConstructorLocomotive()
			throws TrainException {
		locomotive = new Locomotive(0, "5E");
	}

	/**
	 * Tests an invalid, normal negative weight 
	 * 
	 * @author Ahmed n8468184
	 */
	@Test(expected = TrainException.class)
	public void testInvalidWeightConstructorLocomotive() throws TrainException {
		locomotive = new Locomotive(-100, "5E");
	}

	/**
	 * Tests a valid, normal gross weight
	 * 
	 * @author Ahmed n8468184
	 */
	@Test
	public void testNormalWeightConstructorLocomotive() throws TrainException {
		locomotive = new Locomotive(100, "5E");
	}

	/**
	 * Tests a valid, large gross weight
	 * 
	 * @author Ahmed n8468184
	 */
	@Test
	public void testValidLargeWeightConstructorLocomotive()
			throws TrainException {
		locomotive = new Locomotive(1000, "5E");
	}

	/**
	 * Tests the valid, low boundary gross weight
	 * 
	 * @author Ahmed n8468184
	 */
	@Test
	public void testValidLowBoundaryWeightConstructorLocomotive()
			throws TrainException {
		locomotive = new Locomotive(1, "5E");
	}

	// ------ Locomotive - Constructor - classification Tests ------

	/**
	 * Tests an invalid, negative integer as the power class
	 * 
	 * @author Ahmed n8468184
	 */
	@Test(expected = TrainException.class)
	public void testInvalidNegativePowerClassLocomotive() throws TrainException {
		locomotive = new Locomotive(100, "-5E");
	}

	/**
	 * Tests an invalid, large integer as the power class
	 * 
	 * @author Ahmed n8468184
	 */
	@Test(expected = TrainException.class)
	public void testInvalidLargeIntegerPowerClassLocomotive()
			throws TrainException {
		locomotive = new Locomotive(100, "10E");
	}

	/**
	 * Tests an invalid, random character as the power class
	 * 
	 * @author Ahmed n8468184
	 */
	@Test(expected = TrainException.class)
	public void testInvalidPowerClassCharacterLocomotive()
			throws TrainException {
		locomotive = new Locomotive(100, "*E");
	}

	/**
	 * Tests an invalid, random character as the engine type
	 * 
	 * @author Ahmed n8468184
	 */
	@Test(expected = TrainException.class)
	public void testInvalidEngineTypeCharacterLocomotive()
			throws TrainException {
		locomotive = new Locomotive(100, "5*");
	}

	/**
	 * Tests an invalid integer as the engine type
	 * 
	 * @author Ahmed n8468184
	 */
	@Test(expected = TrainException.class)
	public void testInvalidClassificationBothPowerClassLocomotive()
			throws TrainException {
		locomotive = new Locomotive(100, "22");
	}

	/**
	 * Tests an invalid, engine type character as the power class
	 * 
	 * @author Ahmed n8468184
	 */
	@Test(expected = TrainException.class)
	public void testInvalidClassificationBothEngineTypeLocomotive()
			throws TrainException {
		locomotive = new Locomotive(100, "SS");
	}

	/**
	 * Tests an invalid classification code with only the power class
	 * 
	 * @author Ahmed n8468184
	 */
	@Test(expected = TrainException.class)
	public void testInvalidClassificationOnlyPowerClassLocomotive()
			throws TrainException {
		locomotive = new Locomotive(100, "5");
	}

	/**
	 * Tests an invalid classification code with only the engine type
	 * 
	 * @author Ahmed n8468184
	 */
	@Test(expected = TrainException.class)
	public void testInvalidClassificationOnlyEngineTypeLocomotive()
			throws TrainException {
		locomotive = new Locomotive(100, "E");
	}

	/**
	 * Tests an invalid classification code with only one character
	 * 
	 * @author Ahmed n8468184
	 */
	@Test(expected = TrainException.class)
	public void testInvalidClassificationOnlyOneCharacterLocomotive()
			throws TrainException {
		locomotive = new Locomotive(100, "*");
	}

	/**
	 * Tests an invalid classification code with an empty string
	 * 
	 * @author Ahmed n8468184
	 */
	@Test(expected = TrainException.class)
	public void testInvalidClassificationEmptyLocomotive()
			throws TrainException {
		locomotive = new Locomotive(100, "");
	}

	/**
	 * Tests an invalid classification code with a long string
	 * 
	 * @author Ahmed n8468184
	 */
	@Test(expected = TrainException.class)
	public void testInvalidClassificationLongCharacterLocomotive()
			throws TrainException {
		locomotive = new Locomotive(100, "12345ABCDE");
	}

	/**
	 * Tests a valid, normal classification code
	 * 
	 * @author Ahmed n8468184
	 */
	@Test
	public void testNormalClassificationLocomotive() throws TrainException {
		locomotive = new Locomotive(100, "5E");
	}

	/**
	 * Tests a valid classification code with a low boundary power class
	 * 
	 * @author Ahmed n8468184
	 */
	@Test
	public void testValidLowerBoundaryPowerClassLocomotive()
			throws TrainException {
		locomotive = new Locomotive(100, "1E");
	}

	/**
	 * Tests a valid classification code with a high boundary power class
	 * 
	 * @author Ahmed n8468184
	 */
	@Test
	public void testValidUpperBoundaryPowerClassLocomotive()
			throws TrainException {
		locomotive = new Locomotive(100, "9E");
	}

	/**
	 * Tests a valid classification code with a lowercase "e" engine type
	 * 
	 * @author Ahmed n8468184
	 */
	@Test
	public void testValidSmallLetterEEngineTypeLocomotive()
			throws TrainException {
		locomotive = new Locomotive(100, "5e");
	}

	/**
	 * Tests a valid classification code with a lowercase "s" engine type
	 * 
	 * @author Ahmed n8468184
	 */
	@Test
	public void testValidSmallLetterSEngineTypeLocomotive()
			throws TrainException {
		locomotive = new Locomotive(100, "5s");
	}

	
	// ------ Locomotive - power Method Tests ------

	
	/**
	 * Tests a valid, normal power class with the power method
	 * 
	 * @author Ahmed n8468184
	 */
	@Test
	public void testNormalPowerLocomotive() throws TrainException {
		locomotive = new Locomotive(100, "5E");
		assertTrue(locomotive.power() == 500);
	}

	/**
	 * Tests a valid, lower bound power class with the power method
	 * 
	 * @author Ahmed n8468184
	 */
	@Test
	public void testValidLowerBoundPowerLocomotive() throws TrainException {
		locomotive = new Locomotive(100, "1E");
		assertTrue(locomotive.power() ==  100);
	}

	/**
	 * Tests a valid, upper bound power class with the power method
	 * 
	 * @author Ahmed n8468184
	 */
	@Test
	public void testValidUpperBoundPowerLocomotive() throws TrainException {
		locomotive = new Locomotive(100, "9E");
		assertTrue(locomotive.power() ==  900);
	}

	// ------ Locomotive - toString Method Tests ------

	/**
	 * Tests a valid return by the toString method
	 * 
	 * @author Ahmed n8468184
	 */
	@Test
	public void testValidToStringMethodLocomotive() throws TrainException {
		locomotive = new Locomotive(100, "5D");
		assertEquals(locomotive.toString(), "Loco(5D)");
	}

	
	// ------ FreightCar - Constructor - goodsType Tests ------

	
	/*
	 * Tests a valid, normal goods type
	 * 
	 * @author Cameron n8328064
	 */
	@Test
	public void testNormalGoodsTypeFreightCar() throws TrainException {
		freightCar = new FreightCar(70, "G");
	}

	/*
	 * Tests a valid goods type with lowercase "g"
	 * 
	 * @author Cameron n8328064
	 */
	@Test
	public void testValidSmallLetterGGoodsTypeFreightCar()
			throws TrainException {
		freightCar = new FreightCar(70, "g");
	}

	/*
	 * Tests a valid goods type with lowercase "r"
	 * 
	 * @author Cameron n8328064
	 */
	@Test
	public void testValidSmallLetterRGoodsTypeFreightCar()
			throws TrainException {
		freightCar = new FreightCar(70, "r");
	}

	
	// ------ FreightCar - goodsType Method Tests ------

	
	/*
	 * Tests a valid normal goods type with the goodsType method
	 * 
	 * @author Cameron n8328064
	 */
	@Test
	public void testGoodsTypeMethodFreightCar() throws TrainException {
		freightCar = new FreightCar(70, "G");
		assertEquals(freightCar.goodsType(), "G");
	}

	
	// ------ FreightCar - toString Method Tests ------

	
	/*
	 * Tests a valid, normal goods type for the toString method
	 * 
	 * @author Cameron n8328064
	 */
	@Test
	public void testValidToStringMethodFreightCar() throws TrainException {
		freightCar = new FreightCar(70, "G");
		assertEquals(freightCar.toString(), "Freight(G)");
	}

	// ------ PassengerCar - Constructor - numberOfSeats tests ------

	/*
	 * Tests an invalid, negative number of seats for a passenger car
	 * 
	 * @author Cameron n8328064
	 */
	@Test(expected = TrainException.class)
	public void testInvalidNegativeNumberOfSeatsConstructorPassengerCar()
			throws TrainException {
		passengerCar = new PassengerCar(80, -20);
	}

	/*
	 * Tests a valid, normal number of seats for a passenger car
	 * 
	 * @author Cameron n8328064
	 */
	@Test
	public void testNormalNumberOfSeatsConstructorPassengerCar()
			throws TrainException {
		passengerCar = new PassengerCar(80, 20);
	}

	/*
	 * Tests a valid, very large number of seats for a passenger car
	 * 
	 * @author Cameron n8328064
	 */
	@Test
	public void testValidLargeNumberOfSeatsConstructorPassengerCar()
			throws TrainException {
		passengerCar = new PassengerCar(80, 200);
	}

	/*
	 * Tests a valid, low boundary number of seats for a passenger car (i.e., no
	 * seats)
	 * 
	 * @author Cameron n8328064
	 */
	@Test
	public void testValidNoSeatsConstructorPassengerCar() throws TrainException {
		passengerCar = new PassengerCar(80, 0);
	}

	// ------ PassengerCar - board Method Tests ------

	/*
	 * Tests an invalid, low boundary negative number of passengers boarding
	 * 
	 * @author Cameron n8328064
	 */
	@Test(expected = TrainException.class)
	public void testEmptyCarInvalidNegativeNumberPassengersBoardPassengerCar()
			throws TrainException {
		passengerCar = new PassengerCar(80, 20);
		passengerCar.board(-1);
	}

	/*
	 * Tests a valid, low boundary number of passengers boarding (i.e., no
	 * passengers)
	 * 
	 * @author Cameron n8328064
	 */
	@Test
	public void testValidEmptyCarNoPassengersBoardPassengerCar()
			throws TrainException {
		passengerCar = new PassengerCar(80, 20);
		assertTrue(passengerCar.board(0) ==  0);
	}

	/*
	 * Tests a valid, normal number of passengers boarding
	 * 
	 * @author Cameron n8328064
	 */
	@Test
	public void testValidEmptyCarSmallNumberPassengersBoardPassengerCar()
			throws TrainException {
		passengerCar = new PassengerCar(80, 20);
		assertTrue(passengerCar.board(10) ==  0);
	}

	/*
	 * Tests a valid, maximum number of passengers boarding a particular
	 * passenger car
	 * 
	 * @author Cameron n8328064
	 */
	@Test
	public void testValidEmptyCarMaxNumberPassengersBoardPassengerCar()
			throws TrainException {
		passengerCar = new PassengerCar(80, 20);
		assertTrue(passengerCar.board(20) ==  0);
	}

	/*
	 * Tests a valid, one too many passengers boarding a particular passenger
	 * car
	 * 
	 * @author Cameron n8328064
	 */
	@Test
	public void testValidEmptyCarOneTooManyPassengersBoardPassengerCar()
			throws TrainException {
		passengerCar = new PassengerCar(80, 20);
		assertTrue(passengerCar.board(21) ==  1);
	}

	/*
	 * Tests a valid, too many passengers boarding a particular passenger car
	 * 
	 * @author Cameron n8328064
	 */
	@Test
	public void testValidEmptyCarTooManyPassengersBoardPassengerCar()
			throws TrainException {
		passengerCar = new PassengerCar(80, 20);
		assertTrue(passengerCar.board(30) ==  10);
	}

	/*
	 * Tests a valid no passengers boarding a passenger car which has already
	 * been boarded
	 * 
	 * @author Cameron n8328064
	 */
	@Test
	public void testValidNonEmptyCarNoPassengersBoardPassengerCar()
			throws TrainException {
		passengerCar = new PassengerCar(80, 20);
		passengerCar.board(5);
		assertTrue(passengerCar.board(0) ==  0);
	}

	/*
	 * Tests a valid, normal number of passengers boarding a passenger car which
	 * has already been boarded
	 * 
	 * @author Cameron n8328064
	 */
	@Test
	public void testValidNonEmptyCarNormalPassengersBoardPassengerCar()
			throws TrainException {
		passengerCar = new PassengerCar(80, 20);
		passengerCar.board(5);
		assertTrue(passengerCar.board(10) ==  0);
	}

	/*
	 * Tests a valid, maximum number of passengers boarding a passenger car
	 * which has already been boarded
	 * 
	 * @author Cameron n8328064
	 */
	@Test
	public void testValidNonEmptyCarMaxPassengersBoardPassengerCar()
			throws TrainException {
		passengerCar = new PassengerCar(80, 20);
		passengerCar.board(5);
		assertTrue(passengerCar.board(15) ==  0);
	}

	/*
	 * Tests a valid, one too many passengers boarding a passenger car which has
	 * already been boarded
	 * 
	 * @author Cameron n8328064
	 */
	@Test
	public void testValidNonEmptyCarOneTooManyPassengersBoardPassengerCar()
			throws TrainException {
		passengerCar = new PassengerCar(80, 20);
		passengerCar.board(5);
		assertTrue(passengerCar.board(16) ==  1);
	}

	/*
	 * Tests a valid, too many passengers boarding a passenger car which has
	 * already been boarded
	 * 
	 * @author Cameron n8328064
	 */
	@Test
	public void testValidNonEmptyCarTooManyPassengersBoardPassengerCar()
			throws TrainException {
		passengerCar = new PassengerCar(80, 20);
		passengerCar.board(5);
		assertTrue(passengerCar.board(30) ==  15);
	}

	/*
	 * Tests a valid number of passengers boarding multiple times
	 * 
	 * @author Cameron n8328064
	 */
	@Test
	public void testValidNonEmptyCarMultipleBoardPassengerCar()
			throws TrainException {
		passengerCar = new PassengerCar(80, 20);
		passengerCar.board(5);
		passengerCar.board(5);
		assertTrue(passengerCar.board(8) ==  0);
	}

	/*
	 * Tests a valid, too many passengers boarding multiple times
	 * 
	 * @author Cameron n8328064
	 */
	@Test
	public void testValidNonEmptyCarMultipleBoardTooManyPassengerCar()
			throws TrainException {
		passengerCar = new PassengerCar(80, 20);
		passengerCar.board(5);
		passengerCar.board(5);
		assertTrue(passengerCar.board(15) ==  5);
	}

	
	// ------ PassengerCar - alight Method Tests ------

	
	/*
	 * Tests an invalid, negative number of passengers alighting
	 * 
	 * @author Cameron n8328064
	 */
	@Test(expected = TrainException.class)
	public void testInvalidNegativeNumberPassengersAlightPassengerCar()
			throws TrainException {
		passengerCar = new PassengerCar(80, 20);
		passengerCar.alight(-1);
	}

	/*
	 * Tests an invalid, one too many passengers alighting empty car
	 * 
	 * @author Cameron n8328064
	 */
	@Test(expected = TrainException.class)
	public void testEmptyCarOneTooManyPassengersAlightPassengerCar()
			throws TrainException {
		passengerCar = new PassengerCar(80, 20);
		passengerCar.alight(1);
	}

	/*
	 * Tests an invalid, too many passengers alighting empty car
	 * 
	 * @author Cameron n8328064
	 */
	@Test(expected = TrainException.class)
	public void testEmptyCarTooManyPassengersAlightPassengerCar()
			throws TrainException {
		passengerCar = new PassengerCar(80, 20);
		passengerCar.alight(10);
	}

	/*
	 * Tests an invalid, one too many passengers alighting non-empty car
	 * 
	 * @author Cameron n8328064
	 */
	@Test(expected = TrainException.class)
	public void testNormalCarOneTooManyPassengersAlightPassengerCar()
			throws TrainException {
		passengerCar = new PassengerCar(80, 20);
		passengerCar.board(10);
		passengerCar.alight(11);
	}

	/*
	 * Tests an invalid, too many passengers alighting non-empty car
	 * 
	 * @author Cameron n8328064
	 */
	@Test(expected = TrainException.class)
	public void testNormalCarTooManyPassengersAlightPassengerCar()
			throws TrainException {
		passengerCar = new PassengerCar(80, 20);
		passengerCar.board(10);
		passengerCar.alight(20);
	}

	/*
	 * Tests a valid, normal number of passengers alighting
	 * 
	 * @author Cameron n8328064
	 */
	@Test
	public void testNormalCarNormalNumberPassengersAlightPassengerCar()
			throws TrainException {
		passengerCar = new PassengerCar(80, 20);
		passengerCar.board(10);
		passengerCar.alight(5);
		assertTrue(passengerCar.numberOnBoard() ==  5);
	}

	/*
	 * Tests a valid, low boundary number of passengers alighting (i.e., no
	 * passengers)
	 * 
	 * @author Cameron n8328064
	 */
	@Test
	public void testNormalCarNormalNoPassengersAlightPassengerCar()
			throws TrainException {
		passengerCar = new PassengerCar(80, 20);
		passengerCar.board(10);
		passengerCar.alight(0);
		assertTrue(passengerCar.numberOnBoard() ==  10);
	}

	/*
	 * Tests a valid, max number of passengers alighting
	 * 
	 * @author Cameron n8328064
	 */
	@Test
	public void testNormalCarNormalMaxPassengersAlightPassengerCar()
			throws TrainException {
		passengerCar = new PassengerCar(80, 20);
		passengerCar.board(10);
		passengerCar.alight(10);
		assertTrue(passengerCar.numberOnBoard() ==  0);
	}

	// ------ PassengerCar - numberOnBoard Method Tests ------

	/*
	 * Tests the numberOnBoard method with an empty passenger car
	 * 
	 * @author Cameron n8328064
	 */
	@Test
	public void testNumberOnBoardMethodEmptyPassengerCar()
			throws TrainException {
		passengerCar = new PassengerCar(80, 20);
		assertTrue(passengerCar.numberOnBoard() ==  0);
	}

	/*
	 * Tests the numberOnBoard method with a passenger car boarded once
	 * 
	 * @author Cameron n8328064
	 */
	@Test
	public void testNumberOnBoardMethodOneBoardPassengerCar()
			throws TrainException {
		passengerCar = new PassengerCar(80, 20);
		passengerCar.board(5);
		assertTrue(passengerCar.numberOnBoard() ==  5);
	}

	/*
	 * Tests the numberOnBoard method with a passenger car boarded multiple
	 * times
	 * 
	 * @author Cameron n8328064
	 */
	@Test
	public void testNumberOnBoardMethodMultipleBoardPassengerCar()
			throws TrainException {
		passengerCar = new PassengerCar(80, 20);
		passengerCar.board(5);
		passengerCar.board(10);
		passengerCar.board(1);
		assertTrue(passengerCar.numberOnBoard() ==  16);
	}

	/*
	 * Tests the numberOnBoard method with an empty passenger car, boarded and
	 * alighted once
	 * 
	 * @author Cameron n8328064
	 */
	@Test
	public void testNumberOnBoardMethodOneBoardAlightPassengerCar()
			throws TrainException {
		passengerCar = new PassengerCar(80, 20);
		passengerCar.board(10);
		passengerCar.alight(3);
		assertTrue(passengerCar.numberOnBoard() == 7);
	}

	/*
	 * Tests the numberOnBoard method with an empty passenger car, boarded and
	 * alighted multiple times
	 * 
	 * @author Cameron n8328064
	 */
	@Test
	public void testNumberOnBoardMethodMultipleBoardAlightPassengerCar()
			throws TrainException {
		passengerCar = new PassengerCar(80, 20);
		passengerCar.board(10);
		passengerCar.alight(5);
		passengerCar.board(1);
		passengerCar.alight(3);
		passengerCar.board(5);
		passengerCar.alight(5);
		assertTrue(passengerCar.numberOnBoard() ==  3);
	}

	// ------ PassengerCar - numberOfSeats Method Tests ------

	/*
	 * Tests the numberOfSeats method with a passenger car with no seats
	 * 
	 * @author Cameron n8328064
	 */
	@Test
	public void testValidLowerBoundNumberOfSeatsMethodPassengerCar()
			throws TrainException {
		passengerCar = new PassengerCar(80, 0);
		assertTrue(passengerCar.numberOfSeats() ==  0);
	}

	/*
	 * Tests the numberOfSeats method with an passenger car with a normal number
	 * of seats
	 * 
	 * @author Cameron n8328064
	 */
	@Test
	public void testNormalNumberOfSeatsMethodPassengerCar()
			throws TrainException {
		passengerCar = new PassengerCar(80, 20);
		assertTrue(passengerCar.numberOfSeats() ==  20);
	}

	/*
	 * Tests the numberOfSeats method with an passenger car with a large number
	 * of seats
	 * 
	 * @author Cameron n8328064
	 */
	@Test
	public void testValidLargeNumberOfSeatsMethodPassengerCar()
			throws TrainException {
		passengerCar = new PassengerCar(80, 200);
		assertTrue(passengerCar.numberOfSeats() == 200);
	}

	// ------ PassengerCar - toString Method Tests ------

	/**
	 * Tests the toString method with a normal number of passengers on board
	 * 
	 * @author Cameron n8328064
	 */
	@Test
	public void testToStringMethodNormalPassengerCar() throws TrainException {
		passengerCar = new PassengerCar(80, 20);
		passengerCar.board(5);
		assertEquals(passengerCar.toString(), "Passenger(5/20)");
	}

	
	// ------ RollingStock - getGrossWeight Method Tests ------
	
	
	/**
	 * Tests the getGrossWeight method on a rolling stock of type locomotive
	 * 
	 * @author Ahmed n8468184
	 */
	@Test
	public void testGetGrossWeight() throws TrainException {
		Locomotive locomotive = new Locomotive(100, "3E");
		assertTrue(locomotive.getGrossWeight()== 100);
	}
	
	
}
