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


public class LaunchScene {


	public static final String COVER_IMAGE = "resources/images/sea_cover.png";
	public static final String PLAY_BUTTON = "resources/images/play_button.png";
	public static final String SCORES_BUTTON = "resources/images/scores_button.png";

	// TODO 
	BorderPane coverPane = new BorderPane();
	Image coverImage   = new Image(new FileInputStream(COVER_IMAGE));
	coverPane.getChildren().add(new ImageView(coverImage));
	coverPane.setTop(getPlayAndScoresHBox());

	// Create a scene and place a pane in the scene
	Scene startScene = new Scene(coverPane, COVER_WIDTH, COVER_HEIGHT);

	public HBox getPlayAndScoresHBox() throws FileNotFoundException {
		//			HBox playAndScores = new HBox(COVER_WIDTH * 0.25);
		HBox playAndScores = new HBox(COVER_WIDTH * 0.2);
		//			playAndScores.setPadding(new Insets(COVER_HEIGHT * 0.875, COVER_WIDTH * 0.25, COVER_HEIGHT * 0.125, COVER_WIDTH * 0.25));
		playAndScores.setPadding(new Insets(COVER_HEIGHT * 0.825, COVER_WIDTH * 0.15, COVER_HEIGHT * 0.125, COVER_WIDTH * 0.15));
		Image playButton   = new Image(new FileInputStream(PLAY_BUTTON));
		Image scoresButton = new Image(new FileInputStream(SCORES_BUTTON));
		playAndScores.getChildren().add(new Button("", new ImageView(playButton)));
		playAndScores.getChildren().add(new Button("", new ImageView(scoresButton)));
		return playAndScores;

	}

}
