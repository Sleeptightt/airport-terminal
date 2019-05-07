package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * <b> Laboratorio unidad 4 </b>
 * @author César Canales <br>
 * Universidad Icesi
 */
public class Airport {
	
	/**
	 * A list of flights of the airport.
	 */
	private Flight first;
	
	/**
	 * The current page of the airport terminal.
	 */
	private int page;
	
	/**
	 * The lower bound and upper bound of each of the pages.
	 */
	private String[] positions;
	
	/**
	 * This function initializes a new Airport.
	 */
	public Airport() {
		first = null;
		page = 1;
	}
	
	/**
	 * This function generates the specified number of flights randomly. It also calculates the positions of each of the pages.
	 * @param n The number of flights to be generated.
	 */
	public void generateFlights(int n) {
		for (int i = 0; i < n; i++) {
			addFlight(getRandomFlight());
		}
		calculatePositions(n);
	}
	
	/**
	 * This function adds a flight to the linked list of flights.
	 * @param f The flight that is to be added to the flights list.
	 */
	public void addFlight(Flight f) {
		if(first==null) {
			first = f;
		}
		else {
			Flight curr = first;
			while (curr.getNext()!=null) {
				curr = curr.getNext();
			}
			curr.setNext(f);
			f.setPrev(curr);
		}
	}
	
	/**
	 * This calculates the positions of each of the pages. That is, the lower bound and upper bound of each of the pages.
	 * @param n The number of flights in the flights list.
	 */
	public void calculatePositions(int n) {
		int pages = n/14;
		boolean rest = false;
		int remainder = 0;
		if(n%14!=0) {
			remainder = n%14-1;
			rest = true;
			pages++;
		}
		positions = new String[pages];
		for(int i = 0; i < positions.length; i++) {
			if(i == positions.length-1 && rest) {
				positions[i] = (i*14) + ";" + (((i*14)+remainder));
			}else {
				positions[i] = (i*14) + ";" + (((i+1)*14-1));
			}
		}
	}
	
	/**
	 * This function randomly generates a flight with the Random java function.
	 * @return The randomly generated flight.
	 */
	public Flight getRandomFlight() {
		String[] airlines = {Flight.AVIANCA, Flight.AMERICAN, Flight.COPA, Flight.IBERIA, Flight.UNITED, Flight.JETBLUE};
		String[] cities = {"Madrid", "New York", "Beijing", "France", "Buenos Aires", "Panama", "Quito", "Lima", "Bogota", "Berlin"};
		String[] timeTypes = {FlightTime.AM, FlightTime.PM};
		int random = 0 +  (int)(Math.random()*(6));
		String airline = airlines[random];
		random = 0 +  (int)(Math.random()*(10));
		String destination = cities[random];
		random = 0 +  (int)(Math.random()*(2));
		String SALTCHARS = "1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 4) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String flightNumber = airline.substring(0, 2).toUpperCase() + salt.toString();
        int boardingGate = 1 +  (int)(Math.random()*(100));
        int month = 1 +  (int)(Math.random()*(12));
        int days = 28;
        if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
        	days = 1 +  (int)(Math.random()*(31));
        else if(month == 4 || month == 6 || month == 9 || month == 11)
        	days = 1 +  (int)(Math.random()*(30));
        FlightDate flightDate = new FlightDate(2018 +  (int)(Math.random()*(2)), month, days);
        FlightTime flightTime = new FlightTime(1 +  (int)(Math.random()*(12)), 0 +  (int)(Math.random()*(60)), timeTypes[random]);
        
		return new Flight(airline, flightNumber, destination, boardingGate, flightDate, flightTime);
	}
	
	/**
	 * This function uses a bubble sort algorithm to sort the flights list in the specified sorting criteria.
	 * @param c A comparator that determines the criteria in which the flights are going to be sorted.
	 */
	public void bubbleSort(Comparator<Flight> c) {
		boolean swap = true;
        while(swap) {
            swap = false;
            Flight current = first;
            while(current.getNext() != null) {
                Flight nextFlight = current.getNext();
                if(c.compare(current, current.getNext())>0) {
                    if(current.getPrev() != null) {
                        current.getPrev().setNext(nextFlight);
                    }
                    if(nextFlight.getNext() != null) {
                        nextFlight.getNext().setPrev(current);
                    }
                    current.setNext(nextFlight.getNext());
                    nextFlight.setPrev(current.getPrev());
                    current.setPrev(nextFlight);
                    nextFlight.setNext(current);
                    if(current == first)
                        first = nextFlight;
                    swap = true;
                }
                else
                    current = current.getNext();
                
            }
        }	
	}
	
