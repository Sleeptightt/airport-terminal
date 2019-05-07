package model;

/**
 * <b> Laboratorio unidad 4 </b>
 * @author César Canales <br>
 * Universidad Icesi
 */
public class Flight implements Comparable<Flight>{
	
	/**
	 * A type of airline.
	 */
	public final static String AVIANCA = "Avianca";
	
	/**
	 * A type of airline.
	 */
	public final static String AMERICAN = "American Airlines";//17
	
	/**
	 * A type of airline.
	 */
	public final static String COPA = "Copa Airlines";
	
	/**
	 * A type of airline.
	 */
	public final static String UNITED = "United Airlines";
	
	/**
	 * A type of airline.
	 */
	public final static String IBERIA = "Iberia";
	
	/**
	 * A type of airline.
	 */
	public final static String JETBLUE = "JetBlue Airways";
	
	/**
	 * The airline of the flight.
	 */
	private String airline;
	
	/**
	 * The flight's number.
	 */
	private String flightNumber;
	
	/**
	 * The flight's destination.
	 */
	private String destination;
	
	/**
	 * The boarding gate of the flight.
	 */
	private int boardingGate;
	
	/**
	 * The date at which the flight is going to take place.
	 */	
	private FlightDate flightDate;
	
	/**
	 * The time at which the flight is going to take place.
	 */	
	private FlightTime flightTime;
		
	/**
	 * The previous flight in the linked list.
	 */	
	private Flight prev;
	
	/**
	 * The next flight in the linked list.
	 */	
	private Flight next;
	
	/**
	 * This function initializes a new Flight.
	 * @param airline The airline of the flight.
	 * @param flightNumber The number of the flight.
	 * @param destination The destination of the flight.
	 * @param boardingGate The boarding gate of the flight.
	 * @param flightDate The date at which the flight is going to take place.
	 * @param flightTime The time at which the flight is going to take place.
	 */
	public Flight(String airline, String flightNumber, String destination, int boardingGate, FlightDate flightDate,	FlightTime flightTime) {
		this.airline = airline;
		this.flightNumber = flightNumber;
		this.destination = destination;
		this.boardingGate = boardingGate;
		this.flightDate = flightDate;
		this.flightTime = flightTime;
	}

	
	 /* 
	  * The method that compares two flights.
	 * @param f The flight to compare with the current one.
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
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

	/** 
	 * This function does a linear search in the flight array looking for the specified date value.
	 * @param value The date to be found.
	 * @param first The first flight in the linked list.
	 * @return An integer representing the position of the value in the list. If the value is not found, this function returns a -1 instead.
	 */
	public int linearSearchDate(FlightDate value, Flight first) {
		Flight curr = first;
		int pos = -1;
		int counter = 0;
		while(curr != null && pos == -1) {
			if(curr.flightDate.equals(value))
				pos = counter;
			counter++;
			curr = curr.getNext();
		}
		return pos;
	}
	
	/** 
	 * This function does a linear search in the flight array looking for the specified time value.
	 * @param value The time to be found.
	 * @param first The first flight in the linked list.
	 * @return An integer representing the position of the value in the list. If the value is not found, this function returns a -1 instead.
	 */
	public int linearSearchTime(FlightTime value, Flight first) {
		Flight curr = first;
		int pos = -1;
		int counter = 0;
		while(curr != null && pos == -1) {
			if(curr.flightTime.equals(value))
				pos = counter;
			counter++;
			curr = curr.getNext();
		}
		return pos;
    }
    
	/** 
	 * This function does a linear search in the flight array looking for the specified time value.
	 * @param value The airline to be found.
	 * @param first The first flight in the linked list.
	 * @return An integer representing the position of the value in the list. If the value is not found, this function returns a -1 instead.
	 */
	public int linearSearchAirline(String value, Flight first) {
		Flight curr = first;
		int pos = -1;
		int counter = 0;
		while(curr != null && pos == -1) {
			if(curr.airline.equals(value))
				pos = counter;
			counter++;
			curr = curr.getNext();
		}
		return pos;
    }
	
