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
	
	public int linearSearchTime(Flight[] f, FlightTime value) {
        int pos = -1;
        for (int i = 0; i < f.length && pos==-1; i++) {
            if(f[i].flightTime.equals(value))
                pos = i;
        }
        return pos;
    }
    
    public int binarySearchAirline(Flight[] f, String value, int start, int end) {
        if(end-start <= 1) {
            if(f[start].airline.equals(value))
            	return start;
            if(f[end].airline.equals(value))
            	return end;
            return -1;
        }
       
        int midPoint = (start+end)/2;
        if(f[midPoint].airline.compareTo(value) < 0)
            return binarySearchAirline(f, value, midPoint, end);
        else
            return binarySearchAirline(f, value, start, midPoint);
        
    }
	
    public int binarySearchFlightNumber(Flight[] f, String value, int start, int end) {
    	 if(end-start <= 1) {
             if(f[start].flightNumber.equals(value))
             	return start;
             if(f[end].flightNumber.equals(value))
             	return end;
             return -1;
         }
        
         int midPoint = (start+end)/2;
         if(f[midPoint].flightNumber.compareTo(value) < 0)
             return binarySearchFlightNumber(f, value, midPoint, end);
         else
             return binarySearchFlightNumber(f, value, start, midPoint);
	}
    
    public int binarySearchDestination(Flight[] f, String value, int start, int end) {
   	 if(end-start <= 1) {
            if(f[start].destination.equals(value))
            	return start;
            if(f[end].destination.equals(value))
            	return end;
            return -1;
        }
       
        int midPoint = (start+end)/2;
        if(f[midPoint].destination.compareTo(value) < 0)
            return binarySearchDestination(f, value, midPoint, end);
        else
            return binarySearchDestination(f, value, start, midPoint);
	}
    
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

	public FlightDate getFlightDate() {
		return flightDate;
	}

}