	/**
	 * This function uses a selection sort algorithm to sort the flights list in the specified sorting criteria.
	 * @param c A comparator that determines the criteria in which the flights are going to be sorted.
	 */
	public void selectionSort(Comparator<Flight> c) {
		Flight oCurr = first;
        while(oCurr.getNext()!=null) {
            Flight pos = oCurr;
            Flight curr = pos.getNext();
            boolean better = false;
            while(curr != null) {
                if(c.compare(curr, pos) < 0) {
                    pos = curr;
                    better = true;
                }    
                curr = curr.getNext();
            }
            if(better) {
            	if(oCurr.getNext() == pos) {
            		if(oCurr.getPrev() != null) {
                        oCurr.getPrev().setNext(pos);
                    }
                    if(pos.getNext() != null) {
                        pos.getNext().setPrev(oCurr);
                    }
                    oCurr.setNext(pos.getNext());
                    pos.setPrev(oCurr.getPrev());
                    oCurr.setPrev(pos);
                    pos.setNext(oCurr);
                    if(oCurr == first)
                        first = pos;
            	}
            	else if(oCurr.getPrev()!=null && pos.getNext()!= null) {
	                Flight t1 = oCurr.getPrev();
	                Flight t2 = oCurr.getNext();
	                Flight t3 = pos.getPrev();
	                Flight t4 = pos.getNext();
	                oCurr.setNext(t4);
	                oCurr.setPrev(t3);
	                pos.setPrev(t1);
	                pos.setNext(t2);
	                t1.setNext(pos);
	                t2.setPrev(pos);
	                t3.setNext(oCurr);
	                t4.setPrev(oCurr);
	                if(oCurr == first)
	                    first = pos;
            	}
            	else if(oCurr.getPrev() == null && pos.getNext() !=null) {
            		Flight t2 = oCurr.getNext();
	                Flight t3 = pos.getPrev();
	                Flight t4 = pos.getNext();
	                oCurr.setNext(t4);
	                oCurr.setPrev(t3);
	                pos.setPrev(null);
	                pos.setNext(t2);
	                t2.setPrev(pos);
	                t3.setNext(oCurr);
	                t4.setPrev(oCurr);
	                first = pos;
            	}
            	else if(oCurr.getPrev() != null && pos.getNext() == null) {
            		Flight t1 = oCurr.getPrev();
	                Flight t2 = oCurr.getNext();
	                Flight t3 = pos.getPrev();
	                oCurr.setNext(null);
	                oCurr.setPrev(t3);
	                pos.setPrev(t1);
	                pos.setNext(t2);
	                t1.setNext(pos);
	                t2.setPrev(pos);
	                t3.setNext(oCurr);
            	}
            	else {
            		Flight t2 = oCurr.getNext();
	                Flight t3 = pos.getPrev();
	                oCurr.setNext(null);
	                oCurr.setPrev(t3);
	                pos.setPrev(null);
	                pos.setNext(t2);
	                t2.setPrev(pos);
	                t3.setNext(oCurr);
	                first = pos;
            	}
            	oCurr = pos.getNext();
            }else
                oCurr = oCurr.getNext();
        }
		
	}

	/**
	 * This function uses a insertion sort algorithm to sort the flights list in the specified sorting criteria.
	 * @param c A comparator that determines the criteria in which the flights are going to be sorted.
	 */
	public void insertionSort(Comparator<Flight> c) {
		Flight oCurr = first.getNext();
		while(oCurr != null) {
			Flight curr = oCurr;
			while(curr.getPrev()!=null) {
				if(c.compare(curr, curr.getPrev()) < 0) {
					Flight temp = curr.getPrev();
					if(temp.getPrev() != null) {
                        temp.getPrev().setNext(curr);
                    }
                    if(curr.getNext() != null) {
                        curr.getNext().setPrev(temp);
                    }
                    temp.setNext(curr.getNext());
                    curr.setPrev(temp.getPrev());
                    temp.setPrev(curr);
                    curr.setNext(temp);
                    if(temp == first)
                        first = curr;
				}else
				curr = curr.getPrev();
			}
			oCurr = oCurr.getNext();
		}
	}
	
	/**
	 * This function searches according to the criteria that arrives as a parameter.
	 * @param criteria The searching criteria.
	 * @param value The value to be found.
	 * @return The flight that was found or null is it was not found.
	 * @throws NumberFormatException If the value entered is not correct.
	 * @throws ArrayIndexOutOfBoundsException If the value entered is not correct.
	 * @throws StringIndexOutOfBoundsException If the value entered is not correct.
	 */
	public Flight search(int criteria, String value) throws NumberFormatException, ArrayIndexOutOfBoundsException, StringIndexOutOfBoundsException{
        int pos = -1;
        if(criteria == 1) {
            String[] arr = value.split("/");
            pos = first.linearSearchDate(new FlightDate(Integer.parseInt(arr[2]), Integer.parseInt(arr[1]), Integer.parseInt(arr[0])), first);
        }
        else if(criteria == 2) {
            int hour = Integer.parseInt(value.substring(0, 2));
            int minute = Integer.parseInt(value.substring(3, 5));
            String type = value.substring(6);
            pos = first.linearSearchTime(new FlightTime(hour, minute, type), first);
        }
        else if(criteria == 3) {
            pos = first.linearSearchAirline(value, first);
        }
        else if(criteria == 4) {
        	pos = first.linearSearchFlightNumber(value, first);
        }
        else if(criteria == 5) {
        	pos = first.linearSearchDestination(value, first);
        }
        else if(criteria == 6) {
        	pos = first.linearSearchBoardingGate(Integer.parseInt(value), first);
        }
        Flight myFlight = null;
        if(pos!=-1) {
        	Flight curr = first;
        	int counter = 0;
        	while(counter <= pos) {
        		if(counter == pos)
        			myFlight = curr;
        		curr = curr.getNext();
        		counter++;
        	}
        }
        return myFlight;
    }
	

	/**
	 * This function obtains the current page of the airport terminal.
	 * @return The current page of the airport terminal.
	 */
	public int getPage() {
		return page;
	}

	/**
	 * This function changes the current page of the airport terminal.
	 * @param page the page to set.
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * This function obtains the array of positions of each page.
	 * @return the positions array.
	 */
	public String[] getPositions() {
		return positions;
	}

	/**
	 * This function obtains the airport's list of flights.
	 * @return The list of flights of the airport.
	 */
	public List<Flight> getFLights(){
		List<Flight> flights = new ArrayList<Flight>();
		Flight curr = first;
		while(curr!=null) {
			flights.add(curr);
			curr = curr.getNext();
		}
		return flights;
	}
	
	public Flight getFirst() {
		return first;
	}
}
