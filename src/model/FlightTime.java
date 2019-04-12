package model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * <b> Laboratorio unidad 4 </b>
 * @author César Canales <br>
 * Universidad Icesi
 */
public class FlightTime implements Comparable<FlightTime>{

	/**
	 * A type of time.
	 */
	public final static String AM = "A.M";
	
	/**
	 * A type of time.
	 */
	public final static String PM = "P.M";
	
	/**
	 * The hour that composes the time.
	 */
	private int hour;
	/**
	 * The minutes that compose the time.
	 */
	private int minute;
	
	/**
	 * The time type that composes the time.
	 */
	private String timeType;

	/**
	 * This function initializes a new flight time.
	 * @param hour The hour that composes the time.
	 * @param minute The minutes that compose the time.
	 * @param timeType The time type that composes the time.
	 */
	public FlightTime(int hour, int minute, String timeType) {
		this.hour = hour;
		this.minute = minute;
		this.timeType = timeType;
	}

	/* 
	 * This function creates a String representing all the attributes of the object.
	 * @return The string of the time.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		LocalTime lT = LocalTime.of(hour, minute);
		DateTimeFormatter.ofPattern("hh:mm").format(lT);
		return lT.toString() + " " + timeType;
	}

	/* 
	 * This function compares this flight time with the one that arrives as a parameter.
	 * @param The object to be compared.
	 * @return true if the object that arrives as a parameter is the same as this flight time.
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		boolean equals = true;
		if (!(obj instanceof FlightTime)) {
		    equals = false;
		}
		FlightTime fT = (FlightTime) obj;
		if(fT.hour != hour || fT.minute != minute || !fT.timeType.equals(timeType))
			equals = false;
		return equals;
	}
	
	/* 
	 * The method that compares two flight times.
	 * @param fT The flight time to be compared with the current one.
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(FlightTime fT) {
		int comparison = 0;
		if(!timeType.equals(fT.timeType)) {
			if(timeType.equals(AM) && fT.timeType.equals(PM))
				comparison = -1;
			else
				comparison = 1;
		}
		else if(hour != fT.hour)
			comparison = hour - fT.hour;
		else
			comparison = minute - fT.minute;
		return comparison;
	}

	/**
	 * This function obtains the hour value of this time.
	 * @return the hour of this flight time.
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * This function obtains the minutes of this time.
	 * @return the minute value of this time.
	 */
	public int getMinute() {
		return minute;
	}

	/**
	 * This function obtains the time type of this time.
	 * @return the timeType value of this time.
	 */
	public String getTimeType() {
		return timeType;
	}
	
	
}
