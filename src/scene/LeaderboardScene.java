package scene;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class LeaderboardScene extends MyScene{

	public static final String LEADERBOARD_IMAGE = "resources/leaderboard.png";
	public static final String BACK_BUTTON = "resouces/back_button.png";
//	private LeaderboardScene leaderboardScene;
//	
//	public LeaderboardScene() {
//		this.leaderboardScene = new LeaderboardScene();
//	}
	
	@Override 
	public Scene createScene() throws FileNotFoundException {
		
		BorderPane leaderboardPane = new BorderPane();
		leaderboardPane.getChildren().add(MyScene.readImage(LEADERBOARD_IMAGE));
		leaderboardPane.setTop(getBack());
		Scene leaderboardScene = new Scene(leaderboardPane, COVER_WIDTH, COVER_HEIGHT);
		return leaderboardScene;
		
	}
	
	// TODO handle FileNotFoundException
	public HBox getBack() throws FileNotFoundException {
		
		/*
		 *  create HBox to display Back Button
		 */
		HBox back = new HBox();
		back.setPadding(new Insets(COVER_HEIGHT * 0.825, COVER_WIDTH / 2, COVER_HEIGHT * 0.125, COVER_WIDTH / 2));
		Button backButton = new Button("", MyScene.readImage(BACK_BUTTON));
		backButton.setOnMouseClicked(event -> {
			// TODO build new scene leaderboard
			System.out.println("Back is clicked");
			
			
		});
		
		
		return back;
	}
}
