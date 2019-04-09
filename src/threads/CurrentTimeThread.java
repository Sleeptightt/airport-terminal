package threads;

import javafx.application.Platform;
import ui.AirlineController;

public class CurrentTimeThread extends Thread{

	private AirlineController aC;
	private boolean active;
	
	
	public CurrentTimeThread(AirlineController aC) {
		this.aC = aC;
		active = true;
	}
	
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
	
	public void deactivate() {
		active = false;
	}
}
