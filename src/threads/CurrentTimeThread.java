package threads;

import javafx.application.Platform;
import ui.AirlineController;

/**
 * <b> Laboratorio unidad 4 </b>
 * @author César Canales <br>
 * Universidad Icesi
 */
public class CurrentTimeThread extends Thread{

	/**
	 * The airline controller of the thread.
	 */
	private AirlineController aC;
	
	
	/**
	 * The attribute that states whether or not this thread is active or not.
	 */
	private boolean active;
	
	
	/**
	 * This function initializes a new current time thread.
	 * @param aC The airline controller of the current time thread.
	 */
	public CurrentTimeThread(AirlineController aC) {
		this.aC = aC;
		active = true;
	}
	
	/* 
	 * This function starts the thread.
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		while(active) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					aC.changeTime();
				}
			});
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * This function deactivates the thread.
	 */
	public void deactivate() {
		active = false;
	}
}
