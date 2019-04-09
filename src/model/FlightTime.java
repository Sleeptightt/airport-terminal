package model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FlightTime implements Comparable<FlightTime>{

	public final static String AM = "A.M";
	public final static String PM = "P.M";
	
	private int hour;
	private int minute;
	private String timeType;

	public FlightTime(int hour, int minute, String timeType) {
		this.hour = hour;
		this.minute = minute;
		this.timeType = timeType;
	}

	@Override
	public String toString() {
		LocalTime lT = LocalTime.of(hour, minute);
		DateTimeFormatter.ofPattern("hh:mm").format(lT);
		return lT.toString() + " " + timeType;
	}

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
}
