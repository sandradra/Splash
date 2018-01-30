package scene;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class LaunchScene extends MyScene{


	public static final String COVER_IMAGE = "resources/images/sea_cover.png";
	public static final String PLAY_BUTTON = "resources/images/play_button.png";
	public static final String SCORES_BUTTON = "resources/images/scores_button.png";
	
	@Override
	// TODO handle fileNotFoundException
	public Scene createScene() throws FileNotFoundException {
		BorderPane launchPane = new BorderPane();
		launchPane.getChildren().add(MyScene.readImage(COVER_IMAGE));
		launchPane.setTop(getPlayAndScoresHBox());
		Scene launchScene = new Scene(launchPane, COVER_WIDTH, COVER_HEIGHT);
		return launchScene;
	}

	public HBox getPlayAndScoresHBox() throws FileNotFoundException {
		
		// create HBox to store buttons "Play" and "Scores"
		HBox playAndScores = new HBox(COVER_WIDTH * 0.2);
		playAndScores.setPadding(new Insets(COVER_HEIGHT * 0.825, COVER_WIDTH * 0.15, COVER_HEIGHT * 0.125, COVER_WIDTH * 0.15));
		Image playButton   = new Image(new FileInputStream(PLAY_BUTTON));
		Image scoresButton = new Image(new FileInputStream(SCORES_BUTTON));
		playAndScores.getChildren().add(new Button("", new ImageView(playButton)));
		playAndScores.getChildren().add(new Button("", new ImageView(scoresButton)));
		return playAndScores;

	}

}
