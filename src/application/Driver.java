package application;
import java.io.File;

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
	
	@Override
	public void start(Stage primaryStage) {
		

		// create scenes
		MyScene launchScene      = new LaunchScene();
		MyScene leaderboardScene = new LeaderboardScene();
		MyScene endGameScene     = new EndGameScene();
		MyScene startGameScene   = new StartGameScene();

		//---START OF HANDLING BUTTON CLICK---
		
		launchScene.getEventEmitter(LaunchScene.CLICKED_PLAY_BUTTON)
		  .subscribe(event -> {
//			  MyScene startGameScene   = new StartGameScene();
			  primaryStage.setScene(startGameScene.getScene());
		  });
		
		launchScene.getEventEmitter(LaunchScene.CLICKED_RANK_BUTTON)
		  .subscribe(event -> primaryStage.setScene(leaderboardScene.getScene()));
		
		leaderboardScene.getEventEmitter(LeaderboardScene.CLICKED_HOME_BUTTON)
		  .subscribe(event -> primaryStage.setScene(launchScene.getScene()));
		
		startGameScene.getEventEmitter(StartGameScene.GAME_OVER)
		  .subscribe(score -> {((EndGameScene) endGameScene).setScore((int) score);
			  primaryStage.setScene(endGameScene.getScene());
		  });
		
		endGameScene.getEventEmitter(LeaderboardScene.CLICKED_HOME_BUTTON)
		.subscribe(event -> primaryStage.setScene(launchScene.getScene()));
		
		endGameScene.getEventEmitter(LeaderboardScene.CLICKED_RANK_BUTTON)
		  .subscribe(event -> primaryStage.setScene(leaderboardScene.getScene()));
		
		//---END OF HANDLING BUTTON CLICK---
		
		primaryStage.setTitle(GAME_TITLE); 
		primaryStage.setResizable(false);
		primaryStage.setScene(launchScene.getScene()); // Place the scene in the stage
		primaryStage.show(); 
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
