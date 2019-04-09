package threads;

import java.util.Comparator;
import model.Flight;
import ui.AirlineController;

public class SortingThread extends Thread{
	
	private AirlineController aC;
	
	
	public SortingThread(AirlineController aC) {
		this.aC = aC;
	}
	
	@Override
	public void run() {
	/*	aC.getAirport().bubbleSort(new Comparator<Flight>() {
			@Override
			public int compare(Flight a, Flight b) {
				int comparison = 0;
				if(!a.getAirline().equals(b.getAirline()))
					comparison = a.getAirline().compareTo(b.getAirline());
				return comparison;
			}
    	});	*/
	}

}
