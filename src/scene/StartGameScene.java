package scene;

import java.util.Random;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;

import java.io.File;
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
	public static final String GAME_BACKGROUND_MUSIC   = "resources/sounds/play.wav";
	public static final String WHALE_DROP_SOUND_EFFECT = "resources/sounds/drop.wav";

	public static final String GAME_OVER = "game_over";
	
	public static Media gameBackgroundMusic                   = new Media(new File(GAME_BACKGROUND_MUSIC).toURI().toString());
	public static MediaPlayer gameBackgroundMusicMediaPlayer  = new MediaPlayer(gameBackgroundMusic);
	
	public static Media whaleDropSoundEffect                  = new Media(new File(WHALE_DROP_SOUND_EFFECT).toURI().toString());
	public static MediaPlayer whaleDropSoundEffectMediaPlayer = new MediaPlayer(whaleDropSoundEffect);
	
	public static Whale whale;
	public static ArrayList<Rubbish> rubbishes = new ArrayList<Rubbish>();
	public ArrayList<Seaweed> seaweeds  = new ArrayList<Seaweed>();
	public static ArrayList<Splash> splash     = new ArrayList<Splash>();
	public Image whaleImage, rubbishImage, seaweedImage, splashImage;
	public ImageView bgImage, bgImage1;
	public static int score = 0;
	public int numberOfSeaweedRemoved;
	public static boolean gameOver = false;
	public boolean removeRubbish = false;
	public Random random = new Random();
	public Label label   = new Label();
	public HBox hBox     = new HBox();
	public Pane gamePane;
	public Scene theScene;
	public Timeline shoot;
	public Timeline loop;
	public Person person;

	public StartGameScene() {
		this.emitterMap.put(GAME_OVER, new EventEmitter<Object>());
	}
	
	@Override
	public Scene createScene() {

		gameBackgroundMusicMediaPlayer.setOnEndOfMedia(() -> gameBackgroundMusicMediaPlayer.seek(Duration.ZERO) );
		gameBackgroundMusicMediaPlayer.play();
		
		Group group = new Group();
		gamePane   = new Pane();
		bgImage = readImage(GAME_BACKGROUND);
		//String record = "YOUR SCORE:";
		label.setFont(Font.font("Cambria", 32));
		gamePane.getChildren().addAll(bgImage,label);
		group.getChildren().addAll(gamePane);

		theScene = new Scene(group, COVER_WIDTH, COVER_HEIGHT);

		createSplashTimeline();

		rubbishes.add(createRubbish(250));
		seaweeds = createSeaweeds(seaweeds);
		whale    = createWhale();

		whale.getMoveEmitter().subscribe(whale -> handleWhaleMove(score));

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
		if (setY >10) {
			int set= setY;
		}
		double x     = (double)(random.nextInt(250) + 5);
		double y     = (double)(random.nextInt(setY));  // set range
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
			@Override public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case UP:
					whale.jump();
					score += 1; // +1 score whenever jump
					if (whale.y < 250) {
					loop = gameLoop();
					loop.play();
					}
					break;
				case LEFT:					
					whale.left();
					break;
				case RIGHT:
					whale.right();
					break;
				case SPACE:
					createSplash();
					shoot = createSplashTimeline();
					shoot.play();
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
	
	public Timeline gameLoop() {
		EventHandler<ActionEvent> eventHandler = e -> {
		  	whale.setY(whale.getY()+50);
		  	whale.updateUI();
		    	Iterator itSeaweed = seaweeds.iterator();
		    while (itSeaweed.hasNext()) {
		    		Seaweed temp = (Seaweed) itSeaweed.next();
		    	  	temp.setY((temp.getY()+50));
		    	  	temp.updateUI();
		    	  	if (temp.getY()>550) {
		    	  		temp.removeFromLayer();
		    	  		itSeaweed.remove();
		    	  		numberOfSeaweedRemoved++;
		    	  	}
		    }
		    
	    		Iterator itRubbish = rubbishes.iterator();
		    while (itRubbish.hasNext()) {
	    		Rubbish temp = (Rubbish) itRubbish.next();
	    	  	temp.setY((temp.getY()+50));
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
		    
		  	score += 50;
			numberOfSeaweedRemoved = 0;
		};				
			loop = new Timeline(new KeyFrame(Duration.millis(500), eventHandler)); //500
			loop.setCycleCount(3);
			return loop;
	 }

	public Timeline createSplashTimeline() {

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
						System.out.println(score);
						System.out.println(splash.size());					
					}
				}
			};
		};
		shoot = new Timeline(new KeyFrame(Duration.millis(50), eventHandler)); // smoothen out the animation
		shoot.setCycleCount(Timeline.INDEFINITE);
		return shoot;
	}			

	public void handleWhaleMove(int score) {

		whale.toFront();
		
		// TODO add checkSplashHitRubbish function to end game
		if (!whale.checkWhaleInScreen()) {
			whale.fall.stop();
				gameBackgroundMusicMediaPlayer.stop();
				whaleDropSoundEffectMediaPlayer.play();
				Platform.runLater(() -> 
					this.emitterMap.get(GAME_OVER).emit(score));
			return;
		}
		
		
		// land on platforms
		whale.checkHittedSeaweed(whale.findSeaweedBelowWhale(seaweeds, whale));
		
		// TODO allow the whale to drop once it's no longer on a platform
//			while (
//				whale.left.getStatus().equals(Status.RUNNING)
//				|| whale.right.getStatus().equals(Status.RUNNING)
//			) {
//				if (!whale.collidesWith(seaweed)){
//					whale.fall();
//				}
//			}
			
	}

//	public void onSeaweed (Seaweed seaweed) {
//		return (seaweed.x <= whale.x + whale.w)
//					&& (whale.x <= seaweed.x + seaweed.w)
//					&& (seaweed.y - seaweed.h <= whale.y)
//					&& (whale.y <= seaweed.y + seaweed.h);
//		
//	}
	
}
