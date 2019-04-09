package ui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}

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
