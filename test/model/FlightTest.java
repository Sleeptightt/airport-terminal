package model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

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
	 * This function tests whether or not the flights are being compared correctly.
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
		try {
			a.generateFlights(11);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Flight f1 = a.getFLights().get(10);
		int pos = f.linearSearchDate(a.getFLights().toArray(new Flight[a.getFLights().size()]), f1.getFlightDate());
		assertTrue("The search isn't right", a.getFLights().get(pos).equals(f1));
	}
	
	/**
	 * This functions tests the time linear search algorithm.
	 */
	@Test
	void linearSearchTimeTest() {
		setupScenery2();
		Airport a = new Airport();
		try {
			a.generateFlights(11);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Flight f1 = a.getFLights().get(10);
		int pos = f.linearSearchTime(a.getFLights().toArray(new Flight[a.getFLights().size()]), f1.getFlightTime());
		assertTrue("The search isn't right", a.getFLights().get(pos).equals(f1));
	}
	
	/**
	 * This functions tests the airline binary search algorithm.
	 */
	@Test
	void binarySearchAirlineTest() {
		setupScenery2();
		Airport a = new Airport();
		try {
			a.generateFlights(11);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Flight f1 = a.getFLights().get(10);
		Flight[] flights = a.getFLights().toArray(new Flight[a.getFLights().size()]);
		Arrays.sort(flights, new Comparator<Flight>() {
			@Override
			public int compare(Flight a, Flight b) {
				int comparison = 0;
				if (!a.getAirline().equals(b.getAirline()))
					comparison = a.getAirline().compareTo(b.getAirline());
				return comparison;
			}
		});
		int pos = f.binarySearchAirline(flights, f1.getAirline(), 0, a.getFLights().size()-1);
		assertTrue("The search isn't right", flights[pos].getAirline().equals(f1.getAirline()));
	}
	
	/**
	 * This functions tests the flight number binary search algorithm.
	 */
	@Test
	void binarySearchFlightNumberTest() {
		setupScenery2();
		Airport a = new Airport();
		try {
			a.generateFlights(11);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Flight f1 = a.getFLights().get(10);
		Flight[] flights = a.getFLights().toArray(new Flight[a.getFLights().size()]);
		Arrays.sort(flights, new Comparator<Flight>() {
			@Override
			public int compare(Flight a, Flight b) {
				int comparison = 0;
				if (!a.getFlightNumber().equals(b.getFlightNumber()))
					comparison = a.getFlightNumber().compareTo(b.getFlightNumber());
				return comparison;
			}
		});
		int pos = f.binarySearchFlightNumber(flights, f1.getFlightNumber(), 0, a.getFLights().size()-1);
		assertTrue("The search isn't right", flights[pos].getFlightNumber().equals(f1.getFlightNumber()));
	}
	
	/**
	 * This functions tests the destination binary search algorithm.
	 */
	@Test
	void binarySearchDestinationTest() {
		setupScenery2();
		Airport a = new Airport();
		try {
			a.generateFlights(11);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Flight f1 = a.getFLights().get(10);
		Flight[] flights = a.getFLights().toArray(new Flight[a.getFLights().size()]);
		Arrays.sort(flights, new Comparator<Flight>() {
			@Override
			public int compare(Flight a, Flight b) {
				int comparison = 0;
				if (!a.getDestination().equals(b.getDestination()))
					comparison = a.getDestination().compareTo(b.getDestination());
				return comparison;
			}
		});
		int pos = f.binarySearchDestination(flights, f1.getDestination(), 0, a.getFLights().size()-1);
		assertTrue("The search isn't right", flights[pos].getDestination().equals(f1.getDestination()));
	}
	
	/**
	 * This functions tests the boarding gate binary search algorithm.
	 */
	@Test
	void binarySearchBoardingGateTest() {
		setupScenery2();
		Airport a = new Airport();
		try {
			a.generateFlights(11);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Flight f1 = a.getFLights().get(10);
		Flight[] flights = a.getFLights().toArray(new Flight[a.getFLights().size()]);
		Arrays.sort(flights, new Comparator<Flight>() {
			@Override
			public int compare(Flight a, Flight b) {
				int comparison = 0;
				if (a.getBoardingGate() != b.getBoardingGate())
					comparison = a.getBoardingGate() - b.getBoardingGate();
				return comparison;
			}
		});
		int pos = f.binarySearchBoardingGate(flights, f1.getBoardingGate(), 0, a.getFLights().size()-1);
		assertTrue("The search isn't right", flights[pos].getBoardingGate() == f1.getBoardingGate());
	}
	
}
