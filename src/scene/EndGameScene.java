package scene;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import characters.Person;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import util.EventEmitter;

public class EndGameScene extends MyScene {

	public static final String END_GAME_IMAGE = "resources/page/end_game_page.png";
	public static final String HOME_BUTTON = "resources/button/home_button_white.png";
	
	private int score = 0;

	// TODO look for player score 
	public void setScore(int score) {
		this.score = score;
	}
	
	public EndGameScene() {
		super();
		this.emitterMap.put(CLICKED_RANK_BUTTON, new EventEmitter<Object>());
		this.emitterMap.put(CLICKED_HOME_BUTTON, new EventEmitter<Object>());
	}
	
	@Override
	public Scene createScene() {
		
		StackPane endGamePane = new StackPane();
		endGamePane.getChildren().add(MyScene.readImage(END_GAME_IMAGE));
		List<Person> persons = LeaderboardScene.createLeadersFromFile(new File(SCORE_FILE));
		writeNameAndScores(persons, score);
		endGamePane.getChildren().add(displayScore(score));
		endGamePane.getChildren().add(getHomeAndRankHBox());
		
		Scene endGameScene = new Scene(endGamePane, COVER_WIDTH, COVER_HEIGHT);
		return endGameScene;
	}
	
	public VBox getHomeAndRankHBox() {

		VBox homeAndRankVBox = new VBox(10);
		
		/*
		 *  create Home Button
		 */
		ImageView homeButton = MyScene.readImage(HOME_BUTTON);
		homeButton.setOnMouseClicked(event -> {  emitterMap.get(CLICKED_HOME_BUTTON).emit(event);  });
		homeButton.setOnMouseMoved(event -> homeButton.setImage(MyScene.readImage2(HOME_BUTTON_MOUSEOVER)));
		homeButton.setOnMouseExited(event -> homeButton.setImage(MyScene.readImage2(HOME_BUTTON)));
		
		homeAndRankVBox.getChildren().add(homeButton);		
		
		ImageView rankButton = MyScene.readImage(RANK_BUTTON);
		rankButton.setOnMouseClicked(event -> emitterMap.get(CLICKED_RANK_BUTTON).emit(event));
		rankButton.setOnMouseMoved(event -> rankButton.setImage(MyScene.readImage2(RANK_BUTTON_MOUSEOVER)));
		rankButton.setOnMouseExited(event -> rankButton.setImage(MyScene.readImage2(RANK_BUTTON)));
		homeAndRankVBox.getChildren().add(rankButton);
		
		homeAndRankVBox.setPadding(new Insets(COVER_HEIGHT * 0.6, 0, 0, COVER_WIDTH * 0.15));
		
		return homeAndRankVBox;
	}
	
	// write scores on text files
	public void writeNameAndScores(List<Person> persons, int score) {

		
//		String name = JOptionPane.showInputDialog("Well done! Please enter your name: ");
		TextInputDialog dialog = new TextInputDialog("your name");
		dialog.setTitle("Splash");
		dialog.setHeaderText("Well done!");
		dialog.setContentText("Please enter your name: ");
		
		Optional<String> name = dialog.showAndWait();
		
		if (!name.isPresent()) {
			return;
		}
		
		persons.add(new Person(name.get(), score));

		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		
		try {

			fileWriter = new FileWriter(SCORE_FILE, true);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(name.get() + ", " + score + "\n");

			System.out.println("Done");
			
		} catch (IOException e) {
			e.printStackTrace();

		} finally {

			try {

				if (bufferedWriter != null)
					bufferedWriter.close();

				if (fileWriter != null)
					fileWriter.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public Text displayScore(int score) {
		
		Text scoreText = new Text(COVER_WIDTH * 0.8, COVER_HEIGHT / 2, String.valueOf(score));
		scoreText.setFont(Font.loadFont(HOBO_FONT_TYPE, 30));
		scoreText.setFill(Color.rgb(26, 173, 204));
		return scoreText;
	}
	
	public GridPane displayHomeAndRankButton() {
		
		GridPane homeAndScoresPane = new GridPane();
		Button homeButton = new Button("", MyScene.readImage(HOME_BUTTON));
		homeButton.setOnMouseClicked(event -> {  emitterMap.get(CLICKED_HOME_BUTTON).emit(event);  });
		homeAndScoresPane.add(homeButton, 0, 0);
		
		Button rankButton = new Button("", MyScene.readImage(HOME_BUTTON));
		rankButton.setOnMouseClicked(event -> {  emitterMap.get(CLICKED_RANK_BUTTON).emit(event);  });
		
		homeAndScoresPane.add(rankButton, 0, 1);
		return homeAndScoresPane;
	}
			
}
