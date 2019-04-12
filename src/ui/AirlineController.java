package ui;

import model.Airport;
import model.Flight;
import threads.CurrentTimeThread;

import java.io.IOException;
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
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * <b> Laboratorio unidad 4 </b>
 * @author César Canales <br>
 * Universidad Icesi
 */
public class AirlineController {

	/**
	 * The main stage of the fxml file.
	 */
	private Stage stage;
	
	/**
	 * The airport of the controller.
	 */
	private Airport airport;
	
	/**
	 * The current time of the day.
	 */
	private LocalTime curTime;
	
	/**
	 * The thread that advances the current time.
	 */
	private CurrentTimeThread cT;

    /**
     * The label that shows the current time on screen.
     */
    @FXML
    private Label currentTimeLabel;

    /**
     * The label that shows the number of pages on screen.
     */
    @FXML
    private Label pageNumberLabel;

    
    /**
     * A textfield that reads the number of flights.
     */
    @FXML
    private TextField numberOfFlights;
    
    /**
     * The main VBox of the scene.
     */
    @FXML
    private VBox mainVBox;

    /**
     * The title HBox of the stage.
     */
    @FXML
    private HBox titleHBox;
    
    /**
     * The right button of the pagination.
     */
    @FXML
    private Button rightButton;
    
    /**
     * The left button of the pagination.
     */
    @FXML
    private Button leftButton;
    
    /**
     * The listview that shows all the airport's flights.
     */
    @FXML
    private ListView<GridPane> flightsList;
    
    /**
     * The textfield that allows to change the current page.
     */
    @FXML
    private TextField pageTextField;
    
    /**
     * The label that shows how much time it took to search or sort.
     */
    @FXML
    private Label timeTakenLabel;
    
    /**
     * This function initializes all the elements of the GUI.
     */
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
    
    /**
     * This function is triggered when the left page button is pressed.
     * @param event The event that triggered this function to be called.
     */
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
    
    /**
     * This function is triggered when the right page button is pressed.
     * @param event The event that triggered this function to be called.
     */
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
    
    /**
     * This function changes the current time.
     */
    public void changeTime() {
    	curTime = curTime.plusSeconds(1);
    	currentTimeLabel.setText(DateTimeFormatter.ofPattern("hh:mm:ss a").format(curTime));
    }
    
