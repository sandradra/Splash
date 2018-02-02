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
import javafx.event.ActionEvent;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.util.Duration;

public class Main extends Application {
	Whale whale;
	ArrayList<Rubbish> rubbish = new ArrayList<Rubbish>(); 
	ArrayList<Seaweed> seaweed = new ArrayList<Seaweed>();
	ArrayList<Splash> splash = new ArrayList<Splash>();
	int score = 0;
	Image whaleImage; 
	Image rubbishImage;
	Image seaweedImage;
	Image splashImage;  
	ImageView bgImage;
	int createRubbish = 0;
	int createSeaweed = 0;
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
	
	public void loadGame() {
		AnimationTimer bgLoop = new AnimationTimer() {
			public void handle(long l) {
				for (Seaweed i:seaweed) {
					i.y = ((i.h)+200);
					i.updateUI();
				}	
			}
	        };
	        bgLoop.start();
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

	public void moveWhaleOnKeyPress(Scene scene) {
		KeyListener keylistener = new KeyListener();
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case UP: createRubbish = keylistener.upKey(whale, rubbish,seaweed, score);
				seaweed = loopBg();		
			    for (int i = 0; i< createRubbish; i++) {
			    	rubbish.add(createRubbish());
			    }
			    createRubbish = 0;
			    for (int i = 0; i<createSeaweed; i++) {
			    	seaweed.add(createSeaweed());
			    }
			    createSeaweed = 0;
			    break;
				case LEFT: score = keylistener.leftKey(whale, rubbish, score);break;
				case RIGHT: score = keylistener.rightKey(whale, rubbish, score);break;
				case SPACE: break;
				}
			}
		});
	}
	
	public ArrayList<Seaweed> loopBg() {
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
	    return seaweed;
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
