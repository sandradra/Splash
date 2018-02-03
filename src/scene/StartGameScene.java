package scene;

import application.Game;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class StartGameScene extends MyScene{

	@Override
	public Scene createScene() {
		
		// TODO create game object and get the game running
		Game game = new Game();
		Pane startGamePane = new Pane();
		Scene startGameScene = new Scene(startGamePane, COVER_WIDTH, COVER_HEIGHT);
		return startGameScene;
	}
}
