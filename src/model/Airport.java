package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
	private List<Flight> flights;
	
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
		flights = new ArrayList<Flight>();
		page = 1;
	}
	
	/**
	 * This function generates the specified number of flights randomly. It also calculates the positions of each of the pages.
	 * @param n The number of flights to be generated.
	 * @throws IOException If the specified file doesn't exist.
	 */
	public void generateFlights(int n) throws IOException {
		for (int i = 0; i < n; i++) {
			flights.add(getRandomFlight());
		}
		calculatePositions(n);
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
	 * @throws IOException If the specified file doesn't exist.
	 */
	public Flight getRandomFlight() throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(new File("data/FlightInformation.txt")));
		String[] airlines = in.readLine().split(";");
		String[] cities = in.readLine().split(";");
		String[] timeTypes = in.readLine().split(";");
		in.close();
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
		for (int i = 0; i < flights.size(); i++) {
			for(int j = 0; j < flights.size()-i-1;j++) {
				if(c.compare(flights.get(j), flights.get(j+1)) > 0) {
					Flight temp = flights.get(j);
					flights.set(j, flights.get(j+1));
					flights.set(j+1, temp);
				}
			}
		}
		
	}
	
	/**
	 * This function uses a selection sort algorithm to sort the flights list in the specified sorting criteria.
	 * @param c A comparator that determines the criteria in which the flights are going to be sorted.
	 */
	public void selectionSort(Comparator<Flight> c) {
		for (int i = 0; i < flights.size()-1; i++) {
			int pos = i;
			for(int j = i+1; j < flights.size();j++) {
				if(c.compare(flights.get(j), flights.get(pos)) < 0) {
					pos = j;
				}
			}
			Flight temp = flights.get(pos);
			flights.set(pos, flights.get(i));
			flights.set(i, temp);
		}
	}

	/**
	 * This function uses a insertion sort algorithm to sort the flights list in the specified sorting criteria.
	 * @param c A comparator that determines the criteria in which the flights are going to be sorted.
	 */
	public void insertionSort(Comparator<Flight> c) {
		for (int i = 1; i < flights.size(); i++) {
			for(int j = i; j > 0;j--) {
				if(c.compare(flights.get(j), flights.get(j-1)) < 0) {
					Flight temp = flights.get(j);
					flights.set(j, flights.get(j-1));
					flights.set(j-1, temp);
				}
			}
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
        Flight[] f = flights.toArray(new Flight[flights.size()]);
        if(criteria == 1) {
            String[] arr = value.split("/");
            pos = f[0].linearSearchDate(f, new FlightDate(Integer.parseInt(arr[2]), Integer.parseInt(arr[1]), Integer.parseInt(arr[0])));
        }
        else if(criteria == 2) {
            int hour = Integer.parseInt(value.substring(0, 2));
            int minute = Integer.parseInt(value.substring(3, 5));
            String type = value.substring(6);
            pos = f[0].linearSearchTime(f, new FlightTime(hour, minute, type));
        }
        else if(criteria == 3) {
        	Arrays.sort(f, new Comparator<Flight>() {
				@Override
				public int compare(Flight a, Flight b) {
					int comparison = 0;
					if (!a.getAirline().equals(b.getAirline()))
						comparison = a.getAirline().compareTo(b.getAirline());
					return comparison;
				}
			});
            pos = f[0].binarySearchAirline(f, value, 0, f.length-1);
        }
        else if(criteria == 4) {
        	Arrays.sort(f, new Comparator<Flight>() {
				@Override
				public int compare(Flight a, Flight b) {
					int comparison = 0;
					if (!a.getFlightNumber().equals(b.getFlightNumber()))
						comparison = a.getFlightNumber().compareTo(b.getFlightNumber());
					return comparison;
				}
			});
            pos = f[0].binarySearchFlightNumber(f, value, 0, f.length-1);
        }
        else if(criteria == 5) {
        	Arrays.sort(f, new Comparator<Flight>() {
				@Override
				public int compare(Flight a, Flight b) {
					int comparison = 0;
					if (!a.getDestination().equals(b.getDestination()))
						comparison = a.getDestination().compareTo(b.getDestination());
					return comparison;
				}
			});
            pos = f[0].binarySearchDestination(f, value, 0, f.length-1);
        }
        else if(criteria == 6) {
        	Arrays.sort(f, new Comparator<Flight>() {
				@Override
				public int compare(Flight a, Flight b) {
					int comparison = 0;
					if (a.getBoardingGate() != b.getBoardingGate())
						comparison = a.getBoardingGate() - b.getBoardingGate();
					return comparison;
				}
			});
            pos = f[0].binarySearchBoardingGate(f, Integer.parseInt(value), 0, f.length-1);
        }
        Flight myFlight = null;
        if(pos!=-1)
        	myFlight = f[pos];
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
		return flights;
	}
}
