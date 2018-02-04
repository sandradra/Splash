package scene;

import java.util.Random;

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

import java.util.ArrayList;
import java.util.Iterator;
import javafx.util.Duration;
import scene.MyScene;

public class StartGameScene extends MyScene {

	Whale whale;
	ArrayList<Rubbish> rubbish = new ArrayList<Rubbish>();
	ArrayList<Seaweed> seaweed = new ArrayList<Seaweed>();
	//ArrayList<Splash> splash = new ArrayList<Splash>();
	Splash splash;
	Image whaleImage, rubbishImage, seaweedImage, splashImage;
	ImageView bgImage;
	int score, createSeaweed;
	Random r = new Random();
	Pane gamePane;
	Scene theScene;

	// define image paths
	public static final String GAME_BACKGROUND = "resources/page/game_background.png";
	public static final String WHALE_L = "resources/whale/whaleL.png";
	public static final String RUBBISH_1 = "resources/rubbish/rubbish1.png";
	public static final String SEAWEED = "resources/platform/platform.png";
	public static final String SPLASH = "resources/splash/splash.png";

	@Override
	public Scene createScene() {

		Group group = new Group();
		gamePane = new Pane();
		bgImage = readImage(GAME_BACKGROUND);
		gamePane.getChildren().add(bgImage);
		group.getChildren().addAll(gamePane);

		theScene = new Scene(group, COVER_WIDTH, COVER_HEIGHT);

		rubbish.add(createRubbish());
		for (int i = 0; i<12; i++) {seaweed.add(createSeaweed());}
		whale = createWhale();

		EventHandler<ActionEvent> eventHandler = e -> {
			moveWhaleOnKeyPress(theScene);
		};

		Timeline animation = new Timeline(new KeyFrame(Duration.millis(500), eventHandler));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();

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
		double x     = (double)(r.nextInt(250) + 5);
		double y     = (double)(r.nextInt(250) + 100);  // set range
		Rubbish r    = new Rubbish(gamePane, rubbishImage, x,y, 0.0, 0.0);
		return r;
	}

	public Seaweed createSeaweed() {
		seaweedImage = readImage2(SEAWEED);
		double x     = (double)(r.nextInt(350) + 50);
		double y     = (double)(r.nextInt(450));
		Seaweed s    = new Seaweed(gamePane, seaweedImage,x,y,0,0);
		return s;
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
					whale.move(0,-20);
					whale.updateUI();
					loopBg();
					checkhitRubbish();
					break;
				case LEFT:
					whale.move(-20,0);
					whale.updateUI();
					checkhitRubbish();
					break;
				case RIGHT:
					whale.move(20,0);
					whale.updateUI();
					checkhitRubbish();
					break;
				case DOWN:
					whale.move(0,20);
					whale.updateUI();
					checkhitRubbish();
					break;
				case SPACE:
					System.out.println("StartGameScene.moveWhaleOnKeyPress(...).new EventHandler() {...}.handle()");
					createSplash();
					System.out.println("shoot");
					shoot();
					break;
				}
			}
		});
	}

	public void loopBg() {
		if (whale.y <= 150) {
			whale.setY(400);
			Iterator it;
			it = seaweed.iterator();
			while (it.hasNext()) {
				Seaweed temp = (Seaweed) it.next();
				temp.setY((temp.getY()+250));
				temp.updateUI();
				if (temp.getY()>550) {
					temp.removeFromLayer();
					it.remove();
					createSeaweed++;
				}
			}
		}
		for (int i = 0; i<createSeaweed; i++) {
			seaweed.add(createSeaweed());
		}
		createSeaweed = 0;
	}

	public void checkhitRubbish() {
		if (whale.checkHitRubbish(rubbish)) {
			rubbish.add(createRubbish());
			score += 50;
			System.out.println(score);
		}
	}

	public void shoot() {
		EventHandler<ActionEvent> eventHandler = e -> {
			if (splash.y > -10) {
				splash.move(0, -100);
				splash.updateUI();
				splash.checkHitRubbish(rubbish);
				checkhitRubbish();
				System.out.println(splash.y);
			}
		};
		Timeline shoot = new Timeline(new KeyFrame(Duration.millis(500), eventHandler));
		shoot.setCycleCount(Timeline.INDEFINITE);
		shoot.play();
	}

}