    /**
     * This function is triggered when the generate button is pressed.
     * @param event The event that triggered this function to be called.
     */
    @FXML
    void generateListView(ActionEvent event) {
    	numberOfFlights.clear();
    	Label title = new Label("Enter the number of flights you want to generate");
    	title.setFont(Font.font("Regular", 25));
    	title.setStyle("-fx-font-weight: bold");
    	numberOfFlights.setVisible(true);
    	Button generateButton = new Button("Generate");
    	generateButton.setStyle("-fx-font-weight: bold");
    	generateButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent a) {
				boolean in = false;
				try {
					long start = System.currentTimeMillis();
					airport.generateFlights(Integer.parseInt(numberOfFlights.getText()));
					long diff = (System.currentTimeMillis() - start)/1000;
					timeTakenLabel.setText("Time taken to generate: " + diff + " seconds");
					if(Integer.parseInt(numberOfFlights.getText())<0)
						throw new NumberFormatException();
				}catch(NumberFormatException e) {
					in = true;
					numberOfFlights.clear();
					numberOfFlights.setPromptText("Please enter a valid number");
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(!in) {
					mainVBox.getChildren().remove(title);
			    	mainVBox.getChildren().remove(generateButton);
					numberOfFlights.setVisible(false);
					titleHBox.setVisible(true);
					Collections.sort(airport.getFLights());
					int low = Integer.parseInt(airport.getPositions()[airport.getPage()-1].split(";")[0]);
			    	int high = Integer.parseInt(airport.getPositions()[airport.getPage()-1].split(";")[1]);
			    	pageNumberLabel.setText("Number of pages: " + airport.getPositions().length);
					fillList(low, high);
				}
			}
    		
    	});
    	mainVBox.getChildren().add(1, title);
    	mainVBox.getChildren().add(3, generateButton);
    }
    
    /**
     * This function fills the list view depending on the lower and upper bounds.
     * @param low The lower bound of the list.
     * @param high The upper bound of the list.
     */
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
    
    /**
     * This function is triggered when the sort airline button is pressed.
     * @param event The event that triggered this function to be called.
     */
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

    /**
     * This function is triggered when the sort date button is pressed.
     * @param event The event that triggered this function to be called.
     */
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

    /**
     * This function is triggered when the sort destination button is pressed.
     * @param event The event that triggered this function to be called.
     */
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

    /**
     * This function is triggered when the sort flight number button is pressed.
     * @param event The event that triggered this function to be called.
     */
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

    /**
     * This function is triggered when the sort gate button is pressed.
     * @param event The event that triggered this function to be called.
     */
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

    /**
     * This function is triggered when the sort time button is pressed.
     * @param event The event that triggered this function to be called.
     */
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

    /**
     * This function is triggered when the search button is pressed.
     * @param event The event that triggered this function to be called.
     */
    @FXML
    void searchMenu(ActionEvent event) {
    	ObservableList<String> items = FXCollections.observableArrayList();
    	items.add("Date(day/month/year)"); items.add("Time(hour:minute A.M/P.M)"); items.add("Airline"); items.add("Flight number"); items.add("Destiny"); items.add("Boarding gate");
    	Stage searchMenu = new Stage();
    	searchMenu.initModality(Modality.APPLICATION_MODAL);
    	searchMenu.initOwner(stage);
        VBox searchVbox = new VBox(20);
        searchVbox.setAlignment(Pos.CENTER);
        searchVbox.getChildren().add(new Label("Select the search criteria"));
        ComboBox<String> options = new ComboBox<String>(items);
        options.setPromptText("Search criteria");
        searchVbox.getChildren().add(options);
        searchVbox.getChildren().add(new Label("Enter what you want to search for(Case sensitive)"));
        TextField value = new TextField(); value.setPromptText("Keyword");
        value.setAlignment(Pos.CENTER);
        searchVbox.getChildren().add(value);
        Button search = new Button("Search");
        search.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent a) {
				if(options.getValue()!=null && !options.getValue().isEmpty() && !value.getText().isEmpty()) {
					try {
						Flight index = null;
						long start = System.currentTimeMillis();
						if(options.getValue().equals("Date(day/month/year)"))
							index = airport.search(1, value.getText());
						else if(options.getValue().equals("Time(hour:minute A.M/P.M)"))
							index = airport.search(2, value.getText());
						else if(options.getValue().equals("Airline"))
							index = airport.search(3, value.getText());
						else if(options.getValue().equals("Flight number"))
							index = airport.search(4, value.getText());
						else if(options.getValue().equals("Destiny"))
							index = airport.search(5, value.getText());
						else
							index = airport.search(6, value.getText());
						long end = System.currentTimeMillis();
						long diff = (end-start)/1000;
						Stage item = new Stage();
                        item.initModality(Modality.APPLICATION_MODAL);
                        item.initOwner(searchMenu);
                        if(index == null) {
                             VBox itemVbox = new VBox(20);
                             itemVbox.setAlignment(Pos.CENTER);
                             itemVbox.getChildren().add(new Label("No item was found"));
                             itemVbox.getChildren().add(new Label("Time taken to search: " + diff + " seconds"));
                             Scene itemScene = new Scene(itemVbox, 400, 200);
                             itemScene.getStylesheets().add("uiImg/Style.css");
                             item.setScene(itemScene);
                             item.show();
                        }
                        else {
                        	  VBox itemVbox = new VBox(20);
                              itemVbox.setAlignment(Pos.CENTER);
                              Label la = new Label("Airline: ");
                              la.setContentDisplay(ContentDisplay.RIGHT);
                              String airline = "uiImg/american-logo.jpg";
                              if(index.getAirline().equals(Flight.AVIANCA))
                            	  airline = "uiImg/Avianca-logo.jpg";
                              else if(index.getAirline().equals(Flight.COPA))
                            	  airline = "uiImg/copa.png";
                              else if(index.getAirline().equals(Flight.IBERIA))
                            	  airline = "uiImg/iberia-logo.png";
                              else if(index.getAirline().equals(Flight.JETBLUE))
                            	  airline = "uiImg/jetblue-airways-vector-logo.png";
                              else if(index.getAirline().equals(Flight.UNITED))
                            	  airline = "uiImg/united.png";
                              ImageView im = new ImageView(airline);
                              im.setFitHeight(200); im.setFitWidth(500);
                              la.setGraphic(im);
                              itemVbox.getChildren().add(new Label("Date: " + index.getFlightDate()));
                              itemVbox.getChildren().add(new Label("Time: " + index.getFlightTime()));
                              itemVbox.getChildren().add(la);
                              itemVbox.getChildren().add(new Label("Flight number: " + index.getFlightNumber()));
                              itemVbox.getChildren().add(new Label("Destination: " + index.getDestination()));
                              itemVbox.getChildren().add(new Label("Boarding gate: " + index.getBoardingGate()));
                              itemVbox.getChildren().add(new Label("Time taken to search: " + diff + " seconds"));
                              Scene itemScene = new Scene(itemVbox, 900, 800);
                              itemScene.getStylesheets().add("uiImg/Style.css");
                              item.setScene(itemScene);
                              item.show();
                        }
					} catch (NumberFormatException | StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
						value.clear();
					} 
				}
			}	
        });
        searchVbox.getChildren().add(search);
        searchVbox.setStyle("uiImg/Style.css");
        Scene dialogScene = new Scene(searchVbox, 600, 400);
        dialogScene.getStylesheets().add("uiImg/Style.css");
        searchMenu.setScene(dialogScene);
        searchMenu.show();
    }
    
    /**
     * This function modifies the controller's stage.
     * @param stage The stage to set.
     */
    public void setStage(Stage stage) {
    	this.stage = stage;
    }

	/**
	 * This function obtains the current time thread.
	 * @return The current time thread.
	 */
	public CurrentTimeThread getCT() {
		return cT;
	}   
}
