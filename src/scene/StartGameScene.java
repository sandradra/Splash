package scene;

import java.util.Random;

import characters.Person;
import characters.Rubbish;
import characters.Seaweed;
import characters.Splash;
import characters.Whale;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;

import java.util.ArrayList;

import javafx.util.Duration;
import scene.MyScene;
import util.EventEmitter;

public class StartGameScene extends MyScene {

	// define image paths
	public static final String GAME_BACKGROUND = "resources/page/game_background.png";
	public static final String WHALE_L         = "resources/whale/whaleL.png";
	public static final String RUBBISH_1       = "resources/rubbish/rubbish1.png";
	public static final String SEAWEED         = "resources/platform/platform.png";
	public static final String SPLASH          = "resources/splash/splash.png";


	public static final String GAME_OVER = "game_over";
	
	public static Whale whale;
	public ArrayList<Rubbish> rubbishes = new ArrayList<Rubbish>();
	public static ArrayList<Seaweed> seaweeds  = new ArrayList<Seaweed>();
	public Splash splash;
	public Image whaleImage, rubbishImage, seaweedImage, splashImage;
	public ImageView bgImage;
	public static int score = 0;
	public int numberOfSeaweedRemoved;
	public static boolean gameOver = false;
	public Random random = new Random();
	public Pane gamePane;
	public Scene theScene;
	public Timeline shoot;
	public Person person;

	public StartGameScene() {
		this.emitterMap.put(GAME_OVER, new EventEmitter<Object>());
	}
	
	@Override
	public Scene createScene() {

		Group group = new Group();
		gamePane    = new Pane();
		bgImage = readImage(GAME_BACKGROUND);
		gamePane.getChildren().add(bgImage);
		group.getChildren().addAll(gamePane);

		theScene = new Scene(group, COVER_WIDTH, COVER_HEIGHT);

		createSplashTimeline();

		rubbishes.add(createRubbish());
		seaweeds = createSeaweeds(seaweeds);
		whale    = createWhale();

		whale.getMoveEmitter().subscribe(whale -> handleWhaleFallsDown(whale, score));

		moveWhaleOnKeyPress(theScene);
		stopWhaleOnKeyRelease(theScene);

		return theScene;

	}

	public Whale createWhale() {
		whaleImage  = readImage2(WHALE_L);
		double x    = (475 - whaleImage.getWidth()) / 2.0;
		double y    = (600 * 0.85);
		Whale whale = new Whale(gamePane, whaleImage, x, y, 0, 0);
		return whale;
	}

	public Rubbish createRubbish() {
		rubbishImage = readImage2(RUBBISH_1);
		double x     = (double)(random.nextInt(250) + 5);
		double y     = (double)(random.nextInt(250));  // set range
		Rubbish r    = new Rubbish(gamePane, rubbishImage, x, y, 0.0, 0.0);
		return r;
	}

	private Seaweed createSeaweed(int part) {
		// part:  0: top, 1: middle, 2: bottom

		seaweedImage = readImage2(SEAWEED);

		double x = (double)(random.nextInt(350) + 50);
		double y = (double)(random.nextInt(90) + part * 90);

		return new Seaweed(gamePane, seaweedImage, x, y, 0, 0);
	}

	public ArrayList<Seaweed> createSeaweeds(ArrayList<Seaweed> seaweeds) {

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 2; j++) {
				seaweeds.add(createSeaweed(i));
			}
		}
		return seaweeds;
	}

	public void createSplash() {
		splashImage = readImage2(SPLASH);
		//	splash.add(new Splash(gamePane, splashImage, whale.getCenterX()-whale.w*0.5, (whale.getCenterY()-whale.h*0.5)-10, 0.0, 1.0));
		splash      = new Splash(gamePane, splashImage, whale.getCenterX()-whale.w*0.5, (whale.getCenterY()-whale.h*0.5)-10, 0.0, 1.0);
	}

	public void moveWhaleOnKeyPress(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case UP:
					whale.jump();
					loopBg();
					checkhitRubbish();

					break;
				case LEFT:
					whale.left();
					checkhitRubbish();
					break;
				case RIGHT:
					whale.right();
					checkhitRubbish();
					break;
				case SPACE:
					if (splash != null && !checkSplashInScreen()
					|| splash == null) {
						createSplash();
						shoot();
					}
					break;
				default:
					break;
				}
			}
		});
	}

	public void stopWhaleOnKeyRelease(Scene scene) {
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case LEFT:
					whale.stopLeft();
					break;

				case RIGHT:
					whale.stopRight();
					break;

				default:
					break;
				}
			}
		});
	}

	public void loopBg() {
		if (whale.y <= 150) {
			whale.setY(400);

			for (Seaweed s : seaweeds) {
				s.setY((s.getY()+250));
				s.updateUI();
				if (s.getY()>COVER_HEIGHT) {
					s.removeFromLayer();
					seaweeds.remove(s);
					numberOfSeaweedRemoved++;
				}
			}
		}

		// create number of seaweed previously removed
		for (int i = 0; i<numberOfSeaweedRemoved; i++) {
			seaweeds.add(createSeaweed(0)); // 0: top
		}

		numberOfSeaweedRemoved = 0;
	}

	public void checkhitRubbish() {
		if (whale.checkHitRubbish(rubbishes)) {
			rubbishes.add(createRubbish());
			score += 50;
			System.out.println(score);
		}
	}

	public void createSplashTimeline() {
		EventHandler<ActionEvent> eventHandler = e -> {
			if (splash.y > -20) {
				splash.move(0, -100);
				splash.updateUI();
				splash.checkHitRubbish(rubbishes);
				System.out.println(splash.y);
			}
		};

		shoot = new Timeline(new KeyFrame(Duration.millis(500), eventHandler));
		shoot.setCycleCount(Timeline.INDEFINITE);
	}

	public void shoot() {
		shoot.play();
	}

	public boolean checkSplashInScreen() {
		return splash.y > -20;
	}
	
	public void handleWhaleFallsDown(Whale whale, int score) {
		
		// TODO add checkSplashHitRubbish function to end game
		if (!whale.checkWhaleInScreen()) {
			whale.fall.setOnFinished(event -> {
				Platform.runLater(() -> 
				this.emitterMap.get(GAME_OVER).emit(score));
				return;
			});
			
			return;
		}
		
		whale.checkHittedSeaweed(whale.findSeaweedBelowWhale(StartGameScene.seaweeds, StartGameScene.whale));
	}
}
