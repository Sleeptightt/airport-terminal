package model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * <b> Laboratorio unidad 4 </b>
 * @author César Canales <br>
 * Universidad Icesi
 */
class FlightTest {

	/**
	 * The association with the flight.
	 */
	private Flight f;
	
	/**
	 * The initialization of a scenery.
	 */
	public void setupScenery1() {
		
	}
	
	/**
	 * The initialization of a scenery.
	 */
	public void setupScenery2() {
		f = new Flight(Flight.COPA, "CO9821", "New York", 9, new FlightDate(2018, 3, 15), new FlightTime(2, 32, FlightTime.AM));
	}
	
	/**
	 * This function tests the flight constructor.
	 */
	@Test
	void flightTest() {
		setupScenery1();
		String airline = Flight.AVIANCA;
		String flightNumber = "AV2301";
		String destination = "Beijing";
		int boardingGate = 12;
		FlightDate flightDate = new FlightDate(2018, 4, 18);
		FlightTime flightTime = new FlightTime(11, 23, FlightTime.AM);
		
		f = new Flight(airline, flightNumber, destination, boardingGate, flightDate, flightTime);
		
		assertNotNull("The flight is null", f);
		assertTrue("The airline is not assigned correctly", airline.equals(f.getAirline()));
		assertTrue("The flight number is not assigned correctly", flightNumber.equals(f.getFlightNumber()));
		assertTrue("The destination is not assigned correctly", destination.equals(f.getDestination()));
		assertTrue("The boarding gate is not assigned correctly", boardingGate == f.getBoardingGate());
		assertTrue("The flight date is not assigned correctly", flightDate.equals(f.getFlightDate()));
		assertTrue("The flight time is not assigned correctly", flightTime.equals(f.getFlightTime()));
	}

	/**
	 * This function tets whether or not the flights are being compared correctly.
	 */
	@Test
	void compareToTest() {
		setupScenery2();
		Flight testFlight = new Flight(Flight.COPA, "CO9821", "New York", 9, new FlightDate(2018, 3, 15), new FlightTime(2, 32, FlightTime.AM));
		assertTrue("The comparing is not being done correctly", f.compareTo(testFlight) == 0);
	}
	
	/**
	 * This functions tests the date linear search algorithm.
	 */
	@Test
	void linearSearchDateTest() {
		setupScenery2();
		Airport a = new Airport();
		a.generateFlights(11);
		Flight f1 = a.getFLights().get(10);
		int pos = f.linearSearchDate(f1.getFlightDate(), a.getFirst());
		assertTrue("The search isn't right", a.getFLights().get(pos).getFlightDate().equals(f1.getFlightDate()));
	}
	
	/**
	 * This functions tests the time linear search algorithm.
	 */
	@Test
	void linearSearchTimeTest() {
		setupScenery2();
		Airport a = new Airport();
		a.generateFlights(11);
		Flight f1 = a.getFLights().get(10);
		int pos = f.linearSearchTime(f1.getFlightTime(), a.getFirst());
		assertTrue("The search isn't right", a.getFLights().get(pos).getFlightTime().equals(f1.getFlightTime()));
	}
	
	/**
	 * This functions tests the airline linear search algorithm.
	 */
	@Test
	void linearSearchAirlineTest() {
		setupScenery2();
		Airport a = new Airport();
		a.generateFlights(11);
		Flight f1 = a.getFLights().get(10);
		int pos = f.linearSearchAirline(f1.getAirline(), a.getFirst());
		assertTrue("The search isn't right", a.getFLights().get(pos).getAirline().equals(f1.getAirline()));
	}
	
	/**
	 * This functions tests the flight number linear search algorithm.
	 */
	@Test
	void linearSearchFlightNumberTest() {
		setupScenery2();
		Airport a = new Airport();
		a.generateFlights(11);
		Flight f1 = a.getFLights().get(10);
		int pos = f.linearSearchFlightNumber(f1.getFlightNumber(), a.getFirst());
		assertTrue("The search isn't right", a.getFLights().get(pos).getFlightNumber().equals(f1.getFlightNumber()));
	}
	
	/**
	 * This functions tests the destination linear search algorithm.
	 */
	@Test
	void linearSearchDestinationTest() {
		setupScenery2();
		Airport a = new Airport();
		a.generateFlights(11);
		Flight f1 = a.getFLights().get(10);
		int pos = f.linearSearchDestination(f1.getDestination(), a.getFirst());
		assertTrue("The search isn't right", a.getFLights().get(pos).getDestination().equals(f1.getDestination()));
	}
	
	/**
	 * This functions tests the boarding gate linear search algorithm.
	 */
	@Test
	void linearSearchBoardingGateTest() {
		setupScenery2();
		Airport a = new Airport();
		a.generateFlights(11);
		Flight f1 = a.getFLights().get(10);
		int pos = f.linearSearchBoardingGate(f1.getBoardingGate(), a.getFirst());
		assertTrue("The search isn't right", a.getFLights().get(pos).getBoardingGate() == (f1.getBoardingGate()));
	}
	
}
