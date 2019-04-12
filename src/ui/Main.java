package ui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * <b> Laboratorio unidad 4 </b>
 * @author César Canales <br>
 * Universidad Icesi
 */
public class Main extends Application{

	/**
	* The main function responsible of initiating the program.
	* @param args Used to launch the program.
	*/
	public static void main(String[] args) {
		launch(args);
	}

	/**
	* The start function which initializes the stage and displays it. Also deactivates the threads when the window is close by using an anonymous nested class.
	* @param stage The main stage of the graphical interface.
	*/
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Airlines.fxml"));
		Parent root = loader.load();
		
		AirlineController airController = loader.getController();
		airController.setStage(stage);
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent arg0) {
				airController.getCT().deactivate();
			}
    	});
		Scene scene = new Scene(root);
		stage.setTitle("Flights");
		stage.setScene(scene);
		stage.setResizable(true);
		stage.show();
	}

}
