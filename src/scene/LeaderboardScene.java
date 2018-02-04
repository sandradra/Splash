package scene;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import characters.Person;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import util.EventEmitter;

public class LeaderboardScene extends MyScene{

	public static final String LEADERBOARD_PAGE = "resources/page/leaderboard_page.png";

	public LeaderboardScene() {
		super();
		this.emitterMap.put(CLICKED_HOME_BUTTON, new EventEmitter<Object>());
	}

	@Override 
	public Scene createScene() {

		StackPane leaderboardPane = new StackPane();

		List<Person> persons = createLeadersFromFile(new File(SCORE_FILE));
		leaderboardPane.getChildren().add(MyScene.readImage(LEADERBOARD_PAGE));
		leaderboardPane.getChildren().add(getLeaderList(persons));
		leaderboardPane.getChildren().add(getHome(COVER_WIDTH * 0.25, COVER_HEIGHT * 0.35));

		Scene leaderboardScene = new Scene(leaderboardPane, COVER_WIDTH, COVER_HEIGHT);
		return leaderboardScene;

	}

	public Node getHome(double x, double y) {

		/*
		 *  create Home Button
		 */
		ImageView homeButton = MyScene.readImage(HOME_BUTTON);
		homeButton.setOnMouseClicked(event -> {  emitterMap.get(CLICKED_HOME_BUTTON).emit(event);  });
		homeButton.setOnMouseMoved(event -> homeButton.setImage(MyScene.readImage2(HOME_BUTTON_MOUSEOVER)));
		homeButton.setOnMouseExited(event -> homeButton.setImage(MyScene.readImage2(HOME_BUTTON)));
		
		homeButton.setTranslateX(x);
		homeButton.setTranslateY(y);
		

		return homeButton;
	}

	public GridPane getLeaderList(List<Person> persons) {

		GridPane leaderListGridPane = new GridPane();
		leaderListGridPane.setPadding(new Insets(COVER_HEIGHT * 0.27, 0, 0, COVER_WIDTH * 0.22));

		for (int i = 0; i < 5; i++) {

			Text name = new Text(persons.get(i).getName());
			name.setFont(Font.loadFont(HOBO_FONT_TYPE, 25));
			name.setWrappingWidth(100);
			name.setTextAlignment(TextAlignment.LEFT);
			name.setFill(Color.rgb(14, 121, 191));

			Text score = new Text(String.valueOf(persons.get(i).getScore()));
			score.setFont(Font.loadFont(HOBO_FONT_TYPE, 25));
			score.setWrappingWidth(COVER_WIDTH / 4);
			score.setTextAlignment(TextAlignment.LEFT);
			score.setFill(Color.rgb(14, 121, 191));

			Text rank = new Text(String.valueOf(i+1));
			rank.setFont(Font.loadFont(HOBO_FONT_TYPE, 25));
			rank.setWrappingWidth(5);
			rank.setTextAlignment(TextAlignment.LEFT);
			rank.setFill(Color.rgb(14, 121, 191));
			
			leaderListGridPane.add(rank, 0, i);
			leaderListGridPane.add(name, 1, i);
			leaderListGridPane.add(score, 2, i);
			leaderListGridPane.setVgap(20);
			leaderListGridPane.setHgap(60);

		}

		return leaderListGridPane;
	}

	public static List<Person> createLeadersFromFile(File fileName) {

		// read list of leader text file 
		List<String> lines = new ArrayList<String>();

		Scanner scanner;
		try {
			scanner = new Scanner(fileName); 

			while(scanner.hasNextLine()){ lines.add(scanner.nextLine()); }
			scanner.close(); 

		} catch (FileNotFoundException e) { System.out.println("file is not found."); }  

		String[] splitLines = lines.toArray(new String[0]);

		// split lines to cells
		String[][] cells = new String[splitLines.length][];

		for (int i = 0; i < splitLines.length; i++) {
			cells[i] = splitLines[i].split(", "); 
		}

		// create array list of persons
		List<Person> persons = new ArrayList<>();
		for (int i = 0; i < cells.length; i++) {
			persons.add(new Person(cells[i][0], Integer.parseInt(cells[i][1])));
		}

		Collections.sort(persons);

		return persons;
	}

}

