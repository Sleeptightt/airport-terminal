package threads;

import model.Airport;

public class GeneratingThread extends Thread{

	private Airport a;
	private int size;
	
	public GeneratingThread(Airport a, int n) {
		this.a = a;
		size = n;
	}
	
	@Override
	public void run() {
		a.generateFlights(size);
	}
}
