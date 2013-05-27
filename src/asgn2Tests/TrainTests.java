package asgn2Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.FreightCar;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;
import asgn2RollingStock.RollingStock;
import asgn2Train.DepartingTrain;


/**
 * This class will test the DepartingTrain class
 * 
 * @author Ahmed n8468184, Cameron n8328064
 */
public class TrainTests {
	
	ArrayList<RollingStock> train = new ArrayList<RollingStock>();	
	
	
	// ------ Constructor Tests ------
	
	
	/**
	 * Tests if the passenger and seats are set to 0 
	 * 
	 * @author Cameron
	 */
	@Test
	public void testConstructorNoPassengersOrSeats() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		assertTrue(train.numberOnBoard()== 0);
		assertTrue(train.numberOfSeats()== 0);
	}
	
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testConstructorPassengersAndSeatsReset() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.addCarriage(new PassengerCar(80,30));
		train.addCarriage(new FreightCar(80,"G"));
		train.addCarriage(new FreightCar(80,"R"));
		train.board(40);
		train = new DepartingTrain();
		assertTrue(train.numberOnBoard()== 0);
		assertTrue(train.numberOfSeats()== 0);
				
	}
	
	
	
	// ------ addCarriage Method Tests ------
	
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test (expected = TrainException.class)
	public void testAddCarriageInvalidPassengerCarFirst() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new PassengerCar(80,20));	
	}

	/**
	 * 
	 * @author Cameron
	 */
	@Test (expected = TrainException.class)
	public void testAddCarriageInvalidFreightCarFirst() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new FreightCar(70,"G"));
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test (expected = TrainException.class)
	public void testAddCarriageInvalidTwoLocomotives() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(100,"5E"));
		train.addCarriage(new Locomotive(100,"5E"));
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test (expected = TrainException.class)
	public void testAddCarriageInvalidRandomExtraLocomotive() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(100,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.addCarriage(new FreightCar(80,"G"));
		train.addCarriage(new Locomotive(100,"5E"));
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test (expected = TrainException.class)
	public void testAddCarriageInvalidPassengerCarAfterFreightCar() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(100,"5E"));
		train.addCarriage(new FreightCar(80,"G"));
		train.addCarriage(new PassengerCar(80,20));	
	}	
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test (expected = TrainException.class)
	public void testAddCarriageInvalidPassengerCarAfterMultipleFreightCar() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(100,"5E"));
		train.addCarriage(new FreightCar(70,"G"));
		train.addCarriage(new FreightCar(80,"R"));
		train.addCarriage(new FreightCar(90,"D"));
		train.addCarriage(new PassengerCar(80,20));	
	}	
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test (expected = TrainException.class)
	public void testAddCarriageInvalidMixedPassengerCarFreightCar() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(100,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.addCarriage(new FreightCar(80,"G"));
		train.addCarriage(new PassengerCar(80,20));
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test (expected = TrainException.class)
	public void testAddCarriageInvalidPassengerCarPassengersOnBoardFirst() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));	
		train.board(10);
		train.addCarriage(new PassengerCar(80,20));
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test (expected = TrainException.class)
	public void testAddCarriageInvalidFreightCarPassengersOnBoardFirst() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));	
		train.board(10);
		train.addCarriage(new FreightCar(80,"G"));
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testAddCarriageValidOnlyLocomotive() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testAddCarriageValidOnePassengerCar() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testAddCarriageValidMultiplePassengerCar() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.addCarriage(new PassengerCar(90,30));
		train.addCarriage(new PassengerCar(100,40));
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testAddCarriageValidOneFreightCar() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new FreightCar(80,"G"));
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testAddCarriageValidMultipleFreightCar() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new FreightCar(80,"G"));
		train.addCarriage(new FreightCar(85,"R"));
		train.addCarriage(new FreightCar(90,"D"));
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testAddCarriageValidOnePassengerCarOneFreightCar() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.addCarriage(new FreightCar(80,"G"));
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testAddCarriageValidMultiplePassengerCarOneFreightCar() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.addCarriage(new PassengerCar(90,30));
		train.addCarriage(new PassengerCar(100,40));
		train.addCarriage(new FreightCar(80,"G"));
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testAddCarriageValidOnePassengerCarMultipleFreightCar() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.addCarriage(new FreightCar(80,"G"));
		train.addCarriage(new FreightCar(85,"R"));
		train.addCarriage(new FreightCar(90,"D"));
	}	
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testAddCarriageValidMultiplePassengerAndFreightCar() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.addCarriage(new PassengerCar(90,30));
		train.addCarriage(new PassengerCar(100,40));
		train.addCarriage(new FreightCar(80,"G"));
		train.addCarriage(new FreightCar(85,"R"));
		train.addCarriage(new FreightCar(90,"D"));
	}	
	
	
	// ------ board Method Tests ------	
	

	/**
	 * 
	 * @author Cameron
	 */
	@Test (expected = TrainException.class)
	public void testBoardInvalidBoundaryNegativePassengers() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.board(-1);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test (expected = TrainException.class)
	public void testBoardInvalidLargeNegativePassengers() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.board(-15);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test (expected = TrainException.class)
	public void testInvalidBoardOnePassengerCarNoSeatsOneBoard() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,0));
		train.board(1);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test (expected = TrainException.class)
	public void testInvalidBoardOnePassengerCarNoSeatsNormalBoard() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,0));
		train.board(10);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test 
	public void testBoardOnePassengerCarNoBoard() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		assertTrue(train.board(0)==0);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test 
	public void testBoardOnePassengerCarOneBoard() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		assertTrue(train.board(1)==0);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test 
	public void testBoardOnePassengerCarNormalBoard() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		assertTrue(train.board(15)==0);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test 
	public void testBoardOnePassengerCarMultipleNormalBoard() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.board(5);
		train.board(3);
		assertTrue(train.board(8)==0);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test 
	public void testBoardOnePassengerCarMaxBoard() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		assertTrue(train.board(20)==0);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test 
	public void testBoardOnePassengerCarMultipleMaxBoard() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.board(8);
		train.board(7);
		assertTrue(train.board(5)==0);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test 
	public void testBoardOnePassengerCarTooManyBoard() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		assertTrue(train.board(30)==10);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test 
	public void testBoardOnePassengerCarMultipleTooManyBoard() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.board(5);
		train.board(10);
		assertTrue(train.board(15)==10);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test 
	public void testBoardMultiplePassengerCarNoBoard() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,10));
		train.addCarriage(new PassengerCar(90,20));
		train.addCarriage(new PassengerCar(100,30));
		assertTrue(train.board(0)==0);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test 
	public void testBoardMultiplePassengerCarOneBoard() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,10));
		train.addCarriage(new PassengerCar(90,20));
		train.addCarriage(new PassengerCar(100,30));
		assertTrue(train.board(1)==0);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test 
	public void testBoardMultiplePassengerCarNormalBoard() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,10));
		train.addCarriage(new PassengerCar(90,20));
		train.addCarriage(new PassengerCar(100,30));
		assertTrue(train.board(45)==0);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test 
	public void testBoardMultiplePassengerCarMultipleNormalBoard() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,10));
		train.addCarriage(new PassengerCar(90,20));
		train.addCarriage(new PassengerCar(100,30));
		train.board(5);
		train.board(15);
		assertTrue(train.board(25) == 0);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test 
	public void testBoardMultiplePassengerCarMaxBoard() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,10));
		train.addCarriage(new PassengerCar(90,20));
		train.addCarriage(new PassengerCar(100,30));
		assertTrue(train.board(60)==0);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test 
	public void testBoardMultiplePassengerCarMultipleMaxBoard() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,10));
		train.addCarriage(new PassengerCar(90,20));
		train.addCarriage(new PassengerCar(100,30));
		train.board(20);
		train.board(25);
		assertTrue(train.board(15)==0);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test 
	public void testBoardMultiplePassengerCarTooManyBoard() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,10));
		train.addCarriage(new PassengerCar(90,20));
		train.addCarriage(new PassengerCar(100,30));
		assertTrue(train.board(80)==20);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test 
	public void testBoardMultiplePassengerCarMultipleTooManyBoard() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,10));
		train.addCarriage(new PassengerCar(90,20));
		train.addCarriage(new PassengerCar(100,30));
		train.board(30);
		train.board(20);
		assertTrue(train.board(25)==15);
	}
	
	
	// ------ firstCarriage Method Tests ------
	
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testFirstCarriageEmptyTrain() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		assertEquals(train.firstCarriage(), null);
	}
	

	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testFirstCarriageOnlyLocomotive() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		Locomotive loc = new Locomotive(90,"5E");
		train.addCarriage(loc);
		assertEquals(loc,train.firstCarriage());
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testFirstCarriageMultipleCars() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		Locomotive loc = new Locomotive(90,"5E");
		train.addCarriage(loc);
		train.addCarriage(new PassengerCar(100,10));
		train.addCarriage(new PassengerCar(100,20));
		train.addCarriage(new FreightCar(80,"G"));
		train.addCarriage(new FreightCar(80,"G"));
		assertEquals(loc,train.firstCarriage());
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testFirstCarriageMultipleCarsRemove() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		Locomotive loc = new Locomotive(90,"5E");
		train.addCarriage(loc);
		train.addCarriage(new PassengerCar(100,10));
		train.addCarriage(new PassengerCar(100,20));
		train.addCarriage(new FreightCar(80,"G"));
		train.addCarriage(new FreightCar(80,"G"));
		train.removeCarriage();
		train.removeCarriage();
		train.removeCarriage();
		assertEquals(loc,train.firstCarriage());
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testFirstCarriageMultipleCarsRemoveUntilEmpty() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(100,10));
		train.addCarriage(new PassengerCar(100,20));
		train.addCarriage(new FreightCar(80,"G"));
		train.addCarriage(new FreightCar(80,"G"));
		train.removeCarriage();
		train.removeCarriage();
		train.removeCarriage();
		train.removeCarriage();
		train.removeCarriage();
		assertEquals(train.firstCarriage(), null);
	}

	
	// ------ nextCarriage Method Tests ------
	
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testNextCarriageEmptyTrainFirstCall() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		assertEquals(train.nextCarriage(), null);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testNextCarriageEmptyTrainAfterFirstCarriageMethod() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.firstCarriage();
		assertEquals(train.nextCarriage(), null);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testNextCarriageEmptyTrainNotFirstCall() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.nextCarriage();
		train.nextCarriage();
		assertEquals(train.nextCarriage(), null);
	}	

	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testNextCarriageOnlyLocomotiveFirstCall() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		Locomotive loc = new Locomotive(90,"5E");
		train.addCarriage(loc);
		assertEquals(train.nextCarriage(), loc);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testNextCarriageOnlyLocomotiveAfterFirstCarriageMethod() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.firstCarriage();
		assertEquals(train.nextCarriage(), null);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testNextCarriageOnlyLocomotiveNotFirstCall() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.nextCarriage();
		assertEquals(train.nextCarriage(), null);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testNextCarriageMultipleCarsFirstCall() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		Locomotive loc = new Locomotive(90,"5E");
		train.addCarriage(loc);
		train.addCarriage(new PassengerCar(100,10));
		train.addCarriage(new PassengerCar(100,20));
		train.addCarriage(new FreightCar(80,"G"));
		train.addCarriage(new FreightCar(80,"G"));
		assertEquals(loc,train.nextCarriage());
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testNextCarriageMultipleCarsRemove() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		Locomotive loc = new Locomotive(90,"5E");
		train.addCarriage(loc);
		train.addCarriage(new PassengerCar(100,10));
		train.addCarriage(new PassengerCar(100,20));
		train.addCarriage(new FreightCar(80,"G"));
		train.addCarriage(new FreightCar(80,"G"));
		train.removeCarriage();
		train.removeCarriage();
		train.removeCarriage();
		assertEquals(loc,train.firstCarriage());
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testNextCarriageMultipleCarsRemoveUntilEmpty() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(100,10));
		train.addCarriage(new PassengerCar(100,20));
		train.addCarriage(new FreightCar(80,"G"));
		train.addCarriage(new FreightCar(80,"G"));
		train.removeCarriage();
		train.removeCarriage();
		train.removeCarriage();
		train.removeCarriage();
		train.removeCarriage();
		assertEquals(train.firstCarriage(), null);
	}
	
	
	// ------ numberOfSeats Method Tests ------
	
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testNumberOfSeatsNoPassengerCar() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		assertTrue(train.numberOfSeats()==0);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testNumberOfSeatsOnePassengerCarNoSeats() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,0));
		assertTrue(train.numberOfSeats()==0);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testNumberOfSeatsOnePassengerCarSeats() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		assertTrue(train.numberOfSeats()==20);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testNumberOfSeatsOnePassengerCarSeatsSomeOccupied() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.board(10);
		assertTrue(train.numberOfSeats()==20);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testNumberOfSeatsOnePassengerCarSeatsFreightCars() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.addCarriage(new FreightCar(80,"G"));
		train.addCarriage(new FreightCar(85,"R"));
		train.addCarriage(new FreightCar(90,"D"));
		assertTrue(train.numberOfSeats()==20);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testNumberOfSeatsManyPassengerCarNoSeats() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,0));
		train.addCarriage(new PassengerCar(90,0));
		train.addCarriage(new PassengerCar(100,0));
		assertTrue(train.numberOfSeats()==0);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testNumberOfSeatsManyPassengerCarSeatsNoSeats() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.addCarriage(new PassengerCar(90,0));
		train.addCarriage(new PassengerCar(100,15));
		assertTrue(train.numberOfSeats()==35);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testNumberOfSeatsManyPassengerCarSeatsNoSeatsSomeOccupied() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.addCarriage(new PassengerCar(90,0));
		train.addCarriage(new PassengerCar(100,15));
		train.board(25);
		assertTrue(train.numberOfSeats()==35);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testNumberOfSeatsManyPassengerCarSeats() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.addCarriage(new PassengerCar(90,10));
		train.addCarriage(new PassengerCar(100,15));
		assertTrue(train.numberOfSeats()==45);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testNumberOfSeatsManyPassengerCarSeatsSomeOccupied() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.addCarriage(new PassengerCar(90,10));
		train.addCarriage(new PassengerCar(100,15));
		train.board(30);
		assertTrue(train.numberOfSeats()==45);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testNumberOfSeatsManyPassengerCarAndFreightCarsSeats() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.addCarriage(new PassengerCar(90,10));
		train.addCarriage(new PassengerCar(100,15));
		train.addCarriage(new FreightCar(60,"G"));
		train.addCarriage(new FreightCar(70,"R"));
		train.addCarriage(new FreightCar(80,"D"));
		assertTrue(train.numberOfSeats()==45);
	}
	
	
	// ------ numberOnBoard Method Tests ------
	
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testNumberOnBoardNoPassengerCars() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		assertTrue(train.numberOnBoard()== 0);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testNumberOnBoardOnePassengerCarNoBoarded() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		assertTrue(train.numberOnBoard()== 0);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testNumberOnBoardOnePassengerCarSomeBoarded() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.board(15);
		assertTrue(train.numberOnBoard()== 15);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testNumberOnBoardOnePassengerCarMaxBoarded() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.board(20);
		assertTrue(train.numberOnBoard()== 20);
	}
	
	/**
	 * 
	 * @author Cameron
	 */
	@Test
	public void testNumberOnBoardOnePassengerCarTooManyBoarded() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.board(35);
		assertTrue(train.numberOnBoard()== 20);
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testNumberOnBoardOnePassengerCarFreightCarsSomeBoarded() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.addCarriage(new FreightCar(80,"G"));
		train.addCarriage(new FreightCar(85,"R"));
		train.addCarriage(new FreightCar(90,"D"));
		train.board(15);
		assertTrue(train.numberOnBoard()== 15);
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testNumberOnBoardManyPassengersCarNoBoarded() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.addCarriage(new PassengerCar(90,30));
		train.addCarriage(new PassengerCar(100,40));
		assertTrue(train.numberOnBoard()== 0);
	}

	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testNumberOnBoardManyPassengerCarsOneBoarded() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.addCarriage(new PassengerCar(90,30));
		train.addCarriage(new PassengerCar(100,40));
		train.board(1);
		assertTrue(train.numberOnBoard()== 1);
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testNumberOnBoardManyPassengerCarsManyBoarded() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.addCarriage(new PassengerCar(90,30));
		train.addCarriage(new PassengerCar(100,40));
		train.board(60);
		assertTrue(train.numberOnBoard()== 60);
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testNumberOnBoardManyPassengerCarsMaxBoarded() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.addCarriage(new PassengerCar(90,30));
		train.addCarriage(new PassengerCar(100,40));
		train.board(90);
		assertTrue(train.numberOnBoard()== 90);
	}
		
	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testNumberOnBoardManyPassengerCarsTooManyBoarded()
			throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90, "5E"));
		train.addCarriage(new PassengerCar(80, 20));
		train.addCarriage(new PassengerCar(90, 30));
		train.addCarriage(new PassengerCar(100, 40));
		train.board(100);
		assertTrue(train.numberOnBoard()== 90);
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testNumberOnBoardManyPassengerCarsFreightCarsManyBoarded() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.addCarriage(new PassengerCar(90,30));
		train.addCarriage(new PassengerCar(100,40));
		train.addCarriage(new FreightCar(80,"G"));
		train.addCarriage(new FreightCar(85,"R"));
		train.addCarriage(new FreightCar(90,"D"));
		train.board(60);
		assertTrue(train.numberOnBoard()== 60);
	}
	
	
	// ------ removeCarriage Method Tests ------
	

	/*
	 * 
	 * @author Cameron
	 */
	@Test (expected = TrainException.class)
	public void testRemoveCarriageNoRollingStock() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.removeCarriage();
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test (expected = TrainException.class)
	public void testRemoveCarriagePassengerCarPassengerOnBoard() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.board(1);
		train.removeCarriage();
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test (expected = TrainException.class)
	public void testRemoveCarriagePassengerCarPassengersOnBoard() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.board(15);
		train.removeCarriage();
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test (expected = TrainException.class)
	public void testRemoveCarriageFreightCarPassengerOnBoard() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.board(1);
		train.removeCarriage();
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test (expected = TrainException.class)
	public void testRemoveCarriageFreightCarPassengersOnBoard() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.board(15);
		train.removeCarriage();
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testRemoveCarriageLocomotive() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.removeCarriage();
	}

	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testRemoveCarriageOnePassengerCar() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.removeCarriage();
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testRemoveCarriageMultiplePassengerCars() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.addCarriage(new PassengerCar(80,20));
		train.addCarriage(new PassengerCar(80,20));
		train.removeCarriage();
		train.removeCarriage();
		train.removeCarriage();
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testRemoveCarriageOneFreightCar() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new FreightCar(80,"G"));
		train.removeCarriage();
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testRemoveCarriageMultipleFreightCars() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new FreightCar(80,"G"));
		train.addCarriage(new FreightCar(85,"R"));
		train.addCarriage(new FreightCar(90,"D"));
		train.removeCarriage();
		train.removeCarriage();
		train.removeCarriage();
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testRemoveCarriageMultiplePassengerAndFreightCars() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,10));
		train.addCarriage(new PassengerCar(90,20));
		train.addCarriage(new PassengerCar(100,30));
		train.addCarriage(new FreightCar(80,"G"));
		train.addCarriage(new FreightCar(85,"R"));
		train.addCarriage(new FreightCar(90,"D"));
		train.removeCarriage();
		train.removeCarriage();
		train.removeCarriage();
		train.removeCarriage();
		train.removeCarriage();
		train.removeCarriage();
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testRemoveCarriageMultiplePassengerAndFreightCarsAndLocomotive() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,10));
		train.addCarriage(new PassengerCar(90,20));
		train.addCarriage(new PassengerCar(100,30));
		train.addCarriage(new FreightCar(80,"G"));
		train.addCarriage(new FreightCar(85,"R"));
		train.addCarriage(new FreightCar(90,"D"));
		train.removeCarriage();
		train.removeCarriage();
		train.removeCarriage();
		train.removeCarriage();
		train.removeCarriage();
		train.removeCarriage();
		train.removeCarriage();
	}
	
	
	// ------ toString Method Tests ------
	
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testToStringEmptyTrain() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		assertEquals(train.toString(), "");
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testToStringOnlyLocomotive() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		assertEquals(train.toString(), "Loco(5E)");
	}

	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testToStringOnePassengerCar() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		assertEquals(train.toString(), "Loco(5E)-Passenger(0/20)");
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testToStringManyPassengerCar() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.addCarriage(new PassengerCar(80,30));
		train.addCarriage(new PassengerCar(80,15));
		assertEquals(train.toString(), "Loco(5E)-Passenger(0/20)-Passenger(0/30)-Passenger(0/15)");
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testToStringOneFreightCar() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new FreightCar(80,"G"));
		assertEquals(train.toString(), "Loco(5E)-Freight(G)");
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testToStringManyFreightCar() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new FreightCar(80,"G"));
		train.addCarriage(new FreightCar(80,"R"));
		train.addCarriage(new FreightCar(80,"D"));
		assertEquals(train.toString(), "Loco(5E)-Freight(G)-Freight(R)-Freight(D)");
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testToStringManyPassengerFreightCar() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.addCarriage(new PassengerCar(80,30));
		train.addCarriage(new PassengerCar(80,15));
		train.addCarriage(new FreightCar(80,"G"));
		train.addCarriage(new FreightCar(80,"R"));
		train.addCarriage(new FreightCar(80,"D"));
		assertEquals(train.toString(), "Loco(5E)-Passenger(0/20)-Passenger(0/30)-Passenger(0/15)-Freight(G)-Freight(R)-Freight(D)");
	}
	
	
	// ------ trainCanMove Method Tests ------
		
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testTrainCanMoveNoRollingStock() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		assertEquals(train.trainCanMove(), true);
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testTrainCanMoveLocomotiveValid() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		assertEquals(train.trainCanMove(), true);
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testTrainCanMoveLocomotiveValidLowerBound() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(200,"2E"));
		assertEquals(train.trainCanMove(), true);
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testTrainCanMoveLocomotiveTooHeavy() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(300,"2E"));
		assertEquals(train.trainCanMove(), false);
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testTrainCanMoveLocomotiveJustTooHeavy() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(201,"2E"));
		assertEquals(train.trainCanMove(), false);
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testTrainCanMovePassengerCarsValid() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(90,"5E"));
		train.addCarriage(new PassengerCar(80,20));
		train.addCarriage(new PassengerCar(90,30));
		train.addCarriage(new PassengerCar(100,40));
		assertEquals(train.trainCanMove(), true);
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testTrainCanMovePassengerCarsValidLowerBound() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(100,"4E"));
		train.addCarriage(new PassengerCar(100,20));
		train.addCarriage(new PassengerCar(100,30));
		train.addCarriage(new PassengerCar(100,40));
		assertEquals(train.trainCanMove(), true);
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testTrainCanMovePassengerCarsTooHeavy() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(100,"4E"));
		train.addCarriage(new PassengerCar(100,20));
		train.addCarriage(new PassengerCar(110,30));
		train.addCarriage(new PassengerCar(120,40));
		train.addCarriage(new PassengerCar(130,40));
		assertEquals(train.trainCanMove(), false);
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testTrainCanMovePassengerCarsJustTooHeavy() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(100,"4E"));
		train.addCarriage(new PassengerCar(100,20));
		train.addCarriage(new PassengerCar(100,30));
		train.addCarriage(new PassengerCar(101,40));
		assertEquals(train.trainCanMove(), false);
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testTrainCanMoveFreightCarsValid() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(100,"8E"));
		train.addCarriage(new FreightCar(70,"G"));
		train.addCarriage(new FreightCar(80,"R"));
		train.addCarriage(new FreightCar(90,"D"));
		assertEquals(train.trainCanMove(), true);
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testTrainCanMoveFreightCarsValidLowerBound() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(100,"4E"));
		train.addCarriage(new FreightCar(100,"G"));
		train.addCarriage(new FreightCar(100,"R"));
		train.addCarriage(new FreightCar(100,"D"));
		assertEquals(train.trainCanMove(), true);
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testTrainCanMoveFreightCarsTooHeavy() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(100,"2E"));
		train.addCarriage(new FreightCar(70,"G"));
		train.addCarriage(new FreightCar(80,"R"));
		train.addCarriage(new FreightCar(90,"D"));
		assertEquals(train.trainCanMove(), false);
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testTrainCanMoveFreightCarsJustTooHeavy() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(100,"4E"));
		train.addCarriage(new FreightCar(100,"G"));
		train.addCarriage(new FreightCar(100,"R"));
		train.addCarriage(new FreightCar(101,"D"));
		assertEquals(train.trainCanMove(), false);
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testTrainCanMovePassengerFreightCarsValid() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(100,"8E"));
		train.addCarriage(new PassengerCar(80,20));
		train.addCarriage(new PassengerCar(90,30));
		train.addCarriage(new FreightCar(70,"G"));
		train.addCarriage(new FreightCar(80,"R"));
		assertEquals(train.trainCanMove(), true);
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testTrainCanMovePassengerFreightCarsLowerBound() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(100,"4E"));
		train.addCarriage(new PassengerCar(100,20));
		train.addCarriage(new PassengerCar(100,30));
		train.addCarriage(new FreightCar(50,"G"));
		train.addCarriage(new FreightCar(50,"R"));
		assertEquals(train.trainCanMove(), true);
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testTrainCanMovePassengerFreightCarsTooHeavy() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(100,"2E"));
		train.addCarriage(new PassengerCar(100,20));
		train.addCarriage(new PassengerCar(100,30));
		train.addCarriage(new FreightCar(50,"G"));
		train.addCarriage(new FreightCar(50,"R"));
		assertEquals(train.trainCanMove(), false);
	}
	
	/*
	 * 
	 * @author Cameron
	 */
	@Test
	public void testTrainCanMovePassengerFreightCarsJustTooHeavy() throws TrainException {
		DepartingTrain train = new DepartingTrain();
		train.addCarriage(new Locomotive(100,"4E"));
		train.addCarriage(new PassengerCar(100,20));
		train.addCarriage(new PassengerCar(100,30));
		train.addCarriage(new FreightCar(50,"G"));
		train.addCarriage(new FreightCar(51,"R"));
		assertEquals(train.trainCanMove(), false);
	}
	

}
