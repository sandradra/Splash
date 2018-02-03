package scene;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import util.EventEmitter;


public class LaunchScene extends MyScene{

	public static final String COVER_IMAGE = "resources/page/home_page.png";
	public static final String PLAY_BUTTON = "resources/button/play_button_white.png";
	public static final String PLAY_BUTTON_MOUSEOVER = "resources/button/play_button_blue.png";
	
	
	
	public static final String CLICKED_RANK_BUTTON = "clicked_rank_button";
	public static final String CLICKED_PLAY_BUTTON = "clicked_play_button"; 
		
	public LaunchScene() {
		super();
		this.emitterMap.put(CLICKED_RANK_BUTTON, new EventEmitter<Object>());
		this.emitterMap.put(CLICKED_PLAY_BUTTON, new EventEmitter<Object>());
	}
	
	
	@Override
	public Scene createScene() {
		BorderPane launchPane = new BorderPane();
		launchPane.getChildren().add(MyScene.readImage(COVER_IMAGE));
		launchPane.setTop(getPlayAndScoresHBox());
		Scene launchScene = new Scene(launchPane, COVER_WIDTH, COVER_HEIGHT);
		return launchScene;
	}

	public HBox getPlayAndScoresHBox() {
		
		/*
		 *  create HBox to store buttons "Play" and "Scores"
		 */
		HBox playAndScores = new HBox(COVER_WIDTH * 0.1);
		playAndScores.setPadding(new Insets(COVER_HEIGHT * 0.75, 0, 0, COVER_WIDTH * 0.15));
		
		ImageView playButtonImageView   = MyScene.readImage(PLAY_BUTTON);
		ImageView rankButtonImageView = MyScene.readImage(RANK_BUTTON);
		
		/*
		 *  set-up "Play" button
		 */
		playButtonImageView.setOnMouseClicked(event -> emitterMap.get(CLICKED_PLAY_BUTTON).emit(event));
		playButtonImageView.setOnMouseMoved(event -> playButtonImageView.setImage(MyScene.readImage2(PLAY_BUTTON_MOUSEOVER)));
		playButtonImageView.setOnMouseExited(event -> playButtonImageView.setImage(MyScene.readImage2(PLAY_BUTTON)));
		
		playAndScores.getChildren().add(playButtonImageView);
		
		
		/*
		 *  set-up "Scores" button
		 */
		rankButtonImageView.setOnMouseClicked(event -> emitterMap.get(CLICKED_RANK_BUTTON).emit(event));
		rankButtonImageView.setOnMouseMoved(event -> rankButtonImageView.setImage(MyScene.readImage2(RANK_BUTTON_MOUSEOVER)));
		rankButtonImageView.setOnMouseExited(event -> rankButtonImageView.setImage(MyScene.readImage2(RANK_BUTTON)));
		
		playAndScores.getChildren().add(rankButtonImageView);
		
		return playAndScores;

	}
	

}
