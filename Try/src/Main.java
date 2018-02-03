import java.util.Random;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.event.ActionEvent;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;

import java.util.ArrayList;
import java.util.Iterator;
import javafx.util.Duration;

public class Main extends Application {
	Whale whale;
	ArrayList<Rubbish> rubbish = new ArrayList<Rubbish>(); 
	ArrayList<Seaweed> seaweed = new ArrayList<Seaweed>();
	ArrayList<Splash> splash = new ArrayList<Splash>();
	Image whaleImage, rubbishImage, seaweedImage,splashImage;  
	ImageView bgImage;
	int score, createSeaweed;
	Random r = new Random();
	Pane gamePane;
	Scene theScene;

	@Override
	public void start(Stage theStage) {

		Group group = new Group();
		gamePane = new Pane();
		bgImage = new ImageView(new Image("images/bg.png"));
		gamePane.getChildren().add(bgImage);
		group.getChildren().addAll(gamePane);

		theScene = new Scene(group,475,600);
		theStage.setTitle( "Splash!");
		theStage.setScene(theScene);        
		theStage.show();
		
		rubbish.add(createRubbish());
		for (int i = 0; i<12; i++) {seaweed.add(createSeaweed());}
		whale = createWhale(); 

		EventHandler<ActionEvent> eventHandler = e -> {
			moveWhaleOnKeyPress(theScene);
		};

		Timeline animation = new Timeline(new KeyFrame(Duration.millis(500), eventHandler));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play(); 

	}	

	public Whale createWhale() {		
		whaleImage = new Image("images/whaleL.png");
	    double x = (475 - whaleImage.getWidth()) / 2.0;
	    double y = (600 * 0.85);
	    Whale whale = new Whale(gamePane, whaleImage, x, y, 0, 0);
	    return whale;
	}

	public Rubbish createRubbish() {
		rubbishImage = new Image("images/rub1.png");		
	    double x = (double)(r.nextInt(250) + 5); 
        double y = (double)(r.nextInt(250) + 100);  // set range
		Rubbish r = new Rubbish( gamePane, rubbishImage, x,y, 0.0, 0.0);
		return r;
	}
	
	public Seaweed createSeaweed() {
		seaweedImage = new Image("images/platform.png");
		double x = (double)(r.nextInt(350) + 50); 
		double y = (double)(r.nextInt(450)); 
		Seaweed s = new Seaweed(gamePane, seaweedImage,x,y,0,0);
		return s;
	}
	
	public void createSplash() {
		splashImage = new Image("images/Splash.png");
		splash.add(new Splash(gamePane, splashImage, whale.getCenterX(), whale.getCenterY(), 0.0, 1.0));
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
				createSplash();
					
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

	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
