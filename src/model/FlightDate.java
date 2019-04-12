package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <b> Laboratorio unidad 4 </b>
 * @author César Canales <br>
 * Universidad Icesi
 */
public class FlightDate implements Comparable<FlightDate>{
	
	/**
	 * The year that composes the date.
	 */
	private int year;
	
	/**
	 * The month that composes the date.
	 */
	private int month;
	
	/**
	 * The day of the month that composes the date.
	 */
	private int dayOfMonth;
	
	/**
	 * This function initializes a new FlightDate.
	 * @param year The year that composes the date.
	 * @param month The month that composes the date.
	 * @param dayOfMonth The day of the month that composes the date.
	 */
	public FlightDate(int year, int month, int dayOfMonth) {
		this.year = year;
		this.month = month;
		this.dayOfMonth = dayOfMonth;
	}

	/* 
	 * This function creates a String representing all the attributes of the object.
	 * @return The string of the date.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		LocalDate lD = LocalDate.of(year, month, dayOfMonth);
		return lD.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
	}

	/* 
	 * This function compares this flight date with the one that arrives as a parameter.
	 * @param The object to be compared.
	 * @return true if the object that arrives as a parameter is the same as this flight date.
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		boolean equals = true;
		if (!(obj instanceof FlightDate)) {
		    equals = false;
		}
		FlightDate fD = (FlightDate) obj;
		if(fD.year != year || fD.month != month || fD.dayOfMonth != dayOfMonth)
			equals = false;
		return equals;
	}

	/* 
	  * The method that compares two flight dates.
	 * @param fD The flight date to be compared with the current one.
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(FlightDate fD) {
		int comparison = 0;
		if(year != fD.year)
			comparison = year - fD.year;
		else if(month != fD.month)
			comparison = month - fD.month;	
		else if(dayOfMonth != fD.dayOfMonth)
			comparison = dayOfMonth - fD.dayOfMonth;
		return comparison;
	}

	/**
	 * This function obtains the year of this date.
	 * @return the year value of this date.
	 */
	public int getYear() {
		return year;
	}

	/**
	 * This function obtains the month of this date.
	 * @return the month value of this date.
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * This function obtains the day of this date.
	 * @return the dayOfMonth value of this date.
	 */
	public int getDayOfMonth() {
		return dayOfMonth;
	}
	
}
