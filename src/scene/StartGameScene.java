package scene;

import java.util.Random;
import java.io.File;
import java.util.Iterator;
import characters.Person;
import characters.Rubbish;
import characters.Seaweed;
import characters.Splash;
import characters.Whale;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
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

public class StartGameScene extends MyScene{
	
	// define image paths
	public static final String GAME_BACKGROUND = "resources/page/game_background.png";
	public static final String WHALE_L         = "resources/whale/whaleL.png";
	public static final String RUBBISH_1       = "resources/rubbish/rubbish1.png";
	public static final String SEAWEED         = "resources/platform/platform.png";
	public static final String SPLASH          = "resources/splash/splash.png";

	public static final String GAME_OVER = "game_over";
	
	public static final String SPLASH_SOUND = "resources/sounds/splash.wav";
	
	public static Whale whale;
	public static ArrayList<Rubbish> rubbishes = new ArrayList<Rubbish>();
	public static ArrayList<Seaweed> seaweeds  = new ArrayList<Seaweed>();
	public ArrayList<Splash> splash  = new ArrayList<Splash>();
	public Image whaleImage, rubbishImage, seaweedImage, splashImage;
	public ImageView bgImage, bgImage1;
	public static int score = 0;
	public int numberOfSeaweedRemoved;
	public static boolean gameOver = false;
	public boolean removeRubbish = false;
	public Random random = new Random();
	public Label label1 = new Label("YOUR SCORE:");	
	public Label label2 = new Label();
	public Pane gamePane;
	public Scene theScene;
	public Timeline shoot;
	public Timeline loop;
	public Person person;
	public MediaPlayer mediaPlayer;

	public StartGameScene() {
		this.emitterMap.put(GAME_OVER, new EventEmitter<Object>());
	}
	
	@Override
	public Scene createScene(){

		Group group = new Group();
		gamePane   = new Pane();
		bgImage = readImage(GAME_BACKGROUND);
		label1.setFont(Font.font("Cambria", 32));		
		label2.setFont(Font.font("Cambria", 32));
		label1.relocate(0, 0);
		label2.relocate(200, 0);
		gamePane.getChildren().addAll(bgImage,label1,label2);
		group.getChildren().addAll(gamePane);
			    
		theScene = new Scene(group, COVER_WIDTH, COVER_HEIGHT);		
		
		gameLoop();
		createSplashTimeline();

		rubbishes.add(createRubbish(250));
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

	public Rubbish createRubbish(int setY) {
		rubbishImage = readImage2(RUBBISH_1);
		int set = 0;
		if (setY >100) {
			set= setY;
		}
		double x     = (double)(random.nextInt(250) + 5);
		double y     = (double)(random.nextInt(set));  // set range
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
		splash.add(new Splash(gamePane, splashImage, whale.getCenterX()-whale.w*0.5, (whale.getCenterY()-whale.h*0.5)-10, 0.0, 1.0));
	}

	public void moveWhaleOnKeyPress(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override 
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case UP:
					whale.jump();
					score += 1; 
					if (whale.y < 250) {
					playLoop();
					}
					label2.setText(Integer.toString(score));
					break;
				case LEFT:					
					whale.left();
					label2.setText(Integer.toString(score));
					break;
				case RIGHT:
					whale.right();
					label2.setText(Integer.toString(score));
					break;
				case SPACE:
					createSplash();					
					playShoot();
					playSound();
					label2.setText(Integer.toString(score));
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
	
	public void gameLoop() {   // to smooth the animation of scrolling background
		EventHandler<ActionEvent> eventHandler = e -> {
		  	whale.setY(whale.getY()+100);
		  	whale.updateUI();
		    	Iterator<Seaweed> itSeaweed = seaweeds.iterator();
		    while (itSeaweed.hasNext()) {
		    		Seaweed temp = (Seaweed) itSeaweed.next();
		    	  	temp.setY((temp.getY()+100));
		    	  	temp.updateUI();
		    	  	if (temp.getY()>550) {
		    	  		temp.removeFromLayer();
		    	  		itSeaweed.remove();
		    	  		numberOfSeaweedRemoved++;
		    	  	}
		    }
		    
	    		Iterator<Rubbish> itRubbish = rubbishes.iterator();
		    while (itRubbish.hasNext()) {
	    		Rubbish temp = (Rubbish) itRubbish.next();
	    	  	temp.setY((temp.getY()+100));
	    	  	temp.updateUI();
		    	  	if (temp.getY()>550) {
		    	  		temp.removeFromLayer();
		    	  		itRubbish.remove();
		    	  		removeRubbish = true;
		    	  	}
		    }	
		    for (int i=0; i<numberOfSeaweedRemoved; i++) {
					seaweeds.add(createSeaweed(0)); // 0: top
			}
		    
		    if(removeRubbish) {
				rubbishes.add(createRubbish((int)whale.y));
				removeRubbish = false;
		    }
		    
		  	score += 100;
			numberOfSeaweedRemoved = 0;
		};				
			loop = new Timeline(new KeyFrame(Duration.millis(500), eventHandler));
			loop.setCycleCount(5);
	 }

	public void playLoop() {
		loop.play();
	}
	
	public void playSound() {
		Media media = new Media(new File(SPLASH_SOUND).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setAutoPlay(true);
	}
		
	public void createSplashTimeline() {

		EventHandler<ActionEvent> eventHandler = e -> {
			for(Iterator<Splash> it = splash.iterator(); it.hasNext(); ) {
				Splash s = it.next();
				if (s.y > -50) { // buffer
					s.move(0, -20); // smoothen out the animation
					s.updateUI();
					if(s.checkHitRubbish(rubbishes)) {
						s.removeFromLayer();
						it.remove();
						rubbishes.add(createRubbish(250));
						score += 50;
					}
				}
			};
		};
		shoot = new Timeline(new KeyFrame(Duration.millis(50), eventHandler)); // smoothen out the animation
		shoot.setCycleCount(Timeline.INDEFINITE);
	}			
	
	public void playShoot() {
		shoot.play();
	}

	public void handleWhaleFallsDown(Whale whale,int score) {
		
		// add checkSplashHitRubbish function to end game
		if (!whale.checkWhaleInScreen()) {
			whale.stopFall();
			Platform.runLater(() -> this.emitterMap.get(GAME_OVER).emit(score));
		}

		// check if the whale hits seaweed
		whale.checkHittedSeaweed(whale.findSeaweedBelowWhale(StartGameScene.seaweeds, StartGameScene.whale));
		
	}

}
