package application;
import java.io.File;
import java.net.URL;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import scene.EndGameScene;
import scene.LaunchScene;
import scene.LeaderboardScene;
import scene.MyScene;
import scene.StartGameScene;

public class Driver extends Application {


	public static final String GAME_TITLE = "Let's Splash!";

	public static final String BACKGROUND_MUSIC = "resources/sounds/home.wav";
	
	@Override
	public void start(Stage primaryStage) {
		
		// play background music once application is launched
		Media backgroundMusic =  new Media(new File(BACKGROUND_MUSIC).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(backgroundMusic);
		mediaPlayer.setOnEndOfMedia(new Runnable() {
			public void run() { mediaPlayer.seek(Duration.ZERO); }
		});
		mediaPlayer.play();

		// create different scenes
		MyScene launchScene      = new LaunchScene();
		MyScene leaderboardScene = new LeaderboardScene();
		MyScene startGameScene   = new StartGameScene();
		MyScene endGameScene     = new EndGameScene();

		//---START OF HANDLING BUTTON CLICK---
		
		launchScene.getEventEmitter(LaunchScene.CLICKED_PLAY_BUTTON)
		  .subscribe(event -> primaryStage.setScene(startGameScene.getScene()));
		
		launchScene.getEventEmitter(LaunchScene.CLICKED_RANK_BUTTON)
		  .subscribe(event -> primaryStage.setScene(leaderboardScene.getScene()));
		
		leaderboardScene.getEventEmitter(LeaderboardScene.CLICKED_HOME_BUTTON)
		  .subscribe(event -> primaryStage.setScene(launchScene.getScene()));
		
		endGameScene.getEventEmitter(LeaderboardScene.CLICKED_HOME_BUTTON)
		.subscribe(event -> primaryStage.setScene(launchScene.getScene()));
		
		endGameScene.getEventEmitter(LeaderboardScene.CLICKED_RANK_BUTTON)
		  .subscribe(event -> primaryStage.setScene(leaderboardScene.getScene()));
		
//		TODO add event trigger to endGameScene
		
		//---END OF HANDLING BUTTON CLICK---
		
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
		
		//whale 60* 49
		//seaweed 68*20
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
