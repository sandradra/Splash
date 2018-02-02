import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import java.util.Random;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

public class Main extends Application {
	Double x,y;
	Rubbish rubbish;
	Whale whale;
	Seaweed seaweed[] = new Seaweed[12];
	Splash splash;
	Image whaleImage, rubbishImage, seaweedImage, splashImage;  
	ImageView bgImage;
	Random r = new Random();
	Pane gamePane;
	Scene theScene;

	@Override
	public void start(Stage theStage) {

		Group group = new Group();
		gamePane = new Pane();
		bgImage = new ImageView(new Image("images/page/home.png"));
		gamePane.getChildren().add(bgImage);
		group.getChildren().add(gamePane);

		theScene = new Scene(group,475,600);
		theStage.setTitle( "Splash!");
		theStage.setScene(theScene);        
		theStage.show();

		whale = createWhale(); 
		rubbish = createRubbish();
		seaweed = createSeaweed();

		EventHandler<ActionEvent> eventHandler = e -> {
			moveWhaleOnKeyPress(theScene);
		};

		Timeline animation = new Timeline(new KeyFrame(Duration.millis(500), eventHandler));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play(); 
	}

	public Whale createWhale() {		
		whaleImage = new Image("images/whaleL.png");
	x = (Settings.SCENE_WIDTH - whaleImage.getWidth()) / 2.0;
	y = Settings.SCENE_HEIGHT * 0.7;
	Whale whale = new Whale(gamePane, whaleImage, x, y, 0, 0);
	return whale;
	}

	public Rubbish createRubbish() {
		rubbishImage = new Image("images/rub2.png");
		x = (double)(r.nextInt(250) + 5); 
		y = (double)(r.nextInt(250) + 200);   // set range
		Rubbish rubbish = new Rubbish( gamePane, rubbishImage, x, y, 0, 0);
		return rubbish;
	}
	
	public Seaweed[] createSeaweed() {
		seaweedImage = new Image("images/platform.png");
		for (int i = 0; i<12; i++) {
			x = r.nextDouble() * 475;
			y = r.nextDouble() * 600;
			seaweed[i] = new Seaweed(gamePane, seaweedImage,x,y,0,0);
		}
		return seaweed;
	}

	private void moveWhaleOnKeyPress(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case UP: 
					whale.dy = -5;
					whale.move();
					whale.updateUI();
					System.out.println("whale.y: "+ whale.y );		
					System.out.println(seaweed[1].w);
					System.out.println(whale.w);					
					System.out.println(rubbish.w);
					break;
				case LEFT:
					whale.dx = -5;
					whale.move();
					whale.updateUI();
					System.out.println("whale.x: "+ whale.x );
					break;
				case RIGHT:
					whale.dx = 5;
					whale.move();
					whale.updateUI();
					System.out.println("whale.x: "+ whale.x );
					break;
				case SPACE: 
					//shoot = true; 
					break;
				}
			}
		});
	}

	public void checkCollide() {
	}

	public static void main(String[] args) {
		launch(args);
	}

}
