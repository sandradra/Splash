package characters;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import scene.MyScene;

public class Splash extends Character {

	double playerShipMinX, playerShipMaxX, playerShipMinY, playerShipMaxY;
	public static final String SPLASH_IMAGE = "resources/splash/splash.png";

	
	public Splash(Pane layer, Image image, double x, double y, double dx, double dy) {
		super(layer, image, x, y, dx, dy);
		this.image = MyScene.readImage2(SPLASH_IMAGE);
	}

	// TODO create splash animation
//		// the splash moves upwards
//		Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//				System.out.println("this is called every 5 seconds on UI thread");
//			}
//		}));
//		fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
//		fiveSecondsWonder.play();



	public boolean checkHitRubbish(ArrayList<Rubbish> rubbish) {
		boolean hit = false;
		for(Iterator<Rubbish> it = rubbish.iterator(); it.hasNext(); ) {
			Rubbish r = it.next();
			if (this.collidesWith(r)) {
				r.removeFromLayer();
				it.remove();
				hit = true;
			}else{
				this.dx = 0;
			}
		}
		return hit;
	}

}