package model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * <b> Laboratorio unidad 4 </b>
 * @author César Canales <br>
 * Universidad Icesi
 */
class FlightTimeTest {
	
	/**
	 * The association with the flight time.
	 */
	private FlightTime fT;
	
	/**
	 * The initialization of a scenery.
	 */
	public void setupScenery1() {
		
	}
	
	/**
	 * The initialization of a scenery.
	 */
	public void setupScenery2() {
		fT = new FlightTime(11, 36, FlightTime.PM);
	}
	
	/**
	 * This function tests the flight time constructor.
	 */
	@Test
	void flightTimeTest() {
		setupScenery1();
		int hour = 2;
		int minute = 11;
		String timeType = FlightTime.AM;
		fT = new FlightTime(hour, minute, timeType);
		
		assertNotNull("The date is null", fT);
		assertTrue("The hour isn't assigned correctly", hour == fT.getHour());
		assertTrue("The minutes aren't assigned correctly", minute == fT.getMinute());
		assertTrue("The time type isn't assigned correctly", timeType.equals(fT.getTimeType()));
	}
	
	/**
	 * This function tests whether or not the times are being compared correctly.
	 */
	@Test
	void compareToTest() {
		setupScenery2();
		String timeType = FlightTime.AM;
		int hour = 11;
		int minute = 45;
		FlightTime testTime = new FlightTime(hour, minute, timeType);
		assertTrue("The comparing is not being done correctly", fT.compareTo(testTime) > 0);
	}
	
	/**
	 * This function tests whether or not the times are being compared correctly.
	 */
	@Test
	void equalsTest() {
		setupScenery2();
		String timeType = FlightTime.AM;
		int hour = 11;
		int minute = 45;
		FlightTime testTime = new FlightTime(hour, minute, timeType);
		assertTrue("The comparing is not being done correctly", !fT.equals(testTime));
	}
}