	/** 
	 * This function does a linear search in the flight array looking for the specified time value.
	 * @param value The flight number to be found.
	 * @param first The first flight in the linked list.
	 * @return An integer representing the position of the value in the list. If the value is not found, this function returns a -1 instead.
	 */
	public int linearSearchFlightNumber(String value, Flight first) {
		Flight curr = first;
		int pos = -1;
		int counter = 0;
		while(curr != null && pos == -1) {
			if(curr.flightNumber.equals(value))
				pos = counter;
			counter++;
			curr = curr.getNext();
		}
		return pos;
    }
    
	/** 
	 * This function does a linear search in the flight array looking for the specified time value.
	 * @param value The destination to be found.
	 * @param first The first flight in the linked list.
	 * @return An integer representing the position of the value in the list. If the value is not found, this function returns a -1 instead.
	 */
	public int linearSearchDestination(String value, Flight first) {
		Flight curr = first;
		int pos = -1;
		int counter = 0;
		while(curr != null && pos == -1) {
			if(curr.destination.equals(value))
				pos = counter;
			counter++;
			curr = curr.getNext();
		}
		return pos;
    }
	
	/**
	 * This function does a linear search in the flight array looking for the specified time value.
	 * @param value The boarding gate to be found.
	 * @param first The first flight in the linked list.
	 * @return An integer representing the position of the value in the list. If the value is not found, this function returns a -1 instead.
	 */
	public int linearSearchBoardingGate(int value, Flight first) {
		Flight curr = first;
		int pos = -1;
		int counter = 0;
		while(curr != null && pos == -1) {
			if(curr.boardingGate == (value))
				pos = counter;
			counter++;
			curr = curr.getNext();
		}
		return pos;
    }
    
	/* 
	 * This function creates a String representing all the attributes of the object.
	 * @return The string of the flight.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return flightDate + ";" + flightTime + ";" + airline + ";" + flightNumber + ";" + destination + ";" + boardingGate;
	}
	
	/**
	 * This funtion obtains the time of the flight.
	 * @return The flight time of this flight.
	 */
	public FlightTime getFlightTime() {
		return flightTime;
	}
	
	/**
	 * This funtion obtains the airline of the flight.
	 * @return The airline of this flight.
	 */
	public String getAirline() {
		return airline;
	}

	/**
	 * This funtion obtains the destination of the flight.
	 * @return The destination of this flight.
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * This funtion obtains the boarding gate of the flight.
	 * @return The boarding gate of this flight.
	 */
	public int getBoardingGate() {
		return boardingGate;
	}

	/**
	 * This funtion obtains the flight number of the flight.
	 * @return The flight number of this flight.
	 */
	public String getFlightNumber() {
		return flightNumber;
	}

	/**
	 * This funtion obtains the date of the flight.
	 * @return The date of this flight.
	 */
	public FlightDate getFlightDate() {
		return flightDate;
	}

	/**
	 * This function obtains the previous flight in the linked list.
	 * @return The previous flight in the linked list.
	 */
	public Flight getPrev() {
		return prev;
	}


	/**
	 * This function modifies the previous flight in the linked list.
	 * @param prev The flight to swap with the current previous flight.
	 */
	public void setPrev(Flight prev) {
		this.prev = prev;
	}


	/**
	 * This function obtains the next flight in the linked list.
	 * @return The next flight in the linked list.
	 */
	public Flight getNext() {
		return next;
	}


	/**
	 * This function modifies the next flight in the linked list.
	 * @param next The flight to swap with the current next.
	 */
	public void setNext(Flight next) {
		this.next = next;
	}
	
	
	/**
	 * This function modifies the data of this current flight. It is used to facilitate the implementation of the sorting algoritms.
	 * @param airline The airline of the flight.
	 * @param flightNumber The number of the flight.
	 * @param destination The destination of the flight.
	 * @param boardingGate The boarding gate of the flight.
	 * @param flightDate The date at which the flight is going to take place.
	 * @param flightTime The time at which the flight is going to take place.
	 */
	public void setData(String airline, String flightNumber, String destination, int boardingGate, FlightDate flightDate,	FlightTime flightTime) {
		this.airline = airline;
		this.flightNumber = flightNumber;
		this.destination = destination;
		this.boardingGate = boardingGate;
		this.flightDate = flightDate;
		this.flightTime = flightTime;
	}
}
