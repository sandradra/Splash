package application;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import scene.LaunchScene;
import scene.MyScene;

public class Driver extends Application {


	public static final String GAME_TITLE = "Let's Splash!";
	
	
	
	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {
		
//		AudioClip introMusic =  new AudioClip("sound/mystery.wav");
//		introMusic.play();
		
		
		MyScene launchScene = new LaunchScene();

		primaryStage.setTitle(GAME_TITLE); 
		primaryStage.setResizable(false);
		primaryStage.setScene(launchScene.getScene()); // Place the scene in the stage
		primaryStage.show(); 
		
		
//		ImageView introImage =  new ImageView("images/bg-grid.png");  
//		Button btStart = new Button("",new ImageView("images/doodleR.png"));
//		Button btRecord = new Button("",new ImageView("images/doodleS.png"));  
//		Group mainScreen = new Group(introImage,btStart,btRecord);
//		Scene scene = new Scene(mainScreen, 500, 200);
//		theStage.setTitle("Splash!"); 
//		theStage.setScene(scene);
//		theStage.show();
		
		
	}
	
	//  public void displayScore() {
	//   AudioClip introMusic =  new AudioClip("music/introMusic.mp3");
	//   introMusic.play();
	//   ImageView scoreImage =  new ImageView("image/ScoreImage.jpg");  
	//   Button btBack = new Button("",new ImageView("image/back.jpg"));
	//   StackPane pane = new StackPane();
	//   pane.getChildren().addAll(scoreImage,btBack);   
	//  }

			
	public static void main(String[] args) {
		launch(args);
	}
}
