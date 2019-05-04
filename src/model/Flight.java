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
		
	private Flight prev;
	
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
	 * @param f The array of flights of the airport.
	 * @param value The date to be found.
	 * @return An integer representing the position of the value in the array. If the value is not found, this function returns a -1 instead.
	 */
	public int linearSearchDate(Flight[] f, FlightDate value) {
		int pos = -1;
		for (int i = 0; i < f.length && pos==-1; i++) {
			if(f[i].flightDate.equals(value))
				pos = i;
		}
		return pos;
	}
	
	/** 
	 * This function does a linear search in the flight array looking for the specified time value.
	 * @param f The array of flights of the airport.
	 * @param value The time to be found.
	 * @return An integer representing the position of the value in the array. If the value is not found, this function returns a -1 instead.
	 */
	public int linearSearchTime(Flight[] f, FlightTime value) {
        int pos = -1;
        for (int i = 0; i < f.length && pos==-1; i++) {
            if(f[i].flightTime.equals(value))
                pos = i;
        }
        return pos;
    }
    
	/** 
	 * This function does a binary search in the flight array looking for the specified airline value.
	 * Pre: The array is sorted before the search.
	 * @param f The array of flights of the airport.
	 * @param value The value to be found.
	 * @param start The start position of the search.
	 * @param end The end position of the search.
	 * @return An integer representing the position of the value in the array. If the value is not found, this function returns a -1 instead.
	 */
    public int binarySearchAirline(Flight[] f, String value, int start, int end) {
        if(end-start <= 1) {
            if(f[start].airline.equalsIgnoreCase(value))
            	return start;
            if(f[end].airline.equalsIgnoreCase(value))
            	return end;
            return -1;
        }
       
        int midPoint = (start+end)/2;
        if(f[midPoint].airline.compareTo(value) < 0)
            return binarySearchAirline(f, value, midPoint, end);
        else
            return binarySearchAirline(f, value, start, midPoint);
        
    }
	
    /** 
	 * This function does a binary search in the flight array looking for the specified flight number value.
	 * Pre: The array is sorted before the search.
	 * @param f The array of flights of the airport.
	 * @param value The value to be found.
	 * @param start The start position of the search.
	 * @param end The end position of the search.
	 * @return An integer representing the position of the value in the array. If the value is not found, this function returns a -1 instead.
	 */
    public int binarySearchFlightNumber(Flight[] f, String value, int start, int end) {
    	 if(end-start <= 1) {
             if(f[start].flightNumber.equalsIgnoreCase(value))
             	return start;
             if(f[end].flightNumber.equalsIgnoreCase(value))
             	return end;
             return -1;
         }
        
         int midPoint = (start+end)/2;
         if(f[midPoint].flightNumber.compareTo(value) < 0)
             return binarySearchFlightNumber(f, value, midPoint, end);
         else
             return binarySearchFlightNumber(f, value, start, midPoint);
	}
    
    /** 
	 * This function does a binary search in the flight array looking for the specified destination value.
	 * Pre: The array is sorted before the search.
	 * @param f The array of flights of the airport.
	 * @param value The value to be found.
	 * @param start The start position of the search.
	 * @param end The end position of the search.
	 * @return An integer representing the position of the value in the array. If the value is not found, this function returns a -1 instead.
	 */
    public int binarySearchDestination(Flight[] f, String value, int start, int end) {
   	 if(end-start <= 1) {
            if(f[start].destination.equalsIgnoreCase(value))
            	return start;
            if(f[end].destination.equalsIgnoreCase(value))
            	return end;
            return -1;
        }
       
        int midPoint = (start+end)/2;
        if(f[midPoint].destination.compareTo(value) < 0)
            return binarySearchDestination(f, value, midPoint, end);
        else
            return binarySearchDestination(f, value, start, midPoint);
	}
    
    /** 
	 * This function does a binary search in the flight array looking for the specified boarding gate value.
	 * Pre: The array is sorted before the search.
	 * @param f The array of flights of the airport.
	 * @param value The value to be found.
	 * @param start The start position of the search.
	 * @param end The end position of the search.
	 * @return An integer representing the position of the value in the array. If the value is not found, this function returns a -1 instead.
	 */
    public int binarySearchBoardingGate(Flight[] f, int value, int start, int end) {
      	 if(end-start <= 1) {
               if(f[start].boardingGate == value)
               	return start;
               if(f[end].boardingGate == value)
               	return end;
               return -1;
           }
          
           int midPoint = (start+end)/2;
           if(f[midPoint].boardingGate < value)
               return binarySearchBoardingGate(f, value, midPoint, end);
           else
               return binarySearchBoardingGate(f, value, start, midPoint);
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
	 * @return the prev
	 */
	public Flight getPrev() {
		return prev;
	}


	/**
	 * @param prev the prev to set
	 */
	public void setPrev(Flight prev) {
		this.prev = prev;
	}


	/**
	 * @return the next
	 */
	public Flight getNext() {
		return next;
	}


	/**
	 * @param next the next to set
	 */
	public void setNext(Flight next) {
		this.next = next;
	}
	
	public void setData(String airline, String flightNumber, String destination, int boardingGate, FlightDate flightDate,	FlightTime flightTime) {
		this.airline = airline;
		this.flightNumber = flightNumber;
		this.destination = destination;
		this.boardingGate = boardingGate;
		this.flightDate = flightDate;
		this.flightTime = flightTime;
	}
}
