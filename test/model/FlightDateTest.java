package model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * <b> Laboratorio unidad 4 </b>
 * @author César Canales <br>
 * Universidad Icesi
 */
class FlightDateTest {
	
	/**
	 * The association with the flight date.
	 */
	private FlightDate fD;
	
	/**
	 * The initialization of a scenery.
	 */
	public void setupScenery1() {
		
	}
	
	/**
	 * The initialization of a scenery.
	 */
	public void setupScenery2() {
		fD = new FlightDate(2018, 10, 23);
	}
	
	/**
	 * This function tests the flight date constructor.
	 */
	@Test
	void flightDateTest() {
		setupScenery1();
		int day = 2;
		int month = 11;
		int year = 2018;
		fD = new FlightDate(year, month, day);
		
		assertNotNull("The date is null", fD);
		assertTrue("The day isn't assigned correctly", day == fD.getDayOfMonth());
		assertTrue("The month isn't assigned correctly", month == fD.getMonth());
		assertTrue("The year isn't assigned correctly", year == fD.getYear());
	}

	/**
	 * This function tests whether or not the dates are being compared correctly.
	 */
	@Test
	void compareToTest() {
		setupScenery2();
		int year = 2019;
		int month = 8;
		int dayOfMonth = 17;
		FlightDate testDate = new FlightDate(year, month, dayOfMonth);
		assertTrue("The comparing is not being done correctly", fD.compareTo(testDate) < 0);
	}
	
	/**
	 * This function tests whether or not the dates are being compared correctly.
	 */
	@Test
	void equalsTest() {
		setupScenery2();
		int year = 2019;
		int month = 8;
		int dayOfMonth = 17;
		FlightDate testDate = new FlightDate(year, month, dayOfMonth);
		assertTrue("The comparing is not being done correctly", fD.equals(testDate));
	}
	
}
