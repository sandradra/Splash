package application;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Driver extends Application {

	public static final int COVER_WIDTH = 475;
	public static final int COVER_HEIGHT = 600;
	public static final String GAME_TITLE = "Let's Splash!";
	
	
	
	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {
		
		LaunchScene launchScene = new LaunchScene();

		primaryStage.setTitle(GAME_TITLE); 
		primaryStage.setResizable(false);
		primaryStage.setScene(launchScene); // Place the scene in the stage
		primaryStage.show(); 
	}
	

			
	public static void main(String[] args) {
		launch(args);
	}
}
