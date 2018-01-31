import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;

public class Main extends Application {
	
    @Override
    public void start(Stage theStage) {
    	
    	theStage.setTitle( "Splash!");
    		
    		Game game = new Game();
        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );
        
        root.getChildren().add(game.startGame());
        
        theStage.show();
    }
    	
    public static void main(String[] args) {
        launch(args);
    }

}
