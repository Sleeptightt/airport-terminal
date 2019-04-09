package ui;

import model.Airport;
import model.Flight;
import threads.CurrentTimeThread;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AirlineController {

	private Stage stage;
	
	private Airport airport;
	
	private LocalTime curTime;
	
	private CurrentTimeThread cT;

    @FXML
    private Label currentTimeLabel;

    @FXML
    private TextField numberOfFlights;
    
    @FXML
    private VBox mainVBox;

    @FXML
    private HBox titleHBox;
    
    @FXML
    private Button rightButton;
    
    @FXML
    private Button leftButton;
    
    @FXML
    private ListView<GridPane> flightsList;
    
    @FXML
    private TextField pageTextField;
    
    @FXML
    private Label timeTakenLabel;
    
    @FXML
    public void initialize() {
    	stage = new Stage();
    	airport = new Airport();
    	pageTextField.setText(airport.getPage()+"");
    	leftButton.setDisable(true);
    	pageTextField.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent ke)
            {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    try {
						int desiredPage = Integer.parseInt(pageTextField.getText());
						if(desiredPage > airport.getPositions().length || desiredPage <  1)
							throw new IllegalArgumentException();
						airport.setPage(desiredPage);
				    	int low = Integer.parseInt(airport.getPositions()[airport.getPage()-1].split(";")[0]);
				    	int high = Integer.parseInt(airport.getPositions()[airport.getPage()-1].split(";")[1]);
				    	fillList(low, high);
				    	if(airport.getPage() != 1 && leftButton.isDisabled())
				    		leftButton.setDisable(false);
				    	else if(airport.getPage() == 1 && !leftButton.isDisabled())
				    		leftButton.setDisable(true);
				    	
				    	if(airport.getPage() != airport.getPositions().length && rightButton.isDisabled())
				    		rightButton.setDisable(false);
				    	else if(airport.getPage() == airport.getPositions().length && !rightButton.isDisabled())
				    		rightButton.setDisable(true);
					} catch (IllegalArgumentException e) {
						pageTextField.setText(airport.getPage()+"");
					}
                    
                    
                }
            }
        });
    	curTime = LocalTime.now();
    	changeTime();
    	cT = new CurrentTimeThread(this);
    	cT.start();
    }
    
    @FXML
    void leftPageButton(ActionEvent event) {
    	if(rightButton.isDisabled())
    		rightButton.setDisable(false);
    	airport.setPage(airport.getPage()-1);
    	int low = Integer.parseInt(airport.getPositions()[airport.getPage()-1].split(";")[0]);
    	int high = Integer.parseInt(airport.getPositions()[airport.getPage()-1].split(";")[1]);
    	fillList(low, high);
    	if(airport.getPage() == 1)
    		leftButton.setDisable(true);
    }

    @FXML
    void rightPageButton(ActionEvent event) {
    	if(leftButton.isDisabled())
    		leftButton.setDisable(false);
    	airport.setPage(airport.getPage()+1);
    	int low = Integer.parseInt(airport.getPositions()[airport.getPage()-1].split(";")[0]);
    	int high = Integer.parseInt(airport.getPositions()[airport.getPage()-1].split(";")[1]);
    	fillList(low, high);
    	if(airport.getPage() == airport.getPositions().length)
    		rightButton.setDisable(true);
    }
    
    public void changeTime() {
    	curTime = curTime.plusSeconds(1);
    	currentTimeLabel.setText(DateTimeFormatter.ofPattern("hh:mm:ss a").format(curTime));
    }
    
    @FXML
    void generateListView(ActionEvent event) {
    	numberOfFlights.clear();
    	Label title = new Label("Enter the number of flights you want to generate");
    	title.setFont(Font.font("Regular", 25));
    	title.setStyle("-fx-font-weight: bold");
    	numberOfFlights.setVisible(true);
    	Button generateButton = new Button("Generate");
    	//generateButton.setFont(new Font(25));; Note: shrinking button :[
    	generateButton.setStyle("-fx-font-weight: bold");
    	generateButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent a) {
				boolean in = false;
				try {
					airport.generateFlights(Integer.parseInt(numberOfFlights.getText()));
				}catch(NumberFormatException e) {
					in = true;
					numberOfFlights.clear();
					numberOfFlights.setPromptText("Please enter a valid number");
				}
				if(!in) {
					mainVBox.getChildren().remove(title);
			    	mainVBox.getChildren().remove(generateButton);
					numberOfFlights.setVisible(false);
					titleHBox.setVisible(true);
					Collections.sort(airport.getFLights());
					int low = Integer.parseInt(airport.getPositions()[airport.getPage()-1].split(";")[0]);
			    	int high = Integer.parseInt(airport.getPositions()[airport.getPage()-1].split(";")[1]);
					fillList(low, high);
				}
			}
    		
    	});
    	mainVBox.getChildren().add(1, title);
    	mainVBox.getChildren().add(3, generateButton);
    }
    
    public void fillList(int low, int high) {
    	ObservableList<GridPane> items = FXCollections.observableArrayList();
    	for (int i = low; i <= high; i++) {
			String[] arr = airport.getFLights().get(i).toString().split(";");
			GridPane g = new GridPane();
			g.setAlignment(Pos.CENTER);
			for (int j = 0; j < arr.length; j++) {
				ColumnConstraints c = new ColumnConstraints();
				if(j < 1)
					c.setPrefWidth(350);
				else if(j == 1)
					c.setPrefWidth(370);
				else if(j == 2)
					c.setPrefWidth(390);
				else if(j == 3)
					c.setPrefWidth(375);
				else 
					c.setPrefWidth(270);
				Label l = new Label(arr[j]);
				l.setAlignment(Pos.CENTER);
				g.add(l, j, 0);
				g.getColumnConstraints().add(c);
			}
			items.add(g);
		}
    	flightsList.setItems(items);
    	flightsList.setVisible(true);
    	pageTextField.setText(airport.getPage()+"");
    }
    
    @FXML
    void sortAirline(ActionEvent event) {
    	if (!airport.getFLights().isEmpty()) {
			long start = System.currentTimeMillis();
			airport.bubbleSort(new Comparator<Flight>() {

				@Override
				public int compare(Flight a, Flight b) {
					int comparison = 0;
					if (!a.getAirline().equals(b.getAirline()))
						comparison = a.getAirline().compareTo(b.getAirline());
					return comparison;
				}

			});
			long end = System.currentTimeMillis();
			long calc = (end - start) / 1000;
			timeTakenLabel.setText("Time taken to sort: " + calc + " seconds");
			int low = Integer.parseInt(airport.getPositions()[airport.getPage() - 1].split(";")[0]);
			int high = Integer.parseInt(airport.getPositions()[airport.getPage() - 1].split(";")[1]);
			fillList(low, high);
		}
    }

    @FXML
    void sortDate(ActionEvent event) {
    	if (!airport.getFLights().isEmpty()) {
			long start = System.currentTimeMillis();
			Collections.sort(airport.getFLights());
			long end = System.currentTimeMillis();
			long calc = (end - start) / 1000;
			timeTakenLabel.setText("Time taken to sort: " + calc + " seconds");
			int low = Integer.parseInt(airport.getPositions()[airport.getPage() - 1].split(";")[0]);
			int high = Integer.parseInt(airport.getPositions()[airport.getPage() - 1].split(";")[1]);
			fillList(low, high);
		}
    }

    @FXML
    void sortDestination(ActionEvent event) {
    	if (!airport.getFLights().isEmpty()) {
			long start = System.currentTimeMillis();
			airport.selectionSort(new Comparator<Flight>() {

				@Override
				public int compare(Flight a, Flight b) {
					int comparison = 0;
					if (!a.getDestination().equals(b.getDestination()))
						comparison = a.getDestination().compareTo(b.getDestination());
					return comparison;
				}

			});
			long end = System.currentTimeMillis();
			long calc = (end - start) / 1000;
			timeTakenLabel.setText("Time taken to sort: " + calc + " seconds");
			int low = Integer.parseInt(airport.getPositions()[airport.getPage() - 1].split(";")[0]);
			int high = Integer.parseInt(airport.getPositions()[airport.getPage() - 1].split(";")[1]);
			fillList(low, high);
		}
    }

    @FXML
    void sortFlightNumber(ActionEvent event) {
    	if (!airport.getFLights().isEmpty()) {
			long start = System.currentTimeMillis();
			airport.selectionSort(new Comparator<Flight>() {

				@Override
				public int compare(Flight a, Flight b) {
					int comparison = 0;
					if (!a.getFlightNumber().equals(b.getFlightNumber()))
						comparison = a.getFlightNumber().compareTo(b.getFlightNumber());
					return comparison;
				}

			});
			long end = System.currentTimeMillis();
			long calc = (end - start) / 1000;
			timeTakenLabel.setText("Time taken to sort: " + calc + " seconds");
			int low = Integer.parseInt(airport.getPositions()[airport.getPage() - 1].split(";")[0]);
			int high = Integer.parseInt(airport.getPositions()[airport.getPage() - 1].split(";")[1]);
			fillList(low, high);
		}
    }

    @FXML
    void sortGate(ActionEvent event) {
    	if (!airport.getFLights().isEmpty()) {
			long start = System.currentTimeMillis();
			airport.insertionSort(new Comparator<Flight>() {

				@Override
				public int compare(Flight a, Flight b) {
					int comparison = 0;
					if (a.getBoardingGate() != b.getBoardingGate()) {
						if (a.getBoardingGate() > b.getBoardingGate())
							comparison = 1;
						else if (a.getBoardingGate() < b.getBoardingGate())
							comparison = -1;
					}
					return comparison;
				}

			});
			long end = System.currentTimeMillis();
			long calc = (end - start) / 1000;
			timeTakenLabel.setText("Time taken to sort: " + calc + " seconds");
			int low = Integer.parseInt(airport.getPositions()[airport.getPage() - 1].split(";")[0]);
			int high = Integer.parseInt(airport.getPositions()[airport.getPage() - 1].split(";")[1]);
			fillList(low, high);
		}
    }

    @FXML
    void sortTime(ActionEvent event) {
    	if (!airport.getFLights().isEmpty()) {
			long start = System.currentTimeMillis();
			Collections.sort(airport.getFLights(), new Comparator<Flight>() {

				@Override
				public int compare(Flight a, Flight b) {
					int comparison = 0;
					if (!a.getFlightTime().equals(b.getFlightTime()))
						comparison = a.getFlightTime().compareTo(b.getFlightTime());
					return comparison;
				}

			});
			long end = System.currentTimeMillis();
			long calc = (end - start) / 1000;
			timeTakenLabel.setText("Time taken to sort: " + calc + " seconds");
			int low = Integer.parseInt(airport.getPositions()[airport.getPage() - 1].split(";")[0]);
			int high = Integer.parseInt(airport.getPositions()[airport.getPage() - 1].split(";")[1]);
			fillList(low, high);
		}
    }

    @FXML
    void searchMenu(ActionEvent event) {
    	ObservableList<String> items = FXCollections.observableArrayList();
    	items.add("Date"); items.add("Time"); items.add("Airline"); items.add("Flight number"); items.add("Destiny"); items.add("Boarding gate");
    	Stage searchMenu = new Stage();
    	searchMenu.initModality(Modality.APPLICATION_MODAL);
    	searchMenu.initOwner(stage);
        VBox searchVbox = new VBox(20);
        searchVbox.setAlignment(Pos.CENTER);
        searchVbox.getChildren().add(new Text("Select the search criteria"));
        ComboBox<String> options = new ComboBox<String>(items);
        options.setPromptText("Search criteria");
        searchVbox.getChildren().add(options);
        searchVbox.getChildren().add(new Text("Enter what you want to search for"));
        TextField value = new TextField(); value.setPromptText("Keyword");
        value.setAlignment(Pos.CENTER);
        searchVbox.getChildren().add(value);
        Button search = new Button("Search");
        search.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent a) {
				if(!options.getValue().isEmpty() && !value.getText().isEmpty()) {
					try {
						int index = -1;
						if(options.getValue().equals("Date"))
							index = airport.search(1, value.getText());
						else if(options.getValue().equals("Time"))
							index = airport.search(2, value.getText());
						else if(options.getValue().equals("Airline"))
							index = airport.search(3, value.getText());
						else if(options.getValue().equals("Flight number"))
							index = airport.search(4, value.getText());
						else if(options.getValue().equals("Destiny"))
							index = airport.search(5, value.getText());
						else
							index = airport.search(5, value.getText());
						if(index == -1) {
							
						}
						else {
							
						}
					} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
						value.clear();
					} 
				}
			}	
        });
        searchVbox.getChildren().add(search);
        Scene dialogScene = new Scene(searchVbox, 300, 200);
        searchMenu.setScene(dialogScene);
        searchMenu.show();
    }
    
    public void setStage(Stage stage) {
    	this.stage = stage;
    }

	public CurrentTimeThread getCT() {
		return cT;
	}   
}
