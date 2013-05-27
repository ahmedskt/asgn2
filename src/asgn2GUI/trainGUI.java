package asgn2GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.FreightCar;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;
import asgn2RollingStock.RollingStock;
import asgn2Train.DepartingTrain;

/**
 * This class will implement a GUI for the train, using the classes from
 * assignment 2 - part 1
 * 
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
 * 
 */
public class trainGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = -7031008862559936404L;
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 700;

	// The GUI uses a BorderLayout as a main layout, without the WEST space
	private JScrollPane trainPanel = new JScrollPane(); // NORTH space
	// This panel will take all the constructed train carriages, and in addition
	// this will be on the train panel
	private JPanel trainPanelHelper = new JPanel();
	private JPanel driverPanel = new JPanel(); // CENTER space
	private JPanel conductorPanel = new JPanel(); // EAST space
	private JPanel resetPanel = new JPanel(); // SOUTH space

	private JButton btnAddLocomotive, btnAddPassengerCar, btnAddFreightCar,
			btnRemoveCarriage, btnReset;
	private JButton btnBoard, btnAlight;

	private JTextArea trainSummary, passengersSummary;

	private int totalWeight = 0; // train total weight
	private int unBoardedPassengers = 0;

	private DepartingTrain trainGUI;

	/**
	 * This function will set the title and call the createGUI method
	 * 
	 * @param title
	 */
	public trainGUI(String title) {
		super(title);
		createGUI();
	}

	/**
	 * This function will create all the graphics components
	 */
	private void createGUI() {
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// The GUI uses a BorderLayout as a main layout, without the WEST space
		setLayout(new BorderLayout());

		// Sets the preferred sizes for each of the panels
		trainPanel.setPreferredSize(new Dimension(10, 200));
		driverPanel.setPreferredSize(new Dimension(10, 400));
		conductorPanel.setPreferredSize(new Dimension(350, 200));
		resetPanel.setPreferredSize(new Dimension(10, 100));

		createAllButtonsAndTextAreas();

		setTrainPanel();
		setDriverPanel();
		setconductorPanel();
		setResetPanel();

		addMainPanels();

		repaint();

	}

	/**
	 * Sets the Reset Panel Contains a reset button
	 * 
	 * @author Ahmed
	 */
	private void setResetPanel() {
		// Sets the border title
		resetPanel.setBorder(new TitledBorder("Reset the Train"));
		resetPanel.add(btnReset);
	}

	/**
	 * Sets the conductor's controller panel Contains a passengers summary text
	 * area, a board button and an alight button which is not enabled
	 * 
	 */
	private void setconductorPanel() {
		// Sets the border title
		conductorPanel
				.setBorder(new TitledBorder("Conductor Controller Panel"));

		// Adds scrolling functionality to the Passengers Summary text area
		JScrollPane passengersSummaryScroll = new JScrollPane(passengersSummary);
		passengersSummaryScroll.setPreferredSize(new Dimension(300, 200));
		passengersSummaryScroll.setMinimumSize(new Dimension(300, 200));

		conductorPanel.add(passengersSummaryScroll);
		conductorPanel.add(btnBoard);
		conductorPanel.add(btnAlight);

	}

	/**
	 * Sets the driver's controller panel Contains a train carriages summary
	 * text area, three buttons for adding each type of carriage, and a button
	 * for removing the last carriage. All of these are seated in a
	 * GridBagLayout
	 * 
	 * @author Ahmed
	 */
	private void setDriverPanel() {
		// Sets the border title
		driverPanel.setBorder(new TitledBorder("Driver Controller Panel"));
		GridBagLayout layout = new GridBagLayout();
		driverPanel.setLayout(layout);

		// Constraints for all of the Driver Controller Panel components
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.weightx = 100;
		constraints.weighty = 100;
		constraints.insets = new Insets(5, 5, 5, 5);

		// Adds scrolling functionality to the Train Summary text area
		JScrollPane trainSummaryScroll = new JScrollPane(trainSummary);
		trainSummaryScroll.setPreferredSize((new Dimension(400, 600)));
		trainSummaryScroll.setMinimumSize(new Dimension(300, 600));

		// Adds the components to the Driver Panel
		addToPanel(driverPanel, trainSummaryScroll, constraints, 0, 0, 1, 2);
		addToPanel(driverPanel, btnAddLocomotive, constraints, 5, 0, 1, 1);
		addToPanel(driverPanel, btnAddPassengerCar, constraints, 6, 0, 1, 1);

		addToPanel(driverPanel, btnAddFreightCar, constraints, 5, 1, 1, 1);
		addToPanel(driverPanel, btnRemoveCarriage, constraints, 6, 1, 1, 1);

	}

	/**
	 * Creates all buttons and text areas with a specific titles, color and
	 * dimensions
	 * 
	 */
	private void createAllButtonsAndTextAreas() {
		// The four buttons for adding/removing carriages
		btnAddLocomotive = createButton("Add Locomotive", Color.RED, 100, 100);
		btnAddPassengerCar = createButton("Add PassengerCar", Color.GREEN, 100,
				100);
		btnAddFreightCar = createButton("Add FreightCar", Color.YELLOW, 100,
				100);
		btnRemoveCarriage = createButton("Remove Carriage", Color.LIGHT_GRAY,
				100, 100);

		btnReset = createButton("Start", Color.LIGHT_GRAY, 300, 50);
		btnReset.setEnabled(true); // All buttons are disabled initially except
									// for the start button
		btnBoard = createButton("Board", Color.LIGHT_GRAY, 100, 100);
		btnAlight = createButton("Alight", Color.LIGHT_GRAY, 100, 100);

		trainSummary = createTextArea("Train Summary");
		passengersSummary = createTextArea("Passengers Summary");
	}

	/**
	 * Sets the train panel
	 */
	private void setTrainPanel() {
		// Sets the border title
		trainPanel.setBorder(new TitledBorder("Train Configuration"));
	}

	/**
	 * Adds the main panels to the BorderLayout
	 */
	private void addMainPanels() {
		this.getContentPane().add(trainPanel, BorderLayout.NORTH);
		this.getContentPane().add(driverPanel, BorderLayout.CENTER);
		this.getContentPane().add(conductorPanel, BorderLayout.EAST);
		this.getContentPane().add(resetPanel, BorderLayout.SOUTH);

	}

	/**
	 * 
	 * A convenience method to add a component to given grid bag layout
	 * locations. Code due to Cay Horstmann
	 * 
	 * @param component
	 *            the component to add
	 * @param constraints
	 *            the grid bag constraints to use
	 * @param x_position
	 *            the x grid position
	 * @param y_position
	 *            the y grid position
	 * @param width
	 *            the grid width of the component
	 * @param height
	 *            the grid height of the component
	 */
	private void addToPanel(JPanel panel, Component component,
			GridBagConstraints constraints, int x_position, int y_position,
			int width, int height) {
		constraints.gridx = x_position;
		constraints.gridy = y_position;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		panel.add(component, constraints);
	}

	/**
	 * Create a JButton object with a specific title, color, width and height,
	 * and store it in a local variable Add the frame as an actionListener
	 * Return the JButton object
	 * 
	 * @param str
	 *            the button label
	 * @param colorX
	 *            background color
	 * @param width
	 * @param height
	 * @return the JButton object
	 * 
	 */
	private JButton createButton(String str, Color colorX, int width, int height) {
		JButton btnxx = new JButton();
		btnxx.setText(str);
		btnxx.setBackground(colorX);
		btnxx.addActionListener(this);
		btnxx.setEnabled(false);
		btnxx.setPreferredSize(new Dimension(width, height));
		return btnxx;
	}

	/**
	 * Creates a text area and disable it
	 * 
	 * @param title
	 *            the text area title
	 * @return the text area
	 * 
	 */
	private JTextArea createTextArea(String title) {
		JTextArea display = new JTextArea();
		display.setEditable(false);
		display.setLineWrap(true);
		display.setFont(new Font("Arial", Font.BOLD, 12));
		display.setBorder(BorderFactory.createEtchedBorder());
		display.setBorder(BorderFactory.createTitledBorder(title));
		return display;
	}

	/**
	 * Performs a certain action (or method) after a certain button is pressed
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent src) {
		Object source = src.getSource();

		if (source == btnReset) {
			resetAllTrain();

		} else if (source == btnAddLocomotive) {
			getLocomotiveInfo();

		} else if (source == btnAddPassengerCar) {
			getPassengerCarInfo();

		} else if (source == btnAddFreightCar) {
			getFreightCarInfo();

		} else if (source == btnBoard) {
			getBoard();

		} else if (source == btnRemoveCarriage) {
			RemoveCarriage();
		}

	}

	/**
	 * Removes the last carriage from the train
	 * 
	 */
	private void RemoveCarriage() {
		// Sets the variable deleteCarriage to the first carriage and then check
		// if there are any more carriages on the train by looping through the
		// train.
		RollingStock deleteCarriage = trainGUI.firstCarriage();
		RollingStock temp = trainGUI.nextCarriage();
		while (temp != null) {
			deleteCarriage = temp;
			temp = trainGUI.nextCarriage();
		}

		try {
			trainGUI.removeCarriage();
		} catch (TrainException e) {
			// Exception if you try to remove a carriage from an empty train
			JOptionPane.showMessageDialog(this, e, "Wiring Class: Warning",
					JOptionPane.WARNING_MESSAGE);
		}

		// If there is actually a carriage to delete, reduce it's weight from
		// the train's total
		if (deleteCarriage != null) {
			totalWeight -= deleteCarriage.getGrossWeight();
		}
		showSummary();
		redrawingTrainPanel();
	}

	/**
	 * Resets the train panel after confirming the deletion.
	 */
	private void resetAllTrain() {
		if (btnReset.getText() == "Start") {
			start();
		} else {
			int selectedOption = JOptionPane
					.showConfirmDialog(
							null,
							"All the train settings will be deleted! Do you want to proceed?",
							"Wiring Message", JOptionPane.YES_NO_OPTION);
			if (selectedOption == JOptionPane.YES_OPTION) {
				resetTrainPanel();
			}
		}
	}

	/**
	 * Adds the given number of people to passenger carriages on the train. We
	 * seat the passengers in order of the passenger cars from left to right,
	 * and the method returns the number of any leftover passengers.
	 * 
	 * The number of people wishing to board the train will be specified through a MessageDialog box
	 * 
	 * @return the number of people who were unable to board the train because
	 *         they couldn't get a seat
	 * @throws TrainException
	 *             if the number of new passengers is negative
	 */
	private void getBoard() {
		String board = JOptionPane.showInputDialog(null,
				"Please enter the number of passengers to board :");
		if ((board == null) || (board.equals(""))) {
			JOptionPane.showMessageDialog(this,
					"Operation has been cancelled ", "Cancel Message",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			try {
				// Adds number of new passengers to the train and get the number of unboarded passengers 
				unBoardedPassengers += trainGUI.board(Integer.parseInt(board));
			} catch (TrainException e) {
				JOptionPane.showMessageDialog(this, e, "Wiring Class: Warning",
						JOptionPane.WARNING_MESSAGE);
				return;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Unnaceptable input type.",
						"Wiring Class: Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			showSummary();
			redrawingTrainPanel();
		}
	}

	/**
	 * Rebuilds the Train Configuration panel by deleting all the components and painting the new train configuration
	 */
	private void redrawingTrainPanel() {
		trainPanelHelper.removeAll();
		
		// Loop through the train carriages and create the carriage type depending on the rolling stock type
		RollingStock loopCarriage = trainGUI.firstCarriage();		
		while (loopCarriage != null) {
			if (loopCarriage instanceof Locomotive) {
				addButtonToTrainPanel(loopCarriage.toString(), Color.RED);
			} else if (loopCarriage instanceof FreightCar) {
				addButtonToTrainPanel(loopCarriage.toString(), Color.YELLOW);
			} else {
				addButtonToTrainPanel(loopCarriage.toString(), Color.GREEN);
			}
			loopCarriage = trainGUI.nextCarriage();
		}
		trainPanel.getViewport().add(trainPanelHelper);
	}

	/**
	 * Gets freight car information from the user using a MessageDialog box, 
	 * and cancels the operation if they do not enter any data.
	 * 
	 */
	private void getFreightCarInfo() {
		String grossWeight = JOptionPane.showInputDialog(null,
				"Please enter the Freight Car gross weight (in tonnes)");
		if ((grossWeight == null) || (grossWeight.equals(""))) {
			JOptionPane.showMessageDialog(this,
					"Operation has been cancelled ", "Cancel Message",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			String goodsType = JOptionPane.showInputDialog(null,
					"Please enter the goods type ");
			if ((goodsType == null) || (goodsType.equals(""))) {
				JOptionPane.showMessageDialog(this,
						"Operation has been cancelled ", "Cancel Message",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				AddFreightCar(grossWeight, goodsType);
			}
		}
	}

	/**
	 * Adds a freight car after getting the grossWeight and goodsType from the getFreightCarInfo method.
	 * Adds the freight car's grossWeight to the train's total weight.
	 * Draw a new button for this freight car in the Train Configuration panel
	 * 
	 * Throws an exception if they submit an invalid weight or goods type.
	 * 
	 * 
	 * @param grossWeight
	 * @param goodsType
	 * 
	 */
	private void AddFreightCar(String grossWeight, String goodsType) {
		FreightCar newFreightCar = null;
		try {
			newFreightCar = new FreightCar(Integer.parseInt(grossWeight),
					goodsType);
			trainGUI.addCarriage(newFreightCar);
		} catch (TrainException e) {
			JOptionPane.showMessageDialog(this, e, "Wiring Class: Warning",
					JOptionPane.WARNING_MESSAGE);
			return;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Unnaceptable input type.",
					"Wiring Class: Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}

		totalWeight += newFreightCar.getGrossWeight();

		showSummary();
		addButtonToTrainPanel(newFreightCar.toString(), Color.YELLOW);

	}

	/**
	 * Gets passenger car information from the user using a MessageDialog box,
	 * and cancels the operation if they do not enter any data.
	 * 
	 */
	private void getPassengerCarInfo() {
		String grossWeight = JOptionPane.showInputDialog(null,
				"Please enter the Passenger Car gross weight (in tonnes)");
		if ((grossWeight == null) || (grossWeight.equals(""))) {
			JOptionPane.showMessageDialog(this,
					"Operation has been cancelled ", "Cancel Message",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			String numberOfSeats = JOptionPane.showInputDialog(null,
					"Please enter the number of seats");
			if ((numberOfSeats == null) || (numberOfSeats.equals(""))) {
				JOptionPane.showMessageDialog(this,
						"Operation has been cancelled ", "Cancel Message",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				AddPassengerCar(grossWeight, numberOfSeats);
			}
		}
	}

	/**
	 * Adds a passenger car after getting the grossWeight and the numberOfSeats from the getPassengerCarInfo method.
	 * Adds the passenger car's grossWeight to the train's total weight.
	 * Draw a new button for this passenger car in the Train Configuration panel
	 * 
	 * Throws an exception if they submit an invalid weight or goods type.
	 * 
	 * @param grossWeight
	 * @param numberOfSeats
	 * 
	 */
	private void AddPassengerCar(String grossWeight, String numberOfSeats) {
		PassengerCar newPassengerCar = null;
		try {
			newPassengerCar = new PassengerCar(Integer.parseInt(grossWeight),
					Integer.parseInt(numberOfSeats));
			trainGUI.addCarriage(newPassengerCar);
		} catch (TrainException e) {
			JOptionPane.showMessageDialog(this, e, "Wiring Class: Warning",
					JOptionPane.WARNING_MESSAGE);
			return;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Unnaceptable input type.",
					"Wiring Class: Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}

		btnBoard.setEnabled(true);

		totalWeight += newPassengerCar.getGrossWeight();

		showSummary();
		addButtonToTrainPanel(newPassengerCar.toString(), Color.GREEN);
	}

	/**
	 * Gets locomotive information from the user's input using a MessageDialog box,
	 * and cancels the operation if they do not enter any data.
	 * 
	 */
	private void getLocomotiveInfo() {
		String grossWeight = JOptionPane.showInputDialog(null,
				"Please enter the Locomotive gross weight (in tonnes)");
		if ((grossWeight == null) || (grossWeight.equals(""))) {
			JOptionPane.showMessageDialog(this,
					"Operation has been cancelled ", "Cancel Message",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			String classification = JOptionPane.showInputDialog(null,
					"Please enter the Locomotive classification ");
			if ((classification == null) || (classification.equals(""))) {
				JOptionPane.showMessageDialog(this,
						"Operation has been cancelled ", "Cancel Message",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				AddLocomotive(grossWeight, classification);
			}
		}
	}

	/**
	 * Adds a locomotive after getting the grossWeight and the classification from the getLocomotiveInfo method.
	 * Adds the locomotive's grossWeight to the train's total weight.
	 * Draw a new button for this locomotive in the Train Configuration panel
	 * 
	 * Throws an exception if they submit an invalid weight or classification.
	 * 
	 * @param grossWeight
	 * @param classification
	 *
	 */
	private void AddLocomotive(String grossWeight, String classification) {
		Locomotive trainLocomotive = null;
		try {
			trainLocomotive = new Locomotive(Integer.parseInt(grossWeight),
					classification);
			trainGUI.addCarriage(trainLocomotive);
		} catch (TrainException e) {
			JOptionPane.showMessageDialog(this, e, "Wiring Class: Warning",
					JOptionPane.WARNING_MESSAGE);
			return;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Unnaceptable input type.",
					"Wiring Class: Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}

		// btnAddLocomotive.setEnabled(false);
		btnAddFreightCar.setEnabled(true);
		btnAddPassengerCar.setEnabled(true);
		btnRemoveCarriage.setEnabled(true);

		totalWeight = trainLocomotive.getGrossWeight();

		showSummary();
		addButtonToTrainPanel(trainLocomotive.toString(), Color.RED);
	}

	/**
	 * Adds a specific carriage button to the Train Configuration panel
	 * 
	 * @param carriageInfo - button label which is the carriage information
	 * @param carriageColor
	 * 
	 */
	private void addButtonToTrainPanel(String carriageInfo, Color carriageColor) {
		JButton button = createButton(carriageInfo, carriageColor, 150, 100);
		trainPanelHelper.add(button);
		trainPanel.getViewport().add(trainPanelHelper);
	}
	

	/**
	 * Shows a summary for both the driver and the conductor in their text areas.
	 * 
	 */
	private void showSummary() {
		int numberOnBoard = trainGUI.numberOnBoard();
		int numberOfSeats = trainGUI.numberOfSeats();

		// Get the seating capacity
		String seatingInfo = "The seating capacity of the train is : \n"
				+ (numberOfSeats - numberOnBoard);
		seatingInfo += "\n\nThe occupancy of the train is : \n" + numberOnBoard
				+ "/" + numberOfSeats;
		
		if (numberOnBoard < numberOfSeats) {
			seatingInfo += "\n\n The train is not full";
		} else {
			seatingInfo += "\n\n There are no seats available. The train is full!";
		}

		seatingInfo += "\n\n The number of excess passengers are "
				+ unBoardedPassengers;

		passengersSummary.setText(seatingInfo);
		
		

		// Get the train state
		String trainInfo;
		if (trainGUI.firstCarriage() != null) {
			trainInfo = "Train locomotive pulling power state : \n"
					+ totalWeight + "/"
					+ ((Locomotive) trainGUI.firstCarriage()).power();
		} else {
			trainInfo = "There is no locomotive!\n";
		}

		trainInfo += "\n\nTrain consists of : \n" + trainGUI.toString();

		// Check if the train is overloaded or not
		if (!trainGUI.trainCanMove()) {
			JOptionPane.showMessageDialog(this, "The train is overloaded",
					"Wiring Class: Overloaded", JOptionPane.WARNING_MESSAGE);
			trainInfo += "\n\n\n ====  The Train is Overloaded  ====";

			btnAddLocomotive.setEnabled(false);
			btnAddFreightCar.setEnabled(false);
			btnAddPassengerCar.setEnabled(false);

			btnBoard.setEnabled(false);
			btnAlight.setEnabled(false);

		} else {
			trainInfo += "\n\nThe train is not overloaded ";
			btnAddLocomotive.setEnabled(true);
			btnAddFreightCar.setEnabled(true);
			btnAddPassengerCar.setEnabled(true);

			btnBoard.setEnabled(true);
			btnAlight.setEnabled(false);
		}

		trainSummary.setText(trainInfo);
	}
	

	/**
	 * Sets all the components
	 * 
	 */
	private void start() {
		JOptionPane.showMessageDialog(this,
				"Welcome to the Train Manager System ", "Welcome Message",
				JOptionPane.INFORMATION_MESSAGE);
		// Sets the graphical components
		btnAddLocomotive.setEnabled(true);
		btnAddFreightCar.setEnabled(false);
		btnAddPassengerCar.setEnabled(false);
		btnReset.setText("Reset");
		btnAlight.setEnabled(false);
		btnBoard.setEnabled(false);
		btnRemoveCarriage.setEnabled(false);
		trainSummary.setText("");
		passengersSummary.setText("");
		
		// Sets the train
		trainGUI = new DepartingTrain();
		totalWeight = 0;
		unBoardedPassengers = 0;
	}

	/**
	 * Removes all components and starts from the beginning
	 */
	private void resetTrainPanel() {
		trainPanelHelper.removeAll();
		trainPanel.getViewport().add(trainPanelHelper);
		start();
	}

	/**
	 * Main program which instantiates the train and gives it a title
	 */
	public static void main(String[] args) {
		trainGUI train = new trainGUI("Assignment2, Part2: Train Manager..");
		train.setVisible(true);
	}

}
