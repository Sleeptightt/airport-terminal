package model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

/**
 * <b> Laboratorio unidad 4 </b>
 * @author César Canales <br>
 * Universidad Icesi
 */
class AirportTest {

	/**
	 * The association with the airport.
	 */
	private Airport a;
	
	/**
	 * The initialization of a scenery.
	 */
	public void setupScenery1() {
		
	}
	
	/**
	 * The initialization of a scenery.
	 */
	public void setupScenery2() {
		a = new Airport();
	}
	
	/**
	 * This function tests the airport constructor.
	 */
	@Test
	void airportTest() {
		setupScenery1();
		a = new Airport();
		assertNotNull("The list of flights is null", a.getFLights());
	}

	/**
	 * This function tests the generate flights function.
	 */
	@Test
	void generateFlightsTest() {
		setupScenery2();
		int n = 10;
		try {
			a.generateFlights(n);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue("The flights weren't generated", a.getFLights().size() == 10);
	}
	
	/**
	 * This function tests the bubble sort algorithm.
	 */
	@Test
	void bubbleSortTest() {
		setupScenery2();
		int n = 10;
		try {
			a.generateFlights(n);
		} catch (IOException e) {
			e.printStackTrace();
		}
		a.bubbleSort(new Comparator<Flight>() {

			@Override
			public int compare(Flight a, Flight b) {
				int comparison = 0;
				if (!a.getDestination().equals(b.getDestination()))
					comparison = a.getDestination().compareTo(b.getDestination());
				return comparison;
			}
			
		});
		for (int i = 0; i < a.getFLights().size()-1; i++) {
			assertTrue("The flights weren't sorted", a.getFLights().get(i).getDestination().compareTo(a.getFLights().get(i+1).getDestination()) <= 0);
		}
		
	}
	
	/**
	 * This function tests the selection sort algorithm.
	 */
	@Test
	void selectionSortTest() {
		setupScenery2();
		int n = 10;
		try {
			a.generateFlights(n);
		} catch (IOException e) {
			e.printStackTrace();
		}
		a.selectionSort(new Comparator<Flight>() {

			@Override
			public int compare(Flight a, Flight b) {
				int comparison = 0;
				if (!a.getDestination().equals(b.getDestination()))
					comparison = a.getDestination().compareTo(b.getDestination());
				return comparison;
			}
			
		});
		for (int i = 0; i < a.getFLights().size()-1; i++) {
			assertTrue("The flights weren't sorted", a.getFLights().get(i).getDestination().compareTo(a.getFLights().get(i+1).getDestination()) <= 0);
		}
		
	}
	
	/**
	 * This function tests the insertion sort algorithm.
	 */
	@Test
	void insertionSortTest() {
		setupScenery2();
		int n = 10;
		try {
			a.generateFlights(n);
		} catch (IOException e) {
			e.printStackTrace();
		}
		a.insertionSort(new Comparator<Flight>() {

			@Override
			public int compare(Flight a, Flight b) {
				int comparison = 0;
				if (!a.getDestination().equals(b.getDestination()))
					comparison = a.getDestination().compareTo(b.getDestination());
				return comparison;
			}
			
		});
		for (int i = 0; i < a.getFLights().size()-1; i++) {
			assertTrue("The flights weren't sorted", a.getFLights().get(i).getDestination().compareTo(a.getFLights().get(i+1).getDestination()) <= 0);
		}
		
	}
}
