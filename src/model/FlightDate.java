package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FlightDate implements Comparable<FlightDate>{
	
	private int year;
	private int month;
	private int dayOfMonth;
	
	public FlightDate(int year, int month, int dayOfMonth) {
		this.year = year;
		this.month = month;
		this.dayOfMonth = dayOfMonth;
	}

	@Override
	public String toString() {
		LocalDate lD = LocalDate.of(year, month, dayOfMonth);
		return lD.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
	}

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

}
