package model;

public class Flight implements Comparable<Flight>{
	
	public final static String AVIANCA = "Avianca";
	public final static String AMERICAN = "American Airlines";//17
	public final static String COPA = "Copa Airlines";
	public final static String UNITED = "United Airlines";
	public final static String IBERIA = "Iberia";
	public final static String JETBLUE = "JetBlue Airways";
	
	private String airline;
	private String flightNumber;
	private String destination;
	private int boardingGate;
	
	private FlightDate flightDate;
	private FlightTime flightTime;
	
	public Flight(String airline, String flightNumber, String destination, int boardingGate, FlightDate flightDate,	FlightTime flightTime) {
		this.airline = airline;
		this.flightNumber = flightNumber;
		this.destination = destination;
		this.boardingGate = boardingGate;
		this.flightDate = flightDate;
		this.flightTime = flightTime;
	}

	@Override
	public int compareTo(Flight f) {
		int comparison = 0;
		if(!f.flightDate.equals(flightDate)) {
			comparison = flightDate.compareTo(f.flightDate);
		}
		else if(!f.flightTime.equals(flightTime)) {
			comparison = flightTime.compareTo(f.flightTime);
		}
		return comparison;
	}

	public int linearSearchDate(Flight[] f, FlightDate value) {
		int pos = -1;
		for (int i = 0; i < f.length && pos==-1; i++) {
			if(f[i].flightDate.equals(value))
				pos = i;
		}
		return pos;
	}
	
	@Override
	public String toString() {
		return flightDate + ";" + flightTime + ";" + airline + ";" + flightNumber + ";" + destination + ";" + boardingGate;
	}
	
	public FlightTime getFlightTime() {
		return flightTime;
	}
	
	public String getAirline() {
		return airline;
	}

	public String getDestination() {
		return destination;
	}

	public int getBoardingGate() {
		return boardingGate;
	}

	public String getFlightNumber() {
		return flightNumber;
	}
}
